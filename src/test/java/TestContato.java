import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TestContato {
    
    @Test
    public void testInicializacao(){
        Contato contato = new Contato("Alex");
        assertEquals("Alex", contato.getName(),
            "Ao inicializar um contato, o seu nome deve ser igual ao informado no construtor.");
        assertEquals(0, contato.getQuantidadeFones(),
            "Ao inicializar um contato, não deve haver fones na lista de fones.");
    }

    @Test
    public void testAdicinarFoneComNumeroCorreto(){
        Contato contato = new Contato("Alex");
        assertTrue(contato.adicionarFone(new Fone(Identificador.CASA, "(59)19536-2054")),
            "Deve ser possível adicionar um fone a um conato se o número estiver correto.");
        assertTrue(contato.adicionarFone(new Fone(Identificador.TRABALHO, "(16)69902-3026")),
            "Deve ser possível adicionar um fone a um conato se o número estiver correto.");
    }

    @Test
    public void testAdicionarFoneComNumeroIncorreto(){
        Contato contato = new Contato("Alex");
        assertFalse(contato.adicionarFone(new Fone(Identificador.CASA, "(59)195.36-20[5]4")),
            "Não deve ser possível adicionar um fone a um contato se o número estiver incorreto.");
        assertFalse(contato.adicionarFone(new Fone(Identificador.TRABALHO, "(59)num1597-753")),
            "Não deve ser possível adicionar um fone a um contato se o número estiver incorreto.");
    }

    @Test
    public void testRemoverFoneComIndexCorreto(){
        Contato contato = new Contato("Alex");
        assertTrue(contato.adicionarFone(new Fone(Identificador.CASA, "(59)19536-2054")),
            "Deve ser possível adicionar um fone a um conato se o número estiver correto.");
        assertTrue(contato.adicionarFone(new Fone(Identificador.TRABALHO, "(14)49574-2545")),
            "Deve ser possível adicionar um fone a um conato se o número estiver correto.");
        assertTrue(contato.removerFone(0),
            "Deve ser possível remover um contato se o index informado estiver presente na lista de fones.");
        assertTrue(contato.adicionarFone(new Fone(Identificador.CELULAR, "(71)22666-0341")),
            "Deve ser possível adicionar um fone a um conato se o número estiver correto.");
        assertTrue(contato.removerFone(1),
            "Deve ser possível remover um contato se o index informado estiver presente na lista de fones.");
    }

    @Test
    public void testRemoverFoneComIndexIncorreto(){
        Contato contato = new Contato("Alex");
        assertTrue(contato.adicionarFone(new Fone(Identificador.CASA, "(59)19536-2054")),
            "Deve ser possível adicionar um fone a um conato se o número estiver correto.");
        assertFalse(contato.removerFone(2),
            "Não deve ser possível remover um contato se o index informado não estiver presente na lista de fones.");
        assertFalse(contato.removerFone(-1),
            "Não deve ser possível remover um contato se o index informado não estiver presente na lista de fones.");
    }
}
