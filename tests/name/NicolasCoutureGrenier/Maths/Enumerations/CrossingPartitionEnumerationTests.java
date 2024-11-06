package name.NicolasCoutureGrenier.Maths.Enumerations;

import static org.junit.Assert.*;

import org.junit.Test;

import name.NicolasCoutureGrenier.Maths.Numbers;

public class CrossingPartitionEnumerationTests {

  @Test
  public final void testBellMinusCatalan() {
    for(int i=0;i<8;i++) {
      long cnt = 0;
      var en = new CrossingPartitionEnumeration(i);
      while(en.hasMoreElements()) {cnt++;en.nextElement();}
      assertEquals(Numbers.bell(i)-Numbers.catalan(i), cnt);
    }
  }

}