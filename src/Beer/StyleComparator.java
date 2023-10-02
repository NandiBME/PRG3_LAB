package Beer;
import Beer.Beer;

import java.util.Comparator;

public class StyleComparator implements Comparator<Beer> {
    @Override
    public int compare(Beer beer1, Beer beer2)
    {
        return beer1.getStyle().compareTo(beer2.getStyle());
    }

}
