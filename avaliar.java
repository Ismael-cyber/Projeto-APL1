// Bruno Antico Galin 10417318
// Ismael de Sousa e Silva 10410870
// Referência: https://www.youtube.com/watch?v=xk4_1vDrzzo
// Referência: https://www.youtube.com/watch?v=xZuA5hA2yF8
package apl1;

public class avaliar {
  private char[] pilha;
  private int cont;
  private int len;

  public avaliar(int len) {
    this.len = len;
    cont = 0;
    pilha = new char[len];
  }

  public void push(char c) {
    if (isFull()) {
      System.out.println("Pilha cheia.");
    }
    else {
      pilha[cont++] = c;
    }
   }

  public char pop() {
    --cont;
    char topo = pilha[cont];
    pilha[cont] = 0;
    return topo;
  }

  public char top(){
    if (isEmpty()) {
            throw new IllegalStateException("Pilha vazia");
        }
    return pilha[cont-1];
  }

  public int size(){
    return cont;
  }

  public int count(){
    return pilha.length;
  }

  public boolean isFull(){
    return cont == pilha.length;
  }

  public boolean isEmpty(){
    return cont == 0;
  }

  public void clear(){
    cont = 0;
  }

  public static boolean isOperator(char op) {
    if (op == '+' || op == '-' || op == '*' || op == '/' || op == '^') {
      return true;
    }
    return false;
  }

  public int precedence(char op) {
    if (op == '+' || op == '-') {
      return 1;
    }
    if (op == '*' || op == '/') {
      return 2;
    }
    if (op == '^') {
      return 3;
    }
    return 0;
  }

  public double calcular(String expressao, int[] variaveis) {
      pilhadouble valores = new pilhadouble(expressao.length());

      for (int i = 0; i < expressao.length(); i++) {
          char c = expressao.charAt(i);
        
          if (isOperator(c)) {
              double n2 = valores.pop();
              double n1 = valores.pop();
              double res = doOperation(n1, n2, c);
              valores.push(res);
          } 
            
          else if (Character.isLetter(c)) {
              int iVar = Character.toUpperCase(c) - 'A';
              if (iVar >= 0 && iVar < variaveis.length) {
                  valores.push((double) variaveis[iVar]);
              } 
              else {
                  throw new IllegalArgumentException("Variável fora do intervalo: " + c);
              }
          } 
          else if (Character.isDigit(c)) {
              StringBuilder numBuilder = new StringBuilder();
              while (i < expressao.length() && (Character.isDigit(expressao.charAt(i)) || expressao.charAt(i) == '.')) {
                  numBuilder.append(expressao.charAt(i));
                  i++;
              }
              i--;
              valores.push(Double.parseDouble(numBuilder.toString()));
          }
      }
      return valores.pop();
  }

  public double calcularposfix(String expressao, int[] variaveis) {
        String posfix = conversao(expressao);
        return calcular(posfix, variaveis);
    }

    public double doOperation(double n1, double n2, char op) {
            if (op == '+'){
                return n1 + n2;
            }
            if (op == '-'){
                return n1 - n2;
            }
            if (op == '*'){
                return n1 * n2;
            }
            if (op == '/'){
                try{
                  return n1 / n2;
                }
                catch(ArithmeticException e){
                  System.out.println("Erro: Divisão por zero.");
              }
            }
            if (op == '^'){
                return Math.pow(n1, n2);
            }
            else{
                throw new IllegalArgumentException("Operador inválido: " + op);
            }
    }

  private boolean isLetter(char c) {
    return (c >= 'A' && c <= 'Z' || c >= 'a' && c <= 'z' );
  }

  public String conversao(String expressao) {
    StringBuilder posfix = new StringBuilder();
    char c = ' ';

    for(int i = 0; i < expressao.length(); i++) {
      c = expressao.charAt(i);
      if(isOperator(c)) {
        while (!isEmpty() && precedence(top()) >= precedence(c)){
          posfix.append(pop());
        }
        push(c);
      }
      else if(isLetter(c)) {
        posfix.append(c);
      }
      else if(c == '(') {
        push(c);
      }
      else if(c == ')') {
        while (!isEmpty() && top() != '(') {
          posfix.append(pop());
        }
        pop();
      }
    }
    while (!isEmpty()) {
      posfix.append(pop());
    }

    return posfix.toString();
  }
}