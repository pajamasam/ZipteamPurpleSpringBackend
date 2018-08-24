package com.zipteampurple.repository;

import com.zipteampurple.Entity.User;
import com.zipteampurple.Repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindByEmail() {

        //Setup Data scenario
        //Creating a User
        User user = new User();
        user.setFirstName("Madonna");
        user.setLastName("Dons");
        user.setPassword("password");
        user.setUsername("mdons");
        user.setEmail("mdons@gmail.com");

        entityManager.persist(user);

        //Find an inserted record using repository class
        User foundUser = userRepository.findByEmail("mdons@gmail.com");

        // Assertion
        assertThat(foundUser.getEmail(), is(equalTo("mdons@gmail.com")));

    }

}
