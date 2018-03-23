package model;

import view.FramePrincipal;

import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.util.Arrays;
import java.util.Random;

public class CamadaAplicacaoReceptora {

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

        aplicacaoReceptora(mensagem);
    } // Fim do metodo decodificarCaracteres

    /**
     * Metodo: aplicacaoReceptora
     * Funcao: exibir o texto decodificado na Area de Texto "mensagemReceptorTextPane"
     *
     * @param mensagem eh a mensagem que foi decodificada
     * @return void
     */
    private void aplicacaoReceptora(String mensagem) {
        FramePrincipal.mensagemReceptorTextPane.setText(mensagem); // Seta a mensagem no JTextArea "mensagemReceptorTextPane"

        String[] strings = FramePrincipal.mensagemReceptorTextPane.getText().split("\\s+");
        StyledDocument doc = FramePrincipal.mensagemReceptorTextPane.getStyledDocument();
        SimpleAttributeSet set = new SimpleAttributeSet();

        int lastIndex = 0;

        System.out.println("Imprime tamanho da strings: " + Arrays.toString(strings));

        Random random = new Random();
        for (int i = 0; i < strings.length; i++) {
            // Gera cores randomicas
            StyleConstants.setForeground(set, new Color(random.nextInt(256), random.nextInt(256),
                    random.nextInt(256)));
            // Letras estarao em negrito
            StyleConstants.setBold(set, true);
            doc.setCharacterAttributes(lastIndex, strings[i].length(), set, true);
            // Para cada espaco uma palavra tera uma cor diferente
            lastIndex += strings[i].length() + 1;
        }
    } // Fim do metodo aplicacaoReceptora
}
