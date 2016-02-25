package laboration7;

import java.util.LinkedList;
import java.util.Random;

public class ProductGenerator {
	private Multiplication thread;
	private int maxProduct;
	private LinkedList<ProductListener> listeners = new LinkedList<>();

	public ProductGenerator(int maxProduct) {
		this.maxProduct = Math.abs(maxProduct);
	}

	public void start(int product) {
		if (thread == null) {
			if (product < 0)
				product = (-product) % maxProduct;
			thread = new Multiplication(product);
			thread.start();
		}
	}

	public void stop() {
		if (thread != null) {
			thread.interrupt();
		}
	}

	public void setListener(ProductListener listener) {
		listeners.add(listener);
	}

	private class Multiplication extends Thread {
		private int product;

		public Multiplication(int product) {
			this.product = product;
		}

		public void run() {
			int factor1, factor2, res;
			Random rand = new Random();
			while (!Thread.interrupted()) {
				factor1 = rand.nextInt(product) + 1;
				factor2 = rand.nextInt(product) + 1;
				res = factor1 * factor2;

				if (res == product)
					for (ProductListener pl : listeners)
						pl.result(factor1 + "*" + factor2 + "=" + product);

			}
			thread = null;
		}
	}
}
