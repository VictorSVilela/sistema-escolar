package br.com.projeto.repository;

import br.com.projeto.config.HibernateConfig;
import br.com.projeto.model.Materia;
import br.com.projeto.model.Usuario;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Projections;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MateriaRepository extends BaseRepository<Materia> {

    public MateriaRepository() {
        super(Materia.class);
    }

    @SuppressWarnings("unchecked")
    public List<Materia> listarTodas() {
        List<Map<String, Object>> result = HibernateConfig.getSessionFactory().openSession()
                .createCriteria(Materia.class, "bean")
                .createAlias("bean.professor", "professorMateria")
                .setProjection(Projections.distinct(Projections.projectionList()
                        .add(Projections.property("bean.id").as("id"))
                        .add(Projections.property("bean.nome").as("nome"))
                        .add(Projections.property("bean.descricao").as("descricao"))
                        .add(Projections.property("professorMateria.nome").as("professor.nome"))))
                .setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP)
                .list();

        List<Materia> materias = new ArrayList<>();

        result.forEach(usuario -> materias.add(buscarProfessor(usuario)));

        return materias;
    }

    private Materia buscarProfessor(Map<String, Object> objectMap) {
        Usuario professor = new Usuario();
        professor.setNome((String) objectMap.get("professor.nome"));
        Materia materia = new Materia();
        materia.setId((Long) objectMap.get("id"));
        materia.setNome((String) objectMap.get("nome"));
        materia.setDescricao((String) objectMap.get("descricao"));
        materia.setProfessor(professor);

        return materia;
    }


}
