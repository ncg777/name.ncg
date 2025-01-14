package name.ncg777.maths.numbers.predicates;

import java.util.BitSet;
import java.util.TreeSet;

import javax.annotation.Nonnull;

import name.ncg777.computing.Functional.StandardAndGuavaPredicate;
import name.ncg777.maths.Combination;
import name.ncg777.maths.Numbers;
import name.ncg777.maths.numbers.BinaryNatural;

public class Ordinal implements StandardAndGuavaPredicate<BinaryNatural>  {
  private int n = -1;
  TreeSet<Combination> words = new TreeSet<Combination>();
  public Ordinal(int n) {
    if(n < 2) throw new RuntimeException("Ordinal::Invalid n");
    this.n=n;
    TreeSet<Integer> factors = Numbers.factors(n);
    // add the empty rhythm
    words.add(new Combination(new BitSet(n), this.n));
    for(int f : factors) {
      int k = n / f;
      
      for(int i=1; i<=k; i++) {
        BitSet b = new BitSet(n);
        BitSet brev = new BitSet(n);
        for(int j=0; j<i; j++) {
          b.set(j*f, true);
          brev.set(n-((j+1)*f), true);
        }
        
        words.add(new Combination(b, this.n));
        words.add(new Combination(brev, this.n));
      }
    }
  }
  
  @Override
  public boolean apply(@Nonnull BinaryNatural _input) {
    BinaryNatural input = _input.reverse();
    if(input.getN() % this.n != 0) throw new RuntimeException("Ordinal:: rhythm is incompatible with n");
    
    int k = input.getN()/this.n;
    BitSet b = new BitSet(this.n);
    for(int i=0; i<k; i++) {
      b.clear();
      for(int j=0;j<this.n;j++) {
        b.set(j, input.get((i*this.n)+j));
      }
      if(!this.words.contains(new Combination(b, this.n))) return false;
    }
    return true;
  }

}
