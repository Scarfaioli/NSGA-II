package Models;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Interfaces.IIndividuo;

public class Example implements IIndividuo {

    double func[];
    double x[];
    List<IIndividuo> s = new ArrayList<>();
    int n = 0;
    int rank = 0;
    String descricao;
    public double distance;

    Example(double[] x, int ger) {
        this.x = x;
        this.func = new double[x.length];
        for (int i = 0; i < this.func.length; i++) {
            this.func[i] = 0;
            for (int j = 0; j < x.length; j++) {
                int c = 0;
                if(i == j)
                    c = 1;
                this.func[i] += Math.pow(x[j] - c, 2);
            }
        }
        this.descricao = "Individuo " + ger;
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

    @Override
    public int compareTo(IIndividuo b) {
        if(this.distance > b.getDistance()){
            return 1;
        }else if(this.distance < b.getDistance()){
            return -1;
        }
        return 0;
    }

    @Override
    public double getDistance() {
        return this.distance;
    }

    @Override
    public void setDistance(double distance) {
        this.distance = distance;
    }

    @Override
    public double getFunc(int i) {
        return this.func[i];
    }

    @Override
    public void calcDist(int i, double fmax, double fmin, double anterior, double proximo) {
        double aux = (proximo - anterior)/(fmax - fmin);
        this.distance += aux;
    }

    @Override
    public List<IIndividuo> crossover(IIndividuo iIndividuo, int ger) {
        List<IIndividuo> filhos = new ArrayList<>();
        Random random = new Random();
        double[] x = new double[this.x.length];
        for (int i = 0; i < x.length; i++) {
            x[i] = (x[i] + random.nextGaussian(0, 0.3)*Math.abs(x[i]-iIndividuo.getX(i)));
        }
            filhos.add(new Example(x, ger));
        for (int i = 0; i < x.length; i++) {
            x[i] = (iIndividuo.getX(i) + random.nextGaussian(0, 0.1)*Math.abs(x[i]-iIndividuo.getX(i)));
        }
            filhos.add(new Example(x, ger));
        return filhos;
    }

    @Override
    public double getX(int i) {
        return this.x[i];
    }

    @Override
    public void mutacao(int ger) {
        Random random = new Random();
        boolean mutado = false;
        for (int i = 0; i < this.x.length; i++) {
            if(random.nextDouble() < 0.3) { // 10% de chance de mutação
                this.x[i] += random.nextGaussian(0, 0.2); // Mutação gaussiana
                if(this.x[i] < 0) this.x[i] = 0; // Garantir que x não seja negativo
                if(this.x[i] > 1) this.x[i] = 1; // Garantir que x não ultrapasse 1
                mutado = true;
            }
        }
        if(!mutado) {
            this.x[random.nextInt(this.x.length)] += random.nextGaussian(0, 0.2); // Se não mutou, faz uma mutação aleatória
        }
    }

    @Override
    public double[] getX() {
        return this.x;
    }

    @Override
    public IIndividuo crossover(double[] iIndividuo, int ger) {
        Random random = new Random();
        double[] x = new double[this.x.length];
        boolean mutado = false;        
        for (int i = 0; i < x.length; i++) {
            x[i] = (random.nextDouble()<0.2) ? iIndividuo[i] : this.x[i];
            if(x[i] == iIndividuo[i]){
                mutado = true;
            }
        }
        IIndividuo filho = new Example(x, ger);
        if(!mutado) 
            filho.mutacao(iIndividuo);   
        return filho;
    }

    @Override
    public void mutacao(double[] x) {
        Random random = new Random();
        int i = random.nextInt(this.x.length);
        this.x[i] = x[i];
    }
}