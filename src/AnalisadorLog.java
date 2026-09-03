import java.util.ArrayList;
import java.util.List;

public class AnalisadorLog implements IAnalisadorLog {



    
    @Override
    //Percorre o vetor de linhas,divide as em seções a partir do "|" e valida se contém
    //todos os 6 campos nescessários;Verifica se contém "ms" e se o código de erro é menor
    //que 400 para validar o registro.
    public int contarRegistrosValidos(List<String> campos) {
    int registrosValidos = 0;

    for (int i = 0; i < campos.size(); i++) {
        String[] camposLinha = campos.get(i).split("\\|");
        boolean hasMS = camposLinha.length > 5 && camposLinha[5].contains("ms");

        if (camposLinha.length == 6 && !camposLinha[5].equals("null") && hasMS && Integer.parseInt(camposLinha[4].trim()) < 400) {
            registrosValidos++;
        }
    }
    return registrosValidos;
}
    //Verifica quais requisições falharam e armazena suas linhas em um vetor,de acordo com
    //as regras estabelecidas no comentário do método anterior
    @Override
    public List<String> listarRequisicoesComFalha(List<String> campos) {
        List<String> falhas = new ArrayList<>();
        for (int i = 0; i < campos.size(); i++) {
        String[] camposLinha = campos.get(i).split("\\|");
        boolean hasMS = camposLinha.length > 5 && camposLinha[5].contains("ms");

        if (camposLinha.length != 6 || camposLinha[5].equals("null") || !hasMS) {
            falhas.add(campos.get(i));
            continue;
        }

        try {
            if (Integer.parseInt(camposLinha[4].trim()) >= 400) {
                falhas.add(campos.get(i));
            }
        } catch (NumberFormatException e) {
            falhas.add(campos.get(i));
        }}
        return falhas;
    }

    // Verifica se a linha contém o campo "/api/v1/payments" e calcula a média de tempo
    //das requisições
   public double calcularTempoMedioPayments(List<String> campos) {
    int cont = 0;
    int tempoTotal = 0;

    for (int i = 0; i < campos.size(); i++) {
        String[] camposLinha = campos.get(i).split("\\|");

        if (camposLinha.length == 6 && !camposLinha[5].isEmpty() && camposLinha[3].contentEquals(" /api/v1/payments ")) {
            String tempoSemMS = camposLinha[5].replace("ms", "").trim();
            int tempo = Integer.parseInt(tempoSemMS);
            tempoTotal += tempo;
            cont++;
        }
    }

    if (cont == 0) {
        System.out.println("Não há dados suficientes para calcular a média");
        return 0;
    }

    return (double) tempoTotal / cont;
}
}
