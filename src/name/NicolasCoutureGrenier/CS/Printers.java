package name.NicolasCoutureGrenier.CS;

import java.util.Base64;
import java.util.function.Function;

import com.google.common.base.Joiner;

import java.util.List;

import name.NicolasCoutureGrenier.Maths.DataStructures.HeterogeneousPair;
import name.NicolasCoutureGrenier.Maths.DataStructures.HomogeneousPair;
import name.NicolasCoutureGrenier.Maths.DataStructures.Sequence;
import name.NicolasCoutureGrenier.Maths.DataStructures.JaggedArrayList;
import name.NicolasCoutureGrenier.Music.PCS12;

public class Printers {
  public static Function<String, String> stringPrinter = (s) -> s;
  public static Function<Integer, String> integerPrinter = (i) -> Integer.toString(i);
  public static Function<Double, String> doublePrinter = (d) -> Double.toString(d);
  public static Function<Object[],String> doubleArrayPrinter = arrayDecorator(doublePrinter);
  public static Function<Object[],String> integerArrayPrinter = arrayDecorator(integerPrinter);
  public static Function<Sequence, String> sequencePrinter = (s) -> s.toString();
  public static Function<PCS12, String> PCS12Printer = (pcs) -> pcs.toString();
  public static Function<HomogeneousPair<Integer>, String> intPairPrinter = (p) -> {
    Sequence ss = new Sequence();
    ss.add(p.getFirst());
    ss.add(p.getSecond());
    return ss.toString();
  };

  public static<T extends Comparable<? super T>> Function<Object[],String> arrayDecorator(Function<T,String> printer){
    return (arr) -> JaggedArrayList.<T>fromArray(arr).toJSONArrayString(printer);
  }
  public static <T> Function<List<T>,String> listPrinter(Function<T,String> printer) {
    return listPrinter(printer, " ");
  }
  public static <T> Function<List<T>,String> listPrinter(Function<T,String> printer, String separator) {
    return (l) -> Joiner.on(separator).join(l.stream().map(printer).toList());
  }
  public static <T extends Comparable<? super T>> Function<T,String> base64Decorator(Function<T,String> printer) {
    return (var x) -> new String(Base64.getEncoder().encode(printer.apply(x).getBytes()));
  }
  
  public static <T> Function<T,String> nullDecorator(Function<T,String> printer) {
    return (x) -> x == null ? "null" : printer.apply(x);
  }
  
  public static <T extends Comparable<? super T>> Function<JaggedArrayList<T>,String> treeNodeDecorator(Function<T,String> printer) {
    return (JaggedArrayList<T> x) -> {
      return x.toJSONArrayString(printer);
    };
  }
  public static <T extends Comparable<? super T>, U extends Comparable<? super U>> 
    Function<HeterogeneousPair<T,U>, String>
      heterogeneousPairDecorator(Function<T,String> printer1, Function<U,String> printer2) {
    return (p) -> p.toString(printer1, printer2);
  }
  public static <T extends Comparable<? super T>> 
  Function<HomogeneousPair<T>, String>
    homogeneousPairDecorator(Function<T,String> printer) {
  return (p) -> p.toString(printer, printer);
}
 }