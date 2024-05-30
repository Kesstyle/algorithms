package pl.kes.algorithms;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TestJ {

  public static void main(String...args) {
    ZonedDateTime zonedDateTime = ZonedDateTime.now().withZoneSameInstant(ZoneId.of("US/Eastern"));
    System.out.println(zonedDateTime);
    ZonedDateTime w = zonedDateTime.withZoneSameInstant(ZoneId.of("Europe/Warsaw"));
    System.out.println(w);
    System.out.println(zonedDateTime.withZoneSameInstant(ZoneId.of("UTC")).getDayOfMonth() ==
        w.withZoneSameInstant(ZoneId.of("UTC")).getDayOfMonth());
    System.out.println(zonedDateTime.withZoneSameInstant(ZoneId.of("UTC")).getMonth() ==
        w.withZoneSameInstant(ZoneId.of("UTC")).getMonth());
    System.out.println(zonedDateTime.withZoneSameInstant(ZoneId.of("UTC")).getYear() ==
        w.withZoneSameInstant(ZoneId.of("UTC")).getYear());
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
    System.out.println(formatter.format(zonedDateTime));
    System.out.println(formatter.format(w));

    BigDecimal bigDecimal = new BigDecimal(5);
    System.out.println(bigDecimal.setScale(1));
  }

  private int gcd(int a, int b) {
    if (b == 0) {
      return a;
    }
    return gcd(b, a % b);
  }

}
