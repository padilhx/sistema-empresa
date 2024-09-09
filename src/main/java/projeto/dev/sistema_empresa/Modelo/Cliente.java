package projeto.dev.sistema_empresa.Modelo;

public class Cliente {
    private Long id;
    private String cpf;
    private String nome; // Adicione o campo 'nome'

    // Construtores
    public Cliente() {
    }

    public Cliente(Long id, String cpf, String nome) {
        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
    }

    // Getters e setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() { // Adicione o método getNome
        return nome;
    }

    public void setNome(String nome) { // Adicione o método setNome
        this.nome = nome;
    }
}
