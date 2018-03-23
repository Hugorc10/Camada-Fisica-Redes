package model;

import controller.Aplicacao;
import view.FramePrincipal;
import view.MeioDeTransmissaoPanel;

import javax.swing.*;
import java.util.Arrays;
import java.util.concurrent.LinkedBlockingQueue;

public class CamadaFisicaTransmissora {
    private FramePrincipal framePrincipal = new FramePrincipal();
    private Aplicacao aplicacao = new Aplicacao();
    private MeioDeTransmissao meioDeTransmissao = new MeioDeTransmissao();
    private MeioDeTransmissaoPanel meioDeTransmissaoPanel = new MeioDeTransmissaoPanel();

    protected void enviarQuadrosEnquadrados(int[] quadroEnquadrado) {
        System.out.print("\nCamada Fisica Transmissora\n");
        aplicacao.clear = true; // Impede de limpar a tela
        LinkedBlockingQueue<int[]> arrayQueue = new LinkedBlockingQueue<>();
        int[] fluxoBrutoDeBits = new int[0];

        if (FramePrincipal.binarioRadioButton.isSelected()) {
            try {
                aplicacao.limparTela();
                aplicacao.clear = false;

                for (int i = 0; i < codificarParaBinario(quadroEnquadrado).length; i++) {
                    arrayQueue.put(codificarParaBinario(quadroEnquadrado));
                }

                fluxoBrutoDeBits = arrayQueue.poll();

                meioDeTransmissaoPanel.bits = new String[fluxoBrutoDeBits.length];
                for (int i = 0; i < fluxoBrutoDeBits.length; i++)
                    meioDeTransmissaoPanel.bits[i] = Integer.toBinaryString(fluxoBrutoDeBits[i]);

                meioDeTransmissaoPanel.setTecnicaCodificacao(meioDeTransmissaoPanel.BINARIO);

                for (String s : meioDeTransmissaoPanel.bits)
                    framePrincipal.bitsTextArea.append(s);

                System.out.print("Imprimindo bits receptor: " + Arrays.toString(meioDeTransmissaoPanel.bits) + "\n");
            } catch (NumberFormatException e1) {
                JOptionPane.showMessageDialog(null, "Entrada Invalida", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(null, "Excecao Desconhecida Ocorreu", "Exception", JOptionPane.ERROR_MESSAGE);
            }
        } else if (FramePrincipal.manchesterRadioButton.isSelected()) {
            try {
                meioDeTransmissaoPanel.revalidate();
                meioDeTransmissaoPanel.repaint();

                aplicacao.limparTela();
                meioDeTransmissaoPanel.clear = false;

                fluxoBrutoDeBits = codificarParaManchester(quadroEnquadrado);

                meioDeTransmissaoPanel.manchesterBits = new String[fluxoBrutoDeBits.length];
                for (int i = 0; i < fluxoBrutoDeBits.length; i++) {
                    meioDeTransmissaoPanel.manchesterBits[i] = Integer.toBinaryString(fluxoBrutoDeBits[i]);
                }

                meioDeTransmissaoPanel.setTecnicaCodificacao(meioDeTransmissaoPanel.MANCHESTER);

                for (String s : meioDeTransmissaoPanel.manchesterBits)
                    framePrincipal.bitsTextArea.append(s);

            } catch (NumberFormatException e1) {
                JOptionPane.showMessageDialog(null, "Entrada Invalida", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(null,
                        "Excecao Desconhecida Ocorreu",
                        "Exception",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else if (FramePrincipal.manchesterDiferencialRadioButton.isSelected()) {
            try {
                meioDeTransmissaoPanel.revalidate();
                meioDeTransmissaoPanel.repaint();

                aplicacao.limparTela();
                meioDeTransmissaoPanel.clear = false;

                fluxoBrutoDeBits = codificarParaManchesterDiferencial(quadroEnquadrado);

                meioDeTransmissaoPanel.manchesterDiferencialBits = new String[fluxoBrutoDeBits.length];
                for (int i = 0; i < fluxoBrutoDeBits.length; i++) {
                    meioDeTransmissaoPanel.manchesterDiferencialBits[i] = Integer.toBinaryString(fluxoBrutoDeBits[i]);
                }

                meioDeTransmissaoPanel.setTecnicaCodificacao(meioDeTransmissaoPanel.MANCHESTER_DIFERENCIAL);
                meioDeTransmissaoPanel.repaint();

                for (String s : meioDeTransmissaoPanel.manchesterDiferencialBits)
                    framePrincipal.bitsTextArea.append(s);
            } catch (NumberFormatException e1) {
                JOptionPane.showMessageDialog(null, "Entrada Invalida", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(null,
                        "Excecao Desconhecida Ocorreu",
                        "Exception",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

        meioDeTransmissao.transmitirFluxoDeBits(fluxoBrutoDeBits);
    }


    /**
     * Metodo: codificarParaBinario
     * Funcao: Codifica um vetor de bytes contendo os codigos ASCII de caracteres alfabeticos
     * para a codificacao Binaria e insere em um array de inteiros em forma de bits
     *
     * @param quadro = array de bytes contendo codigos ASCII de cada caracter alfabetico
     * @return int[]
     */
    public static int[] codificarParaBinario(int[] quadro) {
        System.out.print("\nCamada Fisica Transmissora Codificacao Binaria\n");
        System.out.print("Imprimindo comprimento do quadro: " + quadro.length + "\n");

        // Variavel que ira receber o comprimento (length) do array "quadro" dividido por quatro
        int n = quadro.length / 4;

        // Verifica  se o comprimento do quadro divido por quatro tem resto diferente de 0
        if (quadro.length % 4 != 0)
            n++;

        int[] bits = new int[n]; // Array que ira conter os inteiros com os bits armazenados no array "quadro"

        int index = 0;

        // Realiza loop ate o i ser menor que o comprimento (length) do vetor bits
        for (int i = 0; i < bits.length; i++) {
            bits[i] = quadro[index];
            index++;

            for (int y = 0; y < 3; y++) {
                if (index < quadro.length) {
                    bits[i] = bits[i] << 8; // Desloca 8 bits a esquerda
                    bits[i] = bits[i] | quadro[index]; // Compara os bits do array 'quadro' para o array 'bits'
                    index++;
                } // fim do if
            } // fim do for
        } // Fim do for

        System.out.print("Imprimindo bits: " + Arrays.toString(bits) + "\n");

        return bits;
    } // Fim do metodo codificarParaBinario

    /**
     * Metodo: codificarParaManchester
     * Funcao: codifica um vetor de bytes contendo os codigos ASCII de caracteres alfabeticos
     * para a codificacao Manchester e insere em um array de inteiros em forma de bits
     *
     * @param quadro = array de bytes contendo codigos ASCII de cada caracter alfabetico
     * @return int[]
     */
    private int[] codificarParaManchester(int[] quadro) {
        System.out.print("\nCamada Fisica Transmissora Codificacao Manchester\n");

        // Variavel que ira receber o comprimento (length) do array "quadro" dividido por dois
        int n = quadro.length / 2;

        // Verifica o comprimento do quadro divido por quatro tiver resto diferente de 0
        if (quadro.length % 2 != 0) {
            n++;
        }

        // Array que ira conter os inteiros com os bits armazenados no array "quadro"
        int[] bits = new int[n];

        int aux = 0; // Variavel auxiliar
        for (int x = 0; x < bits.length; x++) {
            for (int y = 0; y < 2; y++) {
                if (aux < quadro.length) {
                    int temp = quadro[aux];
                    for (int i = 7; i >= 0; i--) {
                        bits[x] = bits[x] << 1;
                        int mask = 1 << i; // Desloca 7 bits a esquerda ate 0
                        if (Integer.toBinaryString(temp & mask).charAt(0) == '0') {
                            bits[x] = bits[x] | 1; // Adiciona o bit 1
                        } else if (Integer.toBinaryString(temp & mask).charAt(0) == '1') {
                            bits[x] = bits[x] | 0; // Adiciona o bit 0
                        }
                        int t = Integer.parseInt(String.valueOf(Integer.toBinaryString(temp & mask).charAt(0)));
                        bits[x] = bits[x] << 1;
                        bits[x] = bits[x] | t;
                    }
                    aux++;
                }
            }
        }

        return bits;
    } // Fim do metodo codificarParaManchester

    /**
     * Metodo: codificarParaManchesterDiferencial
     * Funcao: Codifica um vetor de bytes contendo os codigos ASCII de caracteres alfabeticos
     * para a codificacao Manchester Diferencial e insere em um array de inteiros em forma de bits
     *
     * @param quadro = array de bytes contendo codigos ASCII de cada caracter alfabetico
     * @return int[]
     */
    private int[] codificarParaManchesterDiferencial(int[] quadro) {
        System.out.println("\nCamada Fisica Transmissora Codificacao Manchester Diferencial\n");

        // Variavel que ira receber o comprimento (length) do array "quadro" dividido por dois
        int n = quadro.length / 2;

        // Verifica o comprimento do quadro divido por quatro tiver resto diferente de 0
        if (quadro.length % 2 != 0) {
            n++;
        }

        // Array que ira conter os inteiros com os bits armazenados no array "quadro"
        int[] bits = new int[n];

        int aux = 0; // Variavel auxiliar
        for (int x = 0; x < bits.length; x++) {
            for (int y = 0; y < 2; y++) {
                if (aux < quadro.length) {
                    int temp = quadro[aux];

                    for (int i = 7; i >= 0; i--) {
                        bits[x] = bits[x] << 1;
                        int mask = 1 << i;
                        if (Integer.toBinaryString(temp & mask).charAt(0) == '0') {
                            bits[x] = bits[x] | 1;
                        } else if (Integer.toBinaryString(temp & mask).charAt(0) == '1') {
                            bits[x] = bits[x] | 0;
                        }
                        int t = Integer.parseInt(String.valueOf(Integer.toBinaryString(temp & mask).charAt(0)));
                        bits[x] = bits[x] << 1;
                        bits[x] = bits[x] | t;
                    }
                    aux++;
                } // fim do if
            } // fim do for
        } // fim do for

        return bits;
    } // Fim do metodo codificarParaManchesterDiferencial
}
