import java.io.*;
import java.util.HashMap;
import java.util.HashSet;

public class Endoscope {
    // https://www.hackerrank.com/contests/target-samsung-13-nov19/challenges/endoscope

    public static HashSet<Integer> getOrCreate(HashMap<Integer, HashSet<Integer>> pipes, Integer el) {
        if (!pipes.containsKey(el)) {
            HashSet<Integer> adj = new HashSet<>();
            pipes.put(el, adj);
            return adj;
        } else {
            return pipes.get(el);
        }
    }
    public static void traverse(int[][] map, int vert, int hor, int length, int limit, int height, int width, HashMap<Integer, HashSet<Integer>> pipes)
    {
        if(length>=limit || vert >= height || vert < 0 || hor >= width || hor < 0)
            return;
        if(map[vert][hor] == 1)
        {
            HashSet<Integer> set = getOrCreate(pipes, vert);
            if (!set.contains(hor))
                set.add(hor);
            if(vert+1<height && (map[vert+1][hor]==1 || map[vert+1][hor]==2 || map[vert+1][hor]==4|| map[vert+1][hor]==7))
                traverse(map, vert+1,hor,length+1,limit,height,width, pipes);
            if(vert-1>=0 && (map[vert-1][hor]==1 || map[vert-1][hor]==2 || map[vert-1][hor]==6|| map[vert-1][hor]==5))
                traverse(map, vert-1,hor,length+1,limit,height,width, pipes);
            if(hor-1>=0 && (map[vert][hor-1]==1 || map[vert][hor-1]==3 || map[vert][hor-1]==5|| map[vert][hor-1]==4))
                traverse(map, vert,hor-1,length+1,limit,height,width, pipes);
            if(hor+1<width && (map[vert][hor+1]==1 || map[vert][hor+1]==3 || map[vert][hor+1]==6|| map[vert][hor+1]==7))
                traverse(map, vert,hor+1,length+1,limit,height,width, pipes);
        }
        else if(map[vert][hor] == 2)
        {
            HashSet<Integer> set = getOrCreate(pipes, vert);
            if (!set.contains(hor))
				set.add(hor);
            if(vert-1>=0 && (map[vert-1][hor]==1 || map[vert-1][hor]==2 || map[vert-1][hor]==6|| map[vert-1][hor]==5))
                traverse(map, vert-1,hor,length+1,limit,height,width, pipes);
            if(vert+1<height && (map[vert+1][hor]==1 || map[vert+1][hor]==2 || map[vert+1][hor]==4|| map[vert+1][hor]==7))
                traverse(map, vert+1,hor,length+1,limit,height,width, pipes);

        }
        else if(map[vert][hor] == 3)
        {
            HashSet<Integer> set = getOrCreate(pipes, vert);
            if (!set.contains(hor))
                set.add(hor);
            if(hor-1>=0 && (map[vert][hor-1]==1 || map[vert][hor-1]==3 || map[vert][hor-1]==5|| map[vert][hor-1]==4))
                traverse(map, vert,hor-1,length+1,limit,height,width, pipes);
            if(hor+1<width && (map[vert][hor+1]==1 || map[vert][hor+1]==3 || map[vert][hor+1]==6|| map[vert][hor+1]==7))
                traverse(map, vert,hor+1,length+1,limit,height,width, pipes);
        }
        else if(map[vert][hor] == 4)
        {
            HashSet<Integer> set = getOrCreate(pipes, vert);
            if (!set.contains(hor))
                set.add(hor);
            if(vert-1>=0 && (map[vert-1][hor]==1 || map[vert-1][hor]==2 || map[vert-1][hor]==6|| map[vert-1][hor]==5))
                traverse(map, vert-1,hor,length+1,limit,height,width, pipes);
            if(hor+1<width && (map[vert][hor+1]==1 || map[vert][hor+1]==3 || map[vert][hor+1]==6|| map[vert][hor+1]==7))
                traverse(map, vert,hor+1,length+1,limit,height,width, pipes);
        }
        else if(map[vert][hor] == 5)
        {
            HashSet<Integer> set = getOrCreate(pipes, vert);
            if (!set.contains(hor))
                set.add(hor);
            if(hor+1<width && (map[vert][hor+1]==1 || map[vert][hor+1]==3 || map[vert][hor+1]==6|| map[vert][hor+1]==7))
                traverse(map, vert,hor+1,length+1,limit,height,width, pipes);
            if(vert+1<height && (map[vert+1][hor]==1 || map[vert+1][hor]==2 || map[vert+1][hor]==4|| map[vert+1][hor]==7))
                traverse(map, vert+1,hor,length+1,limit,height,width, pipes);

        }
        else if(map[vert][hor] == 6)
        {
            HashSet<Integer> set = getOrCreate(pipes, vert);
            if (!set.contains(hor))
                set.add(hor);
            if(hor-1>=0 && (map[vert][hor-1]==1 || map[vert][hor-1]==3 || map[vert][hor-1]==5|| map[vert][hor-1]==4))
                traverse(map, vert,hor-1,length+1,limit,height,width, pipes);
            if(vert+1<height && (map[vert+1][hor]==1 || map[vert+1][hor]==2 || map[vert+1][hor]==4|| map[vert+1][hor]==7))
                traverse(map, vert+1,hor,length+1,limit,height,width, pipes);

        }
        else if(map[vert][hor] == 7)
        {
            HashSet<Integer> set = getOrCreate(pipes, vert);
            if (!set.contains(hor))
                set.add(hor);
            if(hor-1>=0 && (map[vert][hor-1]==1 || map[vert][hor-1]==3 || map[vert][hor-1]==5|| map[vert][hor-1]==4))
                traverse(map, vert,hor-1,length+1,limit,height,width, pipes);
            if(vert-1>=0 && (map[vert-1][hor]==1 || map[vert-1][hor]==2 || map[vert-1][hor]==6|| map[vert-1][hor]==5))
                traverse(map, vert-1,hor,length+1,limit,height,width, pipes);
        }
        else return;
    }
    public static void main (String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        String test = bufferedReader.readLine();
        int t = Integer.parseInt(test.replaceAll("\\s+$", ""));
        int height, width, endoRow, endoCol, endoLength;

        int[][] map;
        int[] result = new int[t];
        HashMap<Integer, HashSet<Integer>> pipes = new HashMap<>();
        for (int i = 0; i < t; i++) {
            String[] params = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");
            height = Integer.parseInt(params[0]);
            width = Integer.parseInt(params[1]);
            endoRow = Integer.parseInt(params[2]);
            endoCol = Integer.parseInt(params[3]);
            endoLength = Integer.parseInt(params[4]);
            map = new int[height][width];
            for (int j = 0; j < height; j++) {
                String[] row = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");
                for (int k = 0; k < width; k++) {
                    map[j][k] = Integer.parseInt(row[k]);
                }
            }
            pipes.clear();
            traverse(map, endoRow, endoCol, 0, endoLength, height, width, pipes);
            result[i] = 0;
            for (HashSet<Integer> set : pipes.values()) {
                result[i] += set.size();
            }
        }
        for (int rs : result) {
            bufferedWriter.write(String.valueOf(rs));
            bufferedWriter.newLine();
        }

        bufferedReader.close();
        bufferedWriter.close();
    }
}
