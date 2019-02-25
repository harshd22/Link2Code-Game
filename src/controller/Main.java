package controller;

import javax.swing.SwingUtilities;

import Model.Model;
import View.View;

public class Main {

	public static void main(String[] args) {
		 Model model = new Model();//I create the model
		 Controller controller = new Controller(model);//I create the controller
		 SwingUtilities.invokeLater(()->new View(model));	}
}