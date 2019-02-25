package exceptions;

/**
 * It is the exception throw when the inputs of the questions are wrong
 *
 * @author Fran Colmenar
 *
 */

public class WrongListOfQuestionsFormat extends Exception{

		private static final long serialVersionUID = 1L;
		private String message;

		public WrongListOfQuestionsFormat() {
			message = "Wrong format of questions/answers\n";
		}

		public String getMessage(){
			return this.message;
		}
}
