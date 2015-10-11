package br.unb.cic.lp.gol;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JButton;
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
	private JButton next = new JButton("Next Generation");
	private JButton halt = new JButton("Halt");
	private JButton finish = new JButton("Finish");
	private JButton ok = new JButton("Ok");
	private int width;
	private int height;
	private JButton[][] celulas;
	private Container border, grid, flow;
	
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
		Container janela, flow1, flow2, flow3;
		JLabel texto = new JLabel();

		janela = modeSelect.getContentPane();

		flow3 = new JPanel();
		flow3.setLayout(new FlowLayout ());
		
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
		janela.add (flow3);
		janela.add (flow1);
		janela.add (flow2);
		
		modeSelect.setSize(400, 200);
		modeSelect.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		modeSelect.setVisible(true);
		modeSelect.setLocationRelativeTo(null);  // Abre a janela no centro da tela
	}
	

	public void setTable() {
		int i, j;
		
		this.width = controller.getWidth();
		this.height = controller.getHeight();
		celulas = new JButton[this.width][this.height];
		
		border = gameTable.getContentPane();
		
		grid = new JPanel();
		grid.setLayout(new GridLayout(height, width));
		for (i = 0; i < this.height; i++) {
			for (j = 0; j < this.width; j++) {		
				celulas[i][j] = new JButton();
				if (controller.isCellAlive(i, j)) {
					celulas[i][j].setBackground(new Color (0, 255, 0));
				} else {
					celulas[i][j].setBackground(new Color (1, 1, 1));
				}
				grid.add(celulas[i][j]);
				celulas[i][j].addActionListener(this);
			}
		}
		
		flow = new JPanel();
		flow.setLayout(new FlowLayout());
		next.setBackground(new Color(0, 100, 0));
		halt.setBackground(new Color(255, 0, 0));
		next.setForeground(new Color(1, 1, 1));
		halt.setForeground(new Color(1, 1, 1));
		flow.add(halt);
		flow.add(next);

		halt.addActionListener(this);
		next.addActionListener(this);

		border.setLayout(new BorderLayout(5, 5));
		border.add(BorderLayout.CENTER, grid);
		border.add(BorderLayout.SOUTH, flow);

		gameTable.setSize(800, 600);
		gameTable.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameTable.setVisible(true);
		gameTable.setLocationRelativeTo(null);

	}
	
	public void update () {
		int i, j;

		for (i = 0; i < this.height; i++) {
			for (j = 0; j < this.width; j++) {		
				if (controller.isCellAlive(i, j)) {
					celulas[i][j].setBackground(new Color (0, 255, 0));
				} else {
					celulas[i][j].setBackground(new Color (1, 1, 1));
				}
				grid.add(celulas[i][j]);
				celulas[i][j].addActionListener(this);
			}
		}
		grid.revalidate();

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
	
	@Override
	public void actionPerformed(ActionEvent evento) {
		int i, j;
		
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
		else if (evento.getSource() == next){
			controller.nextGeneration();
		}
		else if (evento.getSource() == halt){ // Halt
			gameTable.dispose();
			controller.halt();
		}
		else if (evento.getSource() == finish){ // End of the game
			gameTable.dispose();
			System.exit(0);
		}
		else { // Make a cell alive
			for (i = 0; i < this.height; i++) {
				for (j = 0; j < this.width; j++) {
					if (evento.getSource() == celulas[i][j]) {
						if (controller.isCellAlive(i, j)) {
							controller.killCell(i, j);
							celulas[i][j].setBackground(new Color (0, 0, 0));
						} else {
							controller.makeCellAlive(i, j);	
							celulas[i][j].setBackground(new Color (0, 255, 0));
						}
					}
				}
			}
			grid.revalidate();
		}
	}
}
