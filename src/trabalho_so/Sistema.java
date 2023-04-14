
package trabalho_so;

import java.util.ArrayList;

public class Sistema {
    Tarefa t1, t2, t3, t4;
    int tempoAtual;
    ArrayList<Tarefa> listatarefas;
    int contadorEsperas;
    double somaEsperas;
    

    public Sistema() {
        t1 = new Tarefa(3, 0); 
        t2 = new Tarefa(5, 3);  
        t3 = new Tarefa(5, 2); 
        t4 = new Tarefa(3, 1); 
        listatarefas = new ArrayList();
    }
    
    public void listaAdd(){
        listatarefas.add(t1);
        listatarefas.add(t2);
        listatarefas.add(t3);
        listatarefas.add(t4);
    }
    
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
    
    public void escalonador(){
        while(tempoAtual < 100){
            for(Tarefa tarefa : listatarefas){
                int tempoEspera = tempoAtual - tarefa.tempoDeIngresso;
                tempoAtual = tempoAtual + tarefa.getTempoComputacional();
                tarefa.setTempoDeIngresso(tempoAtual);
                
                tarefa.setSomaAtraso(tarefa.getSomaAtraso() + (tempoEspera - tarefa.tempoComputacional));
                tarefa.setContadorAtraso(tarefa.getContadorAtraso()+ 1);
                
                somaEsperas = somaEsperas + tempoEspera;
                contadorEsperas++;
                
            }
        }
    }
    
    public ArrayList calculaTempoMedioAtraso(){
        ArrayList listaAtrasosOrdenada = new ArrayList();
        for(Tarefa tarefa : listatarefas){
            double valor = tarefa.getSomaAtraso() / tarefa.getContadorAtraso();
            listaAtrasosOrdenada.add(valor);
        }
        
        double aux;
        for (int i=0; i<listaAtrasosOrdenada.size(); i++) {
            for(int j=i+1; j<listaAtrasosOrdenada.size(); j++){
                if( (double)listaAtrasosOrdenada.get(i) > (double)listaAtrasosOrdenada.get(j)){
                    aux = (double) listaAtrasosOrdenada.get(i);
                    listaAtrasosOrdenada.set(i, listaAtrasosOrdenada.get(j));
                    listaAtrasosOrdenada.set(j, aux);
                }
            }
        }
        return listaAtrasosOrdenada;
    }
    
    public double calculaTempoEsperaMedio(){
       return somaEsperas / (double) contadorEsperas;
    }
    
    public double calculaTempoExecucaoMedio(){
        double somaexEcucao = 0;
        int cont = 0;
        
        for(Tarefa tarefa : listatarefas){
            somaexEcucao = somaexEcucao + tarefa.getTempoComputacional();
            cont++;
        }
        return somaexEcucao / (double)cont;
    }
   
    public void mostraTempoAtraso(ArrayList listaAtrasosOrdenada){
        for(int i=0; i<listaAtrasosOrdenada.size(); i++){
           System.out.println("Atraso Médio da Tarefa " + listatarefas.get(i).toString() + ": " +  listaAtrasosOrdenada.get(i).toString());
        }
    }
}
