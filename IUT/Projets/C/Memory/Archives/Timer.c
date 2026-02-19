#include<stdlib.h>
#include<graph.h>
#include<stdio.h>
#define DELTA 1000000L;


void DessinerScene(int n)
{
  couleur c;
	char buf[100];
	c=CouleurParNom("white");
	ChoisirCouleurDessin(c);
        RemplirRectangle(0,0,100,100);
	c=CouleurParNom("black");
	ChoisirCouleurDessin(c);
	snprintf(buf,100,"temps : %05d",n);
	EcrireTexte(10,20,buf,0);
}



int main()
{
  int n;
  couleur c;
  unsigned long suivant;
    InitialiserGraphique();
    CreerFenetre(10,10,800,500);
    n=0;
    suivant=Microsecondes()+DELTA;
    





    while(1){
      if (Microsecondes()>suivant)
	{
	  n++;
	  DessinerScene(n);
	  suivant=Microsecondes()+DELTA;
	}
    }
    Touche();
    FermerGraphique();
    return EXIT_SUCCESS;
}
