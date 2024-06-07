<!DOCTYPE html>
<html>
<head>
    <title>Affectation de Projet</title>
</head>
<body>
<h2>Affectation de Projet</h2>
<a href="/employees">Employees</a> | <a href="/">Back to Home</a>
<form action="/projects/assign" method="post">
    <label for="employeeId">Nom de l'employé:</label>
    <select name="employeeId" id="employeeId">
    <c:forEach var="employee" items="${employees}">
            <option value="${employee.id}">${employee.name}</option>
        </c:forEach>

    </select>
    <br>
    <label for="projectId">Nom du projet:</label>
    <select name="projectId" id="projectId">
        <c:forEach var="project" items="${projects}">
            <option value="${project.id}">${project.name}</option>
        </c:forEach>
    </select>
    <br>
    <label for="implication" id="implication">Implication:</label>
    <input type="number" name="implication" min="0" max="100" value="20"/> %
    <br>
    <button type="submit">Affecter projet</button>
</form>
</body>
</html>
