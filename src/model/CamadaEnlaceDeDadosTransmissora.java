package model;

import controller.Aplicacao;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Vector;
import java.util.concurrent.LinkedBlockingQueue;

public class CamadaEnlaceDeDadosTransmissora {
    private Aplicacao aplicacao = new Aplicacao();
    private CamadaFisicaTransmissora fisicaTransmissora = new CamadaFisicaTransmissora();

    /**
     *
     * @param quadro
     */
    public void enviarQuadrosDesenquadrados(int[] quadro) {
        System.out.print("\nCamada Enlace de Dados Transmissora");
        int[] quadroEnquadrado = camadaEnlaceDadosTransmissoraEnquadramento(quadro);
        System.out.print("\nImprimindo quadro enquadrado: " + Arrays.toString(quadroEnquadrado) + "\n");

        separarQuadros(quadroEnquadrado);
    }

    private void separarQuadros(int[] quadroDesenquadrado) {
        System.out.print("\nSend Frames");
        LinkedBlockingQueue<Integer> arrayQueue = new LinkedBlockingQueue<>();
        switch (aplicacao.tipoDeEnquadramento) {
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

                    fisicaTransmissora.enviarQuadrosEnquadrados(quadro);
                }
                break;
            case 1:
                x = 0;
                int aux = 0;

                Vector<Integer> vector = new Vector<>();
                while (aux < quadroDesenquadrado.length) {
                    int cont = 0;
                    if (quadroDesenquadrado[x] == 33) {
                        vector.add(quadroDesenquadrado[x]);
                        x++;
                        aux++;
                    }

                    if (vector.size() < quadroDesenquadrado.length) {
                        vector.add(quadroDesenquadrado[x]);
                    } else {

                    }

                    while (quadroDesenquadrado[x] != 33 && vector.size() < quadroDesenquadrado.length) {
                        x++;
                        vector.add(quadroDesenquadrado[x]);
                    }

                    quadro = new int[vector.size()];
                    for (int y = 0; y < quadro.length; y++) {
                        quadro[cont] = vector.firstElement();
                        vector.remove(0);
                        cont++;
                        aux++;
                    }

                    x++;
                    fisicaTransmissora.enviarQuadrosEnquadrados(quadroDesenquadrado);
                }
                break;
            default:
                break;
        }
    }

    private int[] camadaEnlaceDadosTransmissoraEnquadramento(int[] quadro) {
        int[] quadroEnquadrado = new int[0];
        switch (aplicacao.tipoDeEnquadramento) {
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
