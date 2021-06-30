package br.com.projeto.service;

import br.com.projeto.exceptions.RegraNegocioException;
import br.com.projeto.model.Curso;
import br.com.projeto.model.Materia;
import br.com.projeto.repository.CursoRepository;

import java.util.HashSet;
import java.util.List;

public class CursoService {

    private static final CursoRepository cursoRepository = new CursoRepository();

    public Curso inserir(Curso curso) throws RegraNegocioException {
        if (cursoRepository.verificaSeNomeJaCadastrado(curso.getNome())) {
            throw new RegraNegocioException("Já existe um curso com esse nome!");
        }

        if (cursoRepository.verificaSeSiglaJaCadastrada(curso.getSigla())) {
            throw new RegraNegocioException("Já existe um curso com essa sigla!");
        }
        curso.setMaterias(new HashSet<>());
        curso.getMateriasIds().forEach(id-> curso.getMaterias().add(new Materia(id)));
        return cursoRepository.salvar(curso);
    }

    public Curso consultar(Long id) {
        Curso curso = cursoRepository.obter(id);
        //TODO: consultar a lista de matérias somente com o que eu preciso
        return curso;
    }

    public List<Curso> listarTodos() {
        return cursoRepository.listarTodos();
    }

    public Curso editar(Curso curso) throws RegraNegocioException {
        if (cursoRepository.verificaSeNomeJaCadastradoESeEMesmoNome(curso.getNome(),curso.getId()).isPresent()) {
            throw new RegraNegocioException("Já existe um curso com esse nome!");
        }

        if (cursoRepository.verificaSeSiglaJaCadastradaESeEMesmaSigla(curso.getSigla(),curso.getId()).isPresent()) {
            throw new RegraNegocioException("Já existe um curso com essa sigla!");
        }

        curso.setMaterias(new HashSet<>());
        curso.getMateriasIds().forEach(id-> curso.getMaterias().add(new Materia(id)));
        return cursoRepository.editar(curso);
    }

    public void deletar(Long id) {
        cursoRepository.deletar(id);
    }

}
