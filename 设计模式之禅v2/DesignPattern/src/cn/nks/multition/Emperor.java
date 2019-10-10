package cn.nks.multition;

import java.util.ArrayList;
import java.util.Random;

public class Emperor {

	private static int maxNumOfEmperor = 2;

	private static ArrayList<String> nameList = new ArrayList<>();

	private static ArrayList<Emperor> emperorList = new ArrayList<>();

	private static int countNumOfEmperor = 0;

	static {
		for (int i = 0; i < maxNumOfEmperor; i++) {
			emperorList.add(new Emperor("皇" + (i + 1) + "帝"));
		}
	}

	private Emperor() {

	}

	private Emperor(String name) {
		nameList.add(name);
	}

	public static Emperor getInstance() {
		Random random = new Random();
		countNumOfEmperor = random.nextInt(maxNumOfEmperor);
		return emperorList.get(countNumOfEmperor);
	}

	public static void say() {
		System.out.println(nameList.get(countNumOfEmperor));
	}

}
