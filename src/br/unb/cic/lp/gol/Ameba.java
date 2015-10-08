package br.unb.cic.lp.gol;

public class Ameba extends GameEngine{

	public Ameba (int height, int width, Statistics statistics) {
		super (height, width, statistics);
	}
	@Override
	protected boolean shouldKeepAlive(int i, int j) {
		int neighbors;
		neighbors = numberOfNeighborhoodAliveCells(i, j);
		if ((neighbors == 3) || (neighbors == 5) || (neighbors == 7)) {
			return true;
		}
		return false;
	}

	@Override
	protected boolean shouldRevive(int i, int j) {
		int neighbors;
		neighbors = numberOfNeighborhoodAliveCells(i, j);
		if ((neighbors == 1) || (neighbors == 3) || (neighbors == 5) || (neighbors == 8)) {
			return true;
		}
		return false;
	}

}
