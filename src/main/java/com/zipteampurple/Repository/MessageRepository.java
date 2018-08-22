package com.zipteampurple.Repository;

import com.zipteampurple.Entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
//    User findOneByUsername(String userName);
}
