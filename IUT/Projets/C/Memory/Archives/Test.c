#include<stdlib.h>
#include<graph.h>
 
int main()
{
	int i, j, posx=10, posy=10, x, y;
	InitialiserGraphique();
	couleur c;
    	CreerFenetre(10,10,1700,1000);
	while(1){
		while(!SourisCliquee()){
			SourisPosition();
			x=_X;
			y=_Y;
			if((x<=150&&x>=0)&&(y<=150&&y>=0)){
				EffacerEcran(CouleurParNom("white"));

				EcrireTexte(150,150,"souris reussie",3);
			}
		}
		if(Touche()==XK_d){
			EffacerEcran(CouleurParNom("white"));
			c=CouleurParNom("red");
			ChoisirCouleurDessin(c);
			for(i=0;i<5;i++){
				for(j=0;j<4;j++){
					RemplirRectangle(posx,posy,150,150);
					posy=posy+160;
				}
				posy=10;
				posx=posx+160;
			}
		}
		else if(Touche()==XK_q){
			FermerGraphique();
			return EXIT_SUCCESS;

		}
		else if(Touche()==XK_s){
			EffacerEcran(CouleurParNom("White"));
			EcrireTexte(150,150,"Salut", 2);
		}
		else if(Touche()==XK_b){
			EffacerEcran(CouleurParNom("red"));
			EcrireTexte(150,150,"aaa",3);
		}
	}



}
