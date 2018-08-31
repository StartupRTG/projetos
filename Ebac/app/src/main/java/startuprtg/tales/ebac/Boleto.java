package startuprtg.tales.ebac;

/**
 * Created by Raphael on 16/08/2018.
 */

public class Boleto {
    public String nome;
    public String data;
    public String valor;
    public String codigo;

    Boleto(String nome, String data, String valor, String codigo){
        this.nome = nome;
        this.data = data;
        this.valor = valor;
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
