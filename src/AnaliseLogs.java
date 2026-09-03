import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
public class AnaliseLogs{

    //Classe main que instancia a classe ManipuladorArquivo,define o path para entrada e
    //saída; Executa a leitura,escrita e avisa o local do arquivo gerado
    public static void main(String[] args) throws IOException {
        ManipuladorArquivo Analise = new ManipuladorArquivo();
        String pathEntrada = "src/access_exemplo.log";
        String pathSaida = "src/relatorio.txt";
        try{
        List<String> campos = Analise.leitor(pathEntrada);
        Analise.escrever(pathSaida, campos);
        System.out.println("Relatorio gerado em: " + pathSaida);
        }
        catch (FileNotFoundException e){
            System.out.print("Erro! Programa encerrado");
        }
    }
}