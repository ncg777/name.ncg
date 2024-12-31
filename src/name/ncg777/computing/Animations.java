package name.ncg777.computing;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

import name.ncg777.maths.HadamardMatrix;
import name.ncg777.maths.MatrixOfDoubles;
import name.ncg777.maths.MatrixOfIntegers;

import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.distribution.UniformIntegerDistribution;

import org.opencv.core.Mat;

public class Animations {
  public static Enumeration<Mat> Animation20241225_1(int width, int height, double fps, double dur) {
    return new Enumeration<Mat>() {
      int upper = (int)(dur*fps);
      int k = 0;

      public boolean hasMoreElements() {
        return k<upper;
      }

      public Mat nextElement() {
        double normalized_time = ((double) k)/((double)upper);
        var img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        var g = img.createGraphics();
        g.setBackground(new Color(0,0,0,0));

        var list = new ArrayList<Double>();
        list.add(-1.0);
        list.add(1.0);

        for(int _i=0;_i<2;_i++) {
          final double i = list.get(_i);
          for(int _j=0;_j<2;_j++) {
            final double j = list.get(_j);

            GraphicsFunctions.drawParametric2D(
                g, 
                (t) -> 0.5+j*0.35*Math.cos(j*Math.PI*0.125+i*normalized_time*40*Math.PI)*(0.5-0.5*Math.cos((4.0-(Math.abs(2.0-4.0*t)*2.0+2.0*(2.0+(1.0-Math.abs(1.0-2.0*normalized_time)))))*Math.PI)), 
                (t) -> 0.5-j*0.35*Math.sin(               -i*normalized_time*40*Math.PI)*(0.5+0.5*Math.sin((2.0-(Math.abs(1.0-2.0*t)*2.0+2.0*(4.0+(2.0-Math.abs(2.0-4.0*normalized_time)))))*Math.PI)),
                0.0,
                1.0,
                (t) -> 0.0,
                (t) -> 0.0,
                (t) -> Double.valueOf(width),
                (t) -> Double.valueOf(height),
                (t) -> 0.1+0.1*Math.sin(42*Math.PI*t)*Math.sin(20*Math.PI*normalized_time),
                (t,u) -> new Color(
                    (int)Math.round(128.0-127.0*(j*Math.cos(Math.PI*(3.0*t-i*normalized_time)))), 
                    (int)Math.round(128.0+127.0*(j*Math.sin(Math.PI*(3.0*t+i*normalized_time)))), 
                    128,
                    (int)(255.0*Math.pow(1.0-u,3.0))
                    ),
                (t) -> 0.00075+0.0007*Math.sin(Math.PI*2.0*t + Math.PI*Math.sin(80.0*Math.PI*normalized_time))
                );
            g.rotate(40.0*(double)k*2*Math.PI/(double)upper, width/2.0, height/2.0);
          }
        }
        ++k;
        return GraphicsFunctions.BufferedImageToMat(img);
      }
    };
  }

  public static Enumeration<Mat> Animation20241225_2(int width, int height, double fps, double dur) {
    return new Enumeration<Mat>() {
      int upper = (int)(dur*fps);
      int k = 0;

      public boolean hasMoreElements() {
        return k<upper;
      }

      public Mat nextElement() {
        final double t = ((double) k)/((double)upper);
        var img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        var g = img.createGraphics();

        GraphicsFunctions.drawColorField2D(g, 
            (x,y) -> {
              var b = 5.0;
              var th = Math.atan2(x, y);
              var s = 0.5+(th/Math.PI)*0.5;
              var r1 = Math.sqrt((Math.pow(x,2.0)+Math.pow(y,2.0))/2.0);
              var r2 = (t - b*s);
              var p = 2.0*Math.sin(2.0*Math.PI*(1.0*r2+1.0*r1));
              return new Color(
                  (int)(128.0+Math.sin(p*8.0*Math.PI)*127.0),
                  (int)(128.0+Math.sin(Math.PI/4+p*4.0*Math.PI)*127.0),
                  (int)(128.0+Math.cos(p*2.0*Math.PI)*127.0),
                  (int)(128.0*(1.0+Math.tanh(20*(0.5-r1)))));
            }, 
            width, height);
        ++k;
        return GraphicsFunctions.BufferedImageToMat(img);
      }
    };
  }
  public static Enumeration<Mat> Animation20241225_3(int width, int height, double fps, double dur) {
    return new Enumeration<Mat>() {
      int upper = (int)(dur*fps);
      int k = 0;

      public boolean hasMoreElements() {
        return k<upper;
      }

      public Mat nextElement() {
        final double t = ((double) k)/((double)upper);
        var img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        var g = img.createGraphics();

        GraphicsFunctions.drawColorField2D(g, 
            (x,y) -> {
              var b =3.0;
              var th = Math.atan2(x, y);
              var s = (0.5+(th/Math.PI)*0.5);
              var r1 = Math.sqrt((Math.pow(x,2.0)+Math.pow(y,2.0))/2.0);
              var r1p = ((Math.cos(t*4.0*Math.PI)))*r1;
              var r2 = (t - b*s);
              var p = 4.0*Math.cos(2.0*Math.PI*(1.0*r2+2.0*r1p));
              return new Color(
                  (int)(128.0-Math.sin(p*2.0*Math.PI)*127.0),
                  (int)(128.0-Math.sin(Math.PI/4+p*4.0*Math.PI)*127.0),
                  (int)(128.0+Math.cos(p*8.0*Math.PI)*127.0),
                  (int)(127.0*(1.0+Math.tanh(25.0*(0.5-r1-0.025*(Math.sin(th*12.0 -t*Math.PI*6.0)))))));
            }, 
            width, height);
        ++k;
        return GraphicsFunctions.BufferedImageToMat(img);
      }
    };
  }

  public static Enumeration<Mat> Hadamard20241228_1(int n, int width, int height, double fps, double dur) {
    final var m = HadamardMatrix.getMatrix(n);
    return new Enumeration<Mat>() {
      int upper = (int)(dur*fps);
      int k = 0;

      public boolean hasMoreElements() {
        return k<upper;
      }

      public Mat nextElement() {
        final double t = (double) k/(double)upper;
        final Function<Double,Double> _f = (Double r) -> Math.pow(-1.0+2.0*(1.0-r)*r,3.0);
        var img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        var g = img.createGraphics();
        int dim = m.columnCount();
        g.rotate((Math.PI/4.0)-(Math.PI*2.0*t),width/2,height/2);
        GraphicsFunctions.drawColorField2D(g, 
            (_x,_y) -> {
              double th = Math.atan2(0.5+0.5*_x, 0.5+0.5*_y);
              Double r = Math.sqrt((Math.pow(_x, 2.0) + Math.pow(_y, 2.0))/2.0);

              Double x = r*Math.cos(th+Math.PI*Math.sin(2.0*Math.PI*t)*_f.apply(r));
              Double y = r*Math.sin(th+Math.PI*Math.sin(2.0*Math.PI*t)*_f.apply(r));
              int i = (int)Math.floor((0.5+x*0.5)*((double)(dim)));
              int j = (int)Math.floor((0.5+y*0.5)*((double)(dim)));
              double v = (i>=dim || j >= dim) ? 0.0 : m.get(i,j).doubleValue();
              var rfadestart = 0.675;
              var rfadeend = 0.7;
              var v2 = (v*0.5+0.5);
              return new Color(
                  (int)((((0.75-0.25*Math.cos(2.0*Math.PI*t))*v2)*255.0)),
                  (int)((((0.75+0.25*Math.cos(2.0*Math.PI*t))*v2)*255.0)),
                  (int)(0.0),
                  r > rfadeend ? 0 : (
                      r < rfadestart ? (int)(v2*255.0) : 
                        ((int)(255.0*
                            (1.0-(
                                (r-rfadestart)/(rfadeend-rfadestart)
                                ))))
                      ));
            }, 
            width, height);
        ++k;     
        return GraphicsFunctions.BufferedImageToMat(img);
      }
    };
  }

  public static Enumeration<Mat> MatrixOrb20241229_1(MatrixOfIntegers mat, int width, int height, double fps, double dur) {
    return MatrixOrb20241229_1(mat.toMatrixOfDoubles(), width, height, fps, dur);
  }

  public static Enumeration<Mat> MatrixOrb20241229_1(MatrixOfDoubles mat, int width, int height, double fps, double dur) {
    int m = mat.rowCount();
    int n = mat.columnCount();

    return new Enumeration<Mat>() {
      int upper = (int)(dur*fps);
      int k = 0;

      public boolean hasMoreElements() {
        return k<upper;
      }

      public Mat nextElement() {
        final double t = (double) k/(double)upper;
        final Function<Double,Double> _f =  (Double r) -> Math.pow(-1.0+2.0*(1.0-r)*r,3.0);
        var img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        var g = img.createGraphics();
        g.rotate((Math.PI/4.0)-(Math.PI*2.0*t),width/2,height/2);
        GraphicsFunctions.drawColorField2D(g, 
            (_x,_y) -> {
              double th = Math.atan2(0.5+0.5*_x, 0.5+0.5*_y);
              Double r = Math.sqrt((Math.pow(_x, 2.0) + Math.pow(_y, 2.0))/2.0);

              Double x = r*Math.cos(th+Math.PI*Math.sin(2.0*Math.PI*t)*_f.apply(r));
              Double y = r*Math.sin(th+Math.PI*Math.sin(2.0*Math.PI*t)*_f.apply(r));
              int i = (int)Math.floor((0.5+x*0.5)*((double)(m)));
              int j = (int)Math.floor((0.5+y*0.5)*((double)(n)));
              double v = (i>=m || j >= n) ? 0.0 : Math.sin(mat.get(i,j)*Math.PI);
              var rfadestart = 0.675;
              var rfadeend = 0.7;
              var v2 = (v*0.5+0.5);
              return new Color(
                  (int)((((0.75-0.25*Math.cos(2.0*Math.PI*t))*v2)*255.0)),
                  (int)((((0.75+0.25*Math.cos(2.0*Math.PI*t))*v2)*255.0)),
                  (int)(0.0),
                  r > rfadeend ? 0 : (
                      r < rfadestart ? (int)(v2*255.0) : 
                        ((int)(255.0*
                            (1.0-(
                                (r-rfadestart)/(rfadeend-rfadestart)
                                ))))
                      ));
            }, 
            width, height);
        ++k;     
        return GraphicsFunctions.BufferedImageToMat(img);
      }
    };
  }
  
  
  public static Enumeration<Mat> Droplets20241230_1(double freq, int width, int height, double fps, double dur) {
    var nd = new NormalDistribution(0.0, 0.25);
    var ud = new UniformIntegerDistribution(4, 16);
    double period = 1.0/freq;
    
    return new Enumeration<Mat>() {
      List<BiConsumer<Graphics2D, Double>> df = new ArrayList<BiConsumer<Graphics2D, Double>>();
      int upper = (int)(dur*fps);
      int k = 0;
      
      Runnable regen = () -> {
        df.clear();
        int n = ud.sample();
        for(int i=0;i<n;i++) {
          double x=0.0;
          double y=0.0;
          double r1=0.0;
          double r2=0.0;
          x = width*(0.5+0.5*nd.sample());
          if(x < width*0.1) x = width*0.1;
          if(x > width*0.9) x = width*0.9;
          y = height*(0.5+0.5*nd.sample());
          if(y < height*0.1) y = height*0.1;
          if(y > height*0.9) y = height*0.9;
          r1 = width*0.0625*(0.5+0.5*nd.sample());
          if(r1 < 0.025*width) r1 = 0.025*width;
          if(r1 > 0.25*width) r1 = 0.25*width;
          r2 = height*0.0625*(0.5+0.5*nd.sample());
          if(r2 < 0.025*height) r2 = 0.025*height;
          if(r2 > 0.25*height) r2 = 0.25*height;
          final double _x = x;
          final double _y = y;
          final double _r1 = r1;
          final double _r2 = r2;
          
          df.add((Graphics2D g, Double _a) -> {
            Color c = new Color((int)(_a*255.0),(int)(_a*255.0),(int)(_a*255.0),(int)(_a*255.0));
            g.setColor(c);
            g.setPaint(c);
            
            g.fill(new Ellipse2D.Double(_x, _y, _r1, _r2));
          });
        }
      };
      
      boolean init = true;
      
      public boolean hasMoreElements() {
        return k<upper;
      }
      double phase=0.0;
      public Mat nextElement() {
        var img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        var g = img.createGraphics();
        
        double t = ((double)k)/fps;
        double a = 0.4999*(1.0+Math.sin(-(Math.PI/2.0)+(freq*2.0*Math.PI*t)));
        Double _phase = Double.valueOf(t/period);
        _phase -= Math.floor(_phase);
        double phase_diff = _phase-phase;
        
        phase = _phase;
        if(init || phase_diff<0.0) {
          regen.run();
          init = false;
        }
        for(var f : df) {
          f.accept(g, a);
        }
        
        ++k;
        return GraphicsFunctions.BufferedImageToMat(img);
      }
    };
  }
  
}
