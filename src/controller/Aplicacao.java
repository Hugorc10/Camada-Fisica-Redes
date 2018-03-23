package controller;

import model.CamadaAplicacaoTransmissora;
import view.FramePrincipal;

public class Aplicacao {
    public static int tipoDeEnquadramento;
    public static boolean clear;

    private CamadaAplicacaoTransmissora aplicacaoTransmissora = new CamadaAplicacaoTransmissora();
    private FramePrincipal framePrincipal = new FramePrincipal();
    /**
     * Metodo: enviarTexto
     * Funcao: armazena o texto digitado em uma string e o envia para o metodo "codificarCaracteres()"
     *
     * @return void
     */
    public void enviarTexto() {
        String mensagem = FramePrincipal.textoField.getText(); // Armazena a texto digitado na String mensagem
        aplicacaoTransmissora.codificarCaracteres(mensagem); // Chama a proxima camada
    }

    /**
     * Metodo: escolherTipoDeEnquadramento
     * Funcao:
     *
     * @return void
     */
    public void escolherTipoDeEnquadramento() {
        System.out.print("\nEscolher tipo de enquadramento");
        tipoDeEnquadramento = FramePrincipal.optionComboBox.getSelectedIndex();
        System.out.print("\nTipo de enquadramento: " + tipoDeEnquadramento + "\n");
    }

    /**
     * Metodo: limparTela
     * Funcao: limpa o painel e os campos de texto exibidos na aplicacao
     *
     * @return void
     */
    public void limparTela() {
        this.clear = true; // Faz limpar a tela
        framePrincipal.bitsTextArea.setText("");
        framePrincipal.mensagemReceptorTextPane.setText("");
        framePrincipal.validate(); // Revalida a tela
        framePrincipal.repaint(); // Chama o paint() que atualiza o display
    } // Fim do metodo limparTela
}
