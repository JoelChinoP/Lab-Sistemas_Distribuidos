/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package compra.productos;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Joel
 */
@WebService(serviceName = "ComprasDeProducto")
public class ComprasDeProducto {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }
    
    /**
    * Web service operation
    */
   @WebMethod(operationName = "comprasProductos")
   public String comprasProductos(
           @WebParam(name = "CantidadPan") int CantidadPan, 
           @WebParam(name = "CantidadQueso") int CantidadQueso, 
           @WebParam(name = "CantidadPlatanos") int CantidadPlatanos, 
           @WebParam(name = "CantidadNaranjas") int CantidadNaranjas) {

       // Precios por unidad
       double precioPan = 1.50;
       double precioQueso = 2.00;
       double precioPlatanos = 0.50;
       double precioNaranjas = 0.75;

       // Calcular subtotal por producto
       double subtotalPan = CantidadPan * precioPan;
       double subtotalQueso = CantidadQueso * precioQueso;
       double subtotalPlatanos = CantidadPlatanos * precioPlatanos;
       double subtotalNaranjas = CantidadNaranjas * precioNaranjas;

       // Calcular total
       double total = subtotalPan + subtotalQueso + subtotalPlatanos + subtotalNaranjas;

       // Generar resumen
       String resumen = "Resumen de compra:\n"
               + "- Pan (" + CantidadPan + "): $" + String.format("%.2f", subtotalPan) + "\n"
               + "- Queso (" + CantidadQueso + "): $" + String.format("%.2f", subtotalQueso) + "\n"
               + "- Plátanos (" + CantidadPlatanos + "): $" + String.format("%.2f", subtotalPlatanos) + "\n"
               + "- Naranjas (" + CantidadNaranjas + "): $" + String.format("%.2f", subtotalNaranjas) + "\n"
               + "Total: $" + String.format("%.2f", total);

       return resumen;
   }
}
