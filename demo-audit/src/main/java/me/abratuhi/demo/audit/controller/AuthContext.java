package me.abratuhi.demo.audit.controller;

/**
 * See https://stackoverflow.com/questions/66695265/how-to-retrieve-securitycontext-in-a-quarkus-application
 */
public interface AuthContext {
    String getPrincipal();
}
