import java.util.Scanner;

public class VetorSemRepeticao {
    public static final int TAM_VETOR = 10;
    public static final int POS_INICIAL = 0;
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args){
        int[] numeros = new int[TAM_VETOR];
        preencherVetor(numeros);
        imprimi(numeros);
    }

    //Preencher o vetor
    public static void preencherVetor(int[] v){
        int i = POS_INICIAL;
        while(i < v.length){
            System.out.print("Digite o valor para a posição " + i + ": ");
            int valorDigitado = scanner.nextInt(); 

            boolean existe = false;
            for(int j = 0; j < i; j++){
                if(v[j] == valorDigitado){
                    existe = true;
                    break;
                }
            }
            
            if(!existe){
                v[i] = valorDigitado;
                i++;
            }else{
                System.out.println("O número já foi digitado, informe novamente.");
            }

        }
    }

    public static void imprimi(int[] v){
        for (int j = 0; j < v.length; j++) {
            System.out.print(" " + v[j]);
        }
    }
}