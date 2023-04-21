
package trabalho_so;

import java.util.ArrayList;

public class RateMonitonic {
    TarefaRobusta t1, t2, t3, t4;
    ArrayList<TarefaRobusta> listaExecutados = new ArrayList();
    ArrayList<TarefaRobusta> listatarefas = new ArrayList();
    int contTarefas = 4;
    int somaEsperas = 0;
    int somaExecucoes = 0;

    //INICIALIZA AS TAREFAS
    public RateMonitonic() {
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
    
    //DEIXA LISTA DE TAREFAS ORDENADA POR PERIODO(CRESCENTE)
    public void ordenaLista(){
        
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
                        contTarefas++;
                    }
                }
            }    
            //CHAMA O ORDENA LISTA
            ordenaLista();
            
            //VERIFICA A PRIORIDADE LISTA
            for(int i=0; i<listatarefas.size(); i++){
                if(listatarefas.get(i).getTempoChegada() <= tempoAtual && (aux == null || listatarefas.get(i).getPeriodo() < aux.getPeriodo())){
                    aux = listatarefas.get(i);
                    listatarefas.remove(listatarefas.get(i));
                }
            }
            
            //EXECUTA UMA UNIDADE DE TEMPO    
            if(aux != null){
                
                //ATUALIZA O TEMPO DE ESPERA
                if(aux.getTempoComputacional() == aux.getExecucaoFaltante()){
                aux.setEspera(tempoAtual - aux.getTempoChegada());
                }
                
                System.out.println(aux.getNome() + " está sendo executada no instante " + tempoAtual);
                aux.setExecucaoFaltante(aux.getExecucaoFaltante() - 1);
                aux.setTempoChegada(aux.getTempoChegada() + 1);
                tempoAtual = tempoAtual + 1;
               
                
                //SOMA DAS ESPERAS E DAS EXECUCOES
                somaEsperas = somaEsperas + aux.getEspera();
                
                //VERIFICA SE ACABOU A EXECUÇÃO DA TAREFA QUE ESTAVA EXECUTANDO
                if(aux.execucaoFaltante == 0){
                    System.out.println("\n \n \n \n"
                     + aux.getNome() + "Finalizou a execucao no instante " + String.valueOf(tempoAtual)
                      + "\n \n \n \n");
                    
                    //ATUALIZA AS FUNÇÕES PARA A EXECUÇÃO
                    aux.setTempoChegada(aux.getTempoChegada() + aux.getPeriodo() - aux.getTempoComputacional());
                    aux.setDeadline(aux.getDeadline() + aux.getPeriodo());
                    aux.setExecucaoFaltante(aux.getTempoComputacional());
                    
                    //ATUALIZA O TEMPO DE EXECUÇÃO
                    aux.setExecucao(tempoAtual - aux.getEspera());
                    
                    //ATUALIZA O TEMPO DE ATRASO
                    
                    
                    //ADICIONA A LISTA DE EXECUTADOS;
                    listaExecutados.add(aux);
                    aux = null;
                    
                }
                
            }
            else{
                System.out.println("NÃO A TAREFAS A SEREM EXECUTADAS");
                System.exit(0);
            }
            
            
            //VERIFICA SE A TAREFA QUE ESTÁ EM EXECUÇÃO PERDEU DEADLINE
            if(aux != null && aux.getDeadline() < tempoAtual){
                System.out.println("A TAREFA " + aux.getNome() + " PERDEU DEADLINE NO INSTANTE " + tempoAtual);
                break;
            }
            
            boolean quebrarWhile = false;
            //VERIFICA SE ALGUMA TAREFA DA FILA PERDEU DEADLINE
            for(TarefaRobusta tarefa : listatarefas){
                if(tarefa.getDeadline() < tempoAtual){
                    System.out.println("A TAREFA " + tarefa.nome + " PERDEU DEADLINE NO INSTANTE " + tempoAtual);
                    quebrarWhile = true;
                    
                }
            }
            if(quebrarWhile == true){
                break;
            }
    }
    }
    
    public void calculaExecucaoMedia(){
        
    }
    
    public void calculaEsperaMedia(){
        
    }
    
    public void calculaAtrasos(){
        
    }
}

    
    

