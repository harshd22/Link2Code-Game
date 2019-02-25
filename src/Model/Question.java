package Model;

/**
 * It is the object representing to the questions
 * Each question has its own statement and its answer
 *
 * @author Fran Colmenar
 *
 */

public class Question {

	private String question;
	private String answer;
	private boolean used;

	public Question(String question, String answer) {
		super();
		this.question = question;
		this.answer = answer;
		this.used = false;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}


	public boolean isUsed() {
		return used;
	}


	public void setUsed(boolean used) {
		this.used = used;
	}

}
