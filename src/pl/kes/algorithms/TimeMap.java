package pl.kes.algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class TimeMap {

    public static void main(String...args) {
        TimeMap timeMap = new TimeMap();
        timeMap.set("love", "high", 10);
        timeMap.set("love", "low", 20);
        timeMap.get("love", 5);
        timeMap.get("love", 10);
        timeMap.get("love", 15);
        timeMap.get("love", 20);
        timeMap.get("love", 25);
    }

    private Map<String, List<Pair<String, Integer>>> map;

    public TimeMap() {
        map = new HashMap<>();
    }
    
    public void set(String key, String value, int timestamp) {
        if (map.get(key) == null) {
            map.put(key, new ArrayList<>());
        }
        map.get(key).add(new Pair(value, timestamp));
    }
    
    public String get(String key, int timestamp) {
        if (map.get(key) == null) {
            return "";
        }
        return getFloorValue(map.get(key), timestamp);
    }

    private String getFloorValue(List<Pair<String, Integer>> list, int timestamp) {
        int lo = 0;
        int hi = list.size();
        Integer res = null;
        while (lo <= hi && lo < list.size()) {
            int center = (hi + lo) / 2;
            int pairValue = list.get(center).second;
            if (pairValue == timestamp) {
                return list.get(center).first;
            } else if (pairValue < timestamp) {
                res = center;
                lo = center + 1;
            } else {
                hi = center - 1;
            }
        }
        if (res == null) {
            return "";
        } else {
            return list.get(res).first;
        }
    }

    class Pair<T, V> {
        T first;
        V second;

        public Pair(T f, V s) {
            first = f;
            second = s;
        }
    }

    class Tree {
        TreeNode root;

        public void add(int timestamp, String value) {
            root = add(root, timestamp, value);
        }

        public String get(int timestamp) {
            return get(root, timestamp, "");
        }

        private String get(TreeNode node, int timestamp, String lastRes) {
            if (node == null) {
                return lastRes;
            }
            int time = node.timestamp;
            if (timestamp > time) {
                return get(node.right, timestamp, node.value);
            } else if (timestamp < time) {
                return get(node.left, timestamp, lastRes);
            } else {
                return node.value;
            }
        }

        private TreeNode add(TreeNode node, int timestamp, String value) {
            if (node == null) {
                return new TreeNode(timestamp, value);
            }
            int time = node.timestamp;
            if (timestamp > time) {
                node.right = add(node.right, timestamp, value);
            } else if (timestamp < time) {
                node.left = add(node.left, timestamp, value);
            } else {
                node.value = value;
            }

            if (isRed(node.right) && !isRed(node.left)) {
                node = rotateToLeft(node);
            }
            if (isRed(node.left) && isRed(node.left.left)) {
                node = rotateToRight(node);
            }
            if (isRed(node.left) && isRed(node.right)) {
                flipColors(node);
            }
            return node;
        }

        private boolean isRed(TreeNode node) {
            if (node == null) {
                return false;
            }
            return node.color;
        }

        private TreeNode rotateToRight(TreeNode node) {
            TreeNode r = node.left;
            node.left = r.right;
            r.right = node;
            r.color = node.color;
            node.color = true;
            return r;
        }

        private TreeNode rotateToLeft(TreeNode node) {
            TreeNode r = node.right;
            node.right = r.left;
            r.left = node;
            r.color = node.color;
            node.color = true;
            return r;
        }

        private void flipColors(TreeNode node) {
            node.left.color = false;
            node.right.color = false;
        }
    }

    class TreeNode {

        TreeNode left;
        TreeNode right;
        int timestamp;
        String value;
        boolean color = true;

        public TreeNode(int timestamp, String value) {
            this.timestamp = timestamp;
            this.value = value;
        }
    }
}