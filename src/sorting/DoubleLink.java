package sorting;

/**
 * ���Ա�
 * (���顢������˫����
 * 
 * ���Ա���һ�����Խṹ�����Ǿ�����ͬ���͵�n(n>=0)������Ԫ����ɵ��������С�
 * 
 * ���飺�����Ͻ���½�ģ������Ԫ�����ڴ����������ģ���������ٶȿ죻
 * ��̬������ָ����������ܹ���̬���������飻
 * 
 * ���������������һ�֣����ɽڵ���ɣ�ÿ���ڵ㶼����ָ����һ���ڵ��ָ�룻
 * ��������ص��ǣ��ڵ�����ӷ����ǵ���ģ������������˵�����������������ٶȽ�����
 * ���ǵ�����ɾ����������ݵ�Ч�ʽϸߣ�
 * 
 * ˫�������������һ�֣����ɽڵ���ɣ�ÿ���ڵ㶼������ָ�룬�ֱ�ָ����ֱ�Ӻ�̺�
 * ֱ��ǰ�������ԣ���˫�������е�����һ���ڵ㿪ʼ�������Ժܷ���ط�������ǰ���ͺ��
 * �ڵ㣻һ�㶼�ɹ����˫��ѭ������
 * 
 * 
 * @author Stargazer
 * @date 2017-03-18
 */

public class DoubleLink<T> {
	//����ֻʵ����˫������
	
	//��ͷ
	private DNode<T> mHead;
	//�ڵ����
	private int mCount;
	
	//˫�������ڵ㡱��Ӧ����
	private class DNode<T>{
		public DNode prev;			//�ýڵ��ǰ���ڵ�
		public DNode next;			//�ýڵ�ĺ�̽ڵ�
		public T value;					//�ýڵ������ֵ
		
		public DNode(T value, DNode prev, DNode next){
			this.value = value;
			this.prev = prev;
			this.next = next;
		}
	}
	
	//˫������Ĺ��캯��
	public DoubleLink(){
		//������ͷ��ע�⣺��ͷû�д洢����
		mHead = new DNode<T>(null, null, null);
		
		//�յ�˫������ʹ��ͷ�ĵ�ǰ���ͺ��ָ���ָ���ͷ�Լ�
		mHead.prev = mHead.next = mHead;
		
		//��ʼ���ڵ����Ϊ0
		mCount = 0;
	}
	
	//���ؽڵ���Ŀ
	public int size(){
		return mCount;
	}
	
	//���� �����Ƿ�Ϊ��
	public boolean isEmpty(){
		return mCount == 0;
	}
	
	//��ȡ��indexλ�õĽڵ㣨λ�ô�0��ʼ������
	private DNode<T> getNode(int index){
		if(index < 0 || index >= mCount)
			throw new IndexOutOfBoundsException();
		
		//�������
		if(index <= mCount/2){
			DNode<T> node = mHead.next;
			for(int i = 0; i < index; i++)
				node = node.next;
			
			return node;
		}
		
		//�������
		DNode<T> rnode = mHead.prev;
		int rindex = mCount - index - 1;
		for(int j = 0; j < rindex; j++)
			rnode = rnode.prev;
		
		return rnode;
	}
	
	//��ȡ��indexλ�õĽڵ������ֵ
	public T get(int index){
		return getNode(index).value;
	}
	
	//��ȡ��һ���ڵ������ֵ
	public T getFirst(){
		return getNode(0).value;
	}
	
	//��ȡ���һ���ڵ������ֵ
	public T getLast(){
		return getNode(mCount-1).value;
	}
	
	//���ڵ���뵽��indexλ��֮ǰ
	public void insert(int index, T t){
		if(index == 0){
			DNode<T> node = new DNode<T>(t, mHead, mHead.next);
			mHead.next.prev = node;
			mHead.next = node;
			mCount++;
			return;
		}
		
		DNode<T> inode = getNode(index);
		DNode<T> tnode = new DNode<T>(t, inode.prev, inode);
		inode.prev.next = tnode;
		inode.prev = tnode;
		
		mCount++;
		return;
	}
	
	//���ڵ�����һ���ڵ㴦
	public void insertFirst(T t){
		insert(0, t);
	}
	
	//���ڵ�׷�ӵ������ĩβ
	public void appendLast(T t){
		DNode<T> node = new DNode<T>(t, mHead.prev, mHead);
		mHead.prev.next = node;
		mHead.prev = node;
		mCount++;
		return;
	}
	
	//ɾ��indexλ�õĽڵ�
	public void delete(int index){
		DNode<T> inode = getNode(index);
		inode.prev.next = inode.next;
		inode.next.prev = inode.prev;
		inode = null;
		mCount--;
		return;
	}
	
	//ɾ����һ���ڵ�
	public void deleteFirst(){
		delete(0);
	}
	
	//ɾ�����һ���ڵ�
	public void deleteLast(){
		delete(mCount--);
	}
	
	
	
	
}
