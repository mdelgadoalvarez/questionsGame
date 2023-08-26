import java.util.*;
public class HuffmanCode {
   // huffman code fields
   private HuffmanNode overallRoot;

   // inner class
   private static class HuffmanNode implements Comparable<HuffmanNode> {
      public int frequency;
      public char letter;
      public HuffmanNode left;
      public HuffmanNode right;
      
      public HuffmanNode(int frequency, char data) {
         this(frequency, null, null);
         this.data = data;
      }
      
      public HuffmanNode(int frequency, HuffmanNode left, HuffmanNode right) {
         this.frequency = frequency;
         this.left = left;
         this.right = right;
      }
      
      public int compareTo(HuffmanNode other) {
         return this.frequency - other.frequency;
      }
   }
   
   public void HuffmanCode(int[] frequencies) {
      Queue<HuffmanNode> huffmanLetters =  PriorityQueue<>();
      for(int i = 0; i < frequencies.length; i++) {
        if (frequencies[i] > 0) {
            HuffmanNode node = new HuffmanNode(frequencies[i], (char) i);
            huffmanLetters.add(node);
        } 
      }
      while(huffmanLetters.size() > 1) {
         HuffmanNode node1 = huffmanLetters.remove();
         HuffmanNode node2 = huffmanLetters.remove();
         int add = node1.frequency + node2.frequency;
         HuffmanNode merge = new HuffmanNode(add, node1, node2);
         huffmanLetters.add(merge);
      }
      overallRoot = huffmanLetters.remove();
   }
   
   public HuffmanCode(Scanner input) {
   
   }
}