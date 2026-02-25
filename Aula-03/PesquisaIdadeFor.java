import java.util.Scanner;
public class PesquisaIdadeFor{
    public static Scanner input = new Scanner(System.in);
    public static void main(String[] args){
        int idade = 0, somaIdades = 0, qtdPessoas, qtdPessoasMaiorTrinta = 0;
        double media;

        System.out.println("Digite a quantidade de participantes da pesquisa: ");
        qtdPessoas = input.nextInt();

        for (int i = 1; i <= qtdPessoas; i++){
            System.out.println("Digite a idade do " + i + "º participante: ");
            idade = input.nextInt();
            somaIdades += idade;
            if(idade > 30){
                qtdPessoasMaiorTrinta += 1;
            }
        }

        if(qtdPessoas >= 1){
            media = calcularMedia (qtdPessoas, somaIdades);
            System.out.println (qtdPessoas + " pessoas participaram da pesquisa.");
            System.out.println ("A idade média dos participantes foi de " + media + " anos.");
            System.out.println (qtdPessoasMaiorTrinta + " pessoas acima de 30 anos participaram da pesquisa.");
        }
        else {
            System.out.println("Nenhuma pessoa participou da pesquisa.");
        }
    }

    public static double calcularMedia(int qdt, int somatorio){
            return somatorio / qdt;
        }
}