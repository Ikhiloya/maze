//package com.loya.maze.controller;
//
//import com.loya.maze.entity.Profile;
//import com.loya.maze.service.ProfileService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//public class MazeController {
//
//    @Autowired
//    ProfileService profileService;
//
//
//    @RequestMapping(value = "/profile", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
//    public Profile createProfile(@Valid @RequestBody Profile profile) {
//
//        return profileService.createProfile(profile);
//    }
//
//    @RequestMapping(value = "/profiles", method = RequestMethod.GET)
//    public List<Profile> getProfiles() {
//        return profileService.getProfiles();
//    }
//
//    //getProfileById
//    @RequestMapping(value = "/profile/{profileId}", method = RequestMethod.GET)
//    public Optional<Profile> getProfileById(@PathVariable String profileId) {
//        return profileService.getProfileById(profileId);
//    }
//
//    //update Profile
//    @RequestMapping(value = "/profile/{profileId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
//    public Profile updateProfileById(@PathVariable String profileId, @RequestBody Profile profileRequest) {
//        return profileService.updateProfileById(profileId, profileRequest);
//    }
//
//    //delete All Profiles
//    @RequestMapping(value = "/profiles", method = RequestMethod.DELETE)
//    public void deleteAllProfiles() {
//        profileService.deleteAllProfiles();
//    }
//
//    //delete Profile by id
//    @RequestMapping(value = "/profile/{profileId}", method = RequestMethod.DELETE)
//    public void deleteById(@PathVariable String profileId) {
//        profileService.deleteProfileById(profileId);
//    }
//
//    //delete Profile object
//    @RequestMapping(value = "/deleteProfile", method = RequestMethod.DELETE)
//    public void deleteProfile(@RequestBody Profile profile) {
//        profileService.deleteProfile(profile);
//    }
//
//
//
//
//
////
////    //get  All Users
////    @RequestMapping(value = "/users", method = RequestMethod.GET)
////    public List<User> getUsers() {
////
////        PasswordEncoderGenerator.encodePassword("ikhiloya");
////        return customUserDetailsService.getUsers();
////    }
////
////
////    //getUserById
////    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
////    public Optional<User> getUserById(@PathVariable String userId) {
////        return customUserDetailsService.getUserById(userId);
////    }
////
////    //update User
////    @RequestMapping(value = "/user/{userId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
////    public User updateUserById(@PathVariable String userId, @RequestBody User userRequest) {
////        return customUserDetailsService.updateUserById(userId, userRequest);
////    }
////
////
////    //delete All Users
////    @RequestMapping(value = "/users", method = RequestMethod.DELETE)
////    public void deleteAllUsers() {
////        customUserDetailsService.deleteAllUsers();
////    }
////
////    //delete User by id
////    @RequestMapping(value = "/user/{userId}", method = RequestMethod.DELETE)
////    public void deleteUserById(@PathVariable String userId) {
////        customUserDetailsService.deleteUserById(userId);
////    }
////
////    //delete User object
////    @RequestMapping(value = "/deleteUser", method = RequestMethod.DELETE)
////    public void deleteUser(@RequestBody User user) {
////        customUserDetailsService.deleteUser(user);
////    }
//
//
//}
