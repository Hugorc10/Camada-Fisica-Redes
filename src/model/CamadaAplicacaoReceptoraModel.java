package model;

import controller.CamadaAplicacaoReceptoraController;

public class CamadaAplicacaoReceptoraModel {

    /**
     * Metodo: decodificarCaracteres
     * Funcao: converte a mensagem que esta em codigo ASCII para char
     *
     * @param quadro
     * @return void
     */
    protected void decodificarCaracteres(int[] quadro) {
        String mensagem;

        StringBuilder sb = new StringBuilder(quadro.length);
        for (int i = 0; i < quadro.length; ++i) {
            sb.append((char) quadro[i]);
        }

        mensagem = sb.toString();
        System.out.println("Imprimindo mensagem: " + mensagem);

        CamadaAplicacaoReceptoraController.exibirMensagemDecodificada(mensagem);
    } // Fim do metodo decodificarCaracteres
}
