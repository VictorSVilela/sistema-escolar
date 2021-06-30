package br.com.projeto.repository;

import br.com.projeto.config.HibernateConfig;
import br.com.projeto.model.Curso;
import br.com.projeto.model.Escola;
import br.com.projeto.model.Usuario;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    public Curso obterCursoComMaterias(Long cursoId) {
        this.session = HibernateConfig.getSessionFactory().openSession();
        Curso curso = this.session.get(Curso.class, cursoId);

        Optional.ofNullable(curso).ifPresent(elem -> {
            curso.setMateriasIds(new ArrayList<>());
            curso.setMaterias(curso.getMaterias());
            curso.getMaterias().forEach(materia -> curso.getMateriasIds().add(materia.getId()));
        });

        this.session.close();

        return curso;
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

    public boolean verificaSeNomeJaCadastrado(String nome) {
        Long count = (Long) HibernateConfig.getSessionFactory().openSession()
                .createCriteria(this.getTClass(), "bean")
                .add(Restrictions.eq("bean.nome", nome))
                .setProjection(Projections.count("bean.nome"))
                .setMaxResults(1)
                .uniqueResult();

        return count > 0;
    }

    public boolean verificaSeSiglaJaCadastrada(String sigla) {
        Long count = (Long) HibernateConfig.getSessionFactory().openSession()
                .createCriteria(this.getTClass(), "bean")
                .add(Restrictions.eq("bean.sigla", sigla))
                .setProjection(Projections.count("bean.sigla"))
                .setMaxResults(1)
                .uniqueResult();

        return count > 0;
    }

    public Optional<String> verificaSeNomeJaCadastradoESeEMesmoNome(String nome, Long id) {
        String result = (String) HibernateConfig.getSessionFactory().openSession()
                .createCriteria(this.getTClass(), "bean")
                .add(Restrictions.eq("bean.nome", nome))
                .add(Restrictions.ne("bean.id", id))
                .setProjection(Projections.property("bean.nome"))
                .setMaxResults(1)
                .uniqueResult();

        return Optional.ofNullable(result);
    }

    public Optional<Object> verificaSeSiglaJaCadastradaESeEMesmaSigla(String sigla, Long id) {
        String result = (String) HibernateConfig.getSessionFactory().openSession()
                .createCriteria(this.getTClass(), "bean")
                .add(Restrictions.eq("bean.sigla", sigla))
                .add(Restrictions.ne("bean.id", id))
                .setProjection(Projections.property("bean.sigla"))
                .setMaxResults(1)
                .uniqueResult();

        return Optional.ofNullable(result);
    }
}
