package com.proxyseller.Blog.exception;



public class UsernameAlreadyUsedException extends InvalidStateException {

    public static final String USERNAME_ALREADY_USED = "Username \"%s\" already registered,try different username";
    private static final long serialVersionUID = 1L;

    public UsernameAlreadyUsedException(String username) {
        super(String.format(USERNAME_ALREADY_USED, username));
    }
}
