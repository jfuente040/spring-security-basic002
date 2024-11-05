package com.jfuente040.spring_security_basic002.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class DemoController {
    private final static Logger logger = LoggerFactory.getLogger(DemoController.class);

    @GetMapping("/hello")
    public String hello() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        logger.info("Datos del usuario autenticado: {}", auth.getPrincipal());
        logger.info("Datos de los Persmisos del usuario autenticado: {}", auth.getAuthorities());
        logger.info("Is authenticated: " + auth.isAuthenticated());
        return "Hello from Hello";
    }

    @GetMapping("/demoget")
    public String demo() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        logger.info("Datos del usuario autenticado: {}", auth.getPrincipal());
        logger.info("Datos de los Persmisos del usuario autenticado: {}", auth.getAuthorities());
        logger.info("Is authenticated: " + auth.isAuthenticated());
        return "Hello from Demo Get";
    }

    @GetMapping("/adminget")
    public String demoPost() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        logger.info("Datos del usuario autenticado: {}", auth.getPrincipal());
        logger.info("Datos de los Persmisos del usuario autenticado: {}", auth.getAuthorities());
        logger.info("Is authenticated: " + auth.isAuthenticated());
        return "Hello from Admin Get";
    }

    @GetMapping("/add")
    public String addGet() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        logger.info("Datos del usuario autenticado: {}", auth.getPrincipal());
        logger.info("Datos de los Persmisos del usuario autenticado: {}", auth.getAuthorities());
        logger.info("Is authenticated: " + auth.isAuthenticated());
        return "Hello from Add (GET)";
    }

    @PostMapping("/add")
    public String addPost() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        logger.info("Datos del usuario autenticado: {}", auth.getPrincipal());
        logger.info("Datos de los Persmisos del usuario autenticado: {}", auth.getAuthorities());
        logger.info("Is authenticated: " + auth.isAuthenticated());
        return "Hello from Add (POST)";
    }
}
