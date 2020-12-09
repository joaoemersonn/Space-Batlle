package view;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
@SuppressWarnings("serial")
public class PInicio extends JPanel{	

	private JButton start,sair;
	private JLabel imagem;
	
	public PInicio(){
		setLayout(null);
		
		start = new JButton(new ImageIcon(Tela.CAMINHO+"start.png"));
		sair = new JButton(new ImageIcon(Tela.CAMINHO+"exit.png"));
		imagem = new JLabel(new ImageIcon(Tela.CAMINHO+"telainicio"+Tela.HD+".png"));	
		
		add(start).setBounds((Tela.LARGURA_TELA/2)-75, (Tela.ALTURA_TELA/2)+(Tela.ALTURA_TELA/6), 150, 50);
		add(sair).setBounds((Tela.LARGURA_TELA/2)-75, (Tela.ALTURA_TELA/2)+(Tela.ALTURA_TELA/6)+80, 150, 50);
		add(imagem).setBounds(0, 0, Tela.LARGURA_TELA, Tela.ALTURA_TELA);

		setVisible(true);
	}
	public JButton getStart() {
		return start;
	}	
	public JButton getSair() {
		return sair;
	}	
	public JLabel getImagem() {
		return imagem;
	}

}
