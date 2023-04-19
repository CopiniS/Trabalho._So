
package trabalho_so;

import java.util.ArrayList;

public class RateMonitonic {
    TarefaRobusta t1, t2, t3, t4;
    int execucaoFaltante = 5;
    ArrayList<TarefaRobusta> listaExecutados;

    //CRIA AS TAREFAS
    public RateMonitonic() {
        this.t1 = new TarefaRobusta("t1", 6, 3, 0);
        this.t2 = new TarefaRobusta("t2", 10, 5, 0);
        this.t3 = new TarefaRobusta("t3", 10, 5, 0);
        this.t4 = new TarefaRobusta("t4", 6, 3, 0);
        listaExecutados = new ArrayList();
    }
    
    //DEIXA LISTA DE TAREFAS ORDENADA POR PERIODO(CRESCENTE)
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
    
    //FAZ O ESCALONAMENTO
    public void escalonar(ArrayList<TarefaRobusta> listaOrdenada){
        int tempoAtual = 0;
        
        //LIMITA A EXECUCAO NO TEMPO DE 100 UNIDADES
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
            //CHAMA O ORDENA LISTA
            ordenaLista(listaOrdenada);
            
            //VERIFICA A PRIORIDADE LISTA
            for(TarefaRobusta tarefa : listaOrdenada){
                if(tarefa.getTempoChegada() <= tempoAtual && (aux == null || tarefa.getPeriodo() < aux.getPeriodo())){
                    aux = tarefa;
                    listaOrdenada.remove(tarefa);
                }
            
            //EXECUTA UMA UNIDADE DE TEMPO    
            if(aux != null){
                System.out.println(aux.getNome() + " está sendo executada no instante " + tempoAtual);
                
                execucaoFaltante = execucaoFaltante - 1;
                aux.setTempoChegada(aux.getTempoChegada() + 1);
                tempoAtual = tempoAtual + 1;
                
                //ADICIONA A LISTA DE EXECUTADOS
                //ATUALIZA O TEMPO DE CHEGADA E O DEADLINE DO PRÓXIMO PERÍODO
                if(execucaoFaltante == 0){
                    System.out.println("\n \n \n \n"
                     + aux.getNome() + "Finalizou a execucao no instante " + String.valueOf(tempoAtual - 1)
                      + "\n \n \n \n");
                    
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

    
    

