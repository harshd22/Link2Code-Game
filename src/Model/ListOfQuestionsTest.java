package Model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import exceptions.WrongListOfQuestionsFormat;

public class ListOfQuestionsTest {

				/*				checkInputs tests				*/

	/**
	 * It checks the correct behavior of the method checkInputs when the size
	 * of both arrays are different
	 * @throws WrongListOfQuestionsFormat
	 */
	@Test(expected = WrongListOfQuestionsFormat.class)
	public void checkInputsSize1() throws WrongListOfQuestionsFormat {
		ArrayList<String> questions = new ArrayList<String>();//I create the lists
		ArrayList<String> answers = new ArrayList<String>();
		for(int i = 0; i < 2; i++) {//I populate the lists
			questions.add("Question " + i);
			answers.add("Answer " + i);
		}
		questions.add("Question extra");//I add the extra element
		ListOfQuestions.checkInputs(questions, answers);
	}

	/**
	 * It checks the correct behavior of the method checkInputs when the size
	 * of both arrays are different
	 * @throws WrongListOfQuestionsFormat
	 */
	@Test(expected = WrongListOfQuestionsFormat.class)
	public void checkInputsSize2() throws WrongListOfQuestionsFormat {
		ArrayList<String> questions = new ArrayList<String>();//I create the lists
		ArrayList<String> answers = new ArrayList<String>();
		for(int i = 0; i < 2; i++) {//I populate the lists
			questions.add("Question " + i);
			answers.add("Answer " + i);
		}
		answers.add("Answer extra");//I add the extra element
		ListOfQuestions.checkInputs(questions, answers);
	}

	/**
	 * It checks the correct behavior of the method checkInputs when the size is 0
	 *
	 * @throws WrongListOfQuestionsFormat
	 */
	@Test(expected = WrongListOfQuestionsFormat.class)
	public void checkInputsSize3() throws WrongListOfQuestionsFormat {
		ArrayList<String> questions = new ArrayList<String>();//I create the lists
		ArrayList<String> answers = new ArrayList<String>();
		for(int i = 0; i < 2; i++) {//I populate the lists
			answers.add("Answer " + i);
		}
		ListOfQuestions.checkInputs(questions, answers);
	}

	/**
	 * It checks the correct behavior of the method checkInputs when the size is 0
	 *
	 * @throws WrongListOfQuestionsFormat
	 */
	@Test(expected = WrongListOfQuestionsFormat.class)
	public void checkInputsSize4() throws WrongListOfQuestionsFormat {
		ArrayList<String> questions = new ArrayList<String>();//I create the lists
		ArrayList<String> answers = new ArrayList<String>();
		ListOfQuestions.checkInputs(questions, answers);
	}

	/**
	 * It checks the correct behavior of the method checkInputs when the size is 0
	 *
	 * @throws WrongListOfQuestionsFormat
	 */
	@Test(expected = WrongListOfQuestionsFormat.class)
	public void checkInputsSize5() throws WrongListOfQuestionsFormat {
		ArrayList<String> questions = new ArrayList<String>();//I create the lists
		ArrayList<String> answers = new ArrayList<String>();
		questions.add("One");
		ListOfQuestions.checkInputs(questions, answers);
	}

	/**
	 * It checks the correct behavior of the method checkInputs when the lists are correct
	 *
	 * @throws WrongListOfQuestionsFormat
	 */
	@Test
	public void checkInputsCorrect() throws WrongListOfQuestionsFormat {
		ArrayList<String> questions = new ArrayList<String>();//I create the lists
		ArrayList<String> answers = new ArrayList<String>();
		for(int i = 0; i < 2; i++) {//I populate the lists
			questions.add("Question " + i);
			answers.add("Answer " + i);
		}
		assertTrue(ListOfQuestions.checkInputs(questions, answers));
	}

	/**
	 * It checks the correct behavior of the constructor with correct inputs
	 *
	 * @throws WrongListOfQuestionsFormat
	 */
	@Test
	public void checkConstructor1() throws WrongListOfQuestionsFormat {
		ArrayList<String> questions = new ArrayList<String>();//I create the lists
		ArrayList<String> answers = new ArrayList<String>();
		for(int i = 0; i < 2; i++) {//I populate the lists
			questions.add("Question " + i);
			answers.add("Answer " + i);
		}
		ListOfQuestions list = new ListOfQuestions(questions, answers);//I create the list
		assertTrue(2 == list.getList().size());
	}

	/**
	 * It checks the correct behavior of the constructor with wrong inputs
	 *
	 * @throws WrongListOfQuestionsFormat
	 */
	@Test(expected = WrongListOfQuestionsFormat.class)
	public void checkConstructor2() throws WrongListOfQuestionsFormat {
		ArrayList<String> questions = new ArrayList<String>();//I create the lists
		ArrayList<String> answers = new ArrayList<String>();
		ListOfQuestions.checkInputs(questions, answers);
	}

	/**
	 * It checks the correct behavior setList
	 *
	 * @throws WrongListOfQuestionsFormat
	 */
	@Test
	public void checkSetList() throws WrongListOfQuestionsFormat {
		ArrayList<String> questions = new ArrayList<String>();//I create the lists
		ArrayList<String> answers = new ArrayList<String>();
		for(int i = 0; i < 2; i++) {//I populate the lists
			questions.add("Question " + i);
			answers.add("Answer " + i);
		}
		ListOfQuestions originalList = new ListOfQuestions(questions, answers);//I create the list

		ArrayList<Question> newList = new ArrayList<Question>();

		for(int i = 0; i < 2; i++) {//I populate the new lists
			Question newQuestion = new Question("Question2 " + i, "Answer2 " + i);//I create the new Question object
			newList.add(newQuestion);//I add a new question to the new list
		}

		originalList.setList(newList);//I introduc the new list to the original object

		assertEquals(newList, originalList.getList());
	}
}
