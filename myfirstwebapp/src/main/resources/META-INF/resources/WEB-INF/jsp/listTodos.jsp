<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
	<head>
		<title>Todos Page</title>
	</head>
	<body>
		<div>Welcome ${name}!</div>
		<hr>
		<h1>Your Todo's:</h1>
		<table>
			<thead>
				<tr>
					<th>id</th>
					<th>Description</th>
					<th>Target Date</th>
					<th>Is Done?</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${todos}" var="todo">
					<tr>
<<<<<<< Updated upstream
						<td>${todo.id}</td>
						<td>${todo.description}</td>
						<td>${todo.targetDate}</td>
						<td>${todo.done}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table> 
=======
						<th>id</th>
						<th>Description</th>
						<th>Target Date</th>
						<th>Is Done?</th>
						<th></th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${todos}" var="todo">
						<tr>
							<td>${todo.id}</td>
							<td>${todo.description}</td>
							<td>${todo.targetDate}</td>
							<td>${todo.done}</td>
							<td><a href="delete-todo?id=${todo.id}" class="btn btn-warning" >Delete</a></td>
							<td><a href="update-todo?id=${todo.id}" class="btn btn-success" >Update</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div>
				<a href="add-todo" class="btn btn-success">Add Todo</a>
			</div>
		</div>
		<script src="webjars/bootstrap/5.1.3/js/bootstrap.min.js"></script>
		<script src="webjars/jquery/3.6.0/jquery.min.js"></script>
>>>>>>> Stashed changes
	</body>
</html>