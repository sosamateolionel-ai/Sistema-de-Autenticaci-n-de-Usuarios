public class ServicioLogin {

    private static final int MAX_INTENTOS = 3;

    private MetodoAutenticacion metodo; // referencia a la ABSTRACCIÓN
    private int intentosFallidos;

    public ServicioLogin(MetodoAutenticacion metodo) {
        this.metodo = metodo;
        this.intentosFallidos = 0;
    }

    public void setMetodo(MetodoAutenticacion metodo) {
        this.metodo = metodo;
    }

    public boolean iniciarSesion(String credencialPrincipal, String credencialSecundaria) {

        if (intentosFallidos >= MAX_INTENTOS) {
            System.out.println("\nServicio: cuenta bloqueada tras " + MAX_INTENTOS
                    + " intentos fallidos, no se procesa el pedido.");
            return false;
        }

        System.out.println("\n>>> Login con " + metodo.getNombre() + " (" + credencialPrincipal + ")");

        boolean autenticado = metodo.autenticar(credencialPrincipal, credencialSecundaria);

        if (autenticado) {
            intentosFallidos = 0;
            System.out.println("Acceso concedido: sesión iniciada para " + credencialPrincipal + ".");
        } else {
            intentosFallidos++;
            System.out.println("Acceso denegado (intento fallido " + intentosFallidos + " de " + MAX_INTENTOS + ").");
        }

        return autenticado;
    }
}