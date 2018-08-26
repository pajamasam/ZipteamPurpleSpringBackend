package com.zipteampurple.Channel;

import com.zipteampurple.Entity.Channel;
import com.zipteampurple.Service.ChannelService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class ChannelServiceIntegrationTest {

    @Autowired
    private ChannelService channelService;

    private final String admin = "nmaidanos";
    private final String user  = "jmoney";

    private Channel channel;

    @Before
    public void beforeSetup(){
        Channel channel = new Channel();
        channel.setName("New Channel");
        channel.setPurpose("Just a test channel");
        this.channel = this.channelService.create(channel, false);
    }

    @After
    public void afterSetup(){
        this.channelService.delete(this.channel);
    }

    @Test
    public void creationTest(){
        assertEquals(this.channel.getName(), "New Channel");
        assertEquals(this.channel.getName(),"Just a test channel" );
        assertFalse(this.channel.getDirect());
    }

    @Test
    public void deleteChannel(){
        this.channelService.delete(this.channel);
    }


}
