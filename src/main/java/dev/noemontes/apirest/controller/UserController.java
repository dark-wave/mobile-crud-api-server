package dev.noemontes.apirest.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.noemontes.apirest.dto.UserDto;
import dev.noemontes.apirest.exceptions.EmailDuplicatedException;
import dev.noemontes.apirest.exceptions.UserNotFoundException;
import dev.noemontes.apirest.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	private final Logger LOG = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<?> saveUser(@RequestBody UserDto userDto){
		UserDto userDtoSaved = userService.saveUser(userDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(userDtoSaved);
	}
	
	@GetMapping
	public ResponseEntity<?> listUsers() {
		List<UserDto> userDtoList = userService.getAllUsers();
		
		LOG.debug("Vamos a listar todos los usuarios");
		
		if(userDtoList.size()>0) {
			return ResponseEntity.ok().body(userDtoList);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> userDetail(@PathVariable Long id){
		UserDto userDto = userService.getUserById(id);
		
		if(userDto!=null) {
			return ResponseEntity.ok().body(userDto);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<?> editUser(@PathVariable Long id, @RequestBody UserDto userDto){
		try {
			UserDto userUpdated = userService.updateUser(id, userDto);
			
			return ResponseEntity.ok(userUpdated);
		}catch(UserNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}catch(EmailDuplicatedException de) {
			return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(de.getMessage());
		}catch(DataIntegrityViolationException ve) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ve.getMessage());
		}
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> removeUser(@PathVariable Long id){
		try {
			userService.deleteUser(id);
			
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (NotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
}
