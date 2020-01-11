package Drivers;

import java.io.IOException;
import java.util.Scanner;
import Domini.ControladorDomini;

public class DriverController {
	
	
	private void compressuncompress() throws IOException {
		System.out.println("Ara indiqui que desitjat fer: 1 - comprimir , 2 - Descomprimir");
		Scanner obj = new Scanner(System.in);
		int decisio = obj.nextInt();
		if(decisio == 1) {
			System.out.println("Ara siusplau indiqui el path d'entrada ");
			Scanner myobj = new Scanner(System.in);
			String path = myobj.nextLine();
			System.out.println("Ara introdueixi el path de sortida desitjat");
			String path_out = myobj.nextLine();
			System.out.println("Siusplau ara indiqui una cualitat per la compressio de imatges entre 1 - 100, altrament escrigui -1 si no coneix els tipus de fitxers");
			String calitat = myobj.nextLine();
			int qualitat = Integer.parseInt(calitat);
			ControladorDomini.comprimir(path, path_out, qualitat);
			System.out.println("La execució ha finalitzat");
			myobj.close();
		}
		
		else if(decisio == 2) {
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
		
	}
	
	public void proves_path() throws IOException {
		ControladorDomini c = new ControladorDomini();
		System.out.println("Ara indiqui que desitjat fer: 1 - obtenir el nom del fitxer o directori , 2 - Obtenir el size del fitxer , 3 - Obtenir el size del directori");
		System.out.println("4 - Comprovar si un path es directori o fitxer , 5 - obtenir extensio de fitxer"); 
		Scanner myobj = new Scanner(System.in);
		int val = myobj.nextInt();
		System.out.println("Ara indiqui el path sobre el que desitja efectuar la prova");
		myobj.nextLine();
		String path = myobj.nextLine();
		switch(val) {
			case 1 :
				System.out.println("El nom del fitxer/directori  es : " + c.getNameFile(path));
				break;
			case 2 :
				System.out.println("El size del fitxer es : " + ControladorDomini.getsizeoutput(path));
				break;
			case 3 : 
				System.out.println("El size del directori  es : " + ControladorDomini.getFolderSize(path));
				break;
			case 4 :
				System.out.println("Path d'entrada es un directori ?  Resposta : " + c.isDirectory(path)); 
				break;
			case 5 :
				System.out.println("L'extensio del fitxer/directori  es : " + c.getExtension(path));
				break;
				
			default :
				System.out.println("Valor errroni");
				break;
		}
		myobj.close();

	}
	
	public static void main(String[] args) throws IOException {
		
		
		System.out.println("---- Benvingut al Driver del controlador de Domini ----");
		System.out.println("Aquest controlador permet testejar amb un path determinat varies de les crides a funcions que es troben dins de domini");
		System.out.println("Per provar les funcionalitats indiqui primerament que vol efectuar : 1 - Compressio/Descompressio , 2 - Proves amb paths");
		char val;
		DriverController c = new DriverController();
		val = (char) System.in.read();
		System.out.println();
			switch(val) {
				case('1'):
					c.compressuncompress();
					break;
				case('2'):
					c.proves_path();
					break;		
			}
			System.exit(0);

	}
}
