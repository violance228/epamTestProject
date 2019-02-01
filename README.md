# libraryProj
Система Библиотека. Создайте Каталог, по которому можно искать по:
•	Автору (одному из группы).
•	Названию книги или её фрагменте.
•	Одному из ключевых слов книги (атрибут книги).
Каталог книг заполняет Администратор, добавляя и изменяя/удаляя их. Каждая книга должна иметь адрес (место на полке) или читателя.
Читатель чтобы взять книгу регистрируется, оставляя э-мейл и номер телефона. Книга может быть взята у Администратора 
в библиотеке на время не более месяца,только в случае если книга доступна в библиотеке.
Администратор должен иметь страницу где отражаются взятые книги и читатели, которые пользуются книгой.

1.  Install jdk(jre) and set JAVA_HOME example -> set JAVA_HOME /usr/lib/jvm.
2.  Download tomcat(https://tomcat.apache.org/download-80.cgi) or other.
3.  Download postgres -> sudo apt-get install postgresql postgresql-client
4.  Create db with name library and restore file library.sql from git repo
5.  Setting tomcat ${catalina.home}/conf/tomcat-users.xml -> set user and userRole
6.  If tomcat can't start and write error setup jdk or jre, ${catalina.home}/conf/startup. open edit and under word setlocal
write path jdk and jre without bin
