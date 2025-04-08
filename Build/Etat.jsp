<%@ page import="main.java.model.Prevision" %>
<%
    Prevision[] emps = (Prevision[]) request.getAttribute("previsions");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Liste des etats</title>
</head>
<body>
    <h2>Liste des etats</h2>
    <table>
        <thead>
            <tr>
                <th>Previson</th>
                <th>Montant</th>
                <th>Reste</th>
            </tr>
        </thead>
        <tbody>
            <% for (Prevision emp : emps) { %>
                <tr>
                    <td><%= emp.getLibelle() %></td>
                    <td><%= emp.getPric() %></td>
                    <td><%= emp.getReste() %></td>
                </tr>
            <% } %>
        </tbody>
    </table>
</body>
</html>