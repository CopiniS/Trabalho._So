
package trabalho_so;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
       Sistema s1 = new Sistema();
       
       s1.listaAdd();
       s1.ordenarTempoChegada();
       s1.escalonador();
       System.out.println(s1.calculaTempoEsperaMedio());
       System.out.println(s1.calculaTempoExecucaoMedio());
       
       s1.mostraTempoAtraso(s1.calculaTempoMedioAtraso());
    
    }
}
