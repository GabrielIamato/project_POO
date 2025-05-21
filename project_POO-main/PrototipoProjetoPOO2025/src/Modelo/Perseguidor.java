package Modelo;

import Auxiliar.Consts;
import Auxiliar.Desenho;
import auxiliar.Posicao;
import Controler.Tela;
import java.awt.Graphics;
import java.io.Serializable;

public class Perseguidor extends Personagem implements Serializable{
    protected int iContaIntervalos;
    protected int intervaloMovimento;
    
    public Perseguidor(String sNomeImagePNG, int intervaloMovimento) {
        super(sNomeImagePNG);
        this.bTransponivel = true;
        this.bMortal = true;
        this.iContaIntervalos = 0;
        this.intervaloMovimento = intervaloMovimento;
    }

    public void autoDesenho(Posicao Posicao_Hero) {
    super.autoDesenho();

    this.iContaIntervalos++;
    if (this.iContaIntervalos == this.intervaloMovimento) {
        this.iContaIntervalos = 0;

        int linhaHero = Posicao_Hero.getLinha();
        int colunaHero = Posicao_Hero.getColuna();
        int linhaAtual = this.pPosicao.getLinha();
        int colunaAtual = this.pPosicao.getColuna();

        boolean moveu = false;

        // Movimento vertical prioritário
        if (linhaHero < linhaAtual) {
            moveu = this.moveUp();
        } else if (linhaHero > linhaAtual) {
            moveu = this.moveDown();
        }

        // Se não moveu na vertical, tenta mover na horizontal
        if (!moveu) {
            if (colunaHero < colunaAtual) {
                moveu = this.moveLeft();
            } else if (colunaHero > colunaAtual) {
                moveu = this.moveRight();
            }
        }
    }
    
}
   public void voltaAUltimaPosicao(){
        this.pPosicao.volta();
   }
  private boolean validaPosicao(){
        if (!Desenho.acessoATelaDoJogo().ehPosicaoValidaPersonagem(this.getPosicao())) {
            this.voltaAUltimaPosicao();
            return false;
        }
        return true;       
   }
  public boolean moveUp() {
        if(super.moveUp())
            return validaPosicao();
        return false;
    }

    public boolean moveDown() {
        if(super.moveDown())
            return validaPosicao();
        return false;
    }

    public boolean moveRight() {
        if(super.moveRight())
            return validaPosicao();
        return false;
    }

    public boolean moveLeft() {
        if(super.moveLeft())
            return validaPosicao();
        return false;
    } 
 }    

