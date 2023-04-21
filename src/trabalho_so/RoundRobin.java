
package trabalho_so;

import java.util.ArrayList;

public class RoundRobin {
    Tarefa t1, t2, t3, t4;
    int quantum;
    ArrayList<Tarefa> listatarefas = new ArrayList();
    ArrayList<Tarefa> listaOrdenada = new ArrayList();
    int somaEsperas = 0;
    int somaExecucoes = 0;
    
    

    public RoundRobin() {
        this.t1 = new Tarefa("T1",3, 0);
        this.t2 = new Tarefa("T2",5, 3);
        this.t3 = new Tarefa("T3",5, 2);
        this.t4 = new Tarefa("T4",3, 1);
        this.quantum = 2;
    }
    
    //ADICIONA AS TAREFAS NA LISTA
    public void addLista(){
        listatarefas.add(t1);
        listatarefas.add(t2);
        listatarefas.add(t3);
        listatarefas.add(t4);
        
    }
    
    //ORDENA AS TAREFAS POR TEMPO DE INGRESSO
    public void ordenarTempoChegada(){
        
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
    }
    
    //ORDENA AS TAREFAS POR TEMPO COMPUTACIONAL
    public void OrdenarTempoComputacional(){
            addLista();
            ordenarTempoChegada();
            
            int tempoAtual = 0;
            for(int i=0; i<listatarefas.size(); i++){
                Tarefa aux = null;
                for(int j=0; j<listatarefas.size(); j++){
                    
                    if(i==j || listaOrdenada.contains(listatarefas.get(j))){
                        continue;
                    }
                    if((listatarefas.get(i).getTempoComputacional() > listatarefas.get(j).getTempoComputacional() || listaOrdenada.contains(listatarefas.get(i)))
                        && listatarefas.get(j).getTempoDeIngresso() < tempoAtual 
                        && (aux == null || listatarefas.get(j).getTempoComputacional() < aux.getTempoComputacional())){
                        aux = listatarefas.get(j);
                    }
                }   
                
                if(aux == null){
                    listaOrdenada.add(listatarefas.get(i));
                    tempoAtual = tempoAtual + listatarefas.get(i).getTempoComputacional();
                }
                
                else{
                    listaOrdenada.add(aux);
                    tempoAtual = tempoAtual + aux.getTempoComputacional();
                }
            }
            
        }
    
    //INICIALIZA A LISTA DOS TEMPOS QUE FALTAM PARA FINALIZACAO COM OS TEMPOS COMPUTACIONAIS INICIAIS DE CADA TAREFA
    public void inicializaTempoFaltante(ArrayList<Integer> tempoFaltante){
        for(Tarefa tarefa : listaOrdenada){
            tempoFaltante.add(tarefa.getTempoComputacional());
        }
    }
    
    //EXECUTA
    public void escalona(){
        
        OrdenarTempoComputacional();
        int tempoAtual = 0;
        int completos = 0;
        int tempoAux = 0;
        ArrayList<Integer> tempoFaltante = new ArrayList();
         
        inicializaTempoFaltante(tempoFaltante);
        
        //REPETE O PROCESSO ATÉ QUE A QUANTIDADE DE TAREFASS NA LISTA DAS TAREFAS COMPLETAS SEJA IGUAL A QUANTIDADE DE TAREFAS EXISTENTES NO SISTEMA
        while(completos < listaOrdenada.size()){
            tempoAux = 0;
            for(int i=0; i<listaOrdenada.size(); i++){
                
                //VERIFICA SE A TAREFA NÃO TERMINOU A SUA EXECUCAO AINDA
                if(tempoFaltante.get(i) > 0){
                    System.out.println(listaOrdenada.get(i).getNome() + " está executando no tempo " + tempoAtual);
                    
                    //ATUALIZA O TEMPO DE ESPERA DA TAREFA
                    listaOrdenada.get(i).setEspera(listaOrdenada.get(i).getEspera() + (tempoAux - listaOrdenada.get(i).getTempoDeIngresso()));
                    
                    //FAZ A EXECUÇÃO
                    int executa = Math.min(tempoFaltante.get(i), quantum);
                    tempoFaltante.set(i, (tempoFaltante.get(i) - executa));
                    tempoAtual = tempoAtual + executa;
                    tempoAux = tempoAux + executa;
                
                
                    //VERIFICA SE A TAREFA FINALIZOU, E SE POSITIVO ADICIONA 1 AO CONTADOR DOS COMPLETOS
                    if(tempoFaltante.get(i) == 0){
                    
                        System.out.println("\n\n " + listaOrdenada.get(i).getNome() + " finalizou a execucao no tempo " + tempoAtual + "\n\n");
                        completos++;
                        
                        //FAZ A ATUALIZAÇÃO DO TEMPO DE EXECUCAO DA TAREFA
                        listaOrdenada.get(i).setExecucao(tempoAtual - listaOrdenada.get(i).getEspera());
                        
                        //FAZ A ATUALIZAÇÃO DO TEMPO DE ATRASO DA TAREFA
                        listaOrdenada.get(i).setAtraso(tempoAtual - (listaOrdenada.get(i).getTempoComputacional() + listaOrdenada.get(i).getTempoDeIngresso()));
                    } 
                    
                    
                    //CALCULA AS SOMAS DAS ESPERAS E EXECUCOES
                    somaEsperas = somaEsperas + listaOrdenada.get(i).getEspera();
                    somaExecucoes = somaExecucoes + (listaOrdenada.get(i).getExecucao());
                }
            }
        }
    }
    
    public void calculaExecucaoMedia(){
        double execucaoMedia = somaExecucoes / listaOrdenada.size();
        
        System.out.println("Execucao Média: " + execucaoMedia);
    }
    
    public void calculaEsperaMedia(){
        double esperaMedia = somaEsperas / listaOrdenada.size();
        
        System.out.println("Espera Média: " + esperaMedia);
    }
       
    public void calculaAtrasos(){
        int maior = -1;
        int menor = Integer.MAX_VALUE;
        Tarefa tarefaMaior = null;
        Tarefa tarefaMenor = null;
        
        for(Tarefa tarefa : listatarefas){
            if(tarefa.getAtraso() > maior){
                tarefaMaior = tarefa;
                maior = (int) tarefa.getAtraso();
            }
            
            if(tarefa.getAtraso() < menor){
                tarefaMenor = tarefa;
                menor = (int) tarefa.getAtraso();
            }
        }
        
        System.out.println("Tarefa com maior atraso: " + tarefaMaior.getNome() + " com atraso total de " + tarefaMaior.getAtraso());
        System.out.println("Tarefa com menor atraso: " + tarefaMenor.getNome() + " com atraso total de " + tarefaMenor.getAtraso() + "\n\n");
    }
    
    
}
