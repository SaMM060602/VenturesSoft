package com.sandy.oauth.services;

public interface AuthService {
	String authenticate(String username, String password) throws Exception;

}
