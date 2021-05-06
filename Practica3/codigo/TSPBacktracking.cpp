#include <iostream>
#include <stack>
#include <string.h>
using namespace std;


class Node{
  private:
    int id;
    int city_id;
    int g;
    Node *parent;
    int *to_visit;
  public:
    Node(){};
    Node(int i, int c, int cost, Node *p, int *t) {
      id = i;
      city_id = c;
      g = cost;
      parent = p;
      to_visit = t;
      if(id != 0) {
        cout << "Me he creado y mi padre es " << parent->get_id() << endl;
        cout << "Yo soy " << id << endl;
      }
     
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

    Node* get_parent() {
      return parent;
    }

    int* get_to_visit() {
      return to_visit;
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
public:
  Problem(Node r, int **m, int size) {
    root = r;
    not_visited.push(root);
    map = m;
    map_size = size;
    id_count = root.get_id();
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

          for(int j = 0; j < map_size; j++){
            new_to_visit[j] = parent.get_to_visit()[j];
          }
          new_to_visit[i] = 1;

          Node child(id_count, i, sum_cost, &parent, new_to_visit);

          cout << "Dir " << parent.get_parent() << " " << &parent << endl;
           
          if (verbose) {
            cout << "Nodo padre: " << child.get_parent()->get_id() << " Nodo hijo: " << child.get_id() <<
            " Ciudad padre: " << child.get_parent()->get_city_id()<< " Ciudad hijo: " << child.get_city_id() << endl;
          }
          
          
          
          not_visited.push(child);

          
        }        
      }
    

      if (verbose) {
        cout << "Ciudad " << parent.get_city_id() << " visitada" << endl;
      }

      if (!has_child) {
        cout << "Coste " << parent.get_id() << " = " << parent.get_g() + map[parent.get_city_id()][root.get_city_id()] << endl;

        Node up_stack = parent;
        int * path = new int [map_size];
        int counter = 0;
        Node a = *up_stack.get_parent();
        cout << up_stack.get_city_id() << endl;
        cout << a.get_parent()->get_city_id() << endl;

        /*while (up_stack.get_parent() != NULL) {
          path[counter] = up_stack.get_city_id();
          delete &up_stack;
          up_stack = *up_stack.get_parent();
          cout << up_stack.get_city_id() << endl;
        }*/
        cout << "Voy caminando por la vida: " << endl;
        for (int i = 0; i < map_size; i++) {
          cout << "Camino " << i << " - " << path[i] << endl;
        }

      }

      
    }

    return 0;
  }
};















/*
class Problem1{
  private:
    int **map;
    int map_size;
    Node root;
    int id_count;
    stack<int> visited;
    
  public:
    Problem1(int **m, int size, Node r, int i){
      map = m;
      map_size = size;
      root = r;
      id_count = i;
      visited = new int [map_size];
      not_
    }

    int* get_visited(){
      return visited;
    }
    
    int create_node_stack(int id, int city, int cost, int parent, bool verbose = false) {
      
    }
    int create_children(int id, int city, int cost, int parent, bool verbose = false) {
      cout << "-------------------------" << endl;
      
      Node p(id, city, cost, parent);
      
      visited[city] = 1;
      
      for(int j = 0; j < map_size; j++){
        cout << "La ciudad " << j << " ha sido visitada: " << visited[j] << endl;
      }
      
      for(int i = 0; i < map_size; i++){        
        if(visited[i] != 1){
          
          int sum_cost = p.get_g() + map[p.get_city_id()][i];

          Node child(id_count, i, sum_cost, p.get_id());
          id_count++;
          
          if (verbose) cout << "Nodo padre: " << p.get_id() << " Nodo hijo: " << id_count << " Ciudad padre: " << p.get_city_id() << " Ciudad hijo: " << i << endl;
          
          create_children(id_count, child.get_city_id(), child.get_g(), child.get_id(), verbose);
        }
      }
      return 0;
    }
};
*/

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

  int *to_visit = new int [4];
  to_visit[0] = 1;
  
  Node root(0, start, 0, NULL, to_visit);


  Problem problem(root, cities, 4);

  
  //int a = problem.create_children(id_count, start, 0, -1, true);
  problem.DFS(true);

  //cout << problem.get_not_visited().top().get_id() << endl;
  //cout << problem.get_not_visited().top().get_parent() << endl;

  return 0;
}