package br.com.projeto.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tb_curso")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_curso")
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "sigla", nullable = false,length = 5)
    private String sigla;

    @ManyToOne
    @JoinColumn(name = "id_coordenador", nullable = false)
    private Usuario coordenador;

    @ManyToOne
    @JoinColumn(name = "id_escola",nullable = false)
    private Escola escola;

    @ManyToMany(targetEntity = Materia.class, fetch = FetchType.EAGER)
    @JoinTable(name = "tb_materia_curso",
            joinColumns = @JoinColumn(name = "id_curso"),
            inverseJoinColumns = @JoinColumn(name = "id_materia"))
    private Set<Materia> materias;

    @Column(name = "descricao", nullable = false, length = 2500)
    private String descricao;

    @Transient
    private List<Long> materiasIds;

    public Curso() {
    }

    public Curso(Long id, String nome, String sigla, Usuario coordenador, Escola escola, Set<Materia> materias, String descricao) {
        this.id = id;
        this.nome = nome;
        this.sigla = sigla;
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

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
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

    public List<Long> getMateriasIds() {
        return materiasIds;
    }

    public void setMateriasIds(List<Long> materiasIds) {
        this.materiasIds = materiasIds;
    }
}
