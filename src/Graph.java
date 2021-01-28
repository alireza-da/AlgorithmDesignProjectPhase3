import java.util.ArrayList;

public class Graph {
    int V;
    ArrayList<ArrayList<Integer>> graph = new ArrayList<>(V);

    public Graph(int i) {
        this.V = i;
    }

    int minDistance(ArrayList dist ,ArrayList sptSet){
        int min = Integer.MAX_VALUE;
        int min_index = 0;
        for (int i=0 ; i<V ; i++){
         if (( (int)dist.get(i) < min) && (sptSet.get(i) == false)){
            min = (int)dist.get(i);
            min_index   = i;
         }
        }
        return min_index;
    }
    ArrayList diskstra(int src){
        ArrayList<Integer> dist = new ArrayList<>(V);
        dist.add(src , 0);
        ArrayList<Boolean> sptSet = new ArrayList<>(V);
        for (int i=0 ; i<V ; i++){
            int u = minDistance(dist , sptSet);
            sptSet.set(u , true);
            for (int v=0 ; v<V ; v++){
                if (graph.get(u).get(v) >= 0 && sptSet.get(v) == false && dist.get(v) > dist.get(u) + graph.get(u).get(v)){
                    dist.set(v ,dist.get(u) + graph.get(u).get(v) );
                }
            }
        }
        return dist;
    }



}
