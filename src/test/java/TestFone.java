import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TestFone {

    @Test
    public void testInicializacao(){
        Fone fone = new Fone(Identificador.CASA, "(88)22161-5300");
        Fone fone1 = new Fone(Identificador.TRABALHO, "(88)93162-5665");
        Fone fone2 = new Fone(Identificador.CELULAR, "(35)90429-3605");
        Fone fone3 = new Fone(Identificador.CASA, "(76)95862-9705");
        Fone fone4 = new Fone(Identificador.TRABALHO, "(45)94200-0844");
        Fone fone5 = new Fone(Identificador.CASA, "(59)94762-3373");
        assertEquals(Identificador.CASA, fone.getIdentificador(),
            "Ao inicializar um fone, o seu identificador deve ser igual ao informado no construtor.");
        assertEquals(Identificador.TRABALHO, fone1.getIdentificador(),
            "Ao inicializar um fone, o seu identificador deve ser igual ao informado no construtor.");
        assertEquals(Identificador.CELULAR, fone2.getIdentificador(),
            "Ao inicializar um fone, o seu identificador deve ser igual ao informado no construtor.");
        assertEquals(Identificador.CASA, fone3.getIdentificador(),
            "Ao inicializar um fone, o seu identificador deve ser igual ao informado no construtor.");
        assertEquals(Identificador.TRABALHO, fone4.getIdentificador(),
            "Ao inicializar um fone, o seu identificador deve ser igual ao informado no construtor.");
        assertEquals(Identificador.CASA, fone5.getIdentificador(),
            "Ao inicializar um fone, o seu identificador deve ser igual ao informado no construtor.");
        assertEquals("(88)22161-5300", fone.getNumero(),
            "Ao inicializar um fone, o seu numero deve ser igual ao informado no construtor.");
    }

    @Test
    public void testValidarFoneCorretamente1(){
        Fone fone = new Fone(Identificador.CASA, "(88)22161-5300");
        assertTrue(Fone.validarNumero(fone.getNumero()), 
            "Deve ser possível um numero de telefone que contenha apenas os caracteres - , () , . e digitos de 0-9.");
    }

    @Test
    public void testValidarFoneCorretamente2(){
        Fone fone = new Fone(Identificador.CASA, "88221615300");
        assertTrue(Fone.validarNumero(fone.getNumero()), 
            "Deve ser possível um numero de telefone que contenha apenas os caracteres - , () , . e digitos de 0-9.");
    }

    @Test
    public void testValidarFoneCorretamente3(){
        Fone fone = new Fone(Identificador.CASA, "15642-15(77)");
        assertTrue(Fone.validarNumero(fone.getNumero()), 
            "Deve ser possível um numero de telefone que contenha apenas os caracteres - , () , . e digitos de 0-9.");
    }

    @Test
    public void testInvalidarFoneIncorreto1(){
        Fone fone = new Fone(Identificador.CASA, "(99)1564-124a");
        assertFalse(Fone.validarNumero(fone.getNumero()), 
            "Não deve ser possível um numero de telefone que não contenha apenas os caracteres de - , () , . e digitos de 0-9.");
        
    }

    @Test
    public void testInvalidarFoneIncorreto2(){
        Fone fone = new Fone(Identificador.CASA, "991564.124a[]");
        assertFalse(Fone.validarNumero(fone.getNumero()), 
            "Não deve ser possível um numero de telefone que não contenha apenas os caracteres de - , () , . e digitos de 0-9.");
    }

    @Test
    public void testInvalidarFoneIncorreto3(){
        Fone fone = new Fone(Identificador.CASA, "numero123");
        assertFalse(Fone.validarNumero(fone.getNumero()), 
            "Não deve ser possível um numero de telefone que não contenha apenas os caracteres de - , () , . e digitos de 0-9.");
        
    }
}