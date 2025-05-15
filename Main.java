import java.util.ArrayList;
import java.util.List;

import Interfaces.IIndividuo;
import Models.Factory;

public class Main {
    public static void main(String[] args) {
        Factory factory =  new Factory();
        int tamanho = 100;
        List<IIndividuo> pop = factory.createPopIni(tamanho);
        int numeroGeracao = 0;  
        while(numeroGeracao < 1000){
            pop.addAll(factory.makeChildren(pop));
            List<IIndividuo> nextPop = new ArrayList<>();

            List<List<IIndividuo>> fronteiras = NSGAII.fnds(pop);    
            NSGAII.imprimirFronteiras(fronteiras);
            int i;
            for (i = 0; i < fronteiras.size(); i++) {
                if (nextPop.size() + fronteiras.get(i).size() > tamanho) break;
                
                nextPop.addAll(fronteiras.get(i));
            }

            NSGAII.crowdingDistance(fronteiras.get(i));
            Utils.sort(fronteiras.get(i), "distance");
            NSGAII.imprimirCrowdDist(fronteiras);

            nextPop.addAll(fronteiras.get(i).subList(0, tamanho - nextPop.size()));
            pop = nextPop;

            numeroGeracao++;
        }
    }
}