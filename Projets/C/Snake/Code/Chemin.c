#include "Chemin.h"
#include "Deplacement.h"
#include <stdlib.h>
#include <stdio.h>
#include <graph.h>

/*seme les angles pour dire a la queue où aller*/
int Semage(int tab[H][L],int direction,int sxmax,int symax,int* pause,int* fin){
  int newdirec;
  newdirec=CliqueTouche(direction,pause,fin);
  if(newdirec!=direction){
    if(newdirec==0){
      tab[sxmax][symax]=6;
    }
    if(newdirec==1){
      tab[sxmax][symax]=7;
    }
    if(newdirec==2){
      tab[sxmax][symax]=8;
    }
    if(newdirec==3){
      tab[sxmax][symax]=9;
    }
    direction=newdirec;
    return direction;
  }
}

/*arrete la queue le temps que la tete avance un peu pour permettre au serpent de grandir*/
unsigned long TempsArret(int* temoin){
  if(*temoin==1){
    *temoin=0;
    return Microsecondes()+DELTI;
  }else if(*temoin==0){
    return Microsecondes()+DELTO;
  }
}

/*verification de la case ou on va aller*/
int VerifChemin(int tab[H][L], int x, int y){
  if(tab[x][y]==0){
    return 1;
  }else if(tab[x][y]==30){
    return 3;
  }else if(tab[x][y]==50){
    return 2;
  }else{
    return 0;
  }
}

/*Verification sir le joueuer a gagner ou perdu*/
int Gagne(int tab[H][L]){
	int i,j;
	for(i=0;i<=H;i++){
		for(j=0;j<=L;j++){
			if(tab[i][j]==0||tab[i][j]==30||tab[i][j]==30){
				return 1;
			}
		}
	}
	return 0;
}

/*regarde la case ou la queue est pour savoir ou elle va aller ensuite*/
int VoirCase(int tab[H][L],int x, int y,int defaut){
  if(tab[x][y]==9){
    return 3;
  }
  else if(tab[x][y]==8){
    return 2;
  }
  else if(tab[x][y]==7){
    return 1;
  }
  else if(tab[x][y]==6){
    return 0;
  }
  else{
    return defaut;
  }
}
