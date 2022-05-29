# Desafio técnico

Escrever uma API de cadastro de clientes (Nome, CPF, nascimento)

**Importante:** Validar o CPF antes de gravar o cliente no banco de dados

## Informações técnicas

### Endpoints
- [ ]  Endpoint para criar clientes (esse endpoint deve retornar um 422 caso o cpf seja inválido)
- [ ]  Endpoint para buscar cliente por CPF
- [ ]  Endpoint para listar todos os clientes (usando paginação)

### Para devs fullstack (desconsiderar caso seja um teste para backend)
- [ ] Criar uma página para consumir a API

#### Regras para validação do CPF
- O CPF pode ser passado em dois formatos: 
  - 999.999.999-00 (com máscara)
  - 99999999900 (somente números)
  
- Para validação da numeração do CPF pode utilizar as regras do seguinte link: https://www.macoratti.net/alg_cpf.htm#:~:text=O%20algoritmo%20de%20valida%C3%A7%C3%A3o%20do,%3A%20111.444.777%2D05.


## Requisitos 
- Escrever o algoritmo de validação do CPF manualmente (**NÃO** usar libs prontas para isso)
- **Crie um fork desse repositório** para nos enviar o desafio
- Utilize uma linguagem que possibilite o uso de orientação a objetos como Java, C#, Python, Javascript, etc
- Escolha o banco de dados de sua preferência


## Recomendações
- Lembre-se de utilizar boas práticas como: testes, orientação a objetos, design patterns, logs, documentação, etc.
- É importante ser bem simples rodar sua aplicação em qualquer servidor (independente do sistema operacional ou outros softwres instalados). 
- Escreva um Readme.md (explique como subir sua aplicação, tecnologias utilizadas, e como rodar os testes)
