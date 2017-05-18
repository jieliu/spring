package com.tianma.controller.websocket;

import com.tianma.model.websocket.MessageInfo;
import org.junit.Test;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by zuowenxia on 2017/5/2.
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class WebsocketControllerTest {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Test
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