
/**
 * This is a filter interface for filtering QuakeEntry(s) satisfying
 * certain conditions.
 * 
 * @author HVoltBb
 * @version 2/4/18
 */
public interface Filter
{
    public  boolean satisfies(QuakeEntry qe);
    public String getName();
}
