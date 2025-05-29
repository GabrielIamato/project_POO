/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
/**
 *
 * @author gabia
 */
public class Monstro extends Personagem {

    protected boolean imortal;
    protected boolean isTransponivelArma;
    public Monstro(String sNomeImagePNG, boolean imortal) {
        super(sNomeImagePNG);
        this.imortal = imortal;
        this.isTransponivelArma = true;
    }
    

    public boolean isImortal() {
        return imortal;
    }

    public void setImortal(boolean imortal) {
        this.imortal = imortal;
    }

    public boolean isIsTransponivelArma() {
        return isTransponivelArma;
    }

    public void setIsTransponivelArma(boolean isTransponivelArma) {
        this.isTransponivelArma = isTransponivelArma;
    }
    
    
       
}
