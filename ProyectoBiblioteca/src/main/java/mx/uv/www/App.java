package mx.uv.www;

import static spark.Spark.*;
import com.google.gson.*;
import spark.*;
import mx.uv.www.*;

//import java.util.HashMap;
//import java.util.Map;

import com.google.gson.Gson;

/**
 * Hello world!
 *
 */


public class App {

    public static Gson gson = new Gson();
    //private static Map<String, Usuario> usuarios = new HashMap<>();
    //private static Conexion c;

   
    public static void main(String[] args) {
        //intentar conectar a la BD
        Conexion c = new Conexion();
        c.getConnection();

        port(80);
        options("/*", (request, response) -> {
                String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
                System.out.println(accessControlRequestHeaders);
                if (accessControlRequestHeaders != null) {
                    response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
                }
                String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
                System.out.println(accessControlRequestMethod); 
                if (accessControlRequestMethod != null) {
                    response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
                }
                return "OK";
            });
            
        before((req, res)-> res.header("Access-Control-Allow-Origin", "*"));
        before((req, res)-> res.type("application/json"));
        
        get("/resources", (req, res) -> gson.toJson(DAO.consultaUsuarios()) );
        get("/resources", (req, res) -> gson.toJson(DAO.consultaLibros()) );
    
        get("/", (req, res) -> {
            res.redirect("/index.html");
            return null;
        });

        get("/", (req, res) -> {
            res.redirect("/admin.html");
            return null;
        });
        get("/", (req, res) -> {
            res.redirect("/libros.html");
            return null;
        });

        post("/usuarios", (req,res)->{
            String datosUsuario = req.body();
            Usuario u = gson.fromJson(datosUsuario, Usuario.class);
            return DAO.registrarUsuario(u);
        });
        
        post("/libros", (req,res)->{
            String datosLibro = req.body();
            Libro l= gson.fromJson(datosLibro, Libro.class);
            return DAO.registrarLibro(l);
        });

        get("/usuarios", (req, res) -> {
            before((req2, res2) -> res.type("application/json"));
            return gson.toJson(DAO.consultaUsuarios());
        });

        get("/libros", (req, res) -> {
            before((req2, res2) -> res.type("application/json"));
            return gson.toJson(DAO.consultaLibros());
        });



    }
}