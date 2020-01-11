package Domini;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Implementació LZSS
 * @author Ramon Mateo
 *
 */


public class lzss extends Algorisme {
    
    private byte[] bufferCircular;
    private int matchPosition;
    private int matchLength;
    ArbreBinari arb = new ArbreBinari();
	
    public lzss() {
        bufferCircular = new byte[DEFINES.RING_SIZE + DEFINES.MAX_STORE_LENGTH - 1];
        
    }

    /**
     * Algorisme de compressio
     * @param in arraylist de bytes de la lectura del fitxer d'entrada
     * @return retorna un array amb el input comprimit
     * @throws IOException Genera excepcio si falla la compressio
     */

   public ArrayList<Byte> compress(ArrayList<Byte> in) throws IOException {
        ArrayList<Byte> output = new ArrayList<Byte>();
        int num_node = DEFINES.RING_SIZE - DEFINES.MAX_STORE_LENGTH; // Numero del node de l'arbre binari
        byte[] codeBuff = new byte[17]; // the output buffer
        bufferCircular = start_bufferCircular(0, num_node);
        int count = 0;
        int inputRead = 0;
        for(int i = 0; i < DEFINES.MAX_STORE_LENGTH; ++i) {
        	if(in.size() == count) break;
        	bufferCircular[num_node + i] = in.get(count);
        	++count;
        	
        }
        // llegeixo el resultat i miro si te dades. Si fitxer buit acabo compressio        
        if (count <= 0) {
            return output;
        }
        int lenString = (short) count;
        for (int i = 1; i <= DEFINES.MAX_STORE_LENGTH; i++) {
           short pos = (short) (num_node-i);
           if(pos_valida(pos)) {
        	   //insereixo els primers 18 elements del codebuffer
	           insert_node(pos);
	       }
        }
        if(pos_valida(num_node)) {
	        insert_node(num_node);
	    }
        byte mask = 1;
        int posBuffer = 0; // posicio del bufferCircular
        codeBuff[0] = 0; // inicialitzacio del codebuff
        int codeBufPos = 1; //posicio del output buffer comensa a 1 perque el 0 es per el flag
        while(lenString > 0) {
        	
            if (matchLength > lenString) {
                matchLength = lenString;
            }
            if (matchLength >= DEFINES.THRESHOLD) {
                codeBuff[codeBufPos++] = (byte) matchPosition;
                codeBuff[codeBufPos++] = (byte) (((matchPosition >> 4) & 0xF0) | (matchLength - DEFINES.THRESHOLD));
            } 
            else {
                matchLength = 1;
                codeBuff[0] |= mask;
                codeBuff[codeBufPos++] = bufferCircular[num_node];
            }
            mask <<= 1;
            if (mask == 0) {
            	for(int i = 0; i < codeBufPos; ++i) {
            		output.add((Byte)codeBuff[i]);
            	}
                codeBuff[0] = 0;
                codeBufPos = 1;
                mask = 1;
            }
            int  long_lastMatch = matchLength;
            int pos = 0;
            for (int i = 0; i < long_lastMatch; i++) {
            	if(count == in.size()) break;
                inputRead = in.get(count);
                ++count;
                Byte inp = (byte) inputRead;
                arb.deleteNode(posBuffer);
                bufferCircular[posBuffer] = inp;
                if (posBuffer < DEFINES.MAX_STORE_LENGTH - 1) {
                    bufferCircular[posBuffer + DEFINES.RING_SIZE] = inp;
                }
                posBuffer = get_newPos(posBuffer);
                num_node = get_newPos(num_node);
    	        insert_node(num_node);
    	        ++pos;
            } 
            while (pos++ < long_lastMatch) {
                if(pos_valida(posBuffer))arb.deleteNode(posBuffer);
                posBuffer =  get_newPos(posBuffer);
                num_node = get_newPos(num_node);
                if (--lenString != 0) {
        	        insert_node(num_node);
                }
           }
        }     
        if (codeBufPos > 1) {
        	for(int i = 0; i < codeBufPos; ++i) {
        		output.add((Byte)codeBuff[i]);
        	}
            
        }
        return output;
    }

        
    
    /**
     * Funcio de descompressio
     * @param in ArrayList amb els bytes a decodificar
     * @return Retorna arraylist de bytes descodificats del arraylist d'entrada
     * @throws IOException
     */
   
   public ArrayList<Byte> uncompress(ArrayList<Byte> in) throws IOException {
       ArrayList<Byte> output = new ArrayList<Byte>();
       int posBuffer = DEFINES.RING_SIZE - DEFINES.MAX_STORE_LENGTH;
       bufferCircular = start_bufferCircular(0, posBuffer);

       byte flags = 0; // flags
       int flagCount = 0; // count per els flags
       byte[] decodificat = new byte[DEFINES.MAX_STORE_LENGTH];
       int count = 0;
       while (true) {
           
           if (flagCount <= 0) {
        	   if(count == in.size()) break;
               int inputRead = in.get(count);
               ++count;
               flags = (byte) (inputRead % 256);
               flagCount = 7;
           } 
           
           else {
           	 flags = (byte) (flags >> 1);
                flagCount--;
           }

           if ((flags & 1) == 0) {
        	 Boolean breaks = false;
        	 for(int i = 0; i < 2 ; ++i) {
        		 if(count == in.size()) breaks = true; 
        		 else {
        			 decodificat[i] = in.get(count);
        			 ++count;
        		 }
        		 if(breaks) break;
        	 }
        	 if(breaks) break;
           	
                short pos = (short) ((decodificat[0] & 0xFF) | ((decodificat[1] & 0xF0) << 4));
                //Obtinc la longitud del string codificat i li sumu el threshold que se que era minim 3 per tal de poder ser codificat
                short len = (short) ((decodificat[1] & 0x0F) + DEFINES.THRESHOLD);
                for (int k = 0; k < len; k++) {
                    decodificat[k] = bufferCircular[(pos + k) & DEFINES.RING_WRAP];
                    bufferCircular[posBuffer] = decodificat[k];
                    posBuffer = get_newPos(posBuffer);
                }
                //Escric la descodificacio en el buffer de sortida. 
                for(int i = 0; i < len; ++i) {
                	output.add((Byte)decodificat[i]);
                }
                  
           } // flag dif de 0
           // Si no estava codificat escric tal cual el caracter i l'afageixo al buffer circular 
           else {
        	   if(count == in.size()) break;
               //if (input.read(decodificat, 0, 1) != 1) break;
        	   decodificat[0] = (byte)in.get(count);
        	   ++count;
               output.add((Byte)decodificat[0]);
               bufferCircular[posBuffer] = decodificat[0];
               posBuffer = get_newPos(posBuffer);
           }
       }
       return output;
    }
    
    
   
    
   
    
    
    /**
     * Actualitzacio de posicio 
     * @param pos Posicio actual
     * @return retorna pos + 1 % 4096
     * Permet evitar error de acces a direccio invalida del buffer
     */
    
    private int get_newPos(int pos) {
    	return (pos +1) % 4096;
    }
    
    /**
     * Posicio valida dins el arbre binary
     * @param pos Posicio a mirar
     * @return Retorna si la posicio es troba dins l'arbre
     */
    
    private boolean pos_valida(int pos) {
    	return pos >= 0 && pos < DEFINES.RING_SIZE; 	
    }
    
    /**
     * Insercio a l'arbre
     * @param pos Posicio a inserir en el node
     * Obte també el valor actualitzat del matchlength i el matchposition
     */
    
    private void insert_node(int pos) {
    	arb.insertNode(pos, bufferCircular, arb);
    	//tree.insertNode(pos, bufferCircular , tree);
        this.matchLength = arb.get_matchLength();
        this.matchPosition = arb.get_matchposition();
    }
    
    /**
     * Inicialitzacio del BufferCircular
     * @param start Posicio inicial del BufferCircular
     * @param end Posicio final del bufferCircular
     * @return retorna el BufferCircular inicialitzat
     */
    
    private byte[] start_bufferCircular(int start , int end) {
    	byte[] aux  = new byte[DEFINES.RING_SIZE + DEFINES.MAX_STORE_LENGTH - 1];
    	for(int i = start; i < end; ++i) {
    		aux[i] = ' ';
    	}
    	return aux;
    }


}