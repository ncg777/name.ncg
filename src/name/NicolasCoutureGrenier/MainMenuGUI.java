package name.NicolasCoutureGrenier;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import name.NicolasCoutureGrenier.Music.Apps.*;
import name.NicolasCoutureGrenier.Music.Apps.ChordVisual.ChordSeqVisual;
import name.NicolasCoutureGrenier.Music.Apps.KernelEvaluator.Application;
import name.NicolasCoutureGrenier.Maths.Apps.*;

import java.awt.GridLayout;
import java.lang.reflect.Method;

public class MainMenuGUI {
  public static void main(String[] args) {
    // Create the main frame
    JFrame mainFrame = new JFrame("Main Menu");
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainFrame.setSize(800, 600); // Adjust size as needed

    // Create a panel to hold buttons
    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(0, 1)); // Dynamic vertical layout

    addAppButton(panel, "AddSequences", AddSequences.class);
    addAppButton(panel, "BitCounter", BitCounter.class);
    addAppButton(panel, "ChordGraphExplorer", ChordGraphExplorer.class);
    addAppButton(panel, "ChordID", ChordID.class);
    addAppButton(panel, "ChordMatrix", ChordMatrix.class);
    addAppButton(panel, "ChordNavigator", ChordNavigator.class);
    addAppButton(panel, "ChordPermutator", ChordPermutator.class);
    addAppButton(panel, "ChordRotator", ChordRotator.class);
    addAppButton(panel, "ChordSequenceMerger", ChordSequenceMerger.class);
    addAppButton(panel, "ChordSorter", ChordSorter.class);
    addAppButton(panel, "ChordSeqVisual", ChordSeqVisual.class);
    addAppButton(panel, "ChordWalker", ChordWalker.class);
    addAppButton(panel, "Decimator", Decimator.class);
    addAppButton(panel, "KComplexExplorer", KComplexExplorer.class);
    addAppButton(panel, "KernelEvaluator", Application.class);
    addAppButton(panel, "LatticePath", LatticePath.class);
    addAppButton(panel, "LyricalGuideMaker", LyricalGuideMaker.class);
    addAppButton(panel, "MeasureGateSequence", MeasureGateSequence.class);
    addAppButton(panel, "ModularArithmeticSequencer", ModularArithmeticSequencer.class);
    addAppButton(panel, "PCS12IntersectionAndUnion", PCS12IntersectionAndUnion.class);
    addAppButton(panel, "PseudoBase", PseudoBase.class);
    addAppButton(panel, "R16Divider", R16Divider.class);
    addAppButton(panel, "RandomWalker1", RandomWalker1.class);
    addAppButton(panel, "Range", Range.class);
    addAppButton(panel, "RexRandomizer", RexRandomizer.class);
    addAppButton(panel, "RhythmAndSequenceMerger", RhythmAndSequenceMerger.class);
    addAppButton(panel, "RhythmCalc", RhythmCalc.class);
    addAppButton(panel, "RhythmContours", RhythmContours.class);
    addAppButton(panel, "RhythmDiluter", RhythmDiluter.class);
    addAppButton(panel, "RhythmMatrix", RhythmMatrix.class);
    addAppButton(panel, "RhythmMatrixSeq", RhythmMatrixSeq.class);
    addAppButton(panel, "RhythmMerger", RhythmMerger.class);
    addAppButton(panel, "RhythmicPulsations", RhythmicPulsations.class);
    addAppButton(panel, "RhythmicSeqGen", RhythmicSeqGen.class);
    addAppButton(panel, "RnExpander", RnExpander.class);
    addAppButton(panel, "RnPartitioner", RnPartitioner.class);
    addAppButton(panel, "ScaleModulo", ScaleModulo.class);
    addAppButton(panel, "SeqGenBySegments", SeqGenBySegments.class);
    addAppButton(panel, "SeqGenContourFollow", SeqGenContourFollow.class);
    addAppButton(panel, "SeqGenFS", SeqGenFS.class);
    addAppButton(panel, "SeqGenFixedSum", SeqGenFixedSum.class);
    addAppButton(panel, "SequenceCalc", SequenceCalc.class);
    addAppButton(panel, "SequenceConvolver", SequenceConvolver.class);
    addAppButton(panel, "SequenceConvolverAbsoluteTime", SequenceConvolverAbsoluteTime.class);
    addAppButton(panel, "SequenceMap", SequenceMap.class);
    addAppButton(panel, "SequenceMerger", SequenceMerger.class);
    addAppButton(panel, "SequencePermutate", SequencePermutate.class);
    addAppButton(panel, "SequenceScaleModuloPermutation", SequenceScaleModuloPermutation.class);
    addAppButton(panel, "SequenceWrapper", SequenceWrapper.class);
    addAppButton(panel, "SmoothArticulator", SmoothArticulator.class);
    addAppButton(panel, "Tri", Tri.class);
    addAppButton(panel, "WordSequencer", WordSequencer.class);
    addAppButton(panel, "XORCircularConvolver", XORCircularConvolver.class);
    addAppButton(panel, "XRSequenceGenerator", XRSequenceGenerator.class);
    addAppButton(panel, "x2mid", x2mid.class);
    addAppButton(panel, "MixedRadix", MixedRadix.class);
    addAppButton(panel, "NecklaceGenerator", NecklaceGenerator.class);
    addAppButton(panel, "Permutator", Permutator.class);
    addAppButton(panel, "WordPermutator", WordPermutator.class);

    JScrollPane scrollPane = new JScrollPane(panel);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

    // Add the scroll pane to the main frame
    mainFrame.add(scrollPane);

    // Set frame visibility
    mainFrame.setVisible(true);
  }

  private static void addAppButton(JPanel panel, String appName, Class<?> appClass) {
    JButton button = new JButton(appName);
    button.addActionListener(e -> {
      System.out.println(appName + " button clicked.");
      openApp(appClass);
    });
    panel.add(button);
  }

  private static void openApp(Class<?> appClass) {
    try {
      var appClassName = appClass.getCanonicalName();
      // Print out for debugging
      System.out.println("Attempting to open: " + appClassName);

      // Create a ProcessBuilder instance to launch the Java application
      ProcessBuilder processBuilder =
          new ProcessBuilder("java", "-cp", System.getProperty("java.class.path"), appClassName);
      Process process = processBuilder.start();

      // Optionally, you can handle process output or wait for it to complete
      // Example: Reading Output/Errors
      // InputStream is = process.getInputStream();
      // InputStreamReader isr = new InputStreamReader(is);
      // BufferedReader br = new BufferedReader(isr);
      // String line;
      // while ((line = br.readLine()) != null) {
      // System.out.println(line);
      // }

    } catch (Exception ex) {
      System.err.println("Error opening application: " + ex.getMessage());
      ex.printStackTrace(); // Handle exceptions properly
    }
  }
}
