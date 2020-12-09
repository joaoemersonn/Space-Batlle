package model;
import java.awt.Image;
import javax.swing.ImageIcon;
public abstract  class Nave extends Objeto {
	private int vidas;
	private Image imagem ;	

		public Nave(int vidas,ImageIcon imageIcon) {
		this.vidas = vidas;
		this.imagem = imageIcon.getImage();
		setAltura(getImagem().getHeight(null));
		setLargura(getImagem().getWidth(null));

	}	
	public Nave() {
		this.vidas = 3;
	}
	public int getVidas() {
		return vidas;
	}
	public void setVidas(int vidas) {
		this.vidas = vidas;
	}	
	public Image getImagem() {
		return imagem;
	}
	
	public void setImagem(ImageIcon imagem) {
		this.imagem = imagem.getImage();
		setAltura(getImagem().getHeight(null));
		setLargura(getImagem().getWidth(null));
	}
	
	public void setImagem(Image imagem){
		this.imagem = imagem;			
	}
	
}
