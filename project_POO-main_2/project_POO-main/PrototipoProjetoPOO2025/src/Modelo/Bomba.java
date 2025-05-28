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
import java.util.ArrayList;
import java.io.Serializable;
/**
 *
 * @author gabia
 */
public class Bomba extends Personagem implements Serializable {
   public Bomba(String sNomeImagePNG) {
        super(sNomeImagePNG);
        this.bTransponivel = true;
        this.bMortal = true;
    }
}

