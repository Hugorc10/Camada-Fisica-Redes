package model;

import java.util.Arrays;

public class CamadaFisicaTransmissoraModel {

    /**
     * Metodo: codificarParaBinario
     * Funcao: Codifica um vetor de bytes contendo os codigos ASCII de caracteres alfabeticos
     * para a codificacao Binaria e insere em um array de inteiros em forma de bits
     *
     * @param quadro = array de bytes contendo codigos ASCII de cada caracter alfabetico
     * @return int[]
     */
    public static int[] codificarParaBinario(int[] quadro) {
        System.out.print("\nCamada Fisica Transmissora Codificacao Binaria\n");
        System.out.print("Imprimindo comprimento do quadro: " + quadro.length + "\n");

        // Variavel que ira receber o comprimento (length) do array "quadro" dividido por quatro
        int n = quadro.length / 4;

        // Verifica  se o comprimento do quadro divido por quatro tem resto diferente de 0
        if (quadro.length % 4 != 0)
            n++;

        int[] bits = new int[n]; // Array que ira conter os inteiros com os bits armazenados no array "quadro"

        int index = 0;

        // Realiza loop ate o i ser menor que o comprimento (length) do vetor bits
        for (int i = 0; i < bits.length; i++) {
            bits[i] = quadro[index];
            index++;

            for (int y = 0; y < 3; y++) {
                if (index < quadro.length) {
                    bits[i] = bits[i] << 8; // Desloca 8 bits a esquerda
                    bits[i] = bits[i] | quadro[index]; // Compara os bits do array 'quadro' para o array 'bits'
                    index++;
                } // fim do if
            } // fim do for
        } // Fim do for

        System.out.print("Imprimindo bits: " + Arrays.toString(bits) + "\n");

        return bits;
    } // Fim do metodo codificarParaBinario

    /**
     * Metodo: codificarParaManchester
     * Funcao: codifica um vetor de bytes contendo os codigos ASCII de caracteres alfabeticos
     * para a codificacao Manchester e insere em um array de inteiros em forma de bits
     *
     * @param quadro = array de bytes contendo codigos ASCII de cada caracter alfabetico
     * @return int[]
     */
    public static int[] codificarParaManchester(int[] quadro) {
        System.out.print("\nCamada Fisica Transmissora Codificacao Manchester\n");

        // Variavel que ira receber o comprimento (length) do array "quadro" dividido por dois
        int n = quadro.length / 2;

        // Verifica o comprimento do quadro divido por quatro tiver resto diferente de 0
        if (quadro.length % 2 != 0) {
            n++;
        }

        // Array que ira conter os inteiros com os bits armazenados no array "quadro"
        int[] bits = new int[n];

        int aux = 0; // Variavel auxiliar
        for (int x = 0; x < bits.length; x++) {
            for (int y = 0; y < 2; y++) {
                if (aux < quadro.length) {
                    int temp = quadro[aux];
                    for (int i = 7; i >= 0; i--) {
                        bits[x] = bits[x] << 1;
                        int mask = 1 << i; // Desloca 7 bits a esquerda ate 0
                        if (Integer.toBinaryString(temp & mask).charAt(0) == '0') {
                            bits[x] = bits[x] | 1; // Adiciona o bit 1
                        } else if (Integer.toBinaryString(temp & mask).charAt(0) == '1') {
                            bits[x] = bits[x] | 0; // Adiciona o bit 0
                        }
                        int t = Integer.parseInt(String.valueOf(Integer.toBinaryString(temp & mask).charAt(0)));
                        bits[x] = bits[x] << 1;
                        bits[x] = bits[x] | t;
                    }
                    aux++;
                }
            }
        }

        return bits;
    } // Fim do metodo codificarParaManchester

    /**
     * Metodo: codificarParaManchesterDiferencial
     * Funcao: Codifica um vetor de bytes contendo os codigos ASCII de caracteres alfabeticos
     * para a codificacao Manchester Diferencial e insere em um array de inteiros em forma de bits
     *
     * @param quadro = array de bytes contendo codigos ASCII de cada caracter alfabetico
     * @return int[]
     */
    public static int[] codificarParaManchesterDiferencial(int[] quadro) {
        System.out.println("\nCamada Fisica Transmissora Codificacao Manchester Diferencial\n");

        // Variavel que ira receber o comprimento (length) do array "quadro" dividido por dois
        int n = quadro.length / 2;

        // Verifica o comprimento do quadro divido por quatro tiver resto diferente de 0
        if (quadro.length % 2 != 0) {
            n++;
        }

        // Array que ira conter os inteiros com os bits armazenados no array "quadro"
        int[] bits = new int[n];

        int aux = 0; // Variavel auxiliar
        for (int x = 0; x < bits.length; x++) {
            for (int y = 0; y < 2; y++) {
                if (aux < quadro.length) {
                    int temp = quadro[aux];

                    for (int i = 7; i >= 0; i--) {
                        bits[x] = bits[x] << 1;
                        int mask = 1 << i;
                        if (Integer.toBinaryString(temp & mask).charAt(0) == '0') {
                            bits[x] = bits[x] | 1;
                        } else if (Integer.toBinaryString(temp & mask).charAt(0) == '1') {
                            bits[x] = bits[x] | 0;
                        }
                        int t = Integer.parseInt(String.valueOf(Integer.toBinaryString(temp & mask).charAt(0)));
                        bits[x] = bits[x] << 1;
                        bits[x] = bits[x] | t;
                    }
                    aux++;
                } // fim do if
            } // fim do for
        } // fim do for

        return bits;
    } // Fim do metodo codificarParaManchesterDiferencial
}
