<%@ page import="com.example.blog.model.Post" %>
<%@ page import="java.sql.Time" %>
<%@ page import="com.example.blog.utility.TimeAgoUtil" %><%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 9/2/2023
  Time: 11:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Post</title>
</head>
<body>
<h1>POST</h1>
<%
    // Fetch the Post object from the request attributes
    Post post = (Post) request.getAttribute("post");
    if (post != null) {
%>
<!-- Display the post details -->
<h2><%= post.getTitle() %></h2>
<p>By: <%= post.getAuthor() %></p>
<p>Created At: <%= TimeAgoUtil.getTimeAgo(post.getCreateAt()) %></p>
<div>
    <%= post.getContent() %>
</div>
<%
} else {
%>
<p>No post to display.</p>
<%
    }
%>
</body>
</html>
