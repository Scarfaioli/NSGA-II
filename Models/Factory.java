package Models;

import java.util.ArrayList;
import java.util.List;

import Interfaces.IIndividuo;

public class Factory {

    public List<IIndividuo> createPopIni(){
        List<IIndividuo> indList = new ArrayList<>();

        indList.add(new Example(new double[]{1, 5}, "A"));
        indList.add(new Example(new double[]{2, 3}, "B"));
        indList.add(new Example(new double[]{4, 1}, "C"));
        indList.add(new Example(new double[]{3, 4}, "D"));
        indList.add(new Example(new double[]{4, 3}, "E"));
        indList.add(new Example(new double[]{5, 5}, "F"));

        return indList;
    }

    
}
