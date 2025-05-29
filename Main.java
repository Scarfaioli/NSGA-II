import java.util.ArrayList;
import java.util.List;
import Interfaces.IIndividuo;
import Models.Factory;

public class Main {
    public static void main(String[] args) {
        Factory factory =  new Factory();
        int tamanho = 100;
        int numeroGeracao = 0;
        List<IIndividuo> pop = factory.createPopIni(tamanho, numeroGeracao);
        List<List<IIndividuo>> fronteiras;
        do {
            factory.makeChildren(pop, numeroGeracao);
            List<IIndividuo> nextPop = new ArrayList<>();

            fronteiras = NSGAII.fnds(pop);
            int i;
            for (i = 0; i < fronteiras.size(); i++) {
                if (nextPop.size() + fronteiras.get(i).size() > tamanho) break;
                nextPop.addAll(fronteiras.get(i));
            }

            NSGAII.crowdingDistance(fronteiras.get(i));
            Utils.sort(fronteiras.get(i), "distance");

            nextPop.addAll(fronteiras.get(i).subList((fronteiras.get(i).size() - (tamanho - nextPop.size())), fronteiras.get(i).size()-1));
            pop = nextPop;
            System.out.println("Geracao: " + numeroGeracao + " - Distancia: " + pop.get(3).getDistance());

            // Plota nas gerações desejadas
            if (numeroGeracao == 0 || numeroGeracao == 9 || numeroGeracao == 49 || numeroGeracao == 199 || numeroGeracao == 999) {
                PlotUtils.plotObjetivos(pop, numeroGeracao + 1); // +1 pois começa do 0
            }

            numeroGeracao++;
        }while(numeroGeracao < 1000);
        NSGAII.imprimirFronteiras(fronteiras);
    }
}