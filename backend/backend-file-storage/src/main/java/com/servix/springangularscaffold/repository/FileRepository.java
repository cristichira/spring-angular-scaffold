package com.servix.springangularscaffold.repository;

import com.servix.springangularscaffold.domain.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {
}
