all:
	@echo "Compilando TSPBacktracking"
	@g++ TSPBacktracking.cpp -o TSPBacktracking.out
	@echo "Compilando TSPLocal"
	@g++ TSPLocal.cpp -o TSPLocal.out


back:
	@echo "Compilando TSPBacktracking"
	@g++ TSPBacktracking.cpp -o TSPBacktracking.out

local:
	@echo "Compilando TSPLocal"
	@g++ TSPLocal.cpp -o TSPLocal.out

clean:
	@echo "Borrando archivos '.out'"
	rm *.out

run:
	@echo "Ejecutando TSPBacktracking"
	@echo
	@./TSPBacktracking.out
	@echo
	@echo "Ejecutando TSPLocal"
	@echo
	@./TSPLocal.out

run_back:
	@echo "Ejecutando TSPBacktracking" 
	@echo
	@./TSPBacktracking.out mapa_prueba.txt 4 0

run_local:
	@echo "Ejecutando TSPLocal"
	@echo
	@./TSPLocal.out

generate:
	@echo "Generando ciudades"
	@python generaciónCiudades.py
