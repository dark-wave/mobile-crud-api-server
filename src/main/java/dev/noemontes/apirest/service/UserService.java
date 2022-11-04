package dev.noemontes.apirest.service;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import dev.noemontes.apirest.dto.UserDto;
import dev.noemontes.apirest.exceptions.UserNotFoundException;

public interface UserService {
	public UserDto saveUser(UserDto userDto);
	public UserDto updateUser(Long id, UserDto userDto) throws UserNotFoundException;
	public UserDto getUserById(Long id);
	public List<UserDto> getAllUsers();
	public void deleteUser(Long id) throws NotFoundException;
}