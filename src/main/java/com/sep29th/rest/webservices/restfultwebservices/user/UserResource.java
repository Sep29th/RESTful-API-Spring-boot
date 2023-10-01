package com.sep29th.rest.webservices.restfultwebservices.user;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;

@RestController
public class UserResource {

	private UserDaoService userDaoService;

	public UserResource(UserDaoService userDaoService) {
		this.userDaoService = userDaoService;
	}

	@GetMapping(path = "/users")
	public List<User> filterWithParams(@RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "birthDate", required = false) LocalDate birthDate) {
		return this.userDaoService.getWithCondition(new User(name, birthDate));
	}

	@GetMapping(path = "/users/{id}")
	public User retrieveUser(@PathVariable int id) {
		User user = this.userDaoService.findOne(id);
		if (user == null)
			throw new UserNotFoundException("Dont exist the user with id = " + id);
		return user;
	}

	@PostMapping(path = "/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User savedUser = this.userDaoService.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(location).body(savedUser);
	}

	@PutMapping(path = "/users/{id}")
	public ResponseEntity<User> updatePutUser(@PathVariable int id, @Valid @RequestBody User user) {
		User updatePut = this.userDaoService.updatePut(id, user);
		if (updatePut == null)
			throw new UserNotFoundException("Dont exist the user with id = " + id);
		return ResponseEntity.ok().body(updatePut);
	}

//	@PatchMapping(path = "/user/{id}")
//	public ResponseEntity<User> updatePatchUser(@PathVariable int id, @Valid @RequestBody User user) {
//		User updatePut = this.userDaoService.updatePut(id, user);
//		if (updatePut == null)
//			throw new UserNotFoundException("Dont exist the user with id = " + id);
//		return ResponseEntity.ok().body(updatePut);
//	}
	@DeleteMapping(path = "/users/{id}")
	public void deleteUser(@PathVariable int id) {
		this.userDaoService.deleteUser(id);
	}

}
