package Modelo;

import auxiliar.Posicao;
import Auxiliar.Desenho;
import Modelo.Chave;
import java.io.Serializable;
public class Espada extends Arma implements Serializable {

    private char ultimaTecla = 'S'; // posição inicial da espada (baixo)

    public Espada(String sNomeImagePNG, Hero heroi, Chave chave) {
        super(sNomeImagePNG, heroi, chave);
    }

    // Atualiza a posição da espada em volta do herói conforme última tecla
    public void atualizarPosicao(char tecla) {
        this.ultimaTecla = tecla;
        
        int linhaHeroi = heroi.getPosicao().getLinha();
        int colunaHeroi = heroi.getPosicao().getColuna();

        switch(Character.toUpperCase(tecla)) {
            case 'W': // espada acima do herói
                pPosicao.setPosicao(linhaHeroi - 1, colunaHeroi);
                break;
            case 'A': // espada à esquerda do herói
                pPosicao.setPosicao(linhaHeroi, colunaHeroi - 1);
                break;
            case 'S': // espada abaixo do herói
                pPosicao.setPosicao(linhaHeroi + 1, colunaHeroi);
                break;
            case 'D': // espada à direita do herói
                pPosicao.setPosicao(linhaHeroi, colunaHeroi + 1);
                break;
            default:
                // mantém posição atual
                break;
        }

        validaPosicao(); // para garantir que não sai da tela ou posição válida
    }

    public char getUltimaTecla() {
        return ultimaTecla;
    }

    // Método para desenhar a espada
    public void desenhar() {
        if(iImage != null && pPosicao != null) {
            Desenho.desenhar(iImage, pPosicao.getColuna(), pPosicao.getLinha());
        }
    }
}
