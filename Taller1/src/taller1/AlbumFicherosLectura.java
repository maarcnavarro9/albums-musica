/*
Autor: Marc Navarro Amengual
CLASE AlbumFicherosLectura
Aglutina las delcaraciones y funcionalidades necesarias para posibilitar la lectura
de objetos Album desde un fichero de texto
 */
package taller1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class AlbumFicherosLectura {
    //DECLARACIONES
    //declaración atributo de objeto BufferedReader que posibilite el enlace
    //con el fichero de texto a nivel de lectura
    private BufferedReader fichero=null;
    
    //MÉTODOS
    
    //MÉTODO CONSTRUCTOR
    public AlbumFicherosLectura(String nombreFichero){
        try {
            //establecimiento enlace BufferedReader con fichero de texto identificado
            //a través del parámetro String nombreFichero dado
            fichero=new BufferedReader(new FileReader(nombreFichero));            
        }catch (FileNotFoundException error) {
            System.out.println("ERROR EN LA APERTURA DEL FICHERO");
        }
    }
    
    //MÉTODOS FUNCIONALES
    
    //método lectura lleva a cabo la lectura de un objeto Album desde el fichero
    //representado por el objeto BufferedReader correspondiente
    public Album lectura() {
        //DECLARACIONES
        //declaración objeto Album que reporesentara el Album leído desde
        //el fichero
        Album album=null;
        //declaración String linea para almacenar las lineas leidas desde el fichero
        String linea=null;
        //declaraciones Strings para representar las variables temporales correspondientes
        //a los atributos titulo, artista y tipo de un objeto Album
        String titulo,artista,tipo;
        //declaración variable entera para representar la variable temporal correspondiente
        //al atributo posicion, añoEdicion y ediciones de un objeto Album
        int posicion,añoEdicion,ediciones;
        //declaración variable double para representar la variable temporal correspondiente
        //al atributo estrellas de un objeto Album
        double estrellas;
        
        //ACCIONES
        try {
            //lectura linea desde el fichero
            linea=fichero.readLine();
            if (linea!=null) {
                //asignar a posicion la linea leída, una vez convertida a entera
                posicion=Integer.parseInt(linea);
                //lectura de la siguiente linea y asignarla a añoEdicion
                añoEdicion=Integer.parseInt(fichero.readLine());
                //lectura siguiente linea y asignación a titulo de la misma
                titulo=fichero.readLine();
                //lectura siguiente linea y asignación a artista
                artista=fichero.readLine();
                //lectura siguiente linea y asignarla a tipo
                tipo=fichero.readLine();
                //obtener el tipo enumerado
                TiposAlbum tipos = TiposAlbum.deString(tipo);
                //lectura siguiente linea y asignación a estrellas
                estrellas=Double.parseDouble(fichero.readLine());
                //lectura siguiente linea y asignación a ediciones
                ediciones=Integer.parseInt(fichero.readLine());
                //instanciación objeto Album
                album=new Album(posicion,añoEdicion,titulo,artista,tipos,
                        estrellas,ediciones);
            } 
        }catch (IOException error) {
            System.out.println("ERROR DE LECTURA EN EL FICHERO");
        }
        finally {
            //Devolver el objeto Pelicula
            return album;     
        }
    }
    
    //MÉTODO cerrarEnlaceFichero QUE LLEVA A CABO EL CIERRE DEL ENLACE BufferedReader
    //con el fichero 
    public void cerrarEnlaceFichero() {
        try {
            fichero.close();
        }catch (IOException error) {
            System.out.println("ERROR AL CERRAR EL FICHERO");
        }
    } 
}
