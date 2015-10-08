package br.unb.cic.lp.gol;

import java.security.InvalidParameterException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Classe que atua como um controlador do 
 * padrao MVC, separando os componentes da 
 * camada de apresentacao e model. 
 * 
 * @author rbonifacio
 */
public class GameController {

	ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
	private GameEngine engine;
	private GameViewGUI board;
	private Statistics statistics;
	
	public GameEngine getEngine() {
		return engine;
	}
	
	public void setEngine(GameEngine engine) {
		this.engine = engine;
	}
	
	public GameViewGUI getBoard() {
		return board;
	}
	
	public void setBoard(GameViewGUI board) {
		this.board = board;
	}
	
	public void setStatistics(Statistics statistics) {
		this.statistics = statistics;
	}
	
	public void start() {
		board.selectMode();
	}
	
	public void selectRule (int rule) {
		switch (rule) {
		case 1:
			this.engine = (HighLife) context.getBean("highlife");
			break;
		case 2:
			this.engine = (LiveFreeOrDie) context.getBean("livefreeordie");
			break;
		case 3:
			this.engine = (Ameba) context.getBean("ameba");
			break;
		default:
			this.engine = (Conway) context.getBean("conway");
			break;
		}
		board.makeCellsAlive();
	}
	
	public void halt() {
		board.showStatistics (statistics.getRevivedCells(), statistics.getKilledCells());
	}
	
	public void makeCellAlive(int i, int j) {
		try {
			engine.makeCellAlive(i, j);
			//board.update();
		}
		catch(InvalidParameterException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void nextGeneration() {
		engine.nextGeneration();
		board.update();
	}
	
	public boolean isCellAlive (int i, int j) {
		return engine.isCellAlive(i, j);
	}
	
	public void setHeight (int h) {
		engine.setHeight(h);
	}
	
	public void setWidth (int w) {
		engine.setWidth(w);
	}
	
	public int getHeight () {
		return engine.getHeight();
	}
	
	public int getWidth() {
		return engine.getWidth();
	}
}
