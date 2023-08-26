// Michelle Delgado-Alvarez
// Class Project
// QuestionsGame
// The Questions Game is able to read off of certain files and create a game off of that and/or save
// a games questions and anwsers which can then be used later to play in other games. Also, it is
// able to ask questions to the user until anwsered correctly. If not, it will learn by asking the user
// what they were thinking about and storing information about that thing in order to then save it 
// and when asked again it will have that as an option. Assumes files being passed in is in standard
// "Q:" and "A:" format and it's case-sensitive. Also, stores the QuestionNode class.
import java.util.*;
import java.io.*;
public class QuestionsGame {
   private Scanner console;
   private QuestionNode overallRoot;
   
   // QuestionNode stores information about a node to a tree
   private static class QuestionNode {
         public String data; 
         public QuestionNode left; 
         public QuestionNode right; 
   
      // constructs a QuestionNode that stores infromation, but has no left or right branches
      // Parameters:
      //    data - information that will be stored into the node
      public QuestionNode(String data) {
         this(data, null, null);
      }
      
      // constructs a QuestionNode that will store data with the data being passed in, and have
      // a connection with a left and right branches/subtrees
      // Parameters:
      //    data - text that will be stored in the node
      //    left - branch with given connection
      //    right - branch with given connection
      public QuestionNode(String data, QuestionNode left, QuestionNode right) {
         this.data = data;
         this.left = left;
         this.right = right;
      }
   }
   
   // post: initializes the game and starts with computer
   public QuestionsGame() {
      console = new Scanner(System.in);
      overallRoot = new QuestionNode("computer");
   }
   
   // pre : assumes the file being passed in is legal and in standard format
   // post: replaces the current set of questions and anwsers using the information 
   //       found in the file being passed in.
   // Parameters:
   //    input - input that is linked from a file by the user
   public void read(Scanner input) {
      overallRoot = readHelper(input);
   }
   
   // post: reads from the Scanner that is being passed in, which is then able to create
   //       nodes off of the questions and answers from that input. At the end it builds a tree
   //       and returns the new tree.
   // Parameters:
   //    input - input that is linked from a file by the user
   private QuestionNode readHelper(Scanner input) {
      String identifier = input.nextLine();
      String answer = input.nextLine();
      QuestionNode newNode = new QuestionNode(answer);
      if(identifier.equalsIgnoreCase("Q:")) {
         newNode.left = readHelper(input);
         newNode.right = readHelper(input);
      } 
      return newNode;  
   }
   
   // post: stores the current questions from the game to the given output file, which can later 
   //       be used again in another game. The file will be written in standard "Q:" and "A:"
   //       format.
   // Parameters:
   //    output - output file that will store the information
   public void write(PrintStream output) {
      write(output, overallRoot);
   }
   
   // pre : if the root is null it won't be able to "write" anything
   // post: stores the current tree with the given questions and anwsers to the given 
   //       output file
   // Parameters:
   //    output - output file that will store the information
   //    root - keeps track of where we currently are in the tree
   private void write(PrintStream output, QuestionNode root) {
      if(root != null) {
         if(root.left == null && root.right == null) {
            output.println("A:");
            output.println(root.data);
         } else {
            output.println("Q:");
            output.println(root.data);
            write(output, root.left);
            write(output, root.right);
         }
      }
   }
   
   // post: uses the current set of questions to play a full game, and stops when
   //       it reaches an anwser. If the computer wins the game, it will print a comment saying so.
   //       If not, it will ask the user for the name of the object they were think of, a question
   //       that goes with that object, and whether or not their answer for that object is yes/no.
   // Parameters:
   //    overallRoot - the first question, and has connection to the following questions and 
   //                  answers.
   public void askQuestions() {
      overallRoot = askQuestions(overallRoot);
   }
   
   // post: uses the current tree to play a full game, and stops when it reaches an answer, i.e leaf node.
   //       If it does it will print out a comment saying so, else it will ask what object were
   //       they thinking about, a question that goes with that object, and whether or not their
   //       answer for that object is yes/no. It will then store this information and return that,
   //       which will then be saved in the tree. Otherwise, it will just return root.
   // Parameters: 
   //    root - keeps track of where we currently are in the tree
   private QuestionNode askQuestions(QuestionNode root) {
      if(root.left == null && root.right == null) {
         if(yesTo("Would your object happen to be " + root.data + "?")) {
               System.out.println("Great, I got it right!");
         } else { // we reached a leaf node, but the answer we had wasn't the one they had in mind
            System.out.print("What is the name of your object? ");
            QuestionNode thing = new QuestionNode(console.nextLine());
            System.out.println("Please give me a yes/no question that"); 
            System.out.println("distinguishes between your object");
            System.out.print("and mine--> ");
            QuestionNode question = new QuestionNode(console.nextLine()); // creating a new node with their yes/no question
            boolean answer = yesTo("And what is the answer for your object?");
            if(answer) { 
               question.left = thing; // since they said yes, their object goes on the left
               question.right = root;
            } else {
               question.left = root;
               question.right = thing; // since they said no, their object goes on the right
            }
            return question;
         }     
      } else {
         if(yesTo(root.data)) {
            root.left = askQuestions(root.left);
         } else {
            root.right = askQuestions(root.right);
         }
      } 
      return root;
   }

    // Do not modify this method in any way, Provided Method
    // post: asks the user a question, forcing an answer of "y" or "n";
    //       returns true if the answer was yes, returns false otherwise
   private boolean yesTo(String prompt) {
      System.out.print(prompt + " (y/n)? ");
      String response = console.nextLine().trim().toLowerCase();
      while (!response.equals("y") && !response.equals("n")) {
         System.out.println("Please answer y or n.");
         System.out.print(prompt + " (y/n)? ");
         response = console.nextLine().trim().toLowerCase();
      }
      return response.equals("y");
   }
}