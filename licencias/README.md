# PO-Avanzados
Proyecto Integrador desarrollado por Nicolas Gaston Rodriguez Perez, referente a la materia "Técnicas Avanzadas de Programación" de la Universidad de Palermo.

En el repositorio se encuentran todos los archivos y librerías utilizadas para realizar el proyecto.

# Sobre el Proyecto Integrador

Se requiere desarrollar un sistema de gestión de licencias de conducir, el cual debe permitir a los usuarios del sistema solicitar turnos para realizar los trámites correspondientes con la misma.

Por lo tanto, el sistema debe contar con distintos usuarios en los cuales se van a diferenciar en cuanto a sus roles que pueden ejercer dentro del sistema. Para esto habrá inicialmente tres roles distintos: Administrador, Empleado y Usuario Regular, siendo el usuario regular un ciudadano que desea realizar un trámite de su licencia.

El sistema debe permitir al usuario administrador poder crear usuarios y modificarlos, así como los distintos lugares de atención al cliente. El rol empleado es el encargado de actualizar el estado del trámite de la licencia, lo que va a permitir al usuario regular visualizar el estado de su trámite.

Teniendo en cuenta esto, el sistema debe permitir al usuario regular visualizar el estado de su licencia, y dependiendo de su estado, permitir al usuario solicitar un turno para iniciar el trámite o para retirar la licencia ya impresa.

A la hora de solicitar un turno, el sistema debe permitir al usuario elegir la fecha en la cual quiere solicitar el turno. Al momento de registrar el turno, el sistema debe verificar que haya cupos disponibles en ese día, horario y sede elegido previamente.

Además, el sistema debe validar si el usuario está en un trámite de licencia actualmente, ya que si es así, no debe permitir al usuario solicitar más turnos hasta que el último trámite se haya marcado como “Listo” lo cual significa que la licencia ha sido impresa o, que haya sido marcada como “Denegada” significando así que la licencia no ha sido aprobada por un empleado.

El sistema debe contar con una herramienta que permita visualizar estadisticas de los tramites de licencias y exportarlos como un archivo csv con el objetivo de que luego pueda usarse como dataset a la hora de realizar analisis de datos.


# Contenido del Repositorio

## src/main/java/com

Aquí encontrarás todo el código relacionado con los modelos y las diferentes clases utilizadas en todo el sistema, así como las relativas a la capa de servicios.

## src/main/java/fxml

Aquí encontrarás todos los controladores de los documentos FXML relacionados con el front-end.

## src/main/main/resources/fxml/

Aquí encontrarás todos los archivos FXML que contienen el código de cada uno de los elementos que conciernen a la interfaz de usuario.

## src/main/main/resources/fxml/static

Aquí se encuentran todos los archivos estáticos dentro del sistema, como imágenes, archivos css e iconos.


# Ejemplos Ilustrativos

<h3> Login </h3>

![Login](https://github.com/nicoorodriguezp/tap/blob/main/licencias/src/main/resources/static/images/examples/login.png)

<h3> Create User </h3>

![Create-User](https://github.com/nicoorodriguezp/tap/blob/main/licencias/src/main/resources/static/images/examples/create_user.png)

<h3> Home </h3>

![Home](https://github.com/nicoorodriguezp/tap/blob/main/licencias/src/main/resources/static/images/examples/home.png)

<h3> User Licences </h3>

![User-Licences](https://github.com/nicoorodriguezp/tap/blob/main/licencias/src/main/resources/static/images/examples/user_licences.png)

<h3> Create Appointment </h3>

![Create-Appointment](https://github.com/nicoorodriguezp/tap/blob/main/licencias/src/main/resources/static/images/examples/appointment.png)

<h3> User Appointments </h3>

![User-Appointments](https://github.com/nicoorodriguezp/tap/blob/main/licencias/src/main/resources/static/images/examples/user_appointments.png)

<h3> Licences in Process </h3>

![Licences-in-Process](https://github.com/nicoorodriguezp/tap/blob/main/licencias/src/main/resources/static/images/examples/licences_in_process.png)

<h3> Updating Licence </h3>

![Updating-Licence](https://github.com/nicoorodriguezp/tap/blob/main/licencias/src/main/resources/static/images/examples/updating_licence.png)

<h3> Licence Update </h3>

![Licence-Update](https://github.com/nicoorodriguezp/tap/blob/main/licencias/src/main/resources/static/images/examples/licence_update.png)

<h3> User List </h3>

![User-List](https://github.com/nicoorodriguezp/tap/blob/main/licencias/src/main/resources/static/images/examples/user_list.png)

<h3> Create User Admin </h3>

![Create-User-Admin](https://github.com/nicoorodriguezp/tap/blob/main/licencias/src/main/resources/static/images/examples/create_user_admin.png)

<h3> Headquarters List </h3>

![Headquarters-List](https://github.com/nicoorodriguezp/tap/blob/main/licencias/src/main/resources/static/images/examples/headquarters_list.png)

<h3> Headquarters Update </h3>

![Headquarters-Update](https://github.com/nicoorodriguezp/tap/blob/main/licencias/src/main/resources/static/images/examples/update_headquater.png)

<h3> Report - csv </h3>

![Headquarters-Update](https://github.com/nicoorodriguezp/tap/blob/main/licencias/src/main/resources/static/images/examples/statics.png)


<h3> Statics </h3>

![Headquarters-Update](https://github.com/nicoorodriguezp/tap/blob/main/licencias/src/main/resources/static/images/examples/statics.png)


<h3> Integration Test </h3>

![Integration-Test](https://github.com/nicoorodriguezp/tap/blob/main/licencias/src/main/resources/static/images/examples/test.png)
