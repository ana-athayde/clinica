# Clínica

Este projeto simula a criação de um sistema de gerenciamento para uma clínica médica fictícia.

## Estrutura do Projeto

- `view`: O diretório `view` contém os arquivos relacionados à interface do usuário. Alguns dos principais arquivos neste diretório são listados anteriormente.

- `model`: O diretório `model` é responsável pela lógica de dados da aplicação. Ele contém os seguintes subdiretórios:

  - `DAO`: Contém os arquivos relacionados às classes de acesso a dados, responsáveis por interagir com o banco de dados. Alguns dos principais arquivos neste diretório são:

    - `ConnectionFactory.java`: Classe que gerencia a conexão com o banco de dados.

    - `ConsultaDAO.java`: Classe para interação com dados de consultas médicas.

    - `FuncionarioDAO.java`: Classe para operações relacionadas a funcionários da clínica.

    - `MedicoDAO.java`: Classe para operações relacionadas a médicos.

    - `PacienteDAO.java`: Classe para operações relacionadas a pacientes.

  - `services`: Contém os arquivos relacionados aos serviços da aplicação, que podem incluir lógica de negócios e processamento de dados. Alguns dos principais arquivos neste diretório são:

    - `ConsultaServices.java`: Classe que fornece serviços relacionados a consultas médicas.

    - `FuncionarioServices.java`: Classe que oferece serviços relacionados a funcionários da clínica.

    - `MedicoServices.java`: Classe que fornece serviços relacionados a médicos.

    - `PacienteServices.java`: Classe que oferece serviços relacionados a pacientes.

- `lib`: O diretório `lib` contém as bibliotecas (JAR files) usadas no projeto. Alguns dos principais arquivos neste diretório são:

    - `calendar-1.4.jar`: Biblioteca para funcionalidades relacionadas a calendários.
    
    - `mysql-connector-java-8.0.12.jar`: Biblioteca para conexão com banco de dados MySQL.

- `imagens`: O diretório `imagens` contém os recursos gráficos utilizados na interface do usuário. Alguns dos principais arquivos neste diretório são:

    - `fundo_login.jpg`: Imagem de fundo da tela de login.
    
    - `fundo_principal.jpg`: Imagem de fundo da tela principal.
    
    - `icone_agenda.png`: Ícone relacionado à agenda.
    
    - `icone_fechar.png`: Ícone de fechamento.
    
    - `icone_funcionario.png`: Ícone relacionado a funcionários.
    
    - `icone_medica.png`: Ícone relacionado a médicas.
    
    - `icone_medico.png`: Ícone relacionado a médicos.
    
    - `icone_paciente.png`: Ícone relacionado a pacientes.
    
    - `logo_login.png`: Logo da tela de login.

- `controller`: O diretório `controller` contém os arquivos relacionados à lógica de controle da aplicação. Alguns dos principais arquivos neste diretório são:

    - `ConsultaController.java`: Controlador relacionado a consultas médicas.
    
    - `FuncionarioController.java`: Controlador relacionado a funcionários da clínica.
    
    - `MedicoController.java`: Controlador relacionado a médicos.
    
    - `PacienteController.java`: Controlador relacionado a pacientes.
    
    - `PrincipalController.java`: Controlador principal da aplicação.

- `classes`: O diretório `classes` contém as classes principais do projeto. Alguns dos principais arquivos neste diretório são:

    - `Agenda.java`: Classe relacionada à agenda da clínica.
    
    - `Consulta.java`: Classe relacionada a consultas médicas.
    
    - `Funcionario.java`: Classe relacionada a funcionários da clínica.
    
    - `Medico.java`: Classe relacionada a médicos.
    
    - `Paciente.java`: Classe relacionada a pacientes.
    
    - `Pessoa.java`: Classe base que pode ser usada para representar pessoas na aplicação.

## Como Executar

1. Clone este repositório em sua máquina local:

   ```bash
   git clone github.com/ana-athayde/clinica.git

2. Configure um ambiente de servidor web local e um banco de dados, se necessário.

3. Importe os scripts SQL fornecidos (se aplicável) para criar a estrutura do banco de dados.

4. Execute o aplicativo em seu navegador.

# Contribuição

Se você deseja contribuir para o projeto "Clínica", siga estas etapas:

1. Faça um fork do repositório.

2. Clone o repositório forked para o seu ambiente de desenvolvimento.

3. Faça as alterações desejadas e crie um branch para suas modificações.

4. Faça commit das alterações em seu branch.

5. Envie um pull request para o repositório original.

Seus pull requests serão revisados e, se aprovados, mesclados ao projeto.

O projeto pode se beneficiar de contribuições relacionadas a correções de bugs, melhorias de código, recursos adicionais, atualizações de documentação ou qualquer outra forma de aprimoramento.

Lembre-se de seguir as boas práticas de desenvolvimento, incluir testes, documentar seu código e garantir que suas alterações sejam compatíveis com a estrutura do projeto.

Agradecemos a sua contribuição!

