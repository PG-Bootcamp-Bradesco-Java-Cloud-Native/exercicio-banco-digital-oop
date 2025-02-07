package BancoConsole.models;

import java.util.ArrayList;
import java.util.Optional;

public final class Banco {
    private ArrayList<Conta> contas = new ArrayList<Conta>();

    public Banco() {
    }

    public Conta registrarConta(String agencia, String nomeCliente) {
        int numeroNewConta = 1000 + contas.size();
        Conta newConta = new Conta(numeroNewConta, agencia, nomeCliente);
        contas.add(newConta);
        return newConta;
    }

    public Optional<Conta> getContaByNumero(int numero) {
        return contas.stream().filter(c -> c.getNumero() == numero).findFirst();
    }
}
