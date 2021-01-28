import java.util.ArrayList;
import java.util.Collections;

public class main {

    public static Integer findMin(ArrayList<Integer> list)
    {

        if (list == null || list.size() == 0) {
            return Integer.MAX_VALUE;
        }
        ArrayList<Integer> sortedlist = new ArrayList<>(list);
        Collections.sort(sortedlist);
        return sortedlist.get(0);
    }

    static Graph shortestPathFinder(ArrayList<ArrayList<Integer>> matrix, int pos){
        int x = matrix.get(0).size();
        int y = matrix.size();
        Graph g = new Graph(x*y);
        int k = 0;
        for (int i=0 ; i<y ; i++){
            for (int j=0 ; j<x ; j++){
                if (i-1 >= 0 && j-1>=0 && k-x-1>=0 && k-x-1 != pos){
                    g.graph.get(k).set(k-x-1 , (Integer) matrix.get(i-1).get(j-1));
                }
                if (i-1 >= 0 && j>=0 && k-x>=0 && k-x != pos){
                    g.graph.get(k).set(k-x, (Integer) matrix.get(i-1).get(j));
                }
                if (i-1 >= 0 && j+1>=0 && k-x+1>=0 && k-x+1 != pos){
                    g.graph.get(k).set(k-x+1 , (Integer) matrix.get(i-1).get(j+1));
                }

                if (i>= 0 && j-1>=0 && k-1>=0 && k-1 != pos){
                    g.graph.get(k).set(k-1 , (Integer) matrix.get(i).get(j-1));
                }
                if (i >= 0 && j+1<x && k+1>=0 && k+1 != pos){
                    g.graph.get(k).set(k+1 , (Integer) matrix.get(i).get(j+1));
                }

                if (i+1 >= 0 && j-1>=0 && k+x-1>=0 && k+x-1 != pos){
                    g.graph.get(k).set(k+x-1 , (Integer) matrix.get(i+1).get(j-1));
                }
                if (i+1 < y && j>=0 && k+x>=0 && k+x != pos){
                    g.graph.get(k).set(k-x+1 , (Integer) matrix.get(i+1).get(j));
                }
                if (i+1<y && j+1<x && k+x+1>=0 && k+x+1 != pos){
                    g.graph.get(k).set(k+x+1 , (Integer) matrix.get(i+1).get(j+1));
                }
            k++;
            }
        }
        return g;
    }

    static int wallMaker(ArrayList<ArrayList<Integer>> mat , ArrayList pos){
        int x = mat.get(0).size();
        int y = mat.size();
        ArrayList<ArrayList<Integer>> upper_matrix = new ArrayList<>(y);
        ArrayList<ArrayList<Integer>> lower_matrix = new ArrayList<>(y);
        upper_matrix = (ArrayList<ArrayList<Integer>>) mat.subList(0 , (int)pos.get(0)+1);
        lower_matrix = (ArrayList<ArrayList<Integer>>) mat.subList((Integer) pos.get(0), y);
        Graph g1 = shortestPathFinder(upper_matrix , x*(int)pos.get(0) + (int)pos.get(1));
        Graph g2 = shortestPathFinder(lower_matrix , (int)pos.get(1));

        int mini = Integer.MAX_VALUE;
        ArrayList<Integer> selected_line = new ArrayList(x);
        selected_line = mat.get((int)pos.get(0));

        for (int i=0 ; i<(int)pos.get(1) ; i++){
            ArrayList r1 = g1.diskstra((int)pos.get(0)*x + i);
            r1 = (ArrayList) r1.subList( r1.size() - x , r1.size() );
            for (int j=0 ; j<r1.size() ; j++){
                r1.set(j , (int)r1.get(j) + selected_line.get(i));
            }
            ArrayList r2 = g2.diskstra(i);
            r2 = (ArrayList) r2.subList(0 , x);
            for (int j=0 ; j<r2.size() ; j++){
                r2.set(j , (int)r2.get(j) - selected_line.get(j));
            }
            ArrayList<Integer> sum = new ArrayList<>(x - (int)pos.get(1) -1);
            for (int k=(int)pos.get(1)+1 ; k< x ; k++){
                sum.set(k - (int)pos.get(1) -1 , (int)r1.get(k) + (int)r2.get(k));
            }
            int acMin = findMin(sum);
            if (acMin < mini){
                mini = acMin;
            }

        }
        return mini;
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> mat = new ArrayList<>(11);
        for (int i=0 ; i<11 ; i++){
            for (int j=0 ; j<11 ; j++){
                mat.get(i).set(j , 1);
            }
        }
        ArrayList<Integer> pos = new ArrayList<>(2);
        pos.set(0 , 3);
        pos.set(1 , 3);
        System.out.println(wallMaker(mat , pos));
    }


}


















