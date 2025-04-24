package Models;

import java.util.ArrayList;
import java.util.List;

import Interfaces.IIndividuo;

public class Example implements IIndividuo {

    double func[];
    List<IIndividuo> s = new ArrayList<>();
    int n = 0;
    int rank = 0;
    String descricao;

    Example(double[] x, String descricao) {
        this.func = x;
        this.descricao = descricao;
    }

    @Override
    public List<IIndividuo> getDominados() {
        return this.s;
    }

    @Override
    public int getNumeroDominantes() {
        return this.n;
    }

    @Override
    public String getDescricao() {
        return descricao;
    }

    @Override
    public int getRank() {
        return rank;
    }

    @Override
    public void createDominados() {
        this.s = new ArrayList<>();
    }

    @Override
    public void resetDominantes() {
        this.n = 0;
    }

    @Override
    public int getAvaliacao(double func[]) {
        boolean dominando = true;
        boolean dominado = true;

        for (int i = 0; i < this.func.length; i++) {
            if(this.func[i]<func[i])
                dominado = false;
            else if(this.func[i]>func[i])
                dominando = false;
        }
        if(dominado == dominando){
            return -1;
        }else if(dominado){
            return 0;
        }
        return 1;
    }
    
    @Override
    public void domina(IIndividuo dominado) {
        this.s.add(dominado);
    }

    @Override
    public void dominado() {
        this.n++;
    }

    @Override
    public void setRank(int rank) {
        this.rank = rank;
    }

    @Override
    public void dominadoDecremento() {
        this.n--;
    }

    @Override
    public double[] getFunc() {
        return this.func;
    }

}
