package Model;

import java.util.ArrayList;

import exceptions.WrongListOfQuestionsFormat;

/**
 * It is an object which represents all the questions that a map has
 * In order to be able of randomize the questions asked to the user we create the random variable
 * Moreover, for representing the questions we create a list of Question
 *
 * It receives the List of Strings with the questions and the list for the answers
 *
 * @author Fran Colmenar
 *
 */

public class ListOfQuestions {
	private ArrayList<Question> list = new ArrayList<Question>();


	/**
	 * Constructor which calls to the methods in charge of checking the inputs
	 * and filling the list
	 *
	 * @param questions: List with all the questions
	 * @param answers:List with all the answers
	 * @throws WrongListOfQuestionsFormat
	 */
	public ListOfQuestions (ArrayList<String> questions, ArrayList<String> answers) throws WrongListOfQuestionsFormat{
		if(checkInputs(questions, answers)) {//If the inputs are correct I fill the lists
			fillList(questions, answers);
		}
	}

	/**
	 * It checks the validity of the inputs
	 *
	 * @param questions: List with all the questions
	 * @param answers:List with all the answers
	 * @return true if the lists have the correct format and otherwise a exception is throw
	 */
	public static boolean checkInputs(ArrayList<String> questions, ArrayList<String> answers) throws WrongListOfQuestionsFormat{
		if(questions.size() != answers.size()) {//It checks for a correct number of questions and answers
			throw new WrongListOfQuestionsFormat();
		}
		if(questions.size() == 0) {//I check that the lists are not empty
			throw new WrongListOfQuestionsFormat();
		}
		return true;
	}

	/**
	 * It fills the list of the questions with the ones given through the constructor
	 * @param questions: List with all the questions
	 * @param answers:List with all the answers
	 */
	public void fillList(ArrayList<String> questions, ArrayList<String> answers) {
		for(int i = 0; i < questions.size(); i++) {//I go through all the elements of the Arrays
			Question newQuestion = new Question(questions.get(i), answers.get(i));//I create the new Question object
			this.list.add(newQuestion);//I add it to the list of questions
		}
	}

	/**
	 * It asks the questions to the user in order
	 * @return the first question not used if there is an, if not it returns null
	 */
	public Question getNextAnswer() {
		for(int i = 0; i < list.size(); i++) {
			if(!list.get(i).isUsed()) {//If we have not used this question we returned it
				list.get(i).setUsed(true);//I set the question as used
				return list.get(i);
			}
		}
		return null;
	}

	/**
	 * It asks any question of the list
	 */
	public Question getAnyAnswer() {
		int random = (int) (Math.random() * list.size());//I choose a random index
		System.out.println(random);
		return list.get(random);
	}

	/**
	 * It set all the questions of the list as unused
	 * @return
	 */
	public void usedToFalse() {
		for(int i = 0; i < list.size(); i++) {
			list.get(i).setUsed(false);
		}
	}


	public ArrayList<Question> getList() {
		return this.list;
	}

	public void setList(ArrayList<Question> list) {
		this.list = list;
	}

}