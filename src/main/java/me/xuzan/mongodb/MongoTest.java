package me.xuzan.mongodb;

import me.xuzan.mongodb.conf.MongoDataSourceConfig;
import me.xuzan.mongodb.domain.po.User;
import me.xuzan.mongodb.repository.UserRepository;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MongoDataSourceConfig.class})
@FixMethodOrder
public class MongoTest {
    private static Logger logger = LoggerFactory.getLogger(MongoTest.class);

    @Autowired
    private UserRepository userRepository;

    @Before
    public void setup() {
        Set<String> roles = new HashSet<>();
        roles.add("manage");
        User user = new User("1", "user", "123456",
                "name", "email@com.cn", new Date(), roles);

        userRepository.save(user);
    }

    @Test
    public void findAll() {
        List<User> users = userRepository.findAll();
        Assert.notNull(users, "查询无用户信息！");
        for (User user : users) {
            logger.info("===user=== userId:{}, userName:{}, password:{}, registrationDate:{}",
                    user.getUserId(), user.getName(), user.getPassword(), user.getRegistrationDate());
        }
    }
}
