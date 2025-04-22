import java.util.List;

import Interfaces.IIndividuo;
import Models.Factory;

public class Main {
    public static void main(String[] args) {
        //Inicializar Lista de individuos
        Factory factory =  new Factory();
        //Encontrar fronteiras
        List<List<IIndividuo>> fronteiras = factory.createFrontieras(1);
        //Imprimir fronteiras
        factory.imprimirFronteiras(fronteiras);
    }

}