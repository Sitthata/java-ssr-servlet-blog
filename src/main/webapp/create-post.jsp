<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 9/2/2023
  Time: 10:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add new Post</title>
</head>
<body>
<nav>
    <li><a href="ViewAllPosts">Back</a></li>
</nav>
<h1>Create a new Post</h1>
<form action="CreatePost" method="POST">
    <div>
        <label for="title">Title:</label><br>
        <input type="text" id="title" name="title" required>
    </div>

    <div>
        <label for="content">Content:</label><br>
        <textarea id="content" name="content" rows="10" cols="50" required></textarea>
    </div>

    <div>
        <label for="author">Author:</label><br>
        <input type="text" id="author" name="author" required>
    </div>

    <div>
        <input type="submit" value="Create Post">
    </div>
</form>
</body>
</html>
