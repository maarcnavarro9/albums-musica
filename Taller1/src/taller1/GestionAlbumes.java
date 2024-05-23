/*
MARC NAVARRO AMENGUAL
 */
package taller1;

import java.io.File;

public class GestionAlbumes {
    //DECLARACIONES
    //delaración atributo estático String que represente el nombre del fichero
    private static final String NOMBRE_FICHERO="GreatestAlbums.txt";
    //delaración atributo estático String que represente el nombre del fichero aleatorio
    private static final String NOMBRE_FICHERO_ALEATORIO="FicheroAleatorio";
    //declaración de objeto Album
    Album album = new Album();
    
    //declaración del método main
    public static void main(String [] argumentos) {
        //declaración del método métodoPrincipal
        new GestionAlbumes().metodoPrincipal();
    }
    
    //método metodoPrincipal
    private void metodoPrincipal() {
        //DECLARACIONES
        //declaración variable booleana para controlar el final del programa
        boolean fin=false;
        
        //ACCIONES
        try {
            //BUCLE para controlar la ejecución del programa
            while (!fin) {
                //visualización menu
                menu();
                //lectura opción y gestión opción elegida
                switch (LT.readInt()) {
                    case 0:     //TERMINAR EL PROGRAMA
                                fin=true;
                                break;
                    case 1:     //VER EL CONTENIDO DEL FICHERO DE TEXTO Y CREAR
                                //EL FICHERO DE VOTOS ALEATORIO
                                lecturaFicheroYCreacionAleatorio();
                                pausa();
                                break;
                    case 2:     //VER EL CONTENIDO DEL FICHERO DE VOTOS ALEATORIO
                                System.out.println("\nContenido del fichero de discos:");
                                lecturaFicheroAleatorio();
                                pausa();
                                break;
                    case 3:     //REALIZAR UN VOTO A UN ÁLBUM DE FORMA MANUAL
                                votoManual();
                                pausa();
                                break;
                    case 4:     //REALIZAR VOTOS A VARIOS ÁLBUMES DE FORMA ALEATORIA
                                System.out.println("\nVotación aleatoria:");
                                votoAleatorio();
                                pausa();
                                break;
                    case 5:     //VISUALIZAR EL ÁLBUM CON MÁS PUNTUACIÓN/VOTOS
                                System.out.println("\nEl mejor album es:");
                                System.out.println(mejorPuntuacion());
                                pausa();
                                break;
                    default:    System.out.println("\n\tOPCION INCORRECTA - "
                            + "VUELVA A INTRODUCIR UN VALOR");
                                pausa(); 
                }             
            }
        }catch (Exception error) {
           System.out.println("ERROR: "+error.toString());
        } 
    }  
    
    //MÉTODOS FUNCIONALES
    
    //método lecturaFichero, lleva a cabo la lectura del fichero donde están contenidos
    //los 100 mejores álbumes y al mismo tiempo genera el fichero de votos aleatorio
    public void lecturaFicheroYCreacionAleatorio() {
        //DECLARACIONES
        //declaración e instanciación de objeto FicheroAlbumInOut para crear
        //el fichero de acceso aleatorio
        FicheroAlbumInOut ficheroAleatorio;
        //declaración e instanciación de objeto AlbumFicherosLectura
        AlbumFicherosLectura fichero = new AlbumFicherosLectura(NOMBRE_FICHERO);
        //declaración variable booleana para gestionar la ejecución del método
        boolean continuar=true;
        
        //ACCIONES
        //verificar si el fichero de libros existe
        File f=new File(NOMBRE_FICHERO_ALEATORIO);
        if (f.exists()) {
            //mensaje comunicando que si se prosigue con la creación del fichero
            //su contenido puede provocar problemas en el programa
            System.out.println("FICHERO YA EXISTENTE! SI CONTINUA CON ESTA OPCION"
                    + " EL CONTENIDO DEL FICHERO ALEATORIO SE PERDERA");
            continuar=continuar("<> CONTINUAR (s/n): ");
            //en caso de que el usuario quiera continuar, borrar fichero existente
            //y crear uno nuevo
            if(continuar) {
                f.delete();
            }
        }
        if (continuar) {
            //instanciación objeto FicheroAlbumInOut para establecer
            //el enlace con el fichero físico
            ficheroAleatorio = new FicheroAlbumInOut(NOMBRE_FICHERO_ALEATORIO);
   
            System.out.println("\nContenido del fichero de texto:");
            //lectura objeto album desde el fichero
            album=fichero.lectura();
            //BUCLE DE RECORRIDO
            while(album!=null) {
                //escribimos el objeto album leído en el fichero aleatorio
                ficheroAleatorio.escritura(album,true);
                //visualizar resultado
                System.out.println(album);
                //lectura siguiente objeto album
                album=fichero.lectura();
            }
            //cerramos enlaces
            fichero.cerrarEnlaceFichero(); 
            ficheroAleatorio.cierre();
        }
    }
    
    
    //método lecturaFicheroAleatorio lleva a cabo la lectura y visualización
    //del fichero aleatorio de Albums
    public void lecturaFicheroAleatorio() {
        //DECLARACIONES
        //declaración e instanciación de objeto FicheroAlbumInOut para crear
        //el fichero de acceso aleatorio
        FicheroAlbumInOut ficheroAleatorio = new FicheroAlbumInOut(NOMBRE_FICHERO_ALEATORIO);
        
        //ACCIONES
        //leer primer objeto album
        album=ficheroAleatorio.lectura();
        //BUCLE DE RECORRIDO
        while(album!=null) {
            //VISUALIZACIÓN
            System.out.println(album.toStringFicheroAleatorio());
            //leer siguiente objeto album
            album=ficheroAleatorio.lectura();
        }
        //cerrar enlace con el fichero aleatorio
        ficheroAleatorio.cierre(); 
    }
    
    
    //método votoManual lleva a cabo una votación de forma manual al album deseado
    //contenido en el fichero aleatorio
    public void votoManual() {
        //DECLARACIONES
        //declaración e instanciación de objeto FicheroAlbumInOut para crear
        //el fichero de acceso aleatorio
        FicheroAlbumInOut ficheroAleatorio = new FicheroAlbumInOut(NOMBRE_FICHERO_ALEATORIO);
        //declaración variable para almacenar la posición del album a votar
        int posicion;
        //declaración variable para almacenar la puntuación a dar al objeto album
        int puntuacion;
        
        //ACCIONES
        try {  
            //pedir al usuario la posición del album al que quiere votar
            System.out.print("Numero del disco a votar [1..100]: ");
            posicion=LT.readInt();
            
            //verificar si se trata de un album existente
            if((posicion>=0)&&(posicion<=100)) {
                //leemos el objeto album deseado
                album=ficheroAleatorio.lectura(posicion);  
            }else {
                //invocación excepcion no predefinida entradaIncorrecta
                throw new InsercionDatosExcepcion("POSICION ERRONEA");
            }
            //pedir al usuario la puntuación a dar
            System.out.print("Puntuacion [1..10]: ");
            puntuacion=LT.readInt();
            
            //validar si se trata de una puntuación correccta
            if((puntuacion>=0)&&(puntuacion<=10)) {
                //agregar puntuación al objeto album
                album.AddVotos(puntuacion);
                //llamada al método para sobreescribir sobre el fichero aleatorio
                ficheroAleatorio.escritura(album,posicion,true);
            }else {
                //invocación excepcion no predefinida entradaIncorrecta
                throw new InsercionDatosExcepcion("PUNTUACION ERRONEA");
            }
        }catch(InsercionDatosExcepcion error) {
            System.out.println("\nERROR: " + error.toString());        
        }finally {
            //cerrar el enlace con el fichero
            ficheroAleatorio.cierre();
        }
        
    }
    
    
    //método votoAleatorio lleva a cabo una votación de forma aleatoria
    public void votoAleatorio() {
        //DECLARACIONES
        //declaración e instanciación de objeto FicheroAlbumInOut para crear
        //el fichero de acceso aleatorio
        FicheroAlbumInOut ficheroAleatorio = new FicheroAlbumInOut(NOMBRE_FICHERO_ALEATORIO);
        //declaración variable para almacenar el número de votaciones a realizar
        int votaciones;
        //declaración variable para almacenar la posición del album a votar
        int posicion;
        
        //ACCIONES
        try {
            //ACCIONES
            //pedir al usuario el núemero de álbumes a votar
            System.out.print("Numero de votos a generar [1..100]: ");
            votaciones=LT.readInt();
            
            //verificamos si el número introducido es correcto
            if((votaciones>=0)&&(votaciones<=100)) {
                //bucle para recorrer el número de álbumes a votar
                for(int i=1;i<=votaciones;i++) {
                    //visualización primera votación
                    System.out.print("voto: "+numEspaciosAEscribir(i)+"  ");
  
                    //bucle para votar los álbumes aleatoriamente
                    for(int j=1;j<=10;j++) {
                        //obtener número pseudoaleatorio entre [1,100]
                        posicion=(int)(Math.random()*(1-101)+101);
                        //leemos el objeto de la posición obtenida
                        album=ficheroAleatorio.lectura(posicion);
                        //sumamos sus votos
                        album.AddVotos(j);
                        //reescritura del objeto en su posición pertinente
                        ficheroAleatorio.escritura(album,posicion,false);
                        //VISUALIZACIÓN
                        System.out.print("[#"+numEspaciosAEscribir(posicion)+" - "+j+"] ");
                    }
                    System.out.println();
                }
            }else {
                //invocación excepcion no predefinida entradaIncorrecta
                throw new InsercionDatosExcepcion("NUMERO ERRONEO");
            } 
        }catch (InsercionDatosExcepcion error) {
            System.out.println(error.toString());    
        }finally {
            //cerrar enlace con el fichero aleatorio
            ficheroAleatorio.cierre();
        }
    }
    
    
    //método numEspaciosAEscribir sirve como una alternativa a una tabulación (\t)
    //para visualizar el resultado de forma ordenada
    public String numEspaciosAEscribir(int dato) {
        if(dato<=9) {
            return dato+"  ";
        }else if(dato<=99) {
            return dato+" ";
        }else {
            return dato+"";
        }
        
    }
    
    
    //método mejorPuntuacion devuelve un String con el objeto Album el cual 
    //contenga el mayor número de votos
    public String mejorPuntuacion() {
        //declaración e instanciación de objeto FicheroAlbumInOut para crear
        //el fichero de acceso aleatorio
        FicheroAlbumInOut ficheroAleatorio = new FicheroAlbumInOut(NOMBRE_FICHERO_ALEATORIO);
        //declaración objeto albumLocal para almacenar el álbum con la puntuación
        //más grande
        Album albumLocal=new Album();
        
        //ACCIONES
        album=ficheroAleatorio.lectura();
        //BUCLE DE RECORRIDO
        while(album!=null) {
            //verificar si objeto leído tiene mayor número de votos
            if(album.getVotos()>albumLocal.getVotos()) {
                //en caso afirmativo, actualizar objeto album
                albumLocal=album;
            }
            //leer siguiente album
            album=ficheroAleatorio.lectura();
        }
        //cerrar enlace con el fichero
        ficheroAleatorio.cierre();
        //devolver el string con el objeto album de mayor puntuación
        return albumLocal.toStringMejorPuntuacion();
    }
    
    
    //MÉTODO menu LLEVA A CABO LA VISUALIZACIÓN DEL MENU DE LA APLICACIÓN
    private void menu() {
        //limpiar la pantalla
        limpiarPantalla("Gestion de los discos que se deberian escuchar");
        //visualziación opciones
        System.out.println("1. Ver el contenido del fichero de texto e"
                + " inicializar el fichero de votos");
        System.out.println("2. Ver el contenido del fichero de votos"
                + " de acceso aleatorio");
        System.out.println("3. Un voto manual.");
        System.out.println("4. Simular votos aleatorios");
        System.out.println("5. Calcular el mejor album");
        System.out.println("0. SALIR.");
        System.out.print("\n Insertar opcion: "); 
    }
    
    
    //MÉTODO limpiarPantalla LLEVA A CABO LA LIMPIEZA DE LA PANTALLA DE TEXTO
    private void limpiarPantalla(String mensaje) {
        System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"
                + "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"
                + mensaje+"\n\n");
    }
    
    
    //MÉTODO QUE SOLICITA RESPUESTA PARA CONTINUAR O NO CON LA OPCIÓN ELEGIDA
    private boolean continuar(String mensaje) {
        //DECLARACIONES
        //declaración variable char para obtener la respuesta pro teclado
        char respuesta='n';
       
        //ACCIONES
        try {
            //mensaje para continuar o no
            System.out.print(mensaje); 
            //lectura respuesta
            respuesta=LT.readChar();
            //devolver respuesta           
        }catch (Exception error) {
           System.out.println("ERROR: "+error.toString());
        }
        return ((respuesta=='s') || (respuesta=='S'));
    }
    
    
    //MÉTODO QUE SIMULA UNA PAUSA DE LA EJECUCIÓN ESPERANDO A QUE SE PULSE RETURN
    private void pausa() {
        System.out.print("\n\n\n[ CONTINUAR --> pulsar return ] ");         
        try {  
            LT.readChar();     
        }catch (Exception error) {
            System.out.println("ERROR: "+error.toString());
        }  
    }
}
