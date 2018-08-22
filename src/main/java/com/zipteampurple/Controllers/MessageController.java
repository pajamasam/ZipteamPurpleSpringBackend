package com.zipteampurple.Controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zipteampurple.Entity.Message;
import com.zipteampurple.Entity.User;
import com.zipteampurple.Service.ChannelService;
import com.zipteampurple.Service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@CrossOrigin
@RestController
public class MessageController {

    @Autowired
    MessageService messageService;

    @Autowired
    ChannelService channelService;

    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public ResponseEntity<Message> postMessage(@RequestBody Message newMessage) {
        HttpStatus httpStatus = HttpStatus.CREATED;
        newMessage = this.messageService.save(newMessage);

        return new ResponseEntity<>(newMessage, new HttpHeaders(), httpStatus);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity<Message> updateMessage(@RequestBody Message message) {
        HttpStatus httpStatus;

        message = this.messageService.update(message);

        httpStatus = (message == null ? HttpStatus.NOT_ACCEPTABLE : HttpStatus.ACCEPTED);

        return new ResponseEntity<>(message, new HttpHeaders(), httpStatus);
    }

    @RequestMapping(value = "/get_messages", method = RequestMethod.GET)
    public ResponseEntity<?> getAllMessages() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        HttpStatus httpStatus = HttpStatus.CREATED;
        HttpHeaders header = new HttpHeaders();

        String messageJSON = objectMapper.writeValueAsString(this.messageService.getAllMessages());
        header.setContentType(MediaType.APPLICATION_JSON);


        return new ResponseEntity<String>(messageJSON, header, httpStatus);
    }

    @RequestMapping(value = "/messages", method = RequestMethod.GET)
    public ResponseEntity<?> getAllMessages(@RequestParam(value="channelId", required = true) Long channelId) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        HttpStatus httpStatus = HttpStatus.OK;
        HttpHeaders header = new HttpHeaders();

        List<Message> messagesList = this.channelService.getMessages(channelId);

        Message[] messages = messagesList.toArray(new Message[messagesList.size()]);
        Arrays.sort(messages);

        String messagesJSON = objectMapper.writeValueAsString(messages);
        header.setContentType(MediaType.APPLICATION_JSON);


        return new ResponseEntity<String>(messagesJSON, header, httpStatus);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteMessage(@RequestParam(value="messageId", required = true)Long messageId) {
        Message message = messageService.findById(messageId);

        boolean wasDeleted = messageService.delete(message);

        HttpStatus httpStatus = wasDeleted == true ? HttpStatus.OK : HttpStatus.CONFLICT;

        return new ResponseEntity<>(httpStatus);
    }

//    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
//    public ResponseEntity<User> deleteMessage(@RequestBody Message message) {
//        messageService.delete(message);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }


}
