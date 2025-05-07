package Interfaces;

import java.util.List;

public interface IIndividuo {

    /**
     * 
     * @param individuo
     * @return 1 se domina, 0 se é dominado e -1 caso não há dominancia
     */
    public int getAvaliacao(double[] func);

    public int compareTo(IIndividuo b);

    public List<IIndividuo> getDominados();
    
    public int getNumeroDominantes();

    public void createDominados();

    public double[] getFunc();

    public double getFunc(int i);

    public void resetDominantes();
    
    public String getDescricao();
    
    public int getRank();
    
    public void setRank(int rank);
    
    public void domina(IIndividuo dominado);

    public void dominado();

    public double getDistance();

    public void setDistance(double distance);

    public void dominadoDecremento();

    public void calcDist(int i, double fmax, double fmin, double anterior, double proximo);
}
