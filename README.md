# SpringBoot Integration Demo

---

## DESC:
    
Springboot integration demo project

```java
/**
 * desc : Fake code, Just for entertainment
 * @author quaint 
 * @date 2020-01-11 15:39:56
 */
public class Parent{
    
    // Pretend to read pom.xml
    private Stream<String> parent = Files.lines(Paths.get("pom.xml"));
    
    // Pretend to process content
    protected Consumer<Stream<String>> consumer = (stream) -> stream
        .filter(str -> str.toString("dependency to table"))
        .forEach(System.out::printf);
        
    // Code block, execution consumer.
    { consumer.accept(parent); }
}
```

| name | version |
| :---: | :---: |
| springboot | 2.1.2.RELEASE |
| lombok | 1.16.10 |
| swagger2 | 2.7.0 |
| commons-lang3 | 3.4 |

---

## [demo-mybatis-plus](https://github.com/quaintclever/Java-SID/tree/master/demo-mybatis-plus)

```java
// Fake code, Just for entertainment
public class MybatisPlus extends Parent{
    private Stream<String> child = Files.lines(Paths.get("module/pom.xml"));
    { consumer.accept(child); }
}
```

| name | version |
| :---: | :---: |
| mybatis-plus | 3.0-RC2 |
| mysql | 6.0.6 |
| jdbc | 2.1.2.RELEASE |
| druid | 1.0.25 |
| freemarker | 2.3.29 |

---

## [demo-easy-excel](https://github.com/quaintclever/Java-SID/tree/master/demo-easy-excel)

```java
// Fake code, Just for entertainment
public class EasyExcel extends Parent{
    private Stream<String> child = Files.lines(Paths.get("module/pom.xml"));
    { consumer.accept(child); }
}
```

| name | version |
| :---: | :---: |
| easyexcel | 2.1.4 |
| thymeleaf | 2.1.2.RELEASE |

---

## [demo-security](https://github.com/quaintclever/Java-SID/tree/master/demo-security)

```java
// Fake code, Just for entertainment
public class Security extends Parent{
    private Stream<String> child = Files.lines(Paths.get("module/pom.xml"));
    { consumer.accept(child); }
}
```

| name | version |
| :---: | :---: |
| security | 2.1.2.RELEASE |
| thymeleaf | 2.1.2.RELEASE |

---

## [demo-wechat](https://github.com/quaintclever/Java-SID/tree/master/demo-wechat)

```java
// Fake code, Just for entertainment
public class WeChat extends MybatisPlus{
    private Stream<String> child = Files.lines(Paths.get("module/pom.xml"));
    { consumer.accept(child); }
}
```

| name | version |
| :---: | :---: |
| weixin-java-mp | 3.3.0 |
| weixin-java-miniapp | 3.3.0 |
| weixin-java-pay | 3.3.0 |
| weixin-java-open | 3.3.0 |

---

## [demo-elastic-search](https://github.com/quaintclever/Java-SID/tree/master/demo-elastic-search)

```java
// Fake code, Just for entertainment
public class ElasticSearch extends MybatisPlus{
    private Stream<String> child = Files.lines(Paths.get("module/pom.xml"));
    { consumer.accept(child); }
}
```

| name | version |
| :---: | :---: |
| elastic-search | 2.1.2.RELEASE |
| reactor-core | 3.1.6.RELEASE |
		 
springboot version comparison elasticsearch list:

| Spring Data Release Train | Spring Data Elasticsearch  |  Elasticsearch  | Spring Boot |
| --- | :---: | :---: | :---: |
| Moore | 3.2.x | 6.8.4 | 2.2.x |
| Lovelace | 3.1.x | **6.2.2** | **2.1.x** |
| Kay | 3.0.x | 5.5.0 | 2.0.x |
| Ingalls | 2.1.x | 2.4.0 | 1.5.x |


---


## [demo-mail](https://github.com/quaintclever/Java-SID/tree/master/demo-mail)

```java
// Fake code, Just for entertainment
public class Mail extends Parent{
    private Stream<String> child = Files.lines(Paths.get("module/pom.xml"));
    { consumer.accept(child); }
}
```

| name | version |
| :---: | :---: |
| mail | 2.1.2.RELEASE |
    

---


## [demo-mybatis](https://github.com/quaintclever/Java-SID/tree/master/demo-mybatis)

```java
// Fake code, Just for entertainment
public class Mybatis extends Parent{
    private Stream<String> child = Files.lines(Paths.get("module/pom.xml"));
    { consumer.accept(child); }
}
```

| name | version |
| :---: | :---: |
| mysql | 5.1.6 |
| mybatis | 1.3.1 |
| jdbc | 2.1.2.RELEASE |

