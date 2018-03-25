package controller;

import view.FramePrincipal;

import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.util.Arrays;
import java.util.Random;

public class CamadaAplicacaoReceptoraController {

    /**
     * Metodo: exibirMensagemDecodificada
     * Funcao: exibir o texto decodificado na Area de Texto "mensagemDestinatarioTextPane"
     *
     * @param mensagem eh a mensagem que foi decodificada
     * @return void
     */
    public static void exibirMensagemDecodificada(String mensagem) {
        FramePrincipal.mensagemDestinatarioTextPane.setText(mensagem); // Seta a mensagem no JTextArea "mensagemDestinatarioTextPane"

        String[] strings = FramePrincipal.mensagemDestinatarioTextPane.getText().split("\\s+");
        StyledDocument doc = FramePrincipal.mensagemDestinatarioTextPane.getStyledDocument();
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
    } // Fim do metodo exibirMensagemDecodificada
}
