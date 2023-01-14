//Dynamic program: you can add out take out questions/answers

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class Quiz implements ActionListener{

	//Array of questions
	String[] questions = {
		" What is the capital of Brazil?",
		" How many states are in Brazil",
		" How do you say \"Thank you\"?",
		" What language is spoken in Brazil?"
	};
	
	//2D Array to populate the answer options for each question
	String[][] options = {
		{"Sao Paulo","Rio de Janeiro","Brazilian","Brasilia"},
		{"19","23","27","30"},
		{"Obrigado","Grazie","tack","Gracias"},
		{"Spanish","Latin","Brazilian","Portuguese"}
	};
	
	// A char array to hold all the correct answers
	char[] answers = {
		'd',
		'c',
		'a',
		'd'
	};
	
	//Declaration of variables
	char guess;
	char answer;
	int index;
	int correct_guesses =0;
	int total_questions = questions.length;
	int result;
	int seconds=10;
	
	//Creating and initializing GUI components
		// frame variable will hold everything
	JFrame frame = new JFrame();
		// textfield variable will hold the current question number
	JTextField textfield = new JTextField();
		// textarea variable will hold the current question being asked
	JTextArea textarea = new JTextArea();
		//buttons (rectangulat shaped)
	JButton buttonA = new JButton();
	JButton buttonB = new JButton();
	JButton buttonC = new JButton();
	JButton buttonD = new JButton();
		//labels for answers
	JLabel answer_labelA = new JLabel();
	JLabel answer_labelB = new JLabel();
	JLabel answer_labelC = new JLabel();
	JLabel answer_labelD = new JLabel();
		//label for the timer: will display the word "timer"
	JLabel time_label = new JLabel();
		//label for the timer: it will function as a display for the countdown timer
	JLabel seconds_left = new JLabel();
		//this will display the correct answers out of the total questions
	JTextField number_right = new JTextField();
		//this will display the percentage of correct answers
	JTextField percentage = new JTextField();
	
	//TIMER named: "timer" of 10 seconds for each question
		//we want this actionPerfomed method to execute every one second.
	Timer timer = new Timer(1000, new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
		//after each second we will decrease the variable second(already set to 10) by one(like a timer counting down)
			seconds--;
			//we take the seconds_left set to display the count down (do not forget to convert into a String type) 
			seconds_left.setText(String.valueOf(seconds));
			/*it checks if the time runs out and call the displayAnswer method, displaying the answer and disabling
			the buttons*/
			if(seconds<=0) {
				displayAnswer();
		//***this timer will be added at the nextQuestion() method, at the end of if-else block
			}
		}
	});
	
	
	//Constructor
	public Quiz() {
		//Designing:
			
			//frame will close when pressed exit
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			//size of frame
		frame.setSize(1000,650);
		frame.getContentPane().setBackground(new Color(50,50,50));
			//do not use a layout manager, so let it 'null'
		frame.setLayout(null);
			//if you do not want users to resize the size of your frame
		frame.setResizable(false);
		
		//textfield that will hold the question number
			//setBounds determines the location it will be placed and its size (x,y,width,height)
		textfield.setBounds(0,0,1000,50);
			//background color (use RGB)
		textfield.setBackground(new Color(25,25,25));
			//foreground color
		textfield.setForeground(new Color(227,227,227));
			//font of question number
		textfield.setFont(new Font("arial",Font.PLAIN,25));
		textfield.setBorder(BorderFactory.createBevelBorder(1));
			//this will align the text to the center
		textfield.setHorizontalAlignment(JTextField.CENTER);
			//disable people from editing this 
		textfield.setEditable(false);
		
		//textarea will hold the Question being displayed
		textarea.setBounds(0,80,1000,50);
		textarea.setLineWrap(true);
		textarea.setWrapStyleWord(true);
		textarea.setBackground(new Color(25,25,25));
		textarea.setForeground(new Color(227,227,227));
		textarea.setFont(new Font("arial",Font.PLAIN,30));
		textarea.setBorder(BorderFactory.createBevelBorder(1));
		textarea.setEditable(false);
		
		//buttons (rectangular shaped with the letters inside )
			//buttonA
		buttonA.setBounds(15,200,80,80);
		buttonA.setFont(new Font("arial",Font.PLAIN,30));
			// when you click a button it stays highlighted, choosing false will prevent that
		buttonA.setFocusable(false);
			//adding an action listener to this button
		buttonA.addActionListener(this);
		buttonA.setText("a");
			//buttonB
		buttonB.setBounds(15,300,80,80);
		buttonB.setFont(new Font("arial",Font.PLAIN,30));
		buttonB.setFocusable(false);
		buttonB.addActionListener(this);
		buttonB.setText("b");
			//buttonC
		buttonC.setBounds(15,400,80,80);
		buttonC.setFont(new Font("arial",Font.PLAIN,30));
		buttonC.setFocusable(false);
		buttonC.addActionListener(this);
		buttonC.setText("c");
			//buttonD
		buttonD.setBounds(15,500,80,80);
		buttonD.setFont(new Font("arial",Font.PLAIN,30));
		buttonD.setFocusable(false);
		buttonD.addActionListener(this);
		buttonD.setText("d");
		
		//answer options formatted
			//optionA
		answer_labelA.setBounds(125,190,500,100);
		answer_labelA.setBackground(new Color(50,50,50));
		answer_labelA.setForeground(new Color(227,227,227));
		answer_labelA.setFont(new Font("arial",Font.PLAIN,28));
			//optionB
		answer_labelB.setBounds(125,290,500,100);
		answer_labelB.setBackground(new Color(50,50,50));
		answer_labelB.setForeground(new Color(227,227,227));
		answer_labelB.setFont(new Font("arial",Font.PLAIN,28));
			//optionC
		answer_labelC.setBounds(125,390,500,100);
		answer_labelC.setBackground(new Color(50,50,50));
		answer_labelC.setForeground(new Color(227,227,227));
		answer_labelC.setFont(new Font("arial",Font.PLAIN,28));
			//optionD
		answer_labelD.setBounds(125,490,500,100);
		answer_labelD.setBackground(new Color(50,50,50));
		answer_labelD.setForeground(new Color(227,227,227));
		answer_labelD.setFont(new Font("arial",Font.PLAIN,28));
		
		//number displaying the remaining time
		seconds_left.setBounds(925,550,60,60);
		seconds_left.setBackground(new Color(25,25,25));
		seconds_left.setForeground(new Color(227,227,227));
		seconds_left.setFont(new Font("arial",Font.BOLD,28));
		seconds_left.setBorder(BorderFactory.createBevelBorder(1));
		seconds_left.setOpaque(true);
		seconds_left.setHorizontalAlignment(JTextField.CENTER);
		seconds_left.setText(String.valueOf(seconds));
		
		//time label. display the word "timer" in red
		time_label.setBounds(925,510,60,60);
		time_label.setBackground(new Color(50,50,50));
		time_label.setForeground(new Color(255,0,0));
		time_label.setFont(new Font("arial",Font.BOLD,15));
		time_label.setHorizontalAlignment(JTextField.CENTER);
		time_label.setText("Timer");
		
		//display at the end how many correct answers out of the total (rectangle box and text)
		number_right.setBounds(400,225,200,80);
		number_right.setBackground(new Color(25,25,25));
		number_right.setForeground(new Color(227,227,227));
		number_right.setFont(new Font("arial",Font.PLAIN,29));
		number_right.setBorder(BorderFactory.createBevelBorder(1));
		number_right.setHorizontalAlignment(JTextField.CENTER);
		number_right.setEditable(false);
		
		//display score in percentage (rectangle box and text)
		percentage.setBounds(400,300,200,55);
		percentage.setBackground(new Color(25,25,25));
		percentage.setForeground(new Color(227,227,227));
		percentage.setFont(new Font("arial",Font.PLAIN,23));
		percentage.setBorder(BorderFactory.createBevelBorder(1));
		percentage.setHorizontalAlignment(JTextField.CENTER);
		percentage.setEditable(false);
		
		
		//Add to the frame
		frame.add(time_label);
		frame.add(seconds_left);
		frame.add(answer_labelA);
		frame.add(answer_labelB);
		frame.add(answer_labelC);
		frame.add(answer_labelD);
		frame.add(buttonA);
		frame.add(buttonB);
		frame.add(buttonC);
		frame.add(buttonD);
		frame.add(textarea);
		frame.add(textfield);
		frame.setVisible(true);
		
		//call the next question
		nextQuestion();
	}
	
	
	//Method to move to the next question
	public void nextQuestion() {
		
		//it will act as a counter to check which question the user is.
		//if the index is equal or greater than the number of question it will call the results() method
		if(index>=total_questions) {
			results();
		}
		// if not, it will continue displaying the questions from the array
		//we will display the Question#, the question text, and the answer options. 
		//The timer will also starts to count down
		else {
				//this will display the title "Question 1", "Question 2".... 
			textfield.setText("Question "+(index+1));
				//this will display the question being asked
			textarea.setText(questions[index]);
				//display the answers options  
					//(options[answer opt for question 1][first(a) answer of array])
					//(options[answer opt for question 1][second(b) answer of array])
					//(options[answer opt for question 1][third(c) answer of array])
					//(options[answer opt for question 1][fourth(d) answer of array])
			answer_labelA.setText(options[index][0]);
			answer_labelB.setText(options[index][1]);
			answer_labelC.setText(options[index][2]);
			answer_labelD.setText(options[index][3]);
			//timer of 10 seconds starts
			timer.start();
		}
	}

	
	//Method handles button click events
	//it will be triggered when an user clicks a button
	@Override
	public void actionPerformed(ActionEvent e) {
		
			//disable buttons
			buttonA.setEnabled(false);
			buttonB.setEnabled(false);
			buttonC.setEnabled(false);
			buttonD.setEnabled(false);
			
			//determining which button is being pressed and check to see if that's a matching answer
			//'e' is from ActionEvent parameter (above)
				//if someone clicks buttonA we will assign the value 'a' to the answer variable
			if(e.getSource()==buttonA) {
				answer= 'a';
				// then the variable answer is compared to the answer in the array. if correct, score adds 1
				if(answer == answers[index]) {
					correct_guesses++;
				}
			}
			if(e.getSource()==buttonB) {
				answer= 'b';
				if(answer == answers[index]) {
					correct_guesses++;
				}
			}
			if(e.getSource()==buttonC) {
				answer= 'c';
				if(answer == answers[index]) {
					correct_guesses++;
				}
			}
			if(e.getSource()==buttonD) {
				answer= 'd';
				if(answer == answers[index]) {
					correct_guesses++;
				}
			}
			
			//call display answer method
			displayAnswer();
	}

	
	//Method to display the correct answer
	public void displayAnswer() {
		
		//after answering the question the timer stops
		timer.stop();
		
		//disable buttons
		buttonA.setEnabled(false);
		buttonB.setEnabled(false);
		buttonC.setEnabled(false);
		buttonD.setEnabled(false);
		
		//after time is out or after choosing an option, the incorrect options will turn red
		if(answers[index] != 'a')
			answer_labelA.setForeground(new Color(255,0,0));
		if(answers[index] != 'b')
			answer_labelB.setForeground(new Color(255,0,0));
		if(answers[index] != 'c')
			answer_labelC.setForeground(new Color(255,0,0));
		if(answers[index] != 'd')
			answer_labelD.setForeground(new Color(255,0,0));
		
		//adding a delay of 2 seconds before turning the options in red to the original color 
			// timer name: 'pause'  how long? two thousand millisecond = 2 seconds
		Timer pause = new Timer(2000, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//turn the answer option back to the original color
				answer_labelA.setForeground(new Color(227,227,227));
				answer_labelB.setForeground(new Color(227,227,227));
				answer_labelC.setForeground(new Color(227,227,227));
				answer_labelD.setForeground(new Color(227,227,227));
				
				
				answer = ' ';
				//initialized seconds variable to 10
				seconds=10;
				//display the seconds left
				seconds_left.setText(String.valueOf(seconds));
				//re-enable the buttons 
				buttonA.setEnabled(true);
				buttonB.setEnabled(true);
				buttonC.setEnabled(true);
				buttonD.setEnabled(true);
				//increase index by one and move on to the next question
				index++;
				//call nextQuestion method
				nextQuestion();
			}
		});
		
		//start timer "pause" and repeats once, even after each 2 seconds because it is set to false  
		pause.setRepeats(false);
		pause.start();
	}
	
	
	//Method displaying the result
	public void results(){
		
		//disable buttons so user will not be able to click buttons after test has ended
		buttonA.setEnabled(false);
		buttonB.setEnabled(false);
		buttonC.setEnabled(false);
		buttonD.setEnabled(false);
		
		//calculation of the results to find percentage
		result = (int)((correct_guesses/(double)total_questions)*100);
		
		//change textfield to display the word "Results"
		textfield.setText("RESULTS");
		//textarea will be empty
		textarea.setText("");
		//answer options will be empty
		answer_labelA.setText("");
		answer_labelB.setText("");
		answer_labelC.setText("");
		answer_labelD.setText("");
		
		//displaying the results as "(# / #)" and in percentage %
		number_right.setText("You got ("+correct_guesses+"/"+total_questions+")");
		percentage.setText(result+"%");
		
		//adding result components to the frame
		frame.add(number_right);
		frame.add(percentage);
	}	
	
}
