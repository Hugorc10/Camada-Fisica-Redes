/**
 * Autor: Hugo Teixeira Mafra
 * Matricula:
 * Inicio: 12/02/2018
 * Ultima alteracao: 18/02/2018
 * Nome: Codificador de Sinal Digital
 * Funcao: simular o funcionamento do enlace fisico - independente do meio de transmissao -
 * atraves da implementacao de tres tipos de codificacoes estudadas no livro de Redes de Computadores, do Tanenbaum
 */

import view.FramePrincipal;

import javax.swing.*;

// Classe principal
public class Principal {

    // Metodo main
    public static void main(String[] args) {
        // Schedule a job for the event-dispatching thread:
        // Creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                FramePrincipal framePrincipal = new FramePrincipal(); // Instancia da classe Principal
            }
        });
    } // Fim do metodo main
}






