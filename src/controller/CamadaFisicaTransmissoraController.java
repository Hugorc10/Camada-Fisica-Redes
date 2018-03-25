package controller;

import model.CamadaFisicaTransmissoraModel;
import model.MeioDeTransmissaoModel;
import view.FramePrincipal;
import view.PainelMeioDeTransmissao;

import javax.swing.*;
import java.util.Arrays;

public class CamadaFisicaTransmissoraController {
    private Thread thread;
    static PainelMeioDeTransmissao meioDeTransmissao = new PainelMeioDeTransmissao();

    public CamadaFisicaTransmissoraController() {
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                meioDeTransmissao.repaint();
            }
        });

        thread.start();
    }

    /**
     * Metodo: enviarQuadrosEnquadrados
     * Funcao:
     *
     * @param quadroEnquadrado
     */
    public static void enviarQuadrosEnquadrados(int[] quadroEnquadrado) {
        System.out.print("\nEnviar Quadros Enquadrados\n");
        System.out.print("Imprimindo Quadro Enquadrado: " + Arrays.toString(quadroEnquadrado) + "\n");

        PainelMeioDeTransmissao.clear = true; // Impede de limpar a tela

        int[] fluxoBrutoDeBits = new int[0];

        if (FramePrincipal.binarioRadioButton.isSelected()) {
            try {
                AplicacaoTransmissoraController.onClickLimpar();
                meioDeTransmissao.clear = false;

                fluxoBrutoDeBits = CamadaFisicaTransmissoraModel.codificarParaBinario(quadroEnquadrado);

                meioDeTransmissao.bits = new String[fluxoBrutoDeBits.length];
                for (int i = 0; i < fluxoBrutoDeBits.length; i++)
                    meioDeTransmissao.bits[i] = Integer.toBinaryString(fluxoBrutoDeBits[i]);

                meioDeTransmissao.tipoDeCodificacao = meioDeTransmissao.BINARIO;

                System.out.print("Imprimindo meioDeTransmissao.tipoDeCodificacao: "
                        + meioDeTransmissao.tipoDeCodificacao + "\n");

                for (String s : PainelMeioDeTransmissao.bits)
                    FramePrincipal.bitsTextArea.append(s);

                meioDeTransmissao.repaint();
            } catch (Exception e) {
                String error = e.toString();
                JOptionPane.showMessageDialog(null,
                        error,
                        "Exception",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else if (FramePrincipal.manchesterRadioButton.isSelected()) {
            try {
//                painelMeioDeTransmissao = new PainelMeioDeTransmissao();
//                painelMeioDeTransmissao.revalidate();
//                painelMeioDeTransmissao.repaint();

//                AplicacaoTransmissoraController.onClickLimpar();
//                PainelMeioDeTransmissao.clear = false;

                fluxoBrutoDeBits = CamadaFisicaTransmissoraModel.codificarParaManchester(quadroEnquadrado);

                PainelMeioDeTransmissao.manchesterBits = new String[fluxoBrutoDeBits.length];
                for (int i = 0; i < fluxoBrutoDeBits.length; i++) {
                    PainelMeioDeTransmissao.manchesterBits[i] = Integer.toBinaryString(fluxoBrutoDeBits[i]);
                }

                PainelMeioDeTransmissao.tipoDeCodificacao = PainelMeioDeTransmissao.MANCHESTER;

                for (String s : PainelMeioDeTransmissao.manchesterBits)
                    FramePrincipal.bitsTextArea.append(s);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,
                        "Excecao Desconhecida Ocorreu",
                        "Exception",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else if (FramePrincipal.manchesterDiferencialRadioButton.isSelected()) {
            try {
//                painelMeioDeTransmissao = new PainelMeioDeTransmissao();
//                painelMeioDeTransmissao.revalidate();
//                painelMeioDeTransmissao.repaint();

//                AplicacaoTransmissoraController.onClickLimpar();
//                PainelMeioDeTransmissao.clear = false;

                fluxoBrutoDeBits = CamadaFisicaTransmissoraModel.codificarParaManchesterDiferencial(quadroEnquadrado);

                PainelMeioDeTransmissao.manchesterDiferencialBits = new String[fluxoBrutoDeBits.length];
                for (int i = 0; i < fluxoBrutoDeBits.length; i++) {
                    PainelMeioDeTransmissao.manchesterDiferencialBits[i] = Integer.toBinaryString(fluxoBrutoDeBits[i]);
                }

                PainelMeioDeTransmissao.tipoDeCodificacao = PainelMeioDeTransmissao.MANCHESTER_DIFERENCIAL;

                for (String s : PainelMeioDeTransmissao.manchesterDiferencialBits)
                    FramePrincipal.bitsTextArea.append(s);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,
                        "Excecao Desconhecida Ocorreu",
                        "Exception",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

        MeioDeTransmissaoModel.transmitirFluxoDeBits(fluxoBrutoDeBits);
    }
}
