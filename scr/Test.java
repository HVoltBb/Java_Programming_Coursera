import java.util.ArrayList;

public class Test {
    public static void main(String[] shit) {

        // Quiz
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> data = parser.read(source);

        EarthQuakeClient x = new EarthQuakeClient();
        int y = x.filterByDepth(data, -8_000.0, -5_000.0).size();
        int y2= x.filterByPhrase(data, "start", "Explosion").size();
        int y3= x.filterByPhrase(data, "end", "California").size();
        int y4= x.filterByPhrase(data, "any", "Creek").size();

        System.out.println(y + " " + y2 + " " + y3 + " " + y4);

        LargestQuakes z = new LargestQuakes();
        z.findLargestQuakes();



        /*
        EarthQuakeClient x = new EarthQuakeClient();
        ArrayList<QuakeEntry> bigQuakes = x.filterByMagnitude(data, 5.5);

        for(QuakeEntry qe: bigQuakes){
            System.out.println(qe);
        }
        */

    /* Assignment 2: Filtering by Depth
    EarthQuakeClient x = new EarthQuakeClient();
    x.closeToMe();
    x.quakesOfDepth();
    */

        /* Assignment 3: Filtering by Phrase in Title
        EarthQuakeClient x = new EarthQuakeClient();
        x.quakeByPhrase();
        */

        /* Assignment 4: Finding the Closest Earthquakes to a Location
        ClosestQuakes x = new ClosestQuakes();
        x.findClosestQuakes();
        */

        /* Assignment 5: Finding the Largest Magnitude Earthquakes
        LargestQuakes x = new LargestQuakes();
        x.findLargestQuakes();
        */



    }
}
