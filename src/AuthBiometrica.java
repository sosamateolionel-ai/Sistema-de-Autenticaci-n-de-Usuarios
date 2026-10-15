import java.util.HashMap;
import java.util.Map;

public class AuthBiometrica implements MetodoAutenticacion {

    private Map<String, String> huellas; // usuario -> huella registrada

    public AuthBiometrica() {
        this.huellas = new HashMap<>();
    }

    public void registrarHuella(String usuario, String huella) {
        huellas.put(usuario, huella);
    }

    @Override
    public boolean autenticar(String usuario, String huellaLeida) {

        if (!huellas.containsKey(usuario)) {
            System.out.println("[Biometría] No hay huella registrada para " + usuario + ".");
            return false;
        }

        if (!huellas.get(usuario).equals(huellaLeida)) {
            System.out.println("[Biometría] La huella no coincide con la registrada para " + usuario + ".");
            return false;
        }

        System.out.println("[Biometría] Huella reconocida para " + usuario + ".");
        return true;
    }

    @Override
    public String getNombre() {
        return "huella dactilar";
    }
}