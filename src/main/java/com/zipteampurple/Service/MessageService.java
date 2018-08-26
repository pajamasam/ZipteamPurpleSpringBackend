package com.zipteampurple.Service;

import com.zipteampurple.Entity.Message;
import com.zipteampurple.Repository.ChannelRepository;
import com.zipteampurple.Repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    UserService userService;

    @Autowired
    ChannelService channelService;

    public Message save(Message message) {
        if(channelService.ifExists(message.getChannelId())){
            message.setUser(this.userService.getLoggedInUser());
            message.setCreated(new Date());
            return messageRepository.save(message);
        } else {
            return null;
        }
    }

    public Message update(Message message) {
        Message update = messageRepository.findById(message.getId()).get();
        if(update.getUser().getId() == this.userService.getLoggedInUser().getId()){
            update.setUpdated(new Date());
            update.setMessage(message.getMessage());
            return messageRepository.save(update);
        } else {
            return null;
        }
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public boolean delete(Message message) {
        if(message.getUser().getId().equals(this.userService.getLoggedInUser().getId())) {
            messageRepository.delete(message);
            return true;
        } else {
            return false;
        }
    }

    public Message findById(long messageId){
        return this.messageRepository.findById(messageId).get();
    }

}

