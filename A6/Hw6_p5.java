import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.LinkedHashSet;

public class Hw6_p5 {
    /**
     * Determines and prints for X 'directly follows' and 'indirectly follows' with
     * adjacency list.
     * 
     * @param X       as String that represents name of person (node).
     * @param adjList as ArrayList<Node> of nodes (people).
     * @return no return.
     */
    static void allFollows(String X, ArrayList<Node> adjList) {
        // Searches node to X and assigns node to xN.
        Node xN = null;
        for (Node node : adjList) {
            if (node.name.equals(X)) {
                xN = node;
                break;
            }
        }

        // Exits method if X does not exist in adjList.
        if (xN == null) {
            System.out.println(X + " does not exist in adjacency list");
            return;
        }

        // Outputs on console all people X (xN) 'directly follows'.
        if (xN.directlyFollows == null) {
            System.out.println(xN.name + " directly follows {}");
        } else {
            System.out.println(
                    xN.name + " directly follows " + xN.directlyFollows.toString().replace("[", "{").replace("]", "}"));
        }

        // Outputs on console all people X 'indirectly follows';
        if (xN.directlyFollows == null) {
            System.out.println(xN.name + " indirectly follows {}");
        } else {
            // Determines all people X 'indirectly follows'.
            // Initializes variables.
            HashSet<String> visitedNodes = new HashSet<>();
            ArrayList<String> toBeVisitedNodes = new ArrayList<>(xN.directlyFollows);

            // Iterates over all names of nodes in toBeVisitedNodes to find 'indirectly
            // follows'.
            for (int index = 0; index < toBeVisitedNodes.size(); index++) {
                // If node has already been visited, does nothing and goes to next iteration.
                if (visitedNodes.contains(toBeVisitedNodes.get(index))) {
                    continue;
                } else {
                    // Else - adds name of current node to visitedNodes.
                    visitedNodes.add(toBeVisitedNodes.get(index));
                    // Searches adjList for node with node.name.
                    for (Node node : adjList) {
                        // True if node with node.name found.
                        if (node.name.equals(toBeVisitedNodes.get(index)) && node.directlyFollows != null) {
                            // Iterates over 'directly follows' of current node.
                            for (String s : node.directlyFollows) {
                                // If node has already been visited, goes to next iteration.
                                if (visitedNodes.contains(s)) {
                                    continue;
                                } else {
                                    // Else - adds name of node to toBeVisitedNodes.
                                    toBeVisitedNodes.add(s);
                                }
                            }
                            // Stops for loop (Node node : adjList).
                            break;
                        }
                    }
                }
            }

            // Removes duplicates from toBeVisitedNodes.
            LinkedHashSet<String> lHS = new LinkedHashSet<>(toBeVisitedNodes);
            toBeVisitedNodes.clear();
            toBeVisitedNodes.addAll(lHS);

            // Removes 'directly follows' from toBeVisitedNodes, and itself.
            for (String s : xN.directlyFollows) {
                toBeVisitedNodes.remove(s);
            }
            toBeVisitedNodes.remove(xN.name);

            // Outputs on console all people X 'indirectly follows'.
            System.out.println(
                    xN.name + " indirectly follows " + toBeVisitedNodes.toString().replace("[", "{").replace("]", "}"));
        }
    }

    /**
     * Reads input txt file, creates adjacency list, and calls method allFollows to
     * determine and to output on console which people a person (node) is following
     * directly and indirectly.
     * 
     * @param args command-line args as array of String objects.
     * @return no return.
     */
    public static void main(String[] args) {
        // Initializes variable.
        ArrayList<Node> adjacencyList = new ArrayList<>();

        // Reads input txt file and stores values in adjacencyList.
        try {
            Scanner scanner = new Scanner(new File("follows_input.txt"));
            String[] line;
            Node node; // A node is a person.
            while (scanner.hasNext()) {
                // Reads line in txt file, removes whitespace, and splits string by comma.
                line = scanner.nextLine().replaceAll("\\s+", "").split(",");
                // Creates Node object; sets name of person and people person directly follows.
                if (line.length == 1) {
                    node = new Node(line[0], null); // Does not follow anyone.
                } else {
                    node = new Node(line[0], new ArrayList<>());
                    // Sets people this person directly follows.
                    for (int index = 1; index < line.length; index++) {
                        node.directlyFollows.add(line[index]);
                    }
                }
                // Stores node in adjacency list.
                adjacencyList.add(node);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Calls allFollows method for every node in adjacencyList.
        for (Node node : adjacencyList) {
            allFollows(node.name, adjacencyList);
        }
    }
}
