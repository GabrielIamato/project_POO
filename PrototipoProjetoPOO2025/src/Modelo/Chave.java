/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
import Auxiliar.Consts;
import Auxiliar.Desenho;
import auxiliar.Posicao;
import Controler.Tela;
import java.awt.Graphics;
import java.io.Serializable;
/**
 *
 * @author gabia
 */
public class Chave extends Personagem implements Serializable{
     private boolean coletada;
     public Chave(String sNomeImagePNG) {
        super(sNomeImagePNG);
        this.bTransponivel = true;
        this.bMortal = false;
    }

    public boolean isColetada() {
        return coletada;
    }

    public void setColetada(boolean coletada) {
        this.coletada = coletada;
    }
     
}
