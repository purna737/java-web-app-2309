package com.codegnan.app.javawebapp2309.dao;

import java.util.ArrayList;

import java.util.List;

import com.codegnan.app.javawebapp2309.dto.AddressDto;
import com.codegnan.app.javawebapp2309.dto.UserDto;
import com.codegnan.app.javawebapp2309.entity.Address;
import com.codegnan.app.javawebapp2309.entity.Product;
import com.codegnan.app.javawebapp2309.entity.User;
import com.codegnan.app.javawebapp2309.utils.JpaUtil;

import jakarta.persistence.EntityManagerFactory;

public class AddressDaoImpl implements AddressDao {
	private EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();

	@Override
	public boolean save(AddressDto addressDto) {
		boolean isSaved = false;

		var user = new User();
		user.setUserId(addressDto.userDto().userId());

		var address = new Address();
		address.setLabel(addressDto.label());
		address.setLine1(addressDto.line1());
		address.setLine2(addressDto.line2());
		address.setLine3(addressDto.line3());
		address.setCity(addressDto.city());
		address.setState(addressDto.state());
		address.setPincode(addressDto.pincode());
		address.setUser(user);

		try (var entityManager = entityManagerFactory.createEntityManager()) {
			entityManager.getTransaction().begin();

			entityManager.persist(address);

			entityManager.getTransaction().commit();
			isSaved = true;
		} catch (RuntimeException runtimeException) {
			runtimeException.printStackTrace();
		}

		return isSaved;
	}

	@Override
	public List<AddressDto> findByUserId(int userId) {
		List<AddressDto> addressDtosList = new ArrayList<>();

		var jpql = "SELECT a FROM Address a WHERE a.user.userId=:userid";

		try (var entityManager = entityManagerFactory.createEntityManager()) {
			var query = entityManager.createQuery(jpql).setParameter("userid", userId);

			List<Address> addressesList = (List) query.getResultList();

			for (var address : addressesList) {
				var user = address.getUser();

				var userDto = new UserDto(user.getUserId(), user.getFirstName(), user.getLastName());

				var addressDto = new AddressDto(address.getAddressId(), address.getLabel(), address.getLine1(),
						address.getLine2(), address.getLine3(), address.getCity(), address.getState(),
						address.getPincode(), userDto);

				addressDtosList.add(addressDto);
			}
		} catch (RuntimeException runtimeException) {
			runtimeException.printStackTrace();
		}

		return addressDtosList;
	}

	
	@Override
	public boolean updateAddress(AddressDto addressDto) {
		boolean isUpdated = false;

	
		try (var entityManager = entityManagerFactory.createEntityManager()) {

			var address = entityManager.find(Address.class, addressDto.addressId());

			if (address != null) {

				address.setLabel(addressDto.label());
				address.setLine1(addressDto.line1());
				address.setLine2(addressDto.line2());
				address.setLine3(addressDto.line3());
				address.setCity(addressDto.city());
				address.setState(addressDto.state());
				address.setPincode(addressDto.pincode());

				entityManager.getTransaction().begin();

				entityManager.merge(address);

				entityManager.getTransaction().commit();

				isUpdated = true;
			}

		} catch (RuntimeException runtimeException) {
			runtimeException.printStackTrace();
		}

		return isUpdated;
	}

	
}
