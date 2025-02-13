package BancoConsole.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class Banco {
    private static List<Conta> contas = new ArrayList<Conta>();
    private static Conta contaAtiva = null;

    public static void registrarConta(Conta conta) {
        int numeroNewConta = 1000 + contas.size();
        int agencia = 1234;

        conta.agencia = agencia;
        conta.numero = numeroNewConta;

        contas.add(conta);
    }

    public static Conta getContaAtiva() {
        return contaAtiva;
    }

    public static void setContaAtiva(Conta conta) {
        contaAtiva = conta;
    }

    public static Optional<Conta> getContaByNumero(int numero) {
        return contas.stream().filter(c -> c.getNumero() == numero).findFirst();
    }
}
