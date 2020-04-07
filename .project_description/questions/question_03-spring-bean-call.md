@filename question_03-spring-bean-call.
@since 07.04.2020
@author Yuriy Litvinenko
@project(s):D:\GeekBrains\_MyJavaProjectsAndSamples\Java_Spring\gb.lys\cloudstorage_spring

#Тема. Зыпуск приложения в spring.
##Вопрос. В чем разница в методах вызова бина(оба рабочие).
###Variant-1. С явным указанием имени бина.
 В классе ServerMain.
  CloudStorageServer css = context.getBean("cloudStorageServer",
                  CloudStorageServer.class);
###Variant-2. С без указания имени бина.
  CloudStorageServer css = context.getBean(CloudStorageServer.class);

##Ответ.(Возможно)
 Т.к. спринг автоматически присваивает имя бина по имени класса, 
 то во втором варианте неявно вызываетя бин с именем cloudStorageServer, 
 т.к. такое имя будет присвоено автоматически для класса CloudStorageServer.
    
    