import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Show {

  static ArrayList<City> cities;
  static int[][] roads;
  static ArrayList<Path> totalPaths;

  public static class City {
    int isVisited;
    int name;

    public City(int name) {
      isVisited = 0;
      this.name = name;
    }

    public int getVisit() {
      return isVisited;
    }

    public void setVisit(int visit) {
      this.isVisited = visit;
    }

    public int getName() {
      return this.name;
    }
  }

  public static class Path {
    City one;
    City two;
    City three;
    City four;
    City five;
    City six;

    public Path(City one, City two, City three, City four, City five, City six) {
      this.one = one;
      this.two = two;
      this.three = three;
      this.four = four;
      this.five = five;
      this.six = six;
    }

    public boolean containsEnd() {
      if (this.one == cities.get(cities.size() - 1)) {
        return true;
      }
      if (this.two == cities.get(cities.size() - 1)) {
        return true;
      }
      if (this.three == cities.get(cities.size() - 1)) {
        return true;
      }
      if (this.four == cities.get(cities.size() - 1)) {
        return true;
      }
      if (this.five == cities.get(cities.size() - 1)) {
        return true;
      }
      if (this.six == cities.get(cities.size() - 1)) {
        return true;
      }
      return false;
    }

    public City getLast() {
      return six;
    }
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    Scanner scnr = new Scanner(System.in);
    String input = scnr.nextLine();
    String[] temp = input.split(" ");
    int numCities = Integer.parseInt(temp[0]);
    int numRoads = Integer.parseInt(temp[1]);

    cities = new ArrayList<City>();
    roads = new int[numCities][numCities];
    totalPaths = new ArrayList<Path>();

    String[] tempRoads = new String[numRoads];

    for (int i = 0; i < numRoads; i++) {
      tempRoads[i] = scnr.nextLine();
    }
    scnr.close();

    for (int i = 0; i < tempRoads.length; i++) {
      String[] tempData = tempRoads[i].split(" ");
      int city1 = Integer.parseInt(tempData[0]) - 1;
      int city2 = Integer.parseInt(tempData[1]) - 1;

      roads[city1][city2] = 1;
      roads[city2][city1] = 1;
    }

    for (int i = 0; i < numCities; i++) {
      cities.add(new City(i));
    }


    ArrayList<City> path = new ArrayList<City>();
    cities.get(0).setVisit(1);
    int[] visited = new int[cities.size()];
    boolean endLoop = false;
    int week = 0;
    visited[0] = 1;
    DFS(5, cities.get(0), path, visited);
    while (!endLoop) {
      for (int i = 0; i < totalPaths.size(); i++) {
        if (totalPaths.get(i).containsEnd()) {
          endLoop = true;
          break;
        }
      }

      if (totalPaths.size() == 0) {
        week = -1;
        break;
      } else if (endLoop == true) {
        week++;
        break;
      }

      ArrayList<Path> secondPaths = new ArrayList<Path>();

      while (totalPaths.size() != 0) {
        secondPaths.add(totalPaths.get(0));
        totalPaths.remove(0);
      }
      week++;

      for (int i = 0; i < secondPaths.size(); i++) {
        visited = new int[cities.size()];
        path.clear();
        visited[secondPaths.get(i).getLast().getName()] = 1;
        DFS(5, secondPaths.get(i).getLast(), path, visited);
      }
    }
    System.out.println(week);
  }

  public static void DFS(int depth, City curr, ArrayList<City> path, int[] visited) {
    if (depth == 0 && curr.getName() == cities.size() - 1) {
      path.add(curr);
      createPath(path);
      path.remove(curr);
      visited[curr.getName()] = 0;
      return;
    }
    if (depth == 0) {
      path.add(curr);
      createPath(path);
      path.remove(curr);
      visited[curr.getName()] = 0;
      return;
    }

    for (int i = 0; i < cities.size(); i++) {
      if (roads[curr.getName()][i] == 1 && visited[i] == 0) {
        path.add(curr);
        visited[i] = 1;
        DFS(depth - 1, cities.get(i), path, visited);
        path.remove(curr);
        visited[i] = 0;
      }
    }
  }

  public static void createPath(ArrayList<City> path) {
    Path newPath =
        new Path(path.get(0), path.get(1), path.get(2), path.get(3), path.get(4), path.get(5));
    totalPaths.add(newPath);
  }
}
