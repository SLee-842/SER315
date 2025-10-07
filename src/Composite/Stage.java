package Composite;

import java.util.*;

//Composite
public class Stage implements RaceComponent {
	private final String name; //e.g., "Stage 1"
	private final List<RaceComponent> children = new ArrayList<>();
	public Stage(String name) { this.name = name; }
	public Stage add(RaceComponent c){ children.add(c); return this; }
    public void remove(RaceComponent c){ children.remove(c); }
    public List<RaceComponent> getChildren(){
        return Collections.unmodifiableList(children); //read-only view
    }
    @Override public String getName(){ return name; }
    @Override public void printDetails(String indent){
        System.out.println(indent + "* Stage: " + name);
        for (RaceComponent c : children) c.printDetails(indent + "  ");
    }
}