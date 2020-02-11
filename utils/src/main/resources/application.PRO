spring:
  mvc:
    view:
      prefix: /WEB-INF/jsp/
      suffix: .jsp
  datasource:
    driverClassName: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://35.192.94.75:3306/hk_produccion?autoReconnect=true&useSSL=false&serverTimezone=America/Mexico_City
    username: root
    password: 7*DP{~aNW31E
  jpa:
    database-platform: org.hibernate.dialect.MySQL57InnoDBDialect
    hibernate:
      use-new-id-generator-mappings: false
      naming:
        physical-strategy: org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
app:
  version: V.20.02.11 Servicios API REST
  usuario:
    id: admin
    pwd: admin
  email:
    ide: formatos@villaeducativa.edu.mx
    alias: Administrador
    restUri: https://hk-util-pro.appspot.com/api/v1