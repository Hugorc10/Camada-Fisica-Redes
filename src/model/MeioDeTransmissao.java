package model;

import java.util.Arrays;

public class MeioDeTransmissao {
    private CamadaFisicaReceptora fisicaReceptora = new CamadaFisicaReceptora();

    /**
     * Metodo: transmitirFluxoDeBits
     * Funcao: simular a transmissao de comunicacao da informacao no meio de comunicacao
     * passando de uma variavel para outra
     *
     * @param fluxoBrutoDeBits eh o fluxo de binarios codificados
     * @return void
     */
    protected void transmitirFluxoDeBits(int[] fluxoBrutoDeBits) {
        System.out.print("\nMeio de Comunicacao\n");

        int[] fluxoBrutoDeBitsPontoA = new int[fluxoBrutoDeBits.length];

        /*for (int i = 0; i < fluxoBrutoDeBits.length; i++) {
            fluxoBrutoDeBitsPontoA[i] = fluxoBrutoDeBits[i];
        }*/

        fluxoBrutoDeBitsPontoA = fluxoBrutoDeBits;

        int[] fluxoBrutoDeBitsPontoB = new int[fluxoBrutoDeBits.length];

        for (int i = 0; i < fluxoBrutoDeBitsPontoA.length; i++) {
            // fluxoBrutoDeBitsPontoB[i] = (fluxoBrutoDeBitsPontoA[i] and 0000 0000) or (fluxoBrutoDeBitsPontoA[i] and 1111 1111)
            fluxoBrutoDeBitsPontoB[i] = (fluxoBrutoDeBitsPontoA[i] & 0xfffffff0) | (fluxoBrutoDeBitsPontoA[i] & 0xff);
//            fluxoBrutoDeBitsPontoB[i] = (fluxoBrutoDeBitsPontoA[i] & 0xfffffff0) | (fluxoBrutoDeBitsPontoA[i] & 0xf);
        }

        System.out.print("Imprimindo fluxoBrutoDeBitsPontoB: " + Arrays.toString(fluxoBrutoDeBitsPontoB));

        fisicaReceptora.escolherTipoDeDecodificacao(fluxoBrutoDeBitsPontoB);
    } // Fim do metodo transmitirFluxoDeBits
}
