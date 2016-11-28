package com.controller;

import com.model.User;
import com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by yangliu on 28/09/2016.
 */
@Controller
public class UserController {

    // ------------------------
    // PRIVATE FIELDS
    // ------------------------

    @Autowired
    private UserRepository userRepository;

    // ------------------------
    // REST METHODS
    // ------------------------



    //-------------------Retrieve Single User--------------------------------------------------------
    //http://localhost:8090/api/user/1  return user with id 1
    @RequestMapping(value="/user/{id}", method= RequestMethod.GET)
    public ResponseEntity<User> apiServiceCallForGet(@PathVariable("id") String id) {
        if(id != null) {
            User user = userRepository.findOne(Long.valueOf(id));
            return new ResponseEntity(user, HttpStatus.OK);

        }
        return null;
    }

    //-------------------Retrieve All Users--------------------------------------------------------
    //http://localhost:8090/api/user    return all users
    //http://localhost:8090/api/user?id=1
    @RequestMapping(value="/user", method= RequestMethod.GET)
    public Iterable<User> apiServiceCallForGetAll() {           //@RequestParam
        return userRepository.findAll();
    }

    //-------------------Create a User--------------------------------------------------------
    @RequestMapping(value="/user", method= RequestMethod.POST)
    public ResponseEntity<User> apiServiceCallForCreate(@RequestBody User user) {
        userRepository.save(user);
        return new ResponseEntity(user, HttpStatus.OK);
    }


    // ------------------------
    // PUBLIC METHODS
    // ------------------------

    /**
     * /create  --> Create a new user and save it in the database.
     *
     * @param email User's email
     * @param name User's name
     * @return A string describing if the user is succesfully created or not.
     */
    @RequestMapping("/create")
    @ResponseBody
    public String create(String email, String name) {
        User user;
        try {
            user = new User(email, name);
            userRepository.save(user);
        }
        catch (Exception ex) {
            return "Error creating the user: " + ex.toString();
        }
        return "User succesfully created! (id = " + user.getId() + ")";
    }

    /**
     * /delete  --> Delete the user having the passed id.
     *
     * @param id The id of the user to delete
     * @return A string describing if the user is succesfully deleted or not.
     */
    @RequestMapping("/delete")
    @ResponseBody
    public String delete(long id) {
        try {
            User user = new User(id);
            userRepository.delete(user);
        }
        catch (Exception ex) {
            return "Error deleting the user: " + ex.toString();
        }
        return "User succesfully deleted!";
    }

    /**
     * /get-by-email  --> Return the id for the user having the passed email.
     *
     * @param email The email to search in the database.
     * @return The user id or a message error if the user is not found.
     */
    @RequestMapping("/get-by-email")
    @ResponseBody
    public String getByEmail(String email) {
        String userId;
        try {
            User user = userRepository.findByEmail(email);
            userId = String.valueOf(user.getId());
        }
        catch (Exception ex) {
            return "User not found";
        }
        return "The user id is: " + userId;
    }

    /**
     * /update  --> Update the email and the name for the user in the database
     * having the passed id.
     *
     * @param id The id for the user to update.
     * @param email The new email.
     * @param name The new name.
     * @return A string describing if the user is successfully updated or not.
     */
    @RequestMapping("/update")
    @ResponseBody
    public String updateUser(long id, String email, String name) {
        try {
            User user = userRepository.findOne(id);
            user.setEmail(email);
            user.setUserName(name);
            userRepository.save(user);
        }
        catch (Exception ex) {
            return "Error updating the user: " + ex.toString();
        }
        return "User succesfully updated!";
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/validate", method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseEntity validateUser(@RequestBody User user) {
        User existingUser = userRepository.findByUserNameAndPassword(user.getUserName(), user.getPassword());
        if(existingUser != null) {
            return new ResponseEntity("success", HttpStatus.OK);
        }
        else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

}
