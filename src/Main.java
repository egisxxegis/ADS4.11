import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/*input.txt example:
A B
B C
C D
C A
D A
G T
W Z
*/

/*11. Iš duoto grafo suformuoti medį. (grafo realizacija paremta kaimynystės matrica; naudoti
paieškos į gylį metodą)*/

public class Main {
    public static void main(String[] args) throws Exception {
        //skaitau duomenis

        File file = new File("C:\\Users\\Algirdas Benetis\\Desktop\\input.txt");
        ArrayList<String> List = new ArrayList<>();
        ArrayList<String> input = new ArrayList<>();
        Scanner sc = new Scanner(file);
        String temp;
        int alreadyIs1;
        int alreadyIs2;
        while (sc.hasNextLine()) {
            temp = sc.nextLine();
            alreadyIs1 = 0;
            alreadyIs2 = 0;
            input.add(temp);
            String[] tempArray = temp.split(" ");
            for (int i = 0; i < List.size(); i++) {
                if (List.get(i).equals(tempArray[0])) //temp 0
                    alreadyIs1 = 1;
                if (List.get(i).equals(tempArray[1])) //temp 1
                    alreadyIs2 = 1;
            }
            if (alreadyIs1 == 0)
                List.add(tempArray[0]); //temp 0
            if (alreadyIs2 == 0)
                List.add(tempArray[1]); // temp 1
        }

        Collections.sort(List);
        //nusiskaičiau, kuriu matrica
        int edges = List.size();
        int[][] matrix = new int[edges][edges];
        for (int i = 0; i < edges; i++) {
            for (int j = 0; j < edges; j++) {
                matrix[i][j] = 0;
            }
        }
        int beginning = 0;
        int end = 0;

        for (int i = 0; i < input.size(); i++) {
            temp = input.get(i);
            String[] tempArray = temp.split(" ");

            for (int j = 0; j < List.size(); j++) {
                if (tempArray[0].equals(List.get(j))) //temp 0
                    beginning = j;
                if (tempArray[1].equals(List.get(j))) //temp 1
                    end = j;
            }

            matrix[beginning][end] = 1;
            matrix[end][beginning] = 1;
        }
        //sukuriau matrica, ja isspausdinu

        for (int i=0; i<matrix.length; i++)
        {
            for (int j=0; j<matrix.length; j++)
                System.out.print(matrix[i][j] + " ");
            System.out.println();
        }
        System.out.println();


        sc.close();
        //nuo cia sukuriu visited array, kad zinociau kur jau buvau
        boolean[] visited = new boolean[edges];
        //dabar darysim dfs

        makeTree(matrix,0,visited,0);
        for (int i=0; i<matrix.length; i++)
        {
            for (int j=i+1; j<matrix.length; j++)
            {
                if (matrix[i][j]==1)
                    System.out.println(List.get(i) + " " + List.get(j));
            }
        }
    }

    private static void makeTree(int[][] matrix, int start, boolean[] visited, int parent)
    {

        visited[start]=true;
        for(int i=0; i<matrix.length; i++)
        {
            if(matrix[start][i]==1)
            {
                if (visited[i] && i!=parent)
                {
                    matrix[start][i]=0;
                    matrix[i][start]=0;
                }
                if (!visited[i])
                {
                    makeTree(matrix,i,visited,start);
                }
            }

        }
    }
}