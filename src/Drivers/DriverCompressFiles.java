package Drivers;

import java.io.IOException;
import java.util.Scanner;

import Domini.*;

public class DriverCompressFiles {
	
	private void compress() {
		
		Scanner myobj = new Scanner(System.in);
		myobj.nextLine();
		System.out.println("Ara introdueixi el path desitjat:");
		String path = myobj.nextLine();
		System.out.println("Ara introdueixi el path de sortida desitjat");
		String path_out = myobj.nextLine();
		ControladorDomini.comprimir(path, path_out , 50);
		myobj.close();
		System.out.println("La compressio ha finalitzat");
	}
	
	private void uncompress() throws IOException {
		Scanner myobj = new Scanner(System.in);
		myobj.nextLine();
		System.out.println("Ara introdueixi el path desitjat:");
		String path = myobj.nextLine();
		System.out.println("Ara introdueixi el path de sortida desitjat");
		String path_out = myobj.nextLine();
		ControladorDomini.descomprimir(path, path_out);
		myobj.close();
		System.out.println("La descompressio ha finalitzat");
	}
	
	public static void main(String[] args) throws IOException {
		
		
		System.out.println("---- Benvingut al Driver del controlador de la classe CompressFiles Domini ----");
		System.out.println("Aquest controlador permet testejar amb un path determinat les dues crides principals de CompressFiles");
		System.out.println("Per provar les funcionalitats indiqui primerament que vol efectuar : 1 - Compressio de fitxer , altrament 2 per descompressio de fitxer" );
		char val;
		DriverCompressFiles c = new DriverCompressFiles();
		val = (char) System.in.read();
			switch(val) {
				case('1'):
					c.compress();
					break;
				case('2'):
					c.uncompress();
					break;
				
			}
			System.exit(0);

	}
}
