package model;

import view.FramePrincipal;

import javax.swing.*;
import java.util.Arrays;

public class CamadaAplicacaoTransmissora {
    private CamadaEnlaceDeDadosTransmissora enlaceDadosTransmissora = new CamadaEnlaceDeDadosTransmissora();

    /**
     * Metodo: codificarCaracteres
     * Funcao: recebe a string digitada no campo de texto e converte para codigo ASCII
     *
     * @param mensagem
     * @return void
     */
    public void codificarCaracteres(String mensagem) {
        byte[] quadroByte = mensagem.getBytes(); // Insere um codigo ASCII em cada posicao do vetor

        System.out.print("\nTamanho do quadroByte: " + quadroByte.length);
        int[] quadroInteger = new int[quadroByte.length];

        // convertendo byteArray para intArray
        for (int i = 0; i < quadroByte.length; quadroInteger[i] = quadroByte[i++]) ;

        System.out.print("\nTamanho do quadroInteger: " + quadroInteger.length);
        System.out.print("\nQuadro de inteiros: " + Arrays.toString(quadroInteger) + "\n");

        if (FramePrincipal.textoField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "A caixa de texto esta vazia", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            enlaceDadosTransmissora.enviarQuadrosDesenquadrados(quadroInteger);
        }
    } // Fim do metodo codificarCaracteres
}
