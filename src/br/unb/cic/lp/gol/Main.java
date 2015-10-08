package br.unb.cic.lp.gol;

public class Main {

	public static void main(String args[]) {
		GameController controller = new GameController();
		Statistics statistics = new Statistics();
		GameEngine engine = null; 
		GameViewGUI board = new GameViewGUI(controller);
		
		controller.setBoard(board);
		controller.setEngine(engine);
		controller.setStatistics(statistics);
		
		controller.start();
	}
}
/*System.out.println("Forneca a regra a ser usada");

System.out.println("[1] Conway");
System.out.println("[2] HighLife");

Scanner s = new Scanner(System.in);
String option = s.nextLine();*/

//o objeto engine eh declarado como tendo o tipo 
//da classe abstrata GameEngine. eh isso que permite 
//tornar o software mais flexivel. 

/*switch(gui.getOption()) {
 case 1: 
	 engine = (Conway) context.getBean("conway");
 break;
 case 2: 
	 engine = (HighLife) context.getBean("highlife");
 break;
 default: 
	 System.exit(0);		  
}
*/