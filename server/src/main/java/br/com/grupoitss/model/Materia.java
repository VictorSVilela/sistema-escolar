package br.com.grupoitss.model;

import javax.persistence.*;

@Entity
@Table(name = "tb_materia")
public class Materia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @OneToOne
    private Usuario professor;

    private String descricao;

    public Materia() {
    }

    public Materia(Long id, String nome, Usuario professor, String descricao) {
        this.id = id;
        this.nome = nome;
        this.professor = professor;
        this.descricao = descricao;
    }

    public Materia(Long id) {
        this.id = id;
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

    public Usuario getProfessor() {
        return professor;
    }

    public void setProfessor(Usuario professor) {
        this.professor = professor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}