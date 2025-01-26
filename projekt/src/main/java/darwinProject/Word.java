package darwinProject;



public class Word {
         public static void main (String[] args){
             Simulation simulation = new Simulation(10, 10, 2, 20, 1, 5, 50, 20, 0, 3, 7, 50);
             simulation.run();
             System.out.println("System finished doing simulation.");
         }
}