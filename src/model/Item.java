package model;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import controller.Jogo;
import view.Tela;
public class Item extends Objeto{
	private Image imagem;
	private int id,duracao=200;
	
	public Item(int id,int posX,int posY) {
		this.id = id;		
		imagem = new ImageIcon(Tela.CAMINHO+"item"+id+".png").getImage();		
		setLargura(imagem.getWidth(null));
		setAltura(imagem.getHeight(null));
		setPosicaoX(posX);
		setPosicaoY(posY);
	}
	
	public int getId() {
		return id;
	}
	
	public Image getImagem() {
		return imagem;
	}

	public int getDuracao() {
		return duracao;
	}

	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}

	public static void gerarItem(Objeto a){
		if(Regras.numero.nextInt(6)==5)
			Jogo.dropItem.add(new Item(Regras.numero.nextInt(3)+1,a.getPosicaoX(),a.getPosicaoY()));
		else if(Regras.numero.nextInt(15)==10)
			Jogo.dropItem.add(new Item(4,a.getPosicaoX(),a.getPosicaoY()));
	}
	//public static void 

	public static void itemAgir(Personagem personagem,Personagem personagem2, ArrayList<Inimigo> inimigos){
		for(int i=0;i<Jogo.dropItem.size();i++){
			Personagem player=null;
			player = Objeto.colisao(personagem,Jogo.dropItem.get(i))?personagem:player;
			player = Objeto.colisao(personagem2,Jogo.dropItem.get(i))?personagem2:player;
			if(player != null){
				Regras.tocarSom("coleta", false);
				if(Jogo.dropItem.get(i).getId()==1)
					player.setTiroDuplo(player.getTiroDuplo()+10);
				else if(Jogo.dropItem.get(i).getId()==2)
					player.setTiroTriplo(player.getTiroTriplo()+10);
				else if(Jogo.dropItem.get(i).getId()==4){
					for (int j = 0; j < inimigos.size(); j++) {
						Item.gerarItem(inimigos.get(j));
						inimigos.remove(j);
						player.pontuar();
						Regras.tocarSom("bomba",false);
					}
				}
				else if(player.getVidas()<5)
					player.setVidas(personagem.getVidas()+1);
				Jogo.dropItem.remove(i);
			}
			else{
				Jogo.dropItem.get(i).setDuracao(Jogo.dropItem.get(i).getDuracao()-1);
				if(Jogo.dropItem.get(i).getDuracao()<0)
					Jogo.dropItem.remove(i);
			}
		}
	}
	
	
	
}
