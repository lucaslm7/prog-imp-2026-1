import java.util.Random;
public class ExercicioRandom {
    public static final int MIN = 1;
    public static final int MAX = 30;
    public static final int TAM = 100;
    public static Random randon = new Random();

    public static void main (String[] args){
        int[] numeros = new int[TAM];
        preencherAleatorio(numeros);
        double media = calcularMedia(numeros);
        imprimi(numeros);        
        System.out.printf("A média dos números inteiros destes vetores são %.2f\n", media);
        maiorNumero(numeros);
        menorNumero(numeros);
    }

    //Função preenche vetores dentro das posições
    public static void preencherAleatorio(int[] v){
        for (int i = 0; i < v.length; i++){
            v[i] = randon.nextInt(MAX - MIN + 1) + MIN; 
        }
    }

    //Função calcula media
    public static double calcularMedia(int[] v){
        int soma = 0;
        for (int i = 0; i < v.length; i++){
             soma += v[i];
        }
        return (double) soma / (double) v.length; 
    } 

    //Função maior valor
    public static void maiorNumero(int[] v){
        int maiorValor = v[0];
        for (int i = 0; i < v.length; i++){
            if(v[i] > maiorValor){
                maiorValor = v[i];
            }
        }
        System.out.print("O maior valor sorteado foi: " + maiorValor + "\n");
    }

    //função menor valor
    public static void menorNumero(int[] v){
        int menorValor = v[0];
        for(int i = 0; i < v.length; i++){
            if(v[i] < menorValor){
                menorValor = v[i];
            }
        }
        System.out.print("O menor valor sorteado foi: " + menorValor);
    }

    //Função imprimi
    public static void imprimi(int[] v){
        System.out.print("{ ");
        if(v.length >= 1){
            System.out.print(v[0]);
            for(int i = 1; i < v.length; i++){
                System.out.print(", " + v[i]);
            }
        }
        System.out.println(" }");
    }
}