package br.com.projeto.service;

import br.com.projeto.exceptions.RegraNegocioException;
import br.com.projeto.model.Materia;
import br.com.projeto.repository.MateriaRepository;

import java.util.List;

public class MateriaService {

    private static final MateriaRepository materiaRepository = new MateriaRepository();

    public Materia inserir(Materia materia) throws RegraNegocioException {
        if (materiaRepository.verificaSeNomeJaCadastrado(materia.getNome())){
            throw new RegraNegocioException("Já existe uma matéria com esse nome!");
        }
        return materiaRepository.salvar(materia);
    }

    public Materia consultar(Long id) {
        Materia materia = materiaRepository.obter(id);
        return materia;
    }

    public List<Materia> listarTodas() {
        return materiaRepository.listarTodas();
    }

    public Materia editar(Materia materia) throws RegraNegocioException {
        if(materiaRepository.verificaSeNomeJaCadastradoESeEMesmoNome(materia.getNome(),materia.getId()).isPresent()){
            throw new RegraNegocioException("Já existe uma matéria com esse nome!");
        }
        return materiaRepository.editar(materia);
    }

    public void deletar(Long id) {
        materiaRepository.deletar(id);
    }
}