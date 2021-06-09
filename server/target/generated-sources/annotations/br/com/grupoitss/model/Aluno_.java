package br.com.grupoitss.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Aluno.class)
public abstract class Aluno_ {

	public static volatile SingularAttribute<Aluno, Integer> idade;
	public static volatile SingularAttribute<Aluno, Long> sequencia;
	public static volatile SingularAttribute<Aluno, String> nome;
	public static volatile SingularAttribute<Aluno, Long> id;
	public static volatile SingularAttribute<Aluno, String> email;
	public static volatile SingularAttribute<Aluno, Date> dataDaMatricula;

	public static final String IDADE = "idade";
	public static final String SEQUENCIA = "sequencia";
	public static final String NOME = "nome";
	public static final String ID = "id";
	public static final String EMAIL = "email";
	public static final String DATA_DA_MATRICULA = "dataDaMatricula";

}

