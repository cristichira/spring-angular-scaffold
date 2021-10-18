package com.servix.springangularscaffold.controller;

import com.servix.springangularscaffold.dto.user.UserDto;
import com.servix.springangularscaffold.dto.user.UserUpdateDto;
import com.servix.springangularscaffold.enumeration.UserRole;
import com.servix.springangularscaffold.facade.UserFacade;
import com.servix.springangularscaffold.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final SecurityService securityService;
    private final UserFacade userFacade;

    @Autowired
    public UserController(SecurityService securityService,
                          UserFacade userFacade) {
        this.securityService = securityService;
        this.userFacade = userFacade;
    }

    @GetMapping("/{id}")
    @Secured(UserRole.ROLE_ADMIN)
    public ResponseEntity<UserDto> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userFacade.findById(id));
    }

    @GetMapping("/me")
    @Secured(UserRole.ROLE_USER)
    public ResponseEntity<UserDto> getMe() {
        return ResponseEntity.ok(userFacade.findById(securityService.getCurrentUser().getId()));
    }

    @GetMapping("/list")
    @Secured(UserRole.ROLE_ADMIN)
    public ResponseEntity<Page<UserDto>> list(Pageable pageable) {
        return ResponseEntity.ok(userFacade.findAll(pageable));
    }

    @PutMapping("/")
    @PreAuthorize("hasPermission(#userUpdateDto.id, " +
            "T(com.servix.springangularscaffold.permission.UserPermissionChecker).USER_PERMISSION_CHECKER, " +
            "T(com.servix.springangularscaffold.security.permission.PerformedOperation).SINGLE_ENTITY)")
    public ResponseEntity<UserDto> update(@RequestBody UserUpdateDto userUpdateDto) {
        return ResponseEntity.ok(userFacade.update(userUpdateDto));
    }
}
