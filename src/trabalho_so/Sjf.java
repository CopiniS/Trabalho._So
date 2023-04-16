
package trabalho_so;

import java.util.ArrayList;

public class Sjf {
    Tarefa t1, t2, t3, t4;
    
    public Sjf() {
        this.t1 = new Tarefa(3, 0);
        this.t2 = new Tarefa(5, 3);
        this.t3 = new Tarefa(5, 2);
        this.t4 = new Tarefa(3, 1);
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
        
        public double calculaEsperaMedia(ArrayList<Tarefa> listaOrdenada){
            int tempoAtual = 0;
            int somaEsperas = 0;
            for(Tarefa tarefa : listaOrdenada){
                tarefa.espera = tempoAtual - tarefa.tempoDeIngresso;
                tempoAtual = tempoAtual + tarefa.getTempoComputacional();
                somaEsperas = somaEsperas + tarefa.espera;
            }
            return (double) somaEsperas / (double) listaOrdenada.size();
        }
        
        public double calculaExecucaomedia(ArrayList<Tarefa> listaOrdenada){
            int somaexecucao = 0;
        
            for(Tarefa tarefa : listaOrdenada){
                somaexecucao = somaexecucao + (tarefa.espera + tarefa.tempoComputacional);
            }
            return (double) somaexecucao / (double) listaOrdenada.size();
        }
        
        
}
