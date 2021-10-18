package com.servix.springangularscaffold.controller;

import com.servix.springangularscaffold.dto.ObjectWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ExampleController {

    @GetMapping("/test")
    public ResponseEntity<ObjectWrapper> getTest() {
        return ResponseEntity.ok(new ObjectWrapper("Test works ok!"));
    }
}
