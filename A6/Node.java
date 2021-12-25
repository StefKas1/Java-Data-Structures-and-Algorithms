import java.util.ArrayList;

public class Node {
    String name;
    ArrayList<String> directlyFollows;

    /**
     * Constructor for class Node, which initializes Node with given values ​​of
     * arguments.
     * 
     * @param name            String which represents name of person.
     * @param directlyFollows as ArrayList<String> of people this node (person)
     *                        directly follows.
     * @return no return.
     */
    Node(String name, ArrayList<String> directlyFollows) {
        this.name = name;
        this.directlyFollows = directlyFollows;
    }
}
