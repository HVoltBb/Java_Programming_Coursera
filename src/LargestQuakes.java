import java.util.*;

public class LargestQuakes {
    public void findLargestQuakes(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        // String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size());

        // System.out.println("the index of the largest quake is " + indexOfLargest(list));

        ArrayList<QuakeEntry> bigQuakes = getLargest(list, 5);

        for(QuakeEntry qe: bigQuakes){
            System.out.println(qe);
        }
    }

    public int indexOfLargest (ArrayList<QuakeEntry> quakeData){
        int result=0;
        for(int i=1; i<quakeData.size(); ++i){
            if(quakeData.get(i).getMagnitude() > quakeData.get(result).getMagnitude()) result = i;
        }
        return result;
    }

    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> list, int howMany){
        ArrayList<QuakeEntry> temp = new ArrayList<>(list);
        ArrayList<QuakeEntry> ret = new ArrayList<>();
        howMany = Math.min(howMany, list.size());
        int indx = 0;

        for(int i = 0; i < howMany; ++i){
            indx = indexOfLargest(temp);
            ret.add(temp.get(indx));
            temp.remove(indx);
        }
        return ret;
    }
}
