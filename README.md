### Como instalar
primeiro precisara de um baco de dados mysql rodando 
no arquivo application.properties configurar as variaveis de ambiente
EX:
spring.jpa.hibernate.ddl-auto=update

spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/trabalho-devweb

spring.datasource.username=root

spring.datasource.password=root

api.security.token.secret=${JWT_SECRET:minha-chave}

### Aplicações que eu utilizei
- Inttelij
- Dbeaver
- insomnia
- Docker para o banco de dados

### Algumas explicações

- Na raiz do projeto segue o arquivo do insomnia com todos os exemplos de rotas do projeto, so baixar e importar a collection
- Se rodar a rota seeder ja popula as tabelas com alguns dados de teste
- Nela é criado um usuario: usuarioTeste e senha: 123456789 com permissão de admin


### Algumas features

- login e cadastro de usuário
- permissionamento em três categorias, USUARIO, ORGANIZADOR E ADMINISTRADOR
- O permissionamento é em piramide, ex: O ADMINISTRADOR tem todas as permissoes que o ORGANIZADOR e o USUARIO tem, como o ORGANIZADOR tem também as dos USUARIO
- Rotas travadas por permissoes
- Crud de usuarios
- Crud de recursos
- Crud de espaços
- Crud de evento
- Crud de edição de evento
- Crud de atividade de edição de eventos


