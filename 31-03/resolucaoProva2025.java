
public class resolucaoProva2025 {
    
    public static void main(String[] args) {

        int[] vetorA = {10, 20, 30, 40};
        int[] vetorB = {30, 40, 50, 60, 10};
        int[] vetorU = new int[vetorA.length + vetorB.length];

        int tamanhoU = uniao(vetorA, vetorA.length, vetorB, vetorB.length, vetorU);
        System.out.print("Vetor União: ");
        imprimirVetor(vetorU, tamanhoU);

        ordenar(vetorU, tamanhoU);
        System.out.print("Vetor União Ordenado: ");
        imprimirVetor(vetorU, tamanhoU);

        int[] vetorV = {5, 2, 5, 3, 3, 8, 3, 8, 2};
        int[] vetorVSR = new int[vetorV.length];

        int tamanhoVSR = gerarVetorSemRepeticao(vetorV, vetorV.length, vetorVSR);
        imprimirVetor(vetorVSR, tamanhoVSR);
    }

    public static void imprimirVetor(int[] vetor, int tamanho) {
        System.out.print("{ ");
        for (int i = 0; i < tamanho; i++) {
            System.out.print(vetor[i]);
            if (i < tamanho - 1) {
                System.out.print(", ");
            }
        }
        System.out.println(" }");
    }

    public static int uniao(int[] a, int tamA, int[] b, int tamB, int[] u) {
    int tamU = 0; 
    for (int i = 0; i < tamA; i++) {
        boolean repetido = false; 
        for (int j = 0; j < tamU; j++) { 
            if (a[i] == u[j]) {
                repetido = true;
                break; 
            }
        }
        if (repetido == false) { 
            u[tamU] = a[i];
            tamU++;
        }
    }
    for (int i = 0; i < tamB; i++) {
        boolean repetido = false; 
        for (int j = 0; j < tamU; j++) { 
            if (b[i] == u[j]) {
                repetido = true; 
                break;
            }
        }
        if (repetido == false) {
            u[tamU] = b[i];
            tamU++;
        }
    }
    return tamU;
}

public static void ordenar(int[] v, int n) {
        for (int i = 1; i < n; i++) {
            int alvo = v[i];
            int j = i - 1;

            while (j >= 0 && v[j] > alvo) {
                v[j + 1] = v[j];
                j--;
            }
            v[j + 1] = alvo;
        }
    }

    public static int gerarVetorSemRepeticao(int[] v, int tamV, int[] vsr) {
    int tamVSR = 0;
    for (int i = 0; i < tamV; i++) {
        boolean repetido = false;
        for (int j = 0; j < tamVSR; j++) {
            if (v[i] == vsr[j]) {
                repetido = true;
                break; 
            }
        }
        if (repetido == false) {
            vsr[tamVSR] = v[i]; 
            tamVSR++;           
        }
    }
    return tamVSR;
    }
}