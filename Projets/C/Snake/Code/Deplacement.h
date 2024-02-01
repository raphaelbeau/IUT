#ifndef DEPLACEMENT_H
#define DEPLACEMENT_H

#include <stdlib.h>
#include <stdio.h>
#include <graph.h>
#include <time.h>

#define H 42
#define L 62

int CliqueTouche(int defaut, int *pause, int *fin);
void DeplacementQueue(int tab[H][L], int *sxmin, int *symin, int *direc,int);
void DeplacementTete(int tab[H][L], int *sxmax, int *symax, int direction, int *temoin, int *fin, int *point,int menu);

#endif // DEPLACEMENT_H
