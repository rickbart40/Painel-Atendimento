import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PainelAtendimentoService2 {

    // ========================================================
    // PASSO 1 DO SINGLETON: Variável estática para guardar a única instância
    // ========================================================
    private static PainelAtendimentoService2 instancia;

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
    private PainelAtendimentoService2() {
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
    public static PainelAtendimentoService2 getInstancia() {
        // Se a instância ainda não nasceu na memória, nós criamos uma única vez
        if (instancia == null) {
            instancia = new PainelAtendimentoService2();
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
            ticketFormatado = "C-" + this.contadorGeral + " | " + nomeCliente.toUpperCase() + " [COMUM] ";
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

    public int chamarProximaSenha() {
        String proxima = null;

        // Regra: até 2 preferenciais para 1 comum
        boolean podeChamarPreferencial = !this.filaPreferencial.isEmpty() && this.contagemPreferenciaisSeguidos < 2;

        int numeroGuiche = new Random().nextInt(3) + 1;

        if (podeChamarPreferencial) {
            proxima = this.filaPreferencial.remove(0);
            this.contagemPreferenciaisSeguidos++;
        } else if (!this.filaComum.isEmpty()) {
            proxima = this.filaComum.remove(0);
            this.contagemPreferenciaisSeguidos = 0;
        } else if (!this.filaPreferencial.isEmpty()) {
            proxima = this.filaPreferencial.remove(0);
            this.contagemPreferenciaisSeguidos++;
        }

        // Se não houver senhas, encerra este atendimento
        if (proxima == null) {
            System.out.println("Guichê " + numeroGuiche + ": Nenhuma senha aguardando.");
            return numeroGuiche;
        }

        System.out.println("\n=== SALA MÉDICA (GUICHÊ " + numeroGuiche + ") VAI ATENDER ===");

        System.out.println("\n-------------------------------------------");
        System.out.println(">>> PAINEL TV: " + proxima + " COMPARECER AO GUICHÊ " + numeroGuiche);
        System.out.println("-------------------------------------------");

        // Contabiliza o atendimento
        if (numeroGuiche == 1) {
            totalGuiche1++;
        } else if (numeroGuiche == 2) {
            totalGuiche2++;
        } else if (numeroGuiche == 3) {
            totalGuiche3++;
        }
        return numeroGuiche;
    }

    public void exibirRelatorioFinal() {
        int totalAtendimentos = totalGuiche1 + totalGuiche2 + totalGuiche3;

        System.out.println("\n===========================================");
        System.out.println("       RELATÓRIO FINAL DE ATENDIMENTOS");
        System.out.println("===========================================");

        System.out.println("Total de atendimentos no Guichê 1: " + totalGuiche1);

        System.out.println("Total de atendimentos no Guichê 2: " + totalGuiche2);

        System.out.println("Total de atendimentos no Guichê 3: " + totalGuiche3);

        System.out.println("-------------------------------------------");
        System.out.println("Total geral de atendimentos: " + totalAtendimentos);
        System.out.println("===========================================");
    }
}