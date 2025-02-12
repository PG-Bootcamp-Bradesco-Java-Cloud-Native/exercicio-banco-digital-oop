package BancoConsole.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class Banco {
    private static List<Conta> contas = new ArrayList<Conta>();
    private static Conta contaAtiva = null;

    public static Conta registrarConta(String agencia, String nomeCliente) {
        int numeroNewConta = 1000 + contas.size();
        Conta newConta = new Conta(numeroNewConta, agencia, nomeCliente);
        contas.add(newConta);
        return newConta;
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
