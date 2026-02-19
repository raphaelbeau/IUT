#include <stdlib.h>
#include <stdio.h>
#include <graph.h>
#include <time.h>

#include "Afficher.h"
#include "ModifTab.h"
#include "Deplacement.h"
#include "Chemin.h"
#include "Menu.h"
#include "Jeu.h"

#define DELTA 1000000L;
#define DELTO 1000L
#define DELTI 400000L

void LancerJeu(void)
{
    int tab[H][L], direction = 0, temoin = 0, direc = 0, pause = 1,mode=0;
    int sxmin = 17, symin = 30, sxmax = 27, symax = 30, fin = 0, point = 0, Compteur_Temps = 0;
    int *pointeur_pause = &pause;
    int *pointeur_sxmin = &sxmin;
    int *pointeur_symin = &symin;
    int *pointeur_direc = &direc;
    int *pointeur_temoin = &temoin;
    int *pointeur_sxmax = &sxmax;
    int *pointeur_symax = &symax;
    int *pointeur_fin = &fin;
    int *pointeur_point = &point;
    unsigned long suivant = Microsecondes() + DELTA;
    unsigned long suivant2 = Microsecondes() + DELTO;
    unsigned long suivant3 = Microsecondes() + DELTO;
    
    InitEcran();
    mode=MenuDebut();
    init(tab,mode);

    while (1) 
    {
      if(fin!=1)
      {
        Affiche(tab);
        if (ToucheEnAttente() == 1) 
        {
	          direction = Semage(tab, direction, sxmax, symax, pointeur_pause, pointeur_fin);
        }
        if (pause == 1) {
          if (Microsecondes() > suivant) {
            Compteur_Temps++;
            DessinerTimer(Compteur_Temps);
            suivant = Microsecondes() + DELTA;
          }
          if (Microsecondes() > suivant3) {
            DeplacementQueue(tab, pointeur_sxmin, pointeur_symin, pointeur_direc,mode);
            suivant3 = TempsArret(pointeur_temoin);
          }
          if (Microsecondes() > suivant2) {
            DeplacementTete(tab, pointeur_sxmax, pointeur_symax, direction, pointeur_temoin, pointeur_fin, pointeur_point,mode);
            suivant2 = Microsecondes() + DELTO;
          }
          MenuPause(pause);
        }else{
            MenuPause(pause);
        }
      }
      else if (fin == 1) {
	    if(Gagne(tab)==0){
	        MenuFinGagne();
	    }else{
	        MenuFinPerdu();
	    }
      }
   }
}
