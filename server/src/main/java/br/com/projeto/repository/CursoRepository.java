package br.com.projeto.repository;

import br.com.projeto.config.HibernateConfig;
import br.com.projeto.model.Curso;
import br.com.projeto.model.Escola;
import br.com.projeto.model.Usuario;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Projections;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CursoRepository extends BaseRepository<Curso> {

    public CursoRepository() {
        super(Curso.class);
    }

    @SuppressWarnings("unchecked")
    public List<Curso> listarTodos() {
        List<Map<String, Object>> result = HibernateConfig.getSessionFactory().openSession()
                .createCriteria(Curso.class, "bean")
                .createAlias("bean.coordenador", "coordenadorCurso")
                .createAlias("bean.escola", "escolaCurso")
                .setProjection(Projections.distinct(Projections.projectionList()
                        .add(Projections.property("bean.id").as("id"))
                        .add(Projections.property("bean.nome").as("nome"))
                        .add(Projections.property("bean.sigla").as("sigla"))
                        .add(Projections.property("bean.descricao").as("descricao"))
                        .add(Projections.property("coordenadorCurso.nome").as("coordenador.nome"))
                        .add(Projections.property("escolaCurso.nome").as("escola.nome"))))
                .setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP)
                .list();

        List<Curso> cursos = new ArrayList<>();

        result.forEach(curso -> cursos.add(setCurso(curso)));

        return cursos;
    }

    private Curso setCurso(Map<String, Object> objectMap) {
        Curso curso = new Curso();
        Usuario coordenador = new Usuario();
        Escola escola = new Escola();
        coordenador.setNome((String) objectMap.get("coordenador.nome"));

        escola.setNome((String) objectMap.get("escola.nome"));

        curso.setId((Long) objectMap.get("id"));
        curso.setCoordenador(coordenador);
        curso.setSigla((String) objectMap.get("sigla"));
        curso.setNome((String) objectMap.get("nome"));
        curso.setDescricao((String) objectMap.get("descricao"));
        curso.setEscola(escola);

        return curso;
    }

}
