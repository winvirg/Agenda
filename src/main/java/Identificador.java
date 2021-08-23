public enum Identificador {
    
    CELULAR("Celular"),
    CASA("Casa"),
    TRABALHO("Trabalho");

    private String identificador;

    Identificador(String nome){
        this.identificador = nome;
    }

    @Override
    public String toString() {
        return this.identificador;
    }
}