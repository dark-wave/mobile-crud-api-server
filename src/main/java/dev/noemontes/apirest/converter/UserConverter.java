package dev.noemontes.apirest.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import dev.noemontes.apirest.dto.UserDto;
import dev.noemontes.apirest.entity.UserEntity;

/**
 * Clase que contiene los conversores de DTO a Entity para transformar objetos
 * de transferencia de datos en objetos de persistencia y viceversa
 * 
 * @author noemontes
 * @version 1.0.0
 */
@Component
public class UserConverter {
	
	/**
	 * @author noemontes
	 * @since 1.0.0
	 * @param userEntity
	 * @return UserDto
	 */
	public UserDto convertEntityToDto(UserEntity userEntity) {
		UserDto userDto = new UserDto();
		
		userDto.setId(userEntity.getId());
		userDto.setName(userEntity.getName());
		userDto.setLastName(userEntity.getLastName());
		userDto.setEmail(userEntity.getEmail());
		
		return userDto;
	}
	
	/**
	 * @author noemontes
	 * @since 1.0.0
	 * @param userDto
	 * @return UserEntity
	 */
	public UserEntity convertDtoToEntity(UserDto userDto) {
		UserEntity userEntity = new UserEntity();
		
		userEntity.setId(userDto.getId());
		userEntity.setName(userDto.getName());
		userEntity.setLastName(userDto.getLastName());
		userEntity.setEmail(userDto.getEmail());
		
		return userEntity;
	}
	
	/**
	 * @author noemontes
	 * @since 1.0.0
	 * @param userEntityList
	 * @return List<UserDto>
	 */
	public List<UserDto> convertListEntityToListDto(List<UserEntity> userEntityList){
		List<UserDto> userDtoList = new ArrayList<UserDto>();
		
		for(UserEntity userEntity : userEntityList) {
			UserDto userDto = new UserDto();
			userDto.setId(userEntity.getId());
			userDto.setName(userEntity.getName());
			userDto.setLastName(userEntity.getLastName());
			userDto.setEmail(userEntity.getEmail());
			
			userDtoList.add(userDto);
		}
		
		return userDtoList;
	}
}
