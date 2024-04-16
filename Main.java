// Bruno Antico Galin 10417318
// Ismael de Sousa e Silva 10410870
// Referência: https://www.youtube.com/watch?v=xk4_1vDrzzo
// Referência: https://www.youtube.com/watch?v=xZuA5hA2yF8
package apl1;
import java.util.Scanner;

public class Main {
  public static void menu() {
    Scanner scan = new Scanner(System.in);
    String infix = "";
    String posfix = "";
    avaliar inpos = new avaliar(10);
    int[] variaveis = new int[26];
    do {
      System.out.println("---Avaliador de expressões matemáticas---\n1. Entrada da expressão aritmética na notação infixa.\n2. Entrada dos valores numéricos associados às variáveis.\n3. Conversão da expressão, da notação infixa para a notação posfixa, e exibição da expressão convertida para posfixa.\n4. Avaliação da expressão (apresentação do resultado do cálculo, mostrando a expressão e os valores das variáveis).\n5. Encerramento do programa.");
      String opcao = scan.nextLine();
      if (opcao.equals("1")) {
        System.out.println("Digite a expressão na notação infixa: ");
        infix = scan.nextLine();
      }
      else if (opcao.equals("2")) {
        if (infix.isEmpty()) {
          System.out.println("Erro. Não há expressão na memória. Volte à Opção 1.");
        }
        else {
          System.out.println("Digite os valores númericos associados às variáveis da expressão digitada (separe-os por espaço): ");
          String vnum = scan.nextLine();
          String[] numspace = vnum.split(" ");
          try {
            for (int i = 0; i < numspace.length; i++) {
              variaveis[i] = Integer.parseInt(numspace[i]);
            }
          }
          catch (NumberFormatException e) {
            System.out.println("Erro: Caractere inválido. Tente novamente. ");
            infix = "";
          }
        }
      }
      else if (opcao.equals("3")) {
        if (infix.isEmpty()) {
          System.out.println("Erro: Não há expressão na memória. Volte à Opção 1.");
        }
        else {
          posfix = inpos.conversao(infix);
          System.out.println("Expressão convertida para posfixa: \n" + posfix);
        }
      }
        else if (opcao.equals("4")) {
            if (infix.isEmpty()) {
                System.out.println("Erro: Não há expressão na memória. Volte à Opção 1.");
            }
            else {
                double res = inpos.calcularposfix(infix, variaveis);
                System.out.println("Resultado do cálculo: " + res);
                System.out.println("Expressão: " + inpos.conversao(infix));
                System.out.println("Valores das variáveis utilizadas na expressão:");
                for (int i = 0; i < infix.length(); i++) {
                    char c = infix.charAt(i);
                    if (Character.isLetter(c)) {
                    	c = Character.toUpperCase(c);
                        int iVar = c - 'A';
                        if (iVar >= 0 && iVar < variaveis.length) {
                            System.out.println(c + " = " + variaveis[iVar]);
                        }
                    }
                }
            }
        }

      else if (opcao.equals("5")){
        System.out.println("Programa encerrado.");
        break; 
      }
      else{
        System.out.println("Opção inválida. Tente novamente.");
      }
    } while(true);
    scan.close();
  }

  public static void main(String[] args) {
    menu();
  }
}