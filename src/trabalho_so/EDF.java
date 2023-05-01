
package trabalho_so;

import java.util.ArrayList;

public class EDF {
     TarefaRobusta t1, t2, t3, t4;
    ArrayList<TarefaRobusta> listaExecutados = new ArrayList();
    ArrayList<TarefaRobusta> listatarefas = new ArrayList();
    double somaEsperas = 0;
    int contTarefas = 0;

    //CRIA AS TAREFAS
    public EDF() {
        this.t1 = new TarefaRobusta("t1", 6, 3, 0);
        this.t2 = new TarefaRobusta("t2", 10, 5, 0);
        this.t3 = new TarefaRobusta("t3", 10, 5, 0);
        this.t4 = new TarefaRobusta("t4", 6, 3, 0);
        
    }
    
    public void addLista(){
        listatarefas.add(t1);
        listatarefas.add(t2);
        listatarefas.add(t3);
        listatarefas.add(t4);
        
    }
    
    //DEIXA LISTA DE TAREFAS ORDENADA POR DEADLINES(CRESCENTE)
    public void ordenaLista(){
        
        TarefaRobusta aux;
        for (int i=0; i<listatarefas.size(); i++) {
            for(int j=i+1; j<listatarefas.size(); j++){
                if(listatarefas.get(i).getDeadlineFaltante()> listatarefas.get(j).getDeadlineFaltante()){
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
        addLista();
        ordenaLista();
        
        TarefaRobusta aux = null;
        //LIMITA A EXECUCAO NO TEMPO DE 100 UNIDADES
        while(tempoAtual < 100){
            
            
            //VERIFICA SE CHEGOU ALGUMA TAREFA NOVA
            if(!listaExecutados.isEmpty()){
                for(int i=0; i<listaExecutados.size(); i++){
                    if(listaExecutados.get(i).getTempoChegada() <= tempoAtual){
                        listaExecutados.remove(listaExecutados.get(i));
                        
                    }
                }
               
            }    
            
            //CHAMA O ORDENA LISTA
            ordenaLista();
            
            //VERIFICA A PRIORIDADE LISTA
            for(TarefaRobusta tarefa : listatarefas){
                if(tarefa.getTempoChegada() <= tempoAtual && (aux == null || tarefa.getDeadlineFaltante()< aux.getDeadlineFaltante())
                        && !listaExecutados.contains(tarefa)){
                    aux = tarefa;
                }
                
            }
            
            //EXECUTA UMA UNIDADE DE TEMPO    
            if(aux != null){
                
                //ATUALIZA O TEMPO DE ESPERA
                if(aux.getTempoComputacional() == aux.getExecucaoFaltante()){
                    
                    aux.setEspera(tempoAtual - aux.getTempoChegada());
                
                    //SOMA DAS ESPERAS
                    somaEsperas = somaEsperas + aux.getEspera();
                    
                    contTarefas++;
                }
                
                System.out.println(aux.getNome() + " está sendo executada no instante " + tempoAtual);
                
                aux.setExecucaoFaltante(aux.getExecucaoFaltante() - 1);
                tempoAtual = tempoAtual + 1;
                
                //ATUALIZA OS DEADLINES A CADA EXECUCAO
                for(TarefaRobusta tarefa : listatarefas){
                    tarefa.setDeadlineFaltante(tarefa.getDeadlineFaltante() - 1);
                }
                
                
               
                
                //ADICIONA A LISTA DE EXECUTADOS
                //ATUALIZA O TEMPO DE CHEGADA E O DEADLINE DO PRÓXIMO PERÍODO
                if(aux.execucaoFaltante == 0){
                    System.out.println("\n \n \n \n"
                     + aux.getNome() + "Finalizou a execucao no instante " + String.valueOf(tempoAtual)
                      + "\n \n \n \n");
                    
                    aux.setTempoChegada(aux.getTempoChegada() + aux.getPeriodo());
                    listaExecutados.add(aux);
                    aux.setDeadline(aux.getDeadline() + aux.getPeriodo());
                    aux.setExecucaoFaltante(aux.getTempoComputacional());
                    aux.setDeadlineFaltante(aux.getDeadline() - tempoAtual);
                    aux = null;
                    
                }
            }
            else{
                System.out.println("NÃO A TAREFAS A SEREM EXECUTADAS NO TEMPO " + tempoAtual);
                tempoAtual++;
            }
            
            
            
            
            boolean quebrarWhile = false;
            //VERIFICA SE ALGUMA TAREFA QUE ESTÁ NA FILA PERDEU DEADLINE
            for(int i=0; i<listatarefas.size();i++){
                if(!listatarefas.isEmpty() && listatarefas.get(i).getDeadlineFaltante() < 0){
                    System.out.println("A TAREFA " + listatarefas.get(i).getNome() + " PERDEU DEADLINE NO INSTANTE " + tempoAtual);
                    quebrarWhile = true;
                }
            }
            if(quebrarWhile == true){
                break;
            }
    }
    }
    
    public void calculaEsperaMerdia(){
        double resultado = somaEsperas / (double)(contTarefas);
        
        System.out.println("Tempo de Espera media: " + resultado);
    }
    
    public void calculaExecucaoMedia(){
    double resultado;
    double somaExecucoes = 0;
        
        for(TarefaRobusta tarefa : listatarefas){
            somaExecucoes = somaExecucoes + tarefa.getTempoComputacional();
        }
        resultado = somaExecucoes / listatarefas.size();
                
        System.out.println("Tempo de Execucao media: " + resultado);
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
