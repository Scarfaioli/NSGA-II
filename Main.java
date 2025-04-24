import java.util.List;

import Interfaces.IIndividuo;
import Models.Factory;

public class Main {
    public static void main(String[] args) {
        //Inicializar Lista de individuos
        Factory factory =  new Factory();
        //factory.escolheBase(1);
        //Encontrar fronteiras
        List<List<IIndividuo>> fronteiras = factory.Fnds();
        //Imprimir fronteiras
        factory.imprimirFronteiras(fronteiras);
    }
}