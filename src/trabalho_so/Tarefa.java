
package trabalho_so;

public class Tarefa implements Comparable<Tarefa>{
    int tempoComputacional;
    int tempoDeIngresso;
    int espera;
    int atraso;
    int execucao;
    int tempofaltante;
    String nome;
    

    public Tarefa(String nome, int tempoComputacional, int tempoDeIngresso) {
        this.tempoComputacional = tempoComputacional;
        this.tempoDeIngresso = tempoDeIngresso;
        this.espera = 0;
        this.atraso = 0;
        this.execucao = 0;
        this.nome = nome;
        this.tempofaltante = tempoComputacional;
        
        
    }

    public int getTempoComputacional() {
        return tempoComputacional;
        
    }

    public void setTempoComputacional(int tempoComputacional) {
        this.tempoComputacional = tempoComputacional;
    }

    public int getTempoDeIngresso() {
        return tempoDeIngresso;
    }

    public void setTempoDeIngresso(int tempoDeIngresso) {
        this.tempoDeIngresso = tempoDeIngresso;
    }

    public double getAtraso() {
        return atraso;
    }

    public void setAtraso(int atraso) {
        this.atraso = atraso;
    }

    public int getEspera() {
        return espera;
    }

    public void setEspera(int espera) {
        this.espera = espera;
    }

    public int getExecucao() {
        return execucao;
    }

    public void setExecucao(int execucao) {
        this.execucao = execucao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getTempofaltante() {
        return tempofaltante;
    }

    public void setTempofaltante(int tempofaltante) {
        this.tempofaltante = tempofaltante;
    }
    
    
    
    
    
    

    @Override
    public String toString() {
        return "Tarefa{" + "tempoComputacional=" + tempoComputacional + ", tempoDeIngresso=" + tempoDeIngresso + '}';
    }

   @Override
    public int compareTo(Tarefa outraTarefa) {
        if(this.tempoDeIngresso < outraTarefa.tempoDeIngresso) {
            return -1;
        } else if(this.tempoDeIngresso > outraTarefa.tempoDeIngresso) {
            return 1;
        } else {
            return 0;
        }
    }
    
    
    
}
