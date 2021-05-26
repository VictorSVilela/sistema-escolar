package br.com.grupoitss.model;

import javax.persistence.*;


@Entity
@Table(name = "tb_escola")
public class Escola {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @OneToOne
    private Usuario diretor;

    private String descricao;
    private boolean status;

    public Escola() {
    }

    public Escola(Long id, String nome, Usuario diretor, String descricao, boolean status) {
        this.id = id;
        this.nome = nome;
        this.diretor = diretor;
        this.descricao = descricao;
        this.status = status;
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

    public Usuario getDiretor() {
        return diretor;
    }

    public void setDiretor(Usuario diretor) {
        this.diretor = diretor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
