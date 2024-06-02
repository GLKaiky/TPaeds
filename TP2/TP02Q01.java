import java.time.Year;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Date;
import java.io.*;

public class Personagem implements Cloneable {
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

    public void pesquisar(Personagem[] personagem, int index) {
        Scanner scanf = new Scanner(System.in);
        while (true) {
            String id = scanf.nextLine();
            if(id.equals("FIM")){
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
                    personagem[index].setHogwartsStaff(Boolean.parseBoolean(partes[7]));
                    personagem[index].setHogwartsStudent(Boolean.parseBoolean(partes[8]));
                    personagem[index].setActorName(partes[9]);
                    personagem[index].setAlive(Boolean.parseBoolean(partes[10]));
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
                    if(partes[17].equals("VERDADEIRO")){
                        personagem[index].setWizard(true);
                    }else if(partes[17].equals("FALSO")){
                        personagem[index].setWizard(false);
                    }
                }
            }
            personagem[index].imprimir();
            index++;
        }
    }

    public static void main(String[] args) {
        Personagem[] personagem = new Personagem[100];
            
        for (int i = 0; i < personagem.length; i++) {
            personagem[i] = new Personagem();
        }

        personagem[0].pesquisar(personagem, 0);
    }

}
