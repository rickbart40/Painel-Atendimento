import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PainelAtendimentoService {

    // ========================================================
    // PASSO 1 DO SINGLETON: Variável estática para guardar a única instância
    // ========================================================
    private static PainelAtendimentoService instancia;

    // Nossas variáveis normais da fila (continuam iguais)
    private List<String> filaComum;
    private List<String> filaPreferencial;
    private int contadorGeral;
    private int contagemPreferenciaisSeguidos;
    private int totalGuiche1 = 0;
    private int totalGuiche2 = 0;
    private int totalGuiche3 = 0;

    // ========================================================
    // PASSO 2 DO SINGLETON: Construtor PRIVADO!
    // Ninguém de fora consegue dar "new PainelAtendimentoService()"
    // ========================================================
    private PainelAtendimentoService() {
        this.filaComum = new ArrayList<>();
        this.filaPreferencial = new ArrayList<>();
        this.contadorGeral = 0;
        this.contagemPreferenciaisSeguidos = 0;
        this.totalGuiche1 = 0;
        this.totalGuiche2 = 0;
        this.totalGuiche3 = 0;
    }

    // ========================================================
    // PASSO 3 DO SINGLETON: Método estático global de acesso
    // ========================================================
    public static PainelAtendimentoService getInstancia() {
        // Se a instância ainda não nasceu na memória, nós criamos uma única vez
        if (instancia == null) {
            instancia = new PainelAtendimentoService();
        }
        // Se já existe, apenas devolvemos a que já está criada
        return instancia;
    }

    // ========================================================
    // MÉTODOS DE NEGÓCIO (continuam exatamente como fizemos antes!)
    // ========================================================
    public String emitirSenha(String tipoAtendimento, String nomeCliente) {
        this.contadorGeral++;
        String ticketFormatado = "";

        if (tipoAtendimento.equalsIgnoreCase("COMUM")) {
            ticketFormatado = "C-" + this.contadorGeral + " | " + nomeCliente.toUpperCase() ;
            this.filaComum.add(ticketFormatado);
        } else if (tipoAtendimento.equalsIgnoreCase("PREFERENCIAL")) {
            ticketFormatado = "P-" + this.contadorGeral + " | " + nomeCliente.toUpperCase() + " [PRIORITÁRIO]";
            this.filaPreferencial.add(ticketFormatado);
        } else {
            throw new IllegalArgumentException("Tipo de atendimento inválido: " + tipoAtendimento);
        }

        System.out.println("Senha emitida: " + ticketFormatado);
        return ticketFormatado;
    }

    public void chamarProximaSenha(int numeroGuiche){
        String proxima = null;

        // Regra da proporção 2 prioritários para 1 comum
        boolean podeChamarPreferencial = !this.filaPreferencial.isEmpty() && this.contagemPreferenciaisSeguidos < 2;


        if (podeChamarPreferencial) {
            proxima = this.filaPreferencial.remove(0);
            this.contagemPreferenciaisSeguidos++;
        } else if (!this.filaComum.isEmpty()) {
            proxima = this.filaComum.remove(0);
            this.contagemPreferenciaisSeguidos = 0; // Zera o ciclo
        } else if (!this.filaPreferencial.isEmpty()) {
            proxima = this.filaPreferencial.remove(0);
        }

        if (proxima == null) {
            System.out.println("Guichê " + numeroGuiche + ": Nenhuma senha aguardando.");
            return;
        }

        System.out.println("\n-------------------------------------------");
        System.out.println(">>> PAINEL TV: " + proxima + " COMPARECER AO GUICHÊ " + numeroGuiche);
        System.out.println("-------------------------------------------\n");
    }

    public int selecionarGuiche() {

        int numeroGuiche = new Random().nextInt(0, 2) + 1;

        

        if (numeroGuiche == 1) {
            totalGuiche1++;
        } else if (numeroGuiche == 2) {
            totalGuiche2++;
        } else if (numeroGuiche == 3) {
            totalGuiche3++;
        } else {
            throw new IllegalArgumentException("Número de guichê inválido: " + numeroGuiche);
        }

        int totalAtendimentos = totalGuiche1 + totalGuiche2 + totalGuiche3;

        System.out.println("\n=== RELATÓRIO DE ATENDIMENTOS ===");
        System.out.println("Total de atendimentos " + totalAtendimentos);
        return numeroGuiche;
    }
}