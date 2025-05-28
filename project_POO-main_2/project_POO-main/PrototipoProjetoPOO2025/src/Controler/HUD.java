/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controler;

/**
 *
 * @author thiag
 */

import Modelo.Hero;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class HUD {
    private Hero hero;
    
    public HUD(Hero hero) {
        this.hero = hero;
    }
    
    public void draw(Graphics g) {
        // RETANGULO ATRAS
        g.setColor(new Color(0, 0, 0, 150));
        g.fillRect(10, 10, 120, 30);
        
        // CONTADOR DE VIDAS
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.drawString("Vidas: " + hero.getVidas() , 20, 35);
    }
}
