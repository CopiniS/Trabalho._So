
package trabalho_so;

import java.util.ArrayList;

public class RateMonitonic {
    TarefaRobusta t1, t2, t3, t4;
    int execucaoFaltante = 5;

    public RateMonitonic() {
        this.t1 = new TarefaRobusta( 6, 3, 0);
        this.t2 = new TarefaRobusta( 10, 5, 0);
        this.t3 = new TarefaRobusta( 10, 5, 0);
        this.t4 = new TarefaRobusta( 6, 3, 0);
    }
    
    //DEIXA LISTA ORDENADA POR PERIODO(CRESCENTE)
    public ArrayList ordenaLista(ArrayList<TarefaRobusta> listatarefas){
        
        TarefaRobusta aux;
        for (int i=0; i<listatarefas.size(); i++) {
            for(int j=i+1; j<listatarefas.size(); j++){
                if(listatarefas.get(i).getPeriodo()> listatarefas.get(j).getPeriodo()){
                    aux = listatarefas.get(i);
                    listatarefas.set(i, listatarefas.get(j));
                    listatarefas.set(j, aux);
                }
            }
        }
        return listatarefas;
    }
    
    public void escalonar(ArrayList<TarefaRobusta> listaOrdenada){
        int tempoAtual = 0;
        
        while(tempoAtual < 100){
            TarefaRobusta aux = null;
            
            for(TarefaRobusta tarefa : listaOrdenada){
                if(tarefa.getTempoChegada() <= tempoAtual && (aux == null || tarefa.getPeriodo() < aux.getPeriodo())){
                    aux = tarefa;
                }
                
            if(aux != null){
                aux.execucaoFaltante = Math.min(execucaoFaltante, aux.tempoComputacional);
                execucaoFaltante = execucaoFaltante - 1;
                aux.setTempoChegada(aux.getTempoChegada() + 1);
                tempoAtual = tempoAtual + 1;
                
                if(execucaoFaltante == 0){
                    aux.setTempoChegada(aux.getTempoChegada() + aux.getPeriodo() - aux.getTempoComputacional());
                }
            }
        }
    }
    }
}

    
    

