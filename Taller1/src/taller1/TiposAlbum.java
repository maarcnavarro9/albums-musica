/*
Autor: Marc Navarro Amengual
TIPO ENUMERADO TiposAlbum
 */
package taller1;

public enum TiposAlbum {
    
    ALBUM("Album"), ALBUMSOUNDTRACK("Album + Soundtrack"),
    ALBUMCOMPILATION("Album + Compilation"), ALBUMLIVE("Album + Live");
    
    private String stringEnumerado;
    
    //método Set
    private TiposAlbum(String string){
        this.stringEnumerado=string;
    }
    
    //método Get
    public String getString(){
        return stringEnumerado;
    }
    
    //metodo deString verifica de que tipo enumerado se trata
    public static TiposAlbum deString(String string){
        for (int i=0;i<4;i++){
            if(TiposAlbum.equivalente(string, TiposAlbum.values()[i])){
                return TiposAlbum.values()[i];
            }
        }
        return null;
    }
    
    //método equivalente devuelve un booleano para saber si un string se 
    //corresponde con un tipo enumerado teniendo en cuenta el tamaño total que
    //ocupa un tipo enumerado en el ficheroAleatorio
    private static boolean equivalente(String string, TiposAlbum tipo){
        String tipoString=tipo.getString();
        int i;
        for ( i=0;i<tipoString.length();i++){
            if (string.charAt(i)!=tipoString.charAt(i)){
                return false;
            }
        }
        for (int j=i;j<string.length();j++){
            if (string.charAt(j)!=' '){
                return false;
            }
        }
        return true;
    }
}