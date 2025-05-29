/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Auxiliar.Consts;
import Auxiliar.Desenho;
import auxiliar.Posicao;
import Modelo.Chave;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.ImageIcon;

/**
 *
 * @author gabia
 */
public abstract class Arma extends Personagem{
    protected String sNomeImagePNG;
    protected Chave chave;
    protected Hero heroi;
    
    public Arma(String sNomeImagePNG, Hero hero,  Chave chave) {
        super(sNomeImagePNG);
        this.chave = chave;
        this.bMortal = false;
        this.bTransponivel = true;
        this.bVisivel = true;
        this.heroi = hero;
        
    }

    public Chave getChave() {
        return chave;
    }

    public void setChave(Chave chave) {
        this.chave = chave;
    }
    
    
    public String getsNomeImagePNG() {
        return sNomeImagePNG;
    }


    public void setsNomeImagePNG(String sNomeImagePNG) {
        this.sNomeImagePNG = sNomeImagePNG;
    }
    
    public void voltaAUltimaPosicao(){
        this.pPosicao.volta();
   }

    public Posicao getpPosicao() {
        return pPosicao;
    }

    public void setpPosicao(Posicao pPosicao) {
        this.pPosicao = pPosicao;
    }
    
    public boolean validaPosicao(){
        if (!Desenho.acessoATelaDoJogo().ehPosicaoValidaArma(this.getpPosicao())) {
            this.voltaAUltimaPosicao();
            return false;
        }
        return true;       
   }
   
}
