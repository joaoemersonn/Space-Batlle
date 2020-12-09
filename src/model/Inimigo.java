package model;

import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import view.Tela;

public class Inimigo extends Nave {	

	private int velocidade,posLifeX,posLifeY,espera;
	public static int chefe;
	public static boolean temBOSS=false;
	private boolean atira=false,boss;
	private Image life = new ImageIcon(Tela.CAMINHO+"barraLife.png").getImage();
	private Tiro tiro;
	public Inimigo(int vidas, ImageIcon imageIcon,int velociade) {
		super(vidas, imageIcon);
		velocidade = velociade;
	}	
	public Inimigo(int x, boolean b,int fase) {
		int posicao = 0;	
		boss=false;
		if(fase==2){
			setVidas(2);
			setImagem(new ImageIcon(Tela.CAMINHO+"inimigo2.png"));
			velocidade = 3;
		}
		else if(fase==3 && Regras.numero.nextBoolean()){
			setVidas(3);
			setImagem(new ImageIcon(Tela.CAMINHO+"inimigo3.png"));
			velocidade = 1;
			atira=true;				
			posicao = 40;
		}
		else{
			setVidas(1);
			setImagem(new ImageIcon(Tela.CAMINHO+"inimigo1.png"));
			velocidade = 2;
		}
		setAltura(getImagem().getHeight(null));
		setLargura(getImagem().getWidth(null));
		setPosicaoX(x);
		if(b)
			setPosicaoY(0+(posicao/4));
		else
			setPosicaoY(Tela.ALTURA_TELA-posicao);
		posicaoLife();
	}
	public Inimigo(){
		setPosicaoX(Tela.LARGURA_TELA/2);
		setPosicaoY(0);
		setVidas(10);
		velocidade = 3;
		atira=true;
		boss = temBOSS= true;
		setImagem(new ImageIcon(Tela.CAMINHO+"inimigo4.png"));
		posicaoLife();
	}
	public boolean isAtira() {
		return atira;
	}
	public Tiro getTiro() {
		return tiro;
	}
	public void setTiro(Tiro tiro) {
		this.tiro = tiro;
	}

	public void posicaoLife(){
		setPosLifeX(getPosicaoX()); 
		setPosLifeY(getPosicaoY()-8); 
	}
	public void mover(int x,int y){
		setPosicaoY(y);
		setPosicaoX(x);
		posicaoLife();
	}	
	public int getPosLifeX() {
		return posLifeX;
	}
	public void setPosLifeX(int posLifeX) {
		this.posLifeX = posLifeX;
	}
	public int getPosLifeY() {
		return posLifeY;
	}
	public void setPosLifeY(int posLifeY) {
		this.posLifeY = posLifeY;
	}
	public Image getLife() {
		return life;
	}
	public void setLife(Image life) {
		this.life = life;
	}
	public void setVelocidade(int velocidade) {
		this.velocidade = velocidade;
	}
	public int getVelocidade() {
		return velocidade;
	}
	public int getEspera() {
		return espera;
	}
	public void setEspera(int espera) {
		this.espera = espera;
	}

	public void setLocation(int x,boolean b){
		setPosicaoX(x);
		if(b)
			setPosicaoY(0);
		else
			setPosicaoY(Tela.ALTURA_TELA);
	}
	public void addTiro() {
		if(atira){
			if(getPosicaoY()<=(Tela.ALTURA_TELA/2))
				setTiro(new Tiro(getPosicaoX()+(getLargura()/2),getPosicaoY(),Tiro.BAIXO,"tiro2"));
			else
				setTiro(new Tiro(getPosicaoX()+(getLargura()/2),getPosicaoY(),Tiro.CIMA,"tiro2"));
		}		
	}
	public static boolean gerarInimigo(Personagem personagem,Personagem personagem2,ArrayList<Inimigo> inimigos,int cont){
		int fase=1,tempo=30,pontos=0;
		if(personagem !=null)
			pontos =pontos<personagem.getPontos()?personagem.getPontos():pontos;			
		if(personagem2 !=null)
			pontos =pontos<personagem2.getPontos()?personagem2.getPontos():pontos;
			
		if(pontos>=1000)
			fase = 2;
		if(pontos>=1500)
			fase = 3;		
		if(pontos>chefe){
			inimigos.add(new Inimigo());
			chefe += chefe;
		}
		if(inimigos.size()<(pontos/300) && cont>tempo && inimigos.size()<8 && !temBOSS||inimigos.isEmpty()){			
			inimigos.add(new Inimigo(Regras.numero.nextInt(Tela.LARGURA_TELA),Regras.numero.nextBoolean(),Regras.numero.nextInt(fase)+1));
			if(personagem2!=null && personagem != null)
				inimigos.add(new Inimigo(Regras.numero.nextInt(Tela.LARGURA_TELA),Regras.numero.nextBoolean(),Regras.numero.nextInt(fase)+1));
			if(cont>tempo)
				return true;
		}
		return false;
	}
	public static void inimigoAgir(Nave a,Nave c, ArrayList<Inimigo> inimigos ){		
		for(int i=0;i<inimigos.size();i++){
			int dirX,dirY=0,posX=0,posY=0;
			if((c != null && i%2==0)|| (a == null && c !=null) ){				
				posX = c.getPosicaoX();
				posY = c.getPosicaoY();
			}else if( a != null){
				posX = a.getPosicaoX();
				posY = a.getPosicaoY();
			}
			if(posX > inimigos.get(i).getPosicaoX())
				dirX=1;
			else if(posX < inimigos.get(i).getPosicaoX())
				dirX=-1;
			else
				dirX = 0;
			if(inimigos.get(i).isAtira()){
				inimigos.get(i).setEspera(inimigos.get(i).getEspera()+1);
				if(inimigos.get(i).getTiro() == null && inimigos.get(i).getEspera()>50){
					inimigos.get(i).setEspera(0);
					inimigos.get(i).addTiro();
				}
				if(inimigos.get(i).getTiro() !=null && inimigos.get(i).getTiro().getPosicaoY()<=Tela.ALTURA_TELA && inimigos.get(i).getTiro().getPosicaoY()>=0){
					inimigos.get(i).getTiro().mover();
					if(a != null)
						if(Objeto.colisao(inimigos.get(i).getTiro(),a)){
							a.setVidas(a.getVidas()-1);
							inimigos.get(i).setTiro(null);
							Regras.tocarSom("Explosao",false);	
						}
					if(c != null)
						if(Objeto.colisao(inimigos.get(i).getTiro(),c)){
							c.setVidas(c.getVidas()-1);
							inimigos.get(i).setTiro(null);
							Regras.tocarSom("Explosao",false);	
						}
				}else if(inimigos.get(i).getTiro() !=null)
					inimigos.get(i).setTiro(null);


			}
			if(!inimigos.get(i).isAtira() || inimigos.get(i).isBoss()){
				if(posY > inimigos.get(i).getPosicaoY())
					dirY=1;
				else if(posY < inimigos.get(i).getPosicaoY())
					dirY=-1;
				else
					dirY = 0;				
			}
			inimigos.get(i).mover(inimigos.get(i).getPosicaoX() +(inimigos.get(i).getVelocidade()*dirX),inimigos.get(i).getPosicaoY() +(inimigos.get(i).getVelocidade()*dirY));
			if(a != null)
				if(Objeto.colisao(a, inimigos.get(i))){
					a.setVidas(a.getVidas()-1);
					inimigos.get(i).setLocation(Regras.numero.nextInt(Tela.LARGURA_TELA),Regras.numero.nextBoolean());
					Regras.tocarSom("Explosao",false);						
				}
			if(c != null)
				if(Objeto.colisao(c, inimigos.get(i))){
					c.setVidas(c.getVidas()-1);
					inimigos.get(i).setLocation(Regras.numero.nextInt(Tela.LARGURA_TELA),Regras.numero.nextBoolean());
					Regras.tocarSom("Explosao",false);						
				}
		}
	}
	public boolean isBoss() {
		return boss;
	}

}
