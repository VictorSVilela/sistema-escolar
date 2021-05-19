### Client
- AngularJS
- HTML
- CSS
- Bootstrap 4

### Server (Backend)
- Java
- Jersey (REST)
  - GET 
  - POST
  - PUT
  - ...  
- Servlet
- Tomcat 9
- Maven
    - baixar dependências
    - builda a aplicação

-------------

1 - Chamar uma chamada rest do Front para o Back e exibir o Hello World do backend no frontend

2 - Criar entidade Aluno
- Salva em memória (lista no controller)
- AlunoController
- Aluno vai ter
Long id;
String nome;
String email; 
Integer idade;

3 - Backend vai retornar em JSON as infos do Aluno
```json
{
  "id": "",
  "nome": "",
  "email": "",
  "idade": ""
}
```

```javascript 
var aluno = {
  "id": "",
  "nome": "",
  "email": "",
  "idade": ""
}

aluno.id
aluno.nome
aluno.email

```
- Criar o CRUD do Aluno 

4 - Criar um formulário no frontend pra salvar um aluno
- utilizar o bootstrap, angularjs
- Adicionar um botão pra dar submit no formulário pro backend
- Salvar as informações em memória

5 - Criar tela pra listar todos os alunos em uma tabela
- na tabela vai ter as colunas de id, nome, email, idade e ações
- Coluna de ações vai ter dois botões, editar e excluir
- Botão de editar vai para a tela de edição do aluno
- Botão de excluir vai exibir um alerta de confirmação para excluir o aluno

6 - Criar entidade de Usuario
- id
- nome (255 caracteres)
- data de nascimento

7 - Criar entidade de Escola
- id
- nome (255 caracteres)
- diretor (Usuario)
- descricao (300 caracteres)
- ativo (não existe o campo na tela de inclusão, só edição)
No momento que criar uma escola, ela já vai estar ativa

8 - Criar entidade de Matéria
- id
- nome (255 caracteres)
- descrição (2500 caracteres)
- professor (Usuário)

9 - Criar entidade de Curso
- id
- nome
- sigla (somente letras e no máximo 5 caracteres)
- coodenador (Usuário)
- escola (Escola)
- lista de matérias (Set<Materia>)
- descrição (2500 caracteres)

10 - Criar entidade de Turma
- id
- nome
- matricula (sigla do curso - 0000)
  campo somente de leitura
  quando selecionar um curso, vai buscar a sigla do curso e 
  concatenar com a sequencia da turma
- curso (Curso)
- alunos (List<Aluno>)

11 - Alterar entidade de Aluno
- Manter os atributos
- matricula (matricula da Turma - 0) 
- turma (Turma)