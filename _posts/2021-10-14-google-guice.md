---
layout: post
title: Jetty, Jersey & Guice
categories: [Java, Web Development]
---

# Introduction

### Jetty:
Jetty provides a web server that can run as an embedded container and integrates easily with the javax.servlet library.

```text
<dependency>
    <groupId>org.eclipse.jetty</groupId>
    <artifactId>jetty-server</artifactId>
    <version>9.4.3.v20170317</version>
</dependency>
<dependency>
    <groupId>org.eclipse.jetty</groupId>
    <artifactId>jetty-servlet</artifactId>
    <version>9.4.3.v20170317</version>
</dependency>
```

### Jersey
Jersey offers a set of classes and interfaces that simplify the creation of RESTful resources, handling HTTP requests and responses, and managing the lifecycle of your services
It is mainly used for creating Resource classes where-in we define the endpoints of the API.

### Google Guice

A lightweight and flexible dependency injection (DI) framework for Java. 
It works as follows:
- Create a Module: Define your bindings in a Guice module.
- Create an Injector: The injector is responsible for creating and providing objects based on the bindings defined in the module.   
- Inject Dependencies: Use the @Inject annotation to mark the points where dependencies should be injected.

```java
// Interface
interface Engine {
    void start();
}

// Implementation
class V8Engine implements Engine {
    @Override
    public void start() {
        System.out.println("V8 Engine started!");
    }
}

// Car class with injected dependency
class Car {
    private final Engine engine;

    @Inject
    public Car(Engine engine) {
        this.engine = engine;
    }

    public void drive() {
        engine.start();
    }
}

// Guice Module
class CarModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Engine.class).to(V8Engine.class); 
    }
}
```


# Implementation

Simple Jetty-Jersey Web Application, making use of Guice as DI

### Main Application Class
1. Initialises Guice Injector
2. Initialises Jetty Server & also Jersey framework

```java
public class TestApplication {
    public static void main(String[] args) throws Exception {

        // Guice Initialisation
        Injector injector = Guice.createInjector(
                new StoreProvider(),
                new JsonSerDeInitProvider());

        // Create embedded jetty container
        QueuedThreadPool threadPool = getWebserverThreadPool();
        threadPool.setName("JettyContainer");
        Server server = new Server(threadPool);

        ServerConnector connector = new ServerConnector(server);
        connector.setPort(9011);
        connector.setName("JalazTestApplication");
        server.addConnector(connector);

        ContextHandlerCollection contexts = new ContextHandlerCollection();

        ResourceView resourceView = injector.getInstance(ResourceView.class);
        ResourceConfig resourceConfig = new ResourceConfig()
                .register(resourceView);

        ServletContextHandler contextDefault = new ServletContextHandler();
        contextDefault.setContextPath("/");
        contextDefault.addServlet(new ServletHolder(new ServletContainer(resourceConfig)), "/*");
        contextDefault.setVirtualHosts(new String[]{"@JalazTestApplication"});

        contexts.addHandler(contextDefault);
        server.setHandler(contexts);

        try {
            server.start();
            server.join();
        } finally {
            server.stop();
        }

    }

    private static QueuedThreadPool getWebserverThreadPool() {
        int minThreads = 10;
        int maxThreads = 20;
        int idleTimeoutMs = 10000;
        int maxQueuedRequests = 100;

        return new QueuedThreadPool(maxThreads, minThreads, idleTimeoutMs, new ArrayBlockingQueue<>(maxQueuedRequests));
    }
}
```

### Resource Views
Captures the Endpoint definitions

```java
@Path("/jalaz/guice")
@Singleton
public class ResourceView {

    @Inject
    private JsonSeDe jsonSeDe;

    @Inject
    @Named("QUERY_STORE_MAPPER")
    private Map<String, String> q2sMap;

    @GET
    @Path("getstore")
    @Produces(MediaType.APPLICATION_JSON)
    public Response modelDebug(@QueryParam("query") String query) {

        ResponsePojo responsePojo = new ResponsePojo();
        String storeId = q2sMap.getOrDefault(query, "DEFAULT");
        if (storeId.equals("DEFAULT")) {
            responsePojo.setMessage("NOT FOUND");
        } else {
            responsePojo.setMessage("Success");
            responsePojo.setStore(storeId);
        }

        return Response
                .status(Response.Status.OK)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .entity(jsonSeDe.writeValueAsString(responsePojo)).build();

    }
}
```

### DI Implementations

```java
@AllArgsConstructor
public class JsonSeDe {
    private static JsonSeDe instance;

    private ObjectMapper objectMapper;

    public String writeValueAsString(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException("Unable to serialize the object: " + object, e);
        }
    }

    public <T> T readValue(String json, TypeReference<T> typeReference) {
        try {
            return objectMapper.readValue(json, typeReference);
        } catch (Exception e) {
            throw new RuntimeException("Unable to de-serialize the json: " + json, e);
        }
    }

    public <T> T readValue(String json, Class<T> klass) {
        try {
            return objectMapper.readValue(json, klass);
        } catch (Exception e) {
            throw new RuntimeException("Unable to de-serialize the json: " + json, e);
        }
    }

    public static JsonSeDe getInstance() {
        if (instance == null) {
            synchronized (JsonSeDe.class) {
                if (instance == null) {
                    ObjectMapper objectMapper = new ObjectMapper();
                    objectMapper.setVisibility(objectMapper.getSerializationConfig()
                            .getDefaultVisibilityChecker()
                            .withFieldVisibility(JsonAutoDetect.Visibility.ANY)
                            .withGetterVisibility(JsonAutoDetect.Visibility.NONE)
                            .withSetterVisibility(JsonAutoDetect.Visibility.NONE)
                            .withCreatorVisibility(JsonAutoDetect.Visibility.NONE));
                    objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
                    objectMapper.enable(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL);
                    objectMapper.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
                    instance = new JsonSeDe(objectMapper);
                }
            }
        }
        return instance;
    }
}
```

```java
public class JsonSerDeInitProvider extends AbstractModule {

    @Override
    protected void configure() {}

    @Provides
    @Singleton
    JsonSeDe getJsonSerde(){
        return JsonSeDe.getInstance();
    }
}
```

```java
public class StoreProvider extends AbstractModule {

    @Override
    protected void configure() {}

    @Provides
    @Singleton
    @Named("QUERY_STORE_MAPPER")
    public Map<String, String> getQ2SMap() {
        return ImmutableMap.of("mobiles", "electronics", "shoes", "lifestyle", "jeans", "lifestyle");
    }
}
```
