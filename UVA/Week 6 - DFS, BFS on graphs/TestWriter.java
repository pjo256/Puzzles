import java.util.*;
import java.io.*;

public class TestWriter {

	public static void main(String[] args) throws Exception {
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("cycleE.txt")));

		writer.write("2500\n");

		for (int i = 1; i < 2499; i ++) {
			writer.write(i + " " + (i + 1) + "\n");
		}

		writer.close();
	}
}