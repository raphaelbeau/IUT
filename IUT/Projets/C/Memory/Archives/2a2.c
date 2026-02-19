#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define H 4
#define L 1

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

int main(void){
  int tab[H][L];
  int a,i,j,b;
  srand(time(NULL));
  b=(H*L)/2;
  for(i=0;i<H;i++){
    for(j=0;j<L;j++){
      tab[i][j]=0;
    }
  }
  for(i=0;i<H;i++){
    for(j=0;j<L;j++){
      a=rand()%b+1;
      if(DansTab(tab,a)==0){
	tab[i][j]=a;
      }
      else{
	while(DansTab(tab,a)==1){
	  a=rand()%b+1;
	}
	tab[i][j]=a;
      }
    }
  }
  for(i=0;i<H;i++){
    for(j=0;j<L;j++){
      printf("%2d", tab[i][j]);
    }
    printf("\n");
  }
}
