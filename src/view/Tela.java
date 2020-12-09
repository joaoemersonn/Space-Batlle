package view;
import java.awt.Cursor;
import java.awt.DisplayMode;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
public class Tela extends JFrame {	
	private static final long serialVersionUID = 1L;

	public static int ALTURA_TELA = 600;
	public static int LARGURA_TELA = 800;
	public static String HD = new String("");	
	public static String CAMINHO = new String("lib//");
	public static Font fonteGrande = new Font(Font.SERIF,Font.BOLD,LARGURA_TELA/11);
	public static Font fonte = new Font(Font.SERIF,Font.BOLD,LARGURA_TELA/25);
	
	private PJogo jogo;
	private PInicio inicio;
	private PFim fim;
	private PStart start;
	
	public Tela(){					
		setSize(LARGURA_TELA,ALTURA_TELA);
		setResizable(false);			
		//setIgnoreRepaint(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		setUndecorated(true);
		
		jogo = new PJogo();
		inicio = new PInicio();
		fim = new PFim();
		start = new PStart();
		
		add(inicio).setBounds(0, 0, LARGURA_TELA, ALTURA_TELA);
		add(jogo).setBounds(0, 0, LARGURA_TELA, ALTURA_TELA);
		add(fim).setBounds(0, 0, LARGURA_TELA, ALTURA_TELA);	
		add(start).setBounds(0, 0, LARGURA_TELA, ALTURA_TELA);	

		setVisible(true);
	}
	public void inicializar(Boolean redmencionar){
		inicio.repaint();
		createBufferStrategy(2);
		jogo.setBuffer(getBufferStrategy());
                if(redmencionar){
                    getGraphicsConfiguration().getDevice().setFullScreenWindow(this);
		try{
        getGraphicsConfiguration().getDevice().setDisplayMode(
        new DisplayMode(LARGURA_TELA, ALTURA_TELA, 32,
        DisplayMode.REFRESH_RATE_UNKNOWN));
        }catch(Exception e){
        	mensagemErro("N?o foi Possivel Redimensionar a Tela");
        }finally{
        	inicio.repaint();
        	setSize(LARGURA_TELA, ALTURA_TELA);
        }
                }else
                    setLocationRelativeTo(null);
		
				
	}
	@SuppressWarnings("deprecation")
	public void cursorTransparente(boolean transparente){
		if(transparente){
			Image cursorImage = Toolkit.getDefaultToolkit().getImage("xparent.gif"); 
			Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImage, new Point( 0, 0), "" ); 
			setCursor( blankCursor );
		}else
			setCursor(DEFAULT_CURSOR);;
	}
	public PJogo getJogo() {
		return jogo;
	}
	public PInicio getInicio() {
		return inicio;
	}
	public PFim getFim() {
		return fim;
	}

	public void setFim(PFim fim) {
		this.fim = fim;
	}

	public static void mensagemErro(String mensagem) {
		JOptionPane.showMessageDialog(null,mensagem,"Erro", JOptionPane.ERROR_MESSAGE);
	}
        public static boolean mensgemPergunta(String titulo, String msg) {
		return JOptionPane.showConfirmDialog(null, msg, titulo,JOptionPane.YES_NO_OPTION) == 0;

	}
	public PStart getStart() {
		return start;
	}
	public void setStart(PStart start) {
		this.start = start;
	}
}	