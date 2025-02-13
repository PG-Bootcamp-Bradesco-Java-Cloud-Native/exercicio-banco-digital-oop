package BancoConsole.models;

public class ContaPoupanca extends Conta {

    public ContaPoupanca(String nomeCliente) {
        super(nomeCliente);
    }

    @Override
    public void realizarSaque(Double valor) throws Exception {
        if (valor == null) {
            throw new Exception("Valor invalido. Por favor, tente novamente.");
        }
        if (valor <= 0) {
            throw new Exception("Valor invalido: Deve ser superior a 0.");
        }
        if (this.saldo < valor) {
            throw new Exception("Valor invalido: Excede saldo da conta.");
        }

        this.saldo -= valor;
    }
}
