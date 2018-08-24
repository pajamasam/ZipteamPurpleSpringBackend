package com.zipteampurple.suite;

import com.zipteampurple.Repository.UserRepository;
import com.zipteampurple.controller.UserControllerIntegrationTests;
import com.zipteampurple.repository.UserRepositoryDBUnitTest;
import com.zipteampurple.service.UserServiceIntegrationTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        UserServiceIntegrationTests.class,
        UserControllerIntegrationTests.class,
        UserRepositoryDBUnitTest.class
})
public class UserTestSuite {



}
