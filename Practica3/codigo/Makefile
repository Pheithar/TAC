all:
	@echo "Compilando TSP_DFS"
	@g++ TSP_DFS.cpp -o TSP_DFS.out
	@echo "Compilando TSP_DFS con poda"
	@g++ TSP_DFS_poda.cpp -o TSP_DFS_poda.out
	@echo "Compilando TSPLocal"
	@g++ TSPLocal.cpp -o TSPLocal.out


back:
	@echo "Compilando TSP_DFS"
	@g++ TSP_DFS.cpp -o TSP_DFS.out

local:
	@echo "Compilando TSPLocal"
	@g++ TSPLocal.cpp -o TSPLocal.out

clean:
	@echo "Borrando archivos '.out'"
	rm *.out

run:
	@echo "Ejecutando TSP_DFS"
	@echo
	@./TSP_DFS.out
	@echo
	@echo "Ejecutando TSP_DFS con poda"
	@echo
	@./TSP_DFS_poda.out
	@echo
	@echo "Ejecutando TSPLocal"
	@echo
	@./TSPLocal.out

run_dfs:
	@echo "Ejecutando TSP_DFS" 
	@echo
	@./TSP_DFS.out mapa_prueba.txt 8 0

run_dfs_poda:
	@echo "Ejecutando TSP_DFS con poda"
	@echo
	@./TSP_DFS_poda.out mapa_prueba.txt 8 0

run_local:
	@echo "Ejecutando TSPLocal"
	@echo
	@./TSPLocal.out

generate:
	@echo "Generando ciudades"
	@python generaciónCiudades.py
