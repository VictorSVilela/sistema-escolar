package br.com.grupoitss.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Turma.class)
public abstract class Turma_ {

	public static volatile SingularAttribute<Turma, Long> sequencia;
	public static volatile SingularAttribute<Turma, String> sigla;
	public static volatile SingularAttribute<Turma, Curso> curso;
	public static volatile SetAttribute<Turma, Aluno> alunos;
	public static volatile SingularAttribute<Turma, String> matricula;
	public static volatile SingularAttribute<Turma, String> nome;
	public static volatile SingularAttribute<Turma, Long> id;

	public static final String SEQUENCIA = "sequencia";
	public static final String SIGLA = "sigla";
	public static final String CURSO = "curso";
	public static final String ALUNOS = "alunos";
	public static final String MATRICULA = "matricula";
	public static final String NOME = "nome";
	public static final String ID = "id";

}

