package Model;

import static org.junit.Assert.*;

import org.junit.Test;

public class QuestionTests {

	@Test
	public void checkConstructorStatement() {
		String question = "What day is today?";//Trivial variables
		String answer = "Monday";
		Question checkQuestion = new Question(question, answer);//I create the Question object
		assertEquals(question, checkQuestion.getQuestion());
	}

	@Test
	public void checkConstructorAnswer() {
		String question = "What day is today?";//Trivial variables
		String answer = "Monday";
		Question checkQuestion = new Question(question, answer);//I create the Question object
		assertEquals(answer, checkQuestion.getAnswer());
	}

	@Test
	public void checkSetQuestion() {
		String question = "What day is today?";//Trivial variables
		String answer = "Monday";
		Question checkQuestion = new Question(question, answer);//I create the Question object
		String question2 = "Not that";//new statement
		checkQuestion.setQuestion(question2);
		assertEquals(question2, checkQuestion.getQuestion());
	}

	@Test
	public void checkSetAnswer() {
		String question = "What day is today?";//Trivial variables
		String answer = "Monday";
		Question checkQuestion = new Question(question, answer);//I create the Question object
		String answer2 = "Thursday";//new statement
		checkQuestion.setAnswer(answer2);
		assertEquals(answer2, checkQuestion.getAnswer());
	}

}
