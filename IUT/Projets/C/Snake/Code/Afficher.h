#ifndef AFFICHER_H
#define AFFICHER_H

#include <stdlib.h>
#include <stdio.h>
#include <graph.h>
#include <time.h>

#define H 42
#define L 62

void AfficheTab(int tab[H][L], int posx, int posy, int i, int j);
void Affiche(int tab[H][L]);
void DessinerScore(int n);
void DessinerTimer(int n);

#endif // AFFICHER_H
