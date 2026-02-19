#include<stdlib.h>
#include<stdio.h>
#include<graph.h>
#include<time.h>

#define H 4
#define L 4

int DansTab(int tab[H][L],int a){
  int i,j,compteur=0;
  for(i=0;i<H;i++){
    for(j=0;j<L;j++){
      if(tab[i][j]==a){
	compteur=compteur+1;
      }
    }
  }
  return compteur>=2;
}

int main(){
  int i,j,posx=0,posy=0,x,y,compteur=0,a,b;

  /*initialisation tab provisoire*/
  int tab1[H][L];
  for(i=0;i<H;i++){
    for(j=0;j<L;j++){
      tab1[i][j]=0;
    }
  }
  /*creation tableau avec les paires deux a deux*/
  int tab2[H][L];
  srand(time(NULL));
  b=(H*L)/2;
  for(i=0;i<H;i++){
    for(j=0;j<L;j++){
      tab2[i][j]=0;
    }
  }
  for(i=0;i<H;i++){
    for(j=0;j<L;j++){
      a=rand()%b+1;
      if(DansTab(tab2,a)==0){
	tab2[i][j]=a;
      }
      else{
	while(DansTab(tab2,a)==1){
	  a=rand()%b+1;
	}
	tab2[i][j]=a;
      }
    }
  }

  /*partie graphique*/
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
		  if(tab1[i][j]==1){
		    c=CouleurParNom("black");
		    ChoisirCouleurDessin(c);
		    RemplirRectangle(posx,posy,150,150);
		    tab1[i][j]=0;
		  }
		  else if(tab1[i][j]==0){
		    if(tab2[i][j]==1){
		      c=CouleurParNom("green");
		      ChoisirCouleurDessin(c);
		      RemplirRectangle(posx,posy,150,150);
		      tab1[i][j]=1;
		    }
		    else if(tab2[i][j]==2){
		      c=CouleurParNom("blue");
		      ChoisirCouleurDessin(c);
		      RemplirRectangle(posx,posy,150,150);
		      tab1[i][j]=1;
		    }
		    else if(tab2[i][j]==3){
		      c=CouleurParNom("red");
		      ChoisirCouleurDessin(c);
		      RemplirRectangle(posx,posy,150,150);
		      tab1[i][j]=1;
		    }
		    else if(tab2[i][j]==4){
		      c=CouleurParNom("brown");
		      ChoisirCouleurDessin(c);
		      RemplirRectangle(posx,posy,150,150);
		      tab1[i][j]=1;
		    }
		    else if(tab2[i][j]==5){
		      c=CouleurParNom("gray");
		      ChoisirCouleurDessin(c);
		      RemplirRectangle(posx,posy,150,150);
		      tab1[i][j]=1;
		    }
		    else if(tab2[i][j]==6){
		      c=CouleurParNom("pink");
		      ChoisirCouleurDessin(c);
		      RemplirRectangle(posx,posy,150,150);
		      tab1[i][j]=1;
		    }
		    else if(tab2[i][j]==7){
		      c=CouleurParNom("yellow");
		      ChoisirCouleurDessin(c);
		      RemplirRectangle(posx,posy,150,150);
		      tab1[i][j]=1;
		    }
		    else if(tab2[i][j]==8){
		      c=CouleurParNom("light blue");
		      ChoisirCouleurDessin(c);
		      RemplirRectangle(posx,posy,150,150);
		      tab1[i][j]=1;
		    }
		    else if(tab2[i][j]==9){
		      c=CouleurParNom("purple");
		      ChoisirCouleurDessin(c);
		      RemplirRectangle(posx,posy,150,150);
		      tab1[i][j]=1;
		    }
		    else if(tab2[i][j]==10){
		      c=CouleurParNom("orange");
		      ChoisirCouleurDessin(c);
		      RemplirRectangle(posx,posy,150,150);
		      tab1[i][j]=1;
		    }
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
