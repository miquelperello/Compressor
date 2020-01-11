package main_domain;
import Domini.ControladorDomini;
import java.io.*;
import java.util.*;
import java.util.ArrayList;


/**
 * Clase Main de Domini
 * @author Ramon Mateo
 * Executa el programa sense la capa de presentacio. 
 */


public class main {
	
	long init;
	long end;
	
	
	
	private void stadistics(String path , String path_out , int type) {
		System.out.println("El temps ha sigut de : " + (float)((end-init)/1000) + " segons");
		ControladorDomini c = new ControladorDomini();
		int size_input = ControladorDomini.getsizeoutput(path);
		//no es directori 
		if(!c.isDirectory(path)) {
			//tipus 0 es compressio
			if(type == 0) {
				int extension = c.getExtension(path);
				if(extension == 2) {
					String aux = c.getNameFile(path);
					aux = aux.substring(0, aux.lastIndexOf('.'));
					aux = path_out + "//" + aux + ".lzss";
					int size_output = ControladorDomini.getsizeoutput(aux);
					System.out.println("El size d'entrada ha sigut de : " + size_input);
					System.out.println("El size de sortida ha sigut de : " + size_output);
					System.out.println("El compress ratio ha sigut de : " + (double)size_input/size_output );
				}
				
				else {
					String path_new = path_out + "//" + c.getNameFile(path) + ".ppm";
					int size_output = ControladorDomini.getsizeoutput(path_new);
					System.out.println("El size d'entrada ha sigut de : " + size_input);
					System.out.println("El size de sortida ha sigut de : " + size_output);
					System.out.println("El compress ratio ha sigut de : " + (double)size_input/size_output );
				}
			}
			
			else {
			// type = 1
				int extension = c.getExtension(path);
				if(extension == 1) {
					String aux = c.getNameFile(path);
					aux = aux.substring(0, aux.lastIndexOf('.'));
					aux = path_out + "//" + aux + ".ppm";
					int size_output = ControladorDomini.getsizeoutput(aux);
					System.out.println("El size d'entrada ha sigut de : " + size_input);
					System.out.println("El size de sortida ha sigut de : " + size_output);
					System.out.println("El dcompress ratio ha sigut de : " + (double)size_output/size_input );
				}
				
				else {
					String aux = c.getNameFile(path);
					aux = aux.substring(0, aux.lastIndexOf('.'));
					aux = path_out + "//" + aux + ".txt";
					int size_output = ControladorDomini.getsizeoutput(aux);
					System.out.println("El size d'entrada ha sigut de : " + size_input);
					System.out.println("El size de sortida ha sigut de : " + size_output);
					System.out.println("El dcompress ratio ha sigut de : " + (double)size_output/size_input );
				}
			}
			
		}
		
		else {
			System.out.println("El temps de la compressio de carpeta ha sigut de :" + (float)((end - init)/1000) + " segons");
			System.out.println("El size de la sortida de la carpeta d'entrada ha sigut de : " + ControladorDomini.getFolderSize(path));
			String aux = c.getNameFile(path);
			if(type == 0) {
				aux = path_out + "//" + aux + ".prop";
				System.out.println(aux);
				System.out.println("El size de sortida de la carpeta de sortida ha sigut de : " + ControladorDomini.getFolderSize(aux));
			}
			
			else {
				aux = path_out + "//" + aux + "_uncompress";
				System.out.println(aux);
				System.out.println("El size de sortida de la carpeta de sortida ha sigut de : " + ControladorDomini.getFolderSize(aux));
			}
			
			

		}
	}

	/**
	 * Funcio compress
	 * Crida al controlador de domini la funcio compress
	 */
	
	private void compress() {
		ControladorDomini c = new ControladorDomini();
		System.out.println("Ara introdueixi el path desitjat:");
		Scanner myobj = new Scanner(System.in);
		String path = myobj.nextLine();
		System.out.println("Ara introdueixi el path de sortida desitjat");
		String path_out = myobj.nextLine();
		System.out.println("Siusplau ara indiqui una cualitat per la compressio de imatges entre 1 - 100, altrament escrigui -1 si no coneix els tipus de fitxers");
		String calitat = myobj.nextLine();
		int qualitat = Integer.parseInt(calitat);
		this.init = System.currentTimeMillis();
		c.comprimir(path, path_out, qualitat);
		this.end = System.currentTimeMillis();
		stadistics(path , path_out , 0);
		myobj.close();
		System.out.println("La compressio ha finalitzat correctament");
	}
	
	/**
	 * Funcio uncompress
	 * Crida al controlador de domini la funcio uncompress
	 */
	
	
	
	private void uncompress() throws IOException {
		ControladorDomini c = new ControladorDomini();
		Scanner myobj = new Scanner(System.in);
		myobj.nextLine();
		System.out.println("Ara introdueixi el path desitjat:");
		String path = myobj.nextLine();
		System.out.println("Ara introdueixi el path de sortida desitjat");
		String path_out = myobj.nextLine();
		System.out.println("Siusplau ara indiqui una cualitat per la descompressio de imatges entre 1 - 100, altrament escrigui -1 si no coneix els tipus de fitxers");
		String calitat = myobj.nextLine();
		int qualitat = Integer.parseInt(calitat);
		init = System.currentTimeMillis();
		c.descomprimir(path, path_out);
		end = System.currentTimeMillis();
		stadistics(path , path_out , 1);
		myobj.close();
		System.out.println("La descompressio ha finalitzat correctament");
	}
	
	/**
	 * Funcio main del programa 
	 * @param args
	 * @throws IOException
	 */
	
	public static void main(String args[]) throws IOException {
		System.out.println("---- Benvingut a l'execucio del programa des de Domini ----");
		System.out.println("Aquest main permet executar amb un path determinat si les funcions de generacio de estadistiques aixi com la compressio de fitxers funcionen correctament");
		System.out.println("Per provar la compressio de fitxers cal sol que seleccionis si vols descomprimir un fitxer o descomprimir" );
		System.out.println("Escriu 1 si vols comprimir, altrament 2");
		main m = new main();
		Scanner myobj = new Scanner(System.in);
		int val = myobj.nextInt();
		switch(val) {
			case  1 : 
				m.compress();
				break;
				
			case 2 :

				m.uncompress();
				break;
		}
		


	}

	
}
