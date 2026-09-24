package com.codegnan.app.javawebapp2309.dao;

import java.util.List;

import com.codegnan.app.javawebapp2309.dto.AddressDto;

public interface AddressDao {
	boolean save(AddressDto addressDto);
	
	List<AddressDto> findByUserId(int userId);
	
	boolean updateAddress(AddressDto addressDto);
}