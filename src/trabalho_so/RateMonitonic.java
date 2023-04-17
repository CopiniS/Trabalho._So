
package trabalho_so;

import java.util.ArrayList;

public class RateMonitonic {
    TarefaRobusta t1, t2, t3, t4;
    int execucaoFaltante = 5;
    ArrayList<TarefaRobusta> listaExecutados;

    public RateMonitonic() {
        this.t1 = new TarefaRobusta("t1", 6, 3, 0);
        this.t2 = new TarefaRobusta("t2", 10, 5, 0);
        this.t3 = new TarefaRobusta("t3", 10, 5, 0);
        this.t4 = new TarefaRobusta("t4", 6, 3, 0);
        listaExecutados = new ArrayList();
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
            
            //VERIFICA SE ALGUMA TAREFA PERDEU DEADLINE
            for(TarefaRobusta tarefa : listaOrdenada){
                if(tarefa.deadline < tempoAtual){
                    System.out.println("A TAREFA " + tarefa.nome + "PERDEU DEADLINE");
                    System.exit(0);
                }
            }
            
            //VERIFICA SE CHEGOU ALGUMA TAREFA NOVA
            if(!listaExecutados.isEmpty()){
                for(int i=0; i<listaExecutados.size(); i++){
                    if(listaExecutados.get(i).getTempoChegada() == tempoAtual){
                        listaOrdenada.add(listaExecutados.get(i));
                    }
                }
            }    
            //ORDENA A LISTA 
            ordenaLista(listaOrdenada);
            
            //VERIFICA A PRIORIDADE LISTA
            for(TarefaRobusta tarefa : listaOrdenada){
                if(tarefa.getTempoChegada() <= tempoAtual && (aux == null || tarefa.getPeriodo() < aux.getPeriodo())){
                    aux = tarefa;
                    listaOrdenada.remove(tarefa);
                }
                
            if(aux != null){
                execucaoFaltante = execucaoFaltante - 1;
                aux.setTempoChegada(aux.getTempoChegada() + 1);
                tempoAtual = tempoAtual + 1;
                
                if(execucaoFaltante == 0){
                    aux.setTempoChegada(aux.getTempoChegada() + aux.getPeriodo() - aux.getTempoComputacional());
                    listaExecutados.add(aux);
                    aux.setDeadline(aux.getDeadline() + aux.getPeriodo());
                    aux = null;
                    
                }
            }
            else{
                System.out.println("NÃO A TAREFAS A SEREM EXECUTADAS");
            }
        }
    }
    }
}

    
    

