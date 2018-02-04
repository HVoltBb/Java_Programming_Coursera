public class DistanceFilter implements Filter {
    private double maxDistance;
    private Location loc;
    private String name;

    public DistanceFilter(double d, Location loc){
        maxDistance = d;
        this.loc = loc;
        name = "Distance filter";
    }

    public boolean satisfies(QuakeEntry qe){
        return qe.getLocation().distanceTo(loc) < maxDistance;
    }

    public String getName(){
        return name;
    }
}
