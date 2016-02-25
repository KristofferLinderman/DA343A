package laboration7;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class ProductToFileWriter implements ProductListener {
	private BufferedWriter bw;
	private int rows = 0;

	public ProductToFileWriter() {
		try {
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("files/product.txt")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void result(String str) {
		if (rows++ < 10) {
			try {
				bw.write(str);
				bw.newLine();
				bw.flush();
			} catch (IOException e) {
				System.err.println(e);
			}
		}
	}
}
