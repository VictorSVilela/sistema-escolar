package br.com.projeto.service;

import br.com.projeto.handler.RegraNegocioHandler;
import br.com.projeto.model.Materia;
import br.com.projeto.repository.MateriaRepository;

import java.util.List;

public class MateriaService {

    private static final MateriaRepository materiaRepository = new MateriaRepository();

    public Materia inserir(Materia materia) throws RegraNegocioHandler {
        if (materiaRepository.verificaSeNomeJaCadastrado(materia.getNome())){
            throw new RegraNegocioHandler("Já existe uma matéria com esse nome!");
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

    public Materia editar(Materia materia) throws RegraNegocioHandler {
        if(materiaRepository.verificaSeNomeJaCadastradoESeEMesmoNome(materia.getNome(),materia.getId())){
            throw new RegraNegocioHandler("Já existe uma matéria com esse nome!");
        }
        return materiaRepository.editar(materia);
    }

    public void deletar(Long id) {
        materiaRepository.deletar(id);
    }
}