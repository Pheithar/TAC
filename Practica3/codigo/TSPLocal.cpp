#include <iostream>
#include <fstream>

using namespace std;

int** parse_input(string path, int size, bool verbose = false) {
  ifstream f(path);
  string l;
  string aux;
  int count = 0;
  int **map = new int *[size];
  int pos_init = 0;
  int pos_found = 0;
  
  for(int i = 0; i < size; i++) {
    getline(f, l);
    map[count] = new int [size];
    for (int j = 0; j < size; j++) {
      pos_found = l.find(",", pos_init);
      aux = l.substr(pos_init, pos_found - pos_init);
      pos_init = pos_found + 1;
      map[count][j] = stoi(aux); 
    }
    count++; 
  }

  if (verbose) {
    for (int i = 0; i < size; i++) {
    for(int j = 0; j < size; j++){
      cout << map[i][j] << " ";
    }
    cout << endl;
  }
  }
  

  f.close();
  return map;
}

class Node{
  private:
    int city_id;
    int g;
    int *to_visit;
    int *order;
  public:
    Node(){};
    Node(int c, int cost, int *t, int *o) {
      city_id = c;
      g = cost;
      to_visit = t;     
      order = o;
    }
  
    int get_city_id() {
      return city_id;
    }
    
    int get_g() {
      return g;
    }

    int* get_to_visit() {
      return to_visit;
    }

    int* get_order() {
      return order;
    }
};


class Problem
{
private:
  Node root;
  int **map;
  int map_size;
  int id_count;
  int best_path_cost;
  int *best_path;
public:
  Problem(Node r, int **m, int size) {
    root = r;
    map = m;
    map_size = size;
    best_path_cost = -1;
    best_path = new int[map_size];
  }

  void greedy(bool verbose = false){
    Node parent = root;
    // For del camino
    for (int i = 0; i < map_size-1; i++) {
      
      int greedy_path = -1;
      int greedy_city = -1;
      
      // For para elegir el mejor nodo desde cada ciudad
      for (int j = 0; j < map_size; j++) {
        

        if (!parent.get_to_visit()[j]) {
          if (greedy_path == -1 || map[parent.get_city_id()][j] < greedy_path) {
            greedy_path = map[parent.get_city_id()][j];
            greedy_city = j;
          }
        }
      }

      int sum_cost = greedy_path + parent.get_g();
      int *new_to_visit = new int [map_size];
      int *new_order = new int [map_size];
      int flag_order = 0;

      for(int j = 0; j < map_size; j++){
        new_to_visit[j] = parent.get_to_visit()[j];
        new_order[j] = parent.get_order()[j];
        if(new_order[j] == -1 && !flag_order){
          new_order[j] = greedy_city;
          flag_order = 1;
        }
      }
      new_to_visit[greedy_city] = 1;

      Node child(greedy_city, sum_cost, new_to_visit, new_order);

      if (verbose) {
        cout << "Ciudad padre: " << parent.get_city_id()<< " Ciudad hijo: " << child.get_city_id() << endl;
      }

      parent = child;
    }




    if (verbose) {
      cout << "Mejor camino: ";
      for (int i = 0; i < map_size; i++) {
            cout << parent.get_order()[i] << " - ";
      }
      cout << root.get_city_id() << endl;
      cout << "Coste: " << parent.get_g() + map[parent.get_city_id()][root.get_city_id()] << endl;    
    }

    best_path = new int [map_size+1];
    for (int i = 0; i < map_size; i++) {
      best_path[i] = parent.get_order()[i];
    }
    best_path[map_size] = root.get_city_id();
    best_path_cost = parent.get_g() + map[parent.get_city_id()][root.get_city_id()];
  }

  void TwoOpt (bool verbose = false) {

    bool start_again = true;

    while (start_again) {
      start_again = false;

      for (int i = 1; i < map_size-1; i++) {
        for (int k = i+1; k < map_size; k++) {

          int* new_route = TwoOptSwap(best_path, i, k);
          int new_dist = calculateDist(new_route);
          
          if (new_dist < best_path_cost) {
            best_path = new_route;
            best_path_cost = new_dist;
            start_again = true;

            if (verbose) {
              cout << "Nuevo mejor camino encontrado cambiando " << i << " por " << k << endl;
            }
            

          }
        }
      }
    }
    cout << "Mejor camino: ";
      for (int i = 0; i < map_size; i++) {
            cout << best_path[i] << " - ";
      }
      cout << best_path[map_size] << endl;
      cout << "Coste: " << best_path_cost << endl;    
  }



  int* TwoOptSwap (int* route, int i, int k) {

    int *new_route = new int [map_size+1];
    
    for (int j = 0; j < i; j++){
      new_route[j] = route[j];
    }
    int aux = i;
    for (int j = k; j >= i; j--){
      new_route[aux] = route[j];
      aux++;
    }
    for (int j = k+1; j < map_size+1; j++){
      new_route[j] = route[j];
    }
  
    return new_route;
  }

  int calculateDist (int* route) {
    int dist = 0;
    for (int i = 0; i < map_size; i++) {
      dist += map[route[i]][route[i+1]];
    }
    return dist;
  }

};



int main(int argc, char const *argv[]) {
  struct timespec begin, end; 
  clock_gettime(CLOCK_REALTIME, &begin);
  
  int size = stoi(argv[2]);

  int **cities = parse_input(argv[1], size);
  int start = stoi(argv[3]);


  int *to_visit = new int [size];
  int *order = new int [size];

  for (int i = 0; i < size; i++)
  {
    order[i] = -1;
    to_visit[i] = 0; 
  }
  
  to_visit[start] = 1;
  order[0] = start;
  
  Node root(start, 0, to_visit, order);

  Problem problem(root, cities, size);

  problem.greedy();
  problem.TwoOpt(true);
  
  
  clock_gettime(CLOCK_REALTIME, &end);

  long seconds = end.tv_sec - begin.tv_sec;
  long nanoseconds = end.tv_nsec - begin.tv_nsec;
  double elapsed = seconds + nanoseconds*1e-9;

  cout << "Tiempo: " << elapsed << endl;


  return 0;
}
