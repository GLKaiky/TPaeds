#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <stdbool.h>
#include <time.h>
#define MAX_SIZE 100000

int comparacoes2 = 0;
int movimentacoes2 = 0;

typedef struct NameString
{
    char name[200];
} NameString;

typedef struct Date
{
    int day[2];
    int month[2];
    int year[4];
} Date;

typedef struct Personagem
{
    char id[100];
    char name[100];
    char alternate_names[300];
    char house[100];
    char ancestry[100];
    char species[100];
    char patronus[100];
    bool hogwartsStaff;
    char hogwatsStudent[100];
    char actorName[100];
    bool alive;
    Date dateOfBirth;
    int yearOfBirth[4];
    char eyeColour[100];
    char gender[100];
    char hairColour[100];
    bool wizard;

} Personagem;

void imprimir(Personagem personagem)
{
    printf("[%s ##", personagem.id);
    printf(" %s ##", personagem.name);
    printf(" %s ##", personagem.alternate_names);
    printf(" %s ##", personagem.house);
    printf(" %s ##", personagem.ancestry);
    printf(" %s ##", personagem.species);
    printf(" %s ##", personagem.patronus);
    printf(" %s ##", personagem.hogwartsStaff ? "true" : "false");
    printf(" false ##");
    printf(" %s ##", personagem.actorName);
    printf(" %s ##", personagem.alive ? "true" : "false");
    printf(" %d%d-%d%d-%d%d%d%d ##", personagem.dateOfBirth.day[0], personagem.dateOfBirth.day[1], personagem.dateOfBirth.month[0],
           personagem.dateOfBirth.month[1], personagem.dateOfBirth.year[0], personagem.dateOfBirth.year[1],
           personagem.dateOfBirth.year[2], personagem.dateOfBirth.year[3]);

    printf(" %d%d%d%d ##", personagem.yearOfBirth[0], personagem.yearOfBirth[1], personagem.yearOfBirth[2], personagem.yearOfBirth[3]);
    printf(" %s ##", personagem.eyeColour);
    printf(" %s ##", personagem.gender);
    printf(" %s ##", personagem.hairColour);
    printf(" %s]\n", personagem.wizard ? "true" : "false");
}

void charNames(NameString nome[], int indice, int *checkpoint)
{
    scanf(" %[^\r]", &nome[indice].name);
    if (strcmp(nome[indice].name, "FIM") == 0)
    {
        *checkpoint = indice;
        return;
    }
    charNames(nome, indice + 1, checkpoint);
}

int *searchToBin(Personagem personagem[], int indice, NameString nome[])
{
    char id[100];
    int *indice2 = malloc(2 * sizeof(int));
    while (1)
    {
        scanf("%s", &   id);
        int A = strlen(id);
        if (strcmp(id, "FIM") == 0)
        {
            charNames(nome, 0, indice2);
            break;
        }

        char aux[1000];
        FILE *arquivo = fopen("/tmp/characters.csv", "r");
        fgets(aux, sizeof(aux), arquivo); // ignorar cabeçalho

        char linhas[MAX_SIZE];
        char personagem2[MAX_SIZE];
        while (fgets(linhas, sizeof(linhas), arquivo))
        {
            if (strstr(linhas, id))
            {
                strcpy(personagem2, linhas);
                break;
            }
        }
        fclose(arquivo);

        int k = 0, j = 0, cont = 0; // Índice para preenchimento dos campos
        for (int i = 0; i < strlen(personagem2); i++)
        {

            if (personagem2[i] != ';')
            { // Verifica se não é o delimitador

                switch (j)
                {
                case 0:
                    personagem[indice].id[k] = personagem2[i];
                    break;
                case 1:
                    personagem[indice].name[k] = personagem2[i];
                    break;
                case 2:
                    if (personagem2[i] == '[')
                    {
                        personagem2[i] = '{';
                    }
                    if (personagem2[i] == ']')
                    {
                        personagem2[i] = '}';
                    }
                    if (personagem2[i] == '\'')
                    {
                        continue;
                    }
                    personagem[indice].alternate_names[k] = personagem2[i];
                    break;
                case 3:
                    personagem[indice].house[k] = personagem2[i];
                    break;
                case 4:
                    personagem[indice].ancestry[k] = personagem2[i];
                    break;
                case 5:
                    personagem[indice].species[k] = personagem2[i];
                    break;
                case 6:
                    personagem[indice].patronus[k] = personagem2[i];
                    break;
                case 7:
                    personagem[indice].hogwartsStaff = false;
                    break;
                case 8:
                    personagem[indice].hogwatsStudent[k] = personagem2[i];
                    break;
                case 9:
                    personagem[indice].actorName[k] = personagem2[i];
                    break;
                case 10:
                    personagem[indice].alive = false;
                    break;
                case 12:
                    if (personagem2[i] == '-')
                    {
                        cont++;
                        k = 0;
                        continue;
                    }
                    if (cont == 0)
                    {
                        char aux = personagem2[i];
                        personagem[indice].dateOfBirth.day[k] = atoi(&aux);
                        ;
                    }
                    if (cont == 1)
                    {
                        char aux = personagem2[i];
                        if (personagem2[i + 1] == '-' && personagem2[i - 1] == '-')
                        {
                            personagem[indice].dateOfBirth.month[k + 1] = atoi(&aux);
                        }
                        else
                        {
                            personagem[indice].dateOfBirth.month[k] = atoi(&aux);
                        }
                    }
                    if (cont == 2)
                    {
                        char aux = personagem2[i];
                        personagem[indice].dateOfBirth.year[k] = atoi(&aux);
                    }

                    break;
                case 13:
                    char aux = personagem2[i];
                    personagem[indice].yearOfBirth[k] = atoi(&aux);
                    break;
                case 14:
                    personagem[indice].eyeColour[k] = personagem2[i];
                    break;
                case 15:
                    personagem[indice].gender[k] = personagem2[i];
                    break;
                case 16:
                    personagem[indice].hairColour[k] = personagem2[i];
                    break;
                case 17:
                    if (personagem2[i] == 'V')
                    {
                        personagem[indice].wizard = true;
                    }
                    else if (personagem2[i] == 'F')
                    {
                        personagem[indice].wizard = false;
                    }
                    break;
                }
                k++;
            }
            else
            {
                k = 0;
                j++;
            }
        }

        indice++;
    }
    indice2[1] = indice;
    return indice2;
}

int pesquisar(Personagem personagem[], int indice)
{
    char id[100];

    while (1)
    {

        scanf(" %s", &id);
        int A = strlen(id);
        if (strcmp(id, "FIM") == 0)
        {
            break;
        }

        char aux[1000];
        FILE *arquivo = fopen("/tmp/characters.csv", "r");
        fgets(aux, sizeof(aux), arquivo); // ignorar cabeçalho

        char linhas[MAX_SIZE];
        char personagem2[MAX_SIZE];
        while (fgets(linhas, sizeof(linhas), arquivo))
        {
            if (strstr(linhas, id))
            {
                strcpy(personagem2, linhas);
                break;
            }
        }
        fclose(arquivo);

        int k = 0, j = 0, cont = 0; // Índice para preenchimento dos campos
        for (int i = 0; i < strlen(personagem2); i++)
        {

            if (personagem2[i] != ';')
            { // Verifica se não é o delimitador

                switch (j)
                {
                case 0:
                    personagem[indice].id[k] = personagem2[i];
                    break;
                case 1:
                    personagem[indice].name[k] = personagem2[i];
                    break;
                case 2:
                    if (personagem2[i] == '[')
                    {
                        personagem2[i] = '{';
                    }
                    if (personagem2[i] == ']')
                    {
                        personagem2[i] = '}';
                    }
                    if (personagem2[i] == '\'')
                    {
                        continue;
                    }
                    personagem[indice].alternate_names[k] = personagem2[i];
                    break;
                case 3:
                    personagem[indice].house[k] = personagem2[i];
                    break;
                case 4:
                    personagem[indice].ancestry[k] = personagem2[i];
                    break;
                case 5:
                    personagem[indice].species[k] = personagem2[i];
                    break;
                case 6:
                    personagem[indice].patronus[k] = personagem2[i];
                    break;
                case 7:
                    personagem[indice].hogwartsStaff = false;
                    break;
                case 8:
                    personagem[indice].hogwatsStudent[k] = personagem2[i];
                    break;
                case 9:
                    personagem[indice].actorName[k] = personagem2[i];
                    break;
                case 10:
                    personagem[indice].alive = false;
                    break;
                case 12:
                    if (personagem2[i] == '-')
                    {
                        cont++;
                        k = 0;
                        continue;
                    }
                    if (cont == 0)
                    {
                        char aux = personagem2[i];
                        personagem[indice].dateOfBirth.day[k] = atoi(&aux);
                        ;
                    }
                    if (cont == 1)
                    {
                        char aux = personagem2[i];
                        if (personagem2[i + 1] == '-' && personagem2[i - 1] == '-')
                        {
                            personagem[indice].dateOfBirth.month[k + 1] = atoi(&aux);
                        }
                        else
                        {
                            personagem[indice].dateOfBirth.month[k] = atoi(&aux);
                        }
                    }
                    if (cont == 2)
                    {
                        char aux = personagem2[i];
                        personagem[indice].dateOfBirth.year[k] = atoi(&aux);
                    }

                    break;
                case 13:
                    char aux = personagem2[i];
                    personagem[indice].yearOfBirth[k] = atoi(&aux);
                    break;
                case 14:
                    personagem[indice].eyeColour[k] = personagem2[i];
                    break;
                case 15:
                    personagem[indice].gender[k] = personagem2[i];
                    break;
                case 16:
                    personagem[indice].hairColour[k] = personagem2[i];
                    break;
                case 17:
                    if (personagem2[i] == 'V')
                    {
                        personagem[indice].wizard = true;
                    }
                    else if (personagem2[i] == 'F')
                    {
                        personagem[indice].wizard = false;
                    }
                    break;
                }
                k++;
            }
            else
            {
                k = 0;
                j++;
            }
        }
        indice++;
    }
    return indice;
}

bool PesquisaBinaria(int inicio, int fim, Personagem personagem[], NameString nome[], int indice, int* comparacoes)
{
    bool encontrou = false;
    while (inicio <= fim && !encontrou)
    {
        (*comparacoes)++; // Atualiza o número de comparações
        int meio = ((inicio + fim) / 2);
        if (strcmp(nome[indice].name,personagem[meio].name) == 0)
        {
            encontrou = true;
        }
        if (strcmp(nome[indice].name,personagem[meio].name) > 0)
        {
            return PesquisaBinaria(meio + 1, fim, personagem, nome, indice, comparacoes);
        }
        if (strcmp(nome[indice].name,personagem[meio].name) < 0)
        {
            return PesquisaBinaria(inicio, meio - 1, personagem, nome, indice, comparacoes);
        }
    }
    return encontrou;
}

int SelectionSort(Personagem personagem[])
{
    int *valores = malloc(2 * sizeof(int));
    NameString nome[100];
    valores = searchToBin(personagem, 0, nome);

    for (int i = 0; i < valores[1]; i++)
    {
        for (int j = i; j < valores[1]; j++)
        {
            if (strcmp(personagem[i].name, personagem[j].name) > 0)
            {
                Personagem aux;
                aux = personagem[i];
                personagem[i] = personagem[j];
                personagem[j] = aux;
            }
        }
    }

    int inicio = 0, fim = valores[1] - 1, indice = 0;
    int* comparacoes;
    for(int i = 0; i<valores[0]; i++){
        bool resp = PesquisaBinaria(inicio, fim, personagem, nome, i, &comparacoes);
        if (resp == true)
        {
            printf("SIM\n");
        }
        else
        {
            printf("NAO\n");
        }
    }
    return comparacoes;
}

void PesquisaComRetorno(Personagem personagem[], int indice, NameString name[])
{

    while (1)
    {

        if (strcmp(name[indice].name, "FIM") == 0)
        {
            break;
        }

        char aux[1000];
        FILE *arquivo = fopen("/tmp/characters.csv", "r");
        fgets(aux, sizeof(aux), arquivo); // ignorar cabeçalho

        char linhas[MAX_SIZE];
        char personagem2[MAX_SIZE];
        while (fgets(linhas, sizeof(linhas), arquivo))
        {
            if (strstr(linhas, name[indice].name))
            {
                strcpy(personagem2, linhas);
                break;
            }
        }
        fclose(arquivo);

        int k = 0, j = 0, cont = 0; // Índice para preenchimento dos campos
        for (int i = 0; i < strlen(personagem2); i++)
        {

            if (personagem2[i] != ';')
            { // Verifica se não é o delimitador

                switch (j)
                {
                case 0:
                    personagem[indice].id[k] = personagem2[i];
                    break;
                case 1:
                    personagem[indice].name[k] = personagem2[i];
                    break;
                case 2:
                    if (personagem2[i] == '[')
                    {
                        personagem2[i] = '{';
                    }
                    if (personagem2[i] == ']')
                    {
                        personagem2[i] = '}';
                    }
                    if (personagem2[i] == '\'')
                    {
                        continue;
                    }
                    personagem[indice].alternate_names[k] = personagem2[i];
                    break;
                case 3:
                    personagem[indice].house[k] = personagem2[i];
                    break;
                case 4:
                    personagem[indice].ancestry[k] = personagem2[i];
                    break;
                case 5:
                    personagem[indice].species[k] = personagem2[i];
                    break;
                case 6:
                    personagem[indice].patronus[k] = personagem2[i];
                    break;
                case 7:
                    personagem[indice].hogwartsStaff = false;
                    break;
                case 8:
                    personagem[indice].hogwatsStudent[k] = personagem2[i];
                    break;
                case 9:
                    personagem[indice].actorName[k] = personagem2[i];
                    break;
                case 10:
                    personagem[indice].alive = false;
                    break;
                case 12:
                    if (personagem2[i] == '-')
                    {
                        cont++;
                        k = 0;
                        continue;
                    }
                    if (cont == 0)
                    {
                        char aux = personagem2[i];
                        personagem[indice].dateOfBirth.day[k] = atoi(&aux);
                        ;
                    }
                    if (cont == 1)
                    {
                        char aux = personagem2[i];
                        if (personagem2[i + 1] == '-' && personagem2[i - 1] == '-')
                        {
                            personagem[indice].dateOfBirth.month[k + 1] = atoi(&aux);
                        }
                        else
                        {
                            personagem[indice].dateOfBirth.month[k] = atoi(&aux);
                        }
                    }
                    if (cont == 2)
                    {
                        char aux = personagem2[i];
                        personagem[indice].dateOfBirth.year[k] = atoi(&aux);
                    }

                    break;
                case 13:
                    char aux = personagem2[i];
                    personagem[indice].yearOfBirth[k] = atoi(&aux);
                    break;
                case 14:
                    personagem[indice].eyeColour[k] = personagem2[i];
                    break;
                case 15:
                    personagem[indice].gender[k] = personagem2[i];
                    break;
                case 16:
                    personagem[indice].hairColour[k] = personagem2[i];
                    break;
                case 17:
                    if (personagem2[i] == 'V')
                    {
                        personagem[indice].wizard = true;
                    }
                    else if (personagem2[i] == 'F')
                    {
                        personagem[indice].wizard = false;
                    }
                    break;
                }
                k++;
            }
            else
            {
                k = 0;
                j++;
            }
        }
        indice++;
    }
}

void SelectionSortRec(Personagem personagem[],int i, int j, int indice){
    if(i == indice){
        return;
    }  
    if(j == indice){
        SelectionSortRec(personagem,i+1,i+1, indice);
        return;
    }

    if(strcmp(personagem[i].name, personagem[j].name) > 0){
        comparacoes2++;
        Personagem aux;
        aux = personagem[i];
        personagem[i] = personagem[j];
        personagem[j] = aux;
        movimentacoes2 += 3;    
    }

    SelectionSortRec(personagem, i, j+1, indice);
}

void SelectionSortRecursivo(){
    NameString name[100];
    Personagem personagem[100];

    int* checkpoint = malloc(sizeof(int));

    charNames(name, 0, checkpoint);
    PesquisaComRetorno(personagem, 0, name);
    int tam = *checkpoint;

    free(checkpoint);

    SelectionSortRec(personagem, 0, 0, tam);

    for(int i = 0; i<tam; i++){
        imprimir(personagem[i]);
    }
}

void insertionWithColor(int cor, int h, Personagem personagem[], int indice){
    for(int i = (cor+h); i < indice; i+=h){
        Personagem tmp = personagem[i];
        int j = i - h;
        comparacoes2++;
        while((j>=0) && (strcmp(personagem[j].eyeColour, tmp.eyeColour) > 0) || (strcmp(personagem[j].eyeColour, tmp.eyeColour) == 0) && (strcmp(personagem[j].name, tmp.name) > 0)){
            movimentacoes2++;
            personagem[j+h] = personagem[j];
            j-=h;
        }
        personagem[j+h] = tmp;
    }
}

void ShellSort(Personagem personagem[]){
    int h = 1;
    int indice = pesquisar(personagem, 0);
    do{
        h = (h*3) + 1;
    }while(h<indice);
    do{
        h/=3;
        for(int cor = 0; cor<h; cor++){
            insertionWithColor(cor, h, personagem, indice);
        }
    }while(h!=1);

    for(int i = 0; i<indice; i++){
        imprimir(personagem[i]);
    }
}

void QuickSort(Personagem personagem[], int inicio, int fim){
    Personagem pivo = personagem[(inicio+fim)/2];
    int i = inicio, j = fim;
    while(inicio<=fim){
        while((strcmp(personagem[i].house, pivo.house) < 0) || ((strcmp(personagem[i].house, pivo.house) == 0) && (strcmp(personagem[i].name, pivo.name) < 0))){
            i++;
        }

        while((strcmp(personagem[j].house, pivo.house) > 0) || ((strcmp(personagem[j].house, pivo.house) == 0) && (strcmp(personagem[j].name, pivo.name) > 0))){
            j--;
        }

        if(i <= j){
            Personagem aux = personagem[i];
            personagem[i] = personagem[j];
            personagem[j] = aux;
        }

        if(inicio < j){
            QuickSort(personagem, inicio, j);
        }
        if(i < fim){
            QuickSort(personagem, i, fim);
        }

    }
}



int main()
{
    Personagem personagem[100];
    clock_t inicio, fim;
    double tempo_de_execucao;
    inicio = clock();
    int indice = pesquisar(personagem, 0);
    QuickSort(personagem, 0, indice);
    fim = clock();
    tempo_de_execucao = (((double) (fim - inicio)) *1000) / CLOCKS_PER_SEC;

    FILE* arquivo = fopen("matrícula_quicksort.txt", "w");
    fprintf(arquivo, "802169\t%d\t%d\t%lfms", comparacoes2, movimentacoes2, tempo_de_execucao);
    fclose(arquivo);
}
