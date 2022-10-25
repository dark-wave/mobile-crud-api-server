package dev.noemontes.apirest.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.noemontes.apirest.converter.UserConverter;
import dev.noemontes.apirest.dto.UserDto;
import dev.noemontes.apirest.entity.UserEntity;
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
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}
}
