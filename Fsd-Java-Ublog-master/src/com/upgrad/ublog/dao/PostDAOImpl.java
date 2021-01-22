package com.upgrad.ublog.dao;

/**
 * TODO: 3.19. Implement the PostsDAO interface and implement this class using the Singleton pattern.
 *  (Hint: Should have a private no-arg Constructor, a private static instance attribute of type
 *  PostDAOImpl and a public static getInstance() method which returns the instance attribute.)
 * TODO: 3.20. Define the following methods and return null for each of them. You will provide a
 *  proper implementation for each of these methods, later in this project.
 *  a. findByEmailId()
 *  b. findByTag()
 *  c. findAllTags()
 *  d. findByPostId()
 *  e. deleteByPostId() (return false for this method for now)
 * TODO: 3.21. create() method should take post details as input and insert these details into
 *  the POST table. Return the same Post object which was passed as an input argument.
 *  (Hint: You should get the connection using the DatabaseConnection class)
 */

/**
 * TODO: 4.1. Implement findByEmailId() method which takes email id as an input parameter and
 *  returns all the posts corresponding to the email id from the Post table defined
 *  in the database.
 */

/**
 * TODO: 4.4. Implement the deleteByPostId() method which takes post id as an input argument and delete
 *  the corresponding post from the database. If the post was deleted successfully, then return true,
 *  otherwise, return false. (Hint: The executeUpdate() method returns the count of rows affected by the
 *  query.)
 * TODO: 4.5. Implement the findByPostId() method which takes post id as an input argument and return the
 *  post details from the database. If no post exists for the given id, then return a Post object
 *  without setting any of its attributes.
 */

import com.upgrad.ublog.db.Database;
import com.upgrad.ublog.dtos.Post;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO: 4.8. Implement findAllTags() method which returns a list of all tags in the POST table.
 * TODO: 4.9. Implement findByTag() method which takes "tag" as an input argument and returns all the
 *  posts corresponding to the input "tag" from the POST table defined in the database.
 */

public class PostDAOImpl implements PostDAO {

    private static PostDAOImpl instance = new PostDAOImpl();

    public static PostDAOImpl getInstance() {
        if(instance == null){
            instance = new PostDAOImpl();
        }
        return instance;
    }

    @Override
    public Post create(Post post) throws SQLException {
        Connection connection = Database.getConnection();

        Statement statement = connection.createStatement();

        String sql = "insert into post values (" + post.getPostId() + ", '" + post.getEmailId() + "', '" +
                post.getTag() + "', '" + post.getTitle() + "', '" + post.getDescription() + "', '" +
                post.getTimestamp() + "')";

        statement.executeUpdate(sql);

        return post;
    }

    @Override
    public List<Post> findByEmailId(String emailId) throws SQLException {

        List<Post> posts = new ArrayList<>();

        Connection connection = Database.getConnection();
        Statement statement = connection.createStatement();
        String sql = "SELECT * FROM post WHERE emailId = '" + emailId + "'";

        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            Post temp = new Post();
            temp.setPostId(resultSet.getInt("postId"));
            temp.setEmailId(resultSet.getString("emailId"));
            temp.setTag(resultSet.getString("tag"));
            temp.setTitle(resultSet.getString("title"));
            temp.setDescription(resultSet.getString("description"));
            temp.setTimestamp(resultSet.getString("timestamp"));

            posts.add(temp);
        }

        return posts;
    }

    @Override
    public List<Post> findByTag(String tag) throws SQLException {

        List<Post> tags = new ArrayList<>();

        Connection connection = Database.getConnection();
        Statement statement = connection.createStatement();
        String sql = "SELECT * FROM post WHERE tag = '" + tag + "'";

        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            Post post = new Post();
            post.setPostId(resultSet.getInt("postId"));
            post.setEmailId(resultSet.getString("emailId"));
            post.setTag(resultSet.getString("tag"));
            post.setTitle(resultSet.getString("title"));
            post.setDescription(resultSet.getString("description"));
            post.setTimestamp(resultSet.getString("timestamp"));

            tags.add(post);
        }

        return tags;
    }

    @Override
    public Post findByPostId(int postId) throws SQLException {

        Post post = new Post();

        Connection connection = Database.getConnection();
        Statement statement = connection.createStatement();
        String sql = "SELECT * FROM post WHERE postId = " + postId;

        ResultSet resultSet = statement.executeQuery(sql);

        if(!resultSet.next()){
            return null;
        }

        do {
            post.setPostId(resultSet.getInt("postId"));
            post.setEmailId(resultSet.getString("emailId"));
            post.setTag(resultSet.getString("tag"));
            post.setTitle(resultSet.getString("title"));
            post.setDescription(resultSet.getString("description"));
            post.setTimestamp(resultSet.getString("timestamp"));
        } while (resultSet.next());

        return post;
    }

    @Override
    public List<String> findAllTags() throws SQLException {
        List<String> tags = new ArrayList<>();

        Connection connection = Database.getConnection();
        Statement statement = connection.createStatement();
        String sql = "SELECT tag FROM post";

        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            tags.add(resultSet.getString("tag"));
        }

        return tags;

    }

    @Override
    public boolean deleteByPostId(int postId) throws SQLException {

        Connection connection = Database.getConnection();
        Statement statement = connection.createStatement();
        String sql = "Delete from post where postId = " + postId;
        int count = statement.executeUpdate(sql);
        return count != 0;
    }
}