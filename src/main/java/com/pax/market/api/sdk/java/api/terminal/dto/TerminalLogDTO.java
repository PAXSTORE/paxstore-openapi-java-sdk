package com.pax.market.api.sdk.java.api.terminal.dto;

import java.io.Serializable;

public class TerminalLogDTO implements Serializable {
    private static final long serialVersionUID = 3718642464407963116L;

    private Long id;
    private String type;
    private String title;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
