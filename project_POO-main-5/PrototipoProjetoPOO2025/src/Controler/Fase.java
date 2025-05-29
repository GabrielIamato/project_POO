/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controler;

import Auxiliar.Consts;
import Modelo.Parede;
import Modelo.Personagem;
import Modelo.Hero;
import Modelo.Arma;
import Controler.HUD;
import auxiliar.Posicao;
import java.util.ArrayList;

/**
 *
 * @author gabia
 */
public abstract class Fase {
    protected ArrayList<Personagem> personagens;
    protected Arma arma;
    protected Hero hero;
    protected HUD hud;
    protected int cameraLinha = 0;
    protected int cameraColuna = 0;

    public Arma getArma() {
        return arma;
    }

    public void setArma(Arma arma) {
        this.arma = arma;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public HUD getHud() {
        return hud;
    }

    public void setHud(HUD hud) {
        this.hud = hud;
    }
    
    
    public Fase() {
        personagens = new ArrayList<Personagem>();
    }
    public void addPersonagem(Personagem umPersonagem) {
        personagens.add(umPersonagem);
    }

    public ArrayList<Personagem> getPersonagens() {
        return personagens;
    }

    public void setPersonagens(ArrayList<Personagem> personagens) {
        this.personagens = personagens;
    }

    public void removePersonagem(Personagem umPersonagem) {
        personagens.remove(umPersonagem);
    }
    public abstract ArrayList<Posicao> gerarParedesLabirinto();
    
    public void desenhaFase(){}
    
    public void atualizaCamera() {
        int linha = hero.getPosicao().getLinha();
        int coluna = hero.getPosicao().getColuna();

        cameraLinha = Math.max(0, Math.min(linha - Consts.RES / 2, Consts.MUNDO_ALTURA - Consts.RES));
        cameraColuna = Math.max(0, Math.min(coluna - Consts.RES / 2, Consts.MUNDO_LARGURA - Consts.RES));
    }
    
    public int getCameraLinha() {
        return cameraLinha;
    }

    public int getCameraColuna() {
        return cameraColuna;
    }
     
}
