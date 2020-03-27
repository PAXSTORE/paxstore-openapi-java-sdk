/*
 * ********************************************************************************
 * COPYRIGHT
 *               PAX TECHNOLOGY, Inc. PROPRIETARY INFORMATION
 *   This software is supplied under the terms of a license agreement or
 *   nondisclosure agreement with PAX  Technology, Inc. and may not be copied
 *   or disclosed except in accordance with the terms in that agreement.
 *
 *      Copyright (C) 2017 PAX Technology, Inc. All rights reserved.
 * ********************************************************************************
 */
package com.pax.market.api.sdk.java.api.terminalGroupApk.dto;

import com.pax.market.api.sdk.java.api.terminalApk.dto.FileParameter;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author: Shawn
 * @Date: 2019/11/29 16:09
 * @Version 1.0
 */
@Getter
@Setter
public class CreateTerminalGroupApkRequest implements Serializable {

    private static final long serialVersionUID = -5645272376227693380L;

    @NotNull
    private Long groupId;

    private String pushTemplateName;

    @NotBlank
    private String packageName;

    private String version;

    private String templateName;

    private Map<String, String> parameters;
    private List<FileParameter> base64FileParameters;


}
