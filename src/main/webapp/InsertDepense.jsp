<%@ page import="main.java.model.Prevision" %>
<%
    Prevision[] previsions = (Prevision[]) request.getAttribute("prevision");
%>
<body>
    <form name="form1" method="post" action="faireDepense"> 
        <p>
            <select name="libelles"> 
                <% for (Prevision prevision : previsions) { %>
                    <option value="<%= prevision.getId_prevision() %>">
                        <%= prevision.getLibelle() %>
                    </option>
                <% } %>
            </select>
        </p>
        
        <fieldset>
            <legend>Montant a depenser</legend> 
            <input type="number" id="number" name="montantdepense" placeholder="ex : 300000">
        </fieldset>
        <input type="submit" name="submit" value="Valider votre depense">
    </form>
</body>