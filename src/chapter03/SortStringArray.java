package chapter03;

import java.util.Random;

public class SortStringArray {

	public static void main(String[] args) {

		String[] data = new String[10];

		getData(data);

		showData(data);

		String value = findMax(data);

		System.out.println("Max value = " + value);

		sortData(data);

		System.out.println("정렬 후 결과");

		showData(data);

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