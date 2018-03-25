package model;

import controller.AplicacaoTransmissoraController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CamadaEnlaceDeDadosReceptora {

    protected static void receberQuadrosEnquadrados(int[] quadro) {
        CamadaAplicacaoReceptoraModel aplicacaoReceptoraModel = new CamadaAplicacaoReceptoraModel();

        int[] quadroEnquadrado = camadaEnlaceDadosReceptoraEnquadramento(quadro);
        System.out.println("Imprimindo quadro desenquadrado: " + Arrays.toString(quadroEnquadrado));

        aplicacaoReceptoraModel.decodificarCaracteres(quadroEnquadrado);
    }

    private static int[] camadaEnlaceDadosReceptoraEnquadramento(int[] quadro) {
        int[] quadroDesenquadrado = new int[0];
        switch (AplicacaoTransmissoraController.tipoDeEnquadramento) {
            case 0:
                quadroDesenquadrado = camadaEnlaceDadosReceptoraEnquadramentoContagemDeCaracteres(quadro);
                break;
            case 1:
                quadroDesenquadrado = camadaEnlaceDadosReceptoraEnquadramentoInsercaoDeCaracteres(quadro);
                break;
            case 2:
                break;
            default:
                break;
        }

        return quadroDesenquadrado;
    }

    /**
     * Metodo: camadaEnlaceDadosReceptoraEnquadramentoContagemDeCaracteres
     * Funcao:
     *
     * @param quadro
     * @return
     */
    private static int[] camadaEnlaceDadosReceptoraEnquadramentoContagemDeCaracteres(int[] quadro) {
        System.out.print("\nCamada Enlace Dados Receptora Enquadramento Contagem De Caracteres\n");
        int[] quadroDesenquadrado;
        int aux = 0;

        List<Integer> arrayList = new ArrayList<>();
        while (aux < quadro.length) {
            int letras = 0;
            int indexAux = quadro[aux] - 1;
            aux++;
            while (letras < indexAux) {
                arrayList.add(quadro[aux]);
                letras++;
                aux++;
            }
        }

        quadroDesenquadrado = new int[arrayList.size()];
        for (int x = 0; x < quadroDesenquadrado.length; x++) {
            quadroDesenquadrado[x] = arrayList.get(0);
            arrayList.remove(0);
        }

        return quadroDesenquadrado;
    }

    /**
     * Metodo: camadaEnlaceDadosReceptoraEnquadramentoInsercaoDeCaracteres
     * Funcao:
     *
     * @param quadro
     * @return
     */
    private static int[] camadaEnlaceDadosReceptoraEnquadramentoInsercaoDeCaracteres(int[] quadro) {
        System.out.print("\nCamada Enlace Dados Receptora Enquadramento Insercao De Caracteres\n");

        int[] quadroDesenquadrado;
        int x = 0;

        List<Integer> arrayList = new ArrayList<>();
        while (x < quadro.length) {
            if (quadro[x] == 33 || x == 0) {
                if (quadro[x] == 33) {
                    x++;
                } else if (quadro[x] != -1) {
                    arrayList.add(quadro[x]);
                    x++;
                } else {
                    x++;
                }
            } else if (quadro[x] != 33) {
                if (quadro[x] == 33) {
                    x++;
                } else if (quadro[x] != -1) {
                    arrayList.add(quadro[x]);
                    x++;
                } else {
                    x++;
                }
            } else if (quadro[x] == -1) {
                x++;
            }
        }

        quadroDesenquadrado = new int[arrayList.size()];
        for (int y = 0; y < quadroDesenquadrado.length; y++) {
            quadroDesenquadrado[y] = arrayList.get(0);
            arrayList.remove(0);
        }

        return quadroDesenquadrado;
    }

    public int[] CamadaEnlaceDadosReceptoraEnquadramentoInsercaoDeBits(int quadro[]) {
        return new int[0];
    }

    public int[] CamadaEnlaceDadosReceptoraEnquadramentoViolacaoDaCamadaFisica(int quadro[]) {
        return new int[0];
    }
}
