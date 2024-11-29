package name.ncg777.musical.rhythmRelations;

import java.util.BitSet;
import java.util.function.Predicate;

import name.ncg777.mathematics.relations.Relation;
import name.ncg777.musical.Rhythm;

public class PredicatedIntersectionAndXOR implements 
    Relation<Rhythm, Rhythm>   {
  
  public PredicatedIntersectionAndXOR(Predicate<Rhythm> pred){
    ld = pred;
  }
    private Predicate<Rhythm> ld;
  @Override
  public boolean apply(Rhythm a, Rhythm b) {
    BitSet o = new BitSet(a.getN());
    o.or(a);
    o.and(b);
    
    boolean r1 = ld.test(Rhythm.buildRhythm(o, a.getN()));
    o = new BitSet(a.getN());
    o.or(a);
    o.xor(b);
    boolean r2 = ld.test(Rhythm.buildRhythm(o, a.getN()));
    return r1 && r2;
  }
}