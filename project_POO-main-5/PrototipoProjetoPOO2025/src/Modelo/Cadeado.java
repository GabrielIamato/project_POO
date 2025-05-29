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
public class Cadeado extends Personagem implements Serializable {
     
    private ArrayList<Chave> chaves;
    private boolean saida;
            
    public Cadeado(String sNomeImagePNG) {
        super(sNomeImagePNG);
        this.bTransponivel = false;
        this.bMortal = false;
        this.chaves = new ArrayList<>();
        this.saida = false;
    }

    public boolean isSaida() {
        return saida;
    }

    public void setSaida(boolean saida) {
        this.saida = saida;
    }
    
    public void adicionarChave(Chave chave) {
        this.chaves.add(chave);
    }

    public boolean verificarChavesSemDesaparecer(){
        for (Chave c : chaves) {
            if (!c.isColetada()) {
                return false; 
            }
        }
        this.setbTransponivel(true);
        return true;
    }
    public void verificarChaves() {
        if(verificarChavesSemDesaparecer()){
            this.setbVisivel(false);
        }
    }
    
    
}
