package br.unb.cic.lp.gol;

public class Conway extends GameEngine {
	
	public Conway(int height, int width, Statistics statistics) {
		super(height, width, statistics);
	}

	@Override
	protected boolean shouldKeepAlive(int i, int j) {
		int aliveCells = numberOfNeighborhoodAliveCells(i, j);
		
		return (cells[i][j].isAlive() && (aliveCells == 2 || aliveCells == 3));
	}

	@Override
	protected boolean shouldRevive(int i, int j) {
		int aliveCells = numberOfNeighborhoodAliveCells(i, j);
		
		return aliveCells == 3; 
	}
}
