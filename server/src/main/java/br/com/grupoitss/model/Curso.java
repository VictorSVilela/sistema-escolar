package br.com.grupoitss.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "tb_curso")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String matricula;

    @OneToOne
    private Usuario coordenador;

    @ManyToOne
    private Escola escola;

    @OneToMany
    private Set<Materia> materias;

    private String descricao;

    public Curso() {
    }

    public Curso(Long id, String nome, String matricula, Usuario coordenador, Escola escola, Set<Materia> materias, String descricao) {
        this.id = id;
        this.nome = nome;
        this.matricula = matricula;
        this.coordenador = coordenador;
        this.escola = escola;
        this.materias = materias;
        this.descricao = descricao;
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

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Usuario getCoordenador() {
        return coordenador;
    }

    public void setCoordenador(Usuario coordenador) {
        this.coordenador = coordenador;
    }

    public Escola getEscola() {
        return escola;
    }

    public void setEscola(Escola escola) {
        this.escola = escola;
    }

    public Set<Materia> getMaterias() {
        return materias;
    }

    public void setMaterias(Set<Materia> materias) {
        this.materias = materias;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
