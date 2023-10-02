package Beer;
import Beer.Beer;

import java.util.Comparator;

public class StrengthComparator implements Comparator<Beer> {
    @Override
    public int compare(Beer beer1, Beer beer2)
    {
        return Double.compare(beer1.getStrength(),beer2.getStrength());
    }
}
