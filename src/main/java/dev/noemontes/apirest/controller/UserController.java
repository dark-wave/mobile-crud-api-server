package dev.noemontes.apirest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.noemontes.apirest.dto.UserDto;
import dev.noemontes.apirest.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/usuarios")
	public ResponseEntity<?> listUsers() {
		List<UserDto> userDtoList = userService.getAllUsers();
		
		if(userDtoList.size()>0) {
			return ResponseEntity.ok().body(userDtoList);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/usuarios/{id}")
	public ResponseEntity<?> userDetail(@PathVariable Long id){
		UserDto userDto = userService.getUserById(id);
		
		if(userDto!=null) {
			return ResponseEntity.ok().body(userDto);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("/usuarios")
	public ResponseEntity<?> saveUser(@RequestBody UserDto userDto){
		UserDto userDtoSaved = userService.saveUser(userDto);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(userDtoSaved);
	}
}
