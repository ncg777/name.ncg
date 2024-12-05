package name.ncg777.maths.words.relations;

import java.util.function.BiPredicate;
import java.util.function.Predicate;
import name.ncg777.maths.words.Alphabet;
import name.ncg777.maths.words.Tetragraph;
import name.ncg777.maths.words.Word;

public class PredicatedIntersection implements BiPredicate<Tetragraph, Tetragraph> {
  private Predicate<Word> predicate;
  
  public PredicatedIntersection(Alphabet alphabet, Predicate<Word> predicate){
    this.predicate = predicate;
  }
  
  @Override
  public boolean test(Tetragraph a, Tetragraph b) {
    return predicate.test(
        new Word(
              Alphabet.Binary,
              a.toBinaryWord().intersect(b.toBinaryWord())
            )
        );
  }
}