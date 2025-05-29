/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controler;

import Modelo.BichinhoVaiVemHorizontal;
import Modelo.Bomba;
import Modelo.Cadeado;
import Modelo.Caveira;
import Modelo.Chave;
import Modelo.Coracao;
import Modelo.Espada;
import Modelo.Hero;
import Modelo.MonstroBarreira;
import Modelo.Parede;
import Modelo.ZigueZague;
import auxiliar.Posicao;
import java.util.ArrayList;

/**
 *
 * @author gabia
 */
public class Fase1 extends Fase{

    public Fase1() {
        super();
    }
    
    public ArrayList<Posicao> gerarParedesLabirinto() {
        ArrayList<Posicao> paredes = new ArrayList<>();

        // Cria paredes nas bordas
        for (int i = 0; i < 30; i++) {
            paredes.add(new Posicao(0, i));        // topo
            paredes.add(new Posicao(29, i));       // base
            paredes.add(new Posicao(i, 0));        // esquerda
            paredes.add(new Posicao(i, 29));       // direita
        }

        // Adiciona algumas paredes internas (exemplo em forma de labirinto simples)
        for (int i = 2; i < 28; i += 2) {
            for (int j = 2; j < 28; j++) {
                if (j % 4 != 0) {
                    paredes.add(new Posicao(i, j));
                }
            }
        }

        return paredes;
    }

    public void desenhaParedes() {
        ArrayList<Posicao> posicoesParedes = gerarParedesLabirinto();
        
        for (Posicao pos : posicoesParedes) {
            Parede parede = new Parede("parede.jpg");
            parede.setPosicao(pos.getLinha(), pos.getColuna());
            this.addPersonagem(parede);
        }
    }
    
    public void desenhaFase(){
        hero = new Hero("skoot.png");
        hero.setPosicao(4, 4);
        
        this.addPersonagem(hero);
        this.atualizaCamera();
        Chave chave_arma = new Chave("espada.jpg");
        chave_arma.setPosicao(1,1);
                

        this.arma = new Espada("espada.jpg", hero, chave_arma);
        arma.setbVisivel(false);
        
        this.addPersonagem(arma);
        this.addPersonagem(chave_arma);  
        desenhaParedes();
        
        /*Cria faseAtual adiciona personagens*/
        
        ZigueZague zz = new ZigueZague("robo.png", 5);
        zz.setPosicao(9, 10);
        this.addPersonagem(zz);

       /* BichinhoVaiVemHorizontal bBichinhoH = new BichinhoVaiVemHorizontal("roboPink.png", 5, 1);
        bBichinhoH.setPosicao(3, 3);
        this.addPersonagem(bBichinhoH);*/
        
        BichinhoVaiVemHorizontal guarda = new BichinhoVaiVemHorizontal("guardinha.jpg", 3, 3);
        guarda.setPosicao(9,10);
        guarda.setbMortal(true);
        this.addPersonagem(guarda);
        
        Coracao coracao_1 = new Coracao("coracao.png");
        coracao_1.setPosicao(3, 2);
        this.addPersonagem(coracao_1);
        
        Coracao coracao_2 = new Coracao("coracao.png");
        coracao_2.setPosicao(5, 2);
        this.addPersonagem(coracao_2);

        Coracao coracao_3 = new Coracao("coracao.png");
        coracao_3.setPosicao(5, 3);
        this.addPersonagem(coracao_3);

       
        Bomba bomba = new Bomba("bomba.jpg");
        bomba.setPosicao(5,5);
        this.addPersonagem(bomba);
        
        Chave chave_1 = new Chave("chave.jpg");
        chave_1.setPosicao(3,4);
        this.addPersonagem(chave_1);
        
        Chave chave_2 = new Chave("chave.jpg");
        chave_2.setPosicao(3,6);
        this.addPersonagem(chave_2);
        
        
        Cadeado cadeado = new Cadeado("cadeado.png");
        cadeado.setPosicao(2,4);
        this.addPersonagem(cadeado);
        
        cadeado.adicionarChave(chave_1);
        cadeado.adicionarChave(chave_2);
       
        
        Chave chave_saida1 = new Chave("moeda.jpg");
        chave_saida1.setPosicao(6,8);
        this.addPersonagem(chave_saida1);
        
        Chave chave_saida2 = new Chave("moeda.jpg");
        chave_saida2.setPosicao(6,12);
        this.addPersonagem(chave_saida2);
        
        Cadeado saida= new Cadeado("saída.png");
        saida.setPosicao(9,6);
        saida.setSaida(true);
        this.addPersonagem(saida);
        
        saida.adicionarChave(chave_saida1);
        saida.adicionarChave(chave_saida2);
                
        
        
       // chave_1.setColetada(true);
        //chave_2.setColetada(true);
        /*BichinhoVaiVemHorizontal bBichinhoH2 = new BichinhoVaiVemHorizontal("roboPink.png", 5 , 1);
        bBichinhoH2.setPosicao(6, 6);
        this.addPersonagem(bBichinhoH2);*/

        Caveira bV = new Caveira("caveira.png");
        bV.setPosicao(7, 1);
        this.addPersonagem(bV);
        
        MonstroBarreira monstrobarreira = new MonstroBarreira("caveira.png", false);
        monstrobarreira.setPosicao(5, 1);
        monstrobarreira.setIsTransponivelArma(true);
        this.addPersonagem(monstrobarreira);
        ////
       /*Perseguidor perseguidor = new Perseguidor("espada.jpg", 5);
        perseguidor.setPosicao(3,6);
        this.addPersonagem(perseguidor);*/
       
        this.hud = new HUD(hero); 
    }
}
