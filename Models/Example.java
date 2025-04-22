package Models;

import java.util.ArrayList;
import java.util.List;

import Interfaces.IIndividuo;

public class Example implements IIndividuo {

    double var[];
    double func[];
    List<Example> s = new ArrayList<>();
    int n = 0;
    int rank = 0;
    String descricao;

    Example(double[] x) {

    }

    @Override
    public void getResponse() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getResponse'");
    }

    @Override
    public List<IIndividuo> getDominados() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDominados'");
    }

    @Override
    public int getNumeroDominantes() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getNumeroDominantes'");
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createDominados'");
    }

    @Override
    public void resetDominantes() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'resetDominantes'");
    }

    @Override
    public int getAvaliacao(IIndividuo individuo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAvaliacao'");
    }
    
    @Override
    public int domina(IIndividuo dominado) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'domina'");
    }

    @Override
    public int dominado() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'dominado'");
    }

    @Override
    public int setRank(int rank) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setRank'");
    }

    @Override
    public void dominadoDecremento() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'dominadoDecremento'");
    }

}
