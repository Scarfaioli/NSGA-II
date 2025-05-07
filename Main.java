import java.util.List;

import Interfaces.IIndividuo;
import Models.Factory;

public class Main {
    public static void main(String[] args) {
        Factory factory =  new Factory();
        List<IIndividuo> pop = factory.createPopIni();

        List<List<IIndividuo>> fronteiras = NSGAII.fnds(pop);

        NSGAII.imprimirFronteiras(fronteiras);

        NSGAII.crowdingDistance(fronteiras);
        
        NSGAII.imprimirCrowdDist(fronteiras);
    }
}