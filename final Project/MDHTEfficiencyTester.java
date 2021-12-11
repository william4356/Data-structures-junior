import java.util.HashMap;
import java.util.*;
import java.io.*;
import java.io.FileWriter;
public class MDHTEfficiencyTester{
	public static void main(String[] args) throws IOException {
		FileWriter myWriter = new FileWriter("C:\\Users\\willi\\Documents\\testestest.csv");// change this to the file on your computer
		String csvADD = "";
		for (int amount = 1000; amount < 100000; amount += 10){
		long startCummings = System.currentTimeMillis();
		MDHT table = new MDHT();
		for (int i = 0;i<amount ;i++ ) {
			table.insert(i+"",i);		}

		long endCummings = System.currentTimeMillis();

		long totalTime = endCummings - startCummings;
		System.out.println(amount  + " took " + totalTime + " ms");


		long startMap = System.currentTimeMillis();
		HashMap<String, Integer> map = new HashMap<String, Integer>();

		for (int i = 0;i<amount ;i++ ) {
			map.put(i+"",i);
		}
		//System.out.println(map);
		long endMap = System.currentTimeMillis();


		Long totalTimeMap = endMap - startMap;
		csvADD+=("\n"+amount+","+totalTime+","+totalTimeMap);
		System.out.println("\n"+amount  + " took " + totalTime + " ms");
		System.out.println("----------");
	}
	myWriter.write(csvADD);
	myWriter.close();
	}
}