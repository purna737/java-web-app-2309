package com.codegnan.app.javawebapp2309.service;

import com.codegnan.app.javawebapp2309.dao.UserDao;
import com.codegnan.app.javawebapp2309.dao.UserDaoImpl;
import com.codegnan.app.javawebapp2309.dto.CredentialDto;
import com.codegnan.app.javawebapp2309.dto.UserDto;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class UserServiceImpl implements UserService {
	private static final UserDao USER_DAO = new UserDaoImpl();
	
	@Override
	public boolean register(UserDto userDto, CredentialDto credentialDto) {
		return USER_DAO.save(userDto, credentialDto);
	}
	
	@Override
	public CredentialDto login(String username, String loginPassword) {
		CredentialDto credentialDto = null;
		
		credentialDto = USER_DAO.findByUsername(username);
		if (credentialDto != null) {
			char[] inputPassword = loginPassword.toCharArray();
			char[] hashedPassword = credentialDto.loginPassword().toCharArray();
			
			boolean isMatching = BCrypt.verifyer().verify(inputPassword, hashedPassword).verified;
			if (!(isMatching)) {
				return null;
			}
		}
		
		return credentialDto;
	}
	
	@Override
	public String hashPassword(String password) {
		String hashedPassword = BCrypt.withDefaults().hashToString(10, password.toCharArray());
		
		return hashedPassword;
	}
}