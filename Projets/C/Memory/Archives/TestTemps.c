#include<stdlib.h>
#include<graph.h>
#include<stdio.h>
#define DELTA 1000000L;
 
int main()
{
  char buf[100];
  int n;
  unsigned long suivant;
    InitialiserGraphique();
    CreerFenetre(10,10,500,500);
    EcrireTexte(10,100,"Hello World !",2);
    n=0;
    suivant=Microsecondes()+DELTA;
    





    while(1){
      if (Microsecondes()>suivant)
	{
	  n++;
	  snprintf(buf,100,"temps : %05d",n);
	  EcrireTexte(10,20,buf,0);
	  suivant=Microsecondes()+DELTA;
	}
    }
    Touche();
    FermerGraphique();
    return EXIT_SUCCESS;
}
