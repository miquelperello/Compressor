package Domini;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Miquel Perelló
 * @class Classe JPEGDomini
 */
public class JPEGDomini extends Algorisme{
    public static Map<AbstractMap.SimpleEntry<Integer, Integer>, String> lookupTableACL = new HashMap<AbstractMap.SimpleEntry<Integer, Integer>, String>();
    public static Map<AbstractMap.SimpleEntry<Integer, Integer>, String> lookupTableACC = new HashMap<AbstractMap.SimpleEntry<Integer, Integer>, String>();
    public static Map<Integer, String> DCValues = new HashMap<Integer, String>();
    public static String[] taulaACC = new String[161];
    public static String[] taulaAC = new String[161];


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
        taulaAC[160] = "1111111111111101";
        AbstractMap.SimpleEntry<Integer, Integer> primer = new AbstractMap.SimpleEntry<Integer, Integer>(0, 0);
        AbstractMap.SimpleEntry<Integer, Integer> ultim = new AbstractMap.SimpleEntry<Integer, Integer>(16, 0);

        lookupTableACL.put(primer, taulaAC[0]);
        int f = 1;
        for (int i = 0; i < 16; i++) {
            for (int j = 1; j < 11; j++) {
                AbstractMap.SimpleEntry<Integer, Integer> p = new AbstractMap.SimpleEntry<Integer, Integer>(i, j);
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
        for (int i = 0; i < 16; i++) {
            for (int j = 1; j < 11; j++) {
                AbstractMap.SimpleEntry<Integer, Integer> p = new AbstractMap.SimpleEntry<Integer, Integer>(i, j);
                lookupTableACC.put(p, taulaACC[f]);
                f++;
            }
        }
        lookupTableACC.put(ultim, "1111111010");

    }

    /**
     * @brief Taula de quantització de la component Y
     */
    public static int[][] quantLuminance = new int[][]{
            {16, 11, 10, 16, 24, 40, 51, 61},
            {12, 12, 14, 19, 26, 58, 60, 55},
            {14, 13, 16, 24, 40, 57, 69, 56},
            {14, 17, 22, 29, 51, 87, 80, 62},
            {18, 22, 37, 56, 68, 109, 103, 77},
            {24, 35, 55, 64, 81, 104, 113, 92},
            {49, 64, 78, 87, 103, 121, 120, 101},
            {72, 92, 95, 98, 112, 100, 103, 99},
    };


    /**
     * @brief Taula de quantització de la component U i V
     */
    public static int[][] quantChrominance = new int[][]{
            {17, 18, 24, 47, 99, 99, 99, 99},
            {18, 21, 26, 66, 99, 99, 99, 99},
            {24, 26, 56, 99, 99, 99, 99, 99},
            {47, 66, 99, 99, 99, 99, 99, 99},
            {99, 99, 99, 99, 99, 99, 99, 99},
            {99, 99, 99, 99, 99, 99, 99, 99},
            {99, 99, 99, 99, 99, 99, 99, 99},
            {99, 99, 99, 99, 99, 99, 99, 99},
    };


    /**
     * @brief Ordre en zigzag
     */
    public static int[] zigzag = {
            0, 1, 8, 16, 9, 2, 3, 10,
            17, 24, 32, 25, 18, 11, 4, 5,
            12, 19, 26, 33, 40, 48, 41, 34,
            27, 20, 13, 6, 7, 14, 21, 28,
            35, 42, 49, 56, 57, 50, 43, 36,
            29, 22, 15, 23, 30, 37, 44, 51,
            58, 59, 52, 45, 38, 31, 39, 46,
            53, 60, 61, 54, 47, 55, 62, 63,
    };

    /**
     * @brief Funció principal per a la compressió d'imatges mitjançant JPEG
     * @param altura   Altura de l'imatge
     * @param anchura  Amplitud de l'imatge
     * @param matR     Matriu de Color Vermell
     * @param matG     Matriu de Color Verd
     * @param matB     Matriu de color Blau
     * @param calitat
       Qualitat de la compressio
     * @param path_out Path de sortida
     * @return Retorna ArrayList d'ArrayList amb l'imatge codificada
     * @throws IOException
     */


    public static ArrayList<ArrayList<ArrayList<Integer>>> CodificarJpeg(int altura, int anchura, ArrayList<int[][]> matR, ArrayList<int[][]> matG, ArrayList<int[][]> matB, int calitat, String path_out) throws IOException {

        CalitatQuantitzacio(calitat);
        iniciaDC();
        int block = (int) Math.ceil(((double) altura * (double) anchura) / 64);

        ArrayList<ArrayList<Integer>> arraymatR = new ArrayList<ArrayList<Integer>>(block);
        ArrayList<ArrayList<Integer>> arraymatG = new ArrayList<ArrayList<Integer>>(block);
        ArrayList<ArrayList<Integer>> arraymatB = new ArrayList<ArrayList<Integer>>(block);
        
        for (int index = 0; index < matR.size(); ++index) {

            int[][] matriz8R = new int[8][8];
            int[][] matriz8G = new int[8][8];
            int[][] matriz8B = new int[8][8];

            matriz8R = matR.get(index);
            matriz8G = matG.get(index);
            matriz8B = matB.get(index);

            matriz8R = forwardDCT(matriz8R);
            matriz8G = forwardDCT(matriz8G);
            matriz8B = forwardDCT(matriz8B);

            matriz8R = Quantization(matriz8R, 'L', calitat);
            matriz8G = Quantization(matriz8G, 'R', calitat);
            matriz8B = Quantization(matriz8B, 'G', calitat);

            ArrayList<Integer> array8R = zigzag(matriz8R);
            ArrayList<Integer> array8G = zigzag(matriz8G);
            ArrayList<Integer> array8B = zigzag(matriz8B);

            arraymatR.add(array8R);
            arraymatG.add(array8G);
            arraymatB.add(array8B);

        }

        ArrayList<ArrayList<ArrayList<Integer>>> ArrayFinal = new ArrayList<ArrayList<ArrayList<Integer>>>();
        ArrayFinal.add(arraymatR);
        ArrayFinal.add(arraymatG);
        ArrayFinal.add(arraymatB);
        return ArrayFinal;
    }

    /**
     * @brief Donada una calitat, transforma les taules de quantització per a aquella calitat
     * @param calitat: calitat desitjada amb un interval de l'1 al 100.
     */
    private static void AplicaQualitatQuantization(int calitat) {
        int S;
        if (calitat < 50) S = 5000 / calitat;
        else S = 200 - 2 * calitat;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                int interLu = Math.round((S * quantLuminance[i][j] + 50) / 100);
                if (interLu == 0) interLu = 1;
                int interCh = Math.round((S * quantChrominance[i][j] + 50) / 100);
                if (interCh == 0) interCh = 1;

                quantLuminance[i][j] = interLu;
                quantChrominance[i][j] = interCh;

            }
        }


    }

    /**
     * @brief Inicialitza els coeficients per al càlcul de la DCT.
     * @param c ens entra la matriu d'inicialització
     * @return retorna els coeficients inicialitzats.
     */
    public static double[][] initCoefficients(double[][] c) {
        final int N = c.length;
        final double value = 1 / Math.sqrt(2.0);

        for (int i = 1; i < N; i++) {
            for (int j = 1; j < N; j++) {
                c[i][j] = 1;
            }
        }

        for (int i = 0; i < N; i++) {
            c[i][0] = value;
            c[0][i] = value;
        }
        c[0][0] = 0.5;
        return c;
    }

    /**
     * @brief Realitza la operació de DCT
     * @param input: entra la matriu a la que volem aplicar DCT
     * @return retorna la matriu un cop ha sigut aplicada la DCT.
     */
    public static int[][] forwardDCT(int[][] input) {
        final int N = input.length;
        final double mathPI = Math.PI;
        final int halfN = N / 2;
        final double doubN = 2.0 * N;

        double[][] c = new double[N][N];
        c = initCoefficients(c);

        int[][] output = new int[N][N];

        for (int u = 0; u < N; u++) {
            double temp_u = u * mathPI;
            for (int v = 0; v < N; v++) {
                double temp_v = v * mathPI;
                double sum = 0.0;
                for (int x = 0; x < N; x++) {
                    int temp_x = 2 * x + 1;
                    for (int y = 0; y < N; y++) {
                        sum += input[x][y] * Math.cos((temp_x / doubN) * temp_u) * Math.cos(((2 * y + 1) / doubN) * temp_v);
                    }
                }
                sum *= c[u][v] / halfN;
                output[u][v] = (int) sum;
            }
        }
        return output;
    }


    /**
     * @brief Realitza la operació de quantització.
     * @param calitat
     * @param arrayOP: ens entra la matriu que volem quantitzar
     * @param op:      també ens entra el tipus de component que és.
     * @return : retornem la matriu amb la quantització aplicada
     */
    public static int[][] Quantization(int[][] arrayOP, char op, int calitat) {

        CalitatQuantitzacio(calitat);

        if (op == 'L') {

            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {


                    if (quantLuminance[i][j] == 0) {
                        quantLuminance[i][j] = 1;
                    }
                    arrayOP[i][j] = Math.round(arrayOP[i][j] / (quantLuminance[i][j]));

                }

            }

        } else {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (quantChrominance[i][j] == 0) quantChrominance[i][j] = 1;
                    arrayOP[i][j] = Math.round(arrayOP[i][j] / (quantChrominance[i][j]));

                }
            }

        }


        return arrayOP;

    }

    /**
     * @brief Donada una matriu d'entrada aplica l'ordre zigzag i la converteix a un arrayList
     * @param matriz8 : matriu d'entrada que volem zigzag
     * @return : retorna la matriu zigzag
     */
    public static ArrayList<Integer> zigzag(int[][] matriz8) {

        int[] array = new int[64];
        int z = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                array[zigzag[z]] = matriz8[i][j];
                z++;
            }
        }


        ArrayList<Integer> intList = new ArrayList<Integer>(array.length);
        for (int i : array) {
            intList.add(i);
        }

        return intList;

    }

    /**
     * @brief Funció principal per a la descompressió d'imatges mitjançant JPEG
     * @param arraymatR : ArrayList d'ArrayList de valors de la component R
     * @param arraymatG : ArrayList d'ArrayList de valors de la component G
     * @param arraymatB : ArrayList d'ArrayList de valors de la component B
     * @param calitat   : enter que ens indica la calitat desitjada dintre d'un rang 1-100
     * @return retorna un ArrayList dels valors a escriure a persistencia
     * @throws IOException
     */


    public static ArrayList<ArrayList<Integer>> Descomprimeix(ArrayList<ArrayList<Integer>> arraymatR, ArrayList<ArrayList<Integer>> arraymatG, ArrayList<ArrayList<Integer>> arraymatB, int calitat) throws IOException {
        
        CalitatQuantitzacio(calitat);

        ArrayList<Integer> arrayR = new ArrayList<>();
        ArrayList<Integer> arrayG = new ArrayList<>();
        ArrayList<Integer> arrayB = new ArrayList<>();
        ///tenim tots els valors, comencem a tornar enrere

        for (int k = 0; k < arraymatR.size(); k++) {

            //convertir de array a matriz
            int[][] matriz8R = new int[8][8];
            int[][] matriz8G = new int[8][8];
            int[][] matriz8B = new int[8][8];

            matriz8R = ArrayToMat(arraymatR.get(k));
            matriz8G = ArrayToMat(arraymatG.get(k));
            matriz8B = ArrayToMat(arraymatB.get(k));

            matriz8R = Desquantization(matriz8R, 'L', calitat);
            matriz8G = Desquantization(matriz8G, 'R', calitat);
            matriz8B = Desquantization(matriz8B, 'G', calitat);

            matriz8R = inverseDCT(matriz8R);
            matriz8G = inverseDCT(matriz8G);
            matriz8B = inverseDCT(matriz8B);


            for (int h = 0; h < 8; h++) {
                for (int d = 0; d < 8; d++) {

                    int Y = matriz8R[h][d];
                    int Cb = matriz8G[h][d];
                    int Cr = matriz8B[h][d];

                    int R = (int) (Math.round(Y + 1.402 * (Cr - 128)));
                    int G = (int) (Math.round(Y - 0.344136 * (Cb - 128) - 0.714136 * (Cr - 128)));
                    int B = (int) Math.round(Y + 1.772 * (Cb - 128));

                    if (R < 0) R = 0;
                    if (G < 0) G = 0;
                    if (B < 0) B = 0;
                    if (R > 255) R = 255;
                    if (G > 255) G = 255;
                    if (B > 255) B = 255;

                    matriz8R[h][d] = R;
                    matriz8G[h][d] = G;
                    matriz8B[h][d] = B;


                    arrayR.add(R);
                    arrayG.add(G);
                    arrayB.add(B);
                }
            }
        }
        ArrayList<ArrayList<Integer>> ArrayFinal = new ArrayList<ArrayList<Integer>>();
        ArrayFinal.add(arrayR);
        ArrayFinal.add(arrayG);
        ArrayFinal.add(arrayB);
        return ArrayFinal;
    }

    /**
     * Funció auxiliar que donat un ArrayList d'integers, els converteix a codi huffman
     * @param auxR: Ha de ser un ArrayList d'enters
     * @param C:    caracter que identifica si es tracta d'un component de Lluminància o de Chrominància.
     * @return retorna l'string de l'arraylist a codi huffman
     */
    private static String converteixACode(ArrayList<Integer> auxR, char C) {
        // TODO Auto-generated method stub
        int i = 0, rle = 0, size = 0;
        String code = "", code1 = "";
        boolean es16;

        AbstractMap.SimpleEntry<Integer, Integer> AbstractMapSimpleEntry16 = new AbstractMap.SimpleEntry<>(16, 0);
        AbstractMap.SimpleEntry<Integer, Integer> AbstractMapSimpleEntry0 = new AbstractMap.SimpleEntry<>(0, 0);

        //Primer codifiquem DC
        code += codificaDC(auxR.get(0));
        for (i = 1; i < auxR.size(); i++) {

            es16 = false; //Comencem a 1, no ens interesa DC
            while ((auxR.get(i)) == 0) {
                rle++;
                if (totsonzeros(i, auxR)) {//TENIM EOB
                    if (C == 'L') code1 = lookupTableACL.get(AbstractMapSimpleEntry0);
                    else code1 = lookupTableACC.get(AbstractMapSimpleEntry0);
                    try {
                        code1 = code1 + "00000000".substring(code1.length());
                    } catch (Exception e) {
                        System.out.println();
                    }
                    code += code1;
                    break;
                } else if (rle == 16) {
                    if (C == 'L') code1 = lookupTableACL.get(AbstractMapSimpleEntry16);
                    else code1 = lookupTableACC.get(AbstractMapSimpleEntry16);
                    code += code1;
                    es16 = true;
                    break;
                }
                if (i < 63) i++;
                else break;
            }
            if (totsonzeros(i, auxR)) break;

            if (!es16) {
                size = (int) Math.floor(log(auxR.get(i)) + 1);
                if (size == -2147483648) size = 0;//out of range
                AbstractMap.SimpleEntry<Integer, Integer> AbstractMapSimpleEntry = new AbstractMap.SimpleEntry<>(rle, size);

                if (C == 'L') code1 = lookupTableACL.get(AbstractMapSimpleEntry);//Copiem el valor (x,x)
                else code1 = lookupTableACC.get(AbstractMapSimpleEntry);
                code1 += ConvierteABIN(auxR.get(i));// copiem el valor en integer
                if (i == auxR.size() - 1) {//en cas de no eob, doncs el valor serà l'últim
                    if (code1.length() <= 8) {
                        code1 = code1 + "00000000".substring(code1.length());
                    } else if (code1.length() <= 16) {
                        code1 = code1 + "0000000000000000".substring(code1.length());
                    }


                }
                code += code1;
            }
            rle = 0;
        }
        return code;
    }

    /**
     * @brief Donat un Integer, el converteix a Binary String
     * @param integer: enter que volem convertir
     * @return : retornem el Binary String convertit
     */
    private static String ConvierteABIN(Integer integer) {
        // TODO Auto-generated method stub
        if (integer > 0) return Integer.toBinaryString(integer);
        else if (integer < 0) {
            integer = integer * -1;
            String val = Integer.toBinaryString(integer);
            val = val.replace('0', '2').replace('1', '0').replace('2', '1');
            return val;
        }

        return "0";
    }


    /**
     * Funció auxiliar que codifica un enter que és el DC.
     * @param integer : valor DC que volem codificar
     * @return : retornem un String amb la representació del valor DC i la seva mida.
     */
    private static String codificaDC(Integer integer) {
        // TODO Auto-generated method stub
        if (integer == 0) return "00";
        String code = "";
        int size = (int) Math.floor(log(integer) + 1);
        code += DCValues.get(size);
        code += buscataulaDC(integer);
        return code;

    }

    /**
     * Funció auxiliar que donat un integer el converteix a BinaryString
     * @param integer : enter d'entrada
     * @return : retornem el String amb la representació del valor DC.
     */
    private static String buscataulaDC(Integer integer) {
        String ret = "";

        if (integer > 0) {
            ret = Integer.toBinaryString(integer);

            return ret;
        } else if (integer < 0) {
            integer = integer * -1;
            ret = Integer.toBinaryString(integer);
            ret = ret.replace('0', '2').replace('1', '0').replace('2', '1');
            return ret;
        }
        return "00";

    }

    /**
     * @brief Donat un enter calcula el seu logaritme en Base 2
     * @param x: enter d'entrada que volem calcular el logaritme
     * @return : retorna el enter una vegada hem fet el càlcul.
     */
    public static int log(int x) {
        x = (int) Math.abs(x);
        int res = (int) (Math.abs(Math.log(x) / Math.log(2) + 1e-10));
        return res;
    }

    /**
     * @brief Donat un index i un ArrayList, mira si desde la posició de l'index fins al final, tot son 0's.
     * @param j           : index de l'ArrayList
     * @param arrayactual : ArrayList que volem mirar
     * @return
     */
    private static boolean totsonzeros(int j, ArrayList<Integer> arrayactual) {
        for (int i = j; i < arrayactual.size(); i++) {
            if (arrayactual.get(i) != 0) return false;
        }
        return true;
    }


    /**
     * @brief Donada una calitat, transforma les taules de quantització per a aquella calitat
     * @param calitat: calitat desitjada amb un interval de l'1 al 100.
     */
    private static void CalitatQuantitzacio(int calitat) {


        int S;
        if (calitat < 50) S = 5000 / calitat;
        else S = 200 - 2 * calitat;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                int interLu = Math.round((S * quantLuminance[i][j] + 50) / 100);
                if (interLu == 0) interLu = 1;
                int interCh = Math.round((S * quantChrominance[i][j] + 50) / 100);
                if (interCh == 0) interCh = 1;

                quantLuminance[i][j] = interLu;
                quantChrominance[i][j] = interCh;

            }
        }

    }
    
    

    /**
     * @brief Converteix un ArrayList a Matriu
     * @param arrayList : Arraylist que volem convertir
     * @return : matriu d'enters provinent de l'arrayList
     */
    private static int[][] ArrayToMat(ArrayList<Integer> arrayList) {

        int[][] mat = new int[8][8];
        int z = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                mat[i][j] = arrayList.get(zigzag[z]);
                z++;
            }
        }

        return mat;
    }

    /**
     * @brief Donat un array d'enters desquantitza segons el caràcter d'entrada
     * @param arrayOP : matriu d'entrada que volem desquantitzar
     * @param op : caracter que ens determina si és una matriu de lluminancia o chrominancia
     * @param calitat
     * @return
     */
    public static int[][] Desquantization(int[][] arrayOP, char op, int calitat) {

        CalitatQuantitzacio(calitat);

        if (op == 'L') {

            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {


                    arrayOP[i][j] = Math.round(arrayOP[i][j] * (quantLuminance[i][j]));
                }

            }

        } else {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {

                    arrayOP[i][j] = Math.round(arrayOP[i][j] * (quantChrominance[i][j]));
                }
            }

        }

        return arrayOP;
    }

    /**
     * @brief  Funció que aplica la transformada del cosinus inversa
     * @param input
     * @return
     */
    public static int[][] inverseDCT(int[][] input) {
        final int N = input.length;
        final double mathPI = Math.PI;
        final int halfN = N / 2;
        final double doubN = 2.0 * N;

        double[][] c = new double[N][N];
        c = initCoefficients(c);

        int[][] output = new int[N][N];


        for (int x = 0; x < N; x++) {
            int temp_x = 2 * x + 1;
            for (int y = 0; y < N; y++) {
                int temp_y = 2 * y + 1;
                double sum = 0.0;
                for (int u = 0; u < N; u++) {
                    double temp_u = u * mathPI;
                    for (int v = 0; v < N; v++) {
                        sum += c[u][v] * input[u][v] * Math.cos((temp_x / doubN) * temp_u) * Math.cos((temp_y / doubN) * v * mathPI);
                    }
                }
                sum /= halfN;
                output[x][y] = (int) sum;
            }
        }
        return output;
    }

    /**
     * @brief Donat un ArrayList el codifica mitjançant les taules de Huffman Predefinides.
     * @param arraymatR : ArrayList d'ArrayLists del paràmetre R
     * @param arraymatG : ArrayList d'ArrayLists del paràmetre G
     * @param arraymatB : ArrayList d'ArrayLists del paràmetre B
     * @param calitat   : calitat desijtada dintre d'un paràmetre 1-100
     * @return retorna un arraylist d'integers amb la imatge codificada
     * @throws IOException
     */
    public static ArrayList<Integer> CodificarTaules(ArrayList<ArrayList<Integer>> arraymatR, ArrayList<ArrayList<Integer>> arraymatG, ArrayList<ArrayList<Integer>> arraymatB, int calitat) throws IOException {

        CalitatQuantitzacio(calitat);
        ArrayList<Integer> EnviarAEscriure = new ArrayList<Integer>();

        ArrayList<Integer> auxR = new ArrayList<Integer>();
        ArrayList<Integer> auxG = new ArrayList<Integer>();
        ArrayList<Integer> auxB = new ArrayList<Integer>();
        for (int i = 0; i < arraymatR.size(); i++) {
            auxR = arraymatR.get(i); //tenim la primera matriu R
            auxG = arraymatG.get(i);
            auxB = arraymatB.get(i);
            String codeR = converteixACode(auxR, 'L');
            for (String str : codeR.split("(?<=\\G.{8})")) {
                if (str == "00000000") break;
                if (str.length() != 8) {
                    str = str + "00000000".substring(str.length());
                }
                int val = Integer.parseInt(str, 2);
                EnviarAEscriure.add(val);


            }

            EnviarAEscriure.add(0xFF);
            EnviarAEscriure.add(0xF4);
            EnviarAEscriure.add(0xF4);
            EnviarAEscriure.add(0xFF);


            String codeG = converteixACode(auxG, 'C');
            for (String str : codeG.split("(?<=\\G.{8})")) {
                if (str == "00000000") break;
                if (str.length() != 8) {
                    str = str + "00000000".substring(str.length());
                }
                int val = Integer.parseInt(str, 2);
                EnviarAEscriure.add(val);

            }

            EnviarAEscriure.add(0xFF);
            EnviarAEscriure.add(0xF4);
            EnviarAEscriure.add(0xF4);
            EnviarAEscriure.add(0xFF);

            String codeB = converteixACode(auxB, 'C');
            for (String str : codeB.split("(?<=\\G.{8})")) {
                if (str == "00000000") break;
                if (str.length() != 8) {
                    str = str + "00000000".substring(str.length());
                }
                int val = Integer.parseInt(str, 2);
                EnviarAEscriure.add(val);

            }
            EnviarAEscriure.add(0xFF);
            EnviarAEscriure.add(0xF4);
            EnviarAEscriure.add(0xF4);
            EnviarAEscriure.add(0xFF);
        }
        EnviarAEscriure.add(calitat);

        return EnviarAEscriure;

    }


    static int iglobal = 0;

    /**
     * @brief Funcio que descomprimeix donat l'array del principi dels codis huffman
     * @param arrayDeStrings : array amb tots els codis huffmans en tipus String
     * @return retorna un arraylist dels arraylist de les components R, G, B
     */
    public static ArrayList<ArrayList<ArrayList<Integer>>> descomprimir(ArrayList<String> arrayDeStrings) {

        iniciaDC();
        ArrayList<ArrayList<Integer>> arraymatR;
        ArrayList<ArrayList<Integer>> arraymatG = null;
        ArrayList<ArrayList<Integer>> arraymatB;

        int iteratorArray = 0;
        int altura = Integer.parseInt(arrayDeStrings.get(0));
        int anchura = Integer.parseInt(arrayDeStrings.get(1));
        int block = Integer.parseInt(arrayDeStrings.get(2));
        arraymatR = new ArrayList<ArrayList<Integer>>(block);
        arraymatG = new ArrayList<ArrayList<Integer>>(block);
        arraymatB = new ArrayList<ArrayList<Integer>>(block);
        int b = 3;
        String entradatext = "";
        for (int blocs = 0; blocs < block * 3; blocs++) {
            ArrayList<Integer> arrayR = new ArrayList<Integer>(64);
            entradatext = arrayDeStrings.get(b);
            b++;
            boolean trobat = false, es0 = false;
            int size = 0, size1 = 0, p;
            String auxDC = entradatext, auxauxDC = "";
            for (p = 1; trobat == false; p++) {
                try{
                    auxauxDC = auxDC.substring(0, p);
                }
                catch(Exception e){
                    System.out.println("ep!");
                }
                for (int i : DCValues.keySet()) {
                    String val = DCValues.get(i);
                    if (val.equals(auxauxDC)) {
                        if (i == 0) size1 = 2;
                        else if (i == -1) { //DC == 0
                            es0 = true;
                            break;
                        } else {
                            if (i == -1) i = 0;
                            size = i;
                            size1 = i;
                        }
                        trobat = true;
                        break;
                    }

                }

                if (trobat || es0) break;
            }
            int DC = 0;
            if (es0) {
                entradatext = entradatext.substring(2);
            } else {
                entradatext = entradatext.substring(auxauxDC.length() + size);
                DC = 0;
                if (size == 0) auxDC = auxauxDC;
                else {
                    try {

                        auxDC = auxDC.substring(auxauxDC.length(), auxauxDC.length() + size1);
                    } catch (Exception e) {
                        System.out.println();
                    }
                    if (auxDC.charAt(0) == '1') {
                        DC = Integer.parseInt(auxDC, 2);
                    } else { //Valor negatiu, lhem de flipejar	00-> 11
                        String val;
                        val = auxDC.replace('0', '2').replace('1', '0').replace('2', '1');//canviem 1's per 0's
                        int numeroneg = Integer.parseInt(val, 2);
                        numeroneg = numeroneg * -1;
                        DC = numeroneg;
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


            AbstractMap.SimpleEntry<Integer, Integer> AbstractMapSimpleEntry = new AbstractMap.SimpleEntry<Integer, Integer>(0, 0);
            for (int k = 1; entradatext.length() > 0 && !entradatext.equals("0") && iglobal < 64; k++) {

                String shiftedi = entradatext.substring(0, k);//busqueda bit a bit

                if ((AbstractMapSimpleEntry = buscaString(shiftedi, iteratorArray)) != null) {

                    int p1 = AbstractMapSimpleEntry.getKey();
                    int p2 = AbstractMapSimpleEntry.getValue();
                    trobat = true;
                    String valor = null;
                    if (p1 == 0 && p2 == 0) {
                        valor = "0";
                        boolean fi = tractaAC(arrayR, p1, p2, 0, valor);
                        if (fi) entradatext = "";
                        break;
                    } else if (p1 == 16) {
                        entradatext = entradatext.substring(k);
                        valor = "0";
                        boolean fi = tractaAC(arrayR, p1, p2, 0, valor);
                        if (fi) entradatext = "";
                        k = 0;
                    } else {
                        valor = entradatext.substring(k, k + p2);
                        entradatext = entradatext.substring(k + p2);
                        int value = 0;
                        value = Integer.parseInt(valor, 2);
                        boolean fi = tractaAC(arrayR, p1, p2, value, valor);
                        if (fi) entradatext = "";
                        k = 0;
                    }
                }

            }


            //Ens falta tractar AC

            //Hem acabat
            if (iteratorArray == 0) {
                arraymatR.add(arrayR);
                iteratorArray++;
                iglobal = 0;

            } else if (iteratorArray == 1) {
                arraymatG.add(arrayR);
                iteratorArray++;
                iglobal = 0;


            } else if (iteratorArray == 2) {
                arraymatB.add(arrayR);
                iteratorArray = 0;
                iglobal = 0;

            }
        }

        int calitat = Integer.parseInt(arrayDeStrings.get(b));
        CalitatQuantitzacio(calitat);

        ArrayList<ArrayList<Integer>> calitatimatge  = new   ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> calitatarray = new ArrayList<Integer>();
        calitatarray.add(calitat);
        calitatimatge.add(calitatarray);


        ArrayList<ArrayList<ArrayList<Integer>>> ArrayFinal = new ArrayList<ArrayList<ArrayList<Integer>>>();
        ArrayFinal.add(arraymatR);
        ArrayFinal.add(arraymatG);
        ArrayFinal.add(arraymatB);
        ArrayFinal.add(calitatimatge);

        return ArrayFinal;

    }

    /**
     * @brief Busca si un String d'entrada coincideix amb alguna entrada de les taules de Huffman
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
     * @brief  Funcio que tracta i omple l'arrayList d'entrada segons els valors d'entrada
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



}




