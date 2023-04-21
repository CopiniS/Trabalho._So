
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
                    
                    //FAZ A ATUALIZAÇÃO DO TEMPO DE ESPERA DA TAREFA
                    listatarefas.get(i).setEspera(tempoAtual - listatarefas.get(i).getTempoDeIngresso());
                    
                    //FAZ A EXECUÇÃO
                    System.out.println(listatarefas.get(i).getNome() + " iniciou a execucao no tempo: " + tempoAtual);
                    tempoAtual = tempoAtual + listatarefas.get(i).tempoComputacional;
                    System.out.println(listatarefas.get(i).getNome() + " finalizou a execucao no tempo: " + tempoAtual + "\n\n\n");
                    listaOrdenada.add(listatarefas.get(i));
                    
                    //FAZ A ATUALIZAÇÃO DO TEMPO DE ATRASO DA TAREFA
                    listatarefas.get(i).setAtraso(tempoAtual - listatarefas.get(i).getTempoComputacional() + listatarefas.get(i).getTempoDeIngresso());
                    
                    //FAZ A ATUALIZAÇÃO DO TEMPO DE EXECUÇAO DA TAREFA
                    listatarefas.get(i).setExecucao(listatarefas.get(i).getTempoComputacional());
                    
                    //CALCULA AS SOMAS DAS ESPERAS E EXECUCOES
                    somaEsperas = somaEsperas + listatarefas.get(i).getEspera();
                    somaexecucao = somaexecucao + (listatarefas.get(i).getExecucao());
                }
                
                //GUARDA NA LISTA ORDENADA O OBJETO AUX
                else{
                    
                    //FAZ A ATUALIZAÇÃO DO TEMPO DE ESPERA DA TAREFA
                    aux.setEspera(tempoAtual - aux.getTempoDeIngresso());
                    
                    //FAZ A EXECUÇAO
                    System.out.println(aux.getNome() + " iniciou a execucao no tempo: " + tempoAtual);
                    tempoAtual = tempoAtual + aux.getTempoComputacional();
                    System.out.println(aux.getNome() + " finalizou a execucao no tempo: " + tempoAtual + "\n\n\n");
                    listaOrdenada.add(aux);
                    
                    //FAZ A ATUALIZAÇÃO DO TEMPO DE ATRASO DA TAREFA
                    aux.setAtraso(tempoAtual - aux.getTempoComputacional() + aux.getTempoDeIngresso());
                    
                    //FAZ A ATUALIZAÇÃO DO TEMPO DE EXECUÇAO DA TAREFA
                    aux.setExecucao(aux.getTempoComputacional());
                    
                    //CALCULA AS SOMAS DAS ESPERAS E EXECUCOES 
                    somaEsperas = somaEsperas + aux.getEspera();
                    somaexecucao = somaexecucao + (aux.getExecucao());
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
