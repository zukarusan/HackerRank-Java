import java.util.LinkedList;
import java.util.Queue;
import java.io.*;
import java.util.*;

public class NTurnsBinaryTree {
    // https://www.geeksforgeeks.org/problems/number-of-turns-in-binary-tree/1?page=1&company=Samsung&difficulty=Hard&sortBy=submissions

    static LinkedList<Node> searchPath(Node root, int search, HashMap<Node, Node> parents) {
        Stack<Node> stack = new Stack<>();
        LinkedList<Node> path = new LinkedList<>();
        stack.add(root);
        Node node = null;
        while (!stack.isEmpty()) {
            node = stack.pop();
            if (node.data == search) {
                break;
            }
            if (null != node.left) {
                parents.put(node.left, node);
                stack.add(node.left);
            }
            if (null != node.right) {
                parents.put(node.right, node);
                stack.add(node.right);
            }
        }
        if (node.data != search) {
            return path;
        }
        while (node != null && parents.containsKey(node)) {
            path.addFirst(node);
            node = parents.get(node);
        }
        if (node != null) {
            path.addFirst(node);
        }
        return path;
    }
    static int NumberOfTurns(Node root, int first, int second)
    {
//        BTreePrinter.printNode(root);
        //your code here
        HashMap<Node,Node> parents = new HashMap<>();
        LinkedList<Node> firstPath = searchPath(root, first, parents);
        LinkedList<Node> secondPath = searchPath(root, second, parents);
        if (firstPath.isEmpty() || secondPath.isEmpty() || firstPath.peekLast() == secondPath.peekLast()) {
            return -1;
        }
        LinkedList<Node> search, comp;
        if (firstPath.size() <= secondPath.size()) {
            search = firstPath;
            comp = secondPath;
        } else {
            search = secondPath;
            comp = firstPath;
        }
        while (!comp.isEmpty() && !search.isEmpty()
                && search.peekFirst() != comp.peekFirst()) {
            search.pop();
            comp.pop();
        }
        Node lastCommon = null;
        while (!comp.isEmpty() && !search.isEmpty()
                && search.peekFirst() == comp.peekFirst()) {
            lastCommon = search.peekFirst();
            search.pop();
            comp.pop();
        }
        if (lastCommon == null || (search.isEmpty() && comp.isEmpty())) {
            return -1;
        }

        Node[] from = new Node[2];
        int turns = 0;

        from[0] = search.peekLast();
        if (from[0] != null) {
            from[1] = parents.get(from[0]);
        } else {
            from[1] = null;
        }
        turns += countTurns(from, lastCommon, parents, search.iterator());
        Iterator<Node> iterator = comp.iterator();
        from[0] = lastCommon;
        from[1] = !iterator.hasNext() ? null : iterator.next();
        turns += countTurns(from, comp.peekLast(), parents, iterator);
        if (!comp.isEmpty() && !search.isEmpty()) {
            turns += 1;
        }
        return turns;
    }
    static int countTurns(Node[] fromEdge, Node to, HashMap<Node, Node> parents, Iterator<Node> iterator) {
        if (null == to) {
            return 0;
        }
        Node[] curEdge = new Node[2];
        int turns = 0;
        boolean up = parents.get(fromEdge[0]) == fromEdge[1];
        int i = up ? 1 : 0, j = up ? 0 : 1;
        while (fromEdge[1] != null && fromEdge[1] != to) {
            curEdge[0] = fromEdge[1];
            if (up) {
                curEdge[1] = parents.get(fromEdge[1]);
            } else {
                curEdge[1] = !iterator.hasNext() ? null : iterator.next();
            }
            if (curEdge[1] == null) {
                break;
            }
            if ((fromEdge[i].left == fromEdge[j]) != (curEdge[i].left == curEdge[j])) {
                turns++;
            }
            fromEdge[0] = curEdge[0];
            fromEdge[1] = curEdge[1];
        }
        return turns;
    }

    static class Node{
        int data;
        Node left;
        Node right;
        Node(int data){
            this.data = data;
            left=null;
            right=null;
        }
    }
    // https://www.geeksforgeeks.org/problems/number-of-turns-in-binary-tree/1?page=1&company=Samsung&difficulty=Hard&sortBy=submissions
    static Node buildTree(String str){
        if(str.length()==0 || str.charAt(0)=='N'){
            return null;
        }

        String ip[] = str.split(" ");
        // Create the root of the tree
        Node root = new Node(Integer.parseInt(ip[0]));
        // Push the root to the queue

        Queue<Node> queue = new LinkedList<>();

        queue.add(root);
        // Starting from the second element

        int i = 1;
        while(queue.size()>0 && i < ip.length) {

            // Get and remove the front of the queue
            Node currNode = queue.peek();
            queue.remove();

            // Get the current node's value from the string
            String currVal = ip[i];

            // If the left child is not null
            if(!currVal.equals("N")) {

                // Create the left child for the current node
                currNode.left = new Node(Integer.parseInt(currVal));
                // Push it to the queue
                queue.add(currNode.left);
            }

            // For the right child
            i++;
            if(i >= ip.length)
                break;

            currVal = ip[i];

            // If the right child is not null
            if(!currVal.equals("N")) {

                // Create the right child for the current node
                currNode.right = new Node(Integer.parseInt(currVal));

                // Push it to the queue
                queue.add(currNode.right);
            }
            i++;
        }

        return root;
    }

    public static void main (String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t=Integer.parseInt(br.readLine());

        while(t > 0){
            String s = br.readLine();
            Node root = buildTree(s);

            String line = br.readLine();
            String[] splitLine = line.split(" ");
            int nd1 = Integer.parseInt(splitLine[0]);
            int nd2 = Integer.parseInt(splitLine[1]);
            int turn;

            if((turn = NumberOfTurns(root, nd1, nd2)) != 0)
                System.out.println(turn);
            else
                System.out.println("-1");
            t--;
        }
    }
    static class BTreePrinter {

        public static void printNode(Node root) {
            int maxLevel = BTreePrinter.maxLevel(root);

            printNodeInternal(Collections.singletonList(root), 1, maxLevel);
        }

        private static void printNodeInternal(List<Node> nodes, int level, int maxLevel) {
            if (nodes.isEmpty() || BTreePrinter.isAllElementsNull(nodes))
                return;

            int floor = maxLevel - level;
            int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
            int firstSpaces = (int) Math.pow(2, (floor)) - 1;
            int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

            BTreePrinter.printWhitespaces(firstSpaces);

            List<Node> newNodes = new ArrayList<>();
            for (Node node : nodes) {
                if (node != null) {
                    System.out.print(node.data);
                    newNodes.add(node.left);
                    newNodes.add(node.right);
                } else {
                    newNodes.add(null);
                    newNodes.add(null);
                    System.out.print(" ");
                }

                BTreePrinter.printWhitespaces(betweenSpaces);
            }
            System.out.println("");

            for (int i = 1; i <= endgeLines; i++) {
                for (int j = 0; j < nodes.size(); j++) {
                    BTreePrinter.printWhitespaces(firstSpaces - i);
                    if (nodes.get(j) == null) {
                        BTreePrinter.printWhitespaces(endgeLines + endgeLines + i + 1);
                        continue;
                    }

                    if (nodes.get(j).left != null)
                        System.out.print("/");
                    else
                        BTreePrinter.printWhitespaces(1);

                    BTreePrinter.printWhitespaces(i + i - 1);

                    if (nodes.get(j).right != null)
                        System.out.print("\\");
                    else
                        BTreePrinter.printWhitespaces(1);

                    BTreePrinter.printWhitespaces(endgeLines + endgeLines - i);
                }

                System.out.println("");
            }

            printNodeInternal(newNodes, level + 1, maxLevel);
        }

        private static void printWhitespaces(int count) {
            for (int i = 0; i < count; i++)
                System.out.print(" ");
        }

        private static int maxLevel(Node node) {
            if (node == null)
                return 0;

            return Math.max(BTreePrinter.maxLevel(node.left), BTreePrinter.maxLevel(node.right)) + 1;
        }

        private static <T> boolean isAllElementsNull(List<T> list) {
            for (Object object : list) {
                if (object != null)
                    return false;
            }

            return true;
        }

    }
}