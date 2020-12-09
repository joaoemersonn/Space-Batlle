package controller;
import app.App;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import model.Inimigo;
import model.Item;
import model.Personagem;
import model.Regras;
import view.Tela;
public class Jogo {
    public static int  FPS = 80;
    public static boolean multiplayer;
    private int maiorPontuacao,pontosP1,pontosP2;
    private boolean parar = true,mouse;
    private int resolucao = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    
    private Tela tela;
    private Jogar jogar;
    
    public static ArrayList<Item> dropItem;
    private  Personagem jogador,jogador2;
    private ArrayList<Inimigo> inimigos;
    private Controlador controlador;
    
    
    public Jogo(){
        Regras.setHd((resolucao>=1366));
        controlador = new Controlador();
        dropItem = new ArrayList<>();
        inimigos = new ArrayList<>();
        tela = new Tela();
        tela.inicializar(App.PERGUNTA);
        
        jogar = new Jogar();
        
        jogar.start();
        
        tela.getInicio().getStart().addActionListener(controlador);
        tela.getInicio().getSair().addActionListener(controlador);
        
        tela.getStart().getJogador().addActionListener(controlador);
        tela.getStart().getJogadores().addActionListener(controlador);
        tela.getStart().getVoltar().addActionListener(controlador);
        tela.getStart().getMouse().addActionListener(controlador);
        
        tela.getJogo().addKeyListener(controlador);
        
        tela.getFim().getTentar().addActionListener(controlador);
        tela.getFim().getVoltar().addActionListener(controlador);
    }
    public void control(){
        Inimigo.chefe = 2000;
        pontosP1=0;
        pontosP2=0;
        jogador = new Personagem(1);
        if(multiplayer)
            jogador2 = new Personagem(2);
        
        if(mouse && tela.getJogo().getMouseListeners().length < 1 && tela.getJogo().getMouseMotionListeners().length < 1 ){
            tela.getJogo().addMouseListener(controlador);
            tela.getJogo().addMouseMotionListener(controlador);
        }else if(!mouse){
            tela.getJogo().removeMouseListener(controlador);
            tela.getJogo().removeMouseMotionListener(controlador);
        }
        tela.cursorTransparente(true);
        tela.getStart().setVisible(false);
        tela.getFim().setVisible(false);
        tela.getJogo().setVisible(true);
        tela.getJogo().requestFocusInWindow();
        parar = false;
    }
    public void pause(){
        tela.cursorTransparente(!parar);
        tela.getJogo().desenharPause();
    }
    public class Jogar extends Thread{
        int cont;
        public void run(){
            while(true){
                try {Thread.sleep(1000/FPS);} catch (InterruptedException e) {Tela.mensagemErro(e.getMessage());}
                if(!parar){
                    if(pontosP1>maiorPontuacao)	maiorPontuacao = pontosP1;
                    if(pontosP2>maiorPontuacao)	maiorPontuacao = pontosP2;
                    tela.getJogo().desenharJogo(jogador,jogador2,inimigos);
                    if(jogador ==null && jogador2 ==null){
                        fimJogo();
                        tela.getJogo().setVisible(false);
                        tela.getFim().setVisible(true);
                    }
                    if(jogador2!=null){
                        jogador2.andar();
                        Personagem.personagemAgir(jogador2,inimigos);
                        pontosP2 = jogador2.getPontos();
                        if(jogador2.getVidas()<1)
                            jogador2 = null;
                    }
                    if(jogador !=null){
                        //if(!mouse)
                        jogador.andar();
                        Personagem.personagemAgir(jogador,inimigos);
                        pontosP1 = jogador.getPontos();
                        if(jogador.getVidas()<1)
                            jogador = null;
                    }
                    Item.itemAgir(jogador,jogador2,inimigos);
                    Inimigo.inimigoAgir(jogador,jogador2, inimigos);
                    if(Inimigo.gerarInimigo(jogador,jogador2, inimigos, cont))cont=0;
                    cont++;
                }
                else if(tela.getJogo().isVisible())
                    pause();
            }
        }
    }
    public void fimJogo(){
        parar=true;
        if(jogador !=null || jogador2 !=null){
            jogador = null;
            jogador2 = null;
        }
        inimigos.clear();
        dropItem.clear();
        tela.cursorTransparente(false);
        tela.getFim().setPontos(pontosP1);
        tela.getFim().setPontosP2(pontosP2);
        tela.getFim().setMaior(maiorPontuacao);
        tela.getFim().repaint();
    }
    public class Controlador implements KeyListener,MouseListener,MouseMotionListener,ActionListener{
        
        public void keyPressed(KeyEvent e) {
            if(jogador != null){
                if(e.getKeyCode() == KeyEvent.VK_W)
                    jogador.setCima(true);
                if(e.getKeyCode() == KeyEvent.VK_S)
                    jogador.setBaixo(true);
                if(e.getKeyCode() == KeyEvent.VK_A)
                    jogador.setEsquerda(true);
                if(e.getKeyCode() == KeyEvent.VK_D)
                    jogador.setDireita(true);
            }
            if(jogador2 != null){
                if(e.getKeyCode() == KeyEvent.VK_UP)
                    jogador2.setCima(true);
                if(e.getKeyCode() == KeyEvent.VK_DOWN)
                    jogador2.setBaixo(true);
                if(e.getKeyCode() == KeyEvent.VK_LEFT)
                    jogador2.setEsquerda(true);
                if(e.getKeyCode() == KeyEvent.VK_RIGHT)
                    jogador2.setDireita(true);
            }
            
        }
        public void keyReleased(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_P){
                parar = !parar;
                tela.cursorTransparente(!parar);
            }
            if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
                fimJogo();
                tela.getJogo().setVisible(false);
                tela.getStart().setVisible(false);
                tela.getInicio().setVisible(false);
                tela.getFim().setVisible(true);
            }
            if(jogador != null){
                if(e.getKeyCode() == KeyEvent.VK_W)
                    jogador.setCima(false);
                if(e.getKeyCode() == KeyEvent.VK_S)
                    jogador.setBaixo(false);
                if(e.getKeyCode() == KeyEvent.VK_A)
                    jogador.setEsquerda(false);
                if(e.getKeyCode() == KeyEvent.VK_D)
                    jogador.setDireita(false);
                if(e.getKeyCode() == KeyEvent.VK_SPACE)
                    jogador.atirar();
                if(e.getKeyCode() == KeyEvent.VK_CONTROL)
                    jogador.trocarArma();
            }
            if(jogador2 !=null){
                if(e.getKeyCode() == KeyEvent.VK_UP)
                    jogador2.setCima(false);
                if(e.getKeyCode() == KeyEvent.VK_DOWN)
                    jogador2.setBaixo(false);
                if(e.getKeyCode() == KeyEvent.VK_LEFT)
                    jogador2.setEsquerda(false);
                if(e.getKeyCode() == KeyEvent.VK_RIGHT)
                    jogador2.setDireita(false);
                if(e.getKeyCode() == KeyEvent.VK_ENTER)
                    jogador2.atirar();
                if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
                    jogador2.trocarArma();
            }
            
        }
        
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==tela.getInicio().getStart()){
                tela.getInicio().setVisible(false);
                tela.getStart().setVisible(true);
            }
            if(e.getSource() == tela.getStart().getJogador()){
                multiplayer = false;
                control();
            }
            if(e.getSource() == tela.getStart().getJogadores() ){
                multiplayer = true;
                control();
            }
            if(e.getSource() == tela.getFim().getTentar())
                control();
            
            if(e.getSource() == tela.getStart().getMouse()){
                mouse = !mouse;
                if(!mouse){
                    tela.getStart().getMouse().setText("Mouse Desativado");
                    tela.getStart().getMouse().setBackground(Color.GRAY);
                }else{
                    tela.getStart().getMouse().setText("Mouse Ativado");
                    tela.getStart().getMouse().setBackground(Color.ORANGE);
                }
                
            }
            if(e.getSource() == tela.getStart().getVoltar() || e.getSource() == tela.getFim().getVoltar()){
                tela.getStart().setVisible(false);
                tela.getFim().setVisible(false);
                tela.getStart().setVisible(false);
                tela.getInicio().setVisible(true);
            }
            if(e.getSource() == tela.getInicio().getSair())
                System.exit(1);
            
        }
        public void mouseReleased(MouseEvent e) {
            if(jogador != null)
                jogador.atirar();
        }
        boolean mova;
        public void mouseMoved(MouseEvent e) {
            if(!parar && jogador !=null){
                mova = !mova;
                if(mova){
                    jogador.mover(e.getX(), e.getY());
                    tela.getJogo().desenharPersonagem(jogador);
                }
            }
        }
        public void mouseDragged(MouseEvent e){
            if(!parar && jogador !=null){
                mova = !mova;
                if(mova){
                    jogador.mover(e.getX(), e.getY());
                    tela.getJogo().desenharPersonagem(jogador);
                }
            }
        }
        public void keyTyped(KeyEvent e) {}
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
        public void mouseClicked(MouseEvent e) {}
        public void mousePressed(MouseEvent e) {}
    }
}
