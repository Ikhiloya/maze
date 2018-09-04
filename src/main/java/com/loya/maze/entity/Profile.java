//package com.loya.maze.entity;
//
//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.Document;
//
////import javax.persistence.Id;
//import java.util.List;
//
//@Document(collection = "Profile")
//public class Profile {
//    @Id
//    private String id;
//    private String twitterHandle;
//    private String instagramHandle;
//    private String facebookHandle;
//    private String summary;
//    private Contact contact;
//    private List<Photo> photos;
//
//
//    protected Profile() {
//    }
//
//    public Profile(String id, String twitterHandle, String instagramHandle, String facebookHandle, String summary, Contact contact, List<Photo> photos) {
//        this.id = id;
//        this.twitterHandle = twitterHandle;
//        this.instagramHandle = instagramHandle;
//        this.facebookHandle = facebookHandle;
//        this.summary = summary;
//        this.contact = contact;
//        this.photos = photos;
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getTwitterHandle() {
//        return twitterHandle;
//    }
//
//    public void setTwitterHandle(String twitterHandle) {
//        this.twitterHandle = twitterHandle;
//    }
//
//    public String getInstagramHandle() {
//        return instagramHandle;
//    }
//
//    public void setInstagramHandle(String instagramHandle) {
//        this.instagramHandle = instagramHandle;
//    }
//
//    public String getFacebookHandle() {
//        return facebookHandle;
//    }
//
//    public void setFacebookHandle(String facebookHandle) {
//        this.facebookHandle = facebookHandle;
//    }
//
//    public String getSummary() {
//        return summary;
//    }
//
//    public void setSummary(String summary) {
//        this.summary = summary;
//    }
//
//    public Contact getContact() {
//        return contact;
//    }
//
//    public void setContact(Contact contact) {
//        this.contact = contact;
//    }
//
//    public List<Photo> getPhotos() {
//        return photos;
//    }
//
//    public void setPhotos(List<Photo> photos) {
//        this.photos = photos;
//    }
//}
