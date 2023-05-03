package mission;

import java.math.BigDecimal;
import java.math.RoundingMode;

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
		int comp = this.name.compareTo(o.name);
		if (comp != 0) {
			return comp;
		} else {
			comp = this.height - o.height;
			if (comp != 0) {
				return comp;
			} else {
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

}

public class SortObjectArray {

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
				new PhyscData("최길동", 161, 0.5),
				new PhyscData("최길동", 159, 0.5),
				new PhyscData("최길동", 169, 0.5),
		};
		showData(data);

		System.out.println("정렬 후 데이터");
		sortData(data);
		showData(data);
	}

	private static void showData(PhyscData[] data) {
		for(PhyscData d : data) {
			System.out.println("- name = " + d.name + ", height = " + d.height + ", vision = " + d.vision);
		}
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
