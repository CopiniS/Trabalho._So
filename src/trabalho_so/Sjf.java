
package trabalho_so;

import java.util.ArrayList;

public class Sjf {
    Tarefa t1, t2, t3, t4;
    ArrayList<Tarefa> listatarefas = new ArrayList();
    int somaEsperas = 0;
    int somaexecucao = 0;
    
    public Sjf() {
        this.t1 = new Tarefa("T1", 3, 0);
        this.t2 = new Tarefa("T2",5, 3);
        this.t3 = new Tarefa("T3",5, 2);
        this.t4 = new Tarefa("T4",3, 1);
    }
    
    public void addLista(){
        listatarefas.add(t1);
        listatarefas.add(t2);
        listatarefas.add(t3);
        listatarefas.add(t4);
        
    }
    
    //ORDENA AS TAREFAS PELO TEMPO DE INGRESSO
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
            
            ArrayList<Tarefa> listaOrdenada = new ArrayList();
            int tempoAtual = 0;
            Tarefa aux;
            for(int i=0; i<listatarefas.size(); i++){
                aux = null;
                for(int j=0; j<listatarefas.size();j++){
                    
                    if(i==j || listaOrdenada.contains(listatarefas.get(j))){
                        continue;
                    }
                    
                    //COMPARA AS TAREFAS PARA ACHAR A COM MENOR TEMPO COMPUTACIONAL QUE JA TENHA 
                    //INGRESSADO NO SISTEMA, OU SEJA QUE O TEMPO DE INGRESSO SEJA MENOR QUE O TEMPO ATUAL
                    if(listatarefas.get(i).getTempoComputacional() > listatarefas.get(j).getTempoComputacional()
                        && listatarefas.get(j).getTempoDeIngresso() < tempoAtual){
                        aux = listatarefas.get(j);
                        
                    }
                }
                
                
                
                //EXECUTA A TAREFA GUARDADA EM LISTATAREFA.GET(i)
                if(aux == null){
                    
                    listatarefas.get(i).setEspera(tempoAtual - listatarefas.get(i).getTempoDeIngresso());
                    
                    System.out.println(listatarefas.get(i).getNome() + " iniciou a execucao no tempo: " + tempoAtual);
                    tempoAtual = tempoAtual + listatarefas.get(i).tempoComputacional;
                    System.out.println(listatarefas.get(i).getNome() + " finalizou a execucao no tempo: " + tempoAtual + "\n\n\n");
                    
                    listaOrdenada.add(listatarefas.get(i));
                    
                    //CALCULA AS SOMAS DAS ESPERAS / EXECUCOES / ATRASOS
                    somaEsperas = somaEsperas + listatarefas.get(i).getEspera();
                    listatarefas.get(i).setExecucao(listatarefas.get(i).getEspera() + listatarefas.get(i).getTempoComputacional());
                    somaexecucao = somaexecucao + (listatarefas.get(i).getExecucao());
                }
                
                //GUARDA NA LISTA ORDENADA O OBJETO AUX
                else{
                    System.out.println(aux.getNome() + " iniciou a execucao no tempo: " + tempoAtual);
                    tempoAtual = tempoAtual + aux.getTempoComputacional();
                    System.out.println(aux.getNome() + " finalizou a execucao no tempo: " + tempoAtual + "\n\n\n");
                    
                    listaOrdenada.add(aux);
                    
                    somaEsperas = somaEsperas + aux.getEspera();
                    System.out.println("contador222");
                }
            }
        }
        
        public void calculaEsperaMedia(){
            double esperaMedia = somaEsperas / listatarefas.size();
        
            System.out.println("Espera Média: " + esperaMedia + "\n\n\n");
        }
        
        public void calculaExecucaomedia(){
            double execucaoMedia = somaexecucao / listatarefas.size();
        
            System.out.println("Execucao Média: " + execucaoMedia + "\n \n \n");
        }
        
        
}
