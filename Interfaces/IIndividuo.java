package Interfaces;

import java.util.List;

public interface IIndividuo {

    /**
     * 
     * @param individuo
     * @return 1 se domina, 0 se é dominado e -1 caso não há dominancia
     */
    public int getAvaliacao(double[] func);

    public List<IIndividuo> getDominados();
    
    public int getNumeroDominantes();

    public void createDominados();

    public double[] getFunc();

    public void resetDominantes();
    
    public String getDescricao();
    
    public int getRank();
    
    public void setRank(int rank);
    
    public void domina(IIndividuo dominado);

    public void dominado();

    public void dominadoDecremento();
}
