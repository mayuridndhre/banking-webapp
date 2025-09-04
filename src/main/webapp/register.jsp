<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Register Customer</title>
    <!-- Bootstrap CDN -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
</head>
<body class="bg-light">

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card shadow-lg rounded-4">
                <div class="card-header bg-primary text-white text-center fs-4">
                    Register Customer
                </div>
                <div class="card-body">
                    <form action="register" method="post">
                        <div class="mb-3">
                            <label class="form-label">First Name:</label>
                            <input type="text" name="firstName" class="form-control" required>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Last Name:</label>
                            <input type="text" name="lastName" class="form-control" required>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Email:</label>
                            <input type="email" name="email" class="form-control" required>
                        </div>
                        <button type="submit" class="btn btn-success w-100">Register</button>
                    </form>
                </div>
                <div class="card-footer text-center">
                    <a href="index.jsp" class="btn btn-link">Back to Home</a>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
