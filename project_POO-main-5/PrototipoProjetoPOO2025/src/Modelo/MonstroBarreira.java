/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Modelo.Monstro;
import java.io.Serializable;
/**
 *
 * @author gabia
 */
public class MonstroBarreira extends Monstro implements Serializable{
    public MonstroBarreira(String sNomeImagePNG, boolean imortal) {
        super(sNomeImagePNG, imortal);
        this.bTransponivel = false;
    }
    
}
