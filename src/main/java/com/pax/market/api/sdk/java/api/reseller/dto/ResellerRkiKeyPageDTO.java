package com.pax.market.api.sdk.java.api.reseller.dto;

import java.io.Serializable;

/**
 * @author liukai
 * @date 2020/2/7
 */
public class ResellerRkiKeyPageDTO implements Serializable {

    private static final long serialVersionUID = -91938953088313394L;
    private String keyId;

    public String getKeyId() {
        return keyId;
    }

    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }

    @Override
    public String toString() {
        return "ResellerRkiKeyPageDTO{" +
                "keyId='" + keyId + '\'' +
                '}';
    }
}
