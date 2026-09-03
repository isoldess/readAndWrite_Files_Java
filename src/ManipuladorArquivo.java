import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class ManipuladorArquivo{

    // Lê o arquivo linha a linha e armazena num array
    public List<String> leitor(String path) throws IOException{
        String linha = "";
        List<String> Campos = new ArrayList<>();
        try{
            BufferedReader buffRead = new BufferedReader(new FileReader(path));
            while ((linha = buffRead.readLine()) != null){
            Campos.add(linha);
            }
            buffRead.close();
        }
        catch (FileNotFoundException e){
            System.out.println("Erro,o arquivo não foi encontrado!");
        }
        return Campos;
    }
    

    //Escreve o relatório após verificação 
    public void escrever(String path, List<String> campos) throws IOException {

        //Instancia um objeto da classe AnalisadorLog
        AnalisadorLog analisador = new AnalisadorLog();

        //Armazena os resultados dos métodos de AnalisadorLog em variáveis
        int registrosValidos = analisador.contarRegistrosValidos(campos);
        List<String> falhas = analisador.listarRequisicoesComFalha(campos);
        double tempoMedioPayments = analisador.calcularTempoMedioPayments(campos);

        //Instancia o BufferedWriter e gera o arquivo relatorio.txt
        try (BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path))) {
            buffWrite.write("RELATORIO DE ANALISE DO SERVIDOR\n");
            buffWrite.write("  ================================\n");
            buffWrite.write("  Registros validos processados: " + registrosValidos + "\n");
            buffWrite.write("  Requisicoes com falha: " + falhas.size() + "\n");
            buffWrite.write("\n");
            buffWrite.write("  FALHAS ENCONTRADAS\n");
            for (String falha : falhas) {
                buffWrite.write("  " + falha + "\n");
            }
            buffWrite.write("\n");
            buffWrite.write("  TEMPO MEDIO - /api/v1/payments\n");
            buffWrite.write("  " + String.format("%.2f", tempoMedioPayments) + "ms\n");
        }
    }
}