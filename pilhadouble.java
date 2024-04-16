// Bruno Antico Galin 10417318
// Ismael de Sousa e Silva 10410870
// Referência: https://www.youtube.com/watch?v=xk4_1vDrzzo
// Referência: https://www.youtube.com/watch?v=xZuA5hA2yF8
package apl1;

public class pilhadouble {
    private double[] pilha;
    private int cont;
    private int len;

    public pilhadouble(int len) {
        this.len = len;
        cont = 0;
        pilha = new double[len];
    }

    public void push(double valor) {
        if (isFull()) {
            System.out.println("Pilha cheia.");
        } else {
            pilha[cont++] = valor;
        }
    }

    public double pop() {
        return pilha[--cont];
    }

    public double top() {
        return pilha[cont - 1];
    }

    public int size() {
        return cont;
    }

    public int count() {
        return pilha.length;
    }

    public boolean isFull() {
        return cont == pilha.length;
    }

    public boolean isEmpty() {
        return cont == 0;
    }

    public void clear() {
        cont = 0;
    }
}