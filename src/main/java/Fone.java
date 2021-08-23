public class Fone {

    private Identificador id;
    private String numero;
    private boolean deuCerto = true;

    public Fone(Identificador identificador, String numero){
        if (numero.equals(" "))
            deuCerto = false;
        this.numero = numero;
        this.id = identificador;
    }

    public static boolean validarNumero(String numero){
        for(int i = 0; i < numero.length(); i++){
            var charr = numero.charAt(i);
            if(charr != '0' && charr != '1' && charr != '2' && charr != '3' && charr != '4' && charr != '5' && charr != '6'
                    && charr != '7' && charr != '8' && charr != '9' && charr != '(' && charr != ')' && charr != '-')
                return false;
        }
        return true;
    }

    public Identificador getIdentificador() {
        return id;
    }

    public String getNumero() {
        return numero;
    }
    @Override
    public String toString(){
        return getIdentificador() + this.numero;
    }

}