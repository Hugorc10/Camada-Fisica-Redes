package model;

import view.MeioDeTransmissaoPanel;

public class CamadaFisicaReceptora {
    private MeioDeTransmissaoPanel meioDeTransmissaoPanel = new MeioDeTransmissaoPanel();
    private CamadaFisicaReceptora fisicaReceptora = new CamadaFisicaReceptora();
    private CamadaEnlaceDeDadosReceptora enlaceDeDadosReceptora = new CamadaEnlaceDeDadosReceptora();

    /**
     * Metodo: escolherTipoDeDecodificacao
     * Funcao: recebe os bits do vetor de inteiros e decodifica de acordo
     * com o tipo de codificacao
     *
     * @param fluxoBrutoDeBits
     * @return void
     */
    protected void escolherTipoDeDecodificacao(int[] fluxoBrutoDeBits) {
        System.out.print("\nCamada Fisica Receptora\n");
        final int BINARY = 1, MANCHESTER = 2, MANCHESTER_DIFERENCIAL = 3;

        int[] quadro = new int[0];
        switch (meioDeTransmissaoPanel.getTecnicaCodificacao()) {
            case BINARY:
                quadro = fisicaReceptora.decodificarBinario(fluxoBrutoDeBits);
                break;
            case MANCHESTER:
                quadro = fisicaReceptora.decodificarManchester(fluxoBrutoDeBits);
                break;
            case MANCHESTER_DIFERENCIAL:
                quadro = fisicaReceptora.decodificarManchesterDiferencial(fluxoBrutoDeBits);
                break;
            default:
                break;
        } // Fim do switch/case

        enlaceDeDadosReceptora.receberQuadrosEnquadrados(quadro); // chama a proxima camada
    } // Fim do metodo escolherTipoDeDecodificacao

    /**
     * Metodo: decodificarBinario
     * Funcao:
     *
     * @param fluxoBrutoDeBits
     * @return int[]
     */
    private int[] decodificarBinario(int[] fluxoBrutoDeBits) {
        System.out.print("\nCamada Fisica Receptora Decodificacao Binaria\n");
        int[] quadro = new int[fluxoBrutoDeBits.length * 4];

        for (int x = 0; x < quadro.length; x++) {
            quadro[x] = -1;
        } // fim do for

        int i = 31;
        if (fluxoBrutoDeBits[fluxoBrutoDeBits.length - 1] <= 255) {
            i = 7;
        } else if (fluxoBrutoDeBits[fluxoBrutoDeBits.length - 1] <= 65535) {
            i = 15;
        } else if (fluxoBrutoDeBits[fluxoBrutoDeBits.length - 1] <= 16777215) {
            i = 23;
        } // fim do if

        int cont2 = 0;
        for (int x = 0; x < fluxoBrutoDeBits.length; x++) {
            int y = 31;
            if (x == fluxoBrutoDeBits.length - 1) {
                y = i;
            } // fim do if
            String imp = "";
            int cont1 = 1;

            for (; y >= 0; y--) {
                int mask = 1 << y;
                imp += "" + Integer.toBinaryString(fluxoBrutoDeBits[x] & mask).charAt(0);
                if (cont1 != 0 && cont1 % 8 == 0) {
                    quadro[cont2] = Integer.parseInt(imp, 2);
                    cont2++;
                    imp = "";
                } // fim do if
                cont1++;
            } // fim do for
        } // fim do for

        return quadro;
    } // Fim do metodo decodificarBinario

    /**
     * Metodo: camadaFisicaReceptoraDecoficacaoManchester
     * Funcao:
     *
     * @param fluxoBrutoDeBits
     * @return int[]
     */
    public int[] decodificarManchester(int[] fluxoBrutoDeBits) {
        System.out.print("\nCamada Fisica Receptora Decodificacao Manchester\n");
        int[] quadro = new int[fluxoBrutoDeBits.length * 2];

        for (int x = 0; x < quadro.length; x++)
            quadro[x] = -1;

        int i = 31;

        String n = "" + fluxoBrutoDeBits[fluxoBrutoDeBits.length - 1];
        if (n.length() <= 6) {
            i = 15;
        }

        int aux2 = 0;
        for (int x = 0; x < fluxoBrutoDeBits.length; x++) {
            int y = 31;
            if (x == fluxoBrutoDeBits.length - 1) {
                y = i;
            }
            String imp = "";
            int aux1 = 1;

            while (y >= 0) {
                int mask = 1 << y;

                if ((aux1 - 1 != 0) && (aux1 - 1) % 2 != 0) {
                    imp += "" + Integer.toBinaryString(fluxoBrutoDeBits[x] & mask).charAt(0);
                }

                if (aux1 != 0 && aux1 % 16 == 0) {
                    quadro[aux2] = Integer.parseInt(imp, 2);
                    aux2++;
                    imp = "";
                }
                aux1++;
                y--;
            }
        }
        return quadro;
    } // Fim do metodo decodificarManchester

    /**
     * Metodo: decodificarManchesterDiferencial
     * Funcao:
     *
     * @param fluxoBrutoDeBits
     * @return int[]
     */
    private int[] decodificarManchesterDiferencial(int[] fluxoBrutoDeBits) {
        System.out.print("\nCamada Fisica Receptora Decodificacao Manchester Diferencial\n");
        int[] quadro = new int[fluxoBrutoDeBits.length * 2];

        for (int x = 0; x < quadro.length; x++)
            quadro[x] = -1;

        int i = 31;

        String n = "" + fluxoBrutoDeBits[fluxoBrutoDeBits.length - 1];
        if (n.length() <= 6) {
            i = 15;
        }

        int aux2 = 0;
        for (int x = 0; x < fluxoBrutoDeBits.length; x++) {
            int y = 31;
            if (x == fluxoBrutoDeBits.length - 1) {
                y = i;
            }
            String imp = "";
            int aux1 = 1;

            while (y >= 0) {
                int mask = 1 << y;

                if ((aux1 - 1 != 0) && (aux1 - 1) % 2 != 0) {
                    imp += "" + Integer.toBinaryString(fluxoBrutoDeBits[x] & mask).charAt(0);
                }

                if (aux1 != 0 && aux1 % 16 == 0) {
                    quadro[aux2] = Integer.parseInt(imp, 2);
                    aux2++;
                    imp = "";
                }
                aux1++;
                y--;
            }
        }
        return quadro;
    }
}
