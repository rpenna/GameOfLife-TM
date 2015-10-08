package br.unb.cic.lp.gol;

public class LiveFreeOrDie extends GameEngine {
	
	
	public LiveFreeOrDie (int height, int width, Statistics statistics) {
		super (height, width, statistics);
	}

	@Override
	protected boolean shouldKeepAlive(int i, int j) {
		return numberOfNeighborhoodAliveCells(i, j) == 0;
		
	}

	@Override
	protected boolean shouldRevive(int i, int j) {
		return numberOfNeighborhoodAliveCells(i, j) == 2;
	}
}
