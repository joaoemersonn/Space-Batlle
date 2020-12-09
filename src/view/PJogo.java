package view;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import controller.Jogo;
import model.Inimigo;
import model.Item;
import model.Personagem;
import model.Sprite;
@SuppressWarnings("serial")
public class PJogo extends JPanel{	
	private Image background = new ImageIcon(Tela.CAMINHO+"cenario"+Tela.HD+".png").getImage();	
	private Image slot = new ImageIcon(Tela.CAMINHO+"slot.png").getImage();
	private Sprite vidas = new Sprite("coracao",2,1);
	private int p1pontos,p2pontos;
	BufferStrategy buffer;

	public PJogo(){
		setLayout(null);
		setIgnoreRepaint(true);
		setFocusable(true);
		setVisible(false);
	}

	public BufferStrategy getBuffer() {
		return buffer;
	}

	public void setBuffer(BufferStrategy buffer) {
		this.buffer = buffer;
	}

	public void desenharJogo(Personagem personagem,Personagem personagem2,ArrayList<Inimigo> inimigos) {
		Graphics2D g =(Graphics2D)buffer.getDrawGraphics();		
		g.setFont(Tela.fonte);
		g.drawImage(background,0,0,null);
		//g.setColor(Color.BLACK);		
		for(Inimigo ini: inimigos){
			g.drawImage(ini.getImagem(), ini.getPosicaoX(), ini.getPosicaoY(), null);
			if(ini.isAtira() && (ini.getTiro() != null))
				g.drawImage(ini.getTiro().getImagem(),ini.getTiro().getPosicaoX(),ini.getTiro().getPosicaoY(),null);
			for(int i=0;i <ini.getVidas();i++)
				g.drawImage(ini.getLife(), ini.getPosLifeX()+(i*ini.getLife().getWidth(null)), ini.getPosLifeY(), null);
		}
		for(Item item:Jogo.dropItem){
			g.drawImage(item.getImagem(), item.getPosicaoX(), item.getPosicaoY(), null);
		}
		if(personagem != null){
			Image arma = new ImageIcon(Tela.CAMINHO+"arma"+personagem.getArmaSelecionada()+".png").getImage();
			g.drawImage(personagem.getImagem(), personagem.getPosicaoX(), personagem.getPosicaoY(), null);
			for(int i=0;i<personagem.getTiros().size();i++)
				g.drawImage(personagem.getTiros().get(i).getImagem(),personagem.getTiros().get(i).getPosicaoX(),personagem.getTiros().get(i).getPosicaoY(),null);
			p1pontos = personagem.getPontos();
			int v1 = personagem.getVidas(),d;
			d = v1>3?v1:3;
			for(int i=0;i<d;i++){
				if(v1>0) {g.drawImage(vidas.sprites[1],60*i,10,null);v1--;}				
				else g.drawImage(vidas.sprites[0],60*i,10,null);
			}			
			
			g.drawImage(new ImageIcon(Tela.CAMINHO+"slot.png").getImage(), 31, 170, null);
			g.drawImage(arma, 30, 80, null);
			if(personagem.getTiroDuplo()>0){
				g.drawImage(new ImageIcon(Tela.CAMINHO+"item1.png").getImage(), 30, 170, null);
				g.drawString(""+personagem.getTiroDuplo(), 30, 210);
			}
			if(personagem.getTiroTriplo()>0){
				g.drawImage(new ImageIcon(Tela.CAMINHO+"item2.png").getImage(), 80, 170, null);
				g.drawString(""+personagem.getTiroTriplo(), 80, 210);
			}
		}
		if(personagem2 != null){
			Image arma = new ImageIcon(Tela.CAMINHO+"arma"+personagem2.getArmaSelecionada()+".png").getImage();
			g.drawImage(personagem2.getImagem(), personagem2.getPosicaoX(), personagem2.getPosicaoY(), null);
			for(int i=0;i<personagem2.getTiros().size();i++)
				g.drawImage(personagem2.getTiros().get(i).getImagem(),personagem2.getTiros().get(i).getPosicaoX(),personagem2.getTiros().get(i).getPosicaoY(),null);
			p2pontos = personagem2.getPontos();
			int v2 = personagem2.getVidas(),d;
			d = v2>3?v2:3;
			for(int i=1;i<=d;i++){
				if(v2>0) {g.drawImage(vidas.sprites[1],Tela.LARGURA_TELA-60*i,10,null);v2--;}				
				else g.drawImage(vidas.sprites[0],Tela.LARGURA_TELA-60*i,10,null);
			}			
			g.drawImage(slot, Tela.LARGURA_TELA-slot.getHeight(null)-79, 170, null);
			g.drawImage(arma, Tela.LARGURA_TELA-arma.getHeight(null)-30, 80, null);
			if(personagem2.getTiroDuplo()>0){
				Image item1 = new ImageIcon(Tela.CAMINHO+"item1.png").getImage();
				g.drawImage(item1, Tela.LARGURA_TELA-item1.getHeight(null)-30, 170, null);
				g.drawString(""+personagem2.getTiroDuplo(), Tela.LARGURA_TELA-63, 210);
			}
			if(personagem2.getTiroTriplo()>0){
				Image item2 = new ImageIcon(Tela.CAMINHO+"item2.png").getImage();
				g.drawImage(item2, Tela.LARGURA_TELA-item2.getHeight(null)-80, 170, null);
				g.drawString(""+personagem2.getTiroTriplo(), Tela.LARGURA_TELA-123, 210);
			}
		}
		g.setColor(Color.WHITE);
		if(!Jogo.multiplayer)
			p2pontos = 0;
		if(personagem != null || p1pontos>0)
			g.drawString("P1 "+p1pontos, 5, Tela.ALTURA_TELA-5);
		if(personagem2 != null || p2pontos>0)
			g.drawString("P2 "+p2pontos, Tela.LARGURA_TELA-120, Tela.ALTURA_TELA-5);
		g.dispose();
		buffer.show();


	}
	public void desenharPersonagem(Personagem personagem) {
		Graphics2D g = (Graphics2D)buffer.getDrawGraphics();			
		g.drawImage(personagem.getImagem(), personagem.getPosicaoX(), personagem.getPosicaoY(), null);
		g.dispose();
		buffer.show();
	}
	public void desenharPause(){
		Graphics2D g = (Graphics2D)buffer.getDrawGraphics();	
		g.setColor(Color.WHITE);
		g.setFont(Tela.fonteGrande);
		g.drawString("   PAUSADO", (Tela.LARGURA_TELA/2)-(Tela.fonteGrande.getSize()*3), Tela.ALTURA_TELA/2);
		g.dispose();
		buffer.show();
	}
}
