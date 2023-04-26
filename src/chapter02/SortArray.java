package chapter02;

import java.util.Random;

public class SortArray {

	public static void main(String[] args) {

		int[] data = new int[10];

		getData(data);

		showData(data);

		int value = findMax(data);

		System.out.println("Max value = " + value);

		sortData(data);

		System.out.println("정렬 후 결과");

		showData(data);

	}

	private static void sortData(int[] data) {

		for (int i = 0; i < data.length - 1; i++) {
			// System.out.println("i = " + i);
			for (int j = i + 1; j < data.length; j++) {
				// System.out.println("  j = " + j);
				if(data[i] > data[j]) {
					swap(data, i, j);
				}
			}
		}

	}

	private static int findMax(int[] data) {
		int max = Integer.MIN_VALUE;

		for (int i = 0; i < data.length; i++) {
			if (data[i] > max) {
				max = data[i];
			}
		}

		return max;
	}

	private static void showData(int[] data) {
		for (int i = 0; i < data.length; i++) {
			// System.out.println("data[" + i + "] = " + data[i]);
			System.out.print(data[i] + "  ");
		}
		System.out.print("\n");
	}

	private static void getData(int[] data) {

		Random r = new Random(System.currentTimeMillis());

		for (int i = 0; i < data.length; i++) {
			data[i] = r.nextInt(100);
		}
	}

	private static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;

	}
}