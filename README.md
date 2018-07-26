# Spring microservices config client

* create a file called `bootstrap.properties` in `resources` folder
* `bootstrap.properties` is the same as `application.properties` but will be loaded earlier in the application initalization

  ```properties
    spring.profiles.active=development
    spring.application.name=config-client
    spring.cloud.config.uri=http://localhost:8888
  ```

* combining application name and active profile _config-client-development_ is how config-client determine which file consult to the repository that is served by config-server
* `spring.cloud.config.uri` has the uri to the server config

* log of config-client when started

  ```
    c.c.c.ConfigServicePropertySourceLocator : Fetching config from server at : http://localhost:8888
    o.s.web.servlet.DispatcherServlet        : FrameworkServlet 'dispatcherServlet': initialization completed in 10 ms
    c.c.c.ConfigServicePropertySourceLocator : Located environment: name=config-client, profiles=[development], label=null, version=522655d21124536f1769540f380c1a8a638c7d10, state=null
  ```

* Hitting url `http://localhost:8080/message` will return value of `message` property in `config-client-development.properties`
 
* To refresh configuration values on the fly is necessary to add annotation `@RefreshScope` and after a value modification
and commit in the configuration properties file use the _Actuator Refresh API_ `curl --data "" http://localhost:8080/refresh` 
or `curl -X POST http://localhost:8080/actuator/refresh -d {} -H "Content-Type: application/json"`
  * to enable `Actuator's` endpoints in `application.properties`:
    * `management.endpoints.web.exposure.include=*` will enable all endpoints in `application.yml` use `"*"`
    * `management.endpoints.web.exposure.include=refresh,health,info` will enable specific endpoints
  * `Actuator` allow to do modification of properties inside the application 
