import java.util.ArrayList;
import java.util.List;

import Interfaces.IIndividuo;

public class NSGAII {
    public static List<List<IIndividuo>> fnds(List<IIndividuo> indList){
        List<List<IIndividuo>> fronteiraList = new ArrayList<>();
        fronteiraList.add(new ArrayList<>());
        List<IIndividuo> fi = fronteiraList.get(0);

        //Calculo da primeira fronteira O(n²)
        for (IIndividuo iIndividuo : indList) {
            iIndividuo.createDominados();
            iIndividuo.resetDominantes();
            for (IIndividuo iIndividuo2 : indList) {
                int domina = iIndividuo.getAvaliacao(iIndividuo2.getFunc());
                if(domina == 0){
                    iIndividuo.dominado();
                } else if (domina == 1){
                    iIndividuo.domina(iIndividuo2);
                }
            }
            if (iIndividuo.getNumeroDominantes() == 0) {
                iIndividuo.setRank(1);
                fi.add(iIndividuo);
            }
        }
        int i = 1;
        while (!fi.isEmpty()) {
            List<IIndividuo> fq = new ArrayList<>();
            for (IIndividuo iIndividuo : fi) {
                for (IIndividuo iIndividuo2 : iIndividuo.getDominados()) {
                    iIndividuo2.dominadoDecremento();
                    if (iIndividuo2.getNumeroDominantes() == 0){
                        iIndividuo2.setRank(i+1);
                        fq.add(iIndividuo2);
                    }
                }
            }
            fronteiraList.add(fq);
            fi = fronteiraList.get(i);
            i++;
        }
        return fronteiraList;
    }

    public static void crowdingDistance(List<IIndividuo> fronteira){
        if (fronteira.size() < 3) return;
        int l = fronteira.size();
        for (IIndividuo individuo : fronteira) {
            individuo.setDistance(0.0);
        }
        for (int i = 0; i < fronteira.get(0).getFunc().length; i++) {
            Utils.sort(fronteira, i);
            double fmax, fmin;

            fmin = fronteira.get(0).getFunc(i);
            fmax = fronteira.get(l-1).getFunc(i);

            fronteira.get(0).setDistance(Double.MAX_VALUE);
            fronteira.get(l-1).setDistance(Double.MAX_VALUE);
            
            for (int j = 1; j < fronteira.size()-1; j++) {
                double anterior = fronteira.get(j-1).getFunc(i), proximo = fronteira.get(j+1).getFunc(i);
                fronteira.get(j).calcDist(i, fmax, fmin, anterior, proximo);
            }
        }

    }

    public static void imprimirFronteiras(List<List<IIndividuo>> fronteirasList){
        System.out.println();
        int i = 1;
        for (List<IIndividuo> fronteira : fronteirasList) {
            System.out.print("Fronteira "+i+":");
            for (IIndividuo individuo : fronteira) {
                System.out.print(" "+individuo.getDescricao());
            }
            System.out.println();
            System.out.println();
            ++i;
        }
    }

    public static void imprimirCrowdDist(List<List<IIndividuo>> fronteirasList){
        System.out.println();
        int i = 1;
        for (List<IIndividuo> fronteira : fronteirasList) {
            System.out.print("Fronteira "+i+":");
            for (IIndividuo individuo : fronteira) {
                if (individuo.getDistance() == Double.MAX_VALUE) {
                    System.out.print(" "+individuo.getDescricao()+ " distancia = INF+, ");    
                } else{
                    System.out.printf(" %s distancia = %.3f , ", individuo.getDescricao(), individuo.getDistance());
                }
            }
            System.out.println();
            ++i;
        }
    }
}
