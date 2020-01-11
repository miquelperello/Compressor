package Persistencia;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.ArrayList;
import java.io.FileOutputStream;


/**
 * LZSS Persistencia
 * @author Ramon Mateo
 * Implementacio de la classe Persistencia per l'obtencio i guardat de dades de l'algorisme LZSS
 */

public class LZSSPersistencia {

	/**
	 * GetArrayListLZSS
	 * @param path Path sobre el que es volen obtenir les dades
	 * @return Retorna ArrayList de la lectura del fitxer del parametra d'entrada
	 * @throws IOException
	 */

	public ArrayList<Byte> getArrayListLzss(String path) throws IOException {
		Path rn_demo = Paths.get(path);
		ArrayList<Byte> aux = new ArrayList<Byte>();
		try (InputStream in = Files.newInputStream(rn_demo)) {
			byte[] bytes = new byte[(int)Files.size(rn_demo)];
			in.read(bytes);
			for(int i = 0; i < bytes.length; ++i) {
				aux.add((Byte)bytes[i]);
			}

			in.close();
		}
		return aux;
	}

	/**
	 * SaveFileLZSS
	 * @param path_out Path de sortida sobre el que es vol desar el parametra d'entrada ' S '
	 * @param s ArrayList que es desitja guardar en el path_out
	 * @throws IOException
	 */

	public void saveFileLZSS(String path_out , ArrayList<Byte> s) throws IOException {
		FileOutputStream f = new FileOutputStream(path_out);
		byte[] bytes = new byte[s.size()];
		for(int i = 0; i < s.size(); ++i) {
			bytes[i] = s.get(i);
		}
		f.write(bytes, 0, bytes.length);
		f.close();
	}

}
