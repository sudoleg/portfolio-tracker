# Portfolio Manager

I created this portfolio tracker to practice my skills and gain more experience in web development, particularly in the development of RESTful APIs with SpringBoot and UIs with Angular (17). This (very) basic portfolio tracker allows a user to create multiple portfolios, add and remove stocks to/from them. Portfolios may also be renamed and deleted.

## Run the app - installation

```bash
chmod +x start-app.sh
./start-app.sh
```

This will build and start the containers for the Postgres DB and the backend. Afterwards it installs npm dependencies and starts the frontend via npm. (There is an issue I'm working on, therefore the frontend is not yet dockerized.) You can then access the frontend on <http://localhost:4200>. Impersonate a user to start. The DB already has some initial data.

### API docs

- OpenAPI descriptions are available under <http://localhost:8080/api/v1/api-docs>
- The Swagger UI is available under <http://localhost:8080/swagger.html>

## Used technologies

- Backend: SpringBoot
- Frontend: Angular v17

## Used tutorials

- [Ultimate Guide to SpringBoot](https://youtu.be/Nv2DERaMx-4?si=FHX_haHS5XoMwo3i)
- [PostgreSQL & pgAdmin with docker-compose](https://github.com/khezen/compose-postgres/tree/master)
- [Custom REST-API error handling in SpringBoot](https://www.toptal.com/java/spring-boot-rest-api-error-handling)
