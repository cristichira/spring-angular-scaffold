package com.servix.springangularscaffold.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface BaseUrlNameRepository<T> extends JpaRepository<T, Long>, JpaSpecificationExecutor<T> {
    Optional<T> findByUrlName(String urlName);
}
