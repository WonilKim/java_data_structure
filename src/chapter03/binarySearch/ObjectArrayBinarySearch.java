package chapter03.binarySearch;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

//5번 실습 - 2장 실습 2-14를 수정하여 객체 배열의 정렬 구현
class PhyscData implements Comparable<PhyscData> {
	String name;
	int height;
	double vision;

	public PhyscData(String name, int height, double vision) {
		this.name = name;
		this.height = height;
		this.vision = vision;
	}

	@Override
	public int compareTo(PhyscData o) {
		// 값들이 모두 같으면 0 리턴
		if(this.equals(o)) {
			return 0;
		}

		int comp = this.name.compareTo(o.name);
		if (comp != 0) {
			// name 이 같지 않으면 비교값 리턴
			return comp;
		} else {
			// name 이 같으면 
			// height 비교
			comp = this.height - o.height;
			if (comp != 0) {
				// height 의 차가 0이 아니면(height 가 같지 않으면) 차 리턴
				return comp;
			} else {
				// height 가 같으면 
				// vision 의 차 리턴 (double 의 차를 올림 후 int 타입으로 변환)
				return (BigDecimal.valueOf(this.vision - o.vision)).setScale(0, RoundingMode.UP).intValue();
			}
		}
	}

	@Override
	public boolean equals(Object obj) {
		PhyscData o = (PhyscData) obj;

		if (this.name.equals(o.name) && this.height == o.height && this.vision == o.vision)
			return true;
		else
			return false;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		
		return "- name = " + this.name + ", height = " + this.height + ", vision = " + this.vision;
	}

}

public class ObjectArrayBinarySearch {

	public static void main(String[] args) {
		PhyscData[] data = {

				new PhyscData("홍길동", 162, 0.3),
				new PhyscData("홍동", 164, 1.3),
				new PhyscData("홍길", 152, 0.7),
				new PhyscData("김홍길동", 172, 0.3),
				new PhyscData("이길동", 182, 0.6),
				new PhyscData("박길동", 167, 0.2),
				new PhyscData("최길동", 169, 1.5),
				new PhyscData("최길동", 169, 0.5),
				new PhyscData("최길동", 167, 0.5),
				new PhyscData("최길동", 159, 0.5),
				new PhyscData("최길동", 169, 0.5),

		};
		showData(data);
		sortData(data);
		showData(data);
		PhyscData key = new PhyscData("박길동", 167, 0.2);
		int result = linearSearch(data, key);
		System.out.println("\nlinearSearch(): result = " + result);

		key = new PhyscData("최길동", 167, 0.5);
		result = binarySearch(data, key);
		System.out.println("\nbinarySearch(): result = " + result);

		int idx = Arrays.binarySearch(data, key);
		System.out.println("\nArrays.binarySearch(): result = " + result);
	}

	static void showData(PhyscData[] arr) {
		System.out.println();
		for (PhyscData data : arr) {
			System.out.println(data + " ");
		}
		System.out.println();
	}

	private static int binarySearch(PhyscData[] data, PhyscData key) {

		int pl = 0;
		int n = data.length;
		int pr = n - 1;

		do {
			int pc = (pl + pr) / 2;
			if(data[pc].equals(key))
				return pc;
			else if(data[pc].compareTo(key) < 0)
				pl = pc + 1;
			else
				pr = pc - 1;

		} while(pl <= pr);

		return -1;
	}

	private static int linearSearch(PhyscData[] data, PhyscData key) {
		int i = 0;

		while (i < data.length) {
			if (data[i].equals(key)) {
				return i;
			}
			i++;
		}
		return -1;

	}

	private static void sortData(PhyscData[] data) {

		for (int i = 0; i < data.length - 1; i++) {
			// System.out.println("i = " + i);
			for (int j = i + 1; j < data.length; j++) {
				// System.out.println(" j = " + j);
				if (data[i].compareTo(data[j]) > 0) {
					swap(data, i, j);
				}
			}
		}

	}

	private static void swap(PhyscData[] arr, int i, int j) {
		PhyscData temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;

	}

}
