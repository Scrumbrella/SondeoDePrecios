package Modelo;

/**
 *
 * @author Nahum
 */
public class Usuario {
    private int idUsuario;
    private String nombre1;
    private String apellido1;
    private String email;
    private String pass;

    public Usuario() {
    }

    public Usuario(int idUsuario, String nombre1, String apellido1, String email, String pass) {
        this.idUsuario = idUsuario;
        this.nombre1 = nombre1;
        this.apellido1 = apellido1;
        this.email = email;
        this.pass = pass;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre1() {
        return nombre1;
    }

    public void setNombre1(String nombre1) {
        this.nombre1 = nombre1;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
    
    
}
