# Instruções do GitHub Copilot

## Descrição do Projeto

API REST desenvolvida com Spring Boot 4.0.0 e Java 21 para gerenciamento de pessoas (Person). Utiliza Maven como ferramenta de build, MySQL como banco de dados, e segue arquitetura em camadas com controllers, services e models.

## Arquitetura

### Estrutura de Pacotes
- **br.com.wagner.controllers**: Controladores REST (ex: `PersonController`)
- **br.com.wagner.model**: Entidades de domínio (ex: `Person`)
- **br.com.wagner.exception**: Tratamento centralizado de exceções
  - `exception.hadler`: Contém `CustomEntityResponseHadler` com `@ControllerAdvice`
- **br.com.wagner.request.converters**: Utilitários para conversão de dados
- **Startup.java**: Classe principal com `@SpringBootApplication`

### Padrões de Código

#### Controllers
- Usar `@RestController` e `@RequestMapping` no nível da classe
- Endpoints devem especificar `method`, `produces` e `consumes` explicitamente
- Exemplo: `@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)`
- Injetar services com `@Autowired`
- Path variables: `@PathVariable("id") String id`

#### Services
- Anotar com `@Service`
- Usar `Logger` do `java.util.logging` para logs informativos
- Pattern de log: `logger.info("Finding one person");`
- Services atualmente usam mock data com `AtomicLong` para IDs

#### Models
- Implementar `Serializable` com `serialVersionUID = 1L`
- Usar POJOs com getters/setters
- Incluir construtor vazio
- Sobrescrever `equals()` e `hashCode()` quando necessário

#### Tratamento de Exceções
- Usar `@ControllerAdvice` para tratamento global
- Criar `ExceptionResponse` padronizado com timestamp, message e details
- Exceções customizadas no pacote `exception` (ex: `UnsupportedMathOperationExcepetion`)
- Retornar `ResponseEntity<ExceptionResponse>` com status HTTP apropriado

## Configuração e Ambiente

### Dependências Principais
- Spring Boot 4.0.0
- Java 21
- Spring Web (REST)
- Spring Data JPA + Hibernate
- MySQL Connector 8.0.33
- Spring DevTools (desenvolvimento)

### Banco de Dados
- MySQL: `jdbc:mysql://localhost:3306/rest_with_spring_boot_wagner`
- Configuração em `src/main/resources/application.yml`
- Credenciais padrão: username=root, password=(vazio)

### Build e Execução
```bash
mvn clean install          # Build do projeto
mvn spring-boot:run        # Executar aplicação
```

## Convenções Específicas

1. **Nomenclatura**: Classes de serviço no plural (ex: `PersonServices` não `PersonService`)
2. **Endpoints**: Usar substantivos no plural (`/person` para CRUD de Person)
3. **Idioma misto**: Código em inglês, comentários e logs podem ser em português
4. **Package base**: Sempre usar `br.com.wagner` como raiz
5. **Typos conhecidos**: `hadler` em vez de `handler` (manter consistência com código existente)