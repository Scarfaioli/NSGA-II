package Models;

import java.util.ArrayList;
import java.util.List;

import Interfaces.IIndividuo;

public class Factory {

    public void run(List<IIndividuo> indList){

    }

    public Example createIndividuoExample(double var[])
    {
        return new Example(var);
    }

    public List<List<IIndividuo>> createFrontieras(int tipo){
        return new ArrayList<>();
    }

    public void imprimirFronteiras(List<List<IIndividuo>> fronteirasList){

    }

    public List<List<IIndividuo>> Fnds(List<IIndividuo> indList){
        List<List<IIndividuo>> fronteiraList = new ArrayList<>();
        fronteiraList.add(new ArrayList<>());
        List<IIndividuo> fi = fronteiraList.get(0);

        //Calculo da primeira fronteira O(n²)
        for (IIndividuo iIndividuo : indList) {
            iIndividuo.createDominados();
            iIndividuo.resetDominantes();
            for (IIndividuo iIndividuo2 : indList) {
                int domina = iIndividuo.getAvaliacao(iIndividuo2);
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
        return null;
    }
}
