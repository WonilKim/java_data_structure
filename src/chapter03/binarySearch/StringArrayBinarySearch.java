package chapter03.binarySearch;

//3장 - 1번 실습 과제 > 2번 실습: 스트링 객체의 정렬과 이진 탐색 > 3번 실습: 객체 정렬과 이진 탐색
//comparator 구현 실습
import java.util.Arrays;
import java.util.Random;

public class StringArrayBinarySearch {

	public static void main(String[] args) {
		String[] data = { "apple", "grape", "persimmon", "감", "배", "사과", "포도", "pear", "blueberry", "strawberry",
				"melon", "oriental_melon" };

		showData(data);
		sortData(data);
		showData(data);

		String key = "감";
		int result = linearSearch(data, key);
		System.out.println("\nlinearSearch(): result = " + result);

		key = "배";
		result = binarySearch(data, key);
		System.out.println("\nbinarySearch(): result = " + result);
		int idx = Arrays.binarySearch(data, key);
		System.out.println("\nArrays.binarySearch(): result = " + result);

	}

	private static int binarySearch(String[] data, String key) {

		int pl = 0;
		int n = data.length;
		int pr = n - 1;

		do {
			int pc = (pl + pr) / 2;
			if(data[pc] == key)
				return pc;
			else if(data[pc].compareTo(key) < 0)
				pl = pc + 1;
			else
				pr = pc - 1;

		} while(pl <= pr);

		return -1;
	}

	private static int linearSearch(String[] data, String key) {
		int i = 0;

		while (i < data.length) {
			if (data[i] == key) {
				return i;
			}
			i++;
		}
		return -1;

	}

	private static void sortData(String[] data) {

		for (int i = 0; i < data.length - 1; i++) {
			// System.out.println("i = " + i);
			for (int j = i + 1; j < data.length; j++) {
				// System.out.println(" j = " + j);
				if (data[i].compareTo(data[j]) > 0) {
					swap(data, i, j);
				}
			}
		}
		// compareTo() 참고 자료
		// https://mine-it-record.tistory.com/133

	}

	private static String findMax(String[] data) {
		// 키보드로 입력 할 수 있는 아스키 코드가 가장 작은 캐릭터 = '!'
		String max = "!!!!!!!!!!!!!!!!!!";

		for (int i = 0; i < data.length; i++) {
			if (data[i].compareTo(max) > 0) {
				max = data[i];
			}
		}

		return max;
	}

	private static void showData(String[] data) {
		for (int i = 0; i < data.length; i++) {
			// System.out.println("data[" + i + "] = " + data[i]);
			System.out.print(data[i] + "  ");
		}
		System.out.print("\n");
	}

	private static void getData(String[] data) {

		// 랜덤 알파벳 추출
		Random r = new Random(System.currentTimeMillis());

		String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

		for (int i = 0; i < data.length; i++) {
			data[i] = String.valueOf(alphabet.charAt(r.nextInt(alphabet.length())));
		}
	}

	private static void swap(String[] arr, int i, int j) {
		String temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;

	}

}
