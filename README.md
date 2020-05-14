--------------------PROGRESSO--------------------

Fase I
a)Implementar validações nas classes prontas.
b)Implementar coloração de contorno.
c)Implementar coloração de interior, quando cabível.
**d)Implementar o desenho de círculo; OK**
**e)Implementar o desenho de elipse. OK**

Fase II
a)Implementar o desenho de quadrado.
b)Implementar o desenho de retângulo.
c)Implementar o desenho de polígonos, clicando em seus vértices.
d)Implementar  a  escrita  de  texto(clica-se  em  uma  área,  mostra  o  texto  conforme  o  usuário digita)

Fase III 

**a) Implementar o uso contínuo de ferramenta, ou seja, escolhida uma ferramenta ela só deixaria
de ser usada quando outra fosse escolhida; OK**
b) Implementar o desenho contínuo, ou seja, o desenho deve ir ocorrendo, crescendo ou diminuindo, à medida que o mouse é arrastado.
c) Implementar salvamento de desenhos.
d) Implementar carga (abertura de desenho já salvo) de desenhos.



**--------------------COMO USAR GIT--------------------**
**Baixar o projeto**

git clone https://github.com/annajuliapg/Paint.git

**Subir arquivos**

git add .
git commit -m " qualquer mensagem "
git push origin <branch>


**Criar novo branch**

git checkout -b <branch>



**Merge - Tem que estar sem alteração local**

git checkout master
git pull
git checkout <branch>
git merge master

e depois abrir um pull request e mergear
