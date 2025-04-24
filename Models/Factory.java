package Models;

import java.util.ArrayList;
import java.util.List;

import Interfaces.IIndividuo;

public class Factory {

    private List<IIndividuo> indList;
    
    public Factory(){
        this.indList = new ArrayList<>();

        this.indList.add(new Example(new double[]{1, 5}, "A"));
        this.indList.add(new Example(new double[]{2, 3}, "B"));
        this.indList.add(new Example(new double[]{4, 1}, "C"));
        this.indList.add(new Example(new double[]{3, 4}, "D"));
        this.indList.add(new Example(new double[]{4, 3}, "E"));
        this.indList.add(new Example(new double[]{5, 5}, "F"));
    }

    public void imprimirFronteiras(List<List<IIndividuo>> fronteirasList){
        int i = 1;
        for (List<IIndividuo> fronteira : fronteirasList) {
            System.out.print("Fronteira "+i+":");
            for (IIndividuo individuo : fronteira) {
                System.out.print(" "+individuo.getDescricao());
            }
            System.out.println();
            ++i;
        }
    }

    public List<List<IIndividuo>> Fnds(){
        List<List<IIndividuo>> fronteiraList = new ArrayList<>();
        fronteiraList.add(new ArrayList<>());
        List<IIndividuo> fi = fronteiraList.get(0);

        //Calculo da primeira fronteira O(n²)
        for (IIndividuo iIndividuo : this.indList) {
            iIndividuo.createDominados();
            iIndividuo.resetDominantes();
            for (IIndividuo iIndividuo2 : this.indList) {
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
}
