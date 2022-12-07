package mx.uv.www;

public class Usuario {
    String matricula;
    String password;
    String nombre;
    String tipo; 
    
   
    public Usuario() {
    }
    public Usuario(String matricula, String password, String nombre, String tipo) {
        this.matricula = matricula;
        this.password = password;
        this.nombre = nombre;
        this.tipo = tipo;
    }
    public String getMatricula() {
        return matricula;
    }
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
