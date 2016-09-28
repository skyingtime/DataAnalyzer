package com.repository;

import com.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by yangliu on 28/09/2016.
 */
public interface UserRepository extends CrudRepository<User, Long> {

    User findByEmail(String email);
}
