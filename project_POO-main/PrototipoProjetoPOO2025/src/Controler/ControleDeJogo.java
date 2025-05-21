package Controler;

import Modelo.Personagem;
import Modelo.Hero;
import Modelo.Cadeado;
import Modelo.Chave;
import Modelo.Perseguidor;
import auxiliar.Posicao;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ControleDeJogo {
    public void desenhaTudo(ArrayList<Personagem> e){
        Posicao posicaoHeroi = e.get(0).getPosicao();  // Assumindo que o herói é o primeiro da lista

        for (int i = 0; i < e.size(); i++) {
            Personagem p = e.get(i);

            if (p instanceof Perseguidor) {
                ((Perseguidor)p).autoDesenho(posicaoHeroi);
            } else {
                p.autoDesenho();
          }
    }
    }
    public void processaTudo(ArrayList<Personagem> umaFase){
        Hero hero = (Hero)umaFase.get(0);
        Personagem pIesimoPersonagem;
        for(int i = 1; i < umaFase.size(); i++){
            pIesimoPersonagem = umaFase.get(i);
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
                if(pIesimoPersonagem.isbMortal()) {
                    hero.setVivo(false); // Exibe a tela e encerra o jogo
                    //Caso o personagem seja transponível, COME ELE
                } else if(pIesimoPersonagem.isbTransponivel()) {
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
