package Modelo;

import Auxiliar.Consts;
import Auxiliar.Desenho;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.io.Serializable;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class BichinhoVaiVemHorizontal extends Monstro  implements Serializable{
    private boolean bRight;
    private int iContaIntervalos;
    private int intervaloMovimento;
    
    private int passosMaximos;    // Quantidade de passos para cada lado
    private int passosDados;      // Quantos passos já andou na direção atual
    
    public BichinhoVaiVemHorizontal(String sNomeImagePNG, int intervaloMovimento, int passosMaximos) {
        super(sNomeImagePNG, false);
        this.bRight = true;
        this.iContaIntervalos = 0;
        this.intervaloMovimento = intervaloMovimento;
        this.passosMaximos = passosMaximos;
    }
    public void autoDesenho() {
        super.autoDesenho();

        iContaIntervalos++;
        if (iContaIntervalos < intervaloMovimento)
            return;

        iContaIntervalos = 0;

        boolean moveu;
        if (bRight) {
            moveu = this.moveRight();
        } else {
            moveu = this.moveLeft();
        }

        if (moveu) {
            passosDados++;
            if (passosDados >= passosMaximos) {
                passosDados = 0;
                bRight = !bRight; // Inverte direção após X passos
            }
        } else {
            // Se bateu em obstáculo, muda a direção mesmo que não tenha terminado os passos
            passosDados = 0;
            bRight = !bRight;
        }
    }
}

