package name.ncg777.Maths.Enumerations;

import java.util.BitSet;
import java.util.Enumeration;

import name.ncg777.Maths.Objects.Sequence;

public class BitSequenceEnumeration implements Enumeration<Sequence> {
  private BitSetEnumeration e;
  private int n = -1;
  public BitSequenceEnumeration(int n) {
    this.n = n;
    this.e = new BitSetEnumeration(n);
  }

  @Override
  public boolean hasMoreElements() {
    return e.hasMoreElements();
  }
  
  @Override
  public Sequence nextElement() {
    BitSet b = e.nextElement();
    Sequence s = new Sequence();
    for(int i=0;i<n; i++) {
      s.add(b.get(i) ? 1 : 0);
    }
    return s;
  }
}