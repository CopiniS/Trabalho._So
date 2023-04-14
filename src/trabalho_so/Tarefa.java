
package trabalho_so;

public class Tarefa {
    int tempoComputacional;
    int tempoDeIngresso;
    double somaAtraso;
    int contadorAtraso;
    

    public Tarefa(int tempoComputacional, int tempoDeIngresso) {
        this.tempoComputacional = tempoComputacional;
        this.tempoDeIngresso = tempoDeIngresso;
        this.somaAtraso = 0;
        this.contadorAtraso = 0;
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

    public double getSomaAtraso() {
        return somaAtraso;
    }

    public void setSomaAtraso(double atraso) {
        this.somaAtraso = atraso;
    }

    public int getContadorAtraso() {
        return contadorAtraso;
    }

    public void setContadorAtraso(int contador) {
        this.contadorAtraso = contador;
    }
    
    
    
    

    @Override
    public String toString() {
        return "Tarefa{" + "tempoComputacional=" + tempoComputacional + ", tempoDeIngresso=" + tempoDeIngresso + '}';
    }
    
    
    
}
