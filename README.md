# Compressor

Java programm able to compress .txt and .ppm files.

## What it does
Compress .txt and .ppm files to a .compressed file and it decompress it to the original file.

## How I built it
We developed using java sdk 8.

## Challenges I ran into
##### Learn Java.
##### Search information about the JPEG algorithm.
##### Learn about UI/UX and frontend AWT Java.

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

(cat)

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



