package Composite;
import java.time.LocalDate;
import java.util.*;
public class Race implements RaceComponent {
    private final String name;
    private final LocalDate date;
    private final boolean official;
    private final List<RaceComponent> stages = new ArrayList<>();
    public Race(String name, LocalDate date, boolean official){
        this.name = name; this.date = date; this.official = official;
    }
    public Race add(RaceComponent stage){ stages.add(stage); return this; }
    public List<RaceComponent> getStages(){ return Collections.unmodifiableList(stages); }
    public boolean isOfficial(){ return official; }
    public LocalDate getDate(){ return date; }
    @Override public String getName(){ return name; }
    @Override public void printDetails(String indent){
        System.out.println(indent + "* Race: " + name + " (" + (official?"Official":"Unofficial") + ") " + date);
        for (RaceComponent s : stages) s.printDetails(indent + "  ");
    }
}