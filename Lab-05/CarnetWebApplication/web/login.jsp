<%-- 
    Document   : login
    Created on : May 26, 2025, 8:21:45 AM
    Author     : Joel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" errorPage="error.jsp"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="auth.ComprobarUsuario"%>

<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <h1>SISTEMA DE LOGIN</h1>
    
    <%
        // Si es POST, procesar el login
        if ("POST".equals(request.getMethod())) {
            String usuario = request.getParameter("usuario");
            String contrasenia = request.getParameter("contrasenia");
            
            List<String> mensajes = new ArrayList<String>();
            boolean hasError = false;
            
            try {
                // Validaciones básicas
                if (usuario == null || usuario.trim().isEmpty()) {
                    mensajes.add("El usuario es obligatorio");
                    hasError = true;
                }
                
                if (contrasenia == null || contrasenia.trim().isEmpty()) {
                    mensajes.add("La contraseña es obligatoria");
                    hasError = true;
                }
                
                // Si hay errores, mostrar página de error
                if (hasError) {
                    request.setAttribute("mensajes", mensajes);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
                    dispatcher.forward(request, response);
                    return;
                }
                
                // Validar con el servicio
                ComprobarUsuario comprobarUsuario = new ComprobarUsuario();
                boolean esValido = comprobarUsuario.comprobar(usuario, contrasenia);
                
                if (esValido) {
                    session.setAttribute("usuario", usuario);
    %>
                    <h2>¡BIENVENIDO <%= usuario %>!</h2>
                    <p>Login exitoso</p>
                    <p>Hora de ingreso: <%= new java.util.Date() %></p>
    <%
                } else {
                    mensajes.add("Usuario o contraseña incorrectos");
                    mensajes.add("Credenciales: admin / 1234");
                    request.setAttribute("mensajes", mensajes);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
                    dispatcher.forward(request, response);
                    return;
                }
                
            } catch (Exception e) {
                // Si hay excepción, será manejada por errorPage="error.jsp"
                throw e;
            }
            
        } else {
            // Mostrar formulario de login
    %>
    
    <form method="post">
        <p>
            Usuario: <input type="text" name="usuario" required>
        </p>
        <p>
            Contraseña: <input type="password" name="contrasenia" required>
        </p>
        <p>
            <input type="submit" value="Ingresar">
        </p>
    </form>
    
    <p><small>Usuario: admin | Contraseña: 1234</small></p>
    
    <%
        }
    %>
</body>
</html>
