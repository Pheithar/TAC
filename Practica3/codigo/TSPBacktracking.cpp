#include <iostream>
using namespace std;

class Node{
  private:
    int id;
    int city_id;
    int g;
    int parent;
  public:
    Node(){};
    Node(int i, int c, int cost, int f) {
      id = i;
      city_id = c;
      g = cost;
      parent = f;
    }

    int get_id() {
      return id;
    }
  
    int get_city_id() {
      return city_id;
    }
    
    int get_g() {
      return g;
    }

    int get_parent() {
      return parent;
    }
};

class Problem{
  private:
    int **map;
    int map_size;
    Node root;
    int id_count;
    
  public:
    Problem(int **m, int size, Node r, int i){
      map = m;
      map_size = size;
      root = r;
      id_count = i;
    }
    
    int create_children(int id, int city, int cost, int parent, bool verbose = false) {
    
      Node p(id, city, cost, parent);
      
      for(int i = 0; i < map_size; i++){
        if(i != p.get_city_id()){
          id_count++;
          
          if (verbose) cout << "Creando nodo " << id_count << endl;
          
          Node child(id_count, i, p.get_g() + map[p.get_city_id()][i], p.get_id());
        }
      } 
    }
};


int main() {

  int m[4][4] = { {0, 1, 1, 1}, {1, 0, 1, 1}, {1, 1, 0, 1}, {1, 1, 1, 0} };
  int **cities;
  cities = new int * [4];

  for(int i = 0; i < 4; i++){
    cities[i] = new int [4];
    for(int j = 0; j < 4; j++){
      cities[i][j] = m[i][j];
    }
  }
  int start = 0;
  
  int id_count = 0;

  Node root(id_count, start, 0, -1);
  id_count++;

  Problem problem(cities, 4, root, id_count);
  problem.create_children(id_count, start, 0, -1, true);
  
  return 0;
}