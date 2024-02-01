#include <stdlib.h>
#include <stdio.h>
#include <graph.h>
#include "Afficher.h"

/*affichage du plateau de jeu en fonction du tableau*/
void AfficheTab(int tab[H][L], int posx, int posy, int i, int j){
  couleur c;
  if(tab[i][j]==0){
    c=CouleurParNom("black");
    ChoisirCouleurDessin(c);
    RemplirRectangle(posx,posy,20,20);
  }

  if(tab[i][j]==40){
    c=CouleurParNom("dark grey");
    ChoisirCouleurDessin(c);
    RemplirRectangle(posx,posy,20,20);
    c=CouleurParNom("black");
    ChoisirCouleurDessin(c);
    DessinerRectangle(posx+2,posy+2,16,16);
  }
  if(tab[i][j]==1||tab[i][j]==9||tab[i][j]==7||tab[i][j]==8||tab[i][j]==6){
    c=CouleurParNom("dark orange");
    ChoisirCouleurDessin(c);
    RemplirRectangle(posx,posy,20,20);
    c=CouleurParNom("brown");
    ChoisirCouleurDessin(c);
    RemplirArc(posx+5,posy+5,10,10,360,360);
  }
  if(tab[i][j]==30){
    c=CouleurParNom("black");
    ChoisirCouleurDessin(c);
    RemplirRectangle(posx,posy,20,20);
    c=CouleurParNom("red");
    ChoisirCouleurDessin(c);
    RemplirArc(posx,posy,20,20,360,360);
  }
  if(tab[i][j]==50){    
    c=CouleurParNom("black");
    ChoisirCouleurDessin(c);
    RemplirRectangle(posx,posy,20,20);
    c=CouleurParNom("purple");
    ChoisirCouleurDessin(c);
    RemplirArc(posx,posy,20,20,360,360);
  }
  
}

/*parcours du tableau pour rendu graphique*/
void Affiche(int tab[H][L]){
  int i,j,posx=0,posy=0;
  for(i=0;i<=H;i++){
    for(j=0;j<=L;j++){
      AfficheTab(tab, posx, posy, i, j);
      posx=posx+20;
    }
    posx=0;
    posy=posy+20;
  }
  posx=0;
  posy=0;
}

/*affichage du score*/
void DessinerScore(int n){
  couleur c;
  char buf[100];
  c=CouleurParNom("dark blue");
  ChoisirCouleurDessin(c);
  RemplirRectangle(200,875,200,100);
  c=CouleurParNom("yellow");
  ChoisirCouleurDessin(c);
  snprintf(buf,100,"score : %d",n);
  EcrireTexte(250,900,buf,2);
}

/*affichage du timer*/
void DessinerTimer(int n){
  couleur c;
  char buf[100];
  c=CouleurParNom("dark blue");
  ChoisirCouleurDessin(c);
  RemplirRectangle(0,875,200,100);
  c=CouleurParNom("red");
  ChoisirCouleurDessin(c);
  snprintf(buf,100,"time : %d",n);
  EcrireTexte(50,900,buf,2);
}
