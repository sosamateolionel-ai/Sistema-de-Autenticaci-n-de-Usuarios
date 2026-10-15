public interface MetodoAutenticacion {

    boolean autenticar(String credencialPrincipal, String credencialSecundaria);

    String getNombre();

}