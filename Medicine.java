import java.io.*;
import java.util.*;

public class Medicine {
	public static void main(String[] args) {
		try {
			BufferedReader read = new BufferedReader(new FileReader("meds.txt"));
			ArrayList<String> meds = new ArrayList<String>();
			String line;
			while ((line = read.readLine()) != null) {
				if (line.length() > 0) {
					meds.add(line);
				}
				else {
					line = read.readLine();
					break;
				}
			}
			Iterator<String> it = meds.iterator();
			String med;
			int count = 0;
			boolean match = false;
			while (it.hasNext()) {
				med = it.next();
				if (med.substring(0, 2).equals(line)) {
					System.out.println(med);
					count++;
					match = true;
				}
				else if (!med.substring(0, 2).equals(line) && count >= 2) {
					break;
				}
				else if (count == 1) {
					System.out.println(med);
					break;
				}
			}
			if (!match) {
				System.out.println("<NONE>");
			}
		}
		catch (FileNotFoundException f) {
			System.out.println("File not found");
		}
		catch (IOException i) {
			i.printStackTrace();
		}
	}
}