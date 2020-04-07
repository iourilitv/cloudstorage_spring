@filename question_02-spring-bean-init.
@since 07.04.2020
@author Yuriy Litvinenko
@project(s):D:\GeekBrains\_MyJavaProjectsAndSamples\Java_Spring\gb.lys\cloudstorage_spring

#Тема. Способы инициализации бина в spring через конфигурационный класс.
##Вопрос. В чем разница в способах инициализации бина в конфигурационном классе.
Оба способа рабочие и конструкторы классов одинаковые.
В классе ServerSpringConfig.
###Variant-1. инициализации bean через бины(метод без параметров). 
 На основании вебинара Neil Alishev.
  @Bean
  public CloudStorageServer cloudStorageServer() {
    return new CloudStorageServer(
             usersAuthController(), fileUtils(), itemUtils(), propertiesHandler());
  }
 
###Variant-2.
 На основании уроков Преподавателя GB: Алексея Ушаровского
  @Bean
  public CloudStorageServer cloudStorageServer(
                UsersAuthController usersAuthController, FileUtils fileUtils,
                ItemUtils itemUtils, PropertiesHandler propertiesHandler) {
     return new CloudStorageServer(
            usersAuthController, fileUtils, itemUtils, propertiesHandler);
  }
  
  @Bean
  public DataSource dataSource(String jdbcUrl) {
      DriverManagerDataSource ds = new DriverManagerDataSource();
      ds.setUrl(jdbcUrl);
      ds.setUsername("root");
      ds.setPassword("root");
      ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
      return ds;
  }
  
##Выводы.
Оба варинта можно применять.
Вариант 1 - проще код.
Вариант 2 позволяет применить параметр внутри метода. 
