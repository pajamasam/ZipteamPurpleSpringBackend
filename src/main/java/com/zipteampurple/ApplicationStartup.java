package com.zipteampurple;

import com.zipteampurple.Entity.Channel;
import com.zipteampurple.Entity.User;
import com.zipteampurple.Service.ChannelService;
import com.zipteampurple.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    ChannelService channelService;

    @Autowired
    UserService userService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {


        Channel channel = new Channel();
        channel.setName("General");
        channel.setPurpose("Channel that is created when the server first starts");
        this.channelService.create(channel,false);

        User user = new User();
        user.setFirstName("Nicholas");
        user.setLastName("Maidanos");
        user.setPassword("password");
        user.setUsername("nmaidanos");
        user.setEmail("nmaidanos@gmail.com");
        this.userService.save(user);


        User user2 = new User();
        user2.setFirstName("Jae");
        user2.setLastName("Park");
        user2.setPassword("password");
        user2.setUsername("jmoney");
        user2.setEmail("jaepark@gmail.com");
        this.userService.save(user2);

//        User user2 = new User();
//        user2.setFirstName("Jae");
//        user2.setLastName("Park");
//        user2.setPassword("password1");
//        user2.setUsername("J-Money");
//        user2.setEmail("jaepark@gmail.com");
//        this.userService.save(user2);
//
//        User user3 = new User();
//        user3.setFirstName("Josh");
//        user3.setLastName("Chung");
//        user3.setPassword("password");
//        user3.setUsername("jdot2791");
//        user3.setEmail("jdot2791@gmail.com");
//        this.userService.save(user3);
//
//        User user4 = new User();
//        user4.setFirstName("Ryan");
//        user4.setLastName("Small");
//        user4.setPassword("password1");
//        user4.setUsername("Small_Wrld");
//        user4.setEmail("rsmall@gmail.com");
//        this.userService.save(user4);
//
//        User user5 = new User();
//        user5.setFirstName("Lewis");
//        user5.setLastName("Dominguez");
//        user5.setPassword("coronapapi");
//        user5.setUsername("SensualLewis");
//        user5.setEmail("ldominguez@gmail.com");
//        this.userService.save(user5);
//
//        User user6 = new User();
//        user6.setFirstName("Kris");
//        user6.setLastName("Blassingame");
//        user6.setPassword("password1");
//        user6.setUsername("The Notorious G.I.T");
//        user6.setEmail("krisblassingame@gmail.com");
//        this.userService.save(user6);



    }
}
