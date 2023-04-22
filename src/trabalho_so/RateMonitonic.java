
package trabalho_so;

import java.util.ArrayList;

public class RateMonitonic {
    TarefaRobusta t1, t2, t3, t4;
    ArrayList<TarefaRobusta> listaExecutados = new ArrayList();
    ArrayList<TarefaRobusta> listatarefas = new ArrayList();
    int contTarefas = 0;
    int somaEsperas = 0;
    int somaExecucoes = 0;

    //INICIALIZA AS TAREFAS
    public RateMonitonic() {
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
    
    //DEIXA LISTA DE TAREFAS ORDENADA POR PERIODO(CRESCENTE)
    public void ordenaLista(){
        
        TarefaRobusta aux;
        for (int i=0; i<listatarefas.size(); i++) {
            for(int j=i+1; j<listatarefas.size(); j++){
                if(listatarefas.get(i).getPeriodo()> listatarefas.get(j).getPeriodo()){
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
        int tempoAux = 0;
        addLista();
        ordenaLista();
        
        TarefaRobusta aux = null;
        //LIMITA A EXECUCAO NO TEMPO DE 100 UNIDADES
        while(tempoAtual < 100){
            
            
            //VERIFICA SE CHEGOU ALGUMA TAREFA NOVA
            if(!listaExecutados.isEmpty()){
                for(int i=0; i<listaExecutados.size(); i++){
                    if(listaExecutados.get(i).getTempoChegada() == tempoAtual){
                        listatarefas.add(listaExecutados.get(i));
                        contTarefas++;
                        tempoAux = 0;
                    }
                }
            }    
            //CHAMA O ORDENA LISTA
            ordenaLista();
            
            //VERIFICA A PRIORIDADE LISTA
            for(int i=0; i<listatarefas.size(); i++){
                if(listatarefas.get(i).getTempoChegada() <= tempoAtual && (aux == null || listatarefas.get(i).getPeriodo() < aux.getPeriodo())){
                    aux = listatarefas.get(i);
                    listatarefas.remove(listatarefas.get(i));
                }
            }
            
            //EXECUTA UMA UNIDADE DE TEMPO    
            if(aux != null){
                
                //ATUALIZA O TEMPO DE ESPERA
                if(aux.getTempoComputacional() == aux.getExecucaoFaltante()){
                    
                    aux.setEspera(tempoAtual - aux.getTempoChegada());
                
                    //SOMA DAS ESPERAS
                    somaEsperas = somaEsperas + aux.getEspera();
                }
                
                
                
                System.out.println(aux.getNome() + " está sendo executada no instante " + tempoAtual);
                aux.setExecucaoFaltante(aux.getExecucaoFaltante() - 1);
                aux.setTempoChegada(aux.getTempoChegada() + 1);
                tempoAtual++;
                tempoAux++;
               
                
                
                //VERIFICA SE ACABOU A EXECUÇÃO DA TAREFA QUE ESTAVA EXECUTANDO
                if(aux.execucaoFaltante == 0){
                    System.out.println("\n \n \n \n"
                     + aux.getNome() + "Finalizou a execucao no instante " + String.valueOf(tempoAtual)
                      + "\n \n \n \n");
                    
                    
                    
                    
                    //ATUALIZA O TEMPO DE EXECUÇÃO
                    aux.setExecucao(tempoAux - aux.getEspera());
                    System.out.println(aux.getExecucao());
                    
                    //SOMA DAS EXECUCOES
                    somaExecucoes = somaExecucoes + aux.getExecucao();
                    
                    
                    //ATUALIZA O TEMPO DE ATRASO
                    aux.setAtraso(tempoAtual - (aux.getTempoComputacional() + aux.getTempoChegada()));
                    aux.setSomaAtrasos(aux.getSomaAtrasos() + (double)(aux.getAtraso()));
                    
                    //ATUALIZA AS FUNÇÕES PARA A EXECUÇÃO
                    aux.setTempoChegada(aux.getTempoChegada() + aux.getPeriodo() - aux.getTempoComputacional());
                    aux.setDeadline(aux.getDeadline() + aux.getPeriodo());
                    aux.setExecucaoFaltante(aux.getTempoComputacional());
                    
                    
                    //ADICIONA A LISTA DE EXECUTADOS;
                    listaExecutados.add(aux);
                    aux = null;
                    
                }
                
                
                
            }
            else{
                System.out.println("NÃO A TAREFAS A SEREM EXECUTADAS");
                System.exit(0);
            }
            
            
            //VERIFICA SE A TAREFA QUE ESTÁ EM EXECUÇÃO PERDEU DEADLINE
            if(aux != null && aux.getDeadline() < tempoAtual){
                System.out.println("A TAREFA " + aux.getNome() + " PERDEU DEADLINE NO INSTANTE " + tempoAtual);
                break;
            }
            
            boolean quebrarWhile = false;
            //VERIFICA SE ALGUMA TAREFA DA FILA PERDEU DEADLINE
            for(TarefaRobusta tarefa : listatarefas){
                if(tarefa.getDeadline() < tempoAtual){
                    System.out.println("A TAREFA " + tarefa.nome + " PERDEU DEADLINE NO INSTANTE " + tempoAtual);
                    quebrarWhile = true;
                }
            }
            if(quebrarWhile == true){
                break;
            }
    }
    }
    
    public void calculaExecucaoMedia(){
        double resultado = somaExecucoes / listaExecutados.size();
        
        System.out.println("Tempo de Execucao media: " + resultado);
    }
    
    public void calculaEsperaMedia(){
        double resultado = somaEsperas / (double)(contTarefas);
        
        System.out.println("Tempo de Espera media: " + resultado);
    }
    
    public void calculaAtrasos(){
        ArrayList<Double> resultados = new ArrayList();
        for(int i= 0; i<listatarefas.size(); i++){
          resultados.add(listatarefas.get(i).getSomaAtrasos() / listatarefas.get(i).getContadorAtrasos());
        }
       
        TarefaRobusta AtrasoMaior = null;
        double maior = -1;
        TarefaRobusta atrasoMenor = null;
        double menor = Integer.MAX_VALUE;
        for(int i=0; i<listatarefas.size(); i++){
                if(resultados.get(i) > maior){
                    maior = resultados.get(i);
                    AtrasoMaior = listatarefas.get(i);
                }
                
                if(resultados.get(i) < menor){
                    menor = resultados.get(i);
                    atrasoMenor= listatarefas.get(i);
                }
                
        }
        
        System.out.println("Tarefa com maior atraso: " + AtrasoMaior + " com atraso medio de " + maior);
        System.out.println("Tarefa com menor atraso: " + atrasoMenor + " com atraso medio de " + menor);
        
    }
}

    
    

