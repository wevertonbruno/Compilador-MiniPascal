# Compilador MiniPascal
> Projeto de um compilador para a linguagem mini pascal escrito em java. Compreende todas as etapas de analise léxica, sintática, contexto e geração de código.

**Se você é da UNIVASF e está pagando compiladores, não esqueça de nos citar nos trabalhos :)**

<br>
<hr>
<p align="center">
Se você achou isto útil, por favor não esqueça de deixar uma ⭐️ no repo, para ajudar a promover o projeto.<br>
Siga-me no <a href="https://twitter.com/oieusouweverton">Twitter</a> e <a href="https://github.com/wevertonbruno">GitHub</a> para ficar atualizado sobre os projetos e <a href="https://github.com/wevertonbruno?tab=repositories">outros</a>.
</p>
<hr>
<br>

## Como usar

**Para a execução do compilador é necessário compila-lo e gerar um arquivo .jar.**
<p align="justify"> Para funcionamento do compilador, são necessárias as inserções do
caminho do programa fonte e a do caminho do programa objeto gerado após a
geração de código, ambos em seus devido lugares localizados com um rótulo
indicativo. Esses caminhos devem possuir o nome do arquivo .txt, exemplo:
C:\Users\L\Desktop\nomedoarquivo.txt. Dessa forma será possível verificar o
conteúdo do programa fonte e gerar, ou editar, o programa objeto.
Após isso, é necessário escolher entre os botões presentes, qual a opção
do compilador deverá ser executada. Existem 5 possibilidades:
<ul>
<li>Análise Léxica: Executa a análise léxica do programa fonte, e será
mostrada no campo rotulado como, “Impressão da Árvore e Análise
Léxica”. Qualquer mensagem de erro será mostrada no console. Se
houver qualquer mensagem de erro referente a um token inválido, a 
mensagem será mostrada na tela e léxica feita até o ponto de erro será
mostrada.</li>
<li>Análise Sintática: Executa a análise sintática, mostrando quaisquer erros,
que venham a aparecer, na tela de console.</li>
<li>Impressão da Árvore: Essa opção irá imprimir a árvore se a análise
sintática estiver correta. A impressão será mostrada no campo rotulado
como, “Impressão da Árvore e Análise Léxica”.</li>
<li>Análise de Contexto: Executa a análise de contexto, mostrando quaisquer
erros que venham acontecer, na tela de console. Além disso, a árvore
também será imprimida, se a análise de sintática ocorrer de com sucesso.</li>
<li>Geração de Código: É necessário, para executar essa função, inserir, no
campo rotulado como “caminho do programa objeto”, o caminho aonde o
programa objeto será gerado, por exemplo, C:\Users\L\Desktop\objeto.txt.</li></ul></p>

## Mensagens de erro

<p align="justify">Diversas mensagens informando o usuário e possíveis problemas serão
mostrados na tela de console. Abaixo é mostrado quais mensagens podem
aparecer:
<ul>
<li> "Error: Source File not found!": Programa fonte não encontrado.</li>
<li> "Error row ‘x’ column ‘y’ Token “currentSpelling” is not acceptable.”: Indica
um erro léxico, devido a detecção de um token não pertencente a
gramática.</li>
<li> "Syntaxe Error: row ‘x’ column ‘y’. Unexpected token ’currentSpelling’:
Indica um erro léxico, devido a presença de um token em uma posição
indevida.</li>
<li> "Parser concluded": Indica que a análise sintática foi executada com
sucesso.</li>
<li> "Checker and Identifier Concluded": Indica que a análise de contexto foi
realizada com sucesso.</li>
<li> "Error: Program Object file was not found": Indica que o arquivo de destino
do programa objeto não pode ser acessado. Para solucionar esse 
problema, insira um caminho especificando o arquivo objeto a ser gerado
ou editado, um exemplo disso é C:\Users\L\Desktop\objeto.txt.</li>
<li> "Code has been generated": Geração de código foi executada com
sucesso.</li>
<li> "Incorrect Types!": Atribuição com tipo diferente do definido ou operação
onde operandos não possuem o mesmo tipo.</li>
<li> "Missing Arguments on Declaration in line ‘x’": Indica a falta de
argumentos em determinada chamada de rotina.</li>
<li> "Exceded Arguments to Declaration in line ‘x’.": Indica chamada de rotina
com excesso de argumentos.</li>
<li> "Incorrect Type of Arguments on Declaration in line ‘x’.": Indica que o tipo
dos argumentos está incorreto, ou seja, não é o mesmo que foi declarado.</li>
<li> "Incompatible Types in If Expression ": Expressão presente no comando
If não representa um booleano.</li>
<li> "Literal operands in Array Declaration must be Integers": Indica que os
indexadores do array devem ser número inteiros.</li>
<li> "First Operand is larger than Second in Array Declaration": Indica que na
declaração de arrays o valor mínimo é maior que o máximo.</li>
<li> "Incompatible Types in While Expression": Indica que a expressão
presente no comando While não é do tipo booleano.</li>
<li> "Array Operands Must Be Integers.": Os valores que indicam a indexação
do array devem ser inteiros.</li>
<li> "Exceded Array Dimension in line ‘x’: Indica que a dimensão do array se
excedeu. A maior dimensão possível é dois.</li>
<li> "Variable # ‘x’ already declared in this scope!": Indica que uma variável
foi redeclarada em um escopo.</li>
<li> "Variable ‘x’ was not declared": Indica que determinada variável não foi
declarada. </li></ul></p>

## Referência

D.A Watt, D.F Brown. **Programming Language Processors in Java**, Prentice-Hall, Englewood Cliffs, NJ (2000). 
<a href="http://www.cin.ufpe.br/~jml/programming-language-processors-in-java-compilers-and-interpreters.9780130257864.25356.pdf">[link]</a>

## Creditos

[@oieusouweverton](https://github.com/oieusouweverton) and all [contributors](https://github.com/wevertonbruno/WebMobileChat/graphs/contributors)

## Licensa

UNIVASF © [Weverton Bruno](https://github.com/wevertonbruno)

 
[main-image]: https://i.imgur.com/4xO8Mtx.png

