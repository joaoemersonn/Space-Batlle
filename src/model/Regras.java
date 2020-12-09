package model;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import view.Tela;

public class Regras {
	public static Random numero = new Random();	 
	public static void setHd(boolean hd){
		if(hd){
			Tela.HD = "hd";
			Tela.ALTURA_TELA = 768;
			Tela.LARGURA_TELA = 1366;	
		}else{
			Tela.HD = "";
			Tela.ALTURA_TELA = 600;
			Tela.LARGURA_TELA = 800;
		}
	}
	public static void tocarSom(String nomeDoSom,boolean loop) {
		try {
			AudioInputStream arquivo = AudioSystem.getAudioInputStream(new File(Tela.CAMINHO+nomeDoSom+".wav").getAbsoluteFile());
			Clip som = AudioSystem.getClip();
			som.open(arquivo);
			if(loop)
				som.loop(Clip.LOOP_CONTINUOUSLY);
			else
				som.start();
		} catch (IOException e) {
			Tela.mensagemErro("Erro ao Carregar Arquivos!\nVerifique se a pasta lib esta no mesmo diretorio que esta aplicação");
		} catch (Exception e) {
			Tela.mensagemErro(e.getMessage());
		} 
	}
}
