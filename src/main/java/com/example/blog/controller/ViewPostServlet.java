package com.example.blog.controller;

import com.example.blog.model.Post;
import com.example.blog.model.PostDAO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet("/ViewPost")
public class ViewPostServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String postIdParam = request.getParameter("id");
            if (postIdParam == null || postIdParam.isEmpty()) {
                response.sendRedirect("error.jsp");
                return;
            }

            int postId = Integer.parseInt(postIdParam);

            // Fetch post from DAO
            Post post = PostDAO.getPostById(postId);

            if (post != null) {
                // Forward the data to View
                request.setAttribute("post", post);
                request.getRequestDispatcher("/post.jsp").forward(request, response);
            } else {
                // Post not found
                response.sendRedirect("post-not-found.jsp");
            }
        } catch (Exception e) {
            response.sendRedirect("error.jsp");
        }

    }
}
