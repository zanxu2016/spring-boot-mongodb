package me.xuzan.mongodb.repository;

import me.xuzan.mongodb.domain.po.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,String> {

    User findByUserName(String name);
}
