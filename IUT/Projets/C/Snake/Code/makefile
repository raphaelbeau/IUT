exec : Chemin.o Deplacement.o Afficher.o ModifTab.o main.o Menu.o Jeu.o
	gcc -ansi -o exec Chemin.o Deplacement.o Afficher.o ModifTab.o main.o Menu.o Jeu.o -lgraph
run :	exec
	./exec
Chemin.o : Chemin.c
	gcc -c Chemin.c -lgraph
Deplacement.o : Deplacement.c
	gcc -c Deplacement.c -lgraph
Afficher.o : Afficher.c
	gcc -c Afficher.c -lgraph
ModifTab.o : ModifTab.c
	gcc -c ModifTab.c -lgraph
main.o : main.c
	gcc -c main.c -lgraph
Menu.o : Menu.c
	gcc -c Menu.c -lgraph
Jeu.o : Jeu.c
	gcc -c Jeu.c -lgraph
clean :
	rm -f *~
	rm -f *.o
	rm exec
