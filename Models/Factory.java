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
        for (int i = 0; i < pop.size()/2; i++) {
            filhos.addAll(pop.get(i).crossover(pop.get(pop.size()-1-i), ger));
        }

        for (IIndividuo filho : filhos) {
            if(random.nextDouble() < 0.2) filho.mutacao(ger);
        }

        pop.addAll(filhos);
    }

    
}
