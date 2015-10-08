package br.unb.cic.lp.gol;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameViewGUI implements ActionListener{
	
	private GameController controller;
	private JFrame cellsSelection = new JFrame ("Game of Life - Make Cell Alive");
	private JFrame modeSelect = new JFrame ("Game of Lide - Seleção de modo");
	private JFrame gameTable = new JFrame ("Game of Life");
	private JFrame gameEnd = new JFrame ("End of Game of Life");
	private JButton conway = new JButton("Conway");
	private JButton highlife = new JButton("HighLife");
	private JButton liveFreeOrDie = new JButton ("Live Free Or Die");
	private JButton ameba = new JButton ("Ameba");
	private JButton make = new JButton("Make Cell Alive");
	private JButton next = new JButton("Next Generation");
	private JButton halt = new JButton("Halt");
	private JButton finish = new JButton("Finish");
	private JButton ok = new JButton("Ok");
	private int width;
	private int height;
	private JCheckBox[][] celulas;
	
	public GameViewGUI(GameController controller) {
		this.controller = controller;
	}
	
	public void setWidth (int w) {
		this.width = w;
	}
	
	public void setHeight (int h) {
		this.height = h;
	}
	
	public void selectMode () {
		Container janela, flow1, flow2, flow;
		JLabel texto = new JLabel();

		janela = modeSelect.getContentPane();

		flow = new JPanel();
		flow.setLayout(new FlowLayout ());
		
		flow1 = new JPanel();
		flow1.setLayout(new FlowLayout ());
		texto.setText("Selecione o modo do Game of Life:");
		flow1.add(texto);
		
		flow2 = new JPanel();
		flow2.setLayout(new FlowLayout ());
		flow2.add(conway);
		flow2.add(highlife);
		flow2.add(liveFreeOrDie);
		flow2.add(ameba);
		
		highlife.addActionListener(this);
		liveFreeOrDie.addActionListener(this);
		conway.addActionListener(this);
		ameba.addActionListener(this);
		
		janela.setLayout(new GridLayout(3, 1));
		janela.add (flow);
		janela.add (flow1);
		janela.add (flow2);
		
		modeSelect.setSize(400, 200);
		modeSelect.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		modeSelect.setVisible(true);
		modeSelect.setLocationRelativeTo(null);  // Abre a janela no centro da tela
	}
	
	public void makeCellsAlive () {
		int i, j;
		Container border, grid, flow;

		this.width = controller.getWidth();
		this.height = controller.getHeight();
		
		celulas = new JCheckBox[this.width][this.height];
		border = cellsSelection.getContentPane();
		
		grid = new JPanel();
		grid.setLayout(new GridLayout(height + 1, width + 1));
		grid.add(new JLabel());
		for (i = 1; i < width + 1; i++) {
			grid.add(new JLabel(Integer.toString(i)));
		}
		for (i = 0; i < height; i++) {
			for (j = 0; j < width; j++) {	
				if (j == 0) {
					grid.add(new JLabel(Integer.toString(i + 1)));
				}
				celulas[i][j] = new JCheckBox();
				celulas[i][j].addItemListener(new CheckListener());
				grid.add(celulas[i][j]);
			}
		}
		
		flow = new JPanel();
		flow.setLayout(new FlowLayout());
		flow.add(ok);
		
		ok.addActionListener(this);
		
		border.setLayout(new BorderLayout(5, 5));
		border.add(BorderLayout.CENTER, grid);
		border.add(BorderLayout.SOUTH, flow);
		
		cellsSelection.setSize(800, 600);
		cellsSelection.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cellsSelection.setVisible(true);
		cellsSelection.setLocationRelativeTo(null);
	}
	
	public void update () {
		int i, j;
		Container border, grid, flow;
		BufferedImage caveira;
		BufferedImage lifeUp;
		
		try {
			caveira = ImageIO.read(new File(new File(".").getCanonicalPath() + 
					"\\src\\resources\\caveira.GIF"));
			lifeUp = ImageIO.read(new File(new File(".").getCanonicalPath() + 
					"\\src\\resources\\lifeUp.GIF"));
		
			border = gameTable.getContentPane();
		
			grid = new JPanel();
			grid.setLayout(new GridLayout(height + 1, width + 1));
			grid.add(new JLabel());
			for (i = 1; i < width + 1; i++) {
				grid.add(new JLabel("      " + Integer.toString(i)));
			}
			for (i = 0; i < this.height; i++) {
				for (j = 0; j < this.width; j++) {
					if (j == 0) {
						grid.add(new JLabel("      " + Integer.toString(i + 1)));
					}
					if (controller.isCellAlive(i, j)) {
						grid.add(new JLabel(new ImageIcon (lifeUp)));
					}
					else {
						grid.add(new JLabel(new ImageIcon (caveira)));
					}
				}
			}
		
			flow = new JPanel();
			flow.setLayout(new FlowLayout());
			next.setBackground(new Color (0, 100, 0));
			make.setBackground(new Color (230, 230, 0));
			halt.setBackground(new Color (255, 0, 0));
			next.setForeground(new Color (1, 1, 1));
			make.setForeground(new Color (1, 1, 1));
			halt.setForeground(new Color (1, 1, 1));
			flow.add(halt);
			flow.add(make);
			flow.add(next);
		
			make.addActionListener(this);
			halt.addActionListener(this);
			next.addActionListener(this);
			
			border.setLayout(new BorderLayout(5, 5));
			border.add(BorderLayout.CENTER, grid);
			border.add(BorderLayout.SOUTH, flow);
		
			gameTable.setSize(800, 600);
			gameTable.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			gameTable.setVisible(true);
			gameTable.setLocationRelativeTo(null);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Erro de carregamento de imagem.");
		}
	}
	
	public void showStatistics (int revived, int killed) {
		Container janela, flow1, flow2, flow3, flow4;
		JLabel texto1 = new JLabel();
		JLabel texto2 = new JLabel();
		
		janela = gameEnd.getContentPane();
		
		flow1 = new JPanel();
		flow1.setLayout(new FlowLayout());
		
		flow2 = new JPanel();
		flow2.setLayout(new FlowLayout());
		texto1.setText("Células ressuscitadas: " + Integer.toString(revived));
		flow2.add(texto1);
		
		flow3 = new JPanel();
		flow3.setLayout(new FlowLayout());
		texto2.setText("Células aniquiladas: " + Integer.toString(killed));
		flow3.add(texto2);
		
		flow4 = new JPanel();
		flow4.setLayout(new FlowLayout());
		flow4.add(finish);
		
		finish.addActionListener(this);
		
		janela.setLayout(new GridLayout(4, 1));
		janela.add(flow1);
		janela.add(flow2);
		janela.add(flow3);
		janela.add(flow4);
		
		gameEnd.setSize(400, 200);
		gameEnd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameEnd.setVisible(true);
		gameEnd.setLocationRelativeTo(null);  // Abre a janela no centro da tela
	}

	public class CheckListener implements ItemListener {

		/**
		* Método que de fato verifica quais quadrados foram selecionados.
		* 
		*  @param E ItemEvent
		*/
		@Override
		public void itemStateChanged(ItemEvent E) {
			int i, j;
			for (i = 0; i < height; i++) {
				for (j = 0; j < width; j++) {
					if (celulas[i][j].isSelected()) {
						controller.makeCellAlive(i, j);
					}
				}
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent evento) {
		if (evento.getSource() == conway) {
			modeSelect.dispose();
			controller.selectRule(4);
		}
		else if (evento.getSource() == highlife){
			modeSelect.dispose();
			controller.selectRule(1);
		}
		else if (evento.getSource() == liveFreeOrDie){
			modeSelect.dispose();
			controller.selectRule(2);
		}
		else if (evento.getSource() == ameba){
			modeSelect.dispose();
			controller.selectRule(3);
		}
		else if (evento.getSource() == ok){
			cellsSelection.dispose();
			controller.nextGeneration();
		}
		else if (evento.getSource() == make){
			gameTable.dispose();
			makeCellsAlive();
		}
		else if (evento.getSource() == next){
			gameTable.dispose();
			controller.nextGeneration();
		}
		else if (evento.getSource() == halt){ // Halt
			gameTable.dispose();
			controller.halt();
		}
		else { // End of the game
			gameEnd.dispose();
			System.exit(0);
		}
	}
}
