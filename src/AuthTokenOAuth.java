import java.util.Arrays;
import java.util.List;

public class AuthTokenOAuth implements MetodoAutenticacion {

    private List<String> proveedoresPermitidos = Arrays.asList("google", "github");

    @Override
    public boolean autenticar(String proveedor, String token) {

        if (!proveedoresPermitidos.contains(proveedor)) {
            System.out.println("[OAuth] Proveedor \"" + proveedor + "\" no soportado.");
            return false;
        }

        // Regla simulada: el token debe empezar con "proveedor_" y tener al menos 12 caracteres
        if (token == null || !token.startsWith(proveedor + "_") || token.length() < 12) {
            System.out.println("[OAuth] Token rechazado: formato inválido o expirado.");
            return false;
        }

        System.out.println("[OAuth] Token válido emitido por " + proveedor + ".");
        return true;
    }

    @Override
    public String getNombre() {
        return "token OAuth";
    }
}