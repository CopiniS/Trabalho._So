
package trabalho_so;

import java.util.ArrayList;

public class RoundRobin {
    Tarefa t1, t2, t3, t4;
    int quantum;
    ArrayList<Tarefa> listatarefas = new ArrayList();
    
    

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
    public ArrayList OrdenarTempoComputacional(){
            
        
            ArrayList<Tarefa> listaOrdenada = new ArrayList();
            addLista();
            ordenarTempoChegada();
            
            
            int tempoAtual = 0;
            for(int i=0; i<listatarefas.size(); i++){
                Tarefa aux = null;
                for(int j=0; j<listatarefas.size(); j++){
                    
                    if(i==j || listaOrdenada.contains(listatarefas.get(j))){
                        continue;
                    }
                    if(listatarefas.get(i).getTempoComputacional() > listatarefas.get(j).getTempoComputacional()
                        && listatarefas.get(j).getTempoDeIngresso() < tempoAtual){
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
            System.out.println(listaOrdenada.get(0));
            System.out.println(listaOrdenada.get(1));
            System.out.println(listaOrdenada.get(2));
            System.out.println(listaOrdenada.get(3));
            return listaOrdenada;
        }
    
    //INICIALIZA A LISTA DOS TEMPOS QUE FALTAM PARA FINALIZACAO COM OS TEMPOS COMPUTACIONAIS INICIAIS DE CADA TAREFA
    public void inicializaTempoFaltante(ArrayList<Tarefa> listaOrdenada, ArrayList<Integer> tempoFaltante){
        for(Tarefa tarefa : listaOrdenada){
            tempoFaltante.add(tarefa.getTempoComputacional());
        }
    }
    
    //EXECUTA
    public ArrayList escalona(ArrayList<Tarefa> listaOrdenada){
        
        
        int tempoAtual = 0;
        int completos = 0;
        ArrayList<Integer> tempoCompletos = new ArrayList();
        ArrayList<Integer> tempoFaltante = new ArrayList(); 
         
        inicializaTempoFaltante(listaOrdenada, tempoFaltante);
        
        //REPETE O PROCESSO ATÉ QUE A LISTA DAS TAREFAS COMPLETAMENTE EXECUTADAS SEJA IGUAL A QUANTIDADE DE TAREFAS
        while(completos < listaOrdenada.size()){
            for(int i=0; i<listaOrdenada.size(); i++){
                
                //VERIFICA SE A TAREFA NÃO TERMINOU A SUA EXECUCAO AINDA
                if(tempoFaltante.get(i) > 0){
                    
                    System.out.println(listaOrdenada.get(i).getNome() + " está executando no tempo " + tempoAtual);
                    
                    //GUARDA NA VARIAVEL EXECUTA O MENOR VALOR ENTRE O VALOR QUANTUM E A QUANTIDADE DE TEMPO DE EXECUCAO
                    //QUE FALTA PARA A TAREFA FINALIZAR, DEPOIS UTILIZA A VARIAVEL PARA FAZER A EXECUCAO DA TAREFA EM RELACAO AO TEMPO
                    int executa = Math.min(tempoFaltante.get(i), quantum);
                    tempoFaltante.set(i, (tempoFaltante.get(i) - executa));
                    tempoAtual = tempoAtual + executa;
                
                
                    //VERIFICA SE A TAREFA FINALIZOU, E SE POSITIVO ADICIONA NA LISTA DAS EXECUCOES COMPLETAMENTE FINALIZADAS
                    if(tempoFaltante.get(i) == 0){
                    
                        System.out.println(listaOrdenada.get(i).getNome() + " finalizou a execucao no tempo " + tempoAtual);
                    
                        tempoCompletos.add(i, tempoAtual);
                        completos++;
                    } 
                }
            }
        }
        //RETORNA EM QUE MOMENTO FORAM REALIZADAS AS FINALIZACOES DAS EXECUCOES PARA SE FAZER OS CALCULOS DAS MEDIAS
        return tempoCompletos;
    }
    
    public double calculaExecucaoMedia(ArrayList<Integer> tempoCompletos, ArrayList<Tarefa> listaOrdenada){
        int somaExecucao = 0;
        
        for(int i=0; i<tempoCompletos.size(); i++){
           listaOrdenada.get(i).setExecucao(tempoCompletos.get(i) - listaOrdenada.get(i).getTempoDeIngresso());
           
           somaExecucao = somaExecucao + listaOrdenada.get(i).getExecucao();
        }
        return (double) somaExecucao / (double) listaOrdenada.size();
    }
    
    public double calculaEsperaMedia(ArrayList<Tarefa> listaOrdenada){
        int somaEspera = 0;
        for(Tarefa tarefa : listaOrdenada){
            tarefa.setEspera(tarefa.execucao - tarefa.tempoComputacional);
            somaEspera = somaEspera + tarefa.espera;
        }
        return (double) somaEspera / (double) listaOrdenada.size();
    }
    
    
}
