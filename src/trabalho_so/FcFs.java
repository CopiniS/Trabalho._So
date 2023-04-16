
package trabalho_so;

import java.util.ArrayList;

public class FcFs {
    Tarefa t1, t2, t3, t4;
    
    

    public FcFs() {
        t1 = new Tarefa(3, 0); 
        t2 = new Tarefa(5, 3);  
        t3 = new Tarefa(5, 2); 
        t4 = new Tarefa(3, 1); 
       
    }
   
    
    public ArrayList ordenarTempoChegada(){
        ArrayList<Tarefa> listatarefas = new ArrayList();
        Tarefa aux;
        for (int i=0; i<listatarefas.size(); i++) {
            for(int j=i+1; j<listatarefas.size(); j++){
                if(listatarefas.get(i).getTempoDeIngresso() > listatarefas.get(j).getTempoDeIngresso()){
                    aux = listatarefas.get(i);
                    listatarefas.set(i, listatarefas.get(j));
                    listatarefas.set(j, aux);
                }
            }
        }
        return listatarefas;
    }
    
    public double calculaEsperaMedia(ArrayList<Tarefa> listatarefas){
        int tempoAtual = 0;
        int somaEsperas = 0;
        for(Tarefa tarefa : listatarefas){
            tarefa.espera = tempoAtual - tarefa.tempoDeIngresso;
            tempoAtual = tempoAtual + tarefa.getTempoComputacional();
            somaEsperas = somaEsperas + tarefa.espera;
        }
        return (double) somaEsperas / (double) listatarefas.size();
    }
    
    public void calculaTempoMedioAtraso(ArrayList<Tarefa> listatarefas){
        
    }
    
    public double calculaTempoExecucaoMedio(ArrayList<Tarefa> listatarefas){
         int somaexecucao = 0;
        
        for(Tarefa tarefa : listatarefas){
            somaexecucao = somaexecucao + (tarefa.espera + tarefa.tempoComputacional);
        }
        return (double) somaexecucao / (double) listatarefas.size();
    }
       
}
