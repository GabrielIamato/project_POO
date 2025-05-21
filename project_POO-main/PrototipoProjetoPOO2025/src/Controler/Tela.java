package Controler;

import Modelo.Personagem;
import Modelo.Bomba;
import Modelo.Caveira;
import Modelo.Parede;
import Modelo.Hero;
import Modelo.BichinhoVaiVemHorizontal;
import Modelo.Perseguidor;
import Modelo.Cadeado;
import Modelo.Chave;
import Auxiliar.Consts;
import Auxiliar.Desenho;
import Modelo.ZigueZague;
import auxiliar.Posicao;
import java.awt.FlowLayout;
import java.awt.Graphics;
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

    private Hero hero;
    private ArrayList<Personagem> faseAtual;
    private ControleDeJogo cj = new ControleDeJogo();
    private Graphics g2;
    private int cameraLinha = 0;
    private int cameraColuna = 0;

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

        
        faseAtual = new ArrayList<Personagem>();
        hero = new Hero("skoot.png");
        hero.setPosicao(4, 4);
        
        this.addPersonagem(hero);
        this.atualizaCamera();
        
        desenhaFase(1);
        /*Cria faseAtual adiciona personagens*/
        
   
        ZigueZague zz = new ZigueZague("robo.png", 5);
        zz.setPosicao(10, 10);
        this.addPersonagem(zz);

       /* BichinhoVaiVemHorizontal bBichinhoH = new BichinhoVaiVemHorizontal("roboPink.png", 5, 1);
        bBichinhoH.setPosicao(3, 3);
        this.addPersonagem(bBichinhoH);*/
        
        BichinhoVaiVemHorizontal guarda = new BichinhoVaiVemHorizontal("guardinha.jpg", 3, 3);
        guarda.setPosicao(9,10);
        guarda.setbMortal(true);
        this.addPersonagem(guarda);
       
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
        ////
       /*Perseguidor perseguidor = new Perseguidor("monstro.png", 5);
        perseguidor.setPosicao(3,6);
        this.addPersonagem(perseguidor);*/
    }
    
    private ArrayList<Posicao> gerarParedesLabirinto() {
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

    public void desenhaFase(int numFase) {
    if (numFase == 1) {
        ArrayList<Posicao> posicoesParedes = gerarParedesLabirinto();
        
        for (Posicao pos : posicoesParedes) {
            Parede parede = new Parede("parede.jpg");
            parede.setPosicao(pos.getLinha(), pos.getColuna());
            this.addPersonagem(parede);
        }
    }
    }

    public int getCameraLinha() {
        return cameraLinha;
    }

    public int getCameraColuna() {
        return cameraColuna;
    }

    public boolean ehPosicaoValida(Posicao p) {
        return cj.ehPosicaoValida(this.faseAtual, p);
    }
    
    public boolean ehPosicaoValidaPersonagem(Posicao p){
        return cj.ehPosicaoValidaPersonagem(this.faseAtual, p);
    }

    public void addPersonagem(Personagem umPersonagem) {
        faseAtual.add(umPersonagem);
    }

    public void removePersonagem(Personagem umPersonagem) {
        faseAtual.remove(umPersonagem);
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
                int mapaLinha = cameraLinha + i;
                int mapaColuna = cameraColuna + j;

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
        if (!this.faseAtual.isEmpty()) {
            this.cj.desenhaTudo(faseAtual);
            this.cj.processaTudo(faseAtual);
        }

        g.dispose();
        g2.dispose();
        if (!getBufferStrategy().contentsLost()) {
            getBufferStrategy().show();
        }
    }

    private void atualizaCamera() {
        int linha = hero.getPosicao().getLinha();
        int coluna = hero.getPosicao().getColuna();

        cameraLinha = Math.max(0, Math.min(linha - Consts.RES / 2, Consts.MUNDO_ALTURA - Consts.RES));
        cameraColuna = Math.max(0, Math.min(coluna - Consts.RES / 2, Consts.MUNDO_LARGURA - Consts.RES));
    }

    public void go() {
        TimerTask task = new TimerTask() {
            public void run() {
                 if(!hero.isVivo()){
                    gameOver();
                    return;
                }
                repaint();
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 0, Consts.PERIOD);
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_C) {
            this.faseAtual.clear();
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            hero.moveUp();
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            hero.moveDown();
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            hero.moveLeft();
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            hero.moveRight();
        }
        this.atualizaCamera();
        this.setTitle("-> Cell: " + (hero.getPosicao().getColuna()) + ", "
                + (hero.getPosicao().getLinha()));

        //repaint(); /*invoca o paint imediatamente, sem aguardar o refresh*/
    }

    public void mousePressed(MouseEvent e) {
        /* Clique do mouse desligado*/
        int x = e.getX();
        int y = e.getY();

        this.setTitle("X: " + x + ", Y: " + y
                + " -> Cell: " + (y / Consts.CELL_SIDE) + ", " + (x / Consts.CELL_SIDE));

        this.hero.getPosicao().setPosicao(y / Consts.CELL_SIDE, x / Consts.CELL_SIDE);

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
