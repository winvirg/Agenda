import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;

import static org.junit.jupiter.api.Assertions.*;

public class TestAgenda {
    
    @Test
    public void testInicializacao(){
        Agenda agenda = new Agenda();
        assertEquals(0, agenda.getQuantidadeDeContatos(),
            "Ao inicializar uma agenda, não deve haver nenhum contato na lista de contatos.");
    }

    @Test
    public void testAdicionarContatoComSucesso(){
        Agenda agenda = new Agenda();
        Contato contato = new Contato("Alex");
        assertTrue(contato.adicionarFone(new Fone(Identificador.TRABALHO, "(59)19536-2054")),
            "Deve ser possível adicionar um fone a um conato se o número estiver correto.");
        assertTrue(contato.adicionarFone(new Fone(Identificador.CASA, "(63)54612-5374")),
            "Deve ser possível adicionar um fone a um conato se o número estiver correto.");
        assertEquals(2, contato.getQuantidadeFones(),
            "Ao adicionar telefones válidos, eles serão salvos na lista de telefones do contato.");
        assertTrue(agenda.adicionarContato(contato),
            "Deve ser possível adcionar um contato na lista de contatos se o nome ainda não existir.");
        assertEquals(1, agenda.getQuantidadeDeContatos(),
            "Ao adicionar um contato válido, ele será salvo na lista de contatos da agenda.");  
    }

    @Test
    public void testAdicionarContatoSemTelefone(){
        Agenda agenda = new Agenda();
        Contato contato = new Contato("Alex");
        assertFalse(agenda.adicionarContato(contato),
                "Deve ser possível adcionar um contato na lista de contatos se o nome ainda não existir.");
        assertEquals(0, agenda.getQuantidadeDeContatos(),
                "Ao adicionar um contato válido, ele será salvo na lista de contatos da agenda.");
    }

    @Test
    public void testAdicionarContatoRepetido(){
        Agenda agenda = new Agenda();
        Contato contato = new Contato("Alex");
        Contato contato1 = new Contato("Alex");
        assertTrue(contato.adicionarFone(new Fone(Identificador.TRABALHO, "(59)19536-2054")),
            "Deve ser possível adicionar um fone a um conato se o número estiver correto.");
        assertTrue(contato1.adicionarFone(new Fone(Identificador.CELULAR, "(16)69902-3026")),
            "Deve ser possível adicionar um fone a um conato se o número estiver correto.");
        assertTrue(contato1.adicionarFone(new Fone(Identificador.CASA, "(51)31658-4460")),
            "Deve ser possível adicionar um fone a um conato se o número estiver correto.");    
        assertTrue(agenda.adicionarContato(contato),
            "Deve ser possível adcionar um contato na lista de contatos se o nome ainda não existir.");
        assertFalse(agenda.adicionarContato(contato1),
            "Não deve ser possível adicionar um contato na lista de contatos se o nome já existir.");
        assertEquals(1, agenda.getQuantidadeDeContatos(),
            "Ao adicionar um contato válido, ele será salvo na lista de contatos da agenda.");
        assertEquals(3, contato.getQuantidadeFones(),
            "Se o contato já existir deve ser possível apenas adicionar os novos telefones no contato já existente.");
    }

    @Test
    public void testRemoverContatoComSucesso(){
        Agenda agenda = new Agenda();
        Contato contato = new Contato("Alex");
        assertTrue(contato.adicionarFone(new Fone(Identificador.TRABALHO, "(59)19536-2054")),
            "Deve ser possível adicionar um fone a um conato se o número estiver correto.");
        contato.adicionarFone(new Fone(Identificador.CASA, "(16)69902-3026"));
        assertTrue(agenda.adicionarContato(contato),
            "Deve ser possível adcionar um contato na lista de contatos se o nome ainda não existir.");
        assertTrue(agenda.removerContato("Alex"),
            "Deve ser possível remover um contato se o nome estiver cadastrado na lista de contatos da agenda.");
        assertEquals(0, agenda.getQuantidadeDeContatos(),
            "Ao remover um contato ele automáticamente irá ser excluído da lista de contatos.");  
    }

    @Test
    public void testRemoverFoneDoContatoDaAgenda(){
        Agenda agenda = new Agenda();
        Contato contato = new Contato("Alex");
        Fone fone = new Fone(Identificador.TRABALHO, "(59)19536-2054");
        assertTrue(contato.adicionarFone(fone),
            "Deve ser possível adicionar um fone a um conato se o número estiver correto.");
        contato.adicionarFone(new Fone(Identificador.CELULAR, "(16)69902-3026"));
        assertTrue(agenda.adicionarContato(contato),
            "Deve ser possível adcionar um contato na lista de contatos se o nome ainda não existir.");
        assertTrue(agenda.removerFone("Alex", 1),
            "Deve ser possível remover um contato se o nome do contato estiver cadastrado na agenda e se indice do fone corresponder a um fone.");
        assertEquals(1, contato.getQuantidadeFones(),
            "Ao remover um fone pelo indice o fone irá ser excluído permanentemente da lista de fones do contato.");
        List<Fone> fones = Arrays.asList(fone);
        List<Fone> restantes = contato.getFones();
        assertEquals(fones, restantes,
            "Ao remover um fone pelo indice o fone que será excluído tem que corresponder exatamente ao indíce que foi informado.");

    }

    @Test
    public void testRemoverFoneDoContatoDaAgendaComNomeNaoCadastrado(){
        Agenda agenda = new Agenda();
        Contato contato = new Contato("Alex");
        assertTrue(contato.adicionarFone(new Fone(Identificador.TRABALHO, "(59)19536-2054")),
            "Deve ser possível adicionar um fone a um conato se o número estiver correto.");
        assertTrue(agenda.adicionarContato(contato),
            "Deve ser possível adcionar um contato na lista de contatos se o nome ainda não existir.");
        assertFalse(agenda.removerFone("Alexx", 1),
            "Não deve ser possível remover um fone de um contato se o nome do contato não estiver cadastrado na lista");
    }

    @Test
    public void testRemoverFoneDoContatoDaAgendaComIndiceIncorreto(){
        Agenda agenda = new Agenda();
        Contato contato = new Contato("Alex");
        assertTrue(contato.adicionarFone(new Fone(Identificador.TRABALHO, "(59)19536-2054")),
            "Deve ser possível adicionar um fone a um conato se o número estiver correto.");
        assertTrue(agenda.adicionarContato(contato),
            "Deve ser possível adcionar um contato na lista de contatos se o nome ainda não existir.");
        assertFalse(agenda.removerFone("Alex", 2),
            "Não deve ser possível remover um fone de um contato se o indice do fone não conrresponder ao indice válido na lista de fones");
    }

    
    @Test
    public void testRemoverContatoComNomeNaoCadastrado(){
        Agenda agenda = new Agenda();
        Contato contato = new Contato("Alex");
        assertTrue(contato.adicionarFone(new Fone(Identificador.TRABALHO, "(59)19536-2054")),
            "Deve ser possível adicionar um fone a um conato se o número estiver correto.");
        contato.adicionarFone(new Fone(Identificador.CELULAR, "(16)69902-3026"));
        assertTrue(agenda.adicionarContato(contato),
            "Deve ser possível adcionar um contato na lista de contatos se o nome ainda não existir.");
        assertFalse(agenda.removerContato("Ana"),
            "Não deve ser possível remover um contato se o nome do contato não estiver cadastrado na lista de contatos da agenda.");
    }

    @Test
    public void testRecuperarContato() {
        Agenda agenda = new Agenda();
        Contato contato = new Contato("Ana");
        assertTrue(contato.adicionarFone(new Fone(Identificador.TRABALHO, "(59)19536-2054")),
                "Deve ser possível adicionar um fone a um conato se o número estiver correto.");
        assertTrue(agenda.adicionarContato(contato),
                "Deve ser possível adcionar um contato na lista de contatos se o nome ainda não existir.");
        Contato recuperado = agenda.getContato("Ana");
        assertNotNull(recuperado, "Deve ser possivel recuperar um contato ja adicionado");
        assertEquals(1, recuperado.getQuantidadeFones(), "O contato recuperado tem 1 telefone");
    }

    @Test
    public void testRecuperarContatoInexistente() {
        Agenda agenda = new Agenda();
        Contato recuperado = agenda.getContato("Ana");
        assertNull(recuperado, "Deve ser possivel recuperar um contato ja adicionado");
    }

    @Test
    public void testPesquisandoContatosPorNomes(){
        Agenda agenda = new Agenda();
        Contato contato = new Contato("Ana");
        Contato contato1 = new Contato("Adriele");
        Contato contato2 = new Contato("Ariele");
        assertTrue(contato.adicionarFone(new Fone(Identificador.TRABALHO, "(59)19536-2054")),
            "Deve ser possível adicionar um fone a um conato se o número estiver correto.");
        assertTrue(contato1.adicionarFone(new Fone(Identificador.CELULAR, "(46)40354-9846")),
            "Deve ser possível adicionar um fone a um conato se o número estiver correto.");
        assertTrue(contato2.adicionarFone(new Fone(Identificador.TRABALHO, "(37)44338-4811")),
            "Deve ser possível adicionar um fone a um conato se o número estiver correto.");
        assertTrue(agenda.adicionarContato(contato),
            "Deve ser possível adcionar um contato na lista de contatos se o nome ainda não existir.");
        assertTrue(agenda.adicionarContato(contato1),
            "Deve ser possível adcionar um contato na lista de contatos se o nome ainda não existir.");
        assertTrue(agenda.adicionarContato(contato2),
            "Deve ser possível adcionar um contato na lista de contatos se o nome ainda não existir.");
        List<Contato> resultadoExato = Arrays.asList(contato1, contato2);
        List<Contato> resultado = agenda.pesquisar("ri");
        assertEquals(resultadoExato, resultado,
            "Deve ser possível encontrar contatos na lista de contatos se o padrão conrresponder a qualquer nome, identificado ou telefones");
    }

    @Test
    public void testPesquisandoContatosPorNumeros(){
        Agenda agenda = new Agenda();
        Contato contato = new Contato("Geoana");
        Contato contato1 = new Contato("Adriele");
        Contato contato2 = new Contato("Ana");
        assertTrue(contato.adicionarFone(new Fone(Identificador.TRABALHO, "(59)19536-9999")),
            "Deve ser possível adicionar um fone a um conato se o número estiver correto.");
        assertTrue(contato1.adicionarFone(new Fone(Identificador.CELULAR, "(46)40354-8453")),
            "Deve ser possível adicionar um fone a um conato se o número estiver correto.");
        assertTrue(contato2.adicionarFone(new Fone(Identificador.TRABALHO, "(37)44338-4999")),
            "Deve ser possível adicionar um fone a um conato se o número estiver correto.");
        assertTrue(agenda.adicionarContato(contato),
            "Deve ser possível adcionar um contato na lista de contatos se o nome ainda não existir.");
        assertTrue(agenda.adicionarContato(contato1),
            "Deve ser possível adcionar um contato na lista de contatos se o nome ainda não existir.");
        assertTrue(agenda.adicionarContato(contato2),
            "Deve ser possível adcionar um contato na lista de contatos se o nome ainda não existir.");
        List<Contato> resultadoExato = Arrays.asList(contato2, contato);
        List<Contato> resultado = agenda.pesquisar("999");
        assertEquals(resultadoExato, resultado,
            "Deve ser possível encontrar contatos na lista de contatos se o padrão conrresponder a qualquer nome, identificado ou telefones");
    }

    @Test
    public void testPesquisandoContatosSemNenhumResultado(){
        Agenda agenda = new Agenda();
        Contato contato = new Contato("Ana");
        Contato contato1 = new Contato("Adriele");
        Contato contato2 = new Contato("Ariele");
        assertTrue(contato.adicionarFone(new Fone(Identificador.TRABALHO, "(59)19536-9999")),
            "Deve ser possível adicionar um fone a um conato se o número estiver correto.");
        assertTrue(contato1.adicionarFone(new Fone(Identificador.CELULAR, "(46)40354-9846")),
            "Deve ser possível adicionar um fone a um conato se o número estiver correto.");
        assertTrue(contato2.adicionarFone(new Fone(Identificador.TRABALHO, "(37)44338-4811")),
            "Deve ser possível adicionar um fone a um conato se o número estiver correto.");
        assertTrue(agenda.adicionarContato(contato),
            "Deve ser possível adcionar um contato na lista de contatos se o nome ainda não existir.");
        assertTrue(agenda.adicionarContato(contato1),
            "Deve ser possível adcionar um contato na lista de contatos se o nome ainda não existir.");
        assertTrue(agenda.adicionarContato(contato2),
            "Deve ser possível adcionar um contato na lista de contatos se o nome ainda não existir.");
        List<Contato> resultadoExato = new ArrayList<>();
        List<Contato> resultado = agenda.pesquisar("xyz");
        assertEquals(resultadoExato, resultado,
            "Não deve ser possível retornar nenhum resultado de pesquisa se o padrão não conrresponder a nenhum nome, identificador ou telefone.");
    }

    @Test
    public void testAgendaEmOrdemAlfabetica(){
        Agenda agenda = new Agenda();
        Contato contato = new Contato("Carlos");
        Contato contato1 = new Contato("Adriele");
        Contato contato2 = new Contato("Biatriz");
        assertTrue(contato.adicionarFone(new Fone(Identificador.TRABALHO, "(59)19536-9999")),
                "Deve ser possível adicionar um fone a um conato se o número estiver correto.");
        assertTrue(contato1.adicionarFone(new Fone(Identificador.CELULAR, "(46)40354-9846")),
                "Deve ser possível adicionar um fone a um conato se o número estiver correto.");
        assertTrue(contato2.adicionarFone(new Fone(Identificador.TRABALHO, "(37)44338-4811")),
                "Deve ser possível adicionar um fone a um conato se o número estiver correto.");
        assertTrue(agenda.adicionarContato(contato),
            "Deve ser possível adcionar um contato na lista de contatos se o nome ainda não existir.");
        assertTrue(agenda.adicionarContato(contato1),
            "Deve ser possível adcionar um contato na lista de contatos se o nome ainda não existir.");
        assertTrue(agenda.adicionarContato(contato2),
            "Deve ser possível adcionar um contato na lista de contatos se o nome ainda não existir.");
        List<Contato> listaEsperada = Arrays.asList(contato1, contato2, contato);
        List<Contato> lista = agenda.getContatos();
        assertEquals(listaEsperada, lista);
    }

    @Test
    public void testQuantidadeDeTelefones(){
        Agenda agenda = new Agenda();
        Contato contato = new Contato("Carlos");
        Contato contato1 = new Contato("Adriele");
        assertTrue(contato.adicionarFone(new Fone(Identificador.TRABALHO, "(18)12329-5538")),
            "Deve ser possível adicionar um fone a um conato se o número estiver correto.");
        assertTrue(contato.adicionarFone(new Fone(Identificador.CELULAR, "(70)33126-6144")),
            "Deve ser possível adicionar um fone a um conato se o número estiver correto.");
        assertTrue(contato1.adicionarFone(new Fone(Identificador.TRABALHO, "(84)49197-8547")),
            "Deve ser possível adicionar um fone a um conato se o número estiver correto.");
        assertTrue(agenda.adicionarContato(contato),
            "Deve ser possível adcionar um contato na lista de contatos se o nome ainda não existir.");
        assertTrue(agenda.adicionarContato(contato1),
            "Deve ser possível adcionar um contato na lista de contatos se o nome ainda não existir.");
        assertEquals(2, agenda.getQuantidadeDeFones(Identificador.TRABALHO),
            "Deve ser possível pesquisar pela quantidade de fones com base nos identificador pesquisado.");
    }

    @Test
    public void testQuantidadeTotalDeTelefones(){
        Agenda agenda = new Agenda();
        Contato contato = new Contato("Alex");
        Contato contato1 = new Contato("Adriele");
        assertTrue(contato.adicionarFone(new Fone(Identificador.TRABALHO, "(18)12329-5538")),
            "Deve ser possível adicionar um fone a um conato se o número estiver correto.");
        assertTrue(contato.adicionarFone(new Fone(Identificador.CELULAR, "(70)33126-6144")),
            "Deve ser possível adicionar um fone a um conato se o número estiver correto.");
        assertTrue(contato1.adicionarFone(new Fone(Identificador.TRABALHO, "(84)49197-8547")),
            "Deve ser possível adicionar um fone a um conato se o número estiver correto.");
        assertTrue(contato1.adicionarFone(new Fone(Identificador.CASA, "(33)14397-2247")),
            "Deve ser possível adicionar um fone a um conato se o número estiver correto.");
        assertFalse(contato.adicionarFone(new Fone(Identificador.CASA, "(aa)1564-75863")), 
            "Não deve ser possível um numero de telefone que não contenha apenas os caracteres de - , () , . e digitos de 0-9.");
        assertTrue(agenda.adicionarContato(contato),
            "Deve ser possível adcionar um contato na lista de contatos se o nome ainda não existir.");
        assertTrue(agenda.adicionarContato(contato1),
            "Deve ser possível adcionar um contato na lista de contatos se o nome ainda não existir.");
        assertEquals(4, agenda.getQuantidadeDeFones(),
            "Deve ser possível pesquisar pela quantidade total de fones cadastrados na agenda.");
    }
}
