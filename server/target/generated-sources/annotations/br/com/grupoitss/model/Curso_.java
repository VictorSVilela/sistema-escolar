package br.com.grupoitss.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Curso.class)
public abstract class Curso_ {

	public static volatile SingularAttribute<Curso, String> sigla;
	public static volatile SingularAttribute<Curso, Escola> escola;
	public static volatile SingularAttribute<Curso, String> nome;
	public static volatile SingularAttribute<Curso, Long> id;
	public static volatile SingularAttribute<Curso, Usuario> coordenador;
	public static volatile SetAttribute<Curso, Materia> materias;
	public static volatile SingularAttribute<Curso, String> descricao;

	public static final String SIGLA = "sigla";
	public static final String ESCOLA = "escola";
	public static final String NOME = "nome";
	public static final String ID = "id";
	public static final String COORDENADOR = "coordenador";
	public static final String MATERIAS = "materias";
	public static final String DESCRICAO = "descricao";

}

