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
