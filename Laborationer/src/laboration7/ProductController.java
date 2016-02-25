package laboration7;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class ProductController {
	private ProductUI productUI = new ProductUI(this);
	private ProductGenerator productGenerator = new ProductGenerator(10000);

	public ProductController() {
		productGenerator.setListener(new ProductListener() {
			
			public void result(String str) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						productUI.append(str);
					}
				});
			}
		});
		productGenerator.setListener(new ProductToFileWriter());
		JFrame frame = new JFrame("Game results");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(productUI);
		frame.pack();
		frame.setVisible(true);
	}

	public void start() {
		int product;
		boolean ok = false;
		try {
			product = Integer.parseInt(productUI.getProduct());
			if (product >= 1 && product <= 10000) {
				ok = true;
				productGenerator.start(product);
			}
		} catch (NumberFormatException e) {
		}
		if (!ok) {
			System.out.println("Bad input: " + productUI.getProduct());
		}
	}

	public void stop() {
		productGenerator.stop();
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				ProductController controller = new ProductController();
			}
		});
	}
}
