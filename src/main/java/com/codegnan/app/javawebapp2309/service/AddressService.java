package com.codegnan.app.javawebapp2309.service;

import java.util.List;

import com.codegnan.app.javawebapp2309.dto.AddressDto;

public interface AddressService {
	boolean addAddress(AddressDto addressDto);
	
	List<AddressDto> getAddresses(int userId);
	
	boolean UpdateAddress(AddressDto addressDto);
}