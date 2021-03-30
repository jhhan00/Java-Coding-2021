import java.util.*;

public class NTreeCoding {
    static class Node {
        private final int id;
        private final String item;
        private final List<Node> children;

        public int getId() {
            return id;
        }

        public String getItem() {
            return item;
        }

        public List<Node> getChildren() {
            return children;
        }

        Node(int id, String item, List<Node> children) {
            this.id = id;
            this.item = item;
            this.children = children;
        }
    }

    public static List<String> tree = Arrays.asList(
            "1 BROWN 0",
            "2 CONY 0",
            "3 DOLL 1",
            "4 DOLL 2",
            "5 LARGE-BROWN 3",
            "6 SMALL-BROWN 3",
            "7 BLACK-CONY 4",
            "8 BROWN-CONY 4",
            "9 MIDDLE-BROWN 3"
    );
    public static final String search = "BROWN";
    public static Node[] dataSets = new Node[100];
    public static int nRoot=0;

    public static void createRoot(int id, String name) {
        int idx = nRoot++;
        dataSets[idx] = new Node(id, name, new ArrayList<>());
    }

    public static void insertNode(int parent, int id, String name) {
        for(int i=0; i<nRoot; i++) {
            if(dataSets[i].id == parent) {
                Node node = new Node(id, name, new ArrayList<>());
                dataSets[i].children.add(node);
                return;
            } else {
                for(int j=0; j<dataSets[i].getChildren().size(); j++) {
                    insertNode2(dataSets[i].getChildren().get(j), parent, id, name);
                }
            }
        }
    }

    public static void insertNode2(Node node, int parent, int id, String name) {
        if(node.getId() == parent) {
            Node newNode = new Node(id, name, new ArrayList<>());
            node.children.add(newNode);
        } else {
            for(int i=0; i<node.getChildren().size(); i++) {
                insertNode2(node.getChildren().get(i), parent, id, name);
            }
        }
    }

    public static void printNode(Node node, int lvl) {
        System.out.println("level: " + lvl + ", " + + node.getId() + ": " + node.getItem());
        for(int i=0; i<node.getChildren().size(); i++) {
            printNode(node.getChildren().get(i), lvl+1);
        }
    }

    public static void findItem(Node node, String name, String print) {
        if(node.getChildren().size() != 0) {
            for(int i=0; i<node.getChildren().size(); i++) {
                findItem(node.getChildren().get(i), name, print + node.getItem() + "/");
            }
        } else {
            if(node.getItem().contains(name)) {
                System.out.println(node.getItem() + ", " + print + node.getItem());
            }
        }
    }

    public static void main(String[] args) {
        for(String s: tree) {
            int loc1=s.indexOf(" ");
            int loc2=s.indexOf(" ",loc1+1);

            int id = Integer.parseInt(s.substring(0,loc1));
            String name = s.substring(loc1+1,loc2);
            int parent = Integer.parseInt(s.substring(loc2+1));

            if(parent==0) {
                createRoot(id, name);
            } else {
                insertNode(parent, id, name);
            }
        }

//        for (Node dataSet : dataSets) {
//            if (dataSet != null) {
//                printNode(dataSet, 0);
//            }
//        }

        for(Node dataSet : dataSets) {
            if(dataSet != null) {
                findItem(dataSet, search, "");
            }
        }
    }
}
