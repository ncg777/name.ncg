package name.ncg.Music.PCS12Predicates;

import name.ncg.Maths.DataStructures.Sequence;
import name.ncg.Music.PCS12;

import com.google.common.base.Predicate;

public class FifthMax implements Predicate<PCS12>   {

  @Override
  public boolean apply(PCS12 arg0) {
    Sequence s = arg0.getIntervalVector();
    
    return s.get(4).equals(s.getMax());
  }

}
