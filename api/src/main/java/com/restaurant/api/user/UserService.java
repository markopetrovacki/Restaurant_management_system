package com.restaurant.api.user;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Mono;

public interface UserService {

	@GetMapping(value = "/{id}", produces = "application/json")
	User getUser(@PathVariable("id") Integer id);
    
    @GetMapping(value = "/email/{email}", produces = "application/json")
    User getUserByEmail(@PathVariable("email") String email);

    @PostMapping(value = "", consumes = "application/json", produces = "application/json")
    User createUser(@RequestBody User body);

    @DeleteMapping(value = "/{id}")
    Void deleteUser(@PathVariable("id") Integer id);

    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    User updateUser(@PathVariable("id") Integer id, @RequestBody User body);
	
}
