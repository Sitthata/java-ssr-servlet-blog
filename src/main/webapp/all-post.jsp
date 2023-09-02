<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 9/2/2023
  Time: 11:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.blog.model.Post" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.blog.utility.TimeAgoUtil" %>
<html>
<head>
    <title>All Blog Post</title>
</head>
<body>
    <nav>
        <li><a href="create-post.jsp">Add New Post</a></li>
    </nav>
    <h1>All Posts</h1>
    <%
        List<Post> allPosts = (List<Post>) request.getAttribute("allPosts");
        if (allPosts != null) {
            for (Post post : allPosts) {
    %>
    <div>
        <h2><a href="ViewPost?id=<%= post.getId() %>"><%= post.getTitle() %></a></h2>
        <p>By: <%= post.getAuthor() %></p>
        <p>Created At: <%= TimeAgoUtil.getTimeAgo(post.getCreateAt()) %></p>
    </div>
    <%
            }
        }
    %>
</body>
</html>
