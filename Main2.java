public class Main2 {
    public static void main (String[] args){
        System.out.println("=== EMITINDO SENHAS ===");
        
        PainelAtendimentoService2 guiche1 = PainelAtendimentoService2.getInstancia();
        
        guiche1.emitirSenha("COMUM", "Carlos");
        guiche1.emitirSenha("PREFERENCIAL", "Dona Maria");
        guiche1.emitirSenha("PREFERENCIAL", "Mario");
        guiche1.emitirSenha("PREFERENCIAL", "Fabio");
        guiche1.emitirSenha("COMUM", "Marcela");

        PainelAtendimentoService2 guiche2 = PainelAtendimentoService2.getInstancia();
        

        guiche2.chamarProximaSenha();
        guiche2.chamarProximaSenha();
        guiche2.chamarProximaSenha();
        guiche2.chamarProximaSenha();
        guiche2.chamarProximaSenha();

        // Exibe o relatório apenas uma vez, ao final guiche2.exibirRelatorioFinal();
        guiche2.exibirRelatorioFinal();
    }
}
