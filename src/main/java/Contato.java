import java.lang.reflect.Field;
import java.util.ArrayList;

public class Contato {

    private ArrayList<Fone> fones;

    private final String nome;

    public Contato(String name){
        this.nome = name;
        fones = new ArrayList<>();
    }

    public String getName() {
        return nome;
    }

    public int getQuantidadeFones(){
        return fones.size();
    }

    public ArrayList<Fone> getFones() {
        return fones;
    }

    public boolean adicionarFone(Fone fone){
        if(Fone.validarNumero(fone.getNumero())){
            fones.add(fone);
            return true;
        }
        return false;
    }

    public boolean removerFone(int index){
        if(index >= fones.size() || index < 0)
            return false;
        fones.remove(index);
        return true;
    }

    @Override
    public String toString(){
        return this.nome + fones;
    }

}