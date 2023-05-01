
package trabalho_so;

import java.util.ArrayList;
import java.util.Collections;

public class RoundRobin {
    Tarefa t1, t2, t3, t4;
    int quantum;
    ArrayList<Tarefa> listatarefas = new ArrayList();
    ArrayList<Tarefa> listaExecutados = new ArrayList();
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
        Collections.sort(listatarefas);
    }
    
    
    //EXECUTA
    public void escalona(){
        addLista();
        int tempoAtual = 0;
        Tarefa aux = null;
        int numero = listatarefas.size();
        //REPETE O PROCESSO ATÉ QUE A QUANTIDADE DE TAREFASS NA LISTA DAS TAREFAS COMPLETAS SEJA IGUAL A QUANTIDADE DE TAREFAS EXISTENTES NO SISTEMA
        while(listaExecutados.size() < numero){
               
                ordenarTempoChegada();
                
               
                for(int i=0; i<listatarefas.size(); i++){
                    if(aux == null || listatarefas.get(i).getTempoDeIngresso() < aux.getTempoDeIngresso()){
                        aux = listatarefas.get(i);
                    }
                }
                
                
                //EXECUTA
                if(aux != null){
                    System.out.println(aux.getNome() + " está executando no tempo " + tempoAtual);
                    
                    //ATUALIZA O TEMPO DE ESPERA DA TAREFA
                    aux.setEspera(listatarefas.get(0).getEspera() + (tempoAtual - aux.getTempoDeIngresso()));
                    
                    //FAZ A EXECUÇÃO
                    int executa = Math.min(aux.getTempofaltante(), quantum);
                    aux.setTempofaltante(aux.getTempofaltante() - executa);
                    tempoAtual = tempoAtual + executa;
                    aux.setTempoDeIngresso(tempoAtual);
                
                
                    //VERIFICA SE A TAREFA FINALIZOU, E SE POSITIVO ADICIONA 1 AO CONTADOR DOS COMPLETOS
                    if(aux.getTempofaltante() == 0){
                        System.out.println("\n\n " + aux.getNome() + " finalizou a execucao no tempo " + tempoAtual + "\n\n");
                        
                        listatarefas.remove(aux);
                        listaExecutados.add(aux);
                        
                    }
                    
                    else{
                        System.out.println(aux.getNome() + " está executando no tempo " + String.valueOf(tempoAtual-1));
                    }
                    
                    
                    //CALCULA AS SOMAS DAS ESPERAS E EXECUCOES
                    somaEsperas = somaEsperas + aux.getEspera();
                    
                }
        }
    }
    
    public void calculaExecucaoMedia(){
        double resultado;
        
        for(Tarefa tarefa : listaExecutados){
            somaExecucoes = somaExecucoes + tarefa.getTempoComputacional();
        }
        resultado = somaExecucoes / listaExecutados.size();
                
        System.out.println("Tempo de Execucao media: " + resultado);
    }
    
    public void calculaEsperaMedia(){
        double esperaMedia = somaEsperas / listaExecutados.size() ;
        
        System.out.println("Espera Média: " + esperaMedia);
    }
       
    public void calculaAtrasos(){
        int maior = -1;
        int menor = Integer.MAX_VALUE;
        Tarefa tarefaMaior = null;
        Tarefa tarefaMenor = null;
        
        for(Tarefa tarefa : listaExecutados){
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
