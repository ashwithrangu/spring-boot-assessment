<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Book</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
    <h1>Add New Book</h1>
    <form:form action="/addBook" method="post" modelAttribute="book">
        <div class="form-group">
            <label for="title">Title:</label>
            <form:input path="title" id="title" />
            <form:errors path="title" cssClass="error" element="div" />
        </div>
        <div class="form-group">
            <label for="isbn">ISBN:</label>
            <form:input path="isbn" id="isbn" />
            <form:errors path="isbn" cssClass="error" element="div" />
        </div>
        <div class="form-group">
            <label for="author">Author:</label>
            <form:select path="author" id="author">
                <form:option value="" label="-- Select Author --" />
                <form:options items="${authors}" itemValue="id" itemLabel="name" />
            </form:select>
            <form:errors path="author" cssClass="error" element="div" />
        </div>
        <button type="submit" class="submit-btn">Save</button>
        <a href="/" class="button" style="background-color: #6c757d;">Cancel</a>
    </form:form>
</body>
</html>
