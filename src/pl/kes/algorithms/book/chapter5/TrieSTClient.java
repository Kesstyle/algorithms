package pl.kes.algorithms.book.chapter5;

public class TrieSTClient {

  public static void main(String... args) {
    TrieST<Integer> trieST = new TrieST<>();
    trieST.put("now", 1);
    trieST.put("is", 2);
    trieST.put("the", 3);
    trieST.put("time", 4);
    trieST.put("for", 5);
    trieST.put("all", 6);
    trieST.put("good", 7);
    trieST.put("people", 8);
    trieST.put("to", 9);
    trieST.put("come", 10);
    trieST.put("to", 11);
    trieST.put("the", 12);
    trieST.put("aid", 13);
    trieST.put("of", 14);

    System.out.println("Contains now: " + trieST.contains("now"));
    System.out.println("Contains noww: " + trieST.contains("noww"));
    System.out.println("Size is " + trieST.size());
    System.out.println("Value for peop: " + trieST.get("peop"));
    System.out.println("Value for people: " + trieST.get("people"));
    System.out.println("Longest prefix of trans: " + trieST.longestPrefixOf("trans"));
    System.out.println("Longest prefix of theme: " + trieST.longestPrefixOf("theme"));

    System.out.print("Keys with prefix t: ");
    trieST.keysWithPrefix("t").forEach(v -> System.out.print(v + " "));
    System.out.println();

    System.out.print("Keys with prefix co: ");
    trieST.keysWithPrefix("co").forEach(v -> System.out.print(v + " "));
    System.out.println();

    System.out.println("Floor of now: " + trieST.floor("now"));
    System.out.println("Floor of nows: " + trieST.floor("nows"));
    System.out.println("Floor of tu: " + trieST.floor("tu"));
    System.out.println("Floor of tn: " + trieST.floor("tn"));
    System.out.println("Floor of zzzzzzz: " + trieST.floor("zzzzzzz"));
    System.out.println("Floor of aly: " + trieST.floor("aly"));

    System.out.println("Ceil of now: " + trieST.ceil("now"));
    System.out.println("Ceil of nows: " + trieST.ceil("nows"));
    System.out.println("Ceil of tu: " + trieST.ceil("tu"));
    System.out.println("Ceil of tn: " + trieST.ceil("tn"));
    System.out.println("Ceil of thd: " + trieST.ceil("thd"));
    System.out.println("Ceil of aly: " + trieST.ceil("aly"));

    System.out.println("Rank of ha: " + trieST.rank("ha"));
    System.out.println("Rank of ai: " + trieST.rank("ai"));
    System.out.println("Rank of alm: " + trieST.rank("alm"));
    System.out.println("Rank of y: " + trieST.rank("y"));
    System.out.println("Rank of time: " + trieST.rank("time"));

    for (int i = 0; i <= trieST.size(); i++) {
      System.out.println("Selection for rank " + i + " is: " + trieST.select(i));
    }
  }
}
