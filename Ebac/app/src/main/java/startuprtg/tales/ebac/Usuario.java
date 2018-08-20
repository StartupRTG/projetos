package startuprtg.tales.ebac;

/**
 * Created by Raphael on 19/08/2018.
 */

public class Usuario {
    private Integer id;
    private String login;
    private String pwd;

    private static Usuario tbUsuario;

    private Usuario(){}

    private Usuario(Integer id, String login, String pwd){
        this.id = id;
        this.login = login;
        this.pwd = pwd;
    }

    static public void setInstance(Usuario novoUsuario){
        tbUsuario = novoUsuario;
    }

    static public Usuario setInstance(Integer id, String login, String pwd){
        if(tbUsuario == null){
            tbUsuario = new Usuario(id, login, pwd);
        } else {
            tbUsuario.id = id;
            tbUsuario.login = login;
            tbUsuario.pwd = pwd;
        }

        return tbUsuario;
    }

    static public Usuario getInstance(){
        if(tbUsuario == null){
            tbUsuario = new Usuario();
        }

        return tbUsuario;
    }

    public void logOut(){
        tbUsuario = null;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
