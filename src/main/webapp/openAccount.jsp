<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Open Account</title>
</head>
<body>
    <h2>Open New Account</h2>
    <form action="openAccount" method="post">
        Customer ID: <input type="text" name="customerId" required><br><br>
        Account Type:
        <select name="type">
            <option value="SAVINGS">Savings</option>
            <option value="CURRENT">Current</option>
        </select><br><br>
        <input type="submit" value="Open Account">
    </form>
</body>
</html>
