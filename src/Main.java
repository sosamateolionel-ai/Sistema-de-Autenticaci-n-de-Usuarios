//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {
    AuthPassword password = new AuthPassword();
    password.registrarUsuario("lucia", "clave123");

    AuthBiometrica biometrica = new AuthBiometrica();
    biometrica.registrarHuella("lucia", "HUELLA-A1B2");

    AuthTokenOAuth oauth = new AuthTokenOAuth();

    ServicioLogin servicio = new ServicioLogin(password);

    System.out.println("===== Usuario y contraseña =====");
    servicio.iniciarSesion("lucia", "clave123");      // exitoso
    servicio.iniciarSesion("lucia", "incorrecta");    // fallido

    System.out.println("\n===== Cambio a huella dactilar =====");
    servicio.setMetodo(biometrica);
    servicio.iniciarSesion("lucia", "HUELLA-A1B2");   // exitoso
    servicio.iniciarSesion("lucia", "HUELLA-XXXX");   // fallido

    System.out.println("\n===== Cambio a OAuth =====");
    servicio.setMetodo(oauth);
    servicio.iniciarSesion("google", "google_8f3a9c2b71d4");  // exitoso
    servicio.iniciarSesion("facebook", "facebook_123456789"); // proveedor no soportado
    servicio.iniciarSesion("github", "github_abc");           // token demasiado corto

    System.out.println("\n===== Bloqueo por intentos fallidos =====");
    ServicioLogin servicioBloqueo = new ServicioLogin(password);
    servicioBloqueo.iniciarSesion("lucia", "a");
    servicioBloqueo.iniciarSesion("lucia", "b");
    servicioBloqueo.iniciarSesion("lucia", "c");
    servicioBloqueo.iniciarSesion("lucia", "clave123");
}
