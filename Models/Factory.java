package Models;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Interfaces.IIndividuo;

public class Factory {

    public List<IIndividuo> createPopIni(int tamanho, int ger) {
        List<IIndividuo> indList = new ArrayList<>();

        for (int i = 0; i < tamanho; i++) {
            Example example = new Example(new double[] { Math.random(), Math.random() }, ger);
            indList.add(example);
        }
        return indList;
    }

    public void makeChildren(List<IIndividuo> pop, int ger) {
        Random random = new Random();
        List<IIndividuo> filhos = new ArrayList<>();
        
        for (IIndividuo target : pop) {
            IIndividuo base1 = pop.get(random.nextInt(pop.size()));
            IIndividuo base2 = pop.get(random.nextInt(pop.size()));
            
            double[] ruido = pop.get(random.nextInt(pop.size())).getX().clone();
            
            double[] vetDiff = new double[target.getX().length];
            for (int i = 0; i < vetDiff.length; i++) {
                vetDiff[i] = base1.getX(i) - base2.getX(i);
                vetDiff[i] *= 0.5;
                ruido[i] += vetDiff[i];
            }
            filhos.add(target.crossover(ruido, ger));
        }

        pop.addAll(filhos);
    }

    
}
