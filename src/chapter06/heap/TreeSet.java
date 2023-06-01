package chapter06.heap;

class Sets {
	public Sets(int sz)
	{
		n = sz;
		parent = new int[sz + 1]; // Don't want to use parent[0]
		for (int i = 0; i < n; i++) parent[i] = -1;  // 0 for Simple versions
	}

	public void display(){
		
		for (int i = 1; i <= n; i++) 
			System.out.print(" " +  i);
		System.out.println();
		for (int i = 1; i <= n; i++)
			System.out.print(" " + parent[i]);
		System.out.println();
	}

	public void SimpleUnion(int i, int j)
	// Replace the disjoint sets with roots i and j, i != j with their union
	{

		// j 의 부모가 이미 있으면 j 의 루트가 i 를 가리키도록
		if (parent[j] >= 0) {
			while (parent[j] > 0) 
				j = parent[j];

			parent[j] = i;
			return;
		}

		싸이클 해결이 안돼서 무한루프

		while (parent[i] > 0) 
			i = parent[i];	// 루트를 찾는것
		while (parent[j] > 0) 
			j = parent[j];

		// 루트가 같으면 싸이클
		if(i == j) {
			System.out.println("cycle");
			return;
		} 

		// WeightedUnion(i, j);
		System.out.println("i = " + i + " j = " + j);
		parent[j] = i;
	}

	public int SimpleFind(int i)
	// Find the root of the tree containing element i
	{
		while (parent[i] > 0)
			i = parent[i];
		return i;
	}

	public void WeightedUnion(int i, int j)
	// Union sets with roots i and j, i != j, using the weighting rule.
	// parent[i]=-count[i] and parent[j]=-count[j].
	{
		// 루트를 찾는 코드를 추가해야 한다.
		// 같은 루트를 합치는 것(싸이클)을 리젝트 해야 한다. 
		while (parent[i] > 0) 
			i = parent[i];	// 루트를 찾는것

		while (parent[j] > 0) 
			j = parent[j];

		// 루트가 같으면 싸이클
		if(i == j) {
			System.out.println("cycle");
			return;
		} 

		int temp = parent[i] + parent[j];	// 두 루트의 카운트를 합친 수
		if (parent[i] > parent[j]) { // i has fewer nodes // 마이너스 값이라 비교를 반대로
			parent[i] = j;	// i의 부모는 j이다.
			parent[j] = temp;
		} else { // j has fewer nodes
			parent[j] = i;
			parent[i] = temp;
		}
	}

	public int CollapsingFind(int i)
	// Find the root of the tree containing element i.
	// Use the collapsing rule to collapse all nodes from @i@ to the root
	{
		// 루트를 찾아서 루트의 자손들을 모두 루트를 바라보도록 한다
		int r;
		for (r = i; parent[r] > 0; r = parent[r])
			; // find root
		while (i != r) {
			int s = parent[i];
			parent[i] = r;
			i = s;
		}
		return r;
	}

	private int[] parent;
	private int n;
}

public class TreeSet {
	public static void main(String[] args) {

	Sets s1 = new Sets(20);
	s1.SimpleUnion(7,1);
	s1.SimpleUnion(2,3);
	s1.SimpleUnion(4,5);	// 5가 4를 가리키고 있다. 5의 부모는 4
	s1.SimpleUnion(6,7);
	s1.SimpleUnion(4,2);	// 2가 4를 가리키고 있다. 2의 부모는 4

	s1.SimpleUnion(5,7);	// 7이 이미 6을 가리키고 있다. 7은 변경 없이 6이 5를 가리키도록 해야 한다.
	s1.SimpleUnion(2,5);	// 2의 부모와 4의 부모가 같다. find(2) != find(5) 여야 한다. 같으면 싸이클. 넣지 않는다.
	s1.SimpleUnion(1,9);
	s1.display();
	int n1 = s1.SimpleFind(5);
	int n2 = s1.SimpleFind(7);
	System.out.println("5의 parent = " + n1 + "  7의 parent = " + n2);
	s1.WeightedUnion(1, 2);
	s1.WeightedUnion(3, 4);
	s1.WeightedUnion(5, 6);
	s1.WeightedUnion(7, 8);
	System.out.println("find 5: " + s1.CollapsingFind(5)+ "\n");

	s1.display();
	System.out.println ("find 5: " + s1.CollapsingFind(5) + "\n");
	System.out.println ("find 6: " + s1.CollapsingFind(6) + "\n");
	s1.WeightedUnion(1, 3);
	s1.WeightedUnion(5, 7);
	s1.display();
	System.out.println ("find 5: " + s1.CollapsingFind(5) + "\n");
	System.out.println ("find 6: " + s1.CollapsingFind(6) + "\n");
	System.out.println ("find 7: " + s1.CollapsingFind(7) + "\n");
	System.out.println ("find 8: " + s1.CollapsingFind(8) + "\n");
	s1.WeightedUnion(1, 5);
	s1.display();
	System.out.println ("find 1: " + s1.CollapsingFind(1) + "\n");
	System.out.println ("find 2: " + s1.CollapsingFind(2) + "\n");
	System.out.println ("find 3: " + s1.CollapsingFind(3) + "\n");
	System.out.println( "find 4: " + s1.CollapsingFind(4) + "\n");
	System.out.println ("find 5: " + s1.CollapsingFind(5) + "\n");
	System.out.println ("find 6: " + s1.CollapsingFind(6) + "\n");
	System.out.println ("find 7: " + s1.CollapsingFind(7) + "\n");
	System.out.println ("find 8: " + s1.CollapsingFind(8) + "\n");
	s1.display();

	// // weighted 설명
	// s1.SimpleUnion(0,1);
	// s1.SimpleUnion(1,2);
	// s1.SimpleUnion(2,3);
	// s1.SimpleUnion(3,4);
	// s1.SimpleUnion(4,5); 
	// // 0 <- 1 <- 2 <- 3 <- 4 <- 5
	// // 일자로 만들어진다. 트리 형태가 아님. 루트를 찾는데 오래걸린다.
	// // 루트만 상위로 두고 나머지는 모두 루트의 바로 아래 차일드로 만든다.

	// // parant[i] 가 -1 이면 루트였는데 이것을 -카운트로 바꾸는것.
	// // 두 루트를 합칠때 깊이가 낮은것을 깊은것에 붙이는것이 깊이가 증가하지 않는다.

	

}
}