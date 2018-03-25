package controller;

import model.CamadaAplicacaoTransmissoraModel;
import view.FramePrincipal;
import view.PainelMeioDeTransmissao;

public class AplicacaoTransmissoraController {
    public static int tipoDeEnquadramento;

    /**
     * Metodo: onClickIniciar.
     * Funcao: armazena o texto digitado em uma string e o envia para o metodo 'codificarCaracteres()'.
     *
     * @return void
     */
    public void onClickIniciar() {
        // Armazena a texto digitado na String mensagem
        String mensagem = FramePrincipal.mensagemRemetenteTextField.getText();
        FramePrincipal.bitsTextArea.setText("");
        FramePrincipal.mensagemDestinatarioTextPane.setText("");
        CamadaAplicacaoTransmissoraModel.codificarCaracteres(mensagem); // Chama a proxima camada
    } // Fim do metodo onClickIniciar

    /**
     * Metodo: onClickTipoDeEnquadramento.
     * Funcao: seleciona o tipo de enquadrado e guarda em um inteiro.
     *
     * @return void
     */
    public void onClickTipoDeEnquadramento() {
        System.out.print("\nEscolher tipo de enquadramento\n");
        tipoDeEnquadramento = FramePrincipal.tipoEnquadramentoComboBox.getSelectedIndex();
    } // Fim do metodo onClickTipoDeEnquadramento

    /**
     * Metodo: onClickLimpar
     * Funcao: limpa o painel e os campos de texto exibidos na aplicacao
     *
     * @return void
     */
    public static void onClickLimpar() {
        PainelMeioDeTransmissao painelMeioDeTransmissao = new PainelMeioDeTransmissao();
        PainelMeioDeTransmissao.clear = true;
        FramePrincipal.bitsTextArea.setText("");
        FramePrincipal.mensagemDestinatarioTextPane.setText("");
        painelMeioDeTransmissao.validate(); // Revalida a tela
        painelMeioDeTransmissao.repaint(); // Chama o paint() que atualiza o display
    } // Fim do metodo onClickLimpar
}
