package model;
public abstract class Objeto {
	private int posicaoX,posicaoY,altura,largura;

	public int getPosicaoX() {
		return posicaoX;
	}

	public void setPosicaoX(int posicaoX) {
		this.posicaoX = posicaoX;
	}

	public int getPosicaoY() {
		return posicaoY;
	}

	public void setPosicaoY(int posicaoY) {
		this.posicaoY = posicaoY;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	public int getLargura() {
		return largura;
	}

	public void setLargura(int largura) {
		this.largura = largura;
	}

	public static boolean colisao(Objeto a, Objeto b) {
		if(a ==null || b ==null)
			return false;
		int ladoEsquerdoA= a.getPosicaoX();		
		int ladoDireitoA = ladoEsquerdoA+a.getLargura();
		int ladoCimaA=a.getPosicaoY();
		int ladoBaixoA= ladoCimaA+a.getAltura();
	
		int ladoEsquerdoB= b.getPosicaoX();
		int ladoDireitoB = ladoEsquerdoB+b.getLargura();
		int ladoCimaB= b.getPosicaoY();
		int ladoBaixoB= ladoCimaB+b.getAltura();		
	
		if(ladoDireitoA>=ladoEsquerdoB && ladoCimaA<=ladoBaixoB && ladoBaixoA>=ladoCimaB && ladoEsquerdoA<=ladoDireitoB) {
			return true;
		}
		return false;
	}
}
