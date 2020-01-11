# Compressor

Java program able to compress and decompress .txt and .ppm files.

## What it does
Compress .txt and .ppm files to a .compressed file and decompress it to the original file using JPEG and LZSS algorithm.

## How I built it
We developed it using java SDK8 and AWT and Swing for the front-end.

## Challenges I ran into
##### Learn Java.
##### Search information about the JPEG algorithm.
##### Learn about UI/UX and front-end AWT Java.

## How to run it?
```
cd /src/
```
```
make class
```
```
make run
```
## How JPEG Algorithm Works?


#### English 
To achieve the JPEG compression, several algorithms applied to the input image had to be followed. First of all, the conversion of the RGB components to Y'CbCr is performed. Discrete cosine transform and later the quantization tables are applied. These quantization tables have been modified using the quality value introduced at the beginning, following the standard formulas, and Huffman coding is finally applied using the predefined tables. The codification is done using Strings divisions and checking that they are not equivalent to some Huffman code. The coding was done as follows:

The DC block is coded as:
```
Symbol 1: (Size, Amplitude):
- Size -> Number of bits to represent the DC number ((Huffman Encoded))
- Amplitude - X bit representation
```
The 63 AC blocks are coded as:
```
Symbol 1 = (RLE, SIZE):
-RLE-> Name of zeros preceding the nonzero number AC
-Size -> Number of bits to represent AC number ((Huffman Encoded))
Symbol 2 = (Amplitude) - X-bit representation
```
In addition, the bits had to be left-padded for the correct encoding. This is, filling with zeros, because when we convert a BinaryString to int, to save it in the archive, the zeros on the left are "lost" and when we read we did not have them. Finally, a 6 Byte (0xFF) mark was put to mark the end of the block. Through trial-and-error, it has been found that 6 Bytes are enough for this script to avoid accidental errors. Lastly, the quality value is saved in the compressed, so it will allow us to reconstruct the decomposition quantization tables. Following these steps, we obtain an encoded file that later, using the same steps, but vice versa, we can convert to a visible .ppm file.

For the decompressed part, the algorithm first search until it founds the previous mark defined (6 Bytes in (0xFF)). Once we have all the code we had to look for the value of the DC component following the coding explained above. Then we look for values until we find a **1010** that indicates that we have reached the end of the array. And once we have all the matrices filled, we proceed to apply the inverse operations, such as the dequantization, the transformation of the inverse cosine, etc. Finally it is saved with a .ppm file.

The main data structures have been: 2D Array, ArrayList and AbstractMap (Pairs).


#### Catalan
Per aconseguir la compressió JPEG s’ha hagut de seguir diversos algorismes
aplicats a la imatge d’entrada. Primer de tot es fa la conversió de components RGB
A Y’CbCr. Seguidament s’aplica la DCT, per a més tard aplicar les taules de
quantització. Aquestes taules de quantització han sigut modificades mitjançant el
valor de calitat introduït al principi, seguint unes formules disponibles a un article de
Rémi Cogranne .Finalment s’aplica la codificació Huffman mitjançant les taules
predefinides.
Aquesta codificació es fa mitjançant divisions de Strings i comprovant que aquests no siguin
equivalents a algun codi de Huffman. La codificació s’ha fet de la següent manera:

El bloc DC es codifica com:
```
Symbol 1 : (Size, Amplitude) :
- Size -> Nombre de bits per representar el numero DC((Huffman Encoded))
- Amplitude : representació en bits de X
```
Els 63 blocs AC es codifiquen com:
```
Symbol 1 = (RLE, SIZE):
-RLE-> Nombre de zeros precedint al numero nozero AC.
-Size -> Nombre de bits per representar el numero AC((Huffman Encoded))
Symbol 2 = (Amplitude) : representació en bits de X
```
A més, per a la correcta codificació, s’ha hagut de “left-paddejar” els bits. És a dir,
omplir de zeros, ja que al convertir un BinaryString a int per a guardar-ho a l’arxiu, es
perdien els zeros de l’esquerra i al llegir no els teníem. Finalment, per marcar el final
del Bloc, s’ha ficat una marca de 6 Bytes (0xFF). Mitjançant prova-error, s’ha vist que
amb 6 Bytes és suficient per a que accidentalment, existeixin aquesta seqüència
dintre de l’arxiu comprimit. Per acabar es guarda el valor de qualitat, ja que així
podrem reconstruir les taules de quantització a la descompressió. Seguint aquests
passos obtenim un arxiu codificat que més tard aplicant els mateixos passos però a
la inversa, podrem convertir a un arxiu .ppm visible.

Per a la reconversió, primer de tot s’ha hagut de buscar fins trobar la marca anterior
definida( 6 Bytes a (0xFF)). Un cop tenim tot el codi s’ha hagut de buscar el valor del
component DC seguint la codificació explicada anteriorment. Després es van buscant valors
fins trobar un 1010 que ens indica que hem arribat a final de la matriu, o directament els 64
valors. I un cop tenim totes les matrius omplertes, es procedeix a aplicar les operacions
inverses, tal com la desquantització, la transformada del cosinus inversa, etc.
Finalment es guarda amb un arxiu .ppm.

Les principals estructures de dades han sigut: Array de 2 dimensions, ArrayList i
AbstractMap (Pairs).



