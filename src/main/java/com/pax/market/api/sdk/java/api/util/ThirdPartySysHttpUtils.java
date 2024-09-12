/*
 * *******************************************************************************
 * COPYRIGHT
 *               PAX TECHNOLOGY, Inc. PROPRIETARY INFORMATION
 *   This software is supplied under the terms of a license agreement or
 *   nondisclosure agreement with PAX  Technology, Inc. and may not be copied
 *   or disclosed except in accordance with the terms in that agreement.
 *
 *      Copyright (C) 2017 PAX Technology, Inc. All rights reserved.
 * *******************************************************************************
 */
package com.pax.market.api.sdk.java.api.util;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.pax.market.api.sdk.java.api.constant.Constants;
import com.pax.market.api.sdk.java.api.constant.ResultCode;
import com.pax.market.api.sdk.java.api.exception.GatewayException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.net.ssl.*;
import java.io.*;
import java.net.*;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;
import java.util.Map.Entry;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import static com.pax.market.api.sdk.java.api.constant.Constants.HTTP_NO_CONTENT;


/**
 * 网络工具类。
 */
public abstract class ThirdPartySysHttpUtils {
	private static final Logger logger = LoggerFactory.getLogger(ThirdPartySysHttpUtils.class);

	private static final int BUFFER_SIZE = 4096;
	private static final String DEFAULT_CHARSET = Constants.CHARSET_UTF8;
	private static Locale locale = Locale.CHINA;

	/**
     * Sets local.
     *
     * @param locale the locale
     */
    public static void setLocal(Locale locale) {
		ThirdPartySysHttpUtils.locale = locale;
	}

    /**
     * The type Trust all trust manager.
     */
    public static class TrustAllTrustManager implements X509TrustManager {
		public X509Certificate[] getAcceptedIssuers() {
			return null;
		}

		public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		}

		public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		}
	}

	private ThirdPartySysHttpUtils() {
	}

    /**
     * Request string.
     *
     * @param requestUrl     the request url
     * @param requestMethod  the request method
     * @param connectTimeout the connect timeout
     * @param readTimeout    the read timeout
     * @param userData       the user data
     * @param headerMap      the header map
     * @return the string
     */
    public static String request(String requestUrl, String requestMethod, int connectTimeout, int readTimeout, String userData, Map<String, String> headerMap, int retryTimes){
//		return request(requestUrl, requestMethod, connectTimeout, readTimeout, userData, false, headerMap, null);
		return request(requestUrl, requestMethod, connectTimeout, readTimeout, userData, headerMap, null, retryTimes);
	}

    /**
     * Compress request string.
     *
     * @param requestUrl     the request url
     * @param requestMethod  the request method
     * @param connectTimeout the connect timeout
     * @param readTimeout    the read timeout
     * @param userData       the user data
     * @param headerMap      the header map
     * @return the string
     */
    public static String compressRequest(String requestUrl, String requestMethod, int connectTimeout, int readTimeout, String userData, Map<String, String> headerMap, int retryTimes){
		return compressRequest(requestUrl, requestMethod, connectTimeout, readTimeout, userData, headerMap, null, retryTimes);
	}

    /**
     * Request string.
     *
     * @param requestUrl     the request url
     * @param requestMethod  the request method
     * @param connectTimeout the connect timeout
     * @param readTimeout    the read timeout
     * @param userData       the user data
     * @param headerMap      the header map
     * @param saveFilePath   the save file path
     * @return the string
     */
    public static String request(String requestUrl, String requestMethod, int connectTimeout, int readTimeout, String userData, Map<String, String> headerMap, String saveFilePath, int retryTimes){
//		return request(requestUrl, requestMethod, connectTimeout, readTimeout, userData, false, headerMap, saveFilePath);
		try {
			return RetryUtils.retry(() -> {
				return request(requestUrl, requestMethod, connectTimeout, readTimeout, userData, false, headerMap, saveFilePath);
			}, e -> isExceptionShouldRetry(e), retryTimes);
		} catch (GatewayException e) {
			if(e.getResponseCode() == 502) {
				return EnhancedJsonUtils.getSdkJson(ResultCode.BAD_GATEWAY);
			}else{
				return EnhancedJsonUtils.getSdkJson(ResultCode.GATEWAY_TIMEOUT);
			}
		} catch (Exception e) {
			logger.error("Exception Occurred", e);
			if(e instanceof IOException) {
				return EnhancedJsonUtils.getSdkJson(ResultCode.SDK_UN_CONNECT);
			}else{
				return EnhancedJsonUtils.getSdkJson(ResultCode.SDK_RQUEST_EXCEPTION);
			}
		} finally {
			if(StringUtils.isNotBlank(saveFilePath)) {
				FileUtils.deleteFile(saveFilePath);
			}
		}
	}

    /**
     * Compress request string.
     *
     * @param requestUrl     the request url
     * @param requestMethod  the request method
     * @param connectTimeout the connect timeout
     * @param readTimeout    the read timeout
     * @param userData       the user data
     * @param headerMap      the header map
     * @param saveFilePath   the save file path
     * @return the string
     */
    public static String compressRequest(String requestUrl, String requestMethod, int connectTimeout, int readTimeout, String userData, Map<String, String> headerMap, String saveFilePath, int retryTimes){
		try {
			return RetryUtils.retry(() -> {
				return request(requestUrl, requestMethod, connectTimeout, readTimeout, userData, true, headerMap, saveFilePath);
			}, e -> isExceptionShouldRetry(e), retryTimes);
		} catch (GatewayException e) {
			if(e.getResponseCode() == 502) {
				return EnhancedJsonUtils.getSdkJson(ResultCode.BAD_GATEWAY);
			}else{
				return EnhancedJsonUtils.getSdkJson(ResultCode.GATEWAY_TIMEOUT);
			}
		}catch (Exception e) {
			logger.error("Exception Occurred", e);
			return EnhancedJsonUtils.getSdkJson(ResultCode.SDK_UN_CONNECT);
		}finally{
			if(StringUtils.isNotBlank(saveFilePath)) {
				FileUtils.deleteFile(saveFilePath);
			}
		}
	}

	private static boolean isExceptionShouldRetry(Throwable e) {
    	if(e instanceof ConnectException){
    		return true;
		} else if(e instanceof SocketTimeoutException){
    		return true;
		} else if(e instanceof GatewayException && ((GatewayException) e).getResponseCode() == 502) {
			return true;
		}else{
    		return false;
		}
	}

	private static String request(String requestUrl, String requestMethod, int connectTimeout, int readTimeout, String userData, boolean compressData,
								  Map<String, String> headerMap, String saveFilePath) throws ConnectException, SocketTimeoutException, GatewayException {
		HttpURLConnection urlConnection = null;
		try {
			urlConnection = getConnection(requestUrl, connectTimeout, readTimeout);
			return finalRequest(urlConnection, requestMethod, userData, compressData, headerMap, saveFilePath);
		} catch (IOException e) {
			if(e instanceof ConnectException){
				throw (ConnectException)e;
			}else if(e instanceof SocketTimeoutException){
				throw (SocketTimeoutException)e;
			}
			logger.error("IOException Occurred", e);
		} finally {
			if(urlConnection != null) {
				urlConnection.disconnect();
			}
		}
		return EnhancedJsonUtils.getSdkJson(ResultCode.SDK_RQUEST_EXCEPTION);
	}

	private static String finalRequest(HttpURLConnection urlConnection, String requestMethod, String userData, boolean compressData,
									   Map<String, String> headerMap, String saveFilePath) throws ConnectException,SocketTimeoutException, GatewayException{
		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader bufferedReader = null;
		FileOutputStream fileOutputStream = null;
		String filePath = null;
		try {
			urlConnection.setDoInput(true);
			urlConnection.setUseCaches(false);
			urlConnection.setRequestMethod(requestMethod);
			if(locale != null) {
				urlConnection.setRequestProperty(Constants.ACCESS_LANGUAGE, getLanguageTag(locale));
			}
			if (headerMap != null) {
				for (Entry<String, String> entry : headerMap.entrySet()) {
					urlConnection.setRequestProperty(entry.getKey(), entry.getValue());
				}
			}
			if ("GET".equalsIgnoreCase(requestMethod)) {
				urlConnection.connect();
			} else {
				urlConnection.setDoOutput(true);
			}
			
			String rateLimit = "";
			String rateLimitRemain = "";
			String rateLimitReset = "";
			
			if ((null != userData) && (userData.length() > 0)) {
				OutputStream outputStream = null;
				try {
					outputStream = urlConnection.getOutputStream();
					if (!compressData) {
						outputStream.write(userData.getBytes(DEFAULT_CHARSET));
					} else {
						String hexString = AlgHelper.bytes2HexString(compressData(userData.getBytes(DEFAULT_CHARSET)));
						outputStream.write(hexString.getBytes());
					}
					Map<String, List<String>> map = urlConnection.getHeaderFields();
					rateLimit = map.get("X-RateLimit-Limit")==null?"":map.get("X-RateLimit-Limit").get(0);
					rateLimitRemain = map.get("X-RateLimit-Remaining")==null?"":map.get("X-RateLimit-Remaining").get(0);
					rateLimitReset = map.get("X-RateLimit-Reset")==null?"":map.get("X-RateLimit-Reset").get(0);
		
					
				} finally {
					if (outputStream != null) {
						outputStream.close();
					}
				}
			}

			if(saveFilePath != null) {
				filePath = saveFilePath + File.separator + FileUtils.generateMixString(16) ;

				File fileDir = new File(saveFilePath);
				if(!fileDir.exists()) {
					fileDir.mkdirs();
				}
				fileOutputStream = new FileOutputStream(filePath);

				int bytesRead;
				byte[] buffer = new byte[BUFFER_SIZE];
				while ((bytesRead = urlConnection.getInputStream().read(buffer)) != -1) {
					fileOutputStream.write(buffer, 0, bytesRead);
				}



				return EnhancedJsonUtils.getSdkJson(ResultCode.SUCCESS, filePath);
			}
			Map<String, List<String>> map = urlConnection.getHeaderFields();
			rateLimit = map.get("X-RateLimit-Limit")==null?"":map.get("X-RateLimit-Limit").get(0);
			rateLimitRemain = map.get("X-RateLimit-Remaining")==null?"":map.get("X-RateLimit-Remaining").get(0);
			rateLimitReset = map.get("X-RateLimit-Reset")==null?"":map.get("X-RateLimit-Reset").get(0);
			List<String> contentTypeHeaders = map.get("Content-Type");
			String contentType = contentTypeHeaders!=null && contentTypeHeaders.size()>0?contentTypeHeaders.get(0):"";
			int responseCode = urlConnection.getResponseCode();
			if (responseCode == 200 || responseCode == 201 || responseCode == 204) {
				bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), DEFAULT_CHARSET));
			} else if(responseCode == 502) {
				throw new GatewayException(502, "Encounter 502 response code");
			} else if(responseCode == 504) {
				throw new GatewayException(504, "Encounter 504 response code");
			} else {
				if(urlConnection.getErrorStream() != null) {
					bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getErrorStream(), DEFAULT_CHARSET));
				} else {
					bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), DEFAULT_CHARSET));
				}
			}

			String str;
			while ((str = bufferedReader.readLine()) != null) {
				stringBuilder.append(str);
			}
			String resultStr = stringBuilder.toString();
			if(!StringUtils.containsIgnoreCase(contentType, "json") && (responseCode !=  HTTP_NO_CONTENT) ) {
				logger.error("Response code{}, ContentType {} is not supported", responseCode, contentType);
				if(StringUtils.isNotBlank(resultStr)) {
					logger.error("Response content={}", resultStr);
				}
				return EnhancedJsonUtils.getSdkJson(ResultCode.SDK_JSON_ERROR);
			}
			JsonObject json;
			if(StringUtils.isBlank(resultStr) && responseCode ==  HTTP_NO_CONTENT) {
				json = new JsonObject();
			}else {
				json = JsonParser.parseString(resultStr).getAsJsonObject();
			}
			json.addProperty("rateLimit", rateLimit);
			json.addProperty("rateLimitRemain", rateLimitRemain);
			json.addProperty("rateLimitReset", rateLimitReset);
			return json.toString();
		} catch (SocketTimeoutException localSocketTimeoutException) {
			if(StringUtils.containsIgnoreCase(localSocketTimeoutException.toString(),"Read timed out")){
				FileUtils.deleteFile(filePath);
				logger.error("SocketTimeoutException Occurred. Details: {}", localSocketTimeoutException.toString());
				return EnhancedJsonUtils.getSdkJson(ResultCode.SDK_CONNECT_TIMEOUT);
			}else{
				throw localSocketTimeoutException;
			}
		} catch (ConnectException localConnectException) {
			throw localConnectException;
		} catch (FileNotFoundException fileNotFoundException) {
			FileUtils.deleteFile(filePath);
			logger.error("FileNotFoundException Occurred. Details: {}", fileNotFoundException.toString());
			return EnhancedJsonUtils.getSdkJson(ResultCode.SDK_FILE_NOT_FOUND);
		} catch(GatewayException e) {
			throw e;
		}catch (Exception ignored) {
			FileUtils.deleteFile(filePath);
			logger.error("Exception Occurred. Details: {}", ignored.toString());
		} finally {
			if(bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					logger.error("IOException Occurred. Details: {}", e.toString());
				}
			}
			if(fileOutputStream != null){
				try {
					fileOutputStream.close();
				} catch (IOException e) {
					logger.error("IOException Occurred. Details: {}", e.toString());
				}
			}
			if(urlConnection != null) {
				urlConnection.disconnect();
			}
		}

		return EnhancedJsonUtils.getSdkJson(ResultCode.SDK_RQUEST_EXCEPTION);
	}

	private static HttpURLConnection getConnection(String requestUrl, int connectTimeout, int readTimeout) throws IOException {
		URL url = new URL(requestUrl);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		if (conn instanceof HttpsURLConnection) {
			HttpsURLConnection connHttps = (HttpsURLConnection) conn;
			try {
				SSLContext ctx = SSLContext.getInstance("TLS");
				ctx.init(null, new TrustManager[] { new TrustAllTrustManager() }, new SecureRandom());
				connHttps.setSSLSocketFactory(ctx.getSocketFactory());
				connHttps.setHostnameVerifier(new HostnameVerifier() {
					public boolean verify(String hostname, SSLSession session) {
						return true;
					}
				});
			} catch (GeneralSecurityException e){
				logger.error("GeneralSecurityException Occurred. Details: {}", e.toString());
			}
			connHttps.setHostnameVerifier(new HostnameVerifier() {
				public boolean verify(String hostname, SSLSession session) {
					return true;
				}
			});
			conn = connHttps;
		}

		conn.setConnectTimeout(connectTimeout);
		conn.setReadTimeout(readTimeout);
		return conn;
	}

	private static URL buildGetUrl(String url, String query) throws IOException {
		if (StringUtils.isEmpty(query)) {
			return new URL(url);
		}

		return new URL(buildRequestUrl(url, query));
	}

    /**
     * Build request url string.
     *
     * @param url     the url
     * @param queries the queries
     * @return the string
     */
    public static String buildRequestUrl(String url, String... queries) {
		if (queries == null || queries.length == 0) {
			return url;
		}

		StringBuilder newUrl = new StringBuilder(url);
		boolean hasQuery = url.contains("?");
		boolean hasPrepend = url.endsWith("?") || url.endsWith("&");

		for (String query : queries) {
			if (!StringUtils.isEmpty(query)) {
				if (!hasPrepend) {
					if (hasQuery) {
						newUrl.append("&");
					} else {
						newUrl.append("?");
						hasQuery = true;
					}
				}
				newUrl.append(query);
				hasPrepend = false;
			}
		}
		return newUrl.toString();
	}

    /**
     * Build query string.
     *
     * @param params  the params
     * @param charset the charset
     * @return the string
     * @throws IOException the io exception
     */
    public static String buildQuery(Map<String, String> params, String charset) throws IOException {
		if (params == null || params.isEmpty()) {
			return null;
		}

		StringBuilder query = new StringBuilder();
		Set<Entry<String, String>> entries = params.entrySet();
		boolean hasParam = false;

		for (Entry<String, String> entry : entries) {
			String name = entry.getKey();
			String value = entry.getValue();
			// 忽略参数名或参数值为空的参数
			if (StringUtils.areNotEmpty(name, value)) {
				if (hasParam) {
					query.append("&");
				} else {
					hasParam = true;
				}

				query.append(name).append("=").append(URLEncoder.encode(value, charset));
			}
		}

		return query.toString();
	}

    /**
     * Gets response as string.
     *
     * @param conn the conn
     * @return the response as string
     * @throws IOException the io exception
     */
    protected static String getResponseAsString(HttpURLConnection conn) throws IOException {
		String charset = getResponseCharset(conn.getContentType());
		if (conn.getResponseCode() < 400) {
			String contentEncoding = conn.getContentEncoding();
			if (Constants.CONTENT_ENCODING_GZIP.equalsIgnoreCase(contentEncoding)) {
				return getStreamAsString(new GZIPInputStream(conn.getInputStream()), charset);
			} else {
				return getStreamAsString(conn.getInputStream(), charset);
			}
		} else {// Client Error 4xx and Server Error 5xx
			throw new IOException(conn.getResponseCode() + " " + conn.getResponseMessage());
		}
	}

    /**
     * Gets stream as string.
     *
     * @param stream  the stream
     * @param charset the charset
     * @return the stream as string
     * @throws IOException the io exception
     */
    public static String getStreamAsString(InputStream stream, String charset) throws IOException {
		try {
			Reader reader = new InputStreamReader(stream, charset);
			StringBuilder response = new StringBuilder();

			final char[] buff = new char[1024];
			int read = 0;
			while ((read = reader.read(buff)) > 0) {
				response.append(buff, 0, read);
			}

			return response.toString();
		} finally {
			if (stream != null) {
				stream.close();
			}
		}
	}

    /**
     * Gets response charset.
     *
     * @param ctype the ctype
     * @return the response charset
     */
    public static String getResponseCharset(String ctype) {
		String charset = DEFAULT_CHARSET;

		if (!StringUtils.isEmpty(ctype)) {
			String[] params = ctype.split(";");
			for (String param : params) {
				param = param.trim();
				if (param.startsWith("charset")) {
					String[] pair = param.split("=", 2);
					if (pair.length == 2) {
						if (!StringUtils.isEmpty(pair[1])) {
							charset = pair[1].trim();
						}
					}
					break;
				}
			}
		}

		return charset;
	}

    /**
     * 使用默认的UTF-8字符集反编码请求参数值。
     *
     * @param value 参数值
     * @return 反编码后的参数值 string
     */
    public static String decode(String value) {
		return decode(value, DEFAULT_CHARSET);
	}

    /**
     * 使用默认的UTF-8字符集编码请求参数值。
     *
     * @param value 参数值
     * @return 编码后的参数值 string
     */
    public static String encode(String value) {
		return encode(value, DEFAULT_CHARSET);
	}

    /**
     * 使用指定的字符集反编码请求参数值。
     *
     * @param value   参数值
     * @param charset 字符集
     * @return 反编码后的参数值 string
     */
    public static String decode(String value, String charset) {
		String result = null;
		if (!StringUtils.isEmpty(value)) {
			try {
				result = URLDecoder.decode(value, charset);
			} catch (IOException e) {
				logger.error("IOException Occurred. Details: {}", e.toString());
			}
		}
		return result;
	}

    /**
     * 使用指定的字符集编码请求参数值。
     *
     * @param value   参数值
     * @param charset 字符集
     * @return 编码后的参数值 string
     */
    public static String encode(String value, String charset) {
		String result = null;
		if (!StringUtils.isEmpty(value)) {
			try {
				result = URLEncoder.encode(value, charset);
			} catch (IOException e) {
				logger.error("IOException Occurred. Details: {}", e.toString());
			}
		}
		return result;
	}

    /**
     * 从URL中提取所有的参数。
     *
     * @param query URL地址
     * @return 参数映射 map
     */
    public static Map<String, String> splitUrlQuery(String query) {
		Map<String, String> result = new HashMap<String, String>();

		String[] pairs = query.split("&");
		if (pairs != null && pairs.length > 0) {
			for (String pair : pairs) {
				String[] param = pair.split("=", 2);
				if (param != null && param.length == 2) {
					result.put(param[0], param[1]);
				}
			}
		}

		return result;
	}

    /**
     * Compress data byte [ ].
     *
     * @param bytes the bytes
     * @return the byte [ ]
     * @throws IOException the io exception
     */
    public static byte[] compressData(byte[] bytes)
			throws IOException {
		if (null == bytes) {
			return null;
		}
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		GZIPOutputStream gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream);
		gzipOutputStream.write(bytes);
		gzipOutputStream.close();
		return byteArrayOutputStream.toByteArray();
	}

	private static String getLanguageTag(Locale locale) {
		if (locale != null) {
			String localeStr = locale.toString();
			return localeStr.replace("_", "-");
		}
		return null;
	}



}
