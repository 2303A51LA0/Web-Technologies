<%@ page import="javax.servlet.http.Cookie" %>
<%
    String name = (String) request.getAttribute("name");
    String course = (String) request.getAttribute("course");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Student Dashboard</title>

    <!-- Font Awesome CDN -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">

    <style>
        @import url('https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap');

        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Poppins', sans-serif;
            background: linear-gradient(120deg, #a1c4fd, #c2e9fb);
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .dashboard-card {
            background: rgba(255, 255, 255, 0.2);
            backdrop-filter: blur(10px);
            border-radius: 20px;
            box-shadow: 0 8px 32px rgba(0, 0, 0, 0.1);
            padding: 40px;
            width: 400px;
            text-align: center;
            color: #2c3e50;
        }

        .profile-icon {
            width: 80px;
            height: 80px;
            background-color: #ffffff55;
            border-radius: 50%;
            margin: 0 auto 20px;
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 36px;
            color: #2980b9;
        }

        h2 {
            font-weight: 600;
            margin-bottom: 10px;
        }

        .course-info {
            font-size: 16px;
            margin: 10px 0;
        }

        .badge {
            display: inline-block;
            padding: 8px 16px;
            background-color: #3498db;
            color: #fff;
            border-radius: 20px;
            font-size: 14px;
            margin-top: 8px;
        }

        .logout-btn {
            display: inline-block;
            margin-top: 30px;
            padding: 12px 26px;
            background-color: #e74c3c;
            color: #fff;
            border: none;
            border-radius: 30px;
            font-size: 15px;
            text-decoration: none;
            cursor: pointer;
            transition: all 0.3s ease;
        }

        .logout-btn:hover {
            background-color: #c0392b;
            transform: scale(1.05);
        }
    </style>
</head>
<body>

<div class="dashboard-card">
    <div class="profile-icon"><i class="fas fa-user-graduate"></i></div>
    <h2>Hello, <%= name %>!</h2>
    <div class="course-info">
        <p>You are enrolled in:</p>
        <span class="badge"><%= course %></span>
    </div>
    <a class="logout-btn" href="LogoutServlet"><i class="fas fa-sign-out-alt"></i> Logout</a>
</div>

</body>
</html>
