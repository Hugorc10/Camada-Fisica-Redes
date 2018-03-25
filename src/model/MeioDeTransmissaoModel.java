package model;

import java.util.Arrays;

public class MeioDeTransmissaoModel {

    /**
     * Metodo: transmitirFluxoDeBits
     * Funcao: simular a transmissao de comunicacao da informacao no meio de comunicacao
     * passando de uma variavel para outra
     *
     * @param fluxoBrutoDeBits eh o fluxo de binarios codificados
     * @return void
     */
    public static void transmitirFluxoDeBits(int[] fluxoBrutoDeBits) {
        System.out.print("\nMeio de Transmissao\n");

        int[] fluxoBrutoDeBitsPontoA = new int[fluxoBrutoDeBits.length];

        for (int i = 0; i < fluxoBrutoDeBits.length; i++) {
            fluxoBrutoDeBitsPontoA[i] = fluxoBrutoDeBits[i];
        }

        int[] fluxoBrutoDeBitsPontoB = new int[fluxoBrutoDeBits.length];

        for (int i = 0; i < fluxoBrutoDeBitsPontoA.length; i++) {
            // fluxoBrutoDeBitsPontoB[i] = (fluxoBrutoDeBitsPontoA[i] and 0000 0000) or (fluxoBrutoDeBitsPontoA[i] and 1111 1111)
            fluxoBrutoDeBitsPontoB[i] = (fluxoBrutoDeBitsPontoA[i] & ~0xff) | (fluxoBrutoDeBitsPontoA[i] & 0xff);
        }

        System.out.print("Imprimindo fluxoBrutoDeBitsPontoB: " + Arrays.toString(fluxoBrutoDeBitsPontoB));

        CamadaFisicaReceptoraModel.escolherTipoDeDecodificacao(fluxoBrutoDeBitsPontoB);
    } // Fim do metodo transmitirFluxoDeBits
}
