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
public class Parede extends Personagem implements Serializable {
    public Parede(String sNomeImagePNG) {
        super(sNomeImagePNG);
        this.bTransponivel = false;
        this.bMortal = false;
    }
}
