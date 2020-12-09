package model;

import java.awt.Image;

import javax.swing.ImageIcon;

import view.Tela;

public class Tiro extends Objeto{
	public static int CIMA = -1;
	public static int BAIXO = 1;
	public static int ESQUERDA = -1;
	public static int DIREITA = 1;
	
	private int velocidade=80,dano=1,direcaoY,direcaoX;
	private Image imagem;
	
	
	public Tiro(int x,int y,int dir,String image){
		imagem = new ImageIcon(Tela.CAMINHO+image+".png").getImage();
		setPosicaoX(x);
		setPosicaoY(y);
		direcaoY=dir;
		setAltura(imagem.getHeight(null));
		setLargura(imagem.getWidth(null));
	}	
	
	

	public Tiro(int x,int y,int dirY,int dirX,String image){
		imagem = new ImageIcon(Tela.CAMINHO+image+".png").getImage();		
		setPosicaoX(x);
		setPosicaoY(y);
		setAltura(imagem.getHeight(null));
		setLargura(imagem.getWidth(null));
		direcaoY=dirY;
		direcaoX = dirX;
	}
	public Image getImagem() {
		return imagem;
	}
	public void mover(){	
		setPosicaoY(getPosicaoY()+(velocidade*direcaoY));
		setPosicaoX(getPosicaoX()+((velocidade*direcaoX)/4));
	}	
	public int getVelocidade() {
		return velocidade;
	}

	public void setVelocidade(int velocidade) {
		this.velocidade = velocidade;
	}

	public int getDano() {
		return dano;
	}

	public void setDano(int dano) {
		this.dano = dano;
	}
}
