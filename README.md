# spring-basics

A minimal Spring Framework project exploring the different ways to configure a
Spring `ApplicationContext` and wire up dependency injection. Each configuration
style lives on its own branch so they can be compared side by side.

## Configuration styles

Spring supports three equivalent ways to declare beans and wire dependencies.
This repo demonstrates them one at a time:

| Style | Branch | Status |
|---|---|---|
| XML-based configuration | `feature/spring-xml-configuration` | done |
| Annotation-based configuration | `annotation-based-configuration` | done |
| Java-based configuration (`@Configuration` / `@Bean`) | — | planned |

### XML-based configuration (`feature/spring-xml-configuration`)

Beans are declared explicitly in XML files, and the context is loaded from
them via `ClassPathXmlApplicationContext`:

```java
ApplicationContext context = new ClassPathXmlApplicationContext(
        "services.xml", "repository.xml");
UserService service = context.getBean(UserService.class);
```

`src/main/resources/repository.xml` declares repository beans:

```xml
<bean id="userRepository" class="org.example.repository.impl.UserRepositoryImpl"/>
<bean id="paymentRepository"
      class="org.example.repository.impl.PaymentRepositoryImpl"
      lazy-init="true"/>
```

`src/main/resources/services.xml` declares service beans and wires their
dependencies by referencing the bean ids above — `constructor-arg` for
constructor injection, `property` for setter injection:

```xml
<bean id="userService" class="org.example.service.UserService">
    <constructor-arg ref="userRepository"/>
</bean>

<bean id="paymentService" class="org.example.service.PaymentService" lazy-init="true">
    <property name="paymentRepository" ref="paymentRepository"/>
</bean>
```

Wiring is centralized in XML, fully decoupled from the Java classes
themselves — no annotations are needed on `UserRepositoryImpl`, `UserService`,
etc.

### Annotation-based configuration (`annotation-based-configuration`)

Beans are declared directly on the classes via stereotype annotations, and
the context is built by scanning a base package instead of reading config
files:

```java
ApplicationContext context = new AnnotationConfigApplicationContext("org.example");
UserService service = context.getBean(UserService.class);
```

- `@Repository` on `UserRepositoryImpl` registers it as a bean for the
  persistence layer.
- `@Service` on `UserService` registers it as a bean for the service layer;
  its constructor parameter of type `UserRepository` is injected
  automatically (constructor-based DI) — no explicit wiring needed.

```java
@Repository
public class UserRepositoryImpl implements UserRepository { ... }

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }
}
```

Wiring lives next to the classes it configures, and `AnnotationConfigApplicationContext`
discovers it automatically via classpath scanning.

### Java-based configuration (planned)

A future branch will add a `@Configuration` class with `@Bean` methods,
giving explicit, type-safe, XML-free bean definitions — e.g.:

```java
@Configuration
public class AppConfig {
    @Bean
    public UserRepository userRepository() {
        return new UserRepositoryImpl();
    }

    @Bean
    public UserService userService(UserRepository repository) {
        return new UserService(repository);
    }
}
```

loaded via `AnnotationConfigApplicationContext(AppConfig.class)`. This section
will be filled in once that branch lands.
