package com.example.blog.controller;

import com.example.blog.model.Post;
import com.example.blog.model.PostDAO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet("/CreatePost")
public class CreatePostServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            // Get parameters from the request
            String title = request.getParameter("title");
            String content = request.getParameter("content");
            String author = request.getParameter("author");
            LocalDateTime createAt = LocalDateTime.now();

            // Create an instance of Post
            Post newPost = new Post();
            newPost.setTitle(title);
            newPost.setContent(content);
            newPost.setAuthor(author);
            newPost.setCreateAt(createAt);

            if (PostDAO.createPost(newPost)) {
                response.sendRedirect("success.jsp");
            } else {
                response.sendRedirect("failure.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }

    }
}
