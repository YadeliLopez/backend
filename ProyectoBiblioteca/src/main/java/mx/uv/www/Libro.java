package mx.uv.www;

public class Libro {
    String nombre;
    String autor;

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }
    
    public Libro(String nombre, String autor) {
        this.nombre = nombre;
        this.autor = autor;
    }
}
