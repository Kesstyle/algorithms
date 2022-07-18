package pl.kes.algorithms;

public class CountAndSay {

  public static void main(String...args) {
    System.out.println(countAndSay(4));
  }

  public static String countAndSay(int n) {
    if (n == 1) {
      return "1";
    }

    String lastNumber = countAndSay(n - 1);

    String result = "";
    int count = 1;
    char lastDigit = lastNumber.charAt(0);
    for (int i = 1; i < lastNumber.length(); i++) {
      char nextDigit = lastNumber.charAt(i);
      if (lastDigit != 0 && lastDigit != nextDigit) {
        result = result + count + lastDigit;
        count = 1;
        lastDigit = nextDigit;
      } else {
        count++;
      }
    }
    if (count != 0) {
      result = result + count + lastDigit;
    }
    return result;
  }
}
