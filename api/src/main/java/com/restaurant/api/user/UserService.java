package com.restaurant.api.user;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Mono;

public interface UserService {

	@GetMapping(value = "/user/{id}", produces = "application/json")
	User getUser(@PathVariable Integer id);
    
    @GetMapping(value = "/user/email/{email}", produces = "application/json")
    User getUserByEmail(@PathVariable String email);

    @PostMapping(value = "/user", consumes = "application/json", produces = "application/json")
    User createUser(@RequestBody User body);

    @DeleteMapping(value = "/user/{id}")
    Void deleteUser(@PathVariable Integer id);

    @PutMapping(value = "/user/{id}", consumes = "application/json", produces = "application/json")
    User updateUser(@PathVariable Integer id, @RequestBody User body);
	
}
