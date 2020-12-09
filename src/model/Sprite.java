package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import view.Tela;

public class Sprite{
	  
	BufferedImage spriteSheet;
	int width, height;
	int rows, columns;
	public BufferedImage[] sprites;
	int aparencia;
	
	//public Sprite(int aparencia, int width, int height, int columns, int rows, int posX, int posY) throws IOException {
	public Sprite(String imagemNome, int columns, int rows)  {
		try {
			spriteSheet = ImageIO.read(new File(Tela.CAMINHO+imagemNome+".png"));
		} catch (IOException e) {
			Tela.mensagemErro("Erro ao Carregar " +imagemNome+".png "+"!\nVerifique se a pasta lib esta no mesmo diretorio que esta aplicação");
		}
		//this.aparencia=aparencia;
		//this.width = width;
		//this.height = height;
		
		this.width = spriteSheet.getWidth()/columns;
		this.height = spriteSheet.getHeight()/rows;

		this.rows = columns;
		this.columns = rows;
		

		sprites = new BufferedImage[columns * rows];
		for(int i = 0; i < columns; i++) {
			for(int j = 0; j < rows; j++) {
				sprites[(i * rows) + j] = spriteSheet.getSubimage(i * width, j * height, width, height);
			}
		}
	}

	public BufferedImage[] getSprites() {
		return sprites;
	}

	public void setSprites(BufferedImage[] sprites) {
		this.sprites = sprites;
	}
}