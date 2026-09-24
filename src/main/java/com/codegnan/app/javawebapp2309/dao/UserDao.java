package com.codegnan.app.javawebapp2309.dao;

import com.codegnan.app.javawebapp2309.dto.CredentialDto;
import com.codegnan.app.javawebapp2309.dto.UserDto;

public interface UserDao {
	boolean save(UserDto userDto, CredentialDto credentialDto);
	
	CredentialDto findByUsername(String username);
}