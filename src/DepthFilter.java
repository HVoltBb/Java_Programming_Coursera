public class DepthFilter implements Filter{
    private double minDepth;
    private double maxDepth;
    private String name;

    public DepthFilter(double min, double max){
        minDepth = min;
        maxDepth = max;
        name = "Depth filter";
    }
// In the Quiz, boundaries are sometimes inclusive and sometimes exclusive
    public boolean satisfies(QuakeEntry qe){
        return qe.getDepth()<=maxDepth && qe.getDepth()>=minDepth;
    }

    public String getName(){
        return name;
    }
}
