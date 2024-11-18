package com.pax.market.api.sdk.java.api.terminal.dto;



import java.io.Serializable;

/**
 * Sending terminal message request
 * @author shifan
 * @date 2024/11/16
 */

public class TerminalMessageRequest implements Serializable {

    private static final long serialVersionUID = -4933767270727671426L;

    private String title;
    private String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
