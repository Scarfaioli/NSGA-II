import java.util.Comparator;
import java.util.List;

import Interfaces.IIndividuo;

public class Utils {
    public static void sort(List<IIndividuo> lista, int m) {
        lista.sort(Comparator.comparingDouble(ind -> ind.getFunc(m)));
    }
}
