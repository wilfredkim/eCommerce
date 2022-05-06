package com.ecommerce.controllers;

import com.ecommerce.entities.User;
import com.ecommerce.entities.YesNo;
import com.ecommerce.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RequestMapping("/api/users")
public class UserController extends BaseController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(@RequestParam(required = false, name = "username") String username, @RequestParam(required = false, name = "password") String password) {
        try {
            List<User> userList = userService.getList();
            if (userList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(userList, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<User> findUserById(@PathVariable long id) {
        Optional<User> userOptional = userService.findById(id);
        return userOptional.map(user -> new ResponseEntity<>(user, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        try {
            User saveUser = userService.save(user);
            return new ResponseEntity<>(saveUser, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<User> updateOrder(@RequestBody User user) {
        if (user.getId() != null) {
            Optional<User> userOptional = userService.findById(user.getId());
            if (userOptional.isPresent()) {
                User saveUser = userOptional.get();
                saveUser = userService.save(saveUser);
                return new ResponseEntity<>(saveUser, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") long id) {
        try {
            Optional<User> userOptional = userService.findById(id);
            if (userOptional.isPresent()) {
                User saveUser = userOptional.get();
                saveUser.setDeleted(YesNo.YES);
                userService.save(saveUser);
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
