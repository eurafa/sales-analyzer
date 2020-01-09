# Sales Analyzer

## Introdução

Este projeto é responsável pela leitura de dados de vendas e análise dos dados para geração de relatório, válido para avaliação de perfil, em termos de design de software, código Java e boas práticas de desenvolvimento.

## Solicitação

A solicitação indica para ler arquivos, realizar a análise dos dados e gerar relatório, mas é importante ressaltar que não é 100% clara quanto ao volume, a distinção de layout/formato e a distribuição dos mesmos nos arquivos de entrada, e tem alguns pontos contraditórios que deixam dúvidas quanto a saída, se é esperado um relatório único ou um relatório para cada arquivo analisado.

Entre outras dúvidas como:

* Caractere 'ç' como separador, tratar o parse visto que existem nomes com uso desse caractere? 
* Não havendo vendas nos arquivos, como determinar a venda mais cara?
* Não havendo vendas de um determinado vendedor, este deve ser considerado como o pior vendedor?

## Solução proposta

A solução proposta é a varredura no diretório desejado em busca dos arquivos de entrada, que ainda não tem análise realizada e relatório gerado, para então realizá-los.

Dessa forma, é esperado que cada arquivo de entrada contenha um universo de dados, de onde se deve analisar e extrair as informações necessárias para o relatório a ser gravado no arquivo de saída.

Após o processamento das análises, um listener é ativado monitorando o diretório de entrada. A chegada de um novo arquivo automaticamente é o gatilho para a análise do mesmo.

### Tecnologias

* JDK 8
* Gradle
* SpringBoot
* Apache Commons IO
* Logback
* JUnit
* AssertJ

### Como usar

Para

Obs.: Certifique-se da variável de ambiente `%HOMEPATH%` que indica o diretório base, bem como os diretórios internos de entrada e saída.

### Próximos passos

De fato, a aplicação já pode ser usada, porém sempre existem pontos de evolução.