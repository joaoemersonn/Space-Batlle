package view;
import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
@SuppressWarnings("serial")
public class PStart extends JPanel{
	private JButton jogador,jogadores,voltar,mouse;
	private JLabel imagem;
	private Font fonte = new Font(Font.SANS_SERIF,Font.BOLD,20);
	public PStart(){
		setLayout(null);
		jogador = new JButton("1 Jogador");
		jogadores = new JButton("2 Jogadores");
		voltar = new JButton(new ImageIcon(Tela.CAMINHO+"voltar.png"));
		mouse = new JButton("Mouse Desativado");
		mouse.setBackground(Color.GRAY);
		
		jogador.setFont(fonte);
		jogador.setForeground(Color.WHITE);
		jogador.setBackground(Color.blue);
		jogadores.setFont(fonte);
		jogadores.setForeground(Color.WHITE);
		jogadores.setBackground(Color.GREEN);
		
		
		imagem = new JLabel(new ImageIcon(Tela.CAMINHO+"ajuda"+Tela.HD+".png")); 
		add(jogador).setBounds((Tela.LARGURA_TELA/2)-170, (Tela.ALTURA_TELA/2)+(Tela.ALTURA_TELA/6)+80, 160, 50);
		add(mouse).setBounds(50, (Tela.ALTURA_TELA/2)+(Tela.ALTURA_TELA/6)+90, 150, 50);
		add(jogadores).setBounds((Tela.LARGURA_TELA/2), (Tela.ALTURA_TELA/2)+(Tela.ALTURA_TELA/6)+80, 160, 50);
		add(voltar).setBounds((Tela.LARGURA_TELA/2)-75, (Tela.ALTURA_TELA/2)+(Tela.ALTURA_TELA/6)+160, 135, 45);
		add(imagem).setBounds(0, 0, Tela.LARGURA_TELA, Tela.ALTURA_TELA);
		setVisible(false);
	}
	public JButton getJogador() {
		return jogador;
	}
	public void setJogador(JButton jogador) {
		this.jogador = jogador;
	}
	public JButton getJogadores() {
		return jogadores;
	}
	public void setJogadores(JButton jogadores) {
		this.jogadores = jogadores;
	}
	public JButton getVoltar() {
		return voltar;
	}
	public void setVoltar(JButton voltar) {
		this.voltar = voltar;
	}
	public JLabel getImagem() {
		return imagem;
	}
	public void setImagem(JLabel imagem) {
		this.imagem = imagem;
	}
	public JButton getMouse() {
		return mouse;
	}
	public void setMouse(JButton mouse) {
		this.mouse = mouse;
	}
}
