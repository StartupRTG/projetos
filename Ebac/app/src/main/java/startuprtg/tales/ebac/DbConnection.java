package startuprtg.tales.ebac;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.xml.transform.Result;

/**
 * Created by Raphael on 30/08/2018.
 */

public class DbConnection {

    static java.sql.Connection con;

    public static boolean connect(){
        try {
             con = DriverManager.getConnection("jdbc:mysql://localhost", "root", "root");
             return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean getUser(String login, String pwd){
        try {
            PreparedStatement stm = con.prepareStatement("SELECT * FROM cad_usuario WHERE login = '" + login + "' AND password = '" + pwd + "';");
            ResultSet rs = stm.executeQuery();
            Boolean found = false;
            while(rs.next()){
                found = true;
            }
            return found;
        } catch (SQLException e){
            return false;
        }
    }

    public static List<Boleto> getBoleto(){
        try {
            PreparedStatement stm = con.prepareStatement("SELECT nome, data_exp, valor, cd_barra FROM boleto;");
            ResultSet rs = stm.executeQuery();
            List<Boleto> boletos = null;
            while(rs.next()){
                Boleto blt = new Boleto(rs.getString("nome"), rs.getString("data_exp"), rs.getString("valor"), rs.getString("cd_barra"));
                boletos.add(blt);
            }
            return boletos;
        } catch (SQLException e){
            return null;
        }
    }
}
