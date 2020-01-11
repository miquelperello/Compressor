package Domini;
import Persistencia.ControladorPersistencia;
import Persistencia.JPEGpersistencia;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import static Domini.JPEGDomini.CodificarTaules;
import static Persistencia.JPEGpersistencia.EscriuJPEG;
import static Persistencia.JPEGpersistencia.LlegeixDescomprimit;


/**
 * Classe CompressFile
 * @author Ramon Mateo
 * Aquesta Classe Implementa l'algorisme de compressio de fitxers
 */

public class compressFiles {


	private static ControladorDomini dc ;

	/**
	 * Constructor de compressFiles
	 */

	public compressFiles() {
		dc = new ControladorDomini();
	}

	/**
	 * compress
	 * @param path Path d'entrada del fitxer/directori a comprimir
	 * @param path_out Path de sortida del fitxer/directori a comprimir
	 * @param qualitat Indica la qualitat per la compressio d'imatges si el directori o el path ho son.
	 * @return Retorna el tipus de compressio efectuada.
	 */

	public void compress(String path , String path_out , int qualitat) {
		if(dc.isDirectory(path)) {
			compressFolder(path, path_out , qualitat);
		}

		else {

			try {

				compressFile(path, path_out , qualitat);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}



	}

	/**
	 * uncompress
	 * @param path Path d'entrada del fitxer/directori a descomprimir
	 * @param path_out Path de sortida del fitxer/directori a descomprimir
	 * @return Retorna el tipus de descompressio efectuada.
	 */

	public int uncompress(String path , String path_out ) throws IOException {
		if(dc.isDirectory(path)) {
			uncompressFolder(path, path_out );
		}

		else {

			uncompressFile(path, path_out );
		}

		return 0;
	}

	/**
	 * CompressFolder
	 * @param path Path del directori sobre el que es vol comprimir
	 * @param path_out Path del directori on es desa la compressio del directori efectuat
	 * @param qualitat Indica la qualitat per la compressio d'imatges si el directori o el path ho son.
	 * @return retorna el tipus de compressio efecuat.
	 */

	private int compressFolder(String path, String path_out , int qualitat) {
		String nameFirstFolder =  dc.getNameFile(path);
		nameFirstFolder += ".prop";
		path_out += "//" + nameFirstFolder;
		dc.createFolder(path_out);
		return recursiveCompressFolder(path , path_out , qualitat);
	}

	/**
	 * recursiveCompressFolder
	 * @param path Path del directori sobre el que es vol comprimir
	 * @param path_out Path del directori on es desa la compressio del directori efectuat
	 * @param qualitat Indica la qualitat per la compressio d'imatges si el directori o el path ho son.
	 * @return retorna el tipus de compressio efecuat.
	 */

	private int recursiveCompressFolder(String path , String path_out , int qualitat) {
		ArrayList<String> files = dc.getListOfFiles(path);
		for(int i = 0; i < files.size(); ++i) {
			if(dc.isDirectory(files.get(i))) {
				String auxs = path_out + "//" + dc.getNameFile(files.get(i));
				dc.createFolder(auxs);
				recursiveCompressFolder(files.get(i), auxs , qualitat);
			}
			else {
				try {
					compressFile(files.get(i), path_out , qualitat);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return 0;
	}

	/**
	 * CompressFile
	 * @param path Path d'entrada del fitxer a comprimir
	 * @param path_out Path de sortida on es vol desa el fitxer comprimit.
	 * @param calitat Qualitat de compressio de Jpeg
	 * @return Retorna el tipus de compressio
	 * @throws IOException
	 */

	private void compressFile(String path , String path_out  , int calitat) throws IOException {
		int extension = dc.getExtension(path);
		String name = dc.getNameFile(path);
		switch(extension) {

			// comprimir JPEG

			case 1 :
				name = name.substring(0 , name.lastIndexOf('.'));
				path_out = path_out + "//" + name +".ppm";
				ArrayList<ArrayList<int[][]>> ArrayGeneral = new ArrayList<ArrayList<int[][] >>();
				ArrayGeneral = ControladorDomini.getArrayListJPEG(path); //tenemos todo los arrays en arraygeneral ^^
				ArrayList<ArrayList<ArrayList<Integer>>> AlgorismeJPEG  = JPEGDomini.CodificarJpeg( JPEGpersistencia.getAltura(path), JPEGpersistencia.getAnchura(path), ArrayGeneral.get(0), ArrayGeneral.get(1), ArrayGeneral.get(2), calitat, path_out);
				ArrayList<Integer> ArrayFinal = CodificarTaules(AlgorismeJPEG.get(0), AlgorismeJPEG.get(1), AlgorismeJPEG.get(2),calitat);
				JPEGpersistencia.EscriuJPEG(ArrayFinal, path_out);
				break;

			// comprimir LZSS

			case 2 :
				name = name.substring(0 , name.lastIndexOf('.'));
				path_out = path_out + "//" + name + ".lzss";
				ArrayList<Byte> b = dc.getArrayListLzss(path);
				lzss lz = new lzss();
				b = lz.compress(b);
				dc.saveFileLZSS(path_out, b);
				break;


			default :
				break;
		}

	}

	/**
	 * UncompressFolder
	 * @param path Path del directori sobre el que es vol descomprimir
	 * @param path_out Path del directori on es desa la descompressio del directori  o fitxer efectuat
	 * @return retorna el tipus de compressio efecuat.
	 */
	private int uncompressFolder(String path , String path_out ) throws IOException {
		String nameFirstFolder =  dc.getNameFile(path);
		nameFirstFolder = nameFirstFolder.substring(0, nameFirstFolder.lastIndexOf('.'));
		path_out += "//" + nameFirstFolder + "_uncompress";
		dc.createFolder(path_out);
		return recursiveUncompressFolder(path , path_out);
	}

	/**
	 * recursiveUncompressFolder
	 * @param path Path del directori sobre el que es vol descomprimir
	 * @param path_out Path del directori on es desa la descompressio del directori efectuat
	 * @return retorna el tipus de compressio efecuat.
	 */

	private int recursiveUncompressFolder(String path, String path_out) throws IOException {
		ArrayList<String> files = dc.getListOfFiles(path);
		for(int i = 0; i < files.size(); ++i) {
			if(dc.isDirectory(files.get(i))) {
				String auxs = path_out + "//" + dc.getNameFile(files.get(i));
				dc.createFolder(auxs);
				recursiveUncompressFolder(files.get(i), auxs );
			}
			else {
				uncompressFile(files.get(i), path_out );
			}
		}
		return 0;
	}

	/**
	 * UncompressFile
	 * @param path Path d'entrada del fitxer a descomprimir
	 * @param path_out Path de sortida on es vol desa el fitxer descomprimit.
	 * @return Retorna el tipus de descompressio
	 * @throws IOException
	 */

	private void uncompressFile(String path , String path_out ) throws IOException {
		int extension = dc.getExtension(path);
		String name = dc.getNameFile(path);
		switch(extension) {

			// comprimir JPEG

			case 1 :
				name = name.substring(0 , name.lastIndexOf('.'));
				path_out = path_out + "//" + name +".ppm";
				ArrayList<String> arrayDeStrings= ControladorPersistencia.LlegeixDescomprimit(path, path_out);
				ArrayList<ArrayList<ArrayList<Integer>>> arraymat = ControladorDomini.jpegdescomprimir(arrayDeStrings);
				ArrayList<ArrayList<Integer>> arra1 = arraymat.get(3);
				ArrayList<Integer> arraycalitat = arra1.get(0);
				int calitat = arraycalitat.get(0);
				ArrayList<ArrayList<Integer>> ArrayFinal= ControladorDomini.Descomprimeix(arraymat.get(0), arraymat.get(1), arraymat.get(2),calitat);
				JPEGpersistencia.EscriuDescomprimitJPEG(ArrayFinal, path_out);
				break;



			// descomprimir LZSS

			case 4 :

				try {
					ArrayList<Byte> b;
					name = name.substring(0 , name.lastIndexOf('.'));
					path_out = path_out + "//" + name + ".txt";
					b = dc.getArrayListLzss(path);
					lzss lz = new lzss();
					b = lz.uncompress(b);
					dc.saveFileLZSS(path_out, b);
				} catch (IOException e) {
					e.printStackTrace();
				}



				break;




			default :
				break;
		}
	}



}

