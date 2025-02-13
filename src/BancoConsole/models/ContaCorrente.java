package BancoConsole.models;

public class ContaCorrente extends Conta {
    protected double limite;

    public ContaCorrente(String nomeCliente) {
        super(nomeCliente);
        this.limite = 500;
    }

    @Override
    public void realizarSaque(Double valor) throws Exception {
        if (valor == null) {
            throw new Exception("Valor invalido. Por favor, tente novamente.");
        }
        if (valor <= 0) {
            throw new Exception("Valor invalido: Deve ser superior a 0.");
        }
        if (this.saldo + limite < valor) {
            throw new Exception("Valor invalido: Excede limite da conta.");
        }

        this.saldo -= valor;
    }

    @Override
    public String toString() {
        return super.toString() + new StringBuilder("\nLimite: ").append(limite)
                .toString();
    }

}
