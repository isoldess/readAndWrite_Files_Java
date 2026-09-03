import java.util.List;

//Estabelece os métodos nescessários de acordo com a atividade 
public interface IAnalisadorLog {
  
  int contarRegistrosValidos(List<String> Campos);

  List<String> listarRequisicoesComFalha(List<String> linhas);

  double calcularTempoMedioPayments(List<String> linhas);
}