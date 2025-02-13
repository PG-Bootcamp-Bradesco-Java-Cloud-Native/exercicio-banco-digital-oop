package BancoConsole.models;

public abstract class Conta {
    protected int numero;
    protected int agencia;
    protected String nomeCliente;
    protected Double saldo = 0.0;

    public Conta(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public int getNumero() {
        return this.numero;
    }

    public int getAgencia() {
        return this.agencia;
    }

    public String getNomeCliente() {
        return this.nomeCliente;
    }

    public Double getSaldo() {
        return this.saldo;
    }

    public void realizarDeposito(Double valor) throws Exception {
        if (valor == null) {
            throw new Exception("Valor invalido. Por favor, tente novamente.");
        }
        if (valor <= 0) {
            throw new Exception("Valor invalido: Deve ser superior a 0.");
        }

        this.saldo += valor;
    }

    public abstract void realizarSaque(Double valor) throws Exception;

    @Override
    public String toString() {
        return new StringBuilder()
                .append("\nTipo da Conta: ").append(this.getClass().getSimpleName())
                .append("\nNúmero da Conta: ").append(numero)
                .append("\nNúmero da Agência: ").append(agencia)
                .append("\nProprietário: ").append(nomeCliente)
                .append("\nSaldo: ").append(saldo)
                .toString();
    };
}