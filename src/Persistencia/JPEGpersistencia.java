package Persistencia;

import Domini.JPEGDomini;

import java.io.*;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class JPEGpersistencia {


    /**
     *  @brief Classe RGB
     */
    static class RGB{

        //datos

        public int R;
        public int G;
        public int B;

        //Constructora

        public RGB(int a, int b, int c) {
            this.R = a;
            this.G = b;
            this.B = c;
        }

        public RGB() {
            this.R = 0;
            this.G = 0;
            this.B = 0;
        }

        public RGB(int a, int b) {
            this.G = a;
            this.B = b;
        }

    };

    /**
     * Defincions prèvies
     */
    public static Map<AbstractMap.SimpleEntry<Integer, Integer>, String> lookupTableACL = new HashMap<AbstractMap.SimpleEntry<Integer, Integer>, String>();
    public static Map<AbstractMap.SimpleEntry<Integer, Integer>, String> lookupTableACC = new HashMap<AbstractMap.SimpleEntry<Integer, Integer>, String>();
    public static Map<Integer, String> DCValues = new HashMap<Integer,String>();
    public static String [] taulaAC = new String[161];
    public static String [] taulaACC = new String[161];
    public static String tipus;
    public static Integer altura;
    public static Integer anchura;
    public static int block;
    public static int iglobal; //indica per quina posicio va del arrayR
    public static int iteratorArray = 0;


    /**
     * @brief Inicialització de les taules Huffman
     */
    public static void iniciaDC() {
        DCValues.put(-1, "00");
        DCValues.put(1, "010");
        DCValues.put(2, "011");
        DCValues.put(3, "100");
        DCValues.put(4, "101");
        DCValues.put(5, "110");
        DCValues.put(6, "1110");
        DCValues.put(7, "11110");
        DCValues.put(8, "111110");
        DCValues.put(9, "1111110");
        DCValues.put(10, "11111110");
        DCValues.put(11, "111111110");

        //Taula Luminance
        taulaAC[0] = "1010";
        taulaAC[1] = "00";
        taulaAC[2] = "01";
        taulaAC[3] = "100";
        taulaAC[4] = "1011";
        taulaAC[5] = "11010";
        taulaAC[6] = "1111000";
        taulaAC[7] = "11111000";
        taulaAC[8] = "1111110110";
        taulaAC[9] = "1111111110000010";
        taulaAC[10] = "1111111110000011";
        taulaAC[11] = "1100";
        taulaAC[12] = "11011";
        taulaAC[13] = "1111001";
        taulaAC[14] = "111110110";
        taulaAC[15] = "11111110110";
        taulaAC[16] = "1111111110000100";
        taulaAC[17] = "1111111110000101";
        taulaAC[18] = "1111111110000110";
        taulaAC[19] = "1111111110000111";
        taulaAC[20] = "1111111110001000";
        taulaAC[21] = "11100";
        taulaAC[22] = "11111001";
        taulaAC[23] = "1111110111";
        taulaAC[24] = "111111110100";
        taulaAC[25] = "1111111110001001";
        taulaAC[26] = "1111111110001010";
        taulaAC[27] = "1111111110001011";
        taulaAC[28] = "1111111110001100";
        taulaAC[29] = "1111111110001101";
        taulaAC[30] = "1111111110001110";
        taulaAC[31] = "111010";
        taulaAC[32] = "111110111";
        taulaAC[33] = "111111110101";
        taulaAC[34] = "1111111110001111";
        taulaAC[35] = "1111111110010000";
        taulaAC[36] = "1111111110010001";
        taulaAC[37] = "1111111110010010";
        taulaAC[38] = "1111111110010011";
        taulaAC[39] = "1111111110010100";
        taulaAC[40] = "1111111110010101";
        taulaAC[41] = "111011";
        taulaAC[42] = "1111111000";
        taulaAC[43] = "1111111110010110";
        taulaAC[44] = "1111111110010111";
        taulaAC[45] = "1111111110011000";
        taulaAC[46] = "1111111110011001";
        taulaAC[47] = "1111111110011010";
        taulaAC[48] = "1111111110011011";
        taulaAC[49] = "1111111110011100";
        taulaAC[50] = "1111111110011101";
        taulaAC[51] = "1111010";
        taulaAC[52] = "11111110111";
        taulaAC[53] = "1111111110011110";
        taulaAC[54] = "1111111110011111";
        taulaAC[55] = "1111111110100000";
        taulaAC[56] = "1111111110100001";
        taulaAC[57] = "1111111110100010";
        taulaAC[58] = "1111111110100011";
        taulaAC[59] = "1111111110100100";
        taulaAC[60] = "1111111110100101";
        taulaAC[61] = "1111011";
        taulaAC[62] = "111111110110";
        taulaAC[63] = "1111111110100110";
        taulaAC[64] = "1111111110100111";
        taulaAC[65] = "1111111110101000";
        taulaAC[66] = "1111111110101001";
        taulaAC[67] = "1111111110101010";
        taulaAC[68] = "1111111110101011";
        taulaAC[69] = "1111111110101100";
        taulaAC[70] = "1111111110101101";
        taulaAC[71] = "11111010";
        taulaAC[72] = "111111110111";
        taulaAC[73] = "1111111110101110";
        taulaAC[74] = "1111111110101111";
        taulaAC[75] = "1111111110110000";
        taulaAC[76] = "1111111110110001";
        taulaAC[77] = "1111111110110010";
        taulaAC[78] = "1111111110110011";
        taulaAC[79] = "1111111110110100";
        taulaAC[80] = "1111111110110101";
        taulaAC[81] = "111111000";
        taulaAC[82] = "111111111000000";
        taulaAC[83] = "1111111110110110";
        taulaAC[84] = "1111111110110111";
        taulaAC[85] = "1111111110111000";
        taulaAC[86] = "1111111110111001";
        taulaAC[87] = "1111111110111010";
        taulaAC[88] = "1111111110111011";
        taulaAC[89] = "1111111110111100";
        taulaAC[90] = "1111111110111101";
        taulaAC[91] = "111111001";
        taulaAC[92] = "1111111110111110";
        taulaAC[93] = "1111111110111111";
        taulaAC[94] = "1111111111000000";
        taulaAC[95] = "1111111111000001";
        taulaAC[96] = "1111111111000010";
        taulaAC[97] = "1111111111000011";
        taulaAC[98] = "1111111111000100";
        taulaAC[99] = "1111111111000101";
        taulaAC[100] = "1111111111000110";
        taulaAC[101] = "111111010";
        taulaAC[102] = "1111111111000111";
        taulaAC[103] = "1111111111001000";
        taulaAC[104] = "1111111111001001";
        taulaAC[105] = "1111111111001010";
        taulaAC[106] = "1111111111001011";
        taulaAC[107] = "1111111111001100";
        taulaAC[108] = "1111111111001101";
        taulaAC[109] = "1111111111001110";
        taulaAC[110] = "1111111111001111";
        taulaAC[111] = "1111111001";
        taulaAC[112] = "1111111111010000";
        taulaAC[113] = "1111111111010001";
        taulaAC[114] = "1111111111010010";
        taulaAC[115] = "1111111111010011";
        taulaAC[116] = "1111111111010100";
        taulaAC[117] = "1111111111010101";
        taulaAC[118] = "1111111111010110";
        taulaAC[119] = "1111111111010111";
        taulaAC[120] = "1111111111011000";
        taulaAC[121] = "1111111010";
        taulaAC[122] = "1111111111011001";
        taulaAC[123] = "1111111111011010";
        taulaAC[124] = "1111111111011011";
        taulaAC[125] = "1111111111011100";
        taulaAC[126] = "1111111111011101";
        taulaAC[127] = "1111111111011110";
        taulaAC[128] = "1111111111011111";
        taulaAC[129] = "1111111111100000";
        taulaAC[130] = "1111111111100001";
        taulaAC[131] = "11111111000";
        taulaAC[132] = "1111111111100010";
        taulaAC[133] = "1111111111100011";
        taulaAC[134] = "1111111111100100";
        taulaAC[135] = "1111111111100101";
        taulaAC[136] = "1111111111100110";
        taulaAC[137] = "1111111111100111";
        taulaAC[138] = "1111111111101000";
        taulaAC[139] = "1111111111101001";
        taulaAC[140] = "1111111111101010";
        taulaAC[141] = "1111111111101011";
        taulaAC[142] = "1111111111101100";
        taulaAC[143] = "1111111111101101";
        taulaAC[144] = "1111111111101110";
        taulaAC[145] = "1111111111101111";
        taulaAC[146] = "1111111111110000";
        taulaAC[147] = "1111111111110001";
        taulaAC[148] = "1111111111110010";
        taulaAC[149] = "1111111111110011";
        taulaAC[150] = "1111111111110100";
        taulaAC[151] = "11111111111111111101";
        taulaAC[152] = "1111111111110101";
        taulaAC[153] = "1111111111110110";
        taulaAC[154] = "1111111111110111";
        taulaAC[155] = "1111111111111000";
        taulaAC[156] = "1111111111111001";
        taulaAC[157] = "1111111111111010";
        taulaAC[158] = "1111111111111011";
        taulaAC[159] = "1111111111111100";
        taulaAC[160] =  "1111111111111101";
        AbstractMap.SimpleEntry<Integer, Integer> primer = new AbstractMap.SimpleEntry<Integer, Integer>(0,0);
        AbstractMap.SimpleEntry<Integer, Integer> ultim = new AbstractMap.SimpleEntry<Integer, Integer>(16,0);

        lookupTableACL.put(primer, taulaAC[0]);
        int f = 1;
        for(int i = 0; i<16; i++) {
            for(int j=1; j<11; j++) {
                AbstractMap.SimpleEntry<Integer, Integer> p = new AbstractMap.SimpleEntry<Integer, Integer>(i,j);
                lookupTableACL.put(p, taulaAC[f]);
                f++;
            }
        }
        lookupTableACL.put(ultim, "11111111001");

        //Taula Chrominance

        taulaACC[0] = "00";
        taulaACC[1] = "01";
        taulaACC[2] = "100";
        taulaACC[3] = "1010";
        taulaACC[4] = "11000";
        taulaACC[5] = "11001";
        taulaACC[6] = "111000";
        taulaACC[7] = "1111000";
        taulaACC[8] = "111110100";
        taulaACC[9] = "1111110110";
        taulaACC[10] = "111111110100";
        taulaACC[11] = "1011";
        taulaACC[12] = "111001";
        taulaACC[13] = "11110110";
        taulaACC[14] = "111110101";
        taulaACC[15] = "11111110110";
        taulaACC[16] = "111111110101";
        taulaACC[17] = "1111111110001000";
        taulaACC[18] = "1111111110001001";
        taulaACC[19] = "1111111110001010";
        taulaACC[20] = "1111111110001011";
        taulaACC[21] = "11010";
        taulaACC[22] = "11110111";
        taulaACC[23] = "1111110111";
        taulaACC[24] = "111111110110";
        taulaACC[25] = "111111111000010";
        taulaACC[26] = "1111111110001100";
        taulaACC[27] = "1111111110001101";
        taulaACC[28] = "1111111110001110";
        taulaACC[29] = "1111111110001111";
        taulaACC[30] = "1111111110010000";
        taulaACC[31] = "11011";
        taulaACC[32] = "11111000";
        taulaACC[33] = "1111111000";
        taulaACC[34] = "111111110111";
        taulaACC[35] = "1111111110010001";
        taulaACC[36] = "1111111110010010";
        taulaACC[37] = "1111111110010011";
        taulaACC[38] = "1111111110010100";
        taulaACC[39] = "1111111110010101";
        taulaACC[40] = "1111111110010110";
        taulaACC[41] = "111010";
        taulaACC[42] = "111110110";
        taulaACC[43] = "1111111110010111";
        taulaACC[44] = "1111111110011000";
        taulaACC[45] = "1111111110011001";
        taulaACC[46] = "1111111110011010";
        taulaACC[47] = "1111111110011011";
        taulaACC[48] = "1111111110011100";
        taulaACC[49] = "1111111110011101";
        taulaACC[50] = "1111111110011110";
        taulaACC[51] = "111011";
        taulaACC[52] = "1111111001";
        taulaACC[53] = "1111111110011111";
        taulaACC[54] = "1111111110100000";
        taulaACC[55] = "1111111110100001";
        taulaACC[56] = "1111111110100010";
        taulaACC[57] = "1111111110100011";
        taulaACC[58] = "1111111110100100";
        taulaACC[59] = "1111111110100101";
        taulaACC[60] = "1111111110100110";
        taulaACC[61] = "1111001";
        taulaACC[62] = "11111110111";
        taulaACC[63] = "1111111110100111";
        taulaACC[64] = "1111111110101000";
        taulaACC[65] = "1111111110101001";
        taulaACC[66] = "1111111110101010";
        taulaACC[67] = "1111111110101011";
        taulaACC[68] = "1111111110101100";
        taulaACC[69] = "1111111110101101";
        taulaACC[70] = "1111111110101110";
        taulaACC[71] = "1111010";
        taulaACC[72] = "11111111000";
        taulaACC[73] = "1111111110101111";
        taulaACC[74] = "1111111110110000";
        taulaACC[75] = "1111111110110001";
        taulaACC[76] = "1111111110110010";
        taulaACC[77] = "1111111110110011";
        taulaACC[78] = "1111111110110100";
        taulaACC[79] = "1111111110110101";
        taulaACC[80] = "1111111110110110";
        taulaACC[81] = "11111001";
        taulaACC[82] = "1111111110110111";
        taulaACC[83] = "1111111110111000";
        taulaACC[84] = "1111111110111001";
        taulaACC[85] = "1111111110111010";
        taulaACC[86] = "1111111110111011";
        taulaACC[87] = "1111111110111100";
        taulaACC[88] = "1111111110111101";
        taulaACC[89] = "1111111110111110";
        taulaACC[90] = "1111111110111111";
        taulaACC[91] = "111110111";
        taulaACC[92] = "1111111111000000";
        taulaACC[93] = "1111111111000001";
        taulaACC[94] = "1111111111000010";
        taulaACC[95] = "1111111111000011";
        taulaACC[96] = "1111111111000100";
        taulaACC[97] = "1111111111000101";
        taulaACC[98] = "1111111111000110";
        taulaACC[99] = "1111111111000111";
        taulaACC[100] = "1111111111001000";
        taulaACC[101] = "111111000";
        taulaACC[102] = "1111111111001001";
        taulaACC[103] = "1111111111001010";
        taulaACC[104] = "1111111111001011";
        taulaACC[105] = "1111111111001100";
        taulaACC[106] = "1111111111001101";
        taulaACC[107] = "1111111111001110";
        taulaACC[108] = "1111111111001111";
        taulaACC[109] = "1111111111010000";
        taulaACC[110] = "1111111111010001";
        taulaACC[111] = "111111001";
        taulaACC[112] = "1111111111010010";
        taulaACC[113] = "1111111111010011";
        taulaACC[114] = "1111111111010100";
        taulaACC[115] = "1111111111010101";
        taulaACC[116] = "1111111111010110";
        taulaACC[117] = "1111111111010111";
        taulaACC[118] = "1111111111011000";
        taulaACC[119] = "1111111111011001";
        taulaACC[120] = "1111111111011010";
        taulaACC[121] = "111111010";
        taulaACC[122] = "1111111111011011";
        taulaACC[123] = "1111111111011100";
        taulaACC[124] = "1111111111011101";
        taulaACC[125] = "1111111111011110";
        taulaACC[126] = "1111111111011111";
        taulaACC[127] = "1111111111100000";
        taulaACC[128] = "1111111111100001";
        taulaACC[129] = "1111111111100010";
        taulaACC[130] = "1111111111100011";
        taulaACC[131] = "11111111001";
        taulaACC[132] = "1111111111100100";
        taulaACC[133] = "1111111111100101";
        taulaACC[134] = "1111111111100110";
        taulaACC[135] = "1111111111100111";
        taulaACC[136] = "1111111111101000";
        taulaACC[137] = "1111111111101001";
        taulaACC[138] = "1111111111101010";
        taulaACC[139] = "1111111111101011";
        taulaACC[140] = "1111111111101100";
        taulaACC[141] = "11111111100000";
        taulaACC[142] = "1111111111101101";
        taulaACC[143] = "1111111111101110";
        taulaACC[144] = "1111111111101111";
        taulaACC[145] = "1111111111110000";
        taulaACC[146] = "1111111111110001";
        taulaACC[147] = "1111111111110010";
        taulaACC[148] = "1111111111110011";
        taulaACC[149] = "1111111111110100";
        taulaACC[150] = "1111111111110101";
        taulaACC[151] = "1111111111111111101";
        taulaACC[152] = "111111111000011";
        taulaACC[153] = "1111111111110110";
        taulaACC[154] = "1111111111110111";
        taulaACC[155] = "1111111111111000";
        taulaACC[156] = "1111111111111001";
        taulaACC[157] = "1111111111111010";
        taulaACC[158] = "1111111111111011";
        taulaACC[159] = "1111111111111100";
        taulaACC[160] = "1111111111111101";



        lookupTableACC.put(primer, taulaACC[0]);
        f = 1;
        for(int i = 0; i<16; i++) {
            for(int j=1; j<11; j++) {
                AbstractMap.SimpleEntry<Integer, Integer> p = new AbstractMap.SimpleEntry<Integer, Integer>(i,j);
                lookupTableACC.put(p, taulaACC[f]);
                f++;
            }
        }
        lookupTableACC.put(ultim, "1111111010");

    }

//

    /**
     * @brief Funcio que crido desde el domini. retorna arrayList
     * @param path parametre d'arxiu d'entrada
     * @return Arraylist dels arraylist dels tres components RGB
     * @throws IOException
     */
    public static ArrayList<ArrayList<int[][]>> LlegirFile(String path) throws IOException {

        FileInputStream in = new FileInputStream(path);
        String altu= "", anchu = "";
        int altura, anchura;
        char  tipus1 = '0';
        ArrayList<int [][]> arraymatR = new ArrayList<int [][]>();
        ArrayList<int [][]> arraymatG =  new ArrayList<int [][]>();
        ArrayList<int [][]> arraymatB = new ArrayList<int [][]>();

        in.read();
        in.read();

        while((tipus1 =(char)in.read())!= ' ') {
            altu+=Character.toString(tipus1);
        }
        altu = altu.trim();

        while((tipus1 =(char)in.read())!= '\n') {
            anchu+=Character.toString(tipus1);
        }
        anchu = anchu.trim();


        altura =Integer.valueOf(altu);
        anchura = Integer.valueOf(anchu);

        in.read();
        in.read();
        in.read();
        in.read();

        RGB Color = new RGB();
        Color.R = 0;
        Color.G = 0;
        Color.B = 0;

        int var=0;
        int r,g,b, y,u,v;
        while (var<(anchura*altura*3)){
            int[][]  matriz8R = new int[8][8];
            int[][]  matriz8G = new int[8][8];
            int[][]  matriz8B = new int[8][8];

            for(int j = 0; j<8; j++) {
                for(int k = 0; k<8; k++) {
                    if(((r = in.read())==-1)){
                        r = 0;
                    }
                    if(((g = in.read())==-1)){
                        g = 0;
                    }
                    if(((b = in.read())==-1)){
                        b = 0;
                    }

                    y =   (int) Math.round(( 0.299*r) +(0.587 * g) + (0.114*b));
                    u =  (int) Math.round(128-(0.168736 * r) - (0.331264*g) + 0.5*b);
                    v =  (int) Math.round(128 + (0.5 * r) - (0.418688 * g) - (0.081312 * b));

                    //Controlar valors fora de rang
                    if(y<0) y =0;
                    if(u<0) u =0;
                    if(u<0) u =0;

                    if(y>255) y =255;
                    if(u>255) u =255;
                    if(v>255) v =255;

                    matriz8R[j][k] = y;
                    matriz8G[j][k] = u;
                    matriz8B[j][k] = v;
                    var+=3;

                }

            }

            arraymatR.add(matriz8R);
            arraymatG.add(matriz8G);
            arraymatB.add(matriz8B);

        }
        iniciaDC();
        ArrayList<ArrayList<int [][]>> ret = new ArrayList<ArrayList<int [][]>>();
        ret.add(arraymatR);
        ret.add(arraymatG);
        ret.add(arraymatB);
        return ret;

    }

    /**
     * @brief Funcion que crido per escriure l'arxiu comprimit
     * @param arrayFinal array de valors
     * @param path_out path on he d'escriure
     * @throws IOException
     */
    public static void EscriuJPEG(ArrayList<Integer> arrayFinal, String path_out) throws IOException {

        FileOutputStream out = new FileOutputStream(path_out);
        out.write(new String("P6" + "\n").getBytes());
        out.write(new String( altura + " " + anchura + "\n").getBytes());
        out.write(new String("255\n").getBytes());
        for(int i = 0; i < arrayFinal.size(); i++){
            out.write(arrayFinal.get(i));
        }
        out.close();
    }


    /**
     * @brief Funcio que llegeix l'arxiu codificar
     * @param path path d'entrada
     * @param path_out path de sortida
     * @throws IOException
     */
    public static void DecodificarJPEG(String path, String path_out) throws IOException {

            ArrayList<ArrayList<Integer>> arraymatR;
            ArrayList<ArrayList<Integer>> arraymatG;
            ArrayList<ArrayList<Integer>> arraymatB;
            char tipus1;
            String altu= "", anchu = "";
            FileInputStream in = new FileInputStream(path);
            FileOutputStream out =new FileOutputStream(path_out);
            in.read();
            in.read();
            while((tipus1 =(char)in.read())!= ' ') {
                altu+=Character.toString(tipus1);
            }
            altu = altu.trim();
            while((tipus1 =(char)in.read())!= '\n') {
                anchu+=Character.toString(tipus1);
            }
            anchu = anchu.trim();
            in.read();
            in.read();
            in.read();
            in.read();
            iniciaDC();
            tipus = "P6";
            altura =Integer.valueOf(altu);
            anchura = Integer.valueOf(anchu);
            block = (int) Math.ceil(((double)altura * (double) anchura)/64);
            arraymatR = new ArrayList<ArrayList<Integer>>(block);
            arraymatG = new ArrayList<ArrayList<Integer>>(block);
            arraymatB = new ArrayList<ArrayList<Integer>>(block);

            //Lectura de datos
            //Nos entra en bloques de strings de 8 en 8 codificados segun HT. Primero bloque R, despues G Y despues B.

            for(int blocs = 0; blocs < block*3 ; blocs++) {
                ArrayList<Integer> arrayR = new ArrayList<Integer>(64);
                int entrada, contadorbreak = 0;;
                String entradatext = "", shiftedi = null;
                AbstractMap.SimpleEntry<Integer, Integer> AbstractMapSimpleEntry = new AbstractMap.SimpleEntry<Integer,Integer>(0, 0);
                while((entrada = in.read())!=-1) {
                    if(contadorbreak == 0) {
                        if(entrada == 255) contadorbreak++;
                        else contadorbreak = 0;
                    }
                    else if(contadorbreak == 1) {
                        if(entrada == 244) {//marker
                            contadorbreak++;
                        }
                        else contadorbreak = 0;
                    }
                    else if(contadorbreak == 2) {
                        if(entrada == 244) {//marker
                            contadorbreak++;
                        }
                        else contadorbreak = 0;
                    }
                    else if(contadorbreak == 3) {
                        if(entrada == 255) {//marker
                            entradatext = entradatext.substring(0,entradatext.length()-8);
                            break;
                        }
                        else contadorbreak = 0;
                    }
                    String binary = Integer.toBinaryString(entrada);
                    binary = ("00000000" + binary).substring(binary.length());
                    binary = binary + "00000000".substring(binary.length());
                    entradatext += binary;
                }
                //Trobem DC
                boolean trobat = false, es0 = false;
                int size = 0, size1=0, p;
                String auxDC = entradatext, auxauxDC = "";
                for( p = 1; trobat == false; p++) {
                    auxauxDC = auxDC.substring(0,p);
                    for(int i : DCValues.keySet()) {
                        String val = DCValues.get(i);
                        if (val.equals(auxauxDC)) {
                            if(i == 0) size1 =2;
                            else if(i==-1) { //DC == 0
                                es0 = true;
                                break;
                            }
                            else {
                                if(i == -1) i =0;
                                size= i;
                                size1 = i;
                            }
                            trobat = true;
                            break;
                        }
                    }
                    if(trobat || es0) break;
                }
                int DC = 0;
                if(es0) {
                    entradatext = entradatext.substring(2);
                }
                else {
                    entradatext = entradatext.substring(auxauxDC.length()+ size);
                    DC = 0;
                    if(size == 0) auxDC = auxauxDC;
                    else {
                        try{
                            auxDC = auxDC.substring(auxauxDC.length(),auxauxDC.length()+ size1);
                        }
                        catch(Exception e) {
                            System.out.println();
                        }
                        if(auxDC.charAt(0)=='1') {
                            DC = Integer.parseInt(auxDC, 2);
                        }
                        else  { //Valor negatiu, lhem de flipejar	00-> 11
                            String val;
                            val = auxDC.replace('0', '2').replace('1', '0').replace('2', '1');//canviem 1's per 0's
                            int numeroneg = Integer.parseInt(val, 2);
                            numeroneg = numeroneg *-1;
                            DC =  numeroneg;
                        }
                    }
                }
                arrayR.add(DC);
                iglobal++;
                //Tenim DC a $DC
                //Ara hem d'aconseguir els AC.
                /*
                 * La codificació va de la següent manera:
                 * 	-(RLE,SIZE) On rle número de 0's davant del numero, size el valor
                 * 	-(VALUE) Valor codificat en integer
                 */
                for(int k=1; entradatext.length()>0 && !entradatext.equals("0") && iglobal<64; k++) {
                    shiftedi = entradatext.substring(0,k);//busqueda bit a bit
                    if((AbstractMapSimpleEntry = buscaString(shiftedi, iteratorArray))!=null) {
                        int p1 = AbstractMapSimpleEntry.getKey();
                        int p2 = AbstractMapSimpleEntry.getValue();
                        trobat = true;
                        String valor = null;
                        if(p1 == 0 && p2 == 0) {
                            valor = "0";
                            boolean fi = tractaAC(arrayR, p1, p2, 0, valor);
                            if(fi) entradatext = "";
                            break;
                        }
                        else if(p1 == 16) {
                            entradatext = entradatext.substring(k);
                            valor = "0";
                            boolean fi = tractaAC(arrayR, p1, p2, 0, valor);
                            if(fi) entradatext = "";
                            k = 0;
                        }
                        else  {
                            valor = entradatext.substring(k, k + p2);
                            entradatext = entradatext.substring(k+p2);
                            int value = 0;
                            value = Integer.parseInt(valor, 2);
                            boolean fi = tractaAC(arrayR, p1, p2, value, valor);
                            if(fi) entradatext = "";
                            k=0;
                        }
                    }
                }

                //Ens falta tractar AC
                //Hem acabat
                if(iteratorArray == 0 ) {
                    arraymatR.add(arrayR);
                    iteratorArray++;
                    iglobal = 0;
                }
                else if(iteratorArray == 1 ) {
                    arraymatG.add(arrayR);
                    iteratorArray++;
                    iglobal = 0;
                }
                else if(iteratorArray == 2 ) {
                    arraymatB.add(arrayR);
                    iteratorArray= 0;
                    iglobal = 0;
                }
            }
            int calitat = in.read();
    }



//----------------------------DESCOMPRESSIO---------------------------
    /**
     * Busca si un String d'entrada coincideix amb alguna entrada de les taules de Huffman
     * @param shiftedi : String d'entrada
     * @param iteratorArray2 : ens marca si hem de buscar a la taula de Lluminancia o la de chrominancia
     * @return
     */
    private static AbstractMap.SimpleEntry<Integer, Integer> buscaString(String shiftedi, int iteratorArray2) {

        if(iteratorArray2==0) {
            for(AbstractMap.SimpleEntry<Integer, Integer> i: lookupTableACL.keySet()) {
                String actual = lookupTableACL.get(i);
                if(actual.equals( shiftedi))return i;
            }
        }
        else {
            for(AbstractMap.SimpleEntry<Integer, Integer> i: lookupTableACC.keySet()) {
                String actual = lookupTableACC.get(i);
                if(actual.equals( shiftedi))return i;
            }
        }
        return null;
    }


    /**
     * Funcio que tracta i omple l'arrayList d'entrada segons els valors d'entrada
     * @param arrayR2: arrayList d'entrada que volem que sigui tractat
     * @param p1 : primer valor del pair
     * @param p2 : segon valor del pair
     * @param valor : valor
     * @param valor2 : valor en String
     * @return
     */
    //p1 indica 0's
    private static boolean tractaAC(ArrayList<Integer> arrayR2, int p1, int p2, int valor, String valor2) {


        boolean es16 = false;
        if(p1 == 0 && p2 == 0) {//EOB
            while(arrayR2.size()!=64) {
                arrayR2.add(0);
                iglobal++;
            }
            return true;
        }
        else if(p1!= 0) {
            if(p1 == 16) {
                int cp1 = p1;
                while(cp1>0) {
                    arrayR2.add(0);
                    iglobal++;
                    cp1--;
                }
                es16 = true;
                return false;
                //queden 0's
            }
            else {
                int cp1 = p1;
                while(cp1>0) {
                    arrayR2.add(0);
                    iglobal++;
                    cp1--;
                }

            }
        }

        if((p1 != 0 || p2!= 0) && !es16 ) {//no hi ha 0's que quedin i es num
            if(valor2.charAt(0)=='1') {
                arrayR2.add(valor);
                iglobal++;
            }
            else if(!es16) { //Valor negatiu, lhem de flipejar	00-> 11
                String val;
                val = valor2.replace('0', '2').replace('1', '0').replace('2', '1');//canviem 1's per 0's
                int numeroneg = Integer.parseInt(val, 2);
                numeroneg = numeroneg *-1;
                arrayR2.add(numeroneg);
                iglobal++;
            }
        }
        return false;
    }

    /**
     *
     * @param arrayR
     * @param arrayG
     * @param arrayB
     * @param path
     */
    public static void escriuDescomprimit(ArrayList<Integer> arrayR, ArrayList<Integer> arrayG, ArrayList<Integer> arrayB, String path, int anchura, int altura) throws IOException {
        FileOutputStream out = new FileOutputStream(path);
        out.write(new String("P6" + "\n").getBytes());
        out.write(new String( altura + " " + anchura + "\n").getBytes());
        out.write(new String("255\n").getBytes());


        for(int i = 0; i<arrayR.size(); i++){
            out.write(arrayR.get(i));
            out.write(arrayG.get(i));
            out.write(arrayB.get(i));

        }
    out.close();
    }


   public static Integer getAltura(String path) throws IOException {

        FileInputStream in = new FileInputStream(path);

        in.read();
        in.read();
        char tipus1;
        String altu="";


        while((tipus1 =(char)in.read())!= ' ') {
            altu+=Character.toString(tipus1);
        }
        altu = altu.trim();

        altura =Integer.valueOf(altu);
        return altura;
   }

    public static Integer getAnchura(String path) throws IOException {

        FileInputStream in = new FileInputStream(path);

        in.read();
        in.read();
        char tipus1;
        String anchu="";
        String altu="";


        while((tipus1 =(char)in.read())!= ' ') {
            altu+=Character.toString(tipus1);
        }


        while((tipus1 =(char)in.read())!= '\n') {
            anchu+=Character.toString(tipus1);
        }
        anchu = anchu.trim();

        anchura =Integer.valueOf(anchu);
        return anchura;
    }

    /**
     * @brief Donat un path d'un arxiu comprimit, inicia l'algorisme de descompressió
     * @param path : path d'entrada de l'arxiu comprimit.
     * @throws IOException
     * @return
     */
    public static ArrayList<String> LlegeixDescomprimit(String path) throws IOException {

        FileInputStream in = null;
        FileOutputStream out = null;
        char tipus1;
        String altu = "", anchu = "";
        in = new FileInputStream(path);
        in.read();
        in.read();
        while ((tipus1 = (char) in.read()) != ' ') {
            altu += Character.toString(tipus1);
        }
        altu = altu.trim();
        while ((tipus1 = (char) in.read()) != '\n') {
            anchu += Character.toString(tipus1);
        }
        anchu = anchu.trim();
        in.read();
        in.read();
        in.read();
        in.read();

        iniciaDC();

        tipus = "P6";
        altura = Integer.valueOf(altu);
        anchura = Integer.valueOf(anchu);
        block = (int) Math.ceil(((double) altura * (double) anchura) / 64);


        //Lectura de datos
        //Nos entra en bloques de strings de 8 en 8 codificados segun HT. Primero bloque R, despues G Y despues B.

        ArrayList<String> arrayStrings = new ArrayList<String>();
        arrayStrings.add(Integer.toString(altura));
        arrayStrings.add(Integer.toString(anchura));
        arrayStrings.add(Integer.toString(block));

        for (int blocs = 0; blocs < block * 3; blocs++) {
            ArrayList<Integer> arrayR = new ArrayList<Integer>(64);

            int entrada, contadorbreak = 0;
            ;
            String entradatext = "";

            while ((entrada = in.read()) != -1) {
                if (contadorbreak == 0) {
                    if (entrada == 255) contadorbreak++;
                    else contadorbreak = 0;
                } else if (contadorbreak == 1) {
                    if (entrada == 244) {//marker
                        contadorbreak++;
                    } else contadorbreak = 0;
                } else if (contadorbreak == 2) {
                    if (entrada == 244) {//marker
                        contadorbreak++;
                    } else contadorbreak = 0;
                } else if (contadorbreak == 3) {
                    if (entrada == 255) {//marker
                        entradatext = entradatext.substring(0, entradatext.length() - 8);
                        arrayStrings.add(entradatext);
                        break;
                    } else contadorbreak = 0;
                }


                String binary = Integer.toBinaryString(entrada);
                binary = ("00000000" + binary).substring(binary.length());
                binary = binary + "00000000".substring(binary.length());
                entradatext += binary;
            }

        }
        String calitat = Integer.toString(in.read());
        arrayStrings.add(calitat);

        return arrayStrings;
        }

    /**
     * @brief Funcio que escriu l'arxiu descomprimit
     * @param arrayFinal array d'elements a escriure
     * @param path_out path on hem d'escriure
     * @throws IOException
     */
    public static void EscriuDescomprimitJPEG(ArrayList<ArrayList<Integer>> arrayFinal, String path_out) throws IOException {

        ArrayList<Integer> arrayR = arrayFinal.get(0);
        ArrayList<Integer> arrayG = arrayFinal.get(1);
        ArrayList<Integer> arrayB = arrayFinal.get(2);

        FileOutputStream out = new FileOutputStream(path_out);

        out.write(new String(tipus + "\n").getBytes());
        out.write(new String( altura + " " + anchura + "\n").getBytes());
        out.write(new String("255\n").getBytes());

        for (int i = 0; i < arrayR.size(); i++) {

            int r = arrayR.get(i);
            int g = arrayG.get(i);
            int b = arrayB.get(i);

            out.write(r);
            out.write(g);
            out.write(b);

        }

        out.close();

    }



    }
