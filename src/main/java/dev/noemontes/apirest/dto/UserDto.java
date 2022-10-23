package dev.noemontes.apirest.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserDto implements Serializable{
	
	/** Default serial version uid*/
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private String lastName;
	private String email;
}
