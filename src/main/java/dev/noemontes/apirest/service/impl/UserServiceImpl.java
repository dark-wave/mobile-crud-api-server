package dev.noemontes.apirest.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.noemontes.apirest.converter.UserConverter;
import dev.noemontes.apirest.dto.UserDto;
import dev.noemontes.apirest.entity.UserEntity;
import dev.noemontes.apirest.exceptions.EmailDuplicatedException;
import dev.noemontes.apirest.exceptions.UserNotFoundException;
import dev.noemontes.apirest.repository.UserRepository;
import dev.noemontes.apirest.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserConverter userConverter;

	@Override
	@Transactional
	public UserDto saveUser(UserDto userDto){
		UserEntity userEntity = userRepository.save(userConverter.convertDtoToEntity(userDto));
		return userConverter.convertEntityToDto(userEntity);
	}
	
	@Override
	@Transactional
	public UserDto updateUser(Long id, UserDto userDto) throws UserNotFoundException, EmailDuplicatedException, DataIntegrityViolationException {
		Optional<UserEntity> opUser = userRepository.findById(id);
		Optional<UserEntity> opUserByEmail = userRepository.findByEmail(userDto.getEmail());
		
		if(opUser.isPresent()) {
			if(opUserByEmail.isPresent() && opUserByEmail.get().getEmail().equalsIgnoreCase(userDto.getEmail())) {
				throw new EmailDuplicatedException("El email: " + opUserByEmail.get().getEmail() + " se encuentra registrado en base de datos");
			}
			
			UserEntity dbUser = opUser.get();
			dbUser.setName(userDto.getName());
			dbUser.setLastName(userDto.getLastName());
			dbUser.setEmail(userDto.getEmail());
			
			UserEntity dbUserResponse = userRepository.save(dbUser);
			return userConverter.convertEntityToDto(dbUserResponse);
		}else {
			throw new UserNotFoundException("El usuario con id: " + userDto.getId() + " no existe en base de datos");
		} 
	}

	@Override
	@Transactional(readOnly = true)
	public UserDto getUserById(Long id) {
		Optional<UserEntity> userOptional = userRepository.findById(id);
		
		if(userOptional.isPresent()) {
			return userConverter.convertEntityToDto(userOptional.get());
		}else {
			return null;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<UserDto> getAllUsers() {
		List<UserEntity> userEntiryList = (List<UserEntity>) userRepository.findAll();
		
		if(userEntiryList.size()>0) {
			return userConverter.convertListEntityToListDto(userEntiryList);
		}
		
		return new ArrayList<UserDto>();
	}

	@Override
	@Transactional
	public void deleteUser(Long id) throws UserNotFoundException{
		Optional<UserEntity> opUser = userRepository.findById(id);
		
		if(opUser.isPresent()) {
			userRepository.deleteById(id);
		}else {
			throw new UserNotFoundException("El usuario con id: " + id + " no existe en base de datos");
		}
	}
}
