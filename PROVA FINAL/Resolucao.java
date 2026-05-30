

//questão 1 
static int cadastrarConta(Conta[] v, int tam) {
        if (tam >= TAM_CONTAS) {
            System.out.println("Erro: Vetor de contas cheio.");
            return tam;
        }

        Scanner sc = new Scanner(System.in);
        Conta novaConta = new Conta();

        System.out.print("Digite o ID da conta: ");
        novaConta.id = sc.nextInt();

        if (buscaId(v, tam, novaConta.id) != -1) {
            System.out.println("Erro: Já existe uma conta com este ID.");
            return tam;
        }

        System.out.print("Digite o limite da conta: ");
        novaConta.limite = sc.nextDouble();
        if (novaConta.limite < 0) {
            System.out.println("Erro: Limite não pode ser negativo.");
            return tam;
        }

        System.out.print("Digite o saldo inicial: ");
        novaConta.saldo = sc.nextDouble();
        if (novaConta.saldo < 0) {
            System.out.println("Erro: Saldo não pode ser negativo.");
            return tam;
        }

        sc.nextLine(); // Limpar o buffer
        System.out.print("Digite o nome do cliente: ");
        novaConta.cliente = sc.nextLine();

        v[tam] = novaConta;
        System.out.println("Conta cadastrada com sucesso!");
        
        return tam + 1;
    }

//questão 2
public static int buscaBinariaCliente(Conta[] v, int tam, String x) {
        int inicio = 0;
        int fim = tam - 1;

        while (inicio <= fim) {
            int meio = (inicio + fim) / 2;
            int comparacao = v[meio].cliente.compareToIgnoreCase(x);

            if (comparacao == 0) {
                return meio; // Encontrou
            } else if (comparacao < 0) {
                inicio = meio + 1; // Está na metade direita
            } else {
                fim = meio - 1; // Está na metade esquerda
            }
        }
        return -1; // Não encontrou
    }

//questão 3
public static int insertionSortCliente(Conta[] v, int tam) {
        for (int i = 1; i < tam; i++) {
            Conta chave = v[i];
            int j = i - 1;

            while (j >= 0 && v[j].cliente.compareToIgnoreCase(chave.cliente) > 0) {
                v[j + 1] = v[j];
                j = j - 1;
            }
            v[j + 1] = chave;
        }
        return tam;
    }

//questão 4
public static int filtraOperacoes(Operacao[] vOp, int tamOp, Operacao[] vOpFiltrado, int x) {
        int cont = 0;
        for (int i = 0; i < tamOp; i++) {
            if (vOp[i].idConta == x) {
                vOpFiltrado[cont] = vOp[i];
                cont++;
            }
        }
        return cont;
    }

//questão 5
public static void extrato(Conta[] vContas, int tamConta, Operacao[] vOp, int tamOp) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite o ID da conta para emitir o extrato: ");
        int idDesejado = sc.nextInt();

        int posConta = buscaId(vContas, tamConta, idDesejado);
        if (posConta == -1) {
            System.out.println("Conta não encontrada.");
            return;
        }
        
        Conta conta = vContas[posConta];
        Operacao[] opFiltradas = new Operacao[TAM_OP];
        int tamFiltradas = filtraOperacoes(vOp, tamOp, opFiltradas, idDesejado);

        // Define o limite das 5 últimas operações
        int qtdMostrar = Math.min(5, tamFiltradas);
        int inicioExibicao = tamFiltradas - qtdMostrar;

        // Recalcular saldo partindo de zero até a operação anterior à exibição
        double saldoAnterior = 0.0;
        for (int i = 0; i < inicioExibicao; i++) {
            if (opFiltradas[i].tipo == 'D') {
                saldoAnterior += opFiltradas[i].valor;
            } else {
                saldoAnterior -= opFiltradas[i].valor;
            }
        }

        // Cabeçalho
        System.out.println("+--------------------------------------------------------+");
        System.out.printf("|               C O N T A                 N.%03d|\n", conta.id);
        System.out.println("+--------------------------------------------------------+");
        System.out.printf("|CLIENTE: %-47s|\n", conta.cliente);
        System.out.println("+--------------------------------------------------------+");
        System.out.println("| ID | O P E R A Ç Ã O | VALOR     | SALDO   |");
        System.out.println("+----+-----------------+-----------+---------+");

        char sinalSaldoAnterior = saldoAnterior >= 0 ? 'C' : 'D';
        System.out.printf("|    | Saldo Anterior  |           | %7.2f%c|\n", Math.abs(saldoAnterior), sinalSaldoAnterior);

        double saldoAtual = saldoAnterior;

        // Imprime apenas as até 5 últimas
        for (int i = inicioExibicao; i < tamFiltradas; i++) {
            Operacao op = opFiltradas[i];
            String descOp = "";
            
            if (op.tipo == 'D') {
                descOp = "Depósito";
                saldoAtual += op.valor;
            } else if (op.tipo == 'S') {
                descOp = "Saque";
                saldoAtual -= op.valor;
            } else if (op.tipo == 'T') {
                descOp = "Transferência";
                saldoAtual -= op.valor;
            }

            char sinalValor = op.tipo == 'D' ? 'C' : 'D';
            char sinalSaldo = saldoAtual >= 0 ? 'C' : 'D';

            System.out.printf("|%04d|%-17s|%8.2f%c |%8.2f%c|\n", 
                op.id, descOp, Math.abs(op.valor), sinalValor, Math.abs(saldoAtual), sinalSaldo);
        }

        System.out.println("+----+-----------------+-----------+---------+");
        char sinalFinal = saldoAtual >= 0 ? 'C' : 'D';
        System.out.printf("       Saldo Atual                 | %7.2f%c|\n", Math.abs(saldoAtual), sinalFinal);
        System.out.println("                                   +---------+");
    }




//Resolução feita pelo google gemini.