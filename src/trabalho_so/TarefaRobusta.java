
package trabalho_so;

public class TarefaRobusta {
    int periodo;
    int deadline;
    int tempoComputacional;
    int espera;
    int atraso;
    int execucao;
    int tempoChegada;
    int execucaoFaltante;
    

    public TarefaRobusta(int periodo, int tempoComputacional, int tempoChegada) {
        this.periodo = periodo;
        this.deadline = periodo;
        this.tempoComputacional = tempoComputacional;
        this.tempoChegada = tempoChegada;
        this.espera = 0;
        this.atraso = 0;
        this.execucao = 0;
        this.execucaoFaltante = tempoComputacional;
        
    }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int period0) {
        this.periodo = period0;
    }

    public int getDeadline() {
        return deadline;
    }

    public void setDeadline(int deadline) {
        this.deadline = deadline;
    }

    public int getTempoComputacional() {
        return tempoComputacional;
    }

    public void setTempoComputacional(int tempoComputacional) {
        this.tempoComputacional = tempoComputacional;
    }

    public int getEspera() {
        return espera;
    }

    public void setEspera(int espera) {
        this.espera = espera;
    }

    public int getAtraso() {
        return atraso;
    }

    public void setAtraso(int atraso) {
        this.atraso = atraso;
    }

    public int getExecucao() {
        return execucao;
    }

    public void setExecucao(int execucao) {
        this.execucao = execucao;
    }

    public int getTempoChegada() {
        return tempoChegada;
    }

    public void setTempoChegada(int tempoChegada) {
        this.tempoChegada = tempoChegada;
    }

    public int getExecucaoFaltante() {
        return execucaoFaltante;
    }

    public void setExecucaoFaltante(int execucaoFaltante) {
        this.execucaoFaltante = execucaoFaltante;
    }
    
    
    
    
}
