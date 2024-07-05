<%-- 
    Document   : libraryRules
    Created on : Jul 3, 2024, 10:49:21 PM
    Author     : OwO
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Library Rules</title>
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
        ul {
            text-align: left;
            padding-left: 0;
        }
        .back-button {
            margin-top: 20px;
            padding: 10px 20px;
            background-color: #007bff;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            display: inline-block;
        }
        .back-button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Welcome to the Library</h1>
        <h2>Library Rules</h2>
        <ul>
            <li>If you register, make sure to enter a valid email address.</li>
            <li>Inform the staff to create your borrow card to borrow books.</li>
            <li>Maintain silence within the library premises.</li>
            <li>Return borrowed books on or before the due date.</li>
            <li>Handle books and other materials with care.</li>
            <li>Respect other library users and staff.</li>
        </ul>
        <a href="index.jsp" class="back-button">Back to Home</a>
    </div>
</body>
</html>
