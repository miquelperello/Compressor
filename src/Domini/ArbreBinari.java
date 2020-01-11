package Domini;

import java.util.Arrays;

/**
 * Classe ArbreBinari
 * @author RamonMateo
 * Implementacio de la classe Arbre Binari
 */

public class ArbreBinari {
	/**
	 * Necessito un arbre que pugui contindre totes les posicions del buffer circular que son 4096
	 * Declaro Pare , esq i dreta de 4096 posicions + 257 posicions extres per els indexos que comensen amb lletra 
	 */
	
	
	private short[] Arbre = new short[DEFINES.RING_SIZE * 3 + 2 + 257];
    private static final int posEsq = DEFINES.RING_SIZE+1;
    private static final int posDret = posEsq + DEFINES.RING_SIZE +1;
    
    private int matchPosition;
    private int matchLength;
	
    public ArbreBinari() {
    	Arrays.fill(Arbre, 0, Arbre.length, DEFINES.BUIT);
    }
    
    /**
     * Insercio del node 
     * @param pos Posicio a inserir
     * @param ringBuffer BufferCircular
     * @param aux Arbre binari en aquell instant. 
     */
    public void insertNode(int pos , byte[] ringBuffer , ArbreBinari aux){
    	this.Arbre  = aux.Arbre;
        
        int cmp = 1;
        short p = (short) (DEFINES.RING_SIZE + 1 + (ringBuffer[pos] % 255));
        this.Arbre[pos + posEsq] = DEFINES.BUIT;
        this.Arbre[pos + posDret] = DEFINES.BUIT;
        this.matchLength = 0;
        while (true) {
            if (cmp >= 0) {
                if (Arbre[posDret+p] != DEFINES.BUIT) {
                    p = Arbre[posDret + p];
                } 
                else {
                    Arbre[posDret + p] = (short)pos;
                    Arbre[pos] = p;
                    return;
                } 
            } 
            else {
                if (Arbre[posEsq+p] != DEFINES.BUIT) {
                    p = Arbre[posEsq+p];
                } 
                else {
                    Arbre[posEsq+p] = (short)pos;
                    Arbre[pos] = p;
                    return;
                }
            }
            int maxTrobat = find_matchlength(ringBuffer , pos , p);
            if(is_max(maxTrobat , p)) break;       
            
        } 
        set_TLRValues(pos , Arbre[p] , Arbre[posEsq+p] , Arbre[posDret +p]);
        Arbre[Arbre[posEsq + p]] = (short)pos;
        Arbre[Arbre[posDret + p]] = (short)pos;
    	short res = Arbre[p];
        if (Arbre[posDret+res] == p) {
            Arbre[posDret + res] = (short)pos;
        } 
        else {
            Arbre[posEsq+res] = (short)pos;
        }

        
        Arbre[p] = DEFINES.BUIT;
    }
    
    /**
     * Esborrat del node
     * @param node Node a esborrar
     */

    public void deleteNode(int node) {
        //primer cas : el pare no existeix
        if (Arbre[node] == DEFINES.BUIT) {
            return;
        }
        
        short valNode; 

        if (Arbre[posDret+node] == DEFINES.BUIT) {
            valNode = Arbre[posEsq+node];
        }
        
        else if (Arbre[posEsq+node] == DEFINES.BUIT) {
            valNode = Arbre[posDret+node];
        } 
        
        else {
            valNode = Arbre[posEsq+node];
        
            if (Arbre[posDret+valNode] != DEFINES.BUIT) {
            	valNode = Arbre[posDret+valNode];   
                while (Arbre[posDret+valNode] != DEFINES.BUIT) {
               	 	valNode = Arbre[posDret+valNode];   
                }
                
                Arbre[posDret+Arbre[valNode]] = Arbre[posEsq+valNode];
                Arbre[Arbre[posEsq+valNode]] = Arbre[valNode];
                Arbre[posEsq+valNode] = Arbre[node];
                Arbre[Arbre[posEsq+node]] = valNode;
            }

            Arbre[posDret+valNode] = Arbre[posDret+node];
            Arbre[Arbre[posDret+node]] = valNode;
        }
        evalTop(node , valNode);
        
        Arbre[node] = DEFINES.BUIT;
    }	
	
    /**
	 * Get de MatchPosition
	 * @return retorna on s'ha trobat matchposition
	 */
    
    public int get_matchposition() {
		return this.matchPosition;
	}
    
    /**
     * Evaluació top per la seva eliminació
     * @param node Node a esborrar
     * @param valNode Valor del node.  
     */
    
    private void evalTop(int node , short valNode) {
    	short valTop = Arbre[node];
        //En valNode guardo el valor del top
        Arbre[valNode] = valTop;
        //Si esquerra + valortop == node ho insereixo al dret
        if (Arbre[posDret+valTop] == node) {
            Arbre[posDret+valTop] = valNode;
        } 
        else {
            Arbre[posEsq+valTop] = valNode;
        }
    }
	
	/**
	 * Getter de matchlength
	 * @return retorna el matchlength actualitzat
	 */
	
	public int get_matchLength() {
		return this.matchLength;
	}
		
	/**
	 * Insercio de valors a pare , fillesq , filldret
	 * @param pos posicio del node
	 * @param t Valor a inserir en el pare
	 * @param l Valor a inserir en el fill esq
	 * @param r Valor a inserir en el fill dret
	 */
	
	public void set_TLRValues(int pos , short t , short l , short r) {
		Arbre[pos] = t;
        Arbre[pos+ posEsq] = l;
        Arbre[pos+ posDret] = r;
		
	}
	/**
	 * Buscador del maxim matchlength
	 * @param ringBuffer BufferCircular
	 * @param pos Posicio node a insertar
	 * @param p iterador
	 * @return Retorna el maxim de coincidencia trobat. 
	 */
	private int find_matchlength(byte[] ringBuffer , int pos , short p) {
		int find = 1;
        //He trobat algo. Comparo quantes lletres tenen igual. 
        for (short i = 1; i < DEFINES.MAX_STORE_LENGTH; i++) {
            int cmp = (ringBuffer[pos + i] % 255) - (ringBuffer[p + i] % 255);
            if (cmp != 0) {
                break;
            }
            ++find;
        }
        return find;
	}
	
	/**
	 * Funcio per calcular mirar maxim de longitud
	 * @param maxTrobat Longitud maxima trobada en el buffer amb coincidencia
	 * @param p Iterador
	 * @return Retorna si el maxTrobat es el maxim que puc trobar amb 18 caracters. 
	 */
	
	private Boolean is_max(int maxTrobat , short p ) {
		if (maxTrobat > this.matchLength) {
            this.matchPosition = p;
            this.matchLength = maxTrobat;
            // Si trobu un match que  igual o mes gran que el maxim que puc guardar acabo bucle
            if (maxTrobat >= DEFINES.MAX_STORE_LENGTH) {
                return true;
            }
            else return false;
        }
		return false;
	}
	
	
	
}
