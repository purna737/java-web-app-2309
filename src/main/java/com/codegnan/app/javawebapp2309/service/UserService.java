package com.codegnan.app.javawebapp2309.service;

import com.codegnan.app.javawebapp2309.dto.CredentialDto;
import com.codegnan.app.javawebapp2309.dto.UserDto;

public interface UserService {
	boolean register(UserDto userDto, CredentialDto credentialDto);
	
	CredentialDto login(String username, String loginPassword);
	
	String hashPassword(String password);
}