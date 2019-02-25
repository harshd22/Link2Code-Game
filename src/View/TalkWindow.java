package View;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Model.Model;
import Model.Question;
import controller.Controller;

/**
 * JDialog that takes in Model and Controller to display the quiz question asked by the NPC.
 * Does internal checking of the given answer so only passes rightAnswer() or closed() to control.
 *
 * @author Jeremy Logan
 * @review Fran Colmenar
 */
@SuppressWarnings("serial")
class TalkWindow extends JDialog implements ActionListener, PropertyChangeListener {

	private Model model;
	private Controller control;
    private String typedText = null;
    private JTextField textField;
    private Question question;
    //private String answer;
    //private String question;
    private JOptionPane optionPane;
    private String btnString1 = "Enter";
    private String btnString2 = "Cancel";


    /**
     * Creates the reusable dialog.
     */
    public TalkWindow(Frame aFrame, Model m, Controller c) {
        super(aFrame, true);
        this.model=m;
        this.control = c;
        question = model.getQuestion();
        setTitle("Quiz");

        textField = new JTextField(10);

        //Create an array of the text and components to be displayed.
        String msgString1 = question.getQuestion();
        Object[] array = {msgString1, textField};

        //Create an array specifying the number of dialog buttons
        //and their text.
        Object[] options = {btnString1, btnString2};

        //Create the JOptionPane.
        optionPane = new JOptionPane(array, JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION,
                null, options, options[0]);

        //Make this dialog display it.
        setContentPane(optionPane);

        //Handle window closing correctly.
        //setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        //Ensure the text field always gets the first focus.
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent ce) {
                textField.requestFocusInWindow();
            }
        });

        //Register an event handler that puts the text into the option pane.
        textField.addActionListener(this);

        //Register an event handler that reacts to option pane state changes.
        optionPane.addPropertyChangeListener(this);
        pack();
        setLocationRelativeTo(aFrame);
        setLocation(200,200);
    }

    /**
     * This method handles events for the text field.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        optionPane.setValue(btnString1);
    }

    /**
     * This method reacts to state changes in the option pane.
     * @review The methods notclosed and closed are created in order to
     * reduce the amount of code that the method has
     */
    @Override
    public void propertyChange(PropertyChangeEvent e) {
        String prop = e.getPropertyName();

        if (isVisible()
                && (e.getSource() == optionPane)
                && (JOptionPane.VALUE_PROPERTY.equals(prop)
                || JOptionPane.INPUT_VALUE_PROPERTY.equals(prop))) {
            Object value = optionPane.getValue();

            if (value == JOptionPane.UNINITIALIZED_VALUE) {
                //ignore reset
                return;
            }

            //Reset the JOptionPane's value.
            //If you don't do this, then if the user
            //presses the same button next time, no
            //property change event will be fired.
            optionPane.setValue(
                    JOptionPane.UNINITIALIZED_VALUE);

            if (btnString1.equals(value)) {
            	notClosed();
            } else { //user closed dialog or clicked cancel
            	closed();
            }
        }
    }

    /**
     * Actions when the dialog is not closed by the user
     *
     * @review Method created with the previously code of the propertyChange method
     * to reduce the complexity of the previously method
     */
	private void notClosed() {
    	 typedText = textField.getText();
         String ucText = typedText.toUpperCase();
         if (question.getAnswer().toUpperCase().equals(ucText)) {
             JOptionPane.showMessageDialog(this, "Correct answer given");
             sendEvent("Correct answer given");
             exit();
         } else {
             //text was invalid
             textField.selectAll();
             JOptionPane.showMessageDialog(this,
                     "Sorry, \"" + typedText + "\" "
                     + "isn't a valid response.\n"
                     + "Please enter a single letter.",
                     "Try again",
                     JOptionPane.ERROR_MESSAGE);
             typedText = null;
             textField.requestFocusInWindow();
         }
	}

	/**
	 * Actions when the dialog is not closed by the user
	 *
     * @review Method created with the previously code of the propertyChange method
     * to reduce the complexity of the previously method
 	 */
    private void closed() {
    	JOptionPane.showMessageDialog(this, "Okay,  "
                + "You don't have to answer right now");
        typedText = null;
        sendEvent("Close talk");
        exit();
	}

	/**
     * It sends to the controller a specific event
     *
     * @param string: the name of the event
     * @review Method created to make simpler the task of
     * sending an event to the controller
     */
    private void sendEvent(String string) {
    	Object aux = new Object();
		ActionEvent event = new ActionEvent(aux, 0, string);//I create the event
		control.actionPerformed(event);//I send the event to the controller
	}

	/**
     * This method clears the dialog and hides it.
     */
    public void exit() {
        dispose();
    }

}