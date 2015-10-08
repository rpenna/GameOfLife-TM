package br.unb.cic.lp.gol;

/**
 * Uma implementacao de regra de derivacao baseada 
 * na estrategia HighLife. Note que a classe HighLife 
 * herda o comportamento implementado em Conway, mas sobrescreve 
 * (overrides) a implementacao do metodo shouldRevive. 
 * 
 * @author rbonifacio
 */
public class HighLife extends Conway {
	public HighLife(int height, int width, Statistics statistics) {
		super(height, width, statistics);
		
	}

	@Override //essa anotacao eh opcional. em algumas situacoes, eh util. 
	protected boolean shouldRevive(int i, int j) {
		int aliveCells = numberOfNeighborhoodAliveCells(i, j);
		
		return (aliveCells == 3 || aliveCells == 6);
	}
}
