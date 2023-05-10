package chapter03.binarySearch;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class Fruit implements Comparable<Fruit> {

    String name;
    int price;
    String expire;

    public Fruit(String name, int price, String date) {

        this.name = name;
        this.price = price;
        this.expire = date;
    }

    public String getName() {
        return this.name;
    }

    public int getPrice() {
        return this.price;
    }

    public String getExpire() {
        return this.expire;
    }

    @Override
	public int compareTo(Fruit o) {
		// 값들이 모두 같으면 0 리턴
		if(this.equals(o)) {
			return 0;
		}

		int comp = CC_NAME.compare(this, o);
		if (comp != 0) {
			// name 이 같지 않으면 비교값 리턴
			return comp;
		} else {
			// name 이 같으면 
			// price 비교
			comp = CC_PRICE.compare(this, o);
			if (comp != 0) {
				// price 의 차가 0이 아니면(price 가 같지 않으면) 차 리턴
				return comp;
			} else {
				// price 가 같으면 
				// date 의 비교값 리턴
				return CC_EXPIRE.compare(this, o);
			}
		}
	}

	@Override
	public boolean equals(Object obj) {
		Fruit o = (Fruit) obj;

		if (this.name.equals(o.name) && this.price == o.price && CC_EXPIRE.compare(this, o) == 0)
			return true;
		else
			return false;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "- name = " + this.name + ", price = " + this.price + ", date = " + this.expire;
	}

	public static final Comparator<Fruit> CC_NAME = new ComparatorFruitName();
	private static class ComparatorFruitName implements Comparator<Fruit> {// 익명클래스 사용

		@Override
		public int compare(Fruit f1, Fruit f2) {
			// TODO Auto-generated method stub
			int comp = f1.name.compareTo(f2.name);
			return comp;
		}// 익명클래스 사용
	};

	public static final Comparator<Fruit> CC_PRICE = new ComparatorFruitPrice();
	private static class ComparatorFruitPrice implements Comparator<Fruit> {// 익명클래스 사용

		@Override
		public int compare(Fruit f1, Fruit f2) {
			// TODO Auto-generated method stub
			int comp = f1.price - f2.price;
			return comp;
		}// 익명클래스 사용
	};

	public static final Comparator<Fruit> CC_EXPIRE = new ComparatorFruitExpire();
	private static class ComparatorFruitExpire implements Comparator<Fruit> {// 익명클래스 사용

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

}
