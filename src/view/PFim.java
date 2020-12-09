package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PFim extends JPanel {
	private JButton tentar,voltar;
	private Image imagem;
	private int pontos, maior,pontosP2;
	public PFim(){		
		setLayout(null);
		tentar = new JButton(new ImageIcon(Tela.CAMINHO+"tentar.png"));
		voltar = new JButton(new ImageIcon(Tela.CAMINHO+"voltar.png"));
		imagem = new ImageIcon(Tela.CAMINHO+"telafim"+Tela.HD+".png").getImage();

		add(tentar).setBounds((Tela.LARGURA_TELA/2)-75, (Tela.ALTURA_TELA/2)+(Tela.ALTURA_TELA/6), 170, 70);
		add(voltar).setBounds((Tela.LARGURA_TELA/2)-55, (Tela.ALTURA_TELA/2)+(Tela.ALTURA_TELA/6)+80, 135, 45);
		setVisible(false);
	}

	public void paint(Graphics g){
		super.paintComponent(g);
		g.drawImage(imagem, 0, 0, null);
		g.setColor(Color.WHITE);
		g.setFont(Tela.fonte);
		g.drawString("Maior Pontuação\n "+maior,(Tela.LARGURA_TELA/2)-(Tela.LARGURA_TELA/4), (Tela.ALTURA_TELA/2)-80);
		g.setFont(Tela.fonte);
		g.drawString("Pontos Jogador 1:  "+pontos,(Tela.LARGURA_TELA/2)-(Tela.LARGURA_TELA/4), (Tela.ALTURA_TELA/2)+20);
		if(pontosP2>0)
			g.drawString("Pontos Jogador 2:  "+pontosP2,(Tela.LARGURA_TELA/2)-(Tela.LARGURA_TELA/4), (Tela.ALTURA_TELA/2)-20);
		tentar.repaint();
		voltar.repaint();
	}
	public JButton getTentar() {
		return tentar;
	}

	public JButton getVoltar() {
		return voltar;
	}
	public void setPontos(int pontos) {
		this.pontos = pontos;	
	}
	public void setMaior(int maior) {
		this.maior = maior;
	}

	public void setPontosP2(int pontosP2) {
		this.pontosP2 = pontosP2;
	}

}

