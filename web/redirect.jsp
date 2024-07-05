<%-- 
    Document   : redirect
    Created on : Jul 3, 2024, 11:09:06 PM
    Author     : OwO
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Redirect</title>
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            font-family: Arial, sans-serif;
        }
        .container {
            text-align: center;
            border: 1px solid #ddd;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            max-width: 600px;
            background-color: #f9f9f9;
        }
        h1 {
            margin-bottom: 20px;
        }
        .button-group {
            margin-top: 20px;
        }
        .button-group a {
            margin: 0 10px;
            padding: 10px 20px;
            background-color: #007bff;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            display: inline-block;
        }
        .button-group a:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Have fun reading books!</h1>
        <div class="button-group">
            <a href="index.jsp">Back to Home</a>
            <a href="BooksServlet">Book List</a>
        </div>
    </div>
</body>
</html>
