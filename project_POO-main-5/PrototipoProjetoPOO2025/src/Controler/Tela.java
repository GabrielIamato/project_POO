package Controler;

import Controler.Fase;
import Controler.Fase1;
import Modelo.Personagem;
import Modelo.Bomba;
import Modelo.Espada;
import Modelo.Caveira;
import Modelo.Parede;
import Modelo.Hero;
import Modelo.BichinhoVaiVemHorizontal;
import Modelo.Perseguidor;
import Modelo.MonstroBarreira;
import Modelo.Arma;
import Modelo.ArmaFogoEmVolta;
import Modelo.Cadeado;
import Modelo.Chave;
import Modelo.Coracao;
import Auxiliar.Consts;
import Auxiliar.Desenho;
import Modelo.ZigueZague;
import auxiliar.Posicao;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Color; // para o HUD
import java.awt.Font; //para o HUD
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import javax.swing.JButton;

public class Tela extends javax.swing.JFrame implements MouseListener, KeyListener {

    /*private Hero faseAtual.getHero();
    private Arma faseAtual.getArma();*/
    private Fase faseAtual;
   // private ArrayList<Personagem> faseAtual;
    private ControleDeJogo cj = new ControleDeJogo();
    private Graphics g2;
    /*private int cameraLinha = 0;
    private int cameraColuna = 0;*/
    private HUD hud; // adiciona a hud

    public Tela() {
        Desenho.setCenario(this);
        initComponents();
        this.addMouseListener(this);
        /*mouse*/

        this.addKeyListener(this);
        /*teclado*/
 /*Cria a janela do tamanho do tabuleiro + insets (bordas) da janela*/
        this.setSize(Consts.RES * Consts.CELL_SIDE + getInsets().left + getInsets().right,
                Consts.RES * Consts.CELL_SIDE + getInsets().top + getInsets().bottom);

        
        faseAtual = new Fase1();
        faseAtual.desenhaFase();
    }
    

    public boolean ehPosicaoValida(Posicao p) {
        return cj.ehPosicaoValida(this.faseAtual.personagens, p);
    }
    
    public boolean ehPosicaoValidaPersonagem(Posicao p){
        return cj.ehPosicaoValidaPersonagem(this.faseAtual.personagens, p);
    }
    
    public boolean ehPosicaoValidaArma(Posicao p){
        return cj.ehPosicaoValidaArma(this.faseAtual.personagens, p);
    }


    public Graphics getGraphicsBuffer() {
        return g2;
    }

    public void paint(Graphics gOld) {
        Graphics g = this.getBufferStrategy().getDrawGraphics();
        /*Criamos um contexto gráfico*/
        g2 = g.create(getInsets().left, getInsets().top, getWidth() - getInsets().right, getHeight() - getInsets().top);
        /**
         * ***********Desenha cenário de fundo*************
         */
        for (int i = 0; i < Consts.RES; i++) {
            for (int j = 0; j < Consts.RES; j++) {
                int mapaLinha = this.faseAtual.getCameraLinha() + i;
                int mapaColuna = this.faseAtual.getCameraColuna() + j;

                if (mapaLinha < Consts.MUNDO_ALTURA && mapaColuna < Consts.MUNDO_LARGURA) {
                    try {
                        Image newImage = Toolkit.getDefaultToolkit().getImage(
                                new java.io.File(".").getCanonicalPath() + Consts.PATH + "bricks.png");
                        g2.drawImage(newImage,
                                j * Consts.CELL_SIDE, i * Consts.CELL_SIDE,
                                Consts.CELL_SIDE, Consts.CELL_SIDE, null);
                    } catch (IOException ex) {
                        Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        } 
        
        if (!this.faseAtual.personagens.isEmpty()) {
            this.cj.desenhaTudo(faseAtual.personagens);
            this.cj.processaTudo(faseAtual.personagens);
        }
        
        faseAtual.getHud().draw(g2); // DESENHA O HUD NA TELA

        g.dispose();
        g2.dispose();
        if (!getBufferStrategy().contentsLost()) {
            getBufferStrategy().show();
        }
    }

    /*private void atualizaCamera() {
        int linha = faseAtual.getHero().getPosicao().getLinha();
        int coluna = faseAtual.getHero().getPosicao().getColuna();

        cameraLinha = Math.max(0, Math.min(linha - Consts.RES / 2, Consts.MUNDO_ALTURA - Consts.RES));
        cameraColuna = Math.max(0, Math.min(coluna - Consts.RES / 2, Consts.MUNDO_LARGURA - Consts.RES));
    }*/

    public void go() {
        TimerTask task = new TimerTask() {
            public void run() {
                 if(!faseAtual.getHero().isVivo()){
                    gameOver();
                    return;
                }
                 if(faseAtual.getArma() instanceof ArmaFogoEmVolta){
                     ((ArmaFogoEmVolta)faseAtual.getArma()).atualizarPosicao();
                 }
                 if(faseAtual.getArma().getChave().isColetada()){
                     faseAtual.getArma().setbVisivel(true);
                 }
                repaint();
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 0, Consts.PERIOD);
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_C) {
        this.faseAtual.personagens.clear();
    } else if (e.getKeyCode() == KeyEvent.VK_UP) {
        faseAtual.getHero().moveUp();
        if (faseAtual.getArma() instanceof Espada) {
            ((Espada) faseAtual.getArma()).atualizarPosicao('W');
        }
    } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
        faseAtual.getHero().moveDown();
        if (faseAtual.getArma() instanceof Espada) {
            ((Espada) faseAtual.getArma()).atualizarPosicao('S');
        }
    } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
        faseAtual.getHero().moveLeft();
        if (faseAtual.getArma() instanceof Espada) {
            ((Espada) faseAtual.getArma()).atualizarPosicao('A');
        }
    } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
        faseAtual.getHero().moveRight();
        if (faseAtual.getArma() instanceof Espada) {
            ((Espada) faseAtual.getArma()).atualizarPosicao('D');
        }
    } else if (e.getKeyCode() == KeyEvent.VK_W) {
        if (faseAtual.getArma() instanceof Espada) {
            ((Espada) faseAtual.getArma()).atualizarPosicao('W');
        }
    } else if (e.getKeyCode() == KeyEvent.VK_A) {
        if (faseAtual.getArma() instanceof Espada) {
            ((Espada) faseAtual.getArma()).atualizarPosicao('A');
        }
    } else if (e.getKeyCode() == KeyEvent.VK_S) {
        if (faseAtual.getArma() instanceof Espada) {
            ((Espada) faseAtual.getArma()).atualizarPosicao('S');
        }
    } else if (e.getKeyCode() == KeyEvent.VK_D) {
        if (faseAtual.getArma() instanceof Espada) {
            ((Espada) faseAtual.getArma()).atualizarPosicao('D');
        }
    }
        this.faseAtual.atualizaCamera();
        this.setTitle("-> Cell: " + (faseAtual.getHero().getPosicao().getColuna()) + ", "
                + (faseAtual.getHero().getPosicao().getLinha()));

        //repaint(); /*invoca o paint imediatamente, sem aguardar o refresh*/
    }

    public void mousePressed(MouseEvent e) {
        /* Clique do mouse desligado*/
        int x = e.getX();
        int y = e.getY();

        this.setTitle("X: " + x + ", Y: " + y
                + " -> Cell: " + (y / Consts.CELL_SIDE) + ", " + (x / Consts.CELL_SIDE));

        this.faseAtual.getHero().getPosicao().setPosicao(y / Consts.CELL_SIDE, x / Consts.CELL_SIDE);

        repaint();
    }

    public void gameOver() {
    javax.swing.JOptionPane.showMessageDialog(this, "Fim de jogo!");
    this.dispose();
    System.exit(0);
}

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("POO2023-1 - Skooter");
        setAlwaysOnTop(true);
        setAutoRequestFocus(false);
        setResizable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 561, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    public Fase getFaseAtual(){
        return this.faseAtual;
    }
    public void mouseMoved(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseDragged(MouseEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }
}
