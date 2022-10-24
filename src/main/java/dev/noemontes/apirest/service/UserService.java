package dev.noemontes.apirest.service;

import java.util.List;

import dev.noemontes.apirest.dto.UserDto;

public interface UserService {
	public UserDto saveUser(UserDto userDto);
	public UserDto getUserById(Long id);
	public List<UserDto> getAllUsers();
	public void deleteUser(Long id);
}