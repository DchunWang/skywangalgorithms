package heaps;


/**
 * �����
 * 
 * ������Ƕ������ļ��ϣ�
 * 
 * ������
 * ��������һ�ֵݹ鶨��������������ĵݹ鶨��Ϊ��
 * 1��������B0ֻ��һ���ڵ㣻
 * 2��������Bk�����ö�����B(k-1)��ɵģ�����һ��������һ�������������ӣ�
 * 
 * ��B0ֻ��һ���ڵ㣻B1������B0��ɣ�B2������B1��ɣ�B3������B2��ɣ�B4������B3��ɣ�
 * ��������ͬ�Ķ����������һ����ʱ������һ��������һ�����������ӣ�
 * 
 * �����������ʣ�
 * 1��Bk���У�2^k)���ڵ㣻
 * 2��Bk�ĸ߶�Ϊk;
 * 3��Bk�����i��ǡ����C(k, i)���ڵ㣬����C(k, i)�ǽ׳˺�����
 * 4�����Ķ���Ϊk���������κ������ڵ�Ķ�����
 * 
 * �ڵ�Ķ������Ǹýڵ���ӵ�е���������Ŀ��
 * 
 * �������ָ�����������ʵĶ������ļ��ϣ�
 * 1��ÿ�ö�������������С�����ʡ������ڵ�ļ�ֵ <= ���ĺ��ӵļ�ֵ��
 * 2�����������û����ϵĶ�����������ͬ�Ķ�������������Ϊ0�ģ���Ҳ�����ж���k�Ķ�����ֻ��0����1����
 * 
 * 
 * �ϲ���������ѣ�
 * 1������������ѵĸ�����ϲ���һ�������ϲ�����������սڵ�Ķ��������������У�
 * 2�����������и��ڵ������ͬ�Ķ���������������ֱ�����и��ڵ�Ķ���������ͬ��
 * 
 * ��combine(h1,h2)�ж�h1��h2���кϲ�ʱ������ͨ��merge(h1, h2)��h1��h2�ĸ�����ϲ���һ��
 * �����ڵ�Ķ�������������������Ȼ�����whileѭ�����Ժϲ��õ�����������б�������������
 * �еġ����ڵ������ͬ�Ķ�����������������ֱ�����и��ڵ����������ͬΪֹ��
 * 
 * �ڽ��������С����ڵ������ͬ�Ķ���������������ʱ�����Խ��� ���ӵ��������Ϊ4�֣�
 * x�Ǹ�����ĵ�ǰ�ڵ㣬next_x��x����һ�����ֵܣ��ڵ㣺
 * Case 1��x->degree == next_x->degree
 * 				��������ǰ�ڵ�Ķ������롰��һ���ڵ�Ķ��������ʱ����ʱ������Ҫִ���κβ����������鿴����Ľڵ㣻
 * Case 2��x->degree == next_x->degree == next_x->next->degree
 * 				��������ǰ�ڵ�Ķ�����������һ�ڵ�Ķ������͡�����һ���ڵ�Ķ����������ʱ����ʱ����ʱ��ִ���κ�
 * 				�����������鿴����Ľڵ㣻ʵ���������ǽ�����һ���ڵ㡱�͡�����һ���ڵ㡱�ȵ��������������������ӣ�
 * Case 3��x->degree == next_x->degree != next_x->next->degree && x->key <= next_x->key
 * 				��������ǰ�ڵ�Ķ������롰��һ���ڵ�Ķ��� ����ȣ����ҡ���ǰ�ڵ�ļ�ֵkey" <= "��һ���ڵ�Ķ�������
 * 				��ʱ��������һ���ڵ㣨��Ӧ�Ķ�������"��Ϊ����ǰ�ڵ㣨��Ӧ�Ķ�������"�����ӣ�
 * Case 4��x->degree == next_x->degree != next_x->next->degree && x->key > next_x->key
 * 				��������ǰ�ڵ�Ķ������롰��һ���ڵ�Ķ�������ȣ����ҡ���ǰ�ڵ�ļ�ֵkey" > "��һ���ڵ�Ķ�������
 * 				��ʱ��������ǰ�ڵ㣨��Ӧ�Ķ�����������Ϊ����һ���ڵ㣨��Ӧ�Ķ��������������ӣ�
 * 
 * 
 * ������������Խ�������������ǽ���Ҫ����Ľڵ㡱�͵�ǰ���еĶѽ��кϲ���
 * 
 * ɾ��������
 * ɾ��������е�ĳ���ڵ㣬��Ҫ�Ĳ��裺
 * 1�������ýڵ㡱�������������ڵĶ��������ĸ��ڵ�λ�ã������ǣ��ӡ��ýڵ㡱��ʼ��������
 * 		�������������򣩱��������Ͻ������ڵ���ӽڵ�����ݣ�ֱ��Ҫ��ɾ���ļ�ֵ����������λ�ã�
 * 2�������ýڵ����ڵĶ��������Ӷ�������Ƴ������ö���Ѽ�Ϊheap;
 * 3�������ýڵ����ڵĶ����������з�ת�����ﷴת����˼�ǣ����������к��Ӷ�������������Щ
 * 		�������ϳɶ���ѣ����ö���Ѽ�Ϊchild��
 * 4����child��heap���кϲ�����
 * 
 * ɾ�����ܵ�˼�룺���ǽ���ɾ�ڵ�������ڵĶ������й���������Ȼ���ٶԶ�����������Ӧ�Ĵ���
 * 
 * ���²��������¶�����е�ĳ���ڵ㣬�����޸ĸýڵ��ֵ�����޸�֮������ƻ�������ж�����Ҫ����С�ѵ����ʣ�
 * ������Ҫ������Ӧ�ĵ�����
 * 1�����Ǽ��ٽڵ��ֵ���ýڵ�һ��λ��ĳһ�ö������У����� ��������ĳ���ڵ��ֵ�󣬾���Ҫ��֤
 * 		�ö�������Ȼ��һ����С�ѣ���ˣ�����Ҫ���ǲ��ϵĽ��ýڵ��ϵ���
 * 2���������ӽڵ��ֵ�����ǽ�����ֵ�Ľڵ㲻�ϵ��µ����Ӷ���֤������ֵ�Ľڵ����ڵĶ���������С�����ʣ�
 * 
 * ���µ��ܵ�˼�룺���Ǳ��ֱ����½ڵ����ڶ���������С������
 * 
 * 
 * 
 * @author Stargazer
 * @date 2017-03-24
 */
public class BinomialHeap<T extends Comparable<T>> {
	
	private BinomialNode<T> mRoot;			//���ڵ�
	
	private class BinomialNode<T extends Comparable<T>>{
		T key;									//��ֵ
		int degree;							//����
		BinomialNode<T> child;		//����
		BinomialNode<T> parent;	//���ڵ�
		BinomialNode<T> next;		//�ֵܽڵ㣬���������е���һ�ڵ㣿��
		
		public BinomialNode(T key){
			this.key = key;
			this.degree = 0;
			this.child = null;
			this.parent = parent;
			this.next = next;
		}
		
		public String toString(){
			return "key : " + key;
		}
	}
	
	public BinomialHeap(){
		mRoot = null;
	}
	
	/**
	 * ��ȡ������е���С�ڵ�ļ�ֵ
	 */
	public T minimum(){
		if(mRoot == null)
			return null;
		
		BinomialNode<T> x, prev_x;			//x�����������ĵ�ǰ�ڵ�
		BinomialNode<T> y, prev_y;				//y����С�ڵ�
		
		prev_x = mRoot;
		x = mRoot.next;
		prev_y = null;
		y = mRoot;
		
		//�ҵ���С�ڵ�
		while(x != null){
			if(x.key.compareTo(y.key)< 0){
				y = x;
				prev_y = prev_x;
			}
			prev_x = x;
			x = x.next;
		}
		
		return y.key;
	}
	
	/**
	 * �ϲ���������ѣ���child�ϲ���root��
	 */
	private void link(BinomialNode<T> child, BinomialNode<T> root){
		child.parent = root;
		child.next = root.child;
		root.child = child;
		root.degree++;
	}
	
	/**
	 * ��h1,h2�еĸ�����ϲ���һ���������������������غϲ���ĸ��ڵ�
	 */
	private BinomialNode<T> merge(BinomialNode<T> h1, BinomialNode<T> h2){
		if(h1 == null)
			return h2;
		if(h2 == null)
			return h1;
		
		//root���¶ѵĸ���h3��������h1��h2��
		BinomialNode<T> pre_h3, h3, root=null;
		
		pre_h3 = null;
		
		//����whileѭ����,h1,h2,pre_h3,h3��������˳��
		while((h1 != null) && (h2 != null)){
			
			if(h1.degree <= h2.degree){
				h3 = h1;
				h1 = h1.next;
			}else{
				h3 = h2;
				h2 = h2.next;
			}
			
			if(pre_h3 == null){
				pre_h3 = h3;
				root = h3;
			}else{
				pre_h3.next = h3;
				pre_h3 = h3;
			}
			
			if(h1 != null){
				h3.next = h1;
			}else{
				h3.next = h2;
			}
		}
		return root;
	}
	
	/**
	 * �ϲ�����ѣ���h1��h2�ϲ���һ���ѣ������غϲ���Ķ�
	 */
	private BinomialNode<T> union(BinomialNode<T> h1, BinomialNode<T> h2){
		BinomialNode<T> root;
		
		//��h1,h2�еĸ�����ϲ���һ������������������root
		root = merge(h1, h2);
		if(root == null)
			return null;
		
		BinomialNode<T> prev_x = null;
		BinomialNode<T> x = root;
		BinomialNode<T> next_x = x.next;
		
		while(next_x != null){
			if((x.degree != next_x.degree) || (next_x.next != null) && (next_x.degree == next_x.next.degree)){
				//Case 1 : x.degree != next_x.degree
				//Case 2 : x.degree == next_x.degree == next_x.next.degree
				prev_x = x;
				x = next_x;
			}else if(x.key.compareTo(next_x.key) <= 0){
				//Case 3 : x.degree == next_x.degree != next_x.next_degree && x.key <= next_x.key
				x.next = next_x.next;
				link(next_x, x);
			}else{
				//Case 4 : x.degree == next_x.degree != next_x.next.degree && x.key > next_x.key
				if(prev_x == null){
					root = next_x;
				}else{
					prev_x.next = next_x;
				}
				link(x, next_x);
				x = next_x;
			}
			
			next_x = x.next;
		}
		
		return root;
		
	}
	
	/**
	 * �������other�ϲ�����ǰ����
	 */
	public void union(BinomialHeap<T> other){
		if(other != null && other.mRoot != null){
			mRoot = union(mRoot, other.mRoot);
		}
	}
	
	/**
	 * �½���ֵΪkey�Ľڵ㣬��������뵽�������
	 */
	public void insert(T key){
		BinomialNode<T> node;
		
		//��ֹ������ͬ�ļ�ֵ
		if(contains(key) == true){
			System.out.println("insert failed: the key is existed already!");
			
			return;
		}
		
		node = new BinomialNode<T>(key);
		if(node == null)
			return ;
		
		mRoot = union(mRoot, node);
	}
	
	/**
	 * ��ת�����root,�����ط�ת��ĸ��ڵ�
	 */
	private BinomialNode<T> reverse(BinomialNode<T> root){
		BinomialNode<T> next;
		BinomialNode<T> tail = null;
		
		if(root == null){
			return root;
		}
		
		root.parent = null;
		
		while(root.next != null){
			next = root.next;
			root.next = tail;
			tail = root;
			root = next;
			root.parent = null;
		}
		
		root.next = tail;
		
		return root;
	}
	
	/**
	 * �Ƴ������root�е���С�ڵ㣬������ɾ���ڵ��Ķ�����
	 */
	private BinomialNode<T> extractMinimum(BinomialNode<T> root){
		if(root == null)
			return root;
		
		BinomialNode<T> x, prev_x;			//x�����������ĵ�ǰ�ڵ�
		BinomialNode<T> y, prev_y;			//y����С�ڵ�
		
		prev_x = root;
		x = root.next;
		prev_y = null;
		y = root;
		
		//�ҵ���С�ڵ�
		while(x != null){
			if(x.key.compareTo(y.key) < 0){
				y = x;
				prev_y = prev_x;
			}
			prev_x = x;
			x = x.next;
		}
		
		if(prev_y == null)
			root = root.next;			//root�ĸ��ڵ������С���ڵ�
		else
			prev_y.next = y.next;
		
		//��ת��С�ڵ�����ӣ��õ���С��child
		//��������ʹ����С�ڵ����ڶ������ĺ����Ƕ����������Ϊһ�ö����Ķ���������������С�ڵ㣩
		BinomialNode<T> child = reverse(y.child);
		
		//��ɾ����С�ڵ�Ķ����child��root���кϲ�
		root = union(root, child);
		
		y = null;
		
		return root;
	}
	
	public void exxtractMinimum(){
		mRoot = extractMinimum(mRoot);
	}
	
	/**
	 * ���ٽڵ�ļ�ֵ����������еĽڵ�node�ļ�ֵ��СΪkey
	 */
	private void decreaseKey(BinomialNode<T> node, T key){
		if(key.compareTo(node.key) > 0 || contains(key) == true){
			System.out.println("decrease failed : the new key(" + key + ") is existed already, or is no smaller than current key(" + node.key + ")");
			return;
		}
		
		node.key = key;
		
		BinomialNode<T> child, parent;
		child = node;
		parent = node.parent;
		
		while(parent != null && child.key.compareTo(parent.key) < 0){
			//����parent��child������
			T tmp = parent.key;
			parent.key = child.key;
			child.key = tmp;
			
			child = parent;
			parent = child.parent;
		}
	}
	
	/**
	 * ���ӽڵ�ļ�ֵ����������еĽڵ�node�ļ�ֵ����Ϊkey
	 */
	private void increaseKey(BinomialNode<T> node, T key){
		if(key.compareTo(node.key) <= 0 || contains(key) == true){
			System.out.println("increase failed : the new key(" + key + ") is existed already, or is no greater than current key (" +  node.key + ")");
			
			return ;
		}
		
		node.key = key;
		
		BinomialNode<T> cur = node;
		BinomialNode<T> child = cur.child;
		
		while(child != null){
			
			if(cur.key.compareTo(child.key) >0){
				/*
				 * �����ǰ�ڵ�<�������ӣ��������ĺ��ӣ����Ӻ����ӵ��ֵܣ���
				 * �ҳ���С�ڵ㣬Ȼ����С�ڵ�ļ�ֵ�͵�ǰ�ڵ�ļ�ֵ���л���
				 */
				BinomialNode<T> least = child;		//least��child�������ֵ��е���С�ڵ�
				while(child.next != null){
					if(least.key.compareTo(child.next.key) > 0){
						least = child.next;
					}
					child = child.next;
				}
				
				//������С�ڵ�͵�ǰ�ڵ�ļ�ֵ
				T tmp = least.key;
				least.key = cur.key;
				cur.key = tmp;
				
				//������ֵ֮���ٶ�ԭ��С�ڵ���е�����ʹ��������С�ѵ�����
				//���ڵ�<=�ӽڵ�
				cur = least;
				child = cur.child;
			}else{
				child = child.next;
			}
		}
	}
	
	/**
	 * ���¶���ѵĽڵ�node�ļ�ֵΪkey
	 */
	private void updateKey(BinomialNode<T> node, T key){
		if(node == null)
			return ;
		
		int cmp = key.compareTo(node.key);
		if(cmp < 0)
			decreaseKey(node, key);
		else if(cmp > 0)
			increaseKey(node, key);
		else
			System.out.println("No need to upate!");
	}
	
	/**
	 * ��������м�ֵoldkey����Ϊnewkey
	 */
	public void update(T oldkey, T newkey){
		BinomialNode<T> node;
		
		node = search(mRoot, oldkey);
		if(node != null)
			updateKey(node, newkey);
	}
	
	/**
	 * ���ң��ڶ�����в��Ҽ�ֵΪkey�Ľڵ�
	 */
	private BinomialNode<T> search(BinomialNode<T> root, T key){
		BinomialNode<T> child;
		BinomialNode<T> parent = root;
		
		parent = root;
		
		while(parent != null){
			if(parent.key.compareTo(key) == 0)
				return parent;
			else{
				if((child = search(parent.child, key)) != null)
					return child;
				
				parent = parent.next;
			}
		}
		
		return null;
	}
	
	/**
	 * ��������Ƿ������ֵkey
	 */
	public boolean contains(T key){
		return search(mRoot, key) != null ? true : false;
	}
	
	/**
	 * ɾ���ڵ㣬ɾ����ֵΪkey�Ľڵ�
	 */
	private BinomialNode<T> remove(BinomialNode<T> root, T key){
		if(root == null)
			return root;
		
		BinomialNode<T> node;
		
		//���Ҽ�ֵΪkey�Ľڵ�
		if((node = search(root, key)) == null)
			return root;
		
		//����ɾ���Ľڵ�������Ƶ������ڵĶ������ĸ��ڵ�
		BinomialNode<T> parent = node.parent;
		
		while(parent != null){
			//��������
			T tmp = node.key;
			node.key = parent.key;
			parent.key = tmp;
			
			//��һ�����ڵ�
			node = parent;
			parent = node.parent;
		}
		
		//�ҵ�node��ǰһ�����ڵ�prev
		BinomialNode<T> prev = null;
		BinomialNode<T> pos = root;
		
		while(pos != node){
			prev = pos;
			pos = pos.next;
		}
		
		//�Ƴ�node�ڵ�
		if(prev != null)
			prev.next = node.next;
		else
			root = node.next;
		
		root = union(root, reverse(node.child));
		
		node = null;
		
		return root;
	}
	
	public void remove(T key){
		mRoot = remove(mRoot, key);
	}
	
	/**
	 * ��ӡ�����
	 */
	private void print(BinomialNode<T> node, BinomialNode<T> prev, int direction){
		while(node != null){
			if(direction == 1)
				System.out.println(node.key + " is " + prev.key + "'s chld.");
			else
				System.out.println(node.key + " is " + prev.key + "'s next.");
			
			if(node.child != null)
				print(node.child, node, 1);
			
			//�ֵܽڵ�
			prev = node;
			node = node.next;
			direction = 2;
		}
	}
	
	public void print(){
		if(mRoot == null)
			return ;
		
		BinomialNode<T> p = mRoot;
		System.out.println("====�����");
		while(p != null){
			System.out.println(p.degree + " ");
			p  = p.next;
		}
		System.out.println("����ϸ��Ϣ��");
		
		int i = 0;
		p = mRoot;
		while(p != null){
			i++;
			System.out.println("��������" + p.degree);
			
			
			print(p.child, p, 1);
			p = p.next;
		}
		
		System.out.println("");
	}
	
	

}
