# questionsGame

Description: This class project is similar to the 20 Questions game, in which the computer ask a series of yes/no questions in order to guess the object the user is thinking. In this class project, it uses a Binary Tree in which the nodes are created by the inner class, QuestionNode. In this tree, the leaves represent possible answers (guesses) that the computer might make. All the other nodes represent questions that the computer will ask to narrow the possibilities. The left branch represents the next questions the computer will ask if the answer to the current question is yes. The right branch represents the next questions the computer will if the answer to the current question is no. The game is played by starting at the root, asking questions and traveling down the tree depending on the user's response. Once a leaf node is reached, the computer will ask if that answer is accurate. If it's correct, it asks you if you want to play again. If not, it asks for the name of your object and to provide a question that distinguishes your object from the computer's and saves it to the file.

For the read and write methods, they interact with text files containing questions and answers from the 20 questions games in "Q:" and "A:" format. During the game, when the user inputs a new object because the computer didn't guess it correctly or it wants to play again using the previous tree it will use these methods.

Language: Java, Object-Oriented Programming

Integrated Development Environment (IDE): JGrasp

File I worked on:
      QuestionsGame.java

Provided Files:
      big-questions.txt
      HuffmanCode.java
      QuestionMain.class
      QuestionMain.java
      questions.txt
      QuestionsGame$QuestionNode.class
      QuestionsGame.class

Contributors: Michelle Delgado-Alvarez 
