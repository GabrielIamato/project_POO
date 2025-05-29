/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
import Auxiliar.Desenho;
import auxiliar.Posicao;
import Modelo.Arma;
import Modelo.Chave;
import Modelo.Hero;
import java.io.Serializable;
/**
 *
 * @author gabia
 */
public class ArmaFogoEmVolta extends Arma implements Serializable{
    private int posicaoRelativa = 0; // índice da posição em torno do herói

    private final int[][] deslocamentos = {
        {-1, 0}, // cima
        {0, 1},  // direita
        {1, 0},  // baixo
        {0, -1}  // esquerda
    };

    public ArmaFogoEmVolta(String sNomeImagePNG, Hero heroi, Chave chave) {
        super(sNomeImagePNG,heroi, chave);
 
    }
    
    public void atualizarPosicao() {
        posicaoRelativa = (posicaoRelativa + 1) % deslocamentos.length;

        int linhaHeroi = heroi.getPosicao().getLinha();
        int colunaHeroi = heroi.getPosicao().getColuna();

        int novaLinha = linhaHeroi + deslocamentos[posicaoRelativa][0];
        int novaColuna = colunaHeroi + deslocamentos[posicaoRelativa][1];

        pPosicao.setPosicao(novaLinha, novaColuna);
    }
}
