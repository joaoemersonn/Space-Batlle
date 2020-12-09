package model;

import java.util.ArrayList;
import java.util.List;

import view.Tela;

public class Personagem extends Nave {
	private boolean cima, baixo, esquerda, direita;
	private int pontos,tiroDuplo,tiroTriplo,armaSelecionada,velocidade=9,armaAnterior=-1,d,e,mouse;
	private List<Tiro> tiros;
	public List<Tiro> getTiros() {
		return tiros;
	}
	public void setTiros(List<Tiro> tiros) {
		this.tiros = tiros;
	}
	private Sprite sprite;	

	public Personagem(int jogador){
		sprite = new Sprite("nave"+jogador,7,2);
		setImagem(sprite.sprites[0]);
		setTiros(new ArrayList<Tiro>());
		setVidas(3);
		setPosicaoX(Tela.LARGURA_TELA/2);
		setPosicaoY(Tela.ALTURA_TELA/2);
		setAltura(sprite.height);
		setLargura(sprite.width);
	}
	public void mover(int x, int y) {		
		if(x<Tela.LARGURA_TELA){
//			if(x>getPosicaoX()){
//				if(mouse<6)
//					mouse+=2;
//			}else{
//				if(mouse<12){
//					if(mouse<6)
//						mouse=6;
//					mouse+=2;
//				}
//			}				
			setPosicaoX(x-(getLargura()/2));
		}if(y<Tela.ALTURA_TELA){
			setPosicaoY(y-(getAltura()/2));
			atualizarDirecao();
		}
		setImagem(sprite.sprites[sprite.aparencia+mouse]);
		if(mouse>0)
			mouse-=2;
	}
	public void andar(){
		int aparencia;
		if(cima && getPosicaoY()>0){
			setPosicaoY(getPosicaoY()-getVelocidade());			
			atualizarDirecao();
		}
		if(baixo && getPosicaoY()<Tela.ALTURA_TELA-getAltura()){
			setPosicaoY(getPosicaoY()+getVelocidade());
			atualizarDirecao();			
		}
		if(esquerda && getPosicaoX()>0){
			setPosicaoX(getPosicaoX()-getVelocidade());						
			if(e<12){
				if(e<6)e=6;
				e+=2;
			}		
		}else
			if(e>0)
				e-=2;	

		if(direita&& getPosicaoX()<Tela.LARGURA_TELA-getLargura()){
			setPosicaoX(getPosicaoX()+getVelocidade());	
			if(d<6)
				d+=2;		
		}else
			if(d>0)
				d-=2;
		if(esquerda)
			aparencia = e;
		else
			aparencia = d;

		setImagem(sprite.sprites[sprite.aparencia+aparencia]);
	}

	public void atualizarDirecao(){
		if(getPosicaoY()<=(Tela.ALTURA_TELA/2)-(getAltura()/2))
			sprite.aparencia = 1;
		//setImagem(sprite.sprites[1]);
		else
			sprite.aparencia = 0;
		//setImagem(sprite.sprites[0]);
	}
	public void pontuar(){
		pontos+=50;
	}
	public int getPontos() {
		return pontos;
	}
	public void addTiro(){
		if(getPosicaoY()<=(Tela.ALTURA_TELA/2)-(getImagem().getHeight(null)/2))
			getTiros().add(new Tiro(getPosicaoX()+(getImagem().getWidth(null)/2)-8,getPosicaoY()+(getImagem().getHeight(null)/2)+(getImagem().getHeight(null)/6)+8,Tiro.BAIXO,"tiro"));
		else
			getTiros().add(new Tiro(getPosicaoX()+(getImagem().getWidth(null)/2)-8,getPosicaoY()-(getImagem().getHeight(null)/2)-(getImagem().getHeight(null)/6),Tiro.CIMA,"tiro"));

	}	
	public void addTiroDublo(){
		if(getPosicaoY()<=(Tela.ALTURA_TELA/2)-(getImagem().getHeight(null)/2)){
			getTiros().add(new Tiro(getPosicaoX()+(getImagem().getWidth(null)/2)-8,getPosicaoY()+(getImagem().getHeight(null)/2)+(getImagem().getHeight(null)/6)+8,Tiro.BAIXO,Tiro.ESQUERDA,"tiro"));
			getTiros().add(new Tiro(getPosicaoX()+(getImagem().getWidth(null)/2)-8,getPosicaoY()+(getImagem().getHeight(null)/2)+(getImagem().getHeight(null)/6)+8,Tiro.BAIXO,Tiro.DIREITA,"tiro"));
		}else{
			getTiros().add(new Tiro(getPosicaoX()+(getImagem().getWidth(null)/2)-8,getPosicaoY()-(getImagem().getHeight(null)/2)-(getImagem().getHeight(null)/6),Tiro.CIMA,Tiro.ESQUERDA,"tiro"));
			getTiros().add(new Tiro(getPosicaoX()+(getImagem().getWidth(null)/2)-8,getPosicaoY()-(getImagem().getHeight(null)/2)-(getImagem().getHeight(null)/6),Tiro.CIMA,Tiro.DIREITA,"tiro"));
		}
	}
	public void atirar(){		
		if(getTiroTriplo()>0 && getArmaSelecionada()==2){			
			addTiroDublo();
			addTiro();
			setTiroTriplo(getTiroTriplo()-1);
			if(getArmaSelecionada()==2 && getTiroTriplo()==0)
				setArmaSelecionada(0);
		}else if(getTiroDuplo()>0 && getArmaSelecionada()==1){
			addTiroDublo();
			setTiroDuplo(getTiroDuplo()-1);
			if(getArmaSelecionada()==1 && getTiroDuplo()==0)
				setArmaSelecionada(0);
		}else{
			addTiro();
		}		
		Regras.tocarSom("tiro",false);
	}
	public void trocarArma(){
		if(getArmaSelecionada() == 0){			
			if(getTiroTriplo()>0 )				
				setArmaSelecionada(2);
			if(getTiroDuplo()>0 && (1 !=armaAnterior || getTiroTriplo()<1))				
				setArmaSelecionada(1);			
		}else{
			armaAnterior = getArmaSelecionada();
			setArmaSelecionada(0);
		}
	}

	//public void limparTiro(){
	//	getTiros().clear();
	//}	
	public void setPontos(int pontos) {
		this.pontos = pontos;
	}
	public int getTiroDuplo() {
		return tiroDuplo;
	}
	public void setTiroDuplo(int tiroDuplo) {
		this.tiroDuplo = tiroDuplo;
	}
	public int getTiroTriplo() {
		return tiroTriplo;
	}
	public void setTiroTriplo(int tiroTriplo) {
		this.tiroTriplo = tiroTriplo;
	}
	public int getArmaSelecionada() {
		return armaSelecionada;
	}
	public void setArmaSelecionada(int armaSelecionada) {
		this.armaSelecionada = armaSelecionada;
	}
	public int getVelocidade() {
		return velocidade;
	}
	public void setVelocidade(int velocidade) {
		this.velocidade = velocidade;
	}
	public boolean isCima() {
		return cima;
	}
	public void setCima(boolean cima) {
		this.cima = cima;
	}
	public boolean isBaixo() {
		return baixo;
	}
	public void setBaixo(boolean baixo) {
		this.baixo = baixo;
	}
	public boolean isEsquerda() {
		return esquerda;
	}
	public void setEsquerda(boolean esquerda) {
		this.esquerda = esquerda;
	}
	public boolean isDireita() {
		return direita;
	}
	public void setDireita(boolean direita) {
		this.direita = direita;
	}
	public static void personagemAgir(Personagem personagem,ArrayList<Inimigo> inimigos){		
		for(int i=0;i<personagem.getTiros().size();i++){
			if(personagem.getTiros().get(i).getPosicaoY()>=0 && personagem.getTiros().get(i).getPosicaoY()<=Tela.ALTURA_TELA){	
				personagem.getTiros().get(i).mover();
				for(int j=0; j<inimigos.size();j++)
					if(Objeto.colisao(personagem.getTiros().get(i), inimigos.get(j))){
						inimigos.get(j).setVidas(inimigos.get(j).getVidas()-personagem.getTiros().get(i).getDano());
						if(inimigos.get(j).isBoss()){
							personagem.getTiros().get(i).setPosicaoY(-1);;
							//i++;
						}
						if(inimigos.get(j).getVidas()<=0){
							Item.gerarItem(inimigos.get(j));
                                                        if(inimigos.get(j).isBoss())
                                                            Inimigo.temBOSS = false;
							inimigos.remove(j);
						}
						Regras.tocarSom("hit",false);
						personagem.pontuar();
						
					}
			}else
				personagem.getTiros().remove(i);			
		}


	}



}
