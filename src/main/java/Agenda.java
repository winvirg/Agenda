import java.util.*;

public class Agenda {

    private List<Contato> listContatos;
    private Map<String, Contato> contatosMap;
    private ArrayList<Contato> mesmaExpressão;

    public Agenda(){
    	listContatos = new ArrayList<>();
        contatosMap = new HashMap<>();
        mesmaExpressão = new ArrayList();
    }

    public List<Contato> getContatos() {
        return listContatos;
    }

    public int getQuantidadeDeContatos() {
        return listContatos.size();
    }

    public Contato getContato(String name){
        return contatosMap.get(name);
    }

    public boolean adicionarContato(Contato contato){
        if(contato.getFones().isEmpty())
            return false;
        if (contatosMap.containsKey(contato.getName())){
            var contatoExistente = contatosMap.get(contato.getName());     
            listContatos.remove(contatoExistente);
            for(Fone fone : contato.getFones())
                contatoExistente.adicionarFone(fone);
            listContatos.add(contatoExistente);
            listContatos.sort(Comparator.comparing(Contato::getName));
            return false;
        }
        contatosMap.put(contato.getName(),contato);
        listContatos.add(contato);
        listContatos.sort(Comparator.comparing(Contato::getName));
        return true;
    }

    public boolean removerContato(String name){
        if(!contatosMap.containsKey(name))
            return false;
        var contato = contatosMap.get(name);
        listContatos.remove(contato);
        contatosMap.remove(name);
        return true;
    }

    public boolean removerFone(String name, int index){
        if(!contatosMap.containsKey(name))
            return false;
        var contato = contatosMap.get(name);
        if(index >= contato.getQuantidadeFones() || index < 0)
            return false;
        contato.removerFone(index);
        return true;
    }

    public int getQuantidadeDeFones(Identificador identificador){
        int quant = 0;
        for (Contato contato : listContatos)
            for(Fone fone: contato.getFones())
                if(fone.getIdentificador().equals(identificador))
                    quant++;
        return quant;
    }

    public int getQuantidadeDeFones(){
        int quant = 0;
        for(Contato contato : listContatos)
            quant += contato.getQuantidadeFones();
        return quant;
    }

    public ArrayList<Contato> pesquisar(String expressao){
    	mesmaExpressão.clear();
        for (Contato contato : listContatos) {
            var contatoInfo = contato.getFones().stream();
            if (contato.getName().contains(expressao))
            	mesmaExpressão.add(contato);
            else if (contatoInfo.anyMatch(fone -> fone.getNumero().contains(expressao)))
            	mesmaExpressão.add(contato);
        }
        mesmaExpressão.sort(Comparator.comparing(Contato::getName));
        return mesmaExpressão;
    }

    @Override
    public String toString(){
        StringBuilder contatos = new StringBuilder();
        for(Contato contato : listContatos)
            contatos.append(contato.getName()).append(contato.getFones()).append("\n");
        return contatos.toString();
    }

}