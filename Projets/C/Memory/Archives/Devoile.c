#include<stdlib.h>
#include<stdio.h>
#include<graph.h>

#define H 2
#define L 3


int TrouveCarte(int tab[H][L], int x, int y){
  int i,j;
  if(tab[x][y]==1){
    return 1;
  }
  else if(tab[x][y]==2){
    return 2;
  }
  else if(tab[x][y]==3){
    return 3;
  }
  else{
    return 0;
  }
}



int main(){
  int i,j,posx=0,posy=0,x,y,compteur=0;

  int tab1[H][L];
  int tab2[H][L]={{1,2,3},
                  {1,2,3}};

  /*tab verif*/
  for(i=0;i<H;i++){
    for(j=0;j<L;j++){
      tab1[i][j]=tab2[i][j];
    }
  }
  /*algor tab 2a2*/
  

	InitialiserGraphique();
	CreerFenetre(10,10,1000,1000);
	couleur c;
	c=CouleurParNom("black");
	ChoisirCouleurDessin(c);
	

	for(i=0;i<H;i++){
	  for(j=0;j<L;j++){
	    RemplirRectangle(posx,posy,150,150);
	    posx=posx+160;
	  }
	  posx=0;
	  posy=posy+160;
	}
	posx=0;
	posy=0;
	x=150;
	y=150;
	
	while(1){
	  if((SourisCliquee()==1)){
	    for(i=0;i<H;i++){
	      for(j=0;j<L;j++){
		if(((_X>=posx)&&(_Y>=posy))&&((_X<=x)&&(_Y<=y))){
		  if(TrouveCarte(tab1,i,j)==1){
		    c=CouleurParNom("green");
		    ChoisirCouleurDessin(c);
		    RemplirRectangle(posx,posy,150,150);
		    tab1[i][j]=0;
		  }
		  else{
		    c=CouleurParNom("black");
		    ChoisirCouleurDessin(c);
		    RemplirRectangle(posx,posy,150,150);
		    tab1[i][j]=0;
		  }
		}
		posx=posx+160;
		x=x+160;
	      }
	      posy=posy+160;
	      y=y+160;
	      posx=0;
	      x=150;
	    }
	    posx=0;
	    posy=0;
	    x=150;
	    y=150;
	  }
	  /*
	  else if(SourisCliquee()==1){
	    EcrireTexte(850,400,"triche",2);
	  }
	  */
	}
}
