import java.util.HashMap;
import java.util.Map;

public class AuthPassword implements MetodoAutenticacion {

    private Map<String, String> usuarios; // usuario -> contraseña

    public AuthPassword() {
        this.usuarios = new HashMap<>();
    }

    public void registrarUsuario(String usuario, String password) {
        usuarios.put(usuario, password);
    }

    @Override
    public boolean autenticar(String usuario, String password) {

        if (usuario == null || usuario.trim().isEmpty() || password == null || password.isEmpty()) {
            System.out.println("[Password] Error: usuario y contraseña son obligatorios.");
            return false;
        }

        if (!usuarios.containsKey(usuario)) {
            System.out.println("[Password] El usuario \"" + usuario + "\" no existe.");
            return false;
        }

        if (!usuarios.get(usuario).equals(password)) {
            System.out.println("[Password] Contraseña incorrecta para " + usuario + ".");
            return false;
        }

        System.out.println("[Password] Credenciales válidas para " + usuario + ".");
        return true;
    }

    @Override
    public String getNombre() {
        return "usuario y contraseña";
    }
}