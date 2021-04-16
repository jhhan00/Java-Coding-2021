import java.util.*;

public class ProgrammersMultiStage {
    // 다단계 회사 문제 ㅋㅋ
    static class Node {
        String name;
        int money;
        Node parent;
        List<Node> children;

        Node(String name, int money, Node node) {
            this.name = name;
            this.money = money;
            this.parent = node;
            this.children = new ArrayList<>();
        }
    }

    static Node root = new Node("MinHo", 0, null);

    public static void print(Node node, int lv) {
        System.out.println(lv + ", " + node.name + ", " + node.money);
        for(Node n: node.children)
            print(n, lv+1);
    }

    public static void findParent(Node parent, Node node, String pr) {
        if(parent.name.equals(pr)) {
            parent.children.add(node);
            node.parent = parent;
        } else {
            for(Node n: parent.children)
                findParent(n, node, pr);
        }
    }

    public static void moneyBack(Node node, int back) {
        if(node == null) return;
        node.money += back;
        if(node.name.equals("MinHo")) return;
        int percent = node.money / 10;
        node.money -= percent;
        moneyBack(node.parent, percent);
    }

    public static void findSeller(Node node, String name, int money) {
        if(node.name.equals(name)) {
            node.money += money;
            int percent = node.money / 10;
            node.money -= percent;
            moneyBack(node.parent, percent);
        } else {
            for(Node n: node.children)
                findSeller(n, name, money);
        }
    }

    public static void findPersonAndMoney(Node node, String name) {
        if(node.name.equals(name)) {
            System.out.println(node.money);
        } else {
            for(Node n: node.children)
                findPersonAndMoney(n, name);
        }
    }

    public static void main(String[] args) {
        String[] enroll = {"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"};
        String[] referral = {"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"};
        String[] seller = {"young", "john", "tod", "emily", "mary"};
        int[] amount = {12, 4, 2, 5, 10};

        for(int i=0; i<enroll.length; i++) {
            Node node = new Node(enroll[i], 0, null);
            if(referral[i].equals("-")) {
                root.children.add(node);
                node.parent = root;
            } else {
                findParent(root, node, referral[i]);
            }
        }

        for(int i=0; i<seller.length; i++) {
            findSeller(root, seller[i], amount[i]*100);
        }

        print(root, 0);
        System.out.println();
        for (String s : enroll)
            findPersonAndMoney(root, s);
    }
}
