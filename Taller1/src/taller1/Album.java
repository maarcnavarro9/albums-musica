/*
Autor: Marc Navarro Amengual
CLASE Album aglutina los atributos y funcionalidades necesarias para gestionar
objetos Album.
 */
package taller1;

public class Album {
    //DECLARACIÓN ATRIBUTOS
    //declaración atributo de clase constante entero que representa la dimensión
    //en bytes de un objeto Album
    // posicion -> 4 bytes
    // votos -> 4 bytes
    // añoEdicion -> 4 bytes
    // titulo -> 64 caracteres * 2 bytes = 128 bytes
    // artista -> 32 caracteres * 2 bytes = 64 bytes
    // tipo -> 32 caracteres * 2 bytes = 64 bytes
    // estrellas -> 8 bytes
    // ediciones -> 4 bytes
    
    //declaración constante para representar la dimensión total del objeto
    //el fichero aleatorio a escribir en el fichero aleatorio
    private static final int DIMENSION=280;
    //declaración atributos de un objeto Album
    private int posicion, añoEdicion, ediciones, votos;
    private double estrellas;
    private String titulo, artista;
    //declaración tipo enumerado
    private TiposAlbum tipo;
    
    //MÉTODOS
    
    //MÉTODO CONSTRUCTOR sin parámetros
    public Album() {
        votos=0;
    }
    
    //MÉTODO CONSTRUCTOR con parámetros
    public Album(int dato1, int dato2, String dato3, String dato4, TiposAlbum dato5,
            double dato6, int dato7) {
        votos=0;
        posicion=dato1;
        añoEdicion=dato2;
        titulo=dato3;
        artista=dato4;
        tipo=dato5;
        estrellas=dato6;
        ediciones=dato7;   
    }
    
    //MÉTODOS FUNCIONALES
    
    //método toString que convierte a String un objeto Album
    @Override   
    public String toString() {       
        return "Album{num=" + posicion + "," +
               " año=" + añoEdicion + "," +
               " impres=" + ediciones + "," +
               " título=" + titulo + "," +
               " artista=" + artista + "," +
               " tipo=" + tipo.getString() + "," +
               " estrellas=" + estrellas + "}";
    }
    
    //método que convierte a String un objeto Album contenido en el fichero
    //Aleatorio
    public String toStringFicheroAleatorio() {       
        return "# " + posicion + 
                "\tvotos: " + votos +" "+
                "\t" + titulo + 
                "Artista: " + artista +
                "Año: " + añoEdicion;        
    }
    
    //método que convierte a String algunos atributos de un objeto Album 
    //contenido en el fichero Aleatorio
    public String toStringFicheroAleatorioVotoManual() {       
        return "#" + posicion + 
                " votos: " + votos  
                + " por " + titulo 
                + " de " + artista;
    }
    
    //método que convierte a String el objeto Album que contiene la puntuación
    //más alta
    public String toStringMejorPuntuacion() {       
        return "Album{num=" + posicion + "," +
               " año=" + añoEdicion + "," +
               " impres=" + ediciones + "," +
               " votos=" + votos + "," +
               " título=" + titulo + "," +
               " artista=" + artista + "," +
               " tipo=" + tipo + "," +
               " estrellas=" + estrellas + "}";      
    }
    
        
    //método AddVotos sirve como acumulador para llevar la cuenta del número
    //total de votos asignados al objeto Album
    public void AddVotos(int dato) {
        votos=dato+votos;
    }
    
    //MÉTODOS GETS
    
    public int getPosicion() {
        return posicion;
    }
    public int getAñoEdicion() {
        return añoEdicion;
    }
    public String getTitulo() {
        return titulo;
    }
    public String getArtista() {
        return artista;
    } 
    public String getTiposAlbum() {
        return tipo.getString();
    }
    public double getEstrellas() {
        return estrellas;
    }
    public int getEdiciones() {
        return ediciones;
    }
    public int getVotos() {
        return votos;
    }
    public static int getDimension() {
        return DIMENSION;
    }
    
    //MÉTODOS SETS
    
    public void setPosicion(int dato) {
        posicion=dato;
    }
    public void setAñoEdicion(int dato){
        añoEdicion=dato;
    }
    public void setTitulo(String dato) {
        titulo=dato;
    }
    public void setArtista(String dato) {
        artista=dato;
    } 
    public void setTiposAlbum(TiposAlbum dato) {
        tipo=dato;
    }
    public void setEstrellas(double dato) {
        estrellas=dato;
    }
    public void setEdiciones(int dato) {
        ediciones=dato;
    }
    public void setVotos(int dato) {
        votos=dato;
    }
}
