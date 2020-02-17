package p;
 
import java.awt.Color;
import java.util.ArrayList;
 
//2020-01-21
public class PlanetsModel{
    private PlanetsView view;
 
    private static final double G = 4 * Math.PI * Math.PI;
 
    private static final double solarmass = 1;
   
    private static final double earthmass = 1/332946.0;
   
    private static final double jupitermass = 0.001*solarmass;
   
    private static final double AU = 1;
 
    private static final double earthStartSpeedY = 2*Math.PI;
   
    private static final double jupiterStartSpeedY = (2*Math.PI) / (Math.sqrt(5.2));
   
    private ArrayList<Planet> listOfPlanets = new ArrayList<Planet>();
   
    private ArrayList<ArrayList<Planet>> linesForPlanets = new ArrayList<ArrayList<Planet>>();
   
    public PlanetsModel(PlanetsView view) {
        this.view = view;
       
        listOfPlanets.add(new Planet(0,0,0,0, solarmass, 6, Color.RED));
        listOfPlanets.add(new Planet(AU,0,0,earthStartSpeedY, earthmass, 4, Color.BLUE));
        listOfPlanets.add(new Planet(5.2*AU,0,0,jupiterStartSpeedY, jupitermass, 5, Color.ORANGE));
       
        for(int i = 0; i < listOfPlanets.size(); i++) {
            linesForPlanets.add(new ArrayList<>());        
        }
    }
   
    // resets all the accelerations between the planets, needs to be done at each time frame,
    // since the acceleration depends on the positions, which constantly change
    private void resetAcc() {
        for(int i = 0; i < listOfPlanets.size(); i++) {
            listOfPlanets.get(i).setAx(0);
            listOfPlanets.get(i).setAy(0);;
        }
    }
   
    // calculates and sets the acceleration between all planets in the list (listOfPlanets) 
    // at the specific point of time its called  
    private void updateAcc() {
        for(int i = 0; i < listOfPlanets.size(); i++) {
            for(int j = 0; j < listOfPlanets.size(); j++) {
                if(listOfPlanets.get(i).getX() == listOfPlanets.get(j).getX() && 
                   listOfPlanets.get(i).getY() == listOfPlanets.get(j).getY())
                    continue;
                ax(listOfPlanets.get(i), listOfPlanets.get(j));
                ay(listOfPlanets.get(i), listOfPlanets.get(j));
            }
        }
    }
 
    // calculates the acceleration between two planets in the x direction
    private void ax(Planet planetToMove, Planet planetThatCauseMove) {
        double a = G * planetThatCauseMove.getMass() / (Math.pow(planetToMove.getX() - planetThatCauseMove.getX(), 2) + Math.pow(planetToMove.getY() - planetThatCauseMove.getY(), 2));
        planetToMove.addAx(a * (planetThatCauseMove.getX() - planetToMove.getX()) / Math.sqrt(Math.pow(planetToMove.getX() - planetThatCauseMove.getX(), 2) + Math.pow(planetToMove.getY() - planetThatCauseMove.getY(), 2)));
    }
   
    // calculates the acceleration between two planets in the y direction
    private void ay(Planet planetToMove, Planet planetThatCauseMove) {
        double a = G * planetThatCauseMove.getMass() / (Math.pow(planetToMove.getX() - planetThatCauseMove.getX(), 2) + Math.pow(planetToMove.getY() - planetThatCauseMove.getY(), 2));
        planetToMove.addAy(a *(planetThatCauseMove.getY() - planetToMove.getY()) / Math.sqrt(Math.pow(planetToMove.getX() - planetThatCauseMove.getX(), 2) + Math.pow(planetToMove.getY() - planetThatCauseMove.getY(), 2)));
        //System.out.println(planetToMove.getAy());
    }
   
    private void position(double dt) {
        for(int i = 0; i < listOfPlanets.size(); i++) {
            listOfPlanets.get(i).updatePosition(dt);
        }
    }
   
    private void velocity(double dt) {
        for(int i = 0; i < listOfPlanets.size(); i++) {
            listOfPlanets.get(i).updateVelocity(listOfPlanets.get(i).getAx(), listOfPlanets.get(i).getAy(), dt);
        }
    }
   
    // A tail highlighting the track the planets that have moved
    private int j = 0;
    private int rice = 30;
    private void addRiceGrain() {
    	for(int i = 0; i < listOfPlanets.size(); i++) {
    		if(linesForPlanets.get(i).size() >= rice) {
    			if(j >= rice) {
    				j = 0;
    			}
    			linesForPlanets.get(i).set(j, new Planet(listOfPlanets.get(i).getX(), listOfPlanets.get(i).getY(),0,0, solarmass, 1, Color.WHITE));
    		} else {
    		linesForPlanets.get(i).add(new Planet(listOfPlanets.get(i).getX(), listOfPlanets.get(i).getY(),0,0, solarmass, 1, Color.WHITE));
    		}
    	}
    	j++;
    }
   
    private void drawPlanets() {
        for(Planet curInstance: listOfPlanets) { 
        	view.showPlanet(curInstance);
        }
       
        for(ArrayList<Planet> list: linesForPlanets) {
            for(Planet p: list) {
                view.showPlanet(p);
            }
        }
   
    }
   
    // updates the acceleration, position and velocity in accordance to Verlets Algorithm
    private void updateAll (double dt){
        updateAcc();
        addRiceGrain();
        position(dt);
        velocity(dt);
        resetAcc();
        updateAcc();
        velocity(dt);
        resetAcc();
    }
   
    public void update(double dt) {
        // Calculate the new positions and velocities of the planets
        updateAll(dt);
        view.clear();
        // Draw the planets
        drawPlanets();
        view.repaint();
    }
}