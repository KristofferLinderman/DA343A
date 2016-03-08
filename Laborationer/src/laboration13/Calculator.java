package laboration13;

import laboration12.Expression;

public class Calculator {
	public double calculate(double nbr1, double nbr2, char operation) {
		double res = Double.NaN;
		switch (operation) {
		case '+':
			res = nbr1 + nbr2;
			break;
		case '-':
			res = nbr1 - nbr2;
			break;
		case '*':
			res = nbr1 * nbr2;
			break;
		case '/':
			res = nbr1 / nbr2;
			break;
		}
		return res;
	}

	public double calculate(Expression expression) {
		double nbr1 = expression.getNbr1();
		double nbr2 = expression.getNbr2();
		char operation = expression.getOperation();

		return calculate(nbr1, nbr2, operation);
	}
}
