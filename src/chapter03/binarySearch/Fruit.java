package chapter03.binarySearch;

public class Fruit implements Comparable<Fruit> {

    String name;
    int price;
    String date;

    public Fruit(String name, int price, String date) {

        this.name = name;
        this.price = price;
        this.date = date;
    }

    public String getName() {
        return this.name;
    }

    public int getPrice() {
        return this.price;
    }

    public String getDate() {
        return this.date;
    }

    @Override
	public int compareTo(Fruit o) {
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
			// price 비교
			comp = this.price - o.price;
			if (comp != 0) {
				// price 의 차가 0이 아니면(price 가 같지 않으면) 차 리턴
				return comp;
			} else {
				// price 가 같으면 
				// date 의 비교값 리턴
				return this.date.compareTo(o.date);
			}
		}
	}

	@Override
	public boolean equals(Object obj) {
		Fruit o = (Fruit) obj;

		if (this.name.equals(o.name) && this.price == o.price && this.date == o.date)
			return true;
		else
			return false;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "- name = " + this.name + ", price = " + this.price + ", date = " + this.date;
	}

}
