package com.zipteampurple.Controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.zipteampurple.Entity.Channel;
import com.zipteampurple.Entity.User;
import com.zipteampurple.Service.ChannelService;
import com.zipteampurple.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ChannelController {

    @Autowired
    ChannelService channelService;
    @Autowired
    UserService userService;

    @RequestMapping(value = "/post_channel", method = RequestMethod.POST)
    public ResponseEntity<?> postChannel(@RequestBody Channel newChannel) {
        HttpStatus httpStatus;

        newChannel = this.channelService.create(newChannel, false);

        httpStatus = (newChannel != null ?  HttpStatus.CREATED : HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(newChannel, new HttpHeaders(), httpStatus);
    }

    @RequestMapping(value = "/user_channels", method = RequestMethod.GET)
    public ResponseEntity<?> getUserChannels() throws JsonProcessingException {
        HttpStatus httpStatus = HttpStatus.ACCEPTED;
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        List<Channel> userChannels = this.userService.getLoggedInUser().getChannels()
                .stream().filter(channel -> channel.getDirect() == false).collect(Collectors.toList());

        return new ResponseEntity<>(userChannels,httpHeaders, httpStatus);
    }

    @RequestMapping(value = "/user_direct_chats", method = RequestMethod.GET)
    public ResponseEntity<?> getDirectChats() throws JsonProcessingException {
        HttpStatus httpStatus = HttpStatus.ACCEPTED;
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        List<Channel> userChannels = this.userService.getLoggedInUser().getChannels()
                .stream().filter(channel -> channel.getDirect() == true).collect(Collectors.toList());

        return new ResponseEntity<>(userChannels,httpHeaders, httpStatus);
    }

    @RequestMapping(value = "/channel_users", method = RequestMethod.GET)
    public ResponseEntity<?> getChannel_user(@RequestParam(value="channelId", defaultValue="1", required = false) Long channelId) {
        HttpStatus httpStatus;
        httpStatus = HttpStatus.OK;

        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);

        List<User> users = this.channelService.findById(channelId).getUsers();

        return new ResponseEntity<>(users, new HttpHeaders(), httpStatus);
    }

    @RequestMapping(value = "/remove_user", method = RequestMethod.DELETE)
    public ResponseEntity<?> remove_channel_user(@RequestBody Channel channel) {
        HttpStatus httpStatus;

        this.channelService.removeUser(channel);

//        httpStatus = (newChannel != null ?  HttpStatus.CREATED : HttpStatus.NOT_FOUND);

        return new ResponseEntity<>("", new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/add_user", method = RequestMethod.POST)
    public ResponseEntity<?> add_channel_user(@RequestBody Channel channel) {
        HttpStatus httpStatus;

        this.channelService.addUser(channel);

//        httpStatus = (newChannel != null ?  HttpStatus.CREATED : HttpStatus.NOT_FOUND);

        return new ResponseEntity<>("", new HttpHeaders(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/post_directChannel", method = RequestMethod.POST)
    public ResponseEntity<?> postDirectChannel(@RequestBody Channel newChannel) {
        HttpStatus httpStatus = HttpStatus.CREATED;
        newChannel = this.channelService.create(newChannel, true);

        httpStatus = (newChannel != null ?  HttpStatus.CREATED : HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(newChannel, new HttpHeaders(), httpStatus);
    }

    @RequestMapping(value = "/update_channel", method = RequestMethod.PUT)
    public ResponseEntity<Channel> updateChannel(@RequestBody Channel channel) {
        HttpStatus httpStatus;
        Channel updateChannel = this.channelService.update(channel);

        httpStatus = (updateChannel != null ?  HttpStatus.CREATED : HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(channel, new HttpHeaders(), httpStatus);
    }

    @RequestMapping(value = "/get_channels", method = RequestMethod.GET)
    public ResponseEntity<?> getAllChannels() throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        HttpStatus httpStatus = HttpStatus.CREATED;
        HttpHeaders header = new HttpHeaders();

        String channelsJSON = objectMapper.writeValueAsString(this.channelService.getChannels());
        header.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<String>(channelsJSON, header, httpStatus);
    }

    @RequestMapping(value = "/delete_channel", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteChannel(@RequestBody Channel channel) {
        channelService.delete(channel);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
