#include<stdio.h>
#include<stdlib.h>
#include<graph.h>
#include "Menu.h"
#include "Jeu.h"

int MenuDebut(void){
  couleur c=CouleurParNom("green");
  ChoisirCouleurDessin(c);
  RemplirRectangle(1300,100,300,200);
  c=CouleurParNom("purple");
  ChoisirCouleurDessin(c);
  RemplirRectangle(1300,400,300,200);
  c=CouleurParNom("red");
  ChoisirCouleurDessin(c);
  RemplirRectangle(1300,700,300,200);


  
  c=CouleurParNom("white");
  ChoisirCouleurDessin(c);
  EcrireTexte(1400,150,"Normal",1);
  EcrireTexte(1400,450,"Mirror",1);
  EcrireTexte(1400,750,"Hard",1);
  EcrireTexte(50,100,"Snake de Maxim Lalane et Raphael Beau",2);
  EcrireTexte(200,200,"Choisissez un mode de jeu : ",1);
  EcrireTexte(250,250,"-Normal, un jeu snake tout ce qu'il y a de plus basique",1);
  EcrireTexte(250,300,"-Mirror, les murs sur les cotes tombent, les boules violettes font leur apparition !",1);
  EcrireTexte(250,350,"-Hard, Bon courage !! (vraiment)",1);
  EcrireTexte(200,450,"Les regles sont simples :",1);

  
  c=CouleurParNom("dark blue");
  ChoisirCouleurDessin(c);
  RemplirRectangle(650,483,20,20);
  c=CouleurParNom("red");
  ChoisirCouleurDessin(c);
  RemplirArc(650,483,20,20,360,360);

  c=CouleurParNom("dark blue");
  ChoisirCouleurDessin(c);
  RemplirRectangle(803,582,20,20);
  c=CouleurParNom("purple");
  ChoisirCouleurDessin(c);
  RemplirArc(803,582,20,20,360,360);

  c=CouleurParNom("dark grey");
  ChoisirCouleurDessin(c);
  RemplirRectangle(615,530,20,20);
  c=CouleurParNom("black");
  ChoisirCouleurDessin(c);
  DessinerRectangle(617,532,16,16);
  
  c=CouleurParNom("white");
  ChoisirCouleurDessin(c);
  EcrireTexte(250,500,"Regle n1-Mangez un maximum de pommes         pour grandir",1);
  EcrireTexte(250,550,"Regle n2-Ne vous prennez pas de murs        , ou vous perdrez !",1);
  EcrireTexte(250,600,"Regle n3-Malheur a vous si vous prennez une prune surprise        .",1);
  EcrireTexte(250,650,"Regle n4-N'oubliez pas de vous amuser ;)",1);

  EcrireTexte(200,750,"Les Controles :",1);
  EcrireTexte(250,800,"-Deplacez vous avec les fleches du clavier",1);
  EcrireTexte(250,850,"-Mettez pause a tout moments grace a la touche espace",1);
  EcrireTexte(250,900,"-Vous pouvez mettre fin a la partie avec la touche echap",1);

  while(1){
    if(SourisCliquee()==1){
      if(_X>=1300&&_Y>=100&&_X<=1600&&_Y<=300){
	return 1;
      }
      if(_X>=1300&&_Y>=400&&_X<=1600&&_Y<=600){
	return 2;
      }
      if(_X>=1300&&_Y>=700&&_X<=1600&&_Y<=900){
	return 3;
      }
    }
  }
}


/*Initialisation de la page*/
void InitEcran(void){
  InitialiserGraphique();
  CreerFenetre(10,10,1700,1000);
  couleur c;
  c=CouleurParNom("dark blue");
  ChoisirCouleurDessin(c);
  RemplirRectangle(0,0,1700,1000);
}

void MenuPause(int pause){
  couleur c;
  if(pause == 0){
	c=CouleurParNom("white");
	ChoisirCouleurDessin(c);
  EcrireTexte(1330,450,"Menu pause (appuyez sur espace)",1);
  }else{
    c=CouleurParNom("dark blue");
    ChoisirCouleurDessin(c);
    RemplirRectangle(1330,300,500,500);
  }
}

int MenuFinPerdu(void){
	couleur c=CouleurParNom("purple");
  ChoisirCouleurDessin(c);
  RemplirRectangle(1300,400,300,200);
  c=CouleurParNom("red");
  ChoisirCouleurDessin(c);
  RemplirRectangle(1300,700,300,200);
  
  c=CouleurParNom("white");
	ChoisirCouleurDessin(c);
  EcrireTexte(1400,450,"Recommencer",1);
  EcrireTexte(1400,750,"Quitter",1);
  
  EcrireTexte(1350,200,"Dommage !",2);
  EcrireTexte(1280,250,"Peut-etre une prochaine fois",2);
  
  while(1){
    if(SourisCliquee()==1){
      if(_X>=1300&&_Y>=400&&_X<=1600&&_Y<=600)
      {
        FermerGraphique();
        LancerJeu();
      }
      if(_X>=1300&&_Y>=700&&_X<=1600&&_Y<=900)
      {
        FermerGraphique();
        return EXIT_SUCCESS;
      }
    }
  }
}

int MenuFinGagne(void){
	couleur c=CouleurParNom("purple");
  ChoisirCouleurDessin(c);
  RemplirRectangle(1300,400,300,200);
  c=CouleurParNom("red");
  ChoisirCouleurDessin(c);
  RemplirRectangle(1300,700,300,200);
  
  c=CouleurParNom("white");
  ChoisirCouleurDessin(c);
  EcrireTexte(1400,450,"Recommencer",1);
  EcrireTexte(1400,750,"Quitter",1);
  
  EcrireTexte(1350,200,"Felicitations !",2);
  EcrireTexte(1280,200,"Vous etes trop fort !!",2);
  
  while(1){
    if(SourisCliquee()==1){
      if(_X>=1300&&_Y>=400&&_X<=1600&&_Y<=600)
      {
        FermerGraphique();
        LancerJeu();
      }
      if(_X>=1300&&_Y>=700&&_X<=1600&&_Y<=900)
      {
        FermerGraphique();
        return EXIT_SUCCESS;
      }
    }
  }
}
