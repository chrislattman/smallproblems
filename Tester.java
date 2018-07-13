import java.io.*;
import java.util.*;

public class Tester {
	public static void main(String[] args) {
		String line;
		ArrayList<String> entries = new ArrayList<String>();
		boolean worked = false;
		try {
			BufferedReader buffreader = new BufferedReader(new FileReader("input.txt"));
			while ((line = buffreader.readLine()) != null) {
				entries.add(line);
			}
			buffreader.close();
			worked = true;
		}
		catch (FileNotFoundException e) {
			System.out.println("file not found");
		}
		catch (IOException i) {
			i.printStackTrace();
		}
		if (worked) {
			Collections.sort(entries);
			Iterator<String> it = entries.iterator();
			HashMap<String, Integer> items = new HashMap<String, Integer>();
			String first = "";
			String second = "";
			while (it.hasNext()) {
				second = it.next();
				String[] break1 = first.split(",");
				String[] break2 = second.split(",");
				if (break1.length == 3 && break1[0].equals(break2[0]) && items.containsKey(break2[2])) {
					items.replace(break2[2], items.get(break2[2]) + Integer.parseInt(break2[1]));
				}
				else if (break1.length != 3 || break1[0].equals(break2[0])){
					items.put(break2[2], Integer.parseInt(break2[1]));
				}
				else {
					printValue(break1, items);
					items.clear();
					items.put(break2[2], Integer.parseInt(break2[1]));
				}
				first = second;
				second = "";
			}
			printValue(first.split(","), items);
		}
	}

	public static void printValue(String[] break1, HashMap<String, Integer> items) {
		System.out.print(break1[0] + ",");
		Collection<Integer> value = items.values();
		ArrayList<Integer> vals = new ArrayList<Integer>(value);
		int sum = 0;
		for (int j = 0; j < vals.size(); j++) {
			sum += vals.get(j);
		}
		System.out.printf("%d,%.2f,%d\n", sum, (double) sum / vals.size(), vals.size());
	}
}