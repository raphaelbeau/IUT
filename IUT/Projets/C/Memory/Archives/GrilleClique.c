#include<stdlib.h>
#include<stdio.h>
#include<graph.h>

#define H 5
#define L 5

int main(){
  int i,j,posx=0,posy=0,x,y,compteur=0;

	InitialiserGraphique();
	CreerFenetre(10,10,1000,1000);
	couleur c;
	c=CouleurParNom("green");
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
		c=CouleurParNom("black");
		ChoisirCouleurDessin(c);
		if(((_X>=posx)&&(_Y>=posy))&&((_X<=x)&&(_Y<=y))){
		  RemplirRectangle(posx,posy,150,150);
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
