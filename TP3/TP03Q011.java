import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Date;
import java.util.ArrayList;
import java.io.*;

class PilhaSequencial {
    private Personagem[] itens;
    private int topo;
    private int capacidade;
    private Personagem pesquisa;

    // Construtor para inicializar a pilha com uma capacidade específica
    public PilhaSequencial(int capacidade) {
        pesquisa = new Personagem();
        this.capacidade = capacidade;
        itens = new Personagem[capacidade];
        topo = -1;
    }

    // Função para verificar se a pilha está cheia
    public boolean estaCheia() {
        return topo == capacidade - 1;
    }

    // Função para verificar se a pilha está vazia
    public boolean estaVazia() {
        return topo == -1;
    }

    // Função para empilhar um elemento na pilha
    public void empilhar(Personagem personagem) {
        if (estaCheia()) {
            System.out.println("A pilha está cheia.");
            return;
        }
        itens[++topo] = personagem;
    }

    // Função para desempilhar um elemento da pilha
    public Personagem desempilhar() {
        if (estaVazia()) {
            System.out.println("A pilha está vazia.");
        }
        System.out.println("(R) " + itens[topo--].getName());
        return itens[topo];
    }

    // Função para ver o elemento no topo da pilha sem removê-lo
    public Personagem topo() {
        if (estaVazia()) {
            System.out.println("A pilha está vazia.");
        }
        return itens[topo];
    }

    // Função para imprimir os elementos da pilha
    public void imprimirPilha() {
        int j = 0;
        System.out.println("[ Top ]");
        for (int i = topo; i >= 0; i--) {
            SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
            String formattedDateOfBirth = dateFormatter.format(itens[i].getDatOfBirth());

            if (itens[i].getDatOfBirth() != null) {
                formattedDateOfBirth = dateFormatter.format(itens[i].getDatOfBirth());
            }

            System.out.print("[" + j + " ## " + itens[i].getId() + " ## ");
            System.out.print(itens[i].getName() + " ## ");
            System.out.print(itens[i].getAlternateNames() + " ## ");
            System.out.print(itens[i].getHouse() + " ## ");
            System.out.print(itens[i].getAncestry() + " ## ");
            System.out.print(itens[i].getSpecies() + " ## ");
            System.out.print(itens[i].getPatronus() + " ## ");
            System.out.print(itens[i].getHogwartsStaff() + " ## ");
            System.out.print(itens[i].getHogwartsStudent() + " ## ");
            System.out.print(itens[i].getActorName() + " ## ");
            System.out.print(itens[i].getAlive() + " ## ");
            System.out.print(formattedDateOfBirth + " ## ");
            System.out.print(itens[i].getYearOfBirth() + " ## ");
            System.out.print(itens[i].getEyeColour() + " ## ");
            System.out.print(itens[i].getGender() + " ## ");
            System.out.print(itens[i].getHairColour() + " ## ");
            System.out.println(itens[i].getWizard() + "]");
            j++;
        }

        System.out.println("[ Bottom ]");
    }

    public void desmembraArray(ArrayList<String> id, int tam) throws Exception {
        for (int i = 0; i < tam; i++) {
            String[] partes = id.get(i).split(" ");
            if (partes[0].compareTo("I") == 0) {
                pesquisa = pesquisa.pesquisar(partes[1]);
                empilhar(pesquisa);
            } else if (partes[0].compareTo("R") == 0) {
                desempilhar();
            }
        }
    }
}

class Lista {
    private Personagem[] personagem;
    private Personagem pesquisa;
    private int n;

    /**
     * Construtor da classe.
     */
    public Lista() {
        this(6);
    }

    /**
     * Construtor da classe.
     * 
     * @param tamanho Tamanho da lista.
     */
    public Lista(int tamanho) {
        personagem = new Personagem[tamanho];
        pesquisa = new Personagem();
        n = 0;
    }

    /**
     * Insere um elemento na primeira posicao da lista e move os demais
     * elementos para o fim da lista.
     * 
     * @param x int elemento a ser inserido.
     * @throws Exception Se a lista estiver cheia.
     */
    public void inserirInicio(Personagem personagem) throws Exception {

        // validar insercao
        if (n >= this.personagem.length) {
            throw new Exception("Erro ao inserir!");
        }

        // levar elementos para o fim do array
        for (int i = n; i > 0; i--) {
            this.personagem[i] = this.personagem[i - 1];
        }

        this.personagem[0] = personagem;
        n++;
    }

    /**
     * Insere um elemento na ultima posicao da lista.
     * 
     * @param x int elemento a ser inserido.
     * @throws Exception Se a lista estiver cheia.
     */
    public void inserirFim(Personagem personagem) throws Exception {

        // validar insercao
        if (n >= this.personagem.length) {
            throw new Exception("Erro ao inserir!");
        }

        this.personagem[n] = personagem;
        n++;
    }

    /**
     * Insere um elemento em uma posicao especifica e move os demais
     * elementos para o fim da lista.
     * 
     * @param x   int elemento a ser inserido.
     * @param pos Posicao de insercao.
     * @throws Exception Se a lista estiver cheia ou a posicao invalida.
     */
    public void inserir(Personagem personagem, int pos) throws Exception {

        // validar insercao
        if (n >= this.personagem.length || pos < 0 || pos > n) {
            throw new Exception("Erro ao inserir!");
        }

        // levar elementos para o fim do array
        for (int i = n; i > pos; i--) {
            this.personagem[i] = this.personagem[i - 1];
        }

        this.personagem[pos] = personagem;
        n++;
    }

    /**
     * Remove um elemento da primeira posicao da lista e movimenta
     * os demais elementos para o inicio da mesma.
     * 
     * @return resp int elemento a ser removido.
     * @throws Exception Se a lista estiver vazia.
     */
    public Personagem removerInicio() throws Exception {

        // validar remocao
        if (n == 0) {
            throw new Exception("Erro ao remover!");
        }

        Personagem resp = this.personagem[0];
        n--;

        for (int i = 0; i < n; i++) {
            this.personagem[i] = this.personagem[i + 1];
        }

        System.out.println("(R) " + resp.getName());

        return resp;
    }

    /**
     * Remove um elemento da ultima posicao da lista.
     * 
     * @return resp int elemento a ser removido.
     * @throws Exception Se a lista estiver vazia.
     */
    public Personagem removerFim() throws Exception {

        // validar remocao
        if (n == 0) {
            throw new Exception("Erro ao remover!");
        }

        System.out.println("(R) " + this.personagem[n]);

        return this.personagem[--n];
    }

    /**
     * Remove um elemento de uma posicao especifica da lista e
     * movimenta os demais elementos para o inicio da mesma.
     * 
     * @param pos Posicao de remocao.
     * @return resp int elemento a ser removido.
     * @throws Exception Se a lista estiver vazia ou a posicao for invalida.
     */
    public Personagem remover(int pos) throws Exception {

        // validar remocao
        if (n == 0 || pos < 0 || pos >= n) {
            throw new Exception("Erro ao remover!");
        }

        Personagem resp = this.personagem[pos];
        n--;

        for (int i = pos; i < n; i++) {
            this.personagem[i] = this.personagem[i + 1];
        }

        System.out.println("(R) " + resp.getName());

        return resp;
    }

    /**
     * Mostra os elementos da lista separados por espacos.
     */
    public void mostrar() {

        for (int i = 0; i < n; i++) {
            SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
            String formattedDateOfBirth = dateFormatter.format(personagem[i].getDatOfBirth());

            if (personagem[i].getDatOfBirth() != null) {
                formattedDateOfBirth = dateFormatter.format(personagem[i].getDatOfBirth());
            }

            System.out.print("[" + i + " ## " + personagem[i].getId() + " ## ");
            System.out.print(personagem[i].getName() + " ## ");
            System.out.print(personagem[i].getAlternateNames() + " ## ");
            System.out.print(personagem[i].getHouse() + " ## ");
            System.out.print(personagem[i].getAncestry() + " ## ");
            System.out.print(personagem[i].getSpecies() + " ## ");
            System.out.print(personagem[i].getPatronus() + " ## ");
            System.out.print(personagem[i].getHogwartsStaff() + " ## ");
            System.out.print(personagem[i].getHogwartsStudent() + " ## ");
            System.out.print(personagem[i].getActorName() + " ## ");
            System.out.print(personagem[i].getAlive() + " ## ");
            System.out.print(formattedDateOfBirth + " ## ");
            System.out.print(personagem[i].getYearOfBirth() + " ## ");
            System.out.print(personagem[i].getEyeColour() + " ## ");
            System.out.print(personagem[i].getGender() + " ## ");
            System.out.print(personagem[i].getHairColour() + " ## ");
            System.out.println(personagem[i].getWizard() + "]");
        }
    }

    public void desmembraArray(ArrayList<String> id, int tam) throws Exception {
        for (int i = 0; i < tam; i++) {
            String[] partes = id.get(i).split(" ");
            if (partes[0].compareTo("II") == 0) {
                pesquisa = pesquisa.pesquisar(partes[1]);
                inserirInicio(pesquisa);
            } else if (partes[0].compareTo("I*") == 0) {
                pesquisa = pesquisa.pesquisar(partes[2]);
                int pos = Integer.parseInt(partes[1]);
                inserir(pesquisa, pos);
            } else if (partes[0].compareTo("IF") == 0) {
                pesquisa = pesquisa.pesquisar(partes[1]);
                inserirFim(pesquisa);
            } else if (partes[0].compareTo("RI") == 0) {
                removerInicio();
            } else if (partes[0].compareTo("R*") == 0) {
                int pos = Integer.parseInt(partes[1]);
                remover(pos);
            } else if (partes[0].compareTo("RF") == 0) {
                removerFim();
            }

        }
    }

}

class Celula {
    private Personagem personagem;
    private Celula prox, ant;

    // construtor

    public Celula(Personagem personagem) {
        this(personagem, null, null);
    }

    public Celula(Personagem personagem, Celula prox, Celula ant) {
        this.personagem = personagem;
        this.prox = prox;
        this.ant = ant;
    }

    // gets

    public Personagem getPersonagem() {
        return this.personagem;
    }

    public Celula getProx() {
        return this.prox;
    }

    public Celula getAnt() {
        return this.ant;
    }

    // sets

    public void setPersonagem(Personagem personagem) {
        this.personagem = personagem;
    }

    public void setProx(Celula prox) {
        this.prox = prox;
    }

    public void setAnt(Celula ant) {
        this.ant = ant;
    }

}

class CelulaSimples {
    private Personagem personagem;
    private CelulaSimples prox;

    // construtor
    public CelulaSimples(Personagem personagem) {
        this(personagem, null);
    }

    public CelulaSimples(Personagem personagem, CelulaSimples prox) {
        this.personagem = personagem;
        this.prox = prox;
    }

    // gets

    public Personagem getPersonagem() {
        return this.personagem;
    }

    public CelulaSimples getProx() {
        return this.prox;
    }

    // sets

    public void setPersonagem(Personagem personagem) {
        this.personagem = personagem;
    }

    public void setProx(CelulaSimples prox) {
        this.prox = prox;
    }
}

class ListaSimples {
    private CelulaSimples primeiro, ultimo;

    public ListaSimples() {
        this.primeiro = new CelulaSimples(new Personagem());
        this.ultimo = this.primeiro;
    }

    public void inserirFim(Personagem personagem) {
        ultimo.setProx(new CelulaSimples(personagem));
        ultimo = ultimo.getProx();
    }

    public void inserirInicio(Personagem personagem) {
        CelulaSimples tmp = new CelulaSimples(personagem);
        tmp.setProx(primeiro.getProx());
        primeiro.setProx(tmp);
        if (primeiro == ultimo) {
            ultimo = tmp;
        }
        tmp = null;
    }

    public void inserir(Personagem personagem, int pos) throws Exception {
        if (pos < 0 || pos > tamanho()) {
            throw new Exception("Erro ao inserir");
        } else if (pos == 0) {
            inserirInicio(personagem);
        } else if (pos == tamanho()) {
            inserirFim(personagem);
        } else {
            CelulaSimples i = primeiro;
            int j;

            for (j = 0; j < pos; j++, i = i.getProx())
                ;
            CelulaSimples tmp = new CelulaSimples(personagem);
            tmp.setProx(i.getProx());
            i.setProx(tmp);
            i = null;
            tmp = null;
        }
    }

    public int tamanho() {
        CelulaSimples i;
        int j = 0;
        for (i = primeiro; i != null; i = i.getProx(), j++)
            ;
        return j;
    }

    public Personagem removerInicio() {
        CelulaSimples tmp;
        tmp = primeiro;
        primeiro = primeiro.getProx();
        Personagem resp = tmp.getPersonagem();
        tmp.setProx(null);
        tmp = null;
        System.out.println(("(R) " + resp.getName()));
        return resp;
    }

    public Personagem removerFim() {
        CelulaSimples i;
        for (i = primeiro; i.getProx() != ultimo; i = i.getProx())
            ;
        Personagem resp = i.getProx().getPersonagem();
        ultimo = i;
        System.out.println(("(R) ") + resp.getName());
        i.setProx(null);
        i = null;

        return resp;
    }

    public Personagem remover(int pos) throws Exception {
        Personagem resp = new Personagem();
        if (pos < 0 || pos > tamanho()) {
            throw new Exception("Erro ao inserir");
        } else if (pos == 0) {
            resp = removerInicio();
        } else if (pos == tamanho()) {
            resp = removerFim();
        } else {
            CelulaSimples i = primeiro;
            int j;

            for (j = 0; j < pos; j++, i = i.getProx())
                ;
            resp = i.getProx().getPersonagem();
            CelulaSimples tmp = i.getProx();
            i.setProx(tmp.getProx());
            tmp.setProx(null);
            i = null;
            tmp = null;
        }
        System.out.println("(R) " + resp.getName());
        return resp;
    }

    public void mostrar() {
        CelulaSimples i;
        int j = 0;
        for (i = primeiro.getProx(); i != null; i = i.getProx(), j++) {
            SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
            String formattedDateOfBirth = dateFormatter.format(i.getPersonagem().getDatOfBirth());

            if (i.getPersonagem().getDatOfBirth() != null) {
                formattedDateOfBirth = dateFormatter.format(i.getPersonagem().getDatOfBirth());
            }

            System.out.print("[" + j + " ## " + i.getPersonagem().getId() + " ## ");
            System.out.print(i.getPersonagem().getName() + " ## ");
            System.out.print(i.getPersonagem().getAlternateNames() + " ## ");
            System.out.print(i.getPersonagem().getHouse() + " ## ");
            System.out.print(i.getPersonagem().getAncestry() + " ## ");
            System.out.print(i.getPersonagem().getSpecies() + " ## ");
            System.out.print(i.getPersonagem().getPatronus() + " ## ");
            System.out.print(i.getPersonagem().getHogwartsStaff() + " ## ");
            System.out.print(i.getPersonagem().getHogwartsStudent() + " ## ");
            System.out.print(i.getPersonagem().getActorName() + " ## ");
            System.out.print(i.getPersonagem().getAlive() + " ## ");
            System.out.print(formattedDateOfBirth + " ## ");
            System.out.print(i.getPersonagem().getYearOfBirth() + " ## ");
            System.out.print(i.getPersonagem().getEyeColour() + " ## ");
            System.out.print(i.getPersonagem().getGender() + " ## ");
            System.out.print(i.getPersonagem().getHairColour() + " ## ");
            System.out.println(i.getPersonagem().getWizard() + "]");
        }
    }

    public void desmembraArray(ArrayList<String> id, int tam) throws Exception {
        Personagem pesquisa = new Personagem();
        for (int i = 0; i < tam; i++) {
            String[] partes = id.get(i).split(" ");
            if (partes[0].compareTo("II") == 0) {
                pesquisa = pesquisa.pesquisar(partes[1]);
                inserirInicio(pesquisa);
            } else if (partes[0].compareTo("I*") == 0) {
                pesquisa = pesquisa.pesquisar(partes[2]);
                int pos = Integer.parseInt(partes[1]);
                inserir(pesquisa, pos);
            } else if (partes[0].compareTo("IF") == 0) {
                pesquisa = pesquisa.pesquisar(partes[1]);
                inserirFim(pesquisa);
            } else if (partes[0].compareTo("RI") == 0) {
                removerInicio();
            } else if (partes[0].compareTo("R*") == 0) {
                int pos = Integer.parseInt(partes[1]);
                remover(pos);
            } else if (partes[0].compareTo("RF") == 0) {
                removerFim();
            }
        }
    }

}

class ListaDuplaPersonagens {
    private Celula primeiro, ultimo;

    Personagem pesquisa = new Personagem();

    public ListaDuplaPersonagens() {
        this.primeiro = new Celula(new Personagem());
        this.ultimo = this.primeiro;
    }

    public void inserirInicio(Personagem personagem) {
        Celula tmp = new Celula(personagem);
        tmp.setAnt(primeiro);
        tmp.setProx(primeiro.getProx());
        primeiro.setProx(tmp);
        if (primeiro == ultimo) {
            ultimo = tmp;
        } else {
            tmp.getProx().setAnt(tmp);
        }
        tmp = null;
    }

    public void inserirFim(Personagem personagem) {
        ultimo.setProx(new Celula(personagem));
        ultimo.getProx().setAnt(ultimo);
        ultimo = ultimo.getProx();
    }

    public void inserir(Personagem i, int pos) throws Exception {
        if (pos < 0 || pos > tamanho() - 1) {
            throw new Exception("Maior que o tamanho da lista");
        } else if (pos == 0) {
            inserirInicio(i);
        } else if (pos == tamanho() - 1) {
            inserirFim(i);
        } else {
            Celula c = primeiro;
            int j;
            for (j = 0; j < pos; j++, c = c.getProx())
                ;

            Celula tmp = new Celula(i);
            tmp.setAnt(c);
            tmp.setProx(c.getProx());
            tmp.getAnt().setProx(tmp);
            tmp.getProx().setAnt(tmp);
            c = null;
            tmp = null;
        }
    }

    public int tamanho() {
        Celula i;
        int j = 0;
        for (i = primeiro; i != null; i = i.getProx(), j++)
            ;
        return j;
    }

    public Personagem removerInicio() {
        Celula tmp = primeiro;
        Personagem personagem = tmp.getPersonagem();
        primeiro = primeiro.getProx();
        tmp.getProx().setAnt(null);
        tmp.setProx(null);
        tmp = null;
        System.out.println("(R) " + personagem.getName());
        return personagem;
    }

    public Personagem removerFim() {
        Celula tmp = ultimo;
        Personagem personagem = tmp.getPersonagem();
        ultimo = ultimo.getAnt();
        tmp.getAnt().setProx(null);
        tmp.setAnt(null);
        tmp = null;
        System.out.println("(R) " + personagem.getName());
        return personagem;
    }

    public Personagem remover(int pos) throws Exception {
        Personagem personagem;
        if (pos < 0 || pos > tamanho() - 1) {
            throw new Exception("Erro ao inserir na lista, tamanho grande");
        } else if (pos == 0) {
            personagem = removerInicio();
        } else if (pos == tamanho() - 1) {
            personagem = removerFim();
        } else {
            Celula i = primeiro;
            int j;
            for (j = 0; j < pos; j++, i = i.getProx())
                ;
            personagem = i.getPersonagem();
            i.getProx().setAnt(i.getAnt());
            i.getAnt().setProx(i.getProx());
            i = null;
        }
        System.out.println("(R) " + personagem.getName());
        return personagem;
    }

    // funções

    public void desmembraArray(ArrayList<String> id) throws Exception {
        for (int i = 0; i < id.size(); i++) {

            String[] partes = id.get(i).split(" ");

            if (partes[0] == "II") {
                pesquisa = pesquisa.pesquisar(partes[1]);
                inserirInicio(pesquisa);
            } else if (partes[0] == "I*") {
                pesquisa = pesquisa.pesquisar(partes[2]);
                int pos = Integer.parseInt(partes[1]);
                inserir(pesquisa, pos);
            } else if (partes[0] == "IF") {
                pesquisa = pesquisa.pesquisar(partes[1]);
                inserirFim(pesquisa);
            } else if (partes[0] == "RI") {
                removerInicio();
            } else if (partes[0] == "R*") {
                int pos = Integer.parseInt(partes[1]);
                remover(pos);
            } else if (partes[0] == "RF") {
                removerFim();
            }

        }
    }

    public void QuickSort() {
        if (primeiro != null && primeiro.getProx() != ultimo) {
            QuickSort(primeiro.getProx(), ultimo);
        }
    }
    
    private void QuickSort(Celula left, Celula right) {
        if (left != right && left != right.getProx()) {
            Celula pivotCell = partition(left, right);
            if (pivotCell != null) {
                QuickSort(left, pivotCell.getAnt());
                QuickSort(pivotCell.getProx(), right);
            }
        }
    }
    
    private Celula partition(Celula left, Celula right) {
        Personagem pivot = left.getPersonagem();
        Celula i = left;
        Celula j = right;
    
        while (true) {
            while (i != right && comparePersonagens(i.getPersonagem(), pivot) <= 0) {
                i = i.getProx();
            }
    
            while (j != left && comparePersonagens(j.getPersonagem(), pivot) > 0) {
                j = j.getAnt();
            }
    
            if (i == null || j == null || i == j || j.getProx() == i) {
                break;
            }
    
            if (i != j) {
                // Swap elements at i and j
                Personagem temp = i.getPersonagem();
                i.setPersonagem(j.getPersonagem());
                j.setPersonagem(temp);
            }
        }
    
        // Swap the pivot element with j to put the pivot in its correct place
        if (j != null && left != j) {
            Personagem temp = left.getPersonagem();
            left.setPersonagem(j.getPersonagem());
            j.setPersonagem(temp);
        }
    
        return j;
    }
    
    private int comparePersonagens(Personagem a, Personagem b) {
        int houseComparison = a.getHouse().compareTo(b.getHouse());
        if (houseComparison != 0) {
            return houseComparison;
        } else {
            return a.getName().compareTo(b.getName());
        }
    }
    
    
    public void mostrar() {
        Celula i;
        int j = 0;
        for (i = primeiro.getProx(); i != null; i = i.getProx(), j++) {
            SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
            String formattedDateOfBirth = dateFormatter.format(i.getPersonagem().getDatOfBirth());

            if (i.getPersonagem().getDatOfBirth() != null) {
                formattedDateOfBirth = dateFormatter.format(i.getPersonagem().getDatOfBirth());
            }

            System.out.print("[" + i.getPersonagem().getId() + " ## ");
            System.out.print(i.getPersonagem().getName() + " ## ");
            System.out.print(i.getPersonagem().getAlternateNames() + " ## ");
            System.out.print(i.getPersonagem().getHouse() + " ## ");
            System.out.print(i.getPersonagem().getAncestry() + " ## ");
            System.out.print(i.getPersonagem().getSpecies() + " ## ");
            System.out.print(i.getPersonagem().getPatronus() + " ## ");
            System.out.print(i.getPersonagem().getHogwartsStaff() + " ## ");
            System.out.print(i.getPersonagem().getHogwartsStudent() + " ## ");
            System.out.print(i.getPersonagem().getActorName() + " ## ");
            System.out.print(i.getPersonagem().getAlive() + " ## ");
            System.out.print(formattedDateOfBirth + " ## ");
            System.out.print(i.getPersonagem().getYearOfBirth() + " ## ");
            System.out.print(i.getPersonagem().getEyeColour() + " ## ");
            System.out.print(i.getPersonagem().getGender() + " ## ");
            System.out.print(i.getPersonagem().getHairColour() + " ## ");
            System.out.println(i.getPersonagem().getWizard() + "]");
        }
    }
}

class PilhaFlexivel {
    private CelulaSimples topo;

    public PilhaFlexivel() {
        topo = null;
    }

    public void inserir(Personagem personagem) {
        CelulaSimples tmp = new CelulaSimples(personagem);
        tmp.setProx(topo);
        topo = tmp;
        tmp = null;
    }

    public void remover() {
        CelulaSimples tmp = topo;
        Personagem resp = tmp.getPersonagem();
        System.out.println(("(R) " + resp.getName()));
        topo = topo.getProx();
        tmp.setProx(null);
        tmp = null;
    }

    public void mostrar() {
        CelulaSimples i;
        int j = 0;

        System.out.println("[ Top ]");
        for (i = topo; i != null; i = i.getProx(), j++) {
            SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
            String formattedDateOfBirth = dateFormatter.format(i.getPersonagem().getDatOfBirth());

            if (i.getPersonagem().getDatOfBirth() != null) {
                formattedDateOfBirth = dateFormatter.format(i.getPersonagem().getDatOfBirth());
            }

            System.out.print("[" + j + " ## " + i.getPersonagem().getId() + " ## ");
            System.out.print(i.getPersonagem().getName() + " ## ");
            System.out.print(i.getPersonagem().getAlternateNames() + " ## ");
            System.out.print(i.getPersonagem().getHouse() + " ## ");
            System.out.print(i.getPersonagem().getAncestry() + " ## ");
            System.out.print(i.getPersonagem().getSpecies() + " ## ");
            System.out.print(i.getPersonagem().getPatronus() + " ## ");
            System.out.print(i.getPersonagem().getHogwartsStaff() + " ## ");
            System.out.print(i.getPersonagem().getHogwartsStudent() + " ## ");
            System.out.print(i.getPersonagem().getActorName() + " ## ");
            System.out.print(i.getPersonagem().getAlive() + " ## ");
            System.out.print(formattedDateOfBirth + " ## ");
            System.out.print(i.getPersonagem().getYearOfBirth() + " ## ");
            System.out.print(i.getPersonagem().getEyeColour() + " ## ");
            System.out.print(i.getPersonagem().getGender() + " ## ");
            System.out.print(i.getPersonagem().getHairColour() + " ## ");
            System.out.println(i.getPersonagem().getWizard() + "]");
        }

        System.out.println(" [ Bottom ]");
    }

    public void desmembraArray(ArrayList<String> id, int tam) throws Exception {
        Personagem pesquisa = new Personagem();
        for (int i = 0; i < tam; i++) {
            String[] partes = id.get(i).split(" ");
            if (partes[0].compareTo("I") == 0) {
                pesquisa = pesquisa.pesquisar(partes[1]);
                inserir(pesquisa);
            } else if (partes[0].compareTo("R") == 0) {
                remover();
            }
        }
    }

}

class Personagem implements Cloneable {

    // Privados
    private String id;
    private String name;
    private String alternate_names;
    private String house;
    private String ancestry;
    private String species;
    private String patronus;
    private boolean hogwartsStaff;
    private boolean hogwartsStudent;
    private String actorName;
    private boolean alive;
    private Date dateOfBirth;
    private int yearOfBirth;
    private String eyeColour;
    private String gender;
    private String hairColour;
    private boolean wizard;

    // Construtor
    public Personagem(String id, String name, String alternate_names, String house, String ancestry, String species,
            String patronus,
            boolean hogwartsStudent, String actorName, boolean alive, Date dateOfBirth, int yearOfBirth,
            String eyeColour, String gender, String hairColour, boolean wizard) {
        this.id = id;
        this.name = name;
        this.alternate_names = alternate_names;
        this.house = house;
        this.ancestry = ancestry;
        this.species = species;
        this.patronus = patronus;
        this.hogwartsStudent = hogwartsStudent;
        this.actorName = actorName;
        this.alive = alive;
        this.dateOfBirth = dateOfBirth;
        this.yearOfBirth = yearOfBirth;
        this.eyeColour = eyeColour;
        this.gender = gender;
        this.hairColour = hairColour;
        this.wizard = wizard;
    }

    public Personagem() {
    }

    // Gets
    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getAlternateNames() {
        return this.alternate_names;
    }

    public String getHouse() {
        return this.house;
    }

    public String getAncestry() {
        return this.ancestry;
    }

    public String getSpecies() {
        return this.species;
    }

    public String getPatronus() {
        return this.patronus;
    }

    public boolean getHogwartsStaff() {
        return this.hogwartsStaff;
    }

    public boolean getHogwartsStudent() {
        return this.hogwartsStudent;
    }

    public String getActorName() {
        return this.actorName;
    }

    public boolean getAlive() {
        return this.alive;
    }

    public Date getDatOfBirth() {
        return this.dateOfBirth;
    }

    public int getYearOfBirth() {
        return this.yearOfBirth;
    }

    public String getEyeColour() {
        return this.eyeColour;
    }

    public String getGender() {
        return this.gender;
    }

    public String getHairColour() {
        return this.hairColour;
    }

    public boolean getWizard() {
        return this.wizard;
    }

    // Sets

    public void setId(String Id) {
        if (Id == null || Id.equals("0")) {
            this.id = null;
        } else {
            this.id = Id;
        }
    }

    public void setName(String Name) {
        if (Name == null || Name.equals("0")) {
            this.name = null;
        } else {
            this.name = Name;
        }
    }

    public void setAlternate_Names(String Alternate_Names) {
        if (Alternate_Names == null || Alternate_Names.equals("0")) {
            this.alternate_names = null;
        } else if (Alternate_Names.equals("[]")) {
            this.alternate_names = "{}";
        } else {
            Alternate_Names = Alternate_Names.replaceAll("\\['", "{").replaceAll("']", "}").replaceAll("'", "");
            Alternate_Names = Alternate_Names.trim();
            this.alternate_names = Alternate_Names;
        }
    }

    public void setHouse(String House) {
        if (House == null || House.equals("0")) {
            this.house = null;
        } else {
            this.house = House;
        }
    }

    public void setAncestry(String Ancestry) {
        if (Ancestry == null || Ancestry.equals("0")) {
            this.ancestry = null;
        } else {
            this.ancestry = Ancestry;
        }
    }

    public void setSpecies(String Species) {
        if (Species == null || Species.equals("0")) {
            this.species = null;
        } else {
            this.species = Species;
        }
    }

    public void setPatronus(String Patronus) {
        if (Patronus == null || Patronus.equals("0")) {
            this.patronus = null;
        } else {
            this.patronus = Patronus;
        }
    }

    public void setHogwartsStaff(boolean HogwartsStaff) {
        this.hogwartsStaff = HogwartsStaff;
    }

    public void setHogwartsStudent(boolean HogwartsStudent) {
        this.hogwartsStudent = HogwartsStudent;
    }

    public void setActorName(String ActorName) {
        if (ActorName.equals("null") || ActorName.equals("0")) {
            this.actorName = null;
        } else {
            this.actorName = ActorName;
        }
    }

    public void setAlive(boolean Alive) {
        this.alive = Alive;
    }

    public void setDateOfBirth(Date DateOfBirth) {
        this.dateOfBirth = DateOfBirth;
    }

    public void setYearOfBirth(int YearOfBirth) {
        this.yearOfBirth = YearOfBirth;
    }

    public void setEyeColour(String EyeColour) {
        if (EyeColour == null || EyeColour.equals("0")) {
            this.eyeColour = null;
        } else {
            this.eyeColour = EyeColour;
        }
    }

    public void setGender(String Gender) {
        if (Gender == null || Gender.equals("0")) {
            this.gender = null;
        } else {
            this.gender = Gender;
        }
    }

    public void setHairColour(String HairColour) {
        if (HairColour == null || HairColour.equals("0")) {
            this.hairColour = null;
        } else {
            this.hairColour = HairColour;
        }
    }

    public void setWizard(boolean Wizard) {
        this.wizard = Wizard;
    }

    public int getMaiorAno(Personagem[] personagem) {
        int ano = personagem[0].getYearOfBirth();
        for (int i = 0; i < personagem.length; i++) {
            if (personagem[i].getYearOfBirth() > ano) {
                ano = personagem[i].getYearOfBirth();
            }
        }
        return ano;
    }

    public Personagem clone() {
        try {
            Personagem cloned = (Personagem) super.clone();

            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public void imprimir() {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDateOfBirth = dateFormatter.format(getDatOfBirth());

        if (getDatOfBirth() != null) {
            formattedDateOfBirth = dateFormatter.format(getDatOfBirth());
        }

        System.out.print("[" + id + " ## ");
        System.out.print(name + " ## ");
        System.out.print(alternate_names + " ## ");
        System.out.print(house + " ## ");
        System.out.print(ancestry + " ## ");
        System.out.print(species + " ## ");
        System.out.print(patronus + " ## ");
        System.out.print(hogwartsStaff + " ## ");
        System.out.print(hogwartsStudent + " ## ");
        System.out.print(actorName + " ## ");
        System.out.print(alive + " ## ");
        System.out.print(formattedDateOfBirth + " ## ");
        System.out.print(yearOfBirth + " ## ");
        System.out.print(eyeColour + " ## ");
        System.out.print(gender + " ## ");
        System.out.print(hairColour + " ## ");
        System.out.println(wizard + "]");
    }

    public String[] ler() {
        String LocalArquivo = "/tmp/characters.csv";
        StringBuilder personagens = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(LocalArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                personagens.append(linha).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String conteudo = personagens.toString();
        return conteudo.split("\n");
    }

    public Personagem pesquisar(String id) {
        Personagem personagem = new Personagem();

        String[] a = ler();
        for (int i = 0; i < a.length; i++) {
            String[] partes = a[i].split(";");
            if (partes[0].equals(id)) {
                personagem.setId(partes[0]);
                personagem.setName(partes[1]);
                personagem.setAlternate_Names(partes[2]);
                personagem.setHouse(partes[3]);
                personagem.setAncestry(partes[4]);
                personagem.setSpecies(partes[5]);
                personagem.setPatronus(partes[6]);
                if (partes[7].equals("VERDADEIRO")) {
                    personagem.setHogwartsStaff(true);
                } else {
                    personagem.setHogwartsStaff(false);
                }
                if (partes[8].equals("VERDADEIRO")) {
                    personagem.setHogwartsStudent(true);
                } else {
                    personagem.setHogwartsStudent(false);
                }
                personagem.setActorName(partes[9]);
                if (partes[10].equals("VERDADEIRO")) {
                    personagem.setAlive(true);
                } else {
                    personagem.setAlive(false);
                }
                SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
                try {
                    Date dateOfBirth = dateFormatter.parse(partes[12]);
                    personagem.setDateOfBirth(dateOfBirth);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                personagem.setYearOfBirth(Integer.parseInt(partes[13]));
                personagem.setEyeColour(partes[14]);
                personagem.setGender(partes[15]);
                personagem.setHairColour(partes[16]);
                if (partes[17].equals("VERDADEIRO")) {
                    personagem.setWizard(true);
                } else if (partes[17].equals("FALSO")) {
                    personagem.setWizard(false);
                }
            }
        }
        return personagem;
    }

    public int pesquisar(Personagem[] personagem, int index) {
        Scanner scanf = new Scanner(System.in);
        while (true) {
            String id = scanf.nextLine();
            if (id.equals("FIM")) {
                break;
            }
            String[] a = ler();
            for (int i = 0; i < a.length; i++) {
                String[] partes = a[i].split(";");
                if (partes[0].equals(id)) {
                    personagem[index].setId(partes[0]);
                    personagem[index].setName(partes[1]);
                    personagem[index].setAlternate_Names(partes[2]);
                    personagem[index].setHouse(partes[3]);
                    personagem[index].setAncestry(partes[4]);
                    personagem[index].setSpecies(partes[5]);
                    personagem[index].setPatronus(partes[6]);
                    if (partes[7].equals("VERDADEIRO")) {
                        personagem[index].setHogwartsStaff(true);
                    } else {
                        personagem[index].setHogwartsStaff(false);
                    }
                    if (partes[8].equals("VERDADEIRO")) {
                        personagem[index].setHogwartsStudent(true);
                    } else {
                        personagem[index].setHogwartsStudent(false);
                    }
                    personagem[index].setActorName(partes[9]);
                    if (partes[10].equals("VERDADEIRO")) {
                        personagem[index].setAlive(true);
                    } else {
                        personagem[index].setAlive(false);
                    }
                    SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
                    try {
                        Date dateOfBirth = dateFormatter.parse(partes[12]);
                        personagem[index].setDateOfBirth(dateOfBirth);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    personagem[index].setYearOfBirth(Integer.parseInt(partes[13]));
                    personagem[index].setEyeColour(partes[14]);
                    personagem[index].setGender(partes[15]);
                    personagem[index].setHairColour(partes[16]);
                    if (partes[17].equals("VERDADEIRO")) {
                        personagem[index].setWizard(true);
                    } else if (partes[17].equals("FALSO")) {
                        personagem[index].setWizard(false);
                    }
                }
            }
            index++;
        }
        scanf.close();
        return index;
    }

    public int[] InsertionSort() {
        int comparacoes = 0, movimentacoes = 0;
        SimpleDateFormat formatador = new SimpleDateFormat("dd-MM-yyyy");
        Personagem[] personagem = new Personagem[100];
        for (int i = 0; i < 100; i++) {
            personagem[i] = new Personagem();
        }

        int index = pesquisar(personagem, 0);

        for (int i = 1; i < index; i++) {
            Personagem tmp = personagem[i];
            int j = i - 1;
            while (j >= 0 && (personagem[j].getDatOfBirth().compareTo(tmp.getDatOfBirth()) > 0 ||
                    (personagem[j].getDatOfBirth().compareTo(tmp.getDatOfBirth()) == 0 &&
                            personagem[j].getName().compareTo(tmp.getName()) > 0))) {
                comparacoes++;
                movimentacoes++;
                personagem[j + 1] = personagem[j];
                j--;
            }
            personagem[j + 1] = tmp;
        }

        for (int i = 0; i < index; i++) {
            personagem[i].imprimir();
        }
        int[] arr = new int[2];
        arr[0] = comparacoes;
        arr[1] = movimentacoes;
        return arr;
    }

    public int[] InsertionSortParcial() {
        int comparacoes = 0, movimentacoes = 0;
        SimpleDateFormat formatador = new SimpleDateFormat("dd-MM-yyyy");
        Personagem[] personagem = new Personagem[100];
        for (int i = 0; i < 100; i++) {
            personagem[i] = new Personagem();
        }

        int index = pesquisar(personagem, 0);
        int k = 0;
        for (int i = 1; i < index; i++) {
            Personagem tmp = personagem[i];
            int j = i - 1;
            while (j >= 0 && (personagem[j].getDatOfBirth().compareTo(tmp.getDatOfBirth()) > 0 ||
                    (personagem[j].getDatOfBirth().compareTo(tmp.getDatOfBirth()) == 0 &&
                            personagem[j].getName().compareTo(tmp.getName()) > 0))) {
                comparacoes++;
                movimentacoes++;
                personagem[j + 1] = personagem[j];
                j--;
            }
            k++;
            personagem[j + 1] = tmp;
            if (k == 10) {
                i = index;
            }
        }

        for (int i = 0; i < 10; i++) {
            personagem[i].imprimir();
        }
        int[] arr = new int[2];
        arr[0] = comparacoes;
        arr[1] = movimentacoes;
        return arr;
    }

    public int[] SelectionSort() {
        int comparacoes = 0, movimentacoes = 0;
        Personagem[] personagem = new Personagem[100];
        for (int i = 0; i < 100; i++) {
            personagem[i] = new Personagem();
        }

        int index = pesquisar(personagem, 0);

        for (int i = 0; i < index; i++) {
            for (int j = i; j < index; j++) {
                comparacoes++;
                int strcmp = personagem[i].getName().compareTo(personagem[j].getName());
                if (strcmp > 0) {
                    Personagem aux = new Personagem();
                    aux = personagem[i];
                    personagem[i] = personagem[j];
                    personagem[j] = aux;
                    movimentacoes += 3;
                }
            }
        }

        for (int i = 0; i < index; i++) {
            personagem[i].imprimir();
        }

        int arr[] = { comparacoes, movimentacoes };
        return arr;
    }

    /*
     * public int[] SelectionSortParcial() {
     * int comparacoes = 0, movimentacoes = 0;
     * Personagem[] personagem = new Personagem[100];
     * for (int i = 0; i < 100; i++) {
     * personagem[i] = new Personagem();
     * }
     * 
     * int index = pesquisar(personagem, 0);
     * int k = 0;
     * for (int i = 0; i < index; i++) {
     * for (int j = i; j < index; j++) {
     * comparacoes++;
     * if (strcmp > 0) {
     * Personagem aux = new Personagem();
     * aux = personagem[i];
     * personagem[i] = personagem[j];
     * personagem[j] = aux;
     * movimentacoes += 3;
     * }
     * }
     * k++;
     * if(k == 10){
     * i = index;
     * }
     * }
     * 
     * for (int i = 0; i < 10; i++) {
     * personagem[i].imprimir();
     * }
     * 
     * int arr[] = { comparacoes, movimentacoes };
     * return arr;
     * }
     */

    public int[] CountingSort(Personagem[] personagem) {
        int comparacoes = 0, movimentacoes = 0;
        // Determinar o tamanho do array
        int index = pesquisar(personagem, 0);
        int maior = getMaiorAno(personagem);

        // Inicializar arrays de contagem e de saída
        int[] count = new int[maior + 1];
        Personagem[] ordenado = new Personagem[index];

        // Inicializar array de contagem com zeros
        for (int i = 0; i < count.length; i++) {
            count[i] = 0;
        }

        // Contar as ocorrências de cada elemento
        for (int i = 0; i < index; i++) {
            count[personagem[i].getYearOfBirth()]++;
            movimentacoes++;
        }

        // Ajustar o array de contagem
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
            movimentacoes++;
        }

        // Preencher o array ordenado
        for (int i = index - 1; i >= 0; i--) {
            movimentacoes += 2;
            ordenado[count[personagem[i].getYearOfBirth()] - 1] = personagem[i];
            count[personagem[i].getYearOfBirth()]--;
        }

        // Ordenar pelo nom e se os anos forem iguais (Selection Sort)
        for (int i = 0; i < index; i++) {
            for (int j = i; j < index; j++) {
                comparacoes++;
                if (ordenado[i].getYearOfBirth() == ordenado[j].getYearOfBirth()
                        && ordenado[i].getName().compareTo(ordenado[j].getName()) > 0) {
                    movimentacoes += 3;
                    Personagem tmp = ordenado[i];
                    ordenado[i] = ordenado[j];
                    ordenado[j] = tmp;
                }
            }
        }

        // Imprimir os resultados
        for (int i = 0; i < index; i++) {
            ordenado[i].imprimir();
        }
        int[] arr = new int[2];
        arr[0] = comparacoes;
        arr[1] = movimentacoes;
        return arr;
    }

    public int[] MergeSort(int esq, int dir, Personagem[] original, Personagem[] copia) {
        int[] arr = new int[2];
        if (esq < dir) {
            int meio = (dir + esq) / 2;
            MergeSort(esq, meio, original, copia);
            MergeSort(meio + 1, dir, original, copia);
            arr = intercalar(esq, meio, dir, original, copia);
        }
        return arr;
    }

    public int[] intercalar(int esq, int meio, int dir, Personagem[] array, Personagem[] copia) {
        int comparacoes = 0, movimentacoes = 0;
        for (int k = esq; k <= dir; k++) {
            copia[k] = array[k];
            movimentacoes++;
        }

        int i = esq;
        int j = meio + 1;

        for (int k = esq; k <= dir; k++) {
            if (i > meio) {
                comparacoes++;
                array[k] = copia[j++];
                movimentacoes++;
            } else if (j > dir) {
                comparacoes += 2;
                array[k] = copia[i++];
                movimentacoes++;
            } else if (copia[i].getActorName().compareTo(copia[j].getActorName()) < 0) {
                comparacoes += 3;
                array[k] = copia[i++];
                movimentacoes++;
            } else if (copia[i].getActorName().compareTo(copia[j].getActorName()) > 0) {
                comparacoes += 4;
                array[k] = copia[j++];
                movimentacoes++;
            } else if (copia[i].getName().compareTo(copia[j].getName()) < 0) {
                comparacoes += 5;
                array[k] = copia[i++];
                movimentacoes++;
            } else {
                array[k] = copia[j++];
                movimentacoes++;
            }
        }
        int[] arr = new int[2];
        arr[0] = comparacoes;
        arr[1] = movimentacoes;
        return arr;
    }

    public int[] QuickSortParcial(Personagem[] personagem, int inicio, int fim, int k, int comparacoes,
            int movimentacoes) {
        int i = inicio, j = fim;
        Personagem pivo = personagem[(inicio + fim) / 2];
        while (i <= j) {
            while (personagem[i].getHouse().compareTo(pivo.getHouse()) < 0
                    || personagem[i].getHouse().compareTo(pivo.getHouse()) == 0
                            && personagem[i].getName().compareTo(pivo.getName()) < 0) {
                comparacoes++;
                i++;
            }

            while (personagem[j].getHouse().compareTo(pivo.getHouse()) > 0
                    || personagem[j].getHouse().compareTo(pivo.getHouse()) == 0
                            && personagem[j].getName().compareTo(pivo.getName()) > 0) {
                comparacoes++;
                j--;
            }

            if (i <= j) {
                movimentacoes += 3;
                Personagem aux = personagem[i];
                personagem[i] = personagem[j];
                personagem[j] = aux;
                i++;
                j--;
            }

            if (j > inicio && k != 10) {
                comparacoes += 2;
                QuickSortParcial(personagem, inicio, j, k + 1, comparacoes, movimentacoes);
            }

            if (i < fim && k != 10) {
                comparacoes += 2;
                QuickSortParcial(personagem, i, fim, k + 1, comparacoes, movimentacoes);
            }
        }
        int[] arr = new int[2];
        arr[0] = comparacoes;
        arr[1] = movimentacoes;
        return arr;
    }

    private int comparacoes = 0;
    private int movimentacoes = 0;

    public void heaping(Personagem[] heap, int index, int i) {
        int j = i, son1 = (2 * i) + 1, son2 = (2 * i) + 2;
        comparacoes += 2;
        if (son1 < index && heap[son1].hairColour.compareTo(heap[j].hairColour) > 0) {
            j = son1;
        }
        comparacoes += 2;
        if (son2 < index && heap[son2].hairColour.compareTo(heap[j].hairColour) > 0) {
            j = son2;
        }
        comparacoes++;
        if (j != i) {
            movimentacoes += 3;
            Personagem tmp = heap[j];
            heap[j] = heap[i];
            heap[i] = tmp;
            heaping(heap, index, j);

        }
    }

    public void buildHeap(Personagem[] heap, int index) {
        for (int i = (index / 2); i >= 0; i--) {
            heaping(heap, index, i);
        }
    }

    public void sort(Personagem[] heap, int index, int k) {
        Personagem[] newheap = new Personagem[index];
        buildHeap(heap, index);
        int i = 0;
        for (i = index - 1; i >= 0; i--) {
            newheap[i] = heap[0];
            heap[0] = heap[i];
            heaping(heap, i, 0);
        }

        for (int h = 0; h < index; h++) {
            heap[h] = newheap[h];
        }
    }

    public void sortByName(Personagem[] heap, int index) {
        for (int i = 0; i < index; i++) {
            for (int j = i; j < index; j++) {
                if (heap[i].hairColour.compareTo(heap[j].hairColour) == 0 && heap[i].name.compareTo(heap[j].name) > 0) {
                    Personagem tmp = heap[i];
                    heap[i] = heap[j];
                    heap[j] = tmp;
                }
            }
        }
    }

}

public class TP03Q011 {
    public static void main(String[] args) throws Exception {
        Personagem pesquisar = new Personagem();
        ListaDuplaPersonagens lista = new ListaDuplaPersonagens();
        Scanner scanf = new Scanner(System.in);

        ArrayList<String> arr1 = new ArrayList<String>();

        while (true) {
            String id = scanf.nextLine();
            if (id.compareTo("FIM") == 0) {
                break;
            }

            arr1.add(id);

        }

        for (int i = 0; i < arr1.size(); i++) {
            pesquisar = pesquisar.pesquisar(arr1.get(i));
            lista.inserirFim(pesquisar);
        }

        lista.QuickSort();

        lista.mostrar();

        scanf.close();

    }
}
