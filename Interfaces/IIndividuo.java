package Interfaces;

import java.util.List;

public interface IIndividuo {

    public void getResponse();

    /**
     * 
     * @param individuo
     * @return 1 se domina, 0 se é dominado
     */
    public int getAvaliacao(IIndividuo individuo);

    public List<IIndividuo> getDominados();
    
    public int getNumeroDominantes();

    public void createDominados();

    public void resetDominantes();
    
    public String getDescricao();
    
    public int getRank();
    
    public int setRank(int rank);
    
    public int domina(IIndividuo dominado);

    public int dominado();

    public void dominadoDecremento();
}
