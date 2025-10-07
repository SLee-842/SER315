package Composite;

public class RaceType implements RaceComponent{
	private final String name; //e.g., Time Trial, Criterium, Road Race, Gravel
	public RaceType(String name) {this.name = name; }
	@Override public String getName() { return name; }
	@Override public void printDetails(String indent) {
		System.out.println(indent + "- Type: " + name);
	}

}