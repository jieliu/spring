package com.tianma.support;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by zuowenxia on 2017/4/28.
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class EmailUtilsTest {

    @Autowired
    private EmailUtils emailUtils;

    @Test
    public void sendSimpleEmail() throws Exception {
        emailUtils.sendSimpleEmail("835088577@qq.com", "fiboliu@163.com", "测试", "这是一封测试邮件");
        emailUtils.sendSimpleEmail("835088577@qq.com", "fiboliu@163.com", "测试",
                "你好:<br><br><br> <br>best wishes,<br> 刘杰<br> fiboliu@163.com<br>");
    }

    @Test
    public void sendAttachmentsEmail() throws Exception {

    }

    @Test
    public void sendInlineMail() throws Exception {

    }

}