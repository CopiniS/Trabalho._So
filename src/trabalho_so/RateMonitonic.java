
package trabalho_so;

import java.util.ArrayList;

public class RateMonitonic {
    TarefaRobusta t1, t2, t3, t4;
    ArrayList<TarefaRobusta> listaExecutados = new ArrayList();
    ArrayList<TarefaRobusta> listatarefas = new ArrayList();
    int contTarefas = 0;
    int somaEsperas = 0;
    int somaExecucoes = 0;

    //INICIALIZA AS TAREFAS
    public RateMonitonic() {
        this.t1 = new TarefaRobusta("t1", 100, 20, 0);
        this.t2 = new TarefaRobusta("t2", 150, 40, 0);
        this.t3 = new TarefaRobusta("t3", 350, 100, 0);
        
        
    }
    
    public void addLista(){
        listatarefas.add(t1);
        listatarefas.add(t2);
        listatarefas.add(t3);
        
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
        int tempoAux = 0;
        addLista();
        ordenaLista();
        
        TarefaRobusta aux = null;
        //LIMITA A EXECUCAO NO TEMPO DE 100 UNIDADES
        while(tempoAtual < 1000){
            
            
            //VERIFICA SE CHEGOU ALGUMA TAREFA NOVA
            if(listaExecutados.size() != 0){
                for(TarefaRobusta tarefa : listaExecutados){
                    if(tarefa.getTempoChegada() == tempoAtual){
                        listatarefas.add(tarefa);
                        System.out.println(listaExecutados.size());
                        
                    }
                }
            }    
            //CHAMA O ORDENA LISTA
            ordenaLista();
            System.out.println(contTarefas);
            
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
                    System.out.println("tempo de espera de " + aux.getNome() + " " + aux.getEspera());
                
                    //SOMA DAS ESPERAS
                    somaEsperas = somaEsperas + aux.getEspera();
                }
                
                
                
                System.out.println(aux.getNome() + " está sendo executada no instante " + tempoAtual);
                aux.setExecucaoFaltante(aux.getExecucaoFaltante() - 1);
                aux.setTempoChegada(aux.getTempoChegada() + 1);
                tempoAtual++;
               
                
                
                //VERIFICA SE ACABOU A EXECUÇÃO DA TAREFA QUE ESTAVA EXECUTANDO
                if(aux.execucaoFaltante == 0){
                    System.out.println("\n \n \n \n"
                     + aux.getNome() + "Finalizou a execucao no instante " + String.valueOf(tempoAtual)
                      + "\n \n \n \n");
                    
                    
                    
                    
                    //ATUALIZA O TEMPO DE EXECUÇÃO
                    aux.setExecucao(aux.tempoComputacional);
                    
                    //SOMA DAS EXECUCOES
                    somaExecucoes = somaExecucoes + aux.getExecucao();
                    
                    
                    //ATUALIZA AS FUNÇÕES PARA A EXECUÇÃO
                    aux.setTempoChegada(aux.getTempoChegada() + aux.getPeriodo() - aux.getTempoComputacional());
                    aux.setDeadline(aux.getDeadline() + aux.getPeriodo());
                    aux.setExecucaoFaltante(aux.getTempoComputacional());
                    
                    
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
        double resultado = somaExecucoes / listaExecutados.size();
        
        System.out.println("Tempo de Execucao media: " + resultado);
    }
    
    public void calculaEsperaMedia(){
        double resultado = somaEsperas / (double)(contTarefas);
        
        System.out.println("Tempo de Espera media: " + resultado);
    }
    
    public void calculaAtrasos(){
        int maior = -1;
        int menor = Integer.MAX_VALUE;
        TarefaRobusta tarefaMaior = null;
        TarefaRobusta tarefaMenor = null;
        
        for(TarefaRobusta tarefa : listatarefas){
            if(tarefa.getEspera()> maior){
                tarefaMaior = tarefa;
                maior = (int) tarefa.getEspera();
            }
            
            if(tarefa.getEspera()< menor){
                tarefaMenor = tarefa;
                menor = (int) tarefa.getEspera();
            }
        }
        
        System.out.println("Tarefa com maior atraso: " + tarefaMaior.getNome() + " com atraso total de " + tarefaMaior.getEspera());
        System.out.println("Tarefa com menor atraso: " + tarefaMenor.getNome() + " com atraso total de " + tarefaMenor.getEspera()+ "\n\n");
        
    }
}

    
    

