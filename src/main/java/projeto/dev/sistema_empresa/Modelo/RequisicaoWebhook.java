package projeto.dev.sistema_empresa.Modelo;

import java.math.BigDecimal;

public class RequisicaoWebhook {
    private Long id;
    private String nome;
    private String cnpj;
    private BigDecimal saldo;
    private BigDecimal taxaSistema;

   
    public RequisicaoWebhook(Long id, String nome, String cnpj, BigDecimal saldo, BigDecimal taxaSistema) {
        this.id = id;
        this.nome = nome;
        this.cnpj = cnpj;
        this.saldo = saldo;
        this.taxaSistema = taxaSistema;
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public BigDecimal getTaxaSistema() {
        return taxaSistema;
    }

    public void setTaxaSistema(BigDecimal taxaSistema) {
        this.taxaSistema = taxaSistema;
    }
}
