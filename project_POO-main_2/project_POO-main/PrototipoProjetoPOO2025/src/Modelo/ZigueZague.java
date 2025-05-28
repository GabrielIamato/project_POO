package Modelo;

import Auxiliar.Desenho;
import java.util.Random;


public class ZigueZague extends Perseguidor{
    
    public ZigueZague(String sNomeImagePNG, int intervaloMovimento) {
        super(sNomeImagePNG, intervaloMovimento);
    }

    @Override
    public void autoDesenho() {
        super.autoDesenho();  // desenha sempre

        this.iContaIntervalos++;
        if (this.iContaIntervalos == this.intervaloMovimento) {
            this.iContaIntervalos = 0;

            Random rand = new Random();
            int iDirecao = rand.nextInt(4);

            if(iDirecao == 0)
                moveUp();
            else if(iDirecao == 1)
                moveDown();
            else if(iDirecao == 2)
                moveLeft();
            else
                moveRight();
        }  
}
    
}
