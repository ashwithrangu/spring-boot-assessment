<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Author</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
    <h1>Add New Author</h1>
    <form:form action="/addAuthor" method="post" modelAttribute="author">
        <div class="form-group">
            <label for="name">Name:</label>
            <form:input path="name" id="name" />
            <form:errors path="name" cssClass="error" element="div" />
        </div>
        <div class="form-group">
            <label for="email">Email:</label>
            <form:input path="email" id="email" />
            <form:errors path="email" cssClass="error" element="div" />
        </div>
        <button type="submit" class="submit-btn">Save</button>
        <a href="/" class="button" style="background-color: #6c757d;">Cancel</a>
    </form:form>
</body>
</html>
