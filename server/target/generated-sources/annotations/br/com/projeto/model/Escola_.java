package br.com.projeto.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Escola.class)
public abstract class Escola_ {

	public static volatile SingularAttribute<Escola, String> nome;
	public static volatile SingularAttribute<Escola, Long> id;
	public static volatile SingularAttribute<Escola, Usuario> diretor;
	public static volatile SingularAttribute<Escola, String> descricao;
	public static volatile SingularAttribute<Escola, Boolean> status;

	public static final String NOME = "nome";
	public static final String ID = "id";
	public static final String DIRETOR = "diretor";
	public static final String DESCRICAO = "descricao";
	public static final String STATUS = "status";

}

