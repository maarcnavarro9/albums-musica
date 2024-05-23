/*
Autor: Marc Navarro Amengual
EXEPCIÓN NO PREDEFINIDA
 */

package taller1;

public class InsercionDatosExcepcion extends Exception {
    public InsercionDatosExcepcion (String mensaje) {
        super("¡¡¡¡ "+mensaje+" !!!!");
    }
}
