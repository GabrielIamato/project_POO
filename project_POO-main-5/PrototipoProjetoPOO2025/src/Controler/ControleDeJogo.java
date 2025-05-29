package Controler;

import Modelo.Personagem;
import Modelo.Hero;
import Modelo.Cadeado;
import Modelo.Chave;
import Modelo.ZigueZague;
import Modelo.Perseguidor;
import Modelo.Coracao;
import Modelo.Monstro;
import Modelo.Espada;
import Modelo.Bomba;
import Modelo.Arma;
import auxiliar.Posicao;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ControleDeJogo {
    public void desenhaTudo(ArrayList<Personagem> e){
        Posicao posicaoHeroi = e.get(0).getPosicao();  // Assumindo que o herói é o primeiro da lista

        for (int i = 0; i < e.size(); i++) {
            Personagem p = e.get(i);

            if (p instanceof Perseguidor && !(p instanceof ZigueZague)) {
                ((Perseguidor)p).autoDesenho(posicaoHeroi);
            }
            else if(p instanceof Espada){
                ((Espada)p).autoDesenho();
            }
             else {
                p.autoDesenho();
          }
    }
    }
    public void processaTudo(ArrayList<Personagem> umaFase){
        Hero hero = (Hero)umaFase.get(0);
        Arma arma = (Arma)umaFase.get(1);
        hero.atualizarInvencibilidade(); 
        
        Personagem pIesimoPersonagem;
        //posicao [1] é sempre arma
        for(int i = 2; i < umaFase.size(); i++){
            pIesimoPersonagem = umaFase.get(i);
            if(pIesimoPersonagem instanceof Monstro){
                if(arma.getChave().isColetada()){
                    if(arma.getPosicao().igual(pIesimoPersonagem.getPosicao())){
                        Monstro monstro = (Monstro) pIesimoPersonagem;
                        if(!monstro.isImortal()){
                            umaFase.remove(pIesimoPersonagem);
                            i--; 
                        }
                    }
                }
            }
            // Verifica se o cadeado está aberto ou não A TODO O MOMENTO
            if(pIesimoPersonagem instanceof Cadeado) {
                Cadeado cadeado = (Cadeado) pIesimoPersonagem; 
                if(cadeado.isSaida() == false){
                    cadeado.verificarChaves();
                }
                else{
                    cadeado.verificarChavesSemDesaparecer();
                }
            }
            
            //Verifica colisão com outro personagem
            if(hero.getPosicao().igual(pIesimoPersonagem.getPosicao()))
                if(pIesimoPersonagem instanceof Coracao) {
                    Coracao coracao = (Coracao) pIesimoPersonagem;
                    if (!coracao.isColetada()) {
                        hero.ganharVida();
                        coracao.setColetada(true);
                        umaFase.remove(i);
                        i--;
                    }
                } else if(pIesimoPersonagem.isbMortal()) {
                    if (!hero.isInvencivel()) {
                        hero.perderVida();
                           
                        if(!hero.isVivo()) {
                            //JOptionPane.showMessageDialog(null, "Game Over!");
                            // aqui ficaria um gameOver()
                            hero.setVivo(false);
                        }
                        if(pIesimoPersonagem instanceof Bomba){
                            umaFase.remove(pIesimoPersonagem);
                            i--; // Corrige o índice após remoção
                        }
                    } 
                } else if((pIesimoPersonagem.isbTransponivel() && !(pIesimoPersonagem instanceof Arma))) {
                    umaFase.remove(pIesimoPersonagem);
                    i--; // Corrige o índice após remoção
                }
        }
    }
    
    // Personagens que NÃO SÃO HEROIS não atravessarem paredes
    public boolean ehPosicaoValidaPersonagem(ArrayList<Personagem> umaFase, Posicao p){
        Personagem pIesimoPersonagem;
        for(int i = 1; i < umaFase.size(); i++){
         pIesimoPersonagem = umaFase.get(i);
         if(pIesimoPersonagem.getPosicao().igual(p)){
             if(pIesimoPersonagem.isbTransponivel() == false)
                return false;
            }
        }
        return true;
}
    public boolean ehPosicaoValidaArma(ArrayList<Personagem> umaFase, Posicao p){
        Personagem pIesimoPersonagem;
        for(int i = 1; i < umaFase.size(); i++){
         pIesimoPersonagem = umaFase.get(i);
         if(pIesimoPersonagem.getPosicao().igual(p)){
            if ((!pIesimoPersonagem.isbTransponivel()) || pIesimoPersonagem instanceof Hero) {
                if(pIesimoPersonagem instanceof Monstro){
                    Monstro monstro = (Monstro) pIesimoPersonagem;
                    if(!(monstro.isIsTransponivelArma())){
                        return false;
                    }
                    return true;
                }
                return false;
            }
        }
        }
         return true;

    }
    
    /*Retorna true se a posicao p é válida para Hero com relacao a todos os personagens no array*/
    public boolean ehPosicaoValida(ArrayList<Personagem> umaFase, Posicao p){
        Personagem pIesimoPersonagem;
        for(int i = 1; i < umaFase.size(); i++){
            pIesimoPersonagem = umaFase.get(i);        
            if(pIesimoPersonagem.isbTransponivel()){
                if(pIesimoPersonagem.getPosicao().igual(p)){
                    if(pIesimoPersonagem instanceof Chave) {
                        Chave chave = (Chave) pIesimoPersonagem; 
                        chave.setColetada(true);
                    }
                    if(pIesimoPersonagem instanceof Cadeado){
                        Cadeado cadeado = (Cadeado) pIesimoPersonagem;
                        if(cadeado.isSaida()){
                             javax.swing.JOptionPane.showMessageDialog(null, "Fim de jogo, Você Venceu!");
                             System.exit(0);
                        }
                    }
                }
            }
            else{
                if(pIesimoPersonagem.getPosicao().igual(p))
                    return false;
            }
        }        
        return true;
    }
    
}
