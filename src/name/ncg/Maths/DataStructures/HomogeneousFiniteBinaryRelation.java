package name.ncg.Maths.DataStructures;

import java.io.IOException;
import java.util.TreeSet;
import java.util.function.Function;

import com.opencsv.exceptions.CsvException;

import name.ncg.Maths.Relations.Relation;

@SuppressWarnings("serial")
public class HomogeneousFiniteBinaryRelation<L extends Comparable<? super L>>
extends FiniteBinaryRelation<L, L> { 
  
  public HomogeneousFiniteBinaryRelation() {
    super();
  }
  public HomogeneousFiniteBinaryRelation(FiniteBinaryRelation<L,L> rel) {
    super(rel);
  }
  
  public HomogeneousFiniteBinaryRelation(Iterable<L> domain, Relation<L,L> rel) {
    super(domain, domain,rel);
  }
  
  @Override
  public TreeSet<L> domain(){
    TreeSet<L> o = new TreeSet<L>();
    o.addAll(super.domain());
    o.addAll(super.codomain());
    return o;
  }
  
  public TreeSet<L> codomain(){
    return this.domain();
  }
  
  public HomogeneousFiniteBinaryRelation<L> calcTransitiveClosure() {
    var o = new HomogeneousFiniteBinaryRelation<L>();
    boolean grew = false;
    
    do {
      grew = false;
      for(var p : this) {
        if(!o.contains(p)) {
          o.add(p);
          grew = true;
        }
        
        for(var q : this) {
          if(!o.contains(q)) {
            o.add(q);
            grew = true;
          }
          if(p.getSecond().equals(q.getFirst())) {
            var t = OrderedPair.makeOrderedPair(p.getFirst(), q.getSecond());
            if(!o.contains(t)) {
              o.add(t);
              grew = true;
            }
            
          }
        }
      }
    } while(grew);
    return o;
  }
  
  public void writeToCSV(Function<L,String> lToString, String path) throws IOException {
    super.writeToCSV(lToString, lToString, path);
  }
  public static <L extends Comparable<? super L>> 
    HomogeneousFiniteBinaryRelation<L> 
    readFromCSV (Function<String,L> lParser, String path) throws IOException, CsvException {
    return new HomogeneousFiniteBinaryRelation<L>(FiniteBinaryRelation.readFromCSV(lParser, lParser, path));
  }
}