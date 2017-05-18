package com.tianma.controller.websocket;

import com.tianma.model.websocket.MessageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jie on 17-2-19.
 */
@Controller
public class WebsocketController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @GetMapping("/socket/echo.html")
    public String geSocketEcho() {
        return "screen/websocket/echo";
    }

    @ResponseBody
    @RequestMapping(value = "/sendMessage", method = RequestMethod.POST)
    public void sendMessage() {
        List<MessageInfo> messages = getMessage();
        messagingTemplate.convertAndSend("/user/topic/message", messages);
    }

    private List<MessageInfo> getMessage() {
        List<MessageInfo> list = new ArrayList<MessageInfo>();
        list.add(new MessageInfo("msg-1"));
        list.add(new MessageInfo("msg-2"));
        list.add(new MessageInfo("msg-3"));
        list.add(new MessageInfo("msg-4"));
        return list;
    }
}