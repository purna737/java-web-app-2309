package com.codegnan.app.javawebapp2309.dao;

import com.codegnan.app.javawebapp2309.dto.CredentialDto;
import com.codegnan.app.javawebapp2309.dto.UserDto;
import com.codegnan.app.javawebapp2309.entity.Credential;
import com.codegnan.app.javawebapp2309.entity.User;
import com.codegnan.app.javawebapp2309.utils.JpaUtil;

import jakarta.persistence.EntityManagerFactory;

public class UserDaoImpl implements UserDao {
	private EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
	
	public boolean save(UserDto userDto, CredentialDto credentialDto) {
		boolean isSaved = false;

		try (var entityManager = entityManagerFactory.createEntityManager()) {
			entityManager.getTransaction().begin();

			var user = new User();
			user.setFirstName(userDto.firstName());
			user.setLastName(userDto.lastName());
			entityManager.persist(user);
			
			var credential = new Credential();
			credential.setUsername(credentialDto.username());
			credential.setLoginPassword(credentialDto.loginPassword());
			credential.setUser(user);
			entityManager.persist(credential);

			entityManager.getTransaction().commit();
			isSaved = true;
		} catch (RuntimeException runtimeException) {
			runtimeException.printStackTrace();
		}

		return isSaved;
	}
	
	@Override
	public CredentialDto findByUsername(String username) {
		CredentialDto credentialDto = null;
		
		var jpql = "SELECT c FROM Credential c JOIN FETCH c.user WHERE c.username=:usr";

		try (var entityManager = entityManagerFactory.createEntityManager()) {
			var query = entityManager.createQuery(jpql).setParameter("usr", username);

			var credential = (Credential) query.getSingleResult();
			var user = credential.getUser();
	
			var userDto = new UserDto(
					user.getUserId(),
					user.getFirstName(),
					user.getLastName());
			credentialDto = new CredentialDto(
					credential.getCredentialId(),
					credential.getUsername(),
					credential.getLoginPassword(),
					userDto);
		} catch (RuntimeException runtimeException) {
			runtimeException.printStackTrace();
		}
		
		return credentialDto;
	}
}