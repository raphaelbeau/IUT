#include "ModifTab.h"
#include "Afficher.h"
#include <stdio.h>
#include <stdlib.h>
#include <graph.h>

/*initialisation du plateau de jeu*/
void init(int tab[H][L], int mode){
  int i,j;
  couleur c;
  c=CouleurParNom("dark blue");
  ChoisirCouleurDessin(c);
  RemplirRectangle(0,0,1700,1000);
  DessinerScore(0);
  DessinerTimer(0);
  /*initialisation de la grille a 0(noir)*/
  for(i=1;i<H;i++){
    for(j=1;j<L;j++){
      tab[i][j]=0;
    }
  }

  /*initialisation bordure*/
  if(mode==2){
  for(i=0;i<=H;i++){
    tab[i][L]=0;
    tab[i][0]=0;
  }
  }
  else{
  for(i=0;i<=H;i++){
    tab[i][L]=40;
    tab[i][0]=40;
  }
  }
  for(i=0;i<=L;i++){
    tab[H][i]=40;
    tab[0][i]=40;
  }

  /*initialisation du serpent*/
  tab[18][30]=0;
  tab[18][30]=0;
  tab[27][30]=1;

  for(i=18;i<28;i++){
    tab[i][30]=1;
  }
  /*Initalisation des differences pastilles*/
  if(mode==3||mode==2){
  	Pastille(tab,2,2);
  }
  Pastille(tab,5,1);
}

/*Mise en place de pastilles le plateau de jeu
  en fonction de ce que l'on veut placer */
int Pastille(int tab[H][L],int nb_de_pastilles,int type_pastille){
  int ia,ja;
  if(nb_de_pastilles==0){
    return 1;
  }
  srand(time(NULL));
  while(nb_de_pastilles!=0){
    ia=rand()%40;
    ja=rand()%60;
    if(tab[ia][ja]==0){
      if(type_pastille==1){
	/*pommes*/
	tab[ia][ja]=30;
      }else if(type_pastille==2){
	/*pommes pour mur*/
	tab[ia][ja]=50;
      }else if(type_pastille==3){
	/*mur*/
	tab[ia][ja]=40;
      }
      nb_de_pastilles=nb_de_pastilles-1;
    }
  }
}
