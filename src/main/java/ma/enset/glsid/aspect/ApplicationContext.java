package ma.enset.glsid.aspect;

public class ApplicationContext {
    public static boolean hasRole(String role) {
        // Simulation: "admin" is authorized
        return "admin".equals(role);
    }
}
