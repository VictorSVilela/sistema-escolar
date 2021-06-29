package br.com.projeto.repository;

import br.com.projeto.config.HibernateConfig;
import br.com.projeto.model.Escola;
import br.com.projeto.model.Usuario;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class EscolaRepository extends BaseRepository<Escola> {

    public EscolaRepository() {
        super(Escola.class);
    }

    @SuppressWarnings("unchecked")
    public List<Escola> listar() {
        List<Map<String, Object>> result = HibernateConfig.getSessionFactory().openSession()
                .createCriteria(Escola.class, "bean")
                .createAlias("bean.diretor", "diretorEscola")
                .setProjection(Projections.distinct(Projections.projectionList()
                        .add(Projections.property("bean.id").as("id"))
                        .add(Projections.property("bean.nome").as("nome"))
                        .add(Projections.property("diretorEscola.nome").as("diretor.nome"))
                        .add(Projections.property("bean.status").as("status"))
                        .add(Projections.property("bean.descricao").as("descricao"))))
                .setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP)
                .list();

        List<Escola> escolas = new ArrayList<>();

        result.forEach(diretor -> escolas.add(buscarDiretor(diretor)));

        return escolas;
    }

    private Escola buscarDiretor(Map<String, Object> objectMap) {
        Usuario diretor = new Usuario();
        diretor.setNome((String) objectMap.get("diretor.nome"));
        Escola escola = new Escola();
        escola.setId((Long) objectMap.get("id"));
        escola.setNome((String) objectMap.get("nome"));
        escola.setDescricao((String) objectMap.get("descricao"));
        escola.setStatus((boolean) objectMap.get("status"));
        escola.setDiretor(diretor);
        return escola;
    }

    public boolean verificaSeNomeJaCadastrado(String nome) {
        Long count = (Long) HibernateConfig.getSessionFactory().openSession()
                .createCriteria(this.getTClass(), "bean")
                .add(Restrictions.eq("bean.nome",nome))
                .setProjection(Projections.count("bean.nome"))
                .setMaxResults(1)
                .uniqueResult();

        return count > 0;
    }

    public Optional<String> verificaSeNomeJaCadastradoESeEMesmoNome(String nome, Long id) {
        String result = (String) HibernateConfig.getSessionFactory().openSession()
                .createCriteria(this.getTClass(),"bean")
                .add(Restrictions.eq("bean.nome",nome))
                .add(Restrictions.ne("bean.id",id))
                .setProjection(Projections.property("bean.nome"))
                .setMaxResults(1)
                .uniqueResult();

        return Optional.ofNullable(result);
    }
}
