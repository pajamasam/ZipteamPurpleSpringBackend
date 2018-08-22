package com.zipteampurple.Service;

import com.zipteampurple.Entity.Channel;
import com.zipteampurple.Entity.Message;
import com.zipteampurple.Entity.User;
import com.zipteampurple.Repository.ChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ChannelService {

    @Autowired
    ChannelRepository channelRepository;

    @Autowired
    UserService userService;

    public Channel create (Channel channel, Boolean isDirect){

        channel.setCreated(new Date());
        channel.setDirect(isDirect);

        User loggedInUser = this.userService.getLoggedInUser();
        if(loggedInUser != null){
            List<User> users = new ArrayList<>();
            for(User user :channel.getUsers()){
                users.add(this.userService.find(user.getId()));
            }
            channel.setUsers(users);
            channel.getUsers().add(this.userService.getLoggedInUser());
            channel.setUser(this.userService.getLoggedInUser());
        }

        return channelRepository.save(channel);
    }

    public void addUserToDefault(User user){
        Channel channel = this.channelRepository.findById(1L).get();
        channel.addUser(user);
        this.channelRepository.save(channel);
    }

    public Channel update(Channel channel) {
        Channel channelToUpdate = channelRepository.findById(channel.getId()).get();
        Channel updatedChannel = null;
        if(channelToUpdate != null){
            channelToUpdate.setPurpose(channel.getPurpose());
            channelToUpdate.setName(channel.getName());
            channelToUpdate.setPrivate(channel.getPrivate());
            updatedChannel = this.channelRepository.save(channelToUpdate);
        }
        return updatedChannel;
    }

    public List<Message> getMessages(Long channelId){
        return this.channelRepository.findById(channelId).get().getMessages();
    }

    public Iterable<Channel> getChannels() {
        return channelRepository.findAll();
    }

    public boolean ifExists(Long channelId){
        return this.channelRepository.findById(channelId).get() != null ? true : false;
    }

    public void delete(Channel channel) {
        channelRepository.delete(channel);
    }

    public void addUser(Channel channel) {
        Channel foundChannel = this.channelRepository.findById(channel.getId()).get();
        if(!foundChannel.getUsers().contains(this.userService.getLoggedInUser())){
            foundChannel.getUsers().add(this.userService.getLoggedInUser());
        }
        this.channelRepository.save(foundChannel);
    }

    public Channel findById(Long channelId){
        return this.channelRepository.findById(channelId).get();
    }


    public void removeUser(Channel channel) {
        Channel foundChannel = this.channelRepository.findById(channel.getId()).get();
        foundChannel.getUsers().remove(this.userService.getLoggedInUser());
        this.channelRepository.save(foundChannel);
    }

    public List<User> channelUsers(Long channelId) {
        Channel foundChannel = this.channelRepository.findById(channelId).get();
        return foundChannel.getUsers();
    }

    public List<Channel> getUserChannels(User loggedInUser) {
        List<Channel> channels = this.userService.find(loggedInUser.getUsername()).getChannels();

        List<Channel> newChannels = new ArrayList<>();

        return newChannels;
        //return channels.stream().filter(channel -> channel.getDirect().equals(true)).collect(Collectors.toList());
    }


}




