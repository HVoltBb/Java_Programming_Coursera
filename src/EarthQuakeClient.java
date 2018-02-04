import java.lang.reflect.Array;
import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
    double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData){
            if(qe.getMagnitude()>magMin) {
                answer.add(qe);
            }
        }
        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData,
    double distMax,
    Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe: quakeData){
            if(qe.getLocation().distanceTo(from)<distMax) answer.add(qe);
            }
        return answer;
    }

    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        ArrayList<QuakeEntry> y = filterByMagnitude(list, 5.0);
        for(QuakeEntry qe:y){
            System.out.println(qe);
        }
        System.out.println("Found " + y.size() + "quakes that match that criteria");
    }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        // This location is Durham, NC
        // Location city = new Location(35.988, -78.907);


        // This location is Bridgeport, CA
        Location city =  new Location(38.17, -118.82);
        ArrayList<QuakeEntry> nearQuakes = filterByDistanceFrom(list, 1_000_000, city);
        System.out.println("Found " + nearQuakes.size() + " quakes matching the criteria");
        if(nearQuakes.size()!=0){
            for(QuakeEntry qe:nearQuakes){
                System.out.println(qe.getInfo());
            }
        }
    }

    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData, double minDepth, double maxDepth){
        ArrayList<QuakeEntry> result = new ArrayList<>();
        for(QuakeEntry qe : quakeData){
            if (qe.getDepth()>minDepth && qe.getDepth()<maxDepth) result.add(qe);
            }
        return result;
    }

    public void quakesOfDepth(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        ArrayList<QuakeEntry> x = filterByDepth(list, -10_000.0, -5_000.0);
        System.out.println("Find quakes with depth between -10000.0 and -5000.0");
        if(x.size()!=0){
            for(QuakeEntry qe : list){
                System.out.println(qe);
            }
            System.out.println("Found " + x.size() + " quakes that match that criteria");
        }
    }

    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData, String where, String phrase){
        ArrayList<QuakeEntry> result = new ArrayList<>();
            switch (where.toLowerCase()){
                case "start":
                    for(QuakeEntry qe:quakeData) {
                        if (qe.getInfo().indexOf(phrase) == 0) result.add(qe);
                    }
                    break;
                case "end":
                    for(QuakeEntry qe:quakeData) {
                        if (qe.getInfo().indexOf(phrase) == (qe.getInfo().length() - phrase.length())) result.add(qe);
                    }
                    break;
                case "any":
                    for(QuakeEntry qe:quakeData) {
                        if (qe.getInfo().contains(phrase)) result.add(qe);
                    }
                    break;
                    default:
                        System.out.println("Where do you want to search: start, end, or any?");
            }
            return result;
        }


    public void quakeByPhrase(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        String where = "start";
        String phrase = "Explosion";
        ArrayList<QuakeEntry> y = filterByPhrase(list, where, phrase);
        if(y.size()!=0){
            for(QuakeEntry qe: y){
                System.out.println(qe);
            }
            System.out.println("Found " + y.size() + " quakes that match " + phrase + " at " + where);
        }
    }



    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }
    
}
