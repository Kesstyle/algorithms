package pl.kes.algorithms;

class Trie {

    Node top;

    public Trie() {
        
    }
    
    public void insert(String word) {
        if (top == null) {
            top = new Node(word);
            return;
        }
        Node node = top;
        Node prev = null;
        int compareRes = 0;
        while (node != null) {
            String nodeWord = node.value;
            compareRes = compare(word, nodeWord);
            if (compareRes > 0) {
                prev = node;
                node = node.right;
                continue;
            } else if (compareRes < 0) {
                prev = node;
                node = node.left;
                continue;
            } else {
                return;
            }
        }
        if (compareRes > 0) {
            prev.right = new Node(word);
        } else {
            prev.left = new Node(word);
        }
    }
    
    public boolean search(String word) {
        if (top == null) {
            return false;
        }
        Node node = top;
        while (node != null) {
            String nodeWord = node.value;
            if (nodeWord.equals(word)) {
                return true;
            }
            int compareRes = compare(word, nodeWord);
            if (compareRes > 0) {
                node = node.right;
            } else{
                node = node.left;
            }
        }
        return false;
    }
    
    public boolean startsWith(String prefix) {
        if (top == null) {
            return false;
        }
        Node node = top;
        while (node != null) {
            String nodeWord = node.value;
            if (nodeWord.startsWith(prefix)) {
                return true;
            }
            int compareRes = compare(prefix, nodeWord);
            if (compareRes > 0) {
                node = node.right;
            } else{
                node = node.left;
            }
        }
        return false;
    }

    private int compare(String a, String b) {
        int i = 0;
        int j = 0;
        while (i < a.length() && j < b.length()) {
            char ac = a.charAt(i);
            char bc = b.charAt(j);
            if (ac < bc) {
                return -1;
            } else if (ac > bc) {
                return 1;
            }
            i++;
            j++;
        }
        if (i < a.length()) {
            return -1;
        }
        if (j < b.length()) {
            return 1;
        }
        return 0;
    }

    class Node {
        String value;
        Node left;
        Node right;

        public Node(String value) {
            this.value = value;
        }
    }
}
