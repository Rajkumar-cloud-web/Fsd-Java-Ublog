package com.upgrad.ublog.dtos;

import java.sql.Date;
import java.time.LocalDateTime;

/**
 * TODO: 1.6. Declare 6 private instance variables in this class named as postId,
 *  emailId, tag, title, description and timestamp. Out of these 6 variables, postId
 *  will be of type int and timestamp will be of type LocalDateTime
 *  (import java.time.LocalDateTime). Other four variables will be of type String.
 *
 * TODO: 1.7. Provide getters and setters for each of the instance variables. Also,
 *  provide a no-arg constructor and a parameterized constructor to create objects
 *  of Post class.
 *
 * Note: Uncomment the toString() method given below, instead of writing a new one.
 */

public class Post {

    private int postId;
    private String emailId;
    private String tag;
    private String title;
    private String description;
    private String timestamp;

    public Post(){

    }

    public Post(int postId, String emailId, String tag, String title, String description, String timestamp){
        this.postId = postId;
        this.emailId = emailId;
        this.tag = tag;
        this.title = title;
        this.description = description;
        this.timestamp = timestamp;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Post{" +
                "postId=" + postId +
                ", emailId='" + emailId + '\'' +
                ", tag='" + tag + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }

//    public static void main(String[] args) {
//        Post post = new Post();
//        post.setPostId(1);
//        post.setEmailId("dummy@dummy.com");
//        post.setTag("tag");
//        post.setTitle("title");
//        post.setDescription("Description");
//        post.setTimestamp(LocalDateTime.now());
//
//        System.out.println(post);
//
//        /**
//         * Your output should be similar to as shown below.
//         * Post{postId=1, emailId='dummy@dummy.com', tag='tag', title='title', description='Description', timestamp=2020-05-22T21:01:34.483363800}
//         */
//    }
}