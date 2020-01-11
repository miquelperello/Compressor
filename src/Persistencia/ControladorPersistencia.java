package Persistencia;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Controlador de Persistencia
 * @author Ramon Mateo & Miquel Perello
 * Implementacio del controlador de Persistencia
 */


public class ControladorPersistencia {

    private path_dirs p;

    /**
     * Constructora de la classe ControladorPersistencia
     */
    
    public ControladorPersistencia() {
        p = new path_dirs();
    }
    
    /**
     * Get Size Output
     * @param path Obtensio del size del path
     * @return Retorna el size del fitxer que es localitza en el path indicat
     */

    public static int getsizeoutput(String path) {
        File files = new File(path);
        return (int) files.length();
    }



    /**
     * @brief Funcio auxiliar que Llegeix la imatge .ppm especificada al path
     * @param path Path on es troba el fitxer
     * @return retorna un array de byte[]
     */

    public static byte[] LlegeixEscriuArxiu(String path) throws IOException {
        return path_dirs.LlegeixEscriuArxiu(path);

    }


    /**
     * @brief Funcio que crida a JPEGpersistencia per començar la descomprensio donat un path d'entrada i un de sortida
     * @param path Path de l'arxiu d'entrada
     * @param path_out Path de l'arxiu de sortida
     * @return retorna un arraylist dels codis huffmans llegits a l'arxiu introduït amb path
     * @throws IOException
     */
    public static ArrayList<String> LlegeixDescomprimit(String path, String path_out) throws IOException {
        return JPEGpersistencia.LlegeixDescomprimit(path);
    }

    /**
     * @brief Comprovacio de directori
     * @param path Path sobre el que es desitja revisar si es tracta d'un directori o fitxer
     * @return Retorna True si es directori, altrament false
     */

    public  Boolean isDirectory(String path) {
        return p.isDirectory(path);
    }

    public static boolean staticisdirectory(String path) {
        return path_dirs.staticisDirectory(path);
    }

    /**
     * @brief Funcio que retorna el tamany de la carpeta del path
     * @param path path del folder
     * @return retorna el tamany
     */
    public static long getFolderSize(String path){
        return path_dirs.getFolderSize(path);
    }

    /**
     * @brief Get Name File static
     * @param path Path sobre el que es desitja obtenir el nom
     * @return retorna el nom del fitxer o directori.
     */

    public static String getNameFilestatic(String path) {
        return path_dirs.getNameFilestatic(path);
    }

    /**
     * @brief Creacio de Directori
     * @param path Path sobre el que es desitja crear el directori
     * @return Retorna True si s'ha pogut crear correctament el directori, altrament false.
     */

    public Boolean createDirectory(String path) {
        return p.createDirectory(path);
    }
    
    /**
     * @brief Llistat de fitxers
     * @param path Path sobre el que es desitja obtenir tot el llistat de fitxers que conté
     * @return Retorna ArraList de tipus String on cada String es el path d'un fitxer/Directori 
     */

    public ArrayList<String> getListOfFiles(String path) {
        return p.getlistOfFiles(path);
    }
    
    /**
     * @brief Get Name File
     * @param path Path sobre el que es desitja obtenir el nom del fitxer
     * @return Retorna String amb el nom del fitxer sense el seu path. 
     */

    public String getNameFile(String path) {
        return p.getNameFile(path);
    }
    
    /**
     * @brief Obtencio d'extencio
     * @param path Path sobre el que es desitja saber la seva extensio
     * @return Retorna l'extensio del path d'entrada
     */
    
    public int getExtension(String path) {
		return p.getExtension(path);
	}
    
    /**
     * @brief Obtencio d'ArrayList
     * @param path Path sobre el que es desitja llegir
     * @return Retorna ArrayList de tipus Bytes que conté tot el contingut del fitxer indicat en el paràmetra d'entrada. 
     * @throws IOException
     */
	
	public ArrayList<Byte> getArrayListLzss(String path) throws IOException {
		LZSSPersistencia p = new LZSSPersistencia();
		return p.getArrayListLzss(path);
	}
    /**
     * @brief Obtencio d'ArrayList
     * @param path Path sobre el que es desitja llegir
     * @return Retorna ArrayList d'ArrayList d'integers que conté tot el contingut del fitxer indicat en el paràmetra d'entrada.
     * @throws IOException
     */
	public static ArrayList<ArrayList<int[][]>> getArrayListJPEG(String path) throws IOException {

        return JPEGpersistencia.LlegirFile(path);
    }
	
	/**
	 * @brief Save File Lzss
	 * @param path_out Path sobre el que es desitja Desar el paràmetra d'entrada ' S ' 
	 * @param s ArrayList de tipus Bytes que es desitja guardar en el paràmetra d'entrada path_out
	 * @throws IOException 
	 */
	
	public void saveFileLZSS (String path_out , ArrayList<Byte> s) throws IOException {
		LZSSPersistencia p = new LZSSPersistencia();
		p.saveFileLZSS(path_out, s);
	}


}
