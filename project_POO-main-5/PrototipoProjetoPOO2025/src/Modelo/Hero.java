package Modelo;

import Auxiliar.Consts;
import Auxiliar.Desenho;
import Modelo.Arma;
import Controler.ControleDeJogo;
import Controler.Tela;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;
import java.io.Serializable;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Hero extends Personagem implements Serializable{
    
    private boolean invencivel;
    private long tempoInvencivel;
    private Arma arma;
    private int vidas;
    private int maxVidas;
    private boolean vivo;

    public boolean isVivo() {
        return vivo;
    }
   /* public void autoDesenho(){;
        this.iContaIntervalos++;
        if(this.iContaIntervalos == Consts.TIMER){
            this.iContaIntervalos = 0;
            Fogo f = new Fogo("fire.png");
            f.setPosicao(pPosicao.getLinha(),pPosicao.getColuna()+1);
            Desenho.acessoATelaDoJogo().addPersonagem(f);
        }
    }*/
    public void setVivo(boolean vivo) {
        this.vivo = vivo;
        if (!vivo) {
            this.vidas = 0;
        }
    }
    public Hero(String sNomeImagePNG) {
        super(sNomeImagePNG);
        this.vidas = 3;
        this.maxVidas = 5;
        this.vivo = true;
    }
    
    public void perderVida() {
        if (this.vidas > 0) {
            this.vidas--;
            if (this.vidas <= 0) {
                this.vivo = false;
            } else {
                ativarInvencibilidade();
            }
        }
    }
    
    private void ativarInvencibilidade() {
        this.invencivel = true;
        this.tempoInvencivel = System.currentTimeMillis() + 2000; // ele fica invencivel por 2 segundos
    }
    
    public void atualizarInvencibilidade() {
        if(this.invencivel && System.currentTimeMillis() > this.tempoInvencivel) {
            this.invencivel = false;
        }
    }
    
    public boolean isInvencivel() {
        return this.invencivel;
    }
    
    public void ganharVida() {
        if(this.vidas < this.maxVidas) {
            this.vidas++;
        }
    }
    
    public void resetVidas() {
        this.vidas = 3;
        this.vivo = true;
    }
       
    public int getVidas() {
        return vidas;
    }
    
    public void setVidas(int vidas) {
        if(vidas >= 0 && vidas <= maxVidas) {
            this.vidas = vidas;
            this.vivo = (vidas > 0);
        }
    }
    
    public void voltaAUltimaPosicao(){
        this.pPosicao.volta();
    }
    
    
    public boolean setPosicao(int linha, int coluna){
        if(this.pPosicao.setPosicao(linha, coluna)){
            if (!Desenho.acessoATelaDoJogo().ehPosicaoValida(this.getPosicao())) {
                this.voltaAUltimaPosicao();
            }
            return true;
        }
        return false;       
    }

    /*TO-DO: este metodo pode ser interessante a todos os personagens que se movem*/
    private boolean validaPosicao(){
        if (!Desenho.acessoATelaDoJogo().ehPosicaoValida(this.getPosicao())) {
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
