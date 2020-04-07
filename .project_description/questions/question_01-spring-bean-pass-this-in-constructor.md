@filename question_01-spring-bean-pass-this-in-constructor.
@since 07.04.2020
@author Yuriy Litvinenko
@project(s):D:\GeekBrains\_MyJavaProjectsAndSamples\Java_Spring\gb.lys\cloudstorage_spring

#Тема. Использование конструктора класса в spring.
##Вопрос. Могут ли в конструкторе быть другие параметры, кроме создаваемых бинов?
###Пример, конструктора без spring bean.
 В классе CloudStorageServer создаем объект NettyServer и 
   передаем ему себя(объект класса CloudStorageServer).
    new NettyServer(this, PORT).run();
 В классе NettyServer в конструкторе принимаем этот объект.
   public NettyServer(CloudStorageServer storageServer) {
       this.storageServer = storageServer;
   }
 И обращаемся к нему.
   public void printMsg(String msg){
       storageServer.printMsg(msg);
   }
###Пример кода на spring bean.
 ####Variant-1. Тот же код + 
 В классе ServerSpringConfig создаем бин.
     @Bean
     public NettyServer nettyServer(CloudStorageServer storageServer) {
         return new NettyServer(storageServer);
     }
 ERROR. Так делать нельзя - ошибка при создании бина, т.к. при спринг пытается 
 при создании бина NettyServer создать бин CloudStorageServer.
 ####Variant-2.
  В классе CloudStorageServer:
   добавляем переменную     
    private NettyServer nettyServer;
  и переделываем конструктор под бин - 
      public CloudStorageServer(NettyServer nettyServer) {
          this.nettyServer = nettyServer;
      }
  В классе ServerSpringConfig создаем бины.
      @Bean
      public CloudStorageServer cloudStorageServer(NettyServer nettyServer) {
          return new CloudStorageServer(nettyServer);
      }
      @Bean
      public NettyServer nettyServer() {
          return new NettyServer();
      }
  В классе CloudStorageServer:
   Вызываем метод run в NettyServer
        nettyServer.run(PORT);
  В классе NettyServer:
   Удаляем конструктор(теперь пустой - по умолчанию).
   И обращаемся к объекту класса CloudStorageServer.
    public void printMsg(String msg){
        storageServer.printMsg(msg);
    }
  ERROR. NPE - storageServer == null.
  FIXING. 
  В классе CloudStorageServer:
     Добавляем себя в параметры метода run в NettyServer
        nettyServer.run(this, PORT);
  В классе NettyServer:
     добавляем в метод run инициализацию объекта CloudStorageServer.
  public void run(CloudStorageServer storageServer, int port) throws Exception {
      this.storageServer = storageServer;
  Теперь можно обратиться к объекту класса CloudStorageServer.
      public void printMsg(String msg){
          storageServer.printMsg(msg);
      }
    
    