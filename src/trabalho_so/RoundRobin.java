
package trabalho_so;

import java.util.ArrayList;

public class RoundRobin {
    Tarefa t1, t2, t3, t4;
    int quantum;
    
    

    public RoundRobin() {
        this.t1 = new Tarefa(3, 0);
        this.t2 = new Tarefa(5, 3);
        this.t3 = new Tarefa(5, 2);
        this.t4 = new Tarefa(3, 1);
        this.quantum = 2;
    }
    
    public void ordenarTempoChegada(ArrayList<Tarefa> listatarefas){
        
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
    
    public ArrayList OrdenarTempoComputacional(ArrayList<Tarefa> listatarefas){
            ArrayList<Tarefa> listaOrdenada = new ArrayList();
            int tempoAtual = 0;
            for(int i=0; i<listatarefas.size(); i++){
                Tarefa aux = null;
                for(int j=0; j<listatarefas.size();i++){
                    
                    if(i==j || listaOrdenada.contains(listatarefas.get(i))){
                        break;
                    }
                    if(listatarefas.get(i).getTempoComputacional() > listatarefas.get(j).getTempoComputacional()
                        && listatarefas.get(j).getTempoDeIngresso() < tempoAtual){
                        aux = listatarefas.get(j);
                    }
                }   
                
                if(aux == null){
                    listaOrdenada.add(listatarefas.get(i));
                    tempoAtual = tempoAtual + listatarefas.get(i).tempoComputacional;
                }
                
                else{
                    listaOrdenada.add(aux);
                    tempoAtual = tempoAtual + aux.getTempoComputacional();
                }
            }
            return listaOrdenada;
        }
   
    public void inicializaTempoFaltantE(ArrayList<Tarefa> listaOrdenada, ArrayList<Integer> tempoFaltante){
        for(Tarefa tarefa : listaOrdenada){
            tempoFaltante.add(tarefa.getTempoComputacional());
        }
    }
    
    public ArrayList escalona(ArrayList<Tarefa> listaOrdenada){
        int tempoAtual = 0;
        int completos = 0;
        ArrayList<Integer> tempoCompletos = new ArrayList();
        ArrayList<Integer> tempoFaltante = new ArrayList(); 
        inicializaTempoFaltantE(listaOrdenada, tempoFaltante);
        
        while(completos < listaOrdenada.size()){
            for(int i=0; i<listaOrdenada.size(); i++){
                if(tempoFaltante.get(i) > 0){
                    int executa = Math.min(tempoFaltante.get(i), quantum);
                    tempoFaltante.add(i, (tempoFaltante.get(i) - executa));
                    tempoAtual = tempoAtual + executa;
                }
                
                if(tempoFaltante.get(i) == 0){
                    tempoCompletos.add(i, tempoAtual);
                    completos++;
                }
            }
        }
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
