package name.ncg777.musical.pitchClassSet12Relations;

import name.ncg777.mathematics.objects.Sequence;
import name.ncg777.mathematics.relations.Relation;
import name.ncg777.musical.PitchClassSet12;

public class CloseIVs implements Relation<PitchClassSet12, PitchClassSet12> {

  @Override
  public boolean apply(PitchClassSet12 a, PitchClassSet12 b) {
    Sequence iva = a.getIntervalVector();
    Sequence ivb = b.getIntervalVector();
    
    int acc = 0;
    
    for(int i=0;i<iva.size();i++) {
      if(iva.get(i) != ivb.get(i)) acc++;
      if(acc > 2) return false;
    }
    return true;
  }

}
