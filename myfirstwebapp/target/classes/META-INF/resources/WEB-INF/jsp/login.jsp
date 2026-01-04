<html>
	<head>
		<title>Login Page</title>
	</head>
	<body>
		<div class="container">
			<h1>Welcome to Login Page!</h1>
			<pre>${error_message}</pre>
			<form method="post" action="login">
				Name: <input type="text" name="name" />
				Password: <input type="password" name="password" />
				<input type="submit" />
			</form>
		</div>	
	</body>
</html>