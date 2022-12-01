package pl.kes.algorithms.book.chapter3;

import static pl.kes.algorithms.book.chapter3.Color.BLACK;
import static pl.kes.algorithms.book.chapter3.Color.RED;

public class RedBlackBST<Key extends Comparable<Key>, Value> {

  private TreeNode<Key, Value> root;

  public TreeNode<Key, Value> rotateLeft(final TreeNode<Key, Value> source) {
    TreeNode x = source.right;
    source.right = x.left;
    x.left = source;
    x.color = source.color;
    source.color = RED;
    return x;
  }

  public TreeNode<Key, Value> rotateRight(final TreeNode<Key, Value> source) {
    TreeNode<Key, Value> x = source.left;
    source.left = x.right;
    x.right = source;
    x.color = source.color;
    source.color = RED;
    return x;
  }

  public void put(final Key key, final Value value) {
    root = put(root, key, value);
    root.color = BLACK;
  }

  private TreeNode<Key, Value> put(TreeNode<Key, Value> node, final Key key, final Value value) {
    if (node == null) {
      return new TreeNode<>(key, value, RED, 1);
    }
    if (key.compareTo(node.key) > 0) {
      node.right = put(node.right, key, value);
    } else if (key.compareTo(node.key) < 0) {
      node.left = put(node.left, key, value);
    } else {
      node.value = value;
    }

    if (isRed(node.right) && !isRed(node.left)) {
      node = rotateLeft(node);
    }
    if (isRed(node.left) && isRed(node.left.left)) {
      node = rotateRight(node);
    }
    if (isRed(node.left) && isRed(node.right)) {
      flipColors(node);
    }
    node.N = 1 + size(node.left) + size(node.right);
    return node;
  }

  private void flipColors(final TreeNode node) {
    node.color = RED;
    node.left.color = BLACK;
    node.right.color = BLACK;
  }

  private boolean isRed(final TreeNode node) {
    if (node == null) {
      return false;
    }
    return node.color == RED;
  }

  private int size(final TreeNode node) {
    if (node == null) {
      return 0;
    }
    return 1 + size(node.left) + size(node.right);
  }
}
