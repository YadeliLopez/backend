package mx.uv.www;

import static spark.Spark.*;

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

        //port(80);
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
    

        post("/", (req,res)->{
            String datosUsuario = req.body();
            Usuario u = gson.fromJson(datosUsuario, Usuario.class);
            return DAO.registrarUsuario(u);
        });


    }
}