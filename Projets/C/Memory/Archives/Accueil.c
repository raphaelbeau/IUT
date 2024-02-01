#include<stdlib.h>
#include<graph.h>
 
int main()
{
    InitialiserGraphique();
    couleur c;
    CreerFenetre(10,10,1000,1000);
    EcrireTexte(10,450,"Jeu de paires",2.75);
    
    c=CouleurParNom("yellow");
    ChoisirCouleurDessin(c);
    RemplirRectangle(700,100,200,200);
    c=CouleurParNom("orange");
    ChoisirCouleurDessin(c);
    RemplirRectangle(700,400,200,200);
    c=CouleurParNom("red");
    ChoisirCouleurDessin(c);
    RemplirRectangle(700,700,200,200);
    c=CouleurParNom("black");
    ChoisirCouleurDessin(c);
    EcrireTexte(775,200,"2*3",2.75);
    EcrireTexte(775,500,"4*3",2.75);
    EcrireTexte(775,800,"5*4",2.75);


    while(1){
      if((SourisCliquee()==1)){
	if(((_X>=700)&&(_Y>=100))&&((_X<=900)&&(_Y<=300))){
	  
	}
	if(((_X>=700)&&(_Y>=400))&&((_X<=900)&&(_Y<=600))){
	  
	}
	if(((_X>=700)&&(_Y>=700))&&((_X<=900)&&(_Y<=900))){
	  
	}
      }      
    }
}
