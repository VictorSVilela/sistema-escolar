package br.com.grupoitss;

import br.com.grupoitss.controllers.AlunoController;
import br.com.grupoitss.model.Aluno;

public class Teste {
    public static void main(String[] args) {

        Aluno aluno1 = new Aluno();
        Aluno aluno2 = new Aluno();
        Aluno aluno3 = new Aluno();

        aluno1.setId(1l);
        aluno1.setNome("Victor");
        aluno1.setEmail("victor.vilela@grupoitss.com.br");
        aluno1.setIdade(30);

        aluno2.setId(2l);
        aluno2.setNome("Adriane");
        aluno2.setEmail("adriane@gmail.com");
        aluno2.setIdade(27);

        aluno3.setId(3l);
        aluno3.setNome("Fabio");
        aluno3.setEmail("fabio@hotmail.com");
        aluno3.setIdade(21);

        AlunoController alunoController = new AlunoController();
        /*
        alunoController.cadastrar(aluno1);
        alunoController.cadastrar(aluno2);
        alunoController.cadastrar(aluno3);

        alunoController.consultarTodos();

        */

        /*

        Escola escola = new Escola();

        escola.setNome("Escola Santa Terezinha");

        EscolaController escolaController = new EscolaController();

        //System.out.println(escolaController.cadastrar(escola));

        //System.out.println(pessoaController.Excluir(1));


        for (int i = 0; i < escolaController.todasEscolas().size(); i++) {
            System.out.println(escolaController.todasEscolas().get(i).getNome());
        }

        */


    }
}
