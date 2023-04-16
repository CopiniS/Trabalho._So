
package trabalho_so;

public class Tarefa {
    int tempoComputacional;
    int tempoDeIngresso;
    int espera;
    int atraso;
    int execucao;
    

    public Tarefa(int tempoComputacional, int tempoDeIngresso) {
        this.tempoComputacional = tempoComputacional;
        this.tempoDeIngresso = tempoDeIngresso;
        this.espera = 0;
        this.atraso = 0;
        this.execucao = 0;
        
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
    
    
    
    
    
    

    @Override
    public String toString() {
        return "Tarefa{" + "tempoComputacional=" + tempoComputacional + ", tempoDeIngresso=" + tempoDeIngresso + '}';
    }
    
    
    
}
