package com.upgrad.ublog.dao;

import com.upgrad.ublog.dtos.Post;

import java.sql.SQLException;
import java.util.List;

public interface PostDAO {
    public Post create(Post post) throws SQLException;
    public List<Post> findByEmailId(String emailId) throws SQLException;
    public List<Post> findByTag(String tag) throws SQLException;
    public Post findByPostId(int postId) throws SQLException;
    public List<String> findAllTags() throws SQLException;
    public boolean deleteByPostId(int postId) throws SQLException;
}
