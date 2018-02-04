import java.util.ArrayList;

public class MatchAllFilter implements Filter {
    private ArrayList<Filter> list;
    private String name;

    public MatchAllFilter(){
        list = new ArrayList<Filter>();
        name = "";
    }

    public void addFilter(Filter f1) {
        list.add(f1);
        if (list.size() > 1) {
            name = name + "; " + f1.getName();
        } else {
            name = f1.getName();
        }
    }

    public boolean satisfies(QuakeEntry qe){
        boolean res = true;
        for(Filter f : list){
            res = res && f.satisfies(qe);
        }
        return res;
    }

    public String getName(){
        return name;
    }
}
