package com.servix.springangularscaffold.initializer;

import com.servix.springangularscaffold.entity.User;
import com.servix.springangularscaffold.enumeration.Profiles;
import com.servix.springangularscaffold.enumeration.UserRole;
import com.servix.springangularscaffold.repository.UserRepository;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Set;

@Component
public class UserInitializer implements InitializingBean {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserInitializer.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final Environment environment;

    @Autowired
    public UserInitializer(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           Environment environment) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.environment = environment;
    }

    @Override
    @Transactional
    public void afterPropertiesSet() {
        long userCount = userRepository.count();
        if (userCount != 0 || ArrayUtils.contains(environment.getActiveProfiles(), Profiles.TEST)) {
            return;
        }
        LOGGER.info("No users present in the database, performing admin initialization");

        User user = new User();
        user.setCompanyName("Admin");
        user.setFirstName("Admin");
        user.setLastName("Admin");
        user.setFullName("Admin Admin");
        user.setPhoneNumber("0740123456");
        user.setPhoneNumberAlternate("0776543210");
        user.setEmail("admin@admin.com");
        user.setUrlName("admin");
        user.setRoles(Set.of(UserRole.ROLE_ADMIN, UserRole.ROLE_USER));
        user.setPassword(passwordEncoder.encode("password"));

        userRepository.save(user);
    }
}
