package com.servix.springangularscaffold.repository;

import com.servix.springangularscaffold.entity.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserRepository extends BaseUrlNameRepository<User>, JpaSpecificationExecutor<User> {

    User findByEmail(String email);
}
