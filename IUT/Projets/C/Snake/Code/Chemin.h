#ifndef CHEMIN_H
#define CHEMIN_H

#include <stdlib.h>
#include <stdio.h>
#include <graph.h>
#include <time.h>

#define H 42
#define L 62
#define DELTO 1000L
#define DELTI 400000L

int Semage(int tab[H][L], int direction, int sxmax, int symax, int *pause, int *fin);
unsigned long TempsArret(int *temoin);
int VerifChemin(int tab[H][L], int x, int y);
int VoirCase(int tab[H][L], int x, int y, int defaut);
int Gagne(int tab[H][L]);

#endif // CHEMIN_H
