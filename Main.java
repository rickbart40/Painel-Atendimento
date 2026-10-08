

public class Main {
public static void main (String[] args){
        System.out.println("=== EMITINDO SENHAS ===");
        
        PainelAtendimentoService guiche1 = PainelAtendimentoService.getInstancia();
        
        guiche1.emitirSenha("COMUM", "Carlos");
        guiche1.emitirSenha("PREFERENCIAL", "Dona Maria");
        guiche1.emitirSenha("PREFERENCIAL", "Mario");
        guiche1.emitirSenha("PREFERENCIAL", "Fabio");
        guiche1.emitirSenha("COMUM", "Marcela");

        PainelAtendimentoService guiche2 = PainelAtendimentoService.getInstancia();
        
        System.out.println("\n=== SALA MÉDICA (GUICHÊ " + guiche2.selecionarGuiche() + ") VAI ATENDER ===");

        guiche2.chamarProximaSenha(guiche2.selecionarGuiche());
        guiche2.chamarProximaSenha(guiche2.selecionarGuiche());
        guiche2.chamarProximaSenha(guiche2.selecionarGuiche());

        guiche2.selecionarGuiche();
    }
}