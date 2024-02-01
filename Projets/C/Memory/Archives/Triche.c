#include<stdlib.h>
#include<stdio.h>
#include<graph.h>
#include<time.h>

#define DELTA 1000000L
#define H 5
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

void DessinerScene(int n)
{
  couleur c;
  char buf[100];
  c=CouleurParNom("white");
  ChoisirCouleurDessin(c);
  RemplirRectangle(700,450,200,100);
  c=CouleurParNom("red");
  ChoisirCouleurDessin(c);
  snprintf(buf,100,"%d",n);
  EcrireTexte(800,500,buf,2);
}

couleur VoireCouleurCase(int tab[H][L], int x, int y){
  couleur c;
  int i;
  if(tab[x][y]==1){
    c=CouleurParNom("green");
    return c;
  }
  if(tab[x][y]==2){
    c=CouleurParNom("red");
    return c;
  }
  if(tab[x][y]==3){
    c=CouleurParNom("yellow");
    return c;
  }
  if(tab[x][y]==4){
    c=CouleurParNom("orange");
    return c;
  }
  if(tab[x][y]==5){
    c=CouleurParNom("dark green");
    return c;
  }
  if(tab[x][y]==6){
    c=CouleurParNom("light blue");
    return c;
  }
  if(tab[x][y]==7){
    c=CouleurParNom("purple");
    return c;
  }
  if(tab[x][y]==8){
    c=CouleurParNom("dark blue");
    return c;
  }
  if(tab[x][y]==9){
    c=CouleurParNom("pink");
    return c;
  }
  if(tab[x][y]==10){
    c=CouleurParNom("brown");
    return c;
  }
}


int main(){
  int i,j,posx=0,posy=0,x,y,compteur=0,a,b,n=0,temoin=0,pause=0,cliquable=0;
  unsigned long suivant;
  suivant=Microsecondes()+DELTA;

  
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
	EcrireTexte(750,450,"time",2);

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
	    if(ToucheEnAttente()&&Touche()==XK_t){
	      pause=1;
	      if(temoin==0){
		cliquable=1;
		for(i=0;i<H;i++){
		  for(j=0;j<L;j++){
		      c=VoireCouleurCase(tab2,i,j);
		      ChoisirCouleurDessin(c);
		      RemplirRectangle(posx,posy,150,150);
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
		temoin=1;
	      }
	      else{
		cliquable=0;
		pause=0;
		for(i=0;i<H;i++){
		  for(j=0;j<L;j++){
		    if(tab1[i][j]==0){
		      c=CouleurParNom("black");
		      ChoisirCouleurDessin(c);
		      RemplirRectangle(posx,posy,150,150);
		    }
		    else{
		      c=VoireCouleurCase(tab2,i,j);
		      ChoisirCouleurDessin(c);
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
		temoin=0;
	      }

	    }
	    if(pause!=1){
	      if(Microsecondes()>suivant)
		{
		  n++;
		  DessinerScene(n);
		  suivant=Microsecondes()+DELTA;
		}
	    }
	      if((SourisCliquee()==1)&&cliquable!=1){
		for(i=0;i<H;i++){
		  for(j=0;j<L;j++){
		    if(((_X>=posx)&&(_Y>=posy))&&((_X<=x)&&(_Y<=y))){
		      if(tab1[i][j]==0){
			c=VoireCouleurCase(tab2,i,j);
			ChoisirCouleurDessin(c);
			RemplirRectangle(posx,posy,150,150);
			tab1[i][j]=1;
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
	    }
	
	  
}
