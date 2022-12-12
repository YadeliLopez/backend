package mx.uv.www;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.sql.Statement;

// Data Access Object
public class DAO {
    //se hace la conexion con la base de datos
    private static Conexion c = new Conexion();



//* ******************************************************************************************************* */
//CRUD USUARIOS
    // este metodo regresa un conjunto de usuarios que cumpla un criterio
    public static List<Usuario> consultaUsuarios() {
        Statement stm = null;
        ResultSet rs = null;
        Connection conn = null;
        List<Usuario> resultado = new ArrayList<>();
        conn = c.getConnection();
        try {
            String sql = "SELECT * from usuarios";
            stm = (Statement) conn.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                Usuario u = new Usuario(rs.getString("matricula"), rs.getString("nombre"), rs.getString("password"),rs.getString("tipo"));
                resultado.add(u);
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (rs != null)
                try {
                    rs.close();
                } catch (SQLException e) {
                    System.out.println(e);
                }
            rs = null;
            if (stm != null) {
                try {
                    stm.close();
                } catch (Exception e) {
                    System.out.println(e);
                }
                stm = null;
            }
            try {
                conn.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        return resultado;
    }

    public static String registrarUsuario(Usuario u) {
        PreparedStatement stm = null;
        Connection conn = null;
        String msj = "";

        conn = c.getConnection();
        try {
            String sql = "INSERT INTO usuarios (matricula, nombre, password, tipo) values (?,?,?,?)";
            stm = (PreparedStatement) conn.prepareStatement(sql);
            stm.setString(1, u.getMatricula());
            stm.setString(2, u.getNombre());
            stm.setString(3, u.getPassword());
            stm.setString(4, u.getTipo());

            if (stm.executeUpdate() > 0)
                msj = "USUARIO REGISTRADO CON EXITO";
            else
                msj = "ERROR, USUARIO NO REGISTRADO";

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (Exception e) {
                    System.out.println(e);
                }
                stm = null;
            }
            try {
                conn.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return msj;
    }

  /*    public int validarUsuario(Usuario usuario) throws Exception{
        Statement stm;
        ResultSet usuarios;
        String sql = "select * from usuario;";
        
        try (Connection conn = conexion.getConnection();){
            stm = conn.createStatement();
            usuarios = stm.executeQuery(sql);
            System.out.println(usuarios);
            while (usuarios.next()){
                Usuario u = new Usuario(usuarios.getString("matricula"), usuarios.getString("password"), usuarios.getString("tipo"));
                
                    if(usuarios.getString("matricula").equals(u.getMatricula()) && usuarios.getString("password").equals(u.getPassword())){
                        if (usuarios.getString("tipo").equals(u.getTipo())){
                            System.out.println("INGRESO CON EXITO");
                            if (usuarios.getString("tipo").equals(u.getTipo())){
                                return 1;
                            }                      
                        }else{
                            System.out.println("INGRESO SIN EXITO");
                            return 0;
                        }
                    }else{
                        System.out.println("INGRESO SIN EXITO");
                        return 0;
                    }
                
            }
        }catch (SQLException e) {
            throw new Exception("Error en readAll SQLException " + e.getMessage());
        } catch (Exception e) {
            throw new Exception("Error en readAll Exception " + e.getMessage());
        }
        return -1;
    }*/



    public static String eliminaUsuario(String matricula){
        PreparedStatement stm = null;
        Connection conn = null;
        String msj = "";
        conn = c.getConnection();

        try {
            String sql="DELETE FROM usuarios WHERE matricula = ?";
            stm = (PreparedStatement) conn.prepareStatement(sql);

            if (stm.executeUpdate()>0) {
                msj="USUARIO ELIMINADO CON EXITO";
            } else {
                msj="ERROR, USUARIO NO ELIMINADO";
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (stm != null)
                try {
                    stm.close();
                } catch (SQLException e) {
                    System.out.println(e);
                }
            stm = null;
            try {
                conn.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return msj;
    }

    public static String actualizaUsuarios(Usuario u){
        PreparedStatement stm=null;
        Connection conn = null;
        String msj="";

        conn=c.getConnection();
        try {
            String sql="UPDATE usuarios SET matricula = ?, nombre = ?, password = ?, tipo =? where matricula = ?";
            stm = (PreparedStatement) conn.prepareStatement(sql);
            stm.setString(1, u.getMatricula());
            stm.setString(2, u.getNombre());
            stm.setString(3, u.getPassword());
            stm.setString(4, u.getTipo());

            if (stm.executeUpdate()>0) {
                msj="USUARIO ACTUALIZADO CON EXITO";
            } else {
                msj="ERROR, USUARIO NO ACTUALIZADO";
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (stm != null)
                try {
                    stm.close();
                } catch (SQLException e) {
                    System.out.println(e);
                }
            stm = null;
            try {
                conn.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return msj;
    }

//* ******************************************************************************************************* */
//CRUD LIBROS
public static String registrarLibro(Libro L){
    PreparedStatement stm = null;
    Connection conn = null;
    String  msj = "";

    conn = c.getConnection();
    try {
        String sql = "INSERT INTO libros (isbn, titulo, autor) values (?,?,?)";
        stm = (PreparedStatement) conn.prepareStatement(sql);
        stm.setString(1, L.getIsbn());
        stm.setString(1, L.getTitulo());
        stm.setString(2, L.getAutor());

        if (stm.executeUpdate() > 0)
            msj = "LIBRO REGISTRADO CON EXITO";
        else
            msj = "ERROR, LIBRO NO REGISTRADO";

    } catch (Exception e) {
        System.out.println(e);
    } finally {
        if (stm != null) {
            try {
                stm.close();
            } catch (Exception e) {
                System.out.println(e);
            }
            stm = null;
        }
        try {
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    return msj;
    }

    public static List<Libro> consultaLibros() {
        Statement stm = null;
        ResultSet rs = null;
        Connection conn = null;
        List<Libro> resultado = new ArrayList<>();

        conn = c.getConnection();

        try {
            String sql = "SELECT * from libros";
            stm = (Statement) conn.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                Libro L = new Libro(rs.getString("isbn"), rs.getString("titulo"), rs.getString("autor"));
                resultado.add(L);
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (rs != null)
                try {
                    rs.close();
                } catch (SQLException e) {
                    System.out.println(e);
                }
            rs = null;
            if (stm != null) {
                try {
                    stm.close();
                } catch (Exception e) {
                    System.out.println(e);
                }
                stm = null;
            }
            try {
                conn.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return resultado;
    }

    public static String eliminaLibro(String isbn){
        PreparedStatement stm = null;
        Connection conn = null;
        String msj = "";
        conn = c.getConnection();

        try {
            String sql="DELETE FROM libros WHERE isbn = ?";
            stm = (PreparedStatement) conn.prepareStatement(sql);

            if (stm.executeUpdate()>0) {
                msj="LIBRO ELIMINADO CON EXITO";
            } else {
                msj="ERROR, LIBRO NO ELIMINADO";
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (stm != null)
                try {
                    stm.close();
                } catch (SQLException e) {
                    System.out.println(e);
                }
            stm = null;
            try {
                conn.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return msj;
    }

    public static String actualizaLibros(Libro L){
        PreparedStatement stm=null;
        Connection conn = null;
        String msj="";

        conn=c.getConnection();
        try {
            String sql="UPDATE libros SET isbn = ?, titulo = ?, autor = ?, tipo =? where isbn = ?";
            stm = (PreparedStatement) conn.prepareStatement(sql);
            stm.setString(1, L.getIsbn());
            stm.setString(2, L.getAutor());
            stm.setString(3, L.getTitulo());

            if (stm.executeUpdate()>0) {
                msj="LIBRO ACTUALIZADO CON EXITO";
            } else {
                msj="ERROR, LIBRO NO ACTUALIZADO";
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (stm != null)
                try {
                    stm.close();
                } catch (SQLException e) {
                    System.out.println(e);
                }
            stm = null;
            try {
                conn.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return msj;
    }
}