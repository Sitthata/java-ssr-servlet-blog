package com.example.blog.controller;

import com.example.blog.model.Post;
import com.example.blog.model.PostDAO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/ViewAllPosts")
public class ViewAllPostsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            List<Post> allPosts = PostDAO.getAllPosts();

            if (allPosts != null && !allPosts.isEmpty()) {
                request.setAttribute("allPosts", allPosts);
                request.getRequestDispatcher("/all-post.jsp").forward(request, response);
            } else {
                response.sendRedirect("no-post-found.jsp");
            }
        } catch (Exception e) {
            response.sendRedirect("error.jsp");
        }
    }
}
