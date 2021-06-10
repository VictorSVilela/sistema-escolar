package br.com.grupoitss.service;

import br.com.grupoitss.model.Materia;
import br.com.grupoitss.repository.MateriaRepository;

import java.util.List;

public class MateriaService {

    private static final MateriaRepository materiaRepository = new MateriaRepository();

    public Materia inserir(Materia materia) {
        return materiaRepository.salvar(materia);
    }

    public Materia consultar(Long id) {
        Materia materia = materiaRepository.obter(id);
        return materia;
    }

    public List<Materia> listarTodas() {
        return materiaRepository.listarTodas();
    }

    public Materia editar(Materia materia) {
        return materiaRepository.editar(materia);
    }

    public void deletar(Long id) {
        materiaRepository.deletar(id);
    }
}