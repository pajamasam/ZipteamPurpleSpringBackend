package com.zipteampurple.User;

import com.zipteampurple.User.Controller.UserControllerIntegrationTests;
import com.zipteampurple.User.Repository.UserRepositoryDBUnitTest;
import com.zipteampurple.User.Service.UserServiceIntegrationTests;
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
