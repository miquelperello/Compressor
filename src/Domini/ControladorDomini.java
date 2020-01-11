package Domini;
import java.io.IOException;
import java.util.*;

import Persistencia.ControladorPersistencia;
import Persistencia.path_dirs;
import Presentacio.ControladorPresentacio;

import javax.naming.ldap.Control;

/**
 * Controlador de Domini
 * @author Ramon Mateo & Miquel Perello
 * Implementacio del controlador de la capa Domini
 */

public class ControladorDomini {


    private ControladorPersistencia p;

    /**
     * Constructor de la classe ControladorDomini
     */
	public ControladorDomini () {

		p = new ControladorPersistencia();
	}
	
	/**
	 * @brief Comprimir fitxers
	 * @param path Path d'entrada
	 * @param path_out Path de sortida 
	 * @param calitat Qualitat si el fitxer es una imatge
	 */

    public static void comprimir(String path, String path_out, int calitat) {
    	compressFiles c = new compressFiles();
    	c.compress(path, path_out, calitat);
    }
    
    /**
     * @brief Descomprimir fitxers
     * @param path Path d'entrada
     * @param path_out Path de sortida
     */
    
    public static void descomprimir(String path, String path_out) throws IOException {
    	compressFiles c = new compressFiles();
    	c.uncompress(path, path_out);
    }
    
    /**
     * @brief Get Size Output
     * @param path Path sobre el que es vol saber el size
     * @return Retorna el size del fitxer de sortida
     */

    public static int getsizeoutput(String path) {
        int Temps = ControladorPersistencia.getsizeoutput(path);
        return Temps;
    }

	/**
	 * @brief Funcio per detectar si el path es un directori
	 * @param path path d'entrada
	 * @return retorna boolea; 1 sí, 0 no ho es
	 */
	public static boolean staticisDirectory(String path){
		return ControladorPersistencia.staticisdirectory(path);
	}


	/**
	 * @brief Funcio que retorna el tamany de la carpeta del path
	 * @param path path del folder
	 * @return retorna el tamany
	 */
	public static long getFolderSize(String path){
		return ControladorPersistencia.getFolderSize(path);
	}

	/**
	 * @brief Funcio de Domini per començar a descomprimir.
	 * @param arrayLists ArrayList component R
	 * @param arrayLists1 ArrayList component G
	 * @param arrayLists2 ArrayList component B
	 * @param calitat Integer que ens diu la calitat dintre d'un interval 1-100
	 * @return retorna un ArrayList d'ArrayLists
	 * @throws IOException
	 */
	public static ArrayList<ArrayList<Integer>> Descomprimeix(ArrayList<ArrayList<Integer>> arrayLists, ArrayList<ArrayList<Integer>> arrayLists1, ArrayList<ArrayList<Integer>> arrayLists2, Integer calitat) throws IOException {
    	return JPEGDomini.Descomprimeix(arrayLists, arrayLists1, arrayLists2, calitat);
	}

	/**
	 * @brief Funcio de Domini que descomprimeix
	 * @param arrayDeStrings : ArrayList de Strings (Codis Huffman llegits)
	 * @return Retorna un array d'arraylist d'arraylist d'integers.
	 */
	public static ArrayList<ArrayList<ArrayList<Integer>>>  jpegdescomprimir(ArrayList<String> arrayDeStrings) {
    	return JPEGDomini.descomprimir(arrayDeStrings);
	}

	/**
	 * @brief Funcio que invoca a persistencia per creacio de carpeta
	 * @param path Path on es vol crear la carpeta
	 * @return retorna True si s'ha pogut crear la carpeta
	 */
	public Boolean createFolder(String path) {
		return p.createDirectory(path);
	}
	
	/**
	 * @brief isDirectory
	 * @param path Path sobre el que es desitja comprovar si es tracta d'un directori
	 * @return Retorna True si el path es un directori. Altrament False
	 */
	
	public Boolean isDirectory(String path) {
		return p.isDirectory(path);
	}
	
	/**
	 * @brief getListOfFiles
	 * @param path Path sobre el que es desitja obtenir el llistat de fitxers
	 * @return Retorna un array de strings on cada string es un path
	 */
	
	public ArrayList<String> getListOfFiles(String path){
		return p.getListOfFiles(path);
	}
	
	/**
	 * @brief Get Name File
	 * @param path Path sobre el que es desitja obtenir el nom
	 * @return retorna el nom del fitxer o directori. 
	 */
	
	public String getNameFile(String path) {
		return p.getNameFile(path);
	}

	/**
	 * @brief Get Name File static
	 * @param path Path sobre el que es desitja obtenir el nom
	 * @return retorna el nom del fitxer o directori.
	 */

	public static String getNameFilestatic(String path) {
		return ControladorPersistencia.getNameFilestatic(path);
	}
	
	/**
	 * @brief Get Extension
	 * @param path Path sobre el que es desitja obtenir la extensio
	 * @return Retorna l'extensio del fitxer
	 */
	
	public int getExtension(String path) {
		return p.getExtension(path);
	}
	
	/**
	 * @brief getArrayListLzss
	 * @param path Path del fitxer a llegir
	 * @return Retorna ArrayList de bytes amb tots els caracters del fitxer de text
	 * @throws IOException
	 */
	
	public ArrayList<Byte> getArrayListLzss(String path) throws IOException {
		return p.getArrayListLzss(path);
	}
	/**
	 * @brief getArrayListJPEG
	 * @param path Path del fitxer a llegir
	 * @return Retorna ArrayList de bytes amb tots els caracters del fitxer de text
	 * @throws IOException
	 */

	public static ArrayList<ArrayList<int[][]>> getArrayListJPEG(String path) throws IOException {
		return ControladorPersistencia.getArrayListJPEG(path);
	}



	/**
	 * @brief saveFileLZSS
	 * @param path_out Path on es guardara el ArrayList obtingut de la compressio/descompressio de l'algorisme LZSS
	 * @param s ArrayList obtingut de la compressio/descompressio que conte tots els caracters codificats/decodificats per ser escrits en el fitxer indicat en el path
	 * @throws IOException
	 */
	
	public void saveFileLZSS(String path_out , ArrayList<Byte> s) throws IOException {
		p.saveFileLZSS(path_out, s);
	}

	/**
	 * @brief Funcio auxiliar que Llegeix la imatge .ppm especificada al path
	 * @param path Path on es troba el fitxer
	 * @return retorna un array de byte[]
	 */
	
	public static byte[] LlegeixEscriuArxiu(String path) throws IOException {
        return ControladorPersistencia.LlegeixEscriuArxiu(path);
    }
	


}
