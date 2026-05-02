<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Library Management</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
    <h1>Library Management System</h1>

    <c:if test="${not empty successMessage}">
        <div class="message success">${successMessage}</div>
    </c:if>
    <c:if test="${not empty errorMessage}">
        <div class="message error">${errorMessage}</div>
    </c:if>

    <h2>Authors</h2>
    <a href="/addAuthor" class="button">Add New Author</a>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Email</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="author" items="${authors}">
                <tr>
                    <td>${author.id}</td>
                    <td>${author.name}</td>
                    <td>${author.email}</td>
                    <td><a href="/editAuthor/${author.id}">Edit</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <h2>Books (with Authors - Inner Join Query)</h2>
    <a href="/addBook" class="button">Add New Book</a>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Title</th>
                <th>ISBN</th>
                <th>Author Name</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="book" items="${books}">
                <tr>
                    <td>${book.id}</td>
                    <td>${book.title}</td>
                    <td>${book.isbn}</td>
                    <td>${book.author.name}</td>
                    <td><a href="/editBook/${book.id}">Edit</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
