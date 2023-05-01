
package trabalho_so;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class FcFs {
    Tarefa t1, t2, t3, t4;
    int somaEsperas = 0;
    int somaexecucao= 0;
    ArrayList<Tarefa> listatarefas;
    
    

    public FcFs() {
        t1 = new Tarefa("T1",3, 0); 
        t2 = new Tarefa("T2",5, 3);  
        t3 = new Tarefa("T3",5, 2); 
        t4 = new Tarefa("T4",3, 1); 
        listatarefas = new ArrayList();
       
    }
    //ADICIONA AS TAREFAS NA LISTA
    public void addLista(){
        listatarefas.add(t1);
        listatarefas.add(t2);
        listatarefas.add(t3);
        listatarefas.add(t4);
        
    }
    
    //ORDENA A LISTA
    public void ordenarTempoChegada(){
        Tarefa aux;
        for (int i=0; i<listatarefas.size(); i++) {
            aux = null;
            for(int j=i+1; j<listatarefas.size(); j++){
                if(listatarefas.get(i).getTempoDeIngresso() > listatarefas.get(j).getTempoDeIngresso()){
                    aux = listatarefas.get(i);
                    listatarefas.set(i, listatarefas.get(j));
                    listatarefas.set(j, aux);
                }
            }
        }
    }
    
    
    
    //EXECUTA AS TAREFAS
    public void escalona(){
        
        addLista();
        ordenarTempoChegada();
        
        int tempoAtual = 0;
       
        for(Tarefa tarefa : listatarefas){
            
            //FAZ A ATUALIZAÇÃO DO TEMPO DE ESPERA
            tarefa.setEspera(tempoAtual - tarefa.getTempoDeIngresso());
            
            System.out.println(tarefa.getNome() + " iniciou a execucao no tempo: " + tempoAtual);
            tempoAtual = tempoAtual + tarefa.getTempoComputacional();
            System.out.println(tarefa.getNome() + " finalizou a execucao no tempo: " + tempoAtual + "\n\n\n");
            
            //FAZ A ATUALIZAÇÃO DO TEMPO DE EXECUÇÃO
            tarefa.setExecucao(tarefa.getTempoComputacional());
            
            //CALCULA AS SOMAS DAS ESPERAS / EXECUCOES 
            somaEsperas = somaEsperas + tarefa.getEspera();
            somaexecucao = somaexecucao + (tarefa.getExecucao());
        }
    }
    
    public void calculaAtrasos(){
        int maior = -1;
        int menor = Integer.MAX_VALUE;
        Tarefa tarefaMaior = null;
        Tarefa tarefaMenor = null;
        
        for(Tarefa tarefa : listatarefas){
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
    
    public void calculaEsperaMedia(){
        double esperaMedia = (double)(somaEsperas) / (double)(listatarefas.size());
        
        System.out.println("Espera Média: " + esperaMedia + "\n \n");
        
    }
    
    public void calculaExecucaoMedia(){
        double execucaoMedia = somaexecucao / listatarefas.size();
        
        System.out.println("Execucao Média: " + execucaoMedia + "\n \n");
    }
    
}
