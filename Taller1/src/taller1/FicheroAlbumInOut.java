/*
Autor: Marc Navarro Amengual
CALSE FicheroAlbumInOut aglutina las declaraciones y funcionalidades
que posibilitan la lectura y escritura de objetos Album utilizando la clase
RandomAccessFile.
 */
package taller1;

import java.io.*;

public class FicheroAlbumInOut {
    //DECLARACIONES
    //declaración objeto RandomAccessFile para posibilitar la lectura/escritura
    //de objetos Album desde/en fichero
    RandomAccessFile fichero=null;  
   
    //MÉTODOS
    
    //MÉTODO CONSTRUCTOR
    public FicheroAlbumInOut(String nombreFichero) {
        try {
            //instanciación objeto RandomAccessFile para establecer el enlace con
            //el fichero a nivel de lectura/escritura
            fichero = new RandomAccessFile(nombreFichero,"rw");
        }catch (FileNotFoundException error) {
            System.out.println("ERROR: "+error.toString());
        }catch (Exception error) {
            System.out.println("ERRO: "+error.toString());
        }
    }
    
    //MÉTODOS FUNCIONALES
    
    //método lectura que posibilita la lectura de un objeto Album desde el
    //fichero
    public Album lectura() {
        //DECLARACIONES
        //declaración objeto Album
        Album album=new Album();
        //declaración String local que nos sirve como apoyo
        String tipo;
        
        //ACCIONES
        try {
            //lectura atributo posicion del objeto Album
            album.setPosicion(fichero.readInt());
            //lectura atributo votos
            album.setVotos(fichero.readInt());
            //lectura atributo Año Edición
            album.setAñoEdicion(fichero.readInt());
            //lectura campo atributo título
            album.setTitulo(lecturaString(64));
            //lectura campo atributo Artista
            album.setArtista(lecturaString(32));
            //lectura tipo enumerado de álbum
            tipo=lecturaString(32);
            album.setTiposAlbum(TiposAlbum.deString(tipo));
            //lectura del número de estrellas que tiene
            album.setEstrellas(fichero.readDouble());
            //lectura atributo Ediciones
            album.setEdiciones(fichero.readInt());
            
        }catch (EOFException error) {
            return null;
        }
        catch (IOException error) {
            System.out.println("ERROR: "+error.toString());
        }
        return album;
    }
    
    //método que lleva a cabo la lectura de un String desde el fichero a través 
    //de la lectura de los caracteres que lo conforman en función del número 
    //de éstos dado por parámetro
    private String lecturaString(int dimension) {
        //DECLARACIONES
        //declaración String para ir concatenando los caracteres que van a ser
        //leidos desde el fichero
        String campo="";
        
        try {
            //ACCIONES
            //bucle de lectura y concatenación de los caracteres desde el fichero
            for (int i=0;i<dimension;i++) {
                //concatenación en el String campo del caracter leido desde el
                //fichero
                campo=campo+fichero.readChar();
            }  
        }catch (IOException error) {
            System.out.println("ERROR: "+error.toString());            
        }
        //devolución del String resultante
        return campo;
    }
    
    //método lectura que posibilita la lectura de un objeto Album desde el
    //fichero dado el número del elemento de fichero Album
    public Album lectura(int posicion) {
        //DECLARACIONES
        //declaración objeto Contacto
        Album album=new Album();
        //declaración String local que nos sirve como apoyo
        String tipo;
        
        //ACCIONES
        try {
            //verificación si la posición del elemento a leer existe en el fichero
            if ((posicion>0) && (posicion<=(fichero.length()/Album.getDimension()))) {
                //posicionamiento puntero del fichero en el elemento de fichero
                //Elemento dado
                fichero.seek((posicion-1)*(Album.getDimension()));
                //lectura atributo posicion del objeto Album
                album.setPosicion(fichero.readInt());
                //lectura atributo votos
                album.setVotos(fichero.readInt());
                //lectura atributo Año Edición
                album.setAñoEdicion(fichero.readInt());
                //lectura campo atributo título
                album.setTitulo(lecturaString(64));
                //lectura campo atributo Artista
                album.setArtista(lecturaString(32));
                //lectura tipo enumerado de álbum
                tipo=lecturaString(32);
                album.setTiposAlbum(TiposAlbum.deString(tipo));
                //lectura del número de estrellas que tiene
                album.setEstrellas(fichero.readDouble());
                //lectura atributo Ediciones
                album.setEdiciones(fichero.readInt());
            }
            else {
                //invocación excepcion no predefinida entradaIncorrecta
                throw new InsercionDatosExcepcion("POSICIÓN NO EXISTENTE");
            }
        }catch (InsercionDatosExcepcion error) {
            System.out.println(error.toString());
        }catch (IOException error) {
            System.out.println("ERROR: "+error.toString());
        }
        //devolver objeto album
        return album;
    }
    
    //método escritura que posibilita la escritura de un objeto Album en el
    //fichero
    public  void escritura(Album album, boolean escribeAlFinal) {
        try {
            
            //verificar si se debe escribir al final del fichero
            if(escribeAlFinal) {
                //posicionamiento del puntero al final del fichero de objetos Album
                fichero.seek(fichero.length());
            }
            //escritura en el fichero del atributo posición correspondiente al
            //objeto Album 
            fichero.writeInt(album.getPosicion());
            //escritura del número de votos en el fichero aleatorio
            fichero.writeInt(album.getVotos());
            //escritura del año de edición en el fichero aleatorio
            fichero.writeInt(album.getAñoEdicion());
            //escritura de los carácteres del atributo título en el fichero
            escrituraString(album.getTitulo(),64);
            //escritura de los carácteres del atributo artista en el fichero
            escrituraString(album.getArtista(),32);
            //escritura del tipo enumerado de álbum
            escrituraString(album.getTiposAlbum(),32);
            //escritura del número de estrellas que contiene
            fichero.writeDouble(album.getEstrellas());
            //escritura en el fichero del número de ediciones realizadas
            fichero.writeInt(album.getEdiciones());
        }catch (IOException error) {
            System.out.println("ERROR: "+error.toString());
        }
    }
    
    //método que lleva a cabo la escritura de un String en el fichero a través 
    //de la escritura de sus caracteres y en función de la dimensión que ocupa
    private void escrituraString (String campo, int dimension) throws IOException {
        try {
            //bucle de escritura en el fichero, caracter a caracter, del String
            //y en función de la dimensión dada
            for (int i=0;((i<dimension) && (i<campo.length())); i++) {
                //escritura en el fichero del caracter i-ésimo del String
                fichero.writeChar(campo.charAt(i));
            }
            //verificar si la dimensión del String dado es menor que la dimensión
            //del atributo que debe representar del objeto Contacto
            if (campo.length()<dimension) {
                //al ser la dimnensión del String menor que la dimensión del atributo
                //que representa del objeto Contacto se escribirán caracteres
                //espacio hasta llegar a la dimensión del atributo
                for (int i=0; i<(dimension-campo.length()); i++) {
                    //escritura en el fichero del caracter espacio
                    fichero.writeChar(' ');
                }
            }            
        }catch (IOException error) {
            System.out.println("ERROR: "+error.toString());            
        }
    }
    
    
    //método que posibilita la reescritura en el fichero de un objeto Album
    //en función de la posicion que ocupa en el fichero
    public void escritura(Album album,int posicion,boolean mostrarResultado) {
        
        try {
            //verificación si la posición del album a leer existe en el fichero
            if ((posicion>0)&&(posicion<=(fichero.length()/Album.getDimension()))) {
                //posicionamiento del puntero en el elemento de fichero objeto Album
                //correspondiente al número dado por parámetro
                fichero.seek((posicion-1)*Album.getDimension());
                //llamada al método escritura para escribir el objeto en el fichero
                escritura(album,false);
                //verificar si queremos que se muestre el resultado por pantalla
                if(mostrarResultado) {
                    System.out.println(album.toStringFicheroAleatorioVotoManual());
                }
            }
            else {
                //invocación excepcion no predefinida entradaIncorrecta
                throw new InsercionDatosExcepcion("NO ES POSIBLE ESCRIBIR EL CONTACTO DADO");                 
            }
        }catch (InsercionDatosExcepcion error) {
            System.out.println(error.toString());
        }catch (IOException error) {
            System.out.println("ERROR: "+error.toString());
        }
    }
    
    
    //cierre del enlace con el fichero
    public void cierre() {
        try {
            fichero.close();            
        }catch (IOException error) {
            System.out.println("ERROR: "+error.toString());
        }
    }

}
