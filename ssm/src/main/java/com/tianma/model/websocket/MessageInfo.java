package com.tianma.model.websocket;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by jie on 17-2-19.
 */
public class MessageInfo implements Serializable {

    private String id;

    private String msg;

    public MessageInfo(String name) {
        this.id = UUID.randomUUID().toString();
        this.msg = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return msg;
    }

    public void setName(String msg) {
        this.msg = msg;
    }
}
