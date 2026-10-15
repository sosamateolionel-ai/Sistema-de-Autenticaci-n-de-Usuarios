# Sistema de Autenticación de Usuarios

## Descripción

Este proyecto consiste en un sistema de autenticación de usuarios desarrollado en Java. Permite simular el inicio de sesión mediante tres mecanismos diferentes: usuario y contraseña, huella dactilar y token OAuth.

El objetivo principal es aplicar interfaces, polimorfismo y desacoplamiento, permitiendo cambiar el método de autenticación dinámicamente y gestionar los intentos fallidos de inicio de sesión.

## Funcionamiento

### Interfaz `MetodoAutenticacion`

Define el contrato común que deben implementar todos los mecanismos de autenticación.

* `autenticar(String credencialPrincipal, String credencialSecundaria)`: valida las credenciales y devuelve `true` si son aceptadas o `false` si son rechazadas.
* `getNombre()`: devuelve el nombre del método de autenticación utilizado.

### Clase `AuthPassword`

Implementa la autenticación mediante usuario y contraseña.

* Almacena los usuarios y sus contraseñas en un `HashMap`.
* Permite registrar usuarios mediante `registrarUsuario()`.
* Comprueba que las credenciales no estén vacías.
* Verifica que el usuario exista y que la contraseña coincida con la registrada.

### Clase `AuthBiometrica`

Implementa la autenticación mediante huella dactilar simulada.

* Almacena las huellas registradas en un `HashMap`.
* Permite registrar una huella para cada usuario mediante `registrarHuella()`.
* Comprueba que exista una huella registrada para el usuario.
* Compara la huella recibida con la almacenada.

### Clase `AuthTokenOAuth`

Implementa una simulación de autenticación mediante tokens OAuth.

* Admite únicamente los proveedores `google` y `github`.
* Comprueba que el token comience con el nombre del proveedor seguido de un guion bajo.
* Verifica que el token tenga al menos 12 caracteres.
* Rechaza los proveedores no admitidos y los tokens que no cumplen las condiciones.

**Importante:** la validación OAuth es una simulación educativa. No verifica tokens reales, firmas, vencimientos ni identidad mediante un proveedor externo.

### Clase `ServicioLogin`

Administra el proceso de inicio de sesión mediante una referencia a la interfaz `MetodoAutenticacion`.

Sus métodos principales son:

* `iniciarSesion(String credencialPrincipal, String credencialSecundaria)`: solicita la validación al método seleccionado y muestra si el acceso fue concedido o denegado.
* `setMetodo(MetodoAutenticacion metodo)`: permite cambiar el mecanismo de autenticación durante la ejecución.

El servicio también controla los intentos fallidos:

* Permite hasta tres intentos fallidos consecutivos.
* Incrementa el contador cuando la autenticación falla.
* Reinicia el contador cuando la autenticación tiene éxito.
* Bloquea nuevos intentos cuando se alcanzan tres fallos consecutivos.

### Clase `Main`

Contiene el método principal del programa y realiza pruebas con los tres mecanismos de autenticación.

1. Registra un usuario con contraseña.
2. Registra una huella dactilar simulada.
3. Prueba accesos correctos e incorrectos con usuario y contraseña.
4. Cambia a la autenticación biométrica y realiza nuevas pruebas.
5. Cambia a OAuth y prueba distintos proveedores y tokens.
6. Crea un servicio independiente para comprobar el bloqueo después de tres intentos fallidos.

## Ejemplo de funcionamiento

Durante la ejecución, la consola muestra los resultados de cada autenticación.

* **Usuario y contraseña:** el acceso con `lucia` y `clave123` es aceptado; la contraseña incorrecta es rechazada.
* **Huella dactilar:** la huella registrada es aceptada y una huella diferente es rechazada.
* **OAuth:** el token de Google cumple las reglas simuladas; Facebook es rechazado por no estar admitido y el token corto de GitHub es rechazado.
* **Bloqueo:** después de tres intentos fallidos consecutivos, el servicio rechaza un nuevo intento aunque las credenciales sean correctas.

El bloqueo se mantiene en la instancia de `ServicioLogin` que alcanzó el límite. El servicio independiente creado para la última prueba tiene su propio contador.

## Conceptos de programación utilizados

* **Interfaces:** establecen un contrato común para los métodos de autenticación.
* **Polimorfismo:** permite utilizar distintos mecanismos mediante una referencia de tipo `MetodoAutenticacion`.
* **Abstracción:** `ServicioLogin` no depende de una clase concreta de autenticación.
* **Desacoplamiento:** permite agregar nuevos mecanismos sin modificar la lógica principal del servicio.
* **Encapsulamiento:** los atributos de las clases se mantienen privados cuando corresponde.
* **Colecciones:** se utilizan `HashMap` para almacenar usuarios y huellas, y una lista para los proveedores OAuth permitidos.
* **Sobrescritura de métodos:** se utiliza `@Override` para implementar los métodos de la interfaz.
* **Validación de credenciales:** se comprueban usuarios, contraseñas, huellas y tokens.
* **Control de intentos:** se utiliza una constante para establecer el máximo de fallos permitidos.
* **Cambio dinámico de comportamiento:** `setMetodo()` permite seleccionar otro mecanismo durante la ejecución.

## Tecnologías utilizadas

* Java.
* Programación Orientada a Objetos (POO).
* Colecciones de Java (`Map`, `HashMap`, `List` y `Arrays`).
* IntelliJ IDEA como entorno de desarrollo.

## Ejecución

<img width="657" height="705" alt="image" src="https://github.com/user-attachments/assets/612a0475-8956-491a-a13a-a0c666c9f395" />
<img width="802" height="696" alt="image" src="https://github.com/user-attachments/assets/b70fc291-23fb-4b4e-bd30-c56d2cb357d4" />

## Conclusión

Este ejercicio demuestra cómo utilizar interfaces y polimorfismo para crear un sistema de autenticación flexible. La lógica de inicio de sesión y el control de intentos se concentran en `ServicioLogin`, mientras que cada mecanismo implementa sus propias reglas de validación.
