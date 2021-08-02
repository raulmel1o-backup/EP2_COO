# EP 2 — Computação Orientada a Objetos
### Autores
- Enrique Emanuel Rezende Tavares da Silva (11796090)
- Raul Mello Silva (11870986)

## Arquivos
Este zip possui o código fonte dentro do diretório `src`, as classes 
compiladas dentro do diretório `out` e o diagrama UML dentro do diretório 
`resources`

## Compilação e Execução
Para facilitar o trabalho de compilação e execução do programa foi criado um
arquivo `Makefile`, dessa maneira, para compilar todo o programa é necessário 
apenas utilizar o comando:

```> make compile```

Com isso todas as classes serão compiladas a usar o `javac` e inseridas no 
diretório `out`.

Para executar o programa é necessário apenas executar a partir da raiz do EP 
o comando:

```> make exec```

Este comando irá inicializar a aplicação que irá se comunicar com o usuário 
por meio do terminal.

## Funcionamento
O programa funciona por interações de leitura e escrita no terminal, 
uma vez inicializado o programa continuará em execução até que o usuário 
insira a keyword `fim` ou interrompa a sua execução por algum outro meio que 
interrompa a execução do terminal.

O usuário dispõe de oito comandos disponíveis além do comando `fim`, a 
inserção de um comando inválido não deve interromper a execução do programa, 
entretanto deve alertar o usuário sobre o erro e aguardar uma nova instrução.

Os intervalos de tempo são compostos por unidades de 15 minutos cada, portanto 
o primeiro intervalo do dia é de 00:00 e acrescenta-se 15 minutos ao tempo a 
cada novo intervalo.

Os comandos são:

`marcar`: Este comando utiliza a classe `MarcadorDeReuniao` para agendar uma 
reunião entre dois `DateTimes` incluindo uma lista de participantes. Após a 
entrada do comando alguns diálogos serão impressos no terminal solicitando 
informações do usuário.

```
> marcar
Entre quais dias você gostaria de marcar a reunião?
Insira o dia inicial (formato yyyy-MM-dd)
> 2021-07-22
Insira o dia final (formato yyyy-MM-dd)
> 2021-07-28
Insira o email dos convidados separado por ';' (ex: convidado1@email.com;convidado2@email.com
> raul@email.com;emanuel@email.com
```

O comando irá imprimir um erro caso alguma das informações produza uma exceção.

`indicar`: Este comando utiliza a classe `MarcadorDeReuniao` para indicar 
quais horários do dia cada um dos participantes possui disponível para 
comparecer à reunião. Após a entrada do comando alguns diálogos serão 
impressos no terminal solicitando informações do usuário.

```
> indicar
Indique em quais horários você está disponível
Insira o seu email
> raul@email.com
Insira o horario inicial (formato yyyy-MM-dd HH:mm)
2021-07-24 14:00 15:15
Insira o horario final (formato yyyy-MM-dd HH:mm)
2021-07-24 19:45 23:00
```

O comando irá imprimir um erro caso alguma das informações produza uma exceção.

`sobreposicao`: Este comando utiliza a classe `MarcadorDeReuniao` para imprimir 
os intervalos do dia em que os participantes estão disponíveis. Será impressa 
uma tabela onde cada coluna representa um dia do intervalo criado pelo 
marcador da reunião e cada linha corresponde a um intervalo de 15 minutos do 
dia, na posição intervalo x dia há um inteiro que representa a quantidade de 
participantes disponíveis naquele horário. Caso haja zero participantes 
disponíveis o numeral será impresso em vermelho, caso haja todos os 
participantes estejam disponíveis o numeral será impresso em verde e se os 
dois casos anteriores forem falsos a impressão será na cor amarela.

Será impresso também um pequeno sumário logo abaixo da tabela listando apenas 
os horários em que todos os participantes estão disponíveis.

Não será exibido um exemplo de código do comando `sobreposicao` devido ao seu 
grande tamanho.

`adiciona`: Esta comando utiliza a classe `GerenciadorDeSalas` para adicionar 
uma nova sala com um nome, uma capacidade máxima e uma descrição.

```
> adiciona
Insira o nome da sala
Abc
Insira a capacidade máxima da sala
10
Insira uma descrição da sala
Abc é pequena
```

O comando irá imprimir um erro caso alguma das informações produza uma exceção.

`remove`: Este comando utiliza a classe `GerenciadorDeSalas` para remover uma 
sala existente a partir do seu nome.

```
> remove
Insira o nome da sala que será removida
> Abc
```

O comando irá imprimir um erro caso alguma das informações produza uma exceção.

`reserva`: Este comando utiliza a classe `GerenciadorDeSalas` para reservar 
uma das salas criadas para uma reunião a parir do nome da sala, um horário de 
início e um horário de término.

```
> reserva
Insira o nome da sala que será reservada
Abc
Insira o horário inicial (formato yyyy-MM-dd HH:mm)
2021-07-24 12:00
Insira o horário final (formato yyyy-MM-dd HH:mm)
2021-07-24 12:30
```
O comando irá imprimir um erro caso alguma das informações produza uma exceção.


`cancela`: Este comando utiliza a classe `GerenciadorDeSalas` para cancelar 
uma reserva feita anteriormente a partir do nome da sala, data de início e de 
término.

```
> cancela
Insira o nome da sala
Abc
Insira o horário de início da reserva da sala (formato yyyy-MM-dd HH:mm)
2021-07-24 14:15
Insira o horário de início da reserva da sala (formato yyyy-MM-dd HH:mm)
2021-07-24 15:15
```

O comando irá imprimir um erro caso alguma das informações produza uma exceção.

`reservas`: Este comando utiliza a classe `GerenciadorDeSalas` para listar 
todas as reservas de uma dada sala a partir do seu nome.

```
> reservas
Insira o nome da sala
Abc
```

O comando irá imprimir um erro caso alguma das informações produza uma exceção.
