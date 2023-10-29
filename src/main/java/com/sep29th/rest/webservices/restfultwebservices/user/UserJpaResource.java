package com.sep29th.rest.webservices.restfultwebservices.user;

import com.sep29th.rest.webservices.restfultwebservices.jpa.PostRepository;
import com.sep29th.rest.webservices.restfultwebservices.jpa.UserRepository;
import com.sep29th.rest.webservices.restfultwebservices.user.Post;
import com.sep29th.rest.webservices.restfultwebservices.user.User;
import com.sep29th.rest.webservices.restfultwebservices.user.UserDaoService;
import com.sep29th.rest.webservices.restfultwebservices.user.UserNotFoundException;
import jakarta.validation.Valid;
import org.springframework.data.domain.Example;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
public class UserJpaResource {

    private final UserDaoService userDaoService;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public UserJpaResource(UserDaoService userDaoService, UserRepository userRepository, PostRepository postRepository) {
        this.userDaoService = userDaoService;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @GetMapping(path = "/jpa/users")
    public List<User> filterWithParams(@RequestParam(name = "name", required = false) String name,
                                       @RequestParam(name = "birthDate", required = false) LocalDate birthDate) {
        User user = new User(name, birthDate);
        user.setId(null);
        Example<User> exampleUser = Example.of(user);
        return this.userRepository.findAll(exampleUser);
    }

    @GetMapping(path = "/jpa/users/{id}")
    public Optional<User> retrieveUser(@PathVariable int id) {
        Optional<User> user = this.userRepository.findById(id);
        if (user.isEmpty())
            throw new UserNotFoundException("Don't exist the user with id = " + id);
        return user;
    }

    @GetMapping(path = "/jpa/users/{id}/posts")
    public List<Post> retrievePostsForUser(@PathVariable Integer id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) throw new UserNotFoundException("Don't exist the user with id = " + id);
        return user.get().getPosts();
    }

    @PostMapping(path = "/jpa/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User savedUser = this.userRepository.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).body(savedUser);
    }

    @PostMapping(path = "/jpa/users/{id}/posts")
    public ResponseEntity<Post> createPostForUser(@PathVariable Integer id, @Valid @RequestBody Post post) {
        Optional<User> user = this.userRepository.findById(id);
        if (user.isEmpty()) throw new UserNotFoundException("Don't exist the user with id = " + id);
        post.setUser(user.get());
        Post savedPost = this.postRepository.save(post);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedPost.getId())
                .toUri();
        return ResponseEntity.created(location).body(savedPost);
    }

    @PutMapping(path = "/jpa/users/{id}")
    public ResponseEntity<User> updatePutUser(@PathVariable int id, @Valid @RequestBody User user) {
        User updatePut = this.userDaoService.updatePut(id, user);
        if (updatePut == null)
            throw new UserNotFoundException("Don't exist the user with id = " + id);
        return ResponseEntity.ok().body(updatePut);
    }

    //	@PatchMapping(path = "/jpa/user/{id}")
//	public ResponseEntity<User> updatePatchUser(@PathVariable int id, @Valid @RequestBody User user) {
//		User updatePut = this.userDaoService.updatePut(id, user);
//		if (updatePut == null)
//			throw new UserNotFoundException("Dont exist the user with id = " + id);
//		return ResponseEntity.ok().body(updatePut);
//	}
    @DeleteMapping(path = "/jpa/users/{id}")
    public void deleteUser(@PathVariable int id) {
        this.userRepository.deleteById(id);
    }

}
