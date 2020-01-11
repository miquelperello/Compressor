package Persistencia;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;


/**
 * @brief Clase de Paths i Directoris
 * @author Ramon Mateo & Miquel Perelló
 * Clase on s'han definit les funcions per solicitar creacio de directoris , paths.
 */

public class path_dirs {



    /**
     * @brief Comprovacio si un path es directori o fitxer
     * @param path path sobre el que volem comprovar si es directori o fitxer
     * @return Retorna true si es directori, altrament false
     */
    public Boolean isDirectory(String path) {
        File aux = new File(path);
        if(aux.isDirectory()) return true;
        else return false;
    }
    /**
     * @brief Comprovacio si un path es directori o fitxer de manera estàtica
     * @param path path sobre el que volem comprovar si es directori o fitxer
     * @return Retorna true si es directori, altrament false
     */
    public static Boolean staticisDirectory(String path) {
        File aux = new File(path);
        if(aux.isDirectory()) return true;
        else return false;
    }
    /**
     * @brief GetFolderSize
     * @param path path de la carpeta
     * @return retorna tamany d'una carpeta
     */

    public static long getFolderSize(String path) {

        File folder = new File(path);
        return folderSize(folder);
    }



    public static long folderSize(File directory) {
        long length = 0;
        for (File file : directory.listFiles()) {
            if (file.isFile())
                length += file.length();
            else
                length += folderSize(file);
        }
        return length;
    }


    /**
     * @brief Creacio de directori
     * @param path_abs Path absolut on volem crear el directori
     * @return Retorna true si s'ha pogut crear el directori
     */

    public Boolean createDirectory(String path_abs) {

        File aux = new File(path_abs);
        return aux.mkdir();

    }

    /**
     * @brief Obtencio de llistat de fitxers dins d'un mateix directori
     * @param path Path del directori on es volen obtenir el nom dels fitxers i altres directoris
     * @return Retorna ArrayList amb el path de tots els fitxers i directoris trobats.
     */

    public ArrayList<String> getlistOfFiles(String path) {
        File aux = new File(path);
        File [] aux2 = aux.listFiles();
        ArrayList<String> tmp = new ArrayList<String>();
        for(int i = 0; i < aux2.length; ++i) {
            tmp.add(aux2[i].getAbsolutePath());
        }
        return tmp;
    }

    /**
     * @brief Obtencio del nom del fitxer
     * @param path Path del fitxer sobre el que es vol saber el nom
     * @return Retorna el nom del fitxer o directori
     */

    public String getNameFile(String path) {
        File aux = new File(path);
        return aux.getName();
    }

    public static String getNameFilestatic(String path) {
        File aux = new File(path);
        return aux.getName();
    }

    /**
     * @brief Funcio que llegeix l'arxiu del path, el guarda en un BufferedReader i el converteix a byte per poder retornar-lo
     * @ruta path de l'arxiu que volem llegir
     * @return Retorno un array de bytes
     * @throws IOException
     */

    public static byte[] LlegeixEscriuArxiu(String ruta) throws IOException {
        int numOfRows = 0, numOfCols = 0;
        File f = new File(ruta);
        FileInputStream fileReader = new FileInputStream(f);
        BufferedInputStream bufferedReader = new BufferedInputStream(fileReader);
        String magicnum = "";
        while (true) {
            int b = bufferedReader.read();
            if (b != -1) {
                char c = (char) b;
                if (!Character.isWhitespace(c)) {
                    magicnum += c;
                } else {
                    break;
                }
            } else {
                break;
            }
        }
        char tipus1 =0;
        String altu="";
        String anchu="";
        while((tipus1 =(char)bufferedReader.read())!= ' ') {
            altu+=Character.toString(tipus1);
        }
        altu = altu.trim();
        while((tipus1 =(char)bufferedReader.read())!= '\n') {
            anchu+=Character.toString(tipus1);
        }
        anchu = anchu.trim();

        numOfRows = Integer.valueOf(altu);
        numOfCols = Integer.valueOf(anchu);
        bufferedReader.read();
        BufferedImage imatge = new BufferedImage(numOfRows, numOfCols, BufferedImage.TYPE_INT_RGB);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] imageInByte = null;
        if (magicnum.equals("P6")) {

            for (int y = 0; y < numOfCols; y++) {
                for (int x = 0; x < numOfRows; x++) {
                    int[] RGB = new int[3];
                    RGB[0] = bufferedReader.read();
                    RGB[1] = bufferedReader.read();
                    RGB[2] = bufferedReader.read();
                    Color n = new Color(RGB[0], RGB[1], RGB[2],1);
                    imatge.setRGB(x, y, n.getRGB());
                }
            }
            ImageIO.write(imatge, "bmp", baos);
            baos.flush();
            imageInByte = baos.toByteArray();
        }
        bufferedReader.close();
        return imageInByte;
    }



    /**
     * @brief Obtencio d'extensio
     * @param path Path sobre el que es desitja obtenir l'extensio
     * @return Retorna el tipus d'extensio : 1 - ppm ; 2 - txt ; 3 - JPEG ; 4 - LZSS altrament -1
     */
    
    public int getExtension(String path) {
		String extension = path.substring(path.lastIndexOf('.') + 1);
		switch(extension) {
			
		    case "ppm" :
		    	return 1;
		    	
		    case "txt" :
		    	return 2;
		
			case "jpeg":
				return 3;
				
			case "lzss":
				return 4;
				
			default :
				return -1;
		}
	}


}
