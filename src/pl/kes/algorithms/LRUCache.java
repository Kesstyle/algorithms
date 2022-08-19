package pl.kes.algorithms;

import java.util.HashMap;

public class LRUCache {

  public static void main(String...args) {
    LRUCache lRUCache = new LRUCache(2);
    lRUCache.put(1, 1); // cache is {1=1}
    lRUCache.put(2, 2); // cache is {1=1, 2=2}
    System.out.println(lRUCache.get(1));    // return 1
    lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
    System.out.println(lRUCache.get(2));    // returns -1 (not found)
    lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
    System.out.println(lRUCache.get(1));    // return -1 (not found)
    System.out.println(lRUCache.get(3));    // return 3
    System.out.println(lRUCache.get(4));    // return 4
  }

  private HashMap<Integer, ListBiNode> map = new HashMap<>();
  private ListBiNode oldest;
  private ListBiNode newest;
  private int capacity;

  public LRUCache(int capacity) {
    this.capacity = capacity;
  }

  public int get(int key) {
    ListBiNode node = map.get(key);
    if (node == null) {
      return -1;
    }
    processNewOrUpdatedNode(node, node.value);
    return node.value;
  }

  public void put(int key, int value) {
    ListBiNode existingValue = map.get(key);
    if (existingValue == null) {
      existingValue = new ListBiNode(key, value);
      map.put(key, existingValue);
    }
    processNewOrUpdatedNode(existingValue, value);
    if (map.size() > capacity) {
      evict();
    }
  }

  private void evict() {
    int key = oldest.key;
    removeOldNode();
    map.remove(key);
  }

  private ListBiNode processNewOrUpdatedNode(final ListBiNode updatedNode, int newValue) {
    if (newest == null) {
      newest = updatedNode;
      oldest = updatedNode;
      updatedNode.value = newValue;
      return oldest;
    }
    if (newest == updatedNode) {
      updatedNode.value = newValue;
      return oldest;
    }
    if (oldest == updatedNode) {
      oldest = oldest.next;
    }
    ListBiNode prev = updatedNode.prev;
    ListBiNode next = updatedNode.next;

    if (prev != null) {
      prev.next = updatedNode.next;
    }
    if (next != null) {
      next.prev = updatedNode.prev;
    }
    updatedNode.next = null;
    updatedNode.prev = newest;
    newest.next = updatedNode;
    newest = updatedNode;
    updatedNode.value = newValue;
    return oldest;
  }

  private void removeOldNode() {
    oldest = oldest.next;
    oldest.prev = null;
  }

  class ListBiNode {
    public ListBiNode next;
    public ListBiNode prev;
    public int key;
    public int value;

    public ListBiNode(int key, int value) {
      this.key = key;
      this.value = value;
    }
  }
}
