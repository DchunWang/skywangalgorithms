package sorting;

/**
 * ����
 * 
 * ������һ�����Խṹ���������¼����ص㣺
 * 1�������������ǰ��ա��Ƚ��ȳ�(FIFO, First In First Out)����ʽ�������еģ�
 * 2������ֻ�����ڡ����ס�����ɾ�����������ڡ���β�����в��������
 * 
 * 
 * @author Stargazer
 * @date 2017-03-19
 */

public class ArrayQueue {
	//����ʵ�ֵĶ��У���ֻ�ܴ洢int��������
	
	private int[] mArray;
	private int mCount;
	
	public ArrayQueue(int sz){
		mArray = new int[sz];
		mCount = 0;
	}
	
	/**
	 * ��val��ӵ����е�ĩβ
	 * @param val
	 */
	public void add(int val){
		mArray[mCount++] = val;
	}
	
	/**
	 * ���ض��п�ͷԪ��
	 * @return
	 */
	public int front(){
		return mArray[0];
	}
	
	/**
	 * ���ض���Ԫ��ֵ����ɾ������Ԫ��
	 * @return
	 */
	public int pop(){
		int ret = mArray[0];
		mCount--;
		for(int i = 1; i <= mCount; i++)
			mArray[i-1] = mArray[i];
		
		return ret;
	}
	
	/**
	 * ���ض��еĴ�С
	 */
	public int size(){
		return mCount;
	}
	
	/**
	 * ���ض����Ƿ�Ϊ��
	 */
	public boolean isEmpty(){
		return size() == 0;
	}
	
	public static void main(String[] args){
		int tmp = 0;
		ArrayQueue aqueue = new ArrayQueue(12);
		
		//��10/20/30���η��������
		aqueue.add(10);
		aqueue.add(20);
		aqueue.add(30);
		
		//������Ԫ�ظ�ֵ��tmp����ɾ������Ԫ��
		tmp = aqueue.pop();
		System.out.println("��ɾ���Ķ���Ԫ����tmp=" + tmp);
		
		aqueue.add(40);
		
		System.out.println("isEmpty()="  + aqueue.isEmpty());
		System.out.println("size()=" + aqueue.size());
		while(!aqueue.isEmpty()){
			System.out.println("Ԫ��Ϊ:" + aqueue.pop());
		}
	}

}
