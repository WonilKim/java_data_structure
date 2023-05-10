package chapter03.binarySearch;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

//3장 객체 배열 정렬 - binary search
/*
* Comparator를 사용하는 방법
* MyComparator implements Comparator<>
* MyComparator myComparator = new MyComparator();
* Arrays.sort(array, myComparator);
* Collections.sort(list, myComparator);
*/

import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

public class FruitBinarySearch_Test {

	public static void main(String[] args) {
		Fruit[] arr = {
				new Fruit("사과", 200, "2023-5-8"),
				new Fruit("키위", 500, "2023-6-8"),
				new Fruit("오렌지", 200, "2023-7-8"),
				new Fruit("바나나", 50, "2023-5-18"),
				new Fruit("수박", 880, "2023-5-28"),
				new Fruit("체리", 10, "2023-9-8")
		};
		System.out.println("정렬전 객체 배열: ");
		showData(arr);
		Arrays.sort(arr, (a, b) -> a.getPrice() - b.getPrice()); // Fruit에 compareTo()가 있어도 람다식 우선 적용
		System.out.println("람다식 정렬(가격)후 객체 배열: ");
		showData(arr);

		Arrays.sort(arr, new Comparator<Fruit>() {
			@Override
			public int compare(Fruit a1, Fruit a2) {
				return a1.getName().compareTo(a2.getName());
			}
		});
		System.out.println("comparator 정렬(이름)후 객체 배열: ");
		showData(arr);

		Comparator<Fruit> cc_name = new Comparator<Fruit>() {

			@Override
			public int compare(Fruit f1, Fruit f2) {
				// TODO Auto-generated method stub
				int comp = f1.name.compareTo(f2.name);
				return comp;
			}// 익명클래스 사용

		};

		Comparator<Fruit> cc_price = new Comparator<Fruit>() {// 익명클래스 사용

			@Override
			public int compare(Fruit f1, Fruit f2) {
				// TODO Auto-generated method stub
				return f1.getPrice() - f2.getPrice();
			}// 익명클래스 사용
		};

		Comparator<Fruit> cc_expire = new Comparator<Fruit>() {// 익명클래스 사용

			@Override
			public int compare(Fruit f1, Fruit f2) {
				// TODO Auto-generated method stub
				try {
					Date date1 = new SimpleDateFormat("yyyy-M-dd").parse(f1.expire);
					Date date2 = new SimpleDateFormat("yyyy-M-dd").parse(f2.expire);
					int comp = date1.compareTo(date2);
					return comp;

				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return 0;
				}		
				
			}// 익명클래스 사용
		};

		// 이름 정렬 상태

		Fruit newFruit = new Fruit("체리", 500, "2023-5-18");

		int result3 = Arrays.binarySearch(arr, newFruit, cc_name);
		System.out.println("\nArrays.binarySearch() 조회결과 3::" + result3);

		//
		int result4 = binarySearch(arr, newFruit, cc_name);
		System.out.println("\nbinarySearch() 조회결과 4::" + result4);

		int result7 = binarySearch(arr, newFruit, Fruit.CC_NAME);
		System.out.println("\nbinarySearch() 조회결과 7::" + result7);

		// 가격 정렬
		sortData(arr, cc_price);

		System.out.println("comparator 정렬(가격)후 객체 배열 : ");
		showData(arr);

		int result5 = Arrays.binarySearch(arr, newFruit, cc_price);
		System.out.println("\nArrays.binarySearch() 조회결과 5::" + result5);

		int result6 = binarySearch(arr, newFruit, cc_price);
		System.out.println("\nbinarySearch() 조회결과 6::" + result6);

		int result8 = binarySearch(arr, newFruit, Fruit.CC_PRICE);
		System.out.println("\nbinarySearch() 조회결과 8::" + result8);

		// 유통기한 정렬
		sortData(arr, cc_expire);
		System.out.println("comparator 정렬(유통기한)후 객체 배열 : ");
		showData(arr);

		int result9 = binarySearch(arr, newFruit, Fruit.CC_EXPIRE);
		System.out.println("\nbinarySearch() 조회결과 9::" + result9);

		Fruit newFruit2 = new Fruit("망고", 350, "2023-06-08");

		int result10 = binarySearch(arr, newFruit2, Fruit.CC_EXPIRE);
		System.out.println("\nbinarySearch() 조회결과 10::" + result10);

	} // main

	private static void sortData(Fruit[] data, Comparator<Fruit> cc) {

		for (int i = 0; i < data.length - 1; i++) {
			// System.out.println("i = " + i);
			for (int j = i + 1; j < data.length; j++) {
				// System.out.println(" j = " + j);
				if (cc.compare(data[i], data[j]) > 0) {
					swap(data, i, j);
				}
			}
		}

	}

	private static void swap(Fruit[] arr, int i, int j) {
		Fruit temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;

	}

	private static int binarySearch(Fruit[] data, Fruit key, Comparator<Fruit> cc) {

		int pl = 0;
		int n = data.length;
		int pr = n - 1;

		do {
			int pc = (pl + pr) / 2;
			if (cc.compare(data[pc], key) == 0) // 지정한 변수만을 비교해야함.
				return pc;
			else if (cc.compare(data[pc], key) < 0)
				pl = pc + 1;
			else
				pr = pc - 1;

		} while (pl <= pr);

		return -1;
	}

	private static int linearSearch(Fruit[] data, Fruit key) {
		int i = 0;

		while (i < data.length) {
			if (data[i].equals(key)) {
				return i;
			}
			i++;
		}
		return -1;

	}

	private static void showData(Fruit[] arr) {
		for (Fruit f : arr) {
			System.out.println("name = " + f.name + ", price = " + f.price + ", date = " + f.expire);
		}
	}

}
