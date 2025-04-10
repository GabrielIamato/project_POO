package Modelo;

import Auxiliar.Consts;
import Auxiliar.Desenho;
import auxiliar.Posicao;
import Controler.Tela;
import java.awt.Graphics;
import java.io.Serializable;

public class Perseguidor extends Personagem implements Serializable{
    private int iContaIntervalos;
    
    public Perseguidor(String sNomeImagePNG) {
        super(sNomeImagePNG);
        this.bTransponivel = true;
        this.bMortal = true;
        this.iContaIntervalos = 0;
    }
    public void autoDesenho(Posicao Posicao_Hero) {
    super.autoDesenho();

    this.iContaIntervalos++;
    if (this.iContaIntervalos == Consts.TIMER) {
        this.iContaIntervalos = 0;

        int linhaHero = Posicao_Hero.getLinha();
        int colunaHero = Posicao_Hero.getColuna();
        int linhaAtual = this.pPosicao.getLinha();
        int colunaAtual = this.pPosicao.getColuna();

        boolean moveu = false;

        // Movimento vertical prioritário
        if (linhaHero < linhaAtual) {
            moveu = this.pPosicao.moveUp();
        } else if (linhaHero > linhaAtual) {
            moveu = this.pPosicao.moveDown();
        }

        // Se não moveu na vertical, tenta mover na horizontal
        if (!moveu) {
            if (colunaHero < colunaAtual) {
                moveu = this.pPosicao.moveLeft();
            } else if (colunaHero > colunaAtual) {
                moveu = this.pPosicao.moveRight();
            }
        }
    }
}

 }    

