public class Cliente {

    public String nome;
    public String cpf;
    public double totalGasto;
    public boolean bicicletaAlugada;
    public int quantidadeLocacoes;

    public Cliente(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
        this.totalGasto = 0;
        this.bicicletaAlugada = false;
        this.quantidadeLocacoes = 0;
    }

}
