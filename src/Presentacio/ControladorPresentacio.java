package Presentacio;

import Domini.ControladorDomini;
import Persistencia.ControladorPersistencia;

import java.io.IOException;

/**
 * Clase ControladorPresentacion
 * @author Miquel Perelló
 */
public class ControladorPresentacio {


    private ControladorDomini p;

    public ControladorPresentacio () {
         p = new ControladorDomini();
    }

    /**
     * @brief Funcion principal que llama a comprimir  mediante controlador de dominio
     * @param path
     * @param path_out
     * @param calitat
     */
    public static void comprimir(String path, String path_out, int calitat) {
    	ControladorDomini.comprimir(path, path_out, calitat);
    }

    /**
     *@brief Funcion principal que llama a descomprimir mediante controlador de dominio
     * @param path
     * @param path_out
     * @throws IOException
     */
    public static void descomprimir(String path, String path_out) throws IOException {

        ControladorDomini.descomprimir(path, path_out);
    }

    /**
     * @brief Funcio que retorna el size del path
     * @param path
     * @return Retorna el size del fitxer
     */
    public static int getsize(String path) {
        int Temps = ControladorDomini.getsizeoutput(path);
        return Temps;
    }

    /**
     * @brief Funcio per detectar si el path es un directori
     * @param path path d'entrada
     * @return retorna boolea; 1 sí, 0 no ho es
     */
    public static boolean staticisDirectory(String path){
        return ControladorDomini.staticisDirectory(path);
    }

    /**
     * @brief Funcio que retorna el tamany de la carpeta del path
     * @param path path del folder
     * @return retorna el tamany
     */
    public static long getFolderSize(String path){
        return ControladorDomini.getFolderSize(path);
    }

    /**
     * @brief Funcio auxiliar que Llegeix la imatge .ppm especificada al path
     * @param path Path on es troba el fitxer
     * @return retorna un array de byte[]
     */
    public static byte[] LlegeixEscriuArxiu(String path){
        try {
           return ControladorDomini.LlegeixEscriuArxiu(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }


    public static String getNameFile(String path) {
        return ControladorDomini.getNameFilestatic(path);
    }
}
