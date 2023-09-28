1. @Controller + @ResponseBody(@RestController)
    -> Bean + Web Layer
2. @Service
    -> Bean
3. @Repository (JPA + Hibernate)
    -> Define Entity (with @Id & implements Serializable)
    -> Bean
    -> JPA include basic CRUD Operations
    -> method name rules for Hibernate generating implementations
    -> @Query -> JPQL (Entity) or Native SQL
    -> nativeQuery = True
    -> Isolate the Database implementation and Hibernate (MySQL, Oracle)
4. @Configuration (Class Annotation)
    -> @Bean (Method Annotation) -> Create Bean by Method
5. What is Dependency Injection (DI)?
    -> @Autowired
      -> field injection (@Autowired on a field)
      -> constructor injection (@Autowired on a constructor)
    -> Controller depends on Service (because controller autowired service)
    -> AppConfig class depends on yml (Because it used @Value)
6. What is IoC (Inversion of Control)
    -> Java: use new keyword to create object. You are the only one to control
      the relationship between objects.
    -> Spring or SpringBoot : Application Context plays a role of managing the dependency
      between objects. It complains if any missing dependency was found when server starts.

7. RestTemplate
    -> getForObject
    -> UriComponentsBuilder (woth yml, @Value)
    -> Define the return type (Object or Array)
8. model class (DTO)
    -> lombok
    -> ModelMapper
    -> mapper class
9. @Scheduled
    -> @FixRate, @FixDelay, @Scheduled(cron = 2)
10. CommandLineRunner(Interface)
    -> implements run method
    -> @Component
    -> this method will be executed during server start
    -> Server start will fail if the run method fail
11. ApiResonpse<T> 
    -> generics of data
12. Custom Exception class (extends Exception.class)
    -> BusinessException

13. DTO
    -> Deserialization (Controller RequestBody: from JSON to Object)
    -> Serialization (Controller ResponseBody: from Object to JSON)
    -> ObjectMapper (test code)


14. GlobalExceptionHandler
  -> @ControllerAdvise (@RestControllerAdvise)
  -> @ExceptionHandler (method)
  -> Catch from child to parent (includes runtime, checked exception)

15. test code
    -> By Environment & Layer
      -> @Test, @SpringBootTest
      -> Web Layer
        -> @WebMvcTest (Controller Only)
        -> Autowired MockMvc
        -> @MockBean for Service (Controller Autowired Service)
        -> Mockito, when & thenReturn for MockBean's method
        -> mockMvc, perform()
        -> Hamcrest (assertThat)
      -> Service Layer
        -> @SpringBootTest
        -> @MockBean for Repository (Service autowired Repository)
        -> Mockito, when & thenReturn for MockBean's method
        -> @Mock, @InjectMock -> mock normal java class & method
        -> Hamcrest (assertThat)
      -> Repository Layer
        -> @DataJpaLayer
        -> Autowired TestEntityManager
        -> Autowired repository
        -> TestEntityManager.persist()
        -> repository.save(), findById() -> test Hibernate