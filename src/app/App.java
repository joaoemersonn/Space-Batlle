package app;
import controller.Jogo;
import model.Regras;
import view.Tela;
public class App {
    public static boolean PERGUNTA = Tela.mensgemPergunta("Deseja Tentar Redimencionar?", "Deseja Tentar Redimencionar? \n Caso o redimensionamento nao funcione tente iniciar o app novamente!");
    private static Jogo JOGO;
    public static void main(String[] args) {		
		JOGO = new Jogo();
		Regras.tocarSom("somF", true);		
	}
}
	


