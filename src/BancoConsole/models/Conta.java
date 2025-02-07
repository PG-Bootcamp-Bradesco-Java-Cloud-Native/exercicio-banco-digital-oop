package BancoConsole.models;

public class Conta {
    private int numero;
    private String agencia;
    private String nomeCliente;
    private int saldo;

    public Conta(int numero, String agencia, String nomeCliente) {
        this.numero = numero;
        this.agencia = agencia;
        this.nomeCliente = nomeCliente;
    }

    public int getNumero() {
        return this.numero;
    }

    public String getAgencia() {
        return this.agencia;
    }

    public String getNomeCliente() {
        return this.nomeCliente;
    }

    public int getSaldo() {
        return this.saldo;
    }

    public void realizarDeposito(Integer valor) throws Exception {
        if (valor == null) {
            throw new Exception("Valor invalido. Por favor, tente novamente.");
        }
        if (valor <= 0) {
            throw new Exception("Valor invalido: Deve ser superior a 0.");
        }

        this.saldo += valor;
    }

    public void realizarSaque(Integer valor) throws Exception {
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