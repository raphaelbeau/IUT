#include "Deplacement.h"
#include <stdio.h>
#include <stdlib.h>
#include <graph.h>
#include "Chemin.h"
#include "Afficher.h"
#include "ModifTab.h"

/*verifie les touches cliquée par le joueur*/
int CliqueTouche(int defaut,int* pause,int* fin){
  switch(Touche()){
  case XK_Up:
    if(defaut!=0){
      defaut=2;
      return defaut;
    }else{
      return defaut;
    }
  case XK_Down:
    if(defaut!=2){
      defaut=0;
      return defaut;
    }else{
      return defaut; 
    }
  case XK_Right:
    if(defaut!=1){
      defaut=3;
      return defaut;
    }else{
      return defaut; 
    }
  case XK_Left:
    if(defaut!=3){
      defaut=1;
      return defaut;
    }else{
      return defaut; 
    }
  case XK_space:
    if(*pause==1){
      *pause=0;
      return defaut;
    }
    else if(*pause==0){
      *pause=1;
      return defaut;
    }
  case XK_Escape:
    *fin=1;
    return defaut;
  default :
    return defaut;
  }
}

/*deplacement de la queue du serpent en fonction de la trace laissée par la tete*/
void DeplacementQueue(int tab[H][L],int* sxmin,int* symin,int* direc, int menu){
	if(menu==3){
  if(*direc==0){
    *sxmin=*sxmin+1;
    *direc=VoirCase(tab,*sxmin,*symin,*direc);
    tab[*sxmin][*symin]=40;
  }
  else if(*direc==1){
    *symin=*symin-1;
    *direc=VoirCase(tab,*sxmin,*symin,*direc);
    tab[*sxmin][*symin]=40;
  }
  else if(*direc==2){
    *sxmin=*sxmin-1;
    *direc=VoirCase(tab,*sxmin,*symin,*direc);
    tab[*sxmin][*symin]=40;
  }
  else if(*direc==3){
    *symin=*symin+1;
    *direc=VoirCase(tab,*sxmin,*symin,*direc);
    tab[*sxmin][*symin]=40;
  }      
	}else{
  if(*direc==0){
    *sxmin=*sxmin+1;
    *direc=VoirCase(tab,*sxmin,*symin,*direc);
    tab[*sxmin][*symin]=0;
  }
  else if(*direc==1){
    *symin=*symin-1;
    *direc=VoirCase(tab,*sxmin,*symin,*direc);
    tab[*sxmin][*symin]=0;
  }
  else if(*direc==2){
    *sxmin=*sxmin-1;
    *direc=VoirCase(tab,*sxmin,*symin,*direc);
    tab[*sxmin][*symin]=0;
  }
  else if(*direc==3){
    *symin=*symin+1;
    *direc=VoirCase(tab,*sxmin,*symin,*direc);
    tab[*sxmin][*symin]=0;
  }      
	}
}

/*Deplacement de la tete du seroent en fonction de la direction*/
void DeplacementTete(int tab[H][L],int* sxmax,int* symax,int direction,int* temoin,int* fin,int* point,int mode){
	  if(direction==2){
    if(VerifChemin(tab,*sxmax-1,*symax)==1){
      *sxmax=*sxmax-1;
      tab[*sxmax][*symax]=1;
    }else if(VerifChemin(tab,*sxmax-1,*symax)==3){
      *sxmax=*sxmax-1;
      tab[*sxmax][*symax]=1;
      *point=*point+5;
      DessinerScore(*point);
      Pastille(tab,1,1);
      *temoin=1;
    }else if(VerifChemin(tab,*sxmax-1,*symax)==2){
      *sxmax=*sxmax-1;
      tab[*sxmax][*symax]=1;
      if(mode==3){
        Pastille(tab,20,3); 
      }else{
        Pastille(tab,5,3);
      }
      Pastille(tab,1,2);
    }else{
      *fin=1;
    }
  }
  /*droite*/
  if(direction==3){
    if(VerifChemin(tab,*sxmax,*symax+1)==1){
      *symax=*symax+1;
      tab[*sxmax][*symax]=1;
    }else if(VerifChemin(tab,*sxmax,*symax+1)==3){
      *symax=*symax+1;
      tab[*sxmax][*symax]=1;
      *point=*point+5;
      DessinerScore(*point);
      Pastille(tab,1,1);
      *temoin=1;
    }else if(VerifChemin(tab,*sxmax,*symax+1)==2){
      *symax=*symax+1;
      tab[*sxmax][*symax]=1;
            if(mode==3){
        Pastille(tab,20,3); 
      }else{
        Pastille(tab,5,3);
      }
      Pastille(tab,1,2);
    }else{
      *fin=1;
    }
  }
  /*gauche*/
  if(direction==1){
    if(VerifChemin(tab,*sxmax,*symax-1)==1){
      *symax=*symax-1;
      tab[*sxmax][*symax]=1;
    }
    else if(VerifChemin(tab,*sxmax,*symax-1)==3){
      *symax=*symax-1;
      tab[*sxmax][*symax]=1;
      *point=*point+5;
      DessinerScore(*point);
      Pastille(tab,1,1);
      *temoin=1;
    }else if(VerifChemin(tab,*sxmax,*symax-1)==2){
      *symax=*symax-1;
      tab[*sxmax][*symax]=1;
              if(mode==3){
          Pastille(tab,20,3); 
        }else{
          Pastille(tab,5,3);
        }
      Pastille(tab,1,2);
    }else{
      *fin=1;
    }
  }
  /*bas*/
  if(direction==0){
    if(VerifChemin(tab,*sxmax+1,*symax)==1){
      *sxmax=*sxmax+1;
      tab[*sxmax][*symax]=1;
    }
    else if(VerifChemin(tab,*sxmax+1,*symax)==3){
      *sxmax=*sxmax+1;
      tab[*sxmax][*symax]=1;
      *point=*point+5;
      DessinerScore(*point);
      Pastille(tab,1,1);
      *temoin=1;
    }else if(VerifChemin(tab,*sxmax+1,*symax)==2){
      *sxmax=*sxmax+1;
        tab[*sxmax][*symax]=1;
              if(mode==3){
          Pastille(tab,20,3); 
        }else{
          Pastille(tab,5,3);
        }
      Pastille(tab,1,2);
    }else{
      *fin=1;
    }
  }
  
}