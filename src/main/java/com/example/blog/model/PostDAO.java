package com.example.blog.model;

import com.example.blog.utility.DatabaseUtil;

import javax.xml.crypto.Data;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PostDAO {
    public static boolean createPost(Post post) {
        try {
            Connection connection = DatabaseUtil.getConnection();
            String sql = "INSERT INTO posts (title, content, author, createAt) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, post.getTitle());
            preparedStatement.setString(2, post.getContent());
            preparedStatement.setString(3, post.getAuthor());
            // Convert into .sqlTimestamp
            Timestamp timestamp = Timestamp.valueOf(post.getCreateAt());
            preparedStatement.setTimestamp(4, timestamp);

            int rows = preparedStatement.executeUpdate();
            return rows > 0;

        } catch (Exception e) {
            System.out.println("Post create unsuccessfully");
            return false;
        }
    }
    public static Post getPostById(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DatabaseUtil.getConnection();
            String sql = "SELECT * FROM posts WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();

            // Handle null data
            if (!resultSet.next()) {
                return null;
            }

            Post post = new Post();
            post.setId(resultSet.getInt("id"));
            post.setTitle(resultSet.getString("title"));
            post.setContent(resultSet.getString("content"));
            post.setAuthor(resultSet.getString("author"));

            Timestamp timestamp = resultSet.getTimestamp("createAt");
            LocalDateTime createAt = timestamp.toLocalDateTime();
            post.setCreateAt(createAt);
            return post;
        } catch (Exception e) {
            System.out.println("Data not found!");
            return null;
        } finally {
            try {
                // Close resource
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }


        }
    }

    public static List<Post> getAllPosts() {
        List<Post> posts = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseUtil.getConnection();
            String sql = "SELECT * FROM posts";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String content = resultSet.getString("content");
                String author = resultSet.getString("author");
                LocalDateTime createAt = resultSet.getTimestamp("createAt").toLocalDateTime();

                Post post = new Post();
                post.setId(id);
                post.setTitle(title);
                post.setContent(content);
                post.setAuthor(author);
                post.setCreateAt(createAt);

                posts.add(post);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            // Don't forget to close your resources
            if (resultSet != null) try { resultSet.close(); } catch (SQLException ignore) {}
            if (preparedStatement != null) try { preparedStatement.close(); } catch (SQLException ignore) {}
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
        }
        return posts;
    }
}
