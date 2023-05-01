
package trabalho_so;

import java.util.ArrayList;

public class EDF {
     TarefaRobusta t1, t2, t3, t4;
    ArrayList<TarefaRobusta> listaExecutados = new ArrayList();
    ArrayList<TarefaRobusta> listatarefas = new ArrayList();

    //CRIA AS TAREFAS
    public EDF() {
        this.t1 = new TarefaRobusta("t1", 6, 3, 0);
        this.t2 = new TarefaRobusta("t2", 10, 5, 0);
        this.t3 = new TarefaRobusta("t3", 10, 5, 0);
        this.t4 = new TarefaRobusta("t4", 6, 3, 0);
        
    }
    
    public void addLista(){
        listatarefas.add(t1);
        listatarefas.add(t2);
        listatarefas.add(t3);
        listatarefas.add(t4);
        
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
                    if(listaExecutados.get(i).getTempoChegada() <= tempoAtual){
                        listaExecutados.remove(listaExecutados.get(i));
                        
                    }
                }
               
            }    
            
            //CHAMA O ORDENA LISTA
            ordenaLista();
            
            //VERIFICA A PRIORIDADE LISTA
            for(TarefaRobusta tarefa : listatarefas){
                if(tarefa.getTempoChegada() <= tempoAtual && (aux == null || tarefa.getDeadlineFaltante()< aux.getDeadlineFaltante())
                        && !listaExecutados.contains(tarefa)){
                    aux = tarefa;
                }
                
            }
            
            //EXECUTA UMA UNIDADE DE TEMPO    
            if(aux != null){
                System.out.println(aux.getNome() + " está sendo executada no instante " + tempoAtual);
                
                aux.setExecucaoFaltante(aux.getExecucaoFaltante() - 1);
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
                    
                    aux.setTempoChegada(aux.getTempoChegada() + aux.getPeriodo());
                    System.out.println("tempo de chegada: "+aux.getTempoChegada());
                    listaExecutados.add(aux);
                    aux.setDeadline(aux.getDeadline() + aux.getPeriodo());
                    aux.setExecucaoFaltante(aux.getTempoComputacional());
                    aux.setDeadlineFaltante(aux.getDeadline() - tempoAtual);
                    aux = null;
                    
                }
            }
            else{
                System.out.println("NÃO A TAREFAS A SEREM EXECUTADAS NO TEMPO " + tempoAtual);
                tempoAtual++;
            }
            
            
            //VERIFICA SE A TAREFA QUE ESTÁ EM EXECUÇÃO PERDEU DEADLINE
            boolean quebrarWhile1 = false;
            if(aux != null && aux.getDeadlineFaltante() < 0){
                System.out.println("A TAREFA " + aux.getNome() + " PERDEU DEADLINE NO INSTANTE " + tempoAtual);
                quebrarWhile1 = true;
                
            }
            
            boolean quebrarWhile2 = false;
            //VERIFICA SE ALGUMA TAREFA QUE ESTÁ NA FILA PERDEU DEADLINE
            for(int i=0; i<listatarefas.size();i++){
                if(!listatarefas.isEmpty() && listatarefas.get(i).getDeadlineFaltante() < 0 && !listatarefas.get(i).equals(aux)){
                    System.out.println("A TAREFA " + listatarefas.get(i).getNome() + " PERDEU DEADLINE NO INSTANTE " + tempoAtual);
                    quebrarWhile2 = true;
                }
            }
            if(quebrarWhile1 == true || quebrarWhile2 == true){
                break;
            }
    }
    }
    
    public void calculaEsperaMerdia(){
        
    }
    
    public void calculaExecucaoMedia(){
    
    }
    
    public void calculaAtrasos(){
        
    }
}
