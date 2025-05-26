<%-- 
    Document   : error
    Created on : May 26, 2025, 8:21:11 AM
    Author     : Joel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" isErrorPage="true"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
    <title>Error</title>
</head>
<body>
    <h1>ERROR</h1>
    
    <%
        // Obtener mensajes del request
        Object mensajesObj = request.getAttribute("mensajes");
        if (mensajesObj != null && mensajesObj instanceof List) {
            List<String> mensajes = (List<String>) mensajesObj;
            for (String mensaje : mensajes) {
    %>
                <p><strong>* <%= mensaje %></strong></p>
    <%      }
        } else if (exception != null) { %>
            <p><strong>Error: <%= exception.getMessage() %></strong></p>
            <p>Tipo: <%= exception.getClass().getSimpleName() %></p>
    <%  } else { %>
            <p><strong>Ha ocurrido un error inesperado</strong></p>
    <%  } %>
    
    <br>
    <a href="login.jsp">Volver al login</a>
</body>
</html>
