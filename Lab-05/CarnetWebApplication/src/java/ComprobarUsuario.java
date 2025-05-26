/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package auth;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Alexis
 */
@WebService(serviceName = "ComprobarUsuario")
public class ComprobarUsuario {
    
    private final String usuarioValido = "admin";
    private final String contraseniaValida = "1234";

    /**
     * This is a sample web service operation
     * @param txt
     * @return 
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }
    
    @WebMethod(operationName = "Comprobar")
    public boolean comprobar (
            @WebParam(name = "usuario") String user,
            @WebParam(name = "contrasenia") String contra
            ) {
        try {
            // Validar que no sean null o vacíos
            if (user == null || contra == null || user.trim().isEmpty() || contra.trim().isEmpty()) {
                throw new IllegalArgumentException("Usuario y contraseña son obligatorios");
            }
            
            return usuarioValido.equals(user.trim()) && contraseniaValida.equals(contra);
            
        } catch (IllegalArgumentException e) {
            // Lanzar la excepción para que sea manejada por JSP
            throw new RuntimeException("Error al validar credenciales: " + e.getMessage());
        }
    }
}
