# 🗓️ Proyecto: Agenda Interactiva

¡Bienvenido/a! Este repositorio contiene una **agenda interactiva** desarrollada utilizando **Java**. La aplicación permite a los usuarios gestionar contactos de manera eficiente, con funcionalidades para agregar, buscar, eliminar y visualizar contactos. Además, los usuarios pueden crear cuentas, iniciar sesión y guardar sus agendas de manera persistente mediante serialización de objetos.

---

### ✨ Descripción del Proyecto

**Agenda Interactiva** es una aplicación que permite a los usuarios gestionar su lista de contactos. Los usuarios pueden registrar nuevos contactos, buscar, eliminar y ver los existentes. Los datos se almacenan de forma persistente utilizando la serialización de objetos, lo que garantiza que la agenda se mantenga entre sesiones.

- **Gestión de Contactos:** Los usuarios pueden registrar, buscar y eliminar contactos fácilmente.
- **Autenticación de Usuario:** Los usuarios deben registrarse e iniciar sesión para acceder a sus agendas personales.
- **Persistencia de Datos:** La agenda y los contactos se guardan automáticamente utilizando la serialización de objetos.

---

### 🛠️ Estructura del Proyecto

1. **`AgendaUsuario.java`**: Lógica principal de la agenda, que maneja las operaciones de los contactos (registrar, buscar, eliminar, ver).
2. **`Contacto.java`**: Clase que representa a cada contacto, con atributos como nombre, apellidos, teléfono, email y edad.
3. **`ExcepcionNombre.java`**: Excepción personalizada que se utiliza para validar los datos introducidos (como el email).
4. **`MainAgenda.java`**: Clase principal que ejecuta la aplicación y permite la interacción con el usuario.

---

### 🚀 Ejecución del Proyecto

1. **Clonar el repositorio**:
   ```bash
   git clone https://github.com/DannaPatricia/agenda.git
2. **Clonar el repositorio**:
   ```bash
    cd agenda
3. **Abrir el proyecto en tu IDE (como IntelliJ IDEA, Eclipse, etc.) y ejecutar el archivo MainAgenda.java para comenzar.**:

---

## 🎨 Capturas de Pantalla

(Pendiente)

---

## 🤝 Contribuciones

1. Haz un fork del repositorio.
2. Crea una rama para tu funcionalidad:
   ```bash
    git checkout -b mejora-nueva-funcion
3. Realiza los cambios y haz commmit:
   ```bash
   git commit -m "Añadida nueva función X"
4. Haz push a tu rama:
   ```bash
    git push origin mejora-nueva-funcion
5. Crea un pull request desde GitHub.

---

## 📜 Licencia
Este proyecto está bajo la Licencia MIT. Puedes consultar más detalles en el archivo LICENSE.

---

¡Gracias por tu interés en este proyecto! 🚀
