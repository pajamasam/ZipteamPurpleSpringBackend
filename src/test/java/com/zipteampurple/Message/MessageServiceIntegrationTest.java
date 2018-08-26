package com.zipteampurple.Message;

import com.zipteampurple.Entity.Message;
import com.zipteampurple.Service.MessageService;
import com.zipteampurple.Service.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class MessageServiceIntegrationTest {

    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messageService;

    private final String admin = "nmaidanos";
    private final String user  = "jmoney";
    private Message message;



    @Before
    @WithMockUser(admin)
    public void beforeSetup(){
        Message message = new Message();
        message.setMessage("Hello");
        message.setChannelId(1L);
        this.message = this.messageService.save(message);
    }

    @After
    @WithMockUser(user)
    public void afterSetup(){
        this.messageService.delete(this.message);
    }

    @Test
    @WithMockUser(admin)
    public void saveMessage(){
        assertEquals(this.message.getUser().getUsername(), "nmaidanos");
        assertNotNull(this.message.getId());
        assertEquals(this.message.getMessage(), "Hello");
    }

    @Test
    @WithMockUser(admin)
    public void deleteMessageByCreator(){
        boolean deleteResult =  this.messageService.delete(this.message);
        assertTrue(deleteResult);
    }

    @Test
    @WithMockUser(admin)
    public void updateMessage(){

        String newMessage = "NewMessage";
        assertNull(this.message.getUpdated());
        this.message.setMessage(newMessage);

        this.message = this.messageService.update(this.message);

        assertNotNull(this.message.getUpdated());
        assertEquals(this.message.getMessage(), newMessage);
    }

// TODO Still need to find out how to Test this delete message feature
//    @Test
//    @WithMockUser(user)
//    public void deleteMessageByNotUser(){
//        System.out.println(this.message.getUser().getUsername());
//        boolean deleteResult =  this.messageService.delete(this.message);
//        assertFalse(deleteResult);
//        System.out.println(deleteResult);
//    }



}


