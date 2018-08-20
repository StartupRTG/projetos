package startuprtg.tales.ebac;

/**
 * Created by Raphael on 16/08/2018.
 */

public class Boleto {
    public String nome;

    Boleto(String nome){
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
