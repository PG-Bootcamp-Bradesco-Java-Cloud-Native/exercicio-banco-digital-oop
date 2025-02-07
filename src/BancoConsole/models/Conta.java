package BancoConsole.models;

import java.math.BigDecimal;

public class Conta {
    private int numero;
    private String agencia;
    private String nomeCliente;
    private BigDecimal saldo = BigDecimal.ZERO;

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

    public BigDecimal getSaldo() {
        return this.saldo;
    }

    public void realizarDeposito(BigDecimal valor) {
        this.saldo = this.saldo.add(valor);
    }

    public void realizarSaque(BigDecimal valor) throws Exception {
        if (this.saldo.compareTo(valor) < 0) {
            throw new Exception("Valor excede saldo da conta.");
        }

        this.saldo = this.saldo.subtract(valor);
    }
}