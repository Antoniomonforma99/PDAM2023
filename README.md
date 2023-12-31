# TuKomida

![Logo tuKomida](logo.PNG)

TuKomida es un proyecto pensado para poder gestionar el día a día en un establecimiento de hostelería, estas funcionalidades serán descritas más abajo.

La API Rest ha sido desarrollada en Java y haciendo uso de Spring, Spring Data JPA, Spring Security, JWT para la gestión de tokens, entre otros. Cuenta con dos perfiles, uno para desarrollo, con uns base de datos h2, y otro para producción con una base de datos PostgreSQL.

El proyecto se apoya en dos aplicaciones frontend. Vue 3 para el frontend web de los administradores y Flutter para el frontend móvil para los usuarios

Los permisos están definidos mediante roles, tendremos USER, para la aplicación móvil y ADMIN para la aplicación de administración.


## FrontEnd Web
Para desarrollar la aplicación web para los administradores se ha hecho uso de Vue 3 y Vite, trabajando con Axios para el tema de peticiones.

Dentro del panel de administración podrás:
```
-Hacer login mediante un user y un password
-Editar la información de un user, cambiar su contraseña.
-Productos: listar todos, obtener un producto a través de su UUID, editarlo, eliminarlo.
-Categorías: listar todas, obtener una a través de su UUID, editarla, eliminarla,
añadir un producto a una categoría y eliminarlo.
```
Para levantar el proyecto web haremos uso del comando 'npm run dev'

## FrontEnd Móvil
Para desarrollar la aplicación web para los usuarios se ha hecho uso de Flutter y el patrón BLOC.

## Prototipado en figma

- [Frontene movil]https://www.figma.com/file/upATXYdSVJiLQDJt9lgj3m/TuKomida?type=design&node-id=0%3A1&mode=design&t=ZGrTmdgLJu8BvPGL-1

 
## URL base

- http://localhost:8080

## Documentación de la API

- [SwaggerUI](http://localhost:8080/swagger-ui/index.html)

## Gestión de las tareas del proyecto usando GitHub Projects

- https://github.com/users/Antoniomonforma99/projects/6

## ENDPOINTS

### USER

```
GET /me -> Se obtiene los datos del usuario logado.

POST /auth/login -> Se logea un usuario.

POST /auth/login/admin -> Se logea un usuario teniendo que ser admin.

POST /auth/register -> Se resgistra un usuario como usuario.

POST /auth/register/admin -> Se resgistra un usuario como administrador.

PUT /user/changePassword -> Se cambia la contraseña del usuario logado.

DELETE /user/deleteAccount -> Se borra la cuenta del usuario.
```

### PRODUCTO

```
GET /producto/ -> Obtiene todos los productos.

GET /producto/{id} -> Obtiene el detalle de un producto.

POST /producto/new -> Crea un producto nuevo.

PUT /producto/{id} -> Se modifica un producto.

DELETE /producto/{id} -> Se borra un producto.

POST /producto/{id}/addCategoria/{idCategoria} -> Se añade una categoría a un producto.

DELETE /producto/{id}/deleteCategoria/{idCategoria} -> Se borra una categoría a un producto.
```

### FILE

```
GET /download/{filename} ->Obtiene un archivo.
```

### CATEGORÍA

```
GET /categoria/ -> Obtiene todos las categorías.

GET /categoria/{id} -> Obtiene el detalle de una categoría.

POST /categoria/new -> Crea una categoría nueva.

PUT /categoria/{id} -> Se modifica una categoría.

DELETE /categoria/{id} -> Se borra una categoría.
```

### DIRECCIÓN DE ENVÍO
 ```
GET /direccionEnvio/ -> Obtiene todos las direcciones de envío.

GET /direccionEnvio/{id} -> Obtiene el detalle de una dirección de envío.

POST /direccionEnvio/new -> Crea una dirección de envío nueva.

PUT /direccionEnvio/{id} -> Se modifica una dirección de envío.

DELETE /direccionEnvio/{id} -> Se borra una dirección de envío.
```

### USUARIO

Varios usuarios de prueba para testear el funcionamiento, según su rol:

ADMIN:
tukomidadmin@gmail.com contraseña 1234

USER
tukomidauser@gmail.com contraseña 1234





