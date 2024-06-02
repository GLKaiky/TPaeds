#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <stdbool.h>
#include <time.h>
#define MAX_SIZE 100000

typedef struct NameString{
    char name[200];
}NameString;

typedef struct Date{
    int day[2];
    int month[2];
    int year[4];
}Date;

typedef struct Personagem{
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

}Personagem;

void imprimir(Personagem personagem){
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
    printf(" %d%d-%d%d-%d%d%d%d ##", personagem.dateOfBirth.day[0],personagem.dateOfBirth.day[1], personagem.dateOfBirth.month[0], 
    personagem.dateOfBirth.month[1], personagem.dateOfBirth.year[0], personagem.dateOfBirth.year[1], 
    personagem.dateOfBirth.year[2], personagem.dateOfBirth.year[3]);

    printf(" %d%d%d%d ##", personagem.yearOfBirth[0],personagem.yearOfBirth[1],personagem.yearOfBirth[2],personagem.yearOfBirth[3]);
    printf(" %s ##", personagem.eyeColour);
    printf(" %s ##", personagem.gender);
    printf(" %s ##", personagem.hairColour);
    printf(" %s]\n", personagem.wizard ? "true" : "false");
}


void charNames(NameString nome[], int indice){
    scanf(" %s", &nome[indice].name);
    if(strcmp(nome[indice].name, "FIM") == 0){
        return;
    }
    charNames(nome, indice+1);
}

void searchToBin(Personagem personagem[], int indice, NameString nome[]){
        char id[100];

while(1){

    scanf(" %s", &id);
    int A = strlen(id);
    if(strcmp(id, "FIM") == 0){
        charNames(nome, 0);
        break;
    }

    char aux[1000];
    FILE* arquivo = fopen("/tmp/characters.csv", "r");
    fgets(aux, sizeof(aux), arquivo);//ignorar cabeçalho

    char linhas[MAX_SIZE];
    char personagem2[MAX_SIZE];
    while(fgets(linhas, sizeof(linhas), arquivo)){
        if(strstr(linhas, id)){
            strcpy(personagem2, linhas);
            break;
        }
    }    
    fclose(arquivo);

    int k = 0, j = 0, cont = 0; // Índice para preenchimento dos campos
    for(int i = 0; i < strlen(personagem2); i++){

        if(personagem2[i] != ';'){ // Verifica se não é o delimitador

            switch(j){
                case 0:
                    personagem[indice].id[k] = personagem2[i];
                    break;
                case 1:
                    personagem[indice].name[k] = personagem2[i];
                    break;
                case 2:
                    if(personagem2[i] == '['){
                        personagem2[i] = '{';
                    }
                    if(personagem2[i] == ']'){
                        personagem2[i] = '}';
                    }
                    if(personagem2[i] == '\''){
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
                    if(personagem2[i] == '-'){
                        cont++;
                        k = 0;
                        continue;
                    }
                    if(cont == 0){
                        char aux = personagem2[i];
                        personagem[indice].dateOfBirth.day[k] = atoi(&aux);;
                    }
                    if(cont == 1){
                        char aux = personagem2[i];
                        if(personagem2[i+1] == '-' && personagem2[i-1] == '-'){
                            personagem[indice].dateOfBirth.month[k+1] = atoi(&aux);
                        }else{
                        personagem[indice].dateOfBirth.month[k] = atoi(&aux);
                        }
                    }
                    if(cont == 2){
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
                    if (personagem2[i] == 'V') {
                        personagem[indice].wizard = true;
                    } else if (personagem2[i] == 'F') {
                        personagem[indice].wizard = false;
                    }
                    break;
            }
            k++;
        } else {
            k = 0;
            j++;
        }
    }

    indice++;
}
    
}

void pesquisar(Personagem personagem[], int indice){
    char id[100];

while(1){

    scanf(" %s", &id);
    int A = strlen(id);
    if(strcmp(id, "FIM") == 0){
        break;
    }

    char aux[1000];
    FILE* arquivo = fopen("/tmp/characters.csv", "r");
    fgets(aux, sizeof(aux), arquivo);//ignorar cabeçalho

    char linhas[MAX_SIZE];
    char personagem2[MAX_SIZE];
    while(fgets(linhas, sizeof(linhas), arquivo)){
        if(strstr(linhas, id)){
            strcpy(personagem2, linhas);
            break;
        }
    }    
    fclose(arquivo);

    int k = 0, j = 0, cont = 0; // Índice para preenchimento dos campos
    for(int i = 0; i < strlen(personagem2); i++){

        if(personagem2[i] != ';'){ // Verifica se não é o delimitador

            switch(j){
                case 0:
                    personagem[indice].id[k] = personagem2[i];
                    break;
                case 1:
                    personagem[indice].name[k] = personagem2[i];
                    break;
                case 2:
                    if(personagem2[i] == '['){
                        personagem2[i] = '{';
                    }
                    if(personagem2[i] == ']'){
                        personagem2[i] = '}';
                    }
                    if(personagem2[i] == '\''){
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
                    if(personagem2[i] == '-'){
                        cont++;
                        k = 0;
                        continue;
                    }
                    if(cont == 0){
                        char aux = personagem2[i];
                        personagem[indice].dateOfBirth.day[k] = atoi(&aux);;
                    }
                    if(cont == 1){
                        char aux = personagem2[i];
                        if(personagem2[i+1] == '-' && personagem2[i-1] == '-'){
                            personagem[indice].dateOfBirth.month[k+1] = atoi(&aux);
                        }else{
                        personagem[indice].dateOfBirth.month[k] = atoi(&aux);
                        }
                    }
                    if(cont == 2){
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
                    if (personagem2[i] == 'V') {
                        personagem[indice].wizard = true;
                    } else if (personagem2[i] == 'F') {
                        personagem[indice].wizard = false;
                    }
                    break;
            }
            k++;
        } else {
            k = 0;
            j++;
        }
    }

    imprimir(personagem[indice]);
    indice++;
}

}

int PesquisaBinaria(Personagem personagem[]){
    NameString nome[100];
    searchToBin(personagem, 0, nome);

    for(int i = 0; i<100; i++){
        printf("%s", nome[i].name);
        imprimir(personagem[i]);
    }

}



int main(){
    Personagem personagem[100];
    pesquisar(personagem, 0);
}
