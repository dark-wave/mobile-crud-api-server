package dev.noemontes.apirest.converter;

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
	 * @return
	 */
	public UserEntity convertDtoToEntity(UserDto userDto) {
		UserEntity userEntity = new UserEntity();
		
		return userEntity;
	}
}
