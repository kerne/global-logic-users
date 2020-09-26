/**
 * 
 */
package com.globallogic.users.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.globallogic.users.entity.UserEntity;
import com.globallogic.users.model.Response;
import com.globallogic.users.model.User;
import com.globallogic.users.service.UserService;

/**
 * @author kerne
 *
 */
@RestController
@RequestMapping("/users")
public class UsersController {

	@Autowired
	UserService service;

	@PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	
	public ResponseEntity<Object> add(@Validated @RequestBody User user) {
		return ResponseEntity.ok(service.add(user));

	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Optional<UserEntity> get(@PathVariable Integer id) {
		return service.get(id);

	}

	@GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<UserEntity> getAll() {
		return service.getAll();

	}

	@PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> update(@RequestBody User user, @PathVariable Integer id) {
		Optional<UserEntity> opUser = service.update(user, id);
		if (!opUser.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.noContent().build();
	}

	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Response> delete(@PathVariable Integer id) {

		if (service.delete(id)) {
			return ResponseEntity.ok().body(Response.builder().message("User was deleted").build());
		}
		return ResponseEntity.ok().body(Response.builder().message("User wasn't deleted").build());

	}
}
