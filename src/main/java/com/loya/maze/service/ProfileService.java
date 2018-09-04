//package com.loya.maze.service;
//
//import com.loya.maze.dao.ProfileDao;
//import com.loya.maze.entity.Profile;
//import com.loya.maze.exception.ResourceNotFoundException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class ProfileService {
//    @Autowired
//    ProfileDao profileDao;
//
//
//    //create profile
//    public Profile createProfile(Profile profile) {
//        return profileDao.save(profile);
//    }
//
//    //get All profiles
//    public List<Profile> getProfiles() {
//        return profileDao.findAll();
//    }
//
//
//    //getProfileById
//    public Optional<Profile> getProfileById(String profileId) {
//        return profileDao.findById(profileId);
//    }
//
//
//    //update user
//    public Profile updateProfileById(String profileId, Profile profileRequest) {
//        Optional<Profile> byId = profileDao.findById(profileId);
//        if (!byId.isPresent()) {
//            throw new ResourceNotFoundException("profile not found with id " + profileId);
//        }
//
//        Profile profile = byId.get();
//        profile.setTwitterHandle(profileRequest.getTwitterHandle());
//        profile.setInstagramHandle(profileRequest.getInstagramHandle());
//        profile.setFacebookHandle(profileRequest.getFacebookHandle());
//        profile.setSummary(profileRequest.getSummary());
//        profile.setContact(profileRequest.getContact());
//        profile.setPhotos(profileRequest.getPhotos());
//
//        return profileDao.save(profile);
//    }
//
//    //delete All Profiles
//    public void deleteAllProfiles() {
//        profileDao.deleteAll();
//    }
//
//    //delete Profile by id
//    public void deleteProfileById(String profileId) {
//        profileDao.deleteById(profileId);
//    }
//
//    //delete Profile object
//    public void deleteProfile(Profile profile) {
//        profileDao.delete(profile);
//    }
//
//
//}
