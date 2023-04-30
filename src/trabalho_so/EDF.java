
package trabalho_so;

import java.util.ArrayList;

public class EDF {
     TarefaRobusta t1, t2, t3, t4;
    ArrayList<TarefaRobusta> listaExecutados = new ArrayList();
    ArrayList<TarefaRobusta> listatarefas = new ArrayList();

    //CRIA AS TAREFAS
    public EDF() {
        this.t1 = new TarefaRobusta("t1", 20, 10, 0);
        this.t2 = new TarefaRobusta("t2", 50, 25, 0);
        
    }
    
    public void addLista(){
        listatarefas.add(t1);
        listatarefas.add(t2);
        
    }
    
    //DEIXA LISTA DE TAREFAS ORDENADA POR DEADLINES(CRESCENTE)
    public void ordenaLista(){
        
        TarefaRobusta aux;
        for (int i=0; i<listatarefas.size(); i++) {
            for(int j=i+1; j<listatarefas.size(); j++){
                if(listatarefas.get(i).getDeadlineFaltante()> listatarefas.get(j).getDeadlineFaltante()){
                    aux = listatarefas.get(i);
                    listatarefas.set(i, listatarefas.get(j));
                    listatarefas.set(j, aux);
                }
            }
        }
    }
    
    //FAZ O ESCALONAMENTO
    public void escalonar(){
        int tempoAtual = 0;
        addLista();
        ordenaLista();
        
        TarefaRobusta aux = null;
        //LIMITA A EXECUCAO NO TEMPO DE 100 UNIDADES
        while(tempoAtual < 100){
            
            
            //VERIFICA SE CHEGOU ALGUMA TAREFA NOVA
            if(!listaExecutados.isEmpty()){
                for(int i=0; i<listaExecutados.size(); i++){
                    if(listaExecutados.get(i).getTempoChegada() == tempoAtual){
                        listatarefas.add(listaExecutados.get(i));
                    }
                }
            }    
            //CHAMA O ORDENA LISTA
            ordenaLista();
            
            //VERIFICA A PRIORIDADE LISTA
            for(int i=0; i<listatarefas.size(); i++){
                if(listatarefas.get(i).getTempoChegada() <= tempoAtual && (aux == null || listatarefas.get(i).getDeadlineFaltante()< aux.getDeadlineFaltante())){
                    aux = listatarefas.get(i);
                    listatarefas.remove(listatarefas.get(i));
                }
            }
            
            //EXECUTA UMA UNIDADE DE TEMPO    
            if(aux != null){
                System.out.println(aux.getNome() + " está sendo executada no instante " + tempoAtual);
                
                aux.setExecucaoFaltante(aux.getExecucaoFaltante() - 1);
                aux.setTempoChegada(aux.getTempoChegada() + 1);
                aux.setDeadlineFaltante(aux.getDeadlineFaltante() - 1);
                tempoAtual = tempoAtual + 1;
                
                //ATUALIZA OS DEADLINES A CADA EXECUCAO
                for(TarefaRobusta tarefa : listatarefas){
                    tarefa.setDeadlineFaltante(tarefa.getDeadlineFaltante() - 1);
                }
                
               
                
                //ADICIONA A LISTA DE EXECUTADOS
                //ATUALIZA O TEMPO DE CHEGADA E O DEADLINE DO PRÓXIMO PERÍODO
                if(aux.execucaoFaltante == 0){
                    System.out.println("\n \n \n \n"
                     + aux.getNome() + "Finalizou a execucao no instante " + String.valueOf(tempoAtual)
                      + "\n \n \n \n");
                    
                    aux.setTempoChegada(aux.getTempoChegada() + aux.getPeriodo() - aux.getTempoComputacional());
                    listaExecutados.add(aux);
                    aux.setDeadline(aux.getDeadline() + aux.getPeriodo());
                    aux.setExecucaoFaltante(aux.getTempoComputacional());
                    aux.setDeadlineFaltante(aux.getDeadline());
                    aux = null;
                    
                }
            }
            else{
                System.out.println("NÃO A TAREFAS A SEREM EXECUTADAS");
                System.exit(0);
            }
            
            
            //VERIFICA SE A TAREFA QUE ESTÁ EM EXECUÇÃO PERDEU DEADLINE
            if(aux != null && aux.getDeadlineFaltante() < 0){
                System.out.println("A TAREFA " + aux.getNome() + " PERDEU DEADLINE NO INSTANTE " + tempoAtual);
                break;
            }
            
            boolean quebrarWhile = false;
            //VERIFICA SE ALGUMA TAREFA QUE ESTÁ NA FILA PERDEU DEADLINE
            for(int i=0; i<listatarefas.size();i++){
                if(!listatarefas.isEmpty() && listatarefas.get(i).getDeadlineFaltante() < 0){
                    System.out.println("A TAREFA " + listatarefas.get(i).getNome() + " PERDEU DEADLINE NO INSTANTE " + tempoAtual);
                    quebrarWhile = true;
                }
            }
            if(quebrarWhile == true){
                break;
            }
    }
    }
}
