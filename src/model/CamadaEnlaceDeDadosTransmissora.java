package model;

import controller.AplicacaoTransmissoraController;
import controller.CamadaFisicaTransmissoraController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CamadaEnlaceDeDadosTransmissora {

    /**
     * Metodo: enviarQuadrosDesenquadrados
     * Funcao:
     *
     * @param quadro
     */
    public void enviarQuadrosDesenquadrados(int[] quadro) {
        System.out.print("\nEnviar Quadros Desenquadrados\n");
        int[] quadroEnquadrado = camadaEnlaceDadosTransmissoraEnquadramento(quadro);
        System.out.print("Imprimindo Quadros Desenquadrados: " + Arrays.toString(quadroEnquadrado) + "\n");

        separarQuadros(quadroEnquadrado);
    }

    /**
     * Metodo: separarQuadros
     * Funcao:
     *
     * @param quadroDesenquadrado
     */
    private void separarQuadros(int[] quadroDesenquadrado) {
        System.out.print("\nSeparar Quadros\n");
        switch (AplicacaoTransmissoraController.tipoDeEnquadramento) {
            case 0:
                int x = 0;
                int[] quadro;
                int index;
                while (x < quadroDesenquadrado.length) {
                    int cont = 0;
                    index = quadroDesenquadrado[x];
                    quadro = new int[index];
                    quadro[cont] = index;
                    cont++;
                    x++;

                    for (int y = 0; y < index - 1; y++) {
                        quadro[cont] = quadroDesenquadrado[x];
                        cont++;
                        x++;
                    }

                    System.out.print("Imprimindo Quadro Enquadrado: " + Arrays.toString(quadro) + "\n");

                    CamadaFisicaTransmissoraController.enviarQuadrosEnquadrados(quadro);
                }
                break;
            case 1:
                x = 0;
                int aux = 0;

                List<Integer> arrayList = new ArrayList<>();
                while (aux < quadroDesenquadrado.length) {
                    int cont = 0;
                    if (quadroDesenquadrado[x] == 33) {
                        arrayList.add(quadroDesenquadrado[x]);
                        x++;
                        aux++;
                    }

                    if (arrayList.size() < quadroDesenquadrado.length) {
                        arrayList.add(quadroDesenquadrado[x]);
                    }

                    while (quadroDesenquadrado[x] != 33 && arrayList.size() < quadroDesenquadrado.length) {
                        x++;
                        arrayList.add(quadroDesenquadrado[x]);
                    }

                    quadro = new int[arrayList.size()];
                    for (int y = 0; y < quadro.length; y++) {
                        quadro[cont] = arrayList.get(0);
                        arrayList.remove(0);
                        cont++;
                        aux++;
                    }

                    x++;
                    CamadaFisicaTransmissoraController.enviarQuadrosEnquadrados(quadroDesenquadrado);
                }
                break;
            default:
                break;
        }
    }

    private int[] camadaEnlaceDadosTransmissoraEnquadramento(int[] quadro) {
        int[] quadroEnquadrado = new int[0];
        switch (AplicacaoTransmissoraController.tipoDeEnquadramento) {
            case 0:
                quadroEnquadrado = camadaDeEnlaceTransmissoraEnquadramentoContagemDeCaracteres(quadro);
                break;
            case 1:
                quadroEnquadrado = camadaDeEnlaceTransmissoraEnquadramentoInsercaoDeCaracteres(quadro);
                break;
            case 2:
                // camadaDeEnlaceTransmissoraEnquadramentoInsercaoDeBits(quadro);
                break;
            case 3:
                // camadaDeEnlaceTransmissoraEnquadramentoViolacaoCamadaFisica(quadro);
                break;
            default:
                break;
        }

        return quadroEnquadrado;
    }

    private int[] camadaDeEnlaceTransmissoraEnquadramentoContagemDeCaracteres(int[] quadro) {
        int[] quadroEnquadrado;
        int cont = 0;

        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int x = quadro.length - 1; x >= 0; x--) {
            linkedList.addFirst(quadro[x]);
            cont++;

            if (quadro[x] == 32 || x == 0) {
                linkedList.addFirst(cont + 1);
                cont = 0;
            }
        }

        quadroEnquadrado = new int[linkedList.size()];
        for (int x = 0; x < quadroEnquadrado.length; x++) {
            quadroEnquadrado[x] = linkedList.getFirst();
            linkedList.removeFirst();
        }

        return quadroEnquadrado;
    }

    private int[] camadaDeEnlaceTransmissoraEnquadramentoInsercaoDeCaracteres(int[] quadro) {
        int[] quadroEnquadrado;
        char flag = '!';
        char escapeCharacter = '.';

        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int x = quadro.length - 1; x >= 0; x--) {
            if (x == 0) {
                linkedList.addFirst(quadro[x]);
                linkedList.addFirst((int) flag);
            } else if ((quadro.length - 1) == x) {
                linkedList.addFirst((int) flag);
                linkedList.addFirst(quadro[x]);
            } else if (quadro[x] == 32) { // if == espaco
                linkedList.addFirst(quadro[x]);
                linkedList.addFirst((int) flag);
                linkedList.addFirst((int) flag);
            } else if (quadro[x] == 46) { // if == '.'
                linkedList.addFirst((int) escapeCharacter);
            } else {
                linkedList.addFirst(quadro[x]);
            }

        }

        quadroEnquadrado = new int[linkedList.size()];
        for (int x = 0; x < quadroEnquadrado.length; x++) {
            quadroEnquadrado[x] = linkedList.getFirst();
            linkedList.removeFirst();
        }

        return quadroEnquadrado;
    }

    private int[] camadaDeEnlaceTransmissoraEnquadramentoInsercaoDeBits(int[] quadro) {
        return new int[0];
    }

    private int[] camadaDeEnlaceTransmissoraEnquadramentoViolacaoCamadaFisica(int[] quadro) {
        return new int[0];
    }
}
