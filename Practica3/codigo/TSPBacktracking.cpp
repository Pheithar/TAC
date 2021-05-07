#include <iostream>
#include <stack>
#include <string.h>
#include <fstream>
#include <vector>
#include <string>
using namespace std;


class Node{
  private:
    int id;
    int city_id;
    int g;
    int *to_visit;
    int *order;
  public:
    Node(){};
    Node(int i, int c, int cost, int *t, int *o) {
      id = i;
      city_id = c;
      g = cost;
      to_visit = t;     
      order = o;
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
  stack <Node> not_visited;
  int **map;
  int map_size;
  int id_count;
  int best_path_cost;
  int *best_path;
public:
  Problem(Node r, int **m, int size) {
    root = r;
    not_visited.push(root);
    map = m;
    map_size = size;
    id_count = root.get_id();
    best_path_cost = -1;
    best_path = new int[map_size];
  }
  stack<Node> get_not_visited() {
    return not_visited;
  }

  int DFS(bool verbose = false) {
    while (!not_visited.empty()){
      Node parent = not_visited.top();

      not_visited.pop();


      bool has_child = false;

      for(int i = 0; i < map_size; i++) {
        if(!parent.get_to_visit()[i]) {
          id_count++;
          
          has_child = true;

          int sum_cost = parent.get_g() + map[parent.get_city_id()][i];
          int *new_to_visit = new int [map_size];
          int *new_order = new int [map_size];
          int flag_order = 0;

          for(int j = 0; j < map_size; j++){
            new_to_visit[j] = parent.get_to_visit()[j];
            new_order[j] = parent.get_order()[j];
            if(new_order[j] == -1 && !flag_order){
              new_order[j] = i;
              flag_order = 1;
            }
          }
          new_to_visit[i] = 1;
          

          Node child(id_count, i, sum_cost, new_to_visit, new_order);

           
          if (verbose) {
            cout << "Nodo padre: " << parent.get_id() << " Nodo hijo: " << child.get_id() <<
            " Ciudad padre: " << parent.get_city_id()<< " Ciudad hijo: " << child.get_city_id() << endl;
          }
          
          
          
          not_visited.push(child);

          
        }        
      }
    
      if (verbose) {
        cout << "Ciudad " << parent.get_city_id() << " visitada" << endl;
      }

      if (!has_child) {

        int p_cost = parent.get_g() + map[parent.get_city_id()][root.get_city_id()];
                
        if (best_path_cost > p_cost || best_path_cost == -1) {
          best_path_cost = p_cost;
          best_path = parent.get_order();
        }


      }
    }

    cout << "Mejor camino: ";
    for (int i = 0; i < map_size; i++) {
          cout << best_path[i] << " - ";
    }
    cout << root.get_city_id() << endl;
    cout << "Coste: " << best_path_cost << endl;
    
    


    return 0;
  }
};


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

int main(int argc, char const *argv[]) {

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
  
  Node root(0, start, 0, to_visit, order);
  

  Problem problem(root, cities, size);

  
  problem.DFS();

  return 0;
}
