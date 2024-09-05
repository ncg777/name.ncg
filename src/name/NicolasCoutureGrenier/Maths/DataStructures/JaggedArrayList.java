package name.NicolasCoutureGrenier.Maths.DataStructures;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.io.FileNotFoundException;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonFactoryBuilder;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Joiner;

import name.NicolasCoutureGrenier.CS.Parsers;
import name.NicolasCoutureGrenier.CS.Printers;

public class JaggedArrayList<T extends Comparable<? super T>> extends ComparableList<JaggedArrayList<T>> {
  private static final long serialVersionUID = 1L;

  T content = null;
  JaggedArrayList<T> parent = null;

  public JaggedArrayList() {
    super();
  }
  public JaggedArrayList(T t) {
    super();
    content = t;
  }
  
  public boolean add(JaggedArrayList<T> t) {
    if(t == null) throw new RuntimeException("nulls not allowed.");
    if(t.parent != null) {
      t.parent = this;
    }
    
    return super.add(t);
  }

  public JaggedArrayList(T t, JaggedArrayList<T> parent) {
    this(t);
    if(parent == null) throw new RuntimeException("parent is null.");
    parent.add(this);
  }
  public Map<T,JaggedArrayList<T>> toMap(){
    HashMap<T, JaggedArrayList<T>> h = new HashMap<>();
    for(var i: this) h.put(i.content, i);
    h.put(this.content,this);
    return h;
  }
  public void setContent(T t) {
    content = t;
  }

  public T getContent() {
    return content;
  }
  public boolean isRoot() { return this.getRoot().equals(this); }
  public JaggedArrayList<T> getParent() {return this.parent;}
  public JaggedArrayList<T> getRoot(){ 
    JaggedArrayList<T> current = this;
    
    while(!current.isRoot()) { 
      current = current.getParent();
    }
    return current;
  }
  

  public void printToJSON(Function<T,String> printer, String path) throws FileNotFoundException {
    PrintWriter pw = new PrintWriter(path);
    printToJSON(printer,pw);
    pw.flush();
    pw.close();
  }
  public void printToJSON(Function<T,String> printer, Writer sw) {
    try {
      var gen = new JsonFactory(new JsonFactoryBuilder()).createGenerator(sw);
      toJSONArrayString(printer, this, gen);
      gen.flush();  
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  public String toJSONArrayString(Function<T,String> printer) {
    StringWriter sw = new StringWriter();
    this.printToJSON(Printers.nullDecorator(printer),sw);
    return sw.getBuffer().toString();
  }
  
  private static <T extends Comparable<? super T>> void toJSONArrayString(Function<T,String> printer, JaggedArrayList<T> root, JsonGenerator gen) {
    try {
      if(root.getDepth() == 0 && root.getContent()!=null) {
        gen.writeString(printer.apply(root.getContent()));
      } else if((root != null)) {
        gen.writeStartArray();
        for(int i=0;i<root.size();i++) {
          toJSONArrayString(printer, root.get(i), gen); 
        }
        gen.writeEndArray();
      }
    } catch(IOException e) {
      e.printStackTrace();
    }
  }
  private static <T extends Comparable<? super T>> Function<String,T> nullExceptionThrower(Function<String,T> parser) {
    return (s) -> {
      T o = parser.apply(s);
      if(o == null) throw new RuntimeException("nulls not allowerd");
      return o;
    };
  }
  public static <T extends Comparable<? super T>> JaggedArrayList<T> parseJSONArray(String str, Function<String,T> parser) {
    return JaggedArrayList.parseJSONArray(str,JaggedArrayList.nullExceptionThrower(Parsers.nullDecorator(Parsers.quoteRemoverDecorator(parser))),new JaggedArrayList<T>());
  }
  private static <T extends Comparable<? super T>> JaggedArrayList<T> parseJSONArray(String str, Function<String,T> parser, JaggedArrayList<T> root) {
    try {      
      var b = new JsonFactoryBuilder().build();
      var p = b.setCodec(new ObjectMapper()).createParser(str).readValueAsTree();
      if(p == null) return root;
      if(p.isArray()) {
        JaggedArrayList<T> arr = new JaggedArrayList<T>(null, root);
        for(int i=0;i<p.size();i++) {
          parseJSONArray(p.get(i).toString(), parser, arr);
        }
        return arr;
      } else {
        return new JaggedArrayList<T>(parser.apply(str),root);
      }  
    } catch (JsonParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return null;
    
  }
  
  @Override
  public String toString() {
    return this.toString((s) -> s.toString());
  }
  
  public String toString(Function<T,String> printer) {
    return this.toJSONArrayString(Printers.nullDecorator(printer)); 
  }
  public String toIndentedString(
      final int level,
      final String indentationStr,
      final String nodeSeparator,
      final String leftEnclose, 
      final String rightEnclose, 
      final Function<T,String> printer
      ) {
    String indent = "";
    for(int i=0;i<level;i++) indent += indentationStr;
    final String indent1 = indent;
    var subItems = this.stream().<String>map(
        (q) -> { 
          return  
            (q.toIndentedString(
                level+1,
                indentationStr,
                nodeSeparator, 
                leftEnclose, 
                rightEnclose,
                printer));
          }
        ).toList();
    
    StringBuilder b = new StringBuilder();
    
    b.append(indent1+leftEnclose+printer.apply(this.content)+rightEnclose);  
    
    
    if(subItems.size() > 0) {
      b.append(nodeSeparator+Joiner.on(nodeSeparator).join(subItems));  
    }
    
    return b.toString();
  }

  
  @SuppressWarnings({"unchecked"})
  private static <T extends Comparable<? super T>> JaggedArrayList<T> fromArray(Object object, JaggedArrayList<T> parent) {  
    if(object == null) return null;
    
    if(object.getClass().isArray()) {
      int l = Array.getLength(object);
      JaggedArrayList<T> t = new JaggedArrayList<T>(null, parent);  
      for(int i=0;i<l;i++) {
        var o = Array.get(object, i);
        if(o==null) {
          throw new RuntimeException("nulls not supported");
        } else {
          fromArray(o,t);
        }
      }
      return parent;
    } else {
      return new JaggedArrayList<T>((T)object,parent);
    }
  }
  public static <T extends Comparable<? super T>> JaggedArrayList<T> fromArray(Object object) {
    return fromArray(object,new JaggedArrayList<T>());
  }
  
  private Class<T> getContentClass() {
    @SuppressWarnings("unchecked")
    Function<T,Class<T>> f = (c) -> c == null ? null : (Class<T>) c.getClass();
    var o = search(f);
    return o == null ? getRoot().search(f) : o;
  }
 
  public <U> U search(Function<T,U> f) {
    U o = f.apply(this.content);
    if(o != null) {
      return o;
    } else {
      for(var n : this) {
        o = n.search(f);
        if(o!= null) return o;
      } 
    }
    return o;
  }
  
  public int getDepth() {
    if(this.size() == 0) return 0;
    
    int max = -1;
    for(JaggedArrayList<T> i : this) {
      if(i != null) {
        int x = i.getDepth();
        if(x > max) max = x;  
      }
    }
    return max+1;
  }
  
  /**
   * @return A jagged array representing this tree
   */
  @Override
  public Object[] toArray() {
    var o = toJaggedArray();
    return (Object[])o;
  }
  
  private Object toJaggedArray() {   
    if(this.getDepth() < 1) {
      if(this.size() == 0) {
        return this.getContent();
      }
      Class<T> c = this.getContentClass();
      var o = Array.newInstance(c, this.size());
      for(int i=0;i<this.size();i++) {
        Array.set(o, i, this.get(i) == null ? null : this.get(i).getContent());
      }
      return o;
    } else {
      Class<T> c = this.getContentClass();
      
      if(c==null) return null;
      List<Object> l = new ArrayList<Object>();
      for(int i=0;i<this.size();i++) {
        if(this.get(i) == null) { l.add(null);}
        else {
          var arr = this.get(i).toJaggedArray();
          l.add(arr);  
        }
      }
      Object o = Array.newInstance(Object.class, l.size());
      
      for(int i=0;i<l.size();i++) {
        Array.set(o,i,l.get(i));
      }
      
      return o;
    }
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + Objects.hash(content, parent);
    return result;
  }
  @SuppressWarnings("rawtypes")
  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (!super.equals(obj)) return false;
    if (getClass() != obj.getClass()) return false;
    JaggedArrayList other = (JaggedArrayList) obj;
    if(this.size()!=other.size()) return false;
    if(!this.content.equals(other.content)) return false;
    for(int i=0;i<this.size();i++) {
      if(!this.get(i).equals(other.get(i))) return false;
    }
    return true;
  }
}
