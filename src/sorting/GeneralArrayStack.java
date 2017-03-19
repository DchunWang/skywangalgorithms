package sorting;

import java.lang.reflect.Array;

/**
 * ջ
 * 
 * ջ��һ�����Խṹ���������¼����ص㣺
 * 1��ջ�������ǰ��ա�����ȳ�(LIFO, Last In First Out)����ʽ����ջ�ģ�
 * 2����ջ�����/ɾ������ʱ��ֻ�ܴ�ջ�����в�����
 * 
 * ջͨ�����������ֲ�����
 * push -- ��ջ�����Ԫ��
 * peek -- ����ջ��Ԫ�أ�����ɾ��Ԫ��
 * pop --  ���ز�ɾ��ջ��Ԫ��
 * 
 * 
 * @author Stargazer
 * @date 2017-03-19
 */

public class GeneralArrayStack<T> {

	private static final int DEFAULT_SIZE = 12;
	private T[] mArray;
	private int count;
	
	public GeneralArrayStack(Class<T> type){
		this(type, DEFAULT_SIZE);
	}
	
	public GeneralArrayStack(Class<T> type, int size){
		//����ֱ��ʹ��mArray=new T[DEFAULT_SIZE];
		mArray = (T[]) Array.newInstance(type, size);
		count = 0;
	}
	
	/**
	 * ��val��ӵ�ջ��
	 * @param val
	 */
	public void push(T val){
		mArray[count++] = val;
	}
	
	/**
	 * ����ջ��Ԫ��ֵ��������ɾ��
	 */
	public T peek(){
		return mArray[count-1];
	}
	/**
	 * ����ջ��Ԫ��ֵ����ɾ��ջ��Ԫ��
	 */
	public T pop(){
		T ret = mArray[count-1];
		count--;
		return ret;
	}
	
	/**
	 * ����ջ�Ĵ�С
	 */
	public int size(){
		return count;
	}
	
	/**
	 * ����ջ�Ƿ�Ϊ��
	 */
	public boolean isEmpty(){
		return size() == 0;
	}
	
	/**
	 * ��ӡջ
	 */
	public void printArrayStack(){
		if(isEmpty()){
			System.out.println("stack is Empty!");
			return;
		}
		
		System.out.println("stack size() = " + size());
		
		int i = size() - 1;
		while(i >= 0){
			System.out.println(mArray[i]);
			i--;
		}
	}
	
	public static void main(String[] args){
		String tmp;
		GeneralArrayStack<String> astack = new GeneralArrayStack<String>(String.class);
		
		//��10��20��30��������ջ��
		astack.push("10");
		astack.push("20");
		astack.push("30");
		
		//��ջ��Ԫ�ظ�ֵ��tmp,��ɾ��ջ��Ԫ��
		tmp = astack.pop();
		System.out.println("ջ��Ԫ��tmp=" + tmp);
		
		//ֻ��ջ��Ԫ�ظ�ֵ��tmp������ɾ��Ԫ��
		tmp = astack.peek();
		System.out.println("��ʱ��ջ��Ԫ��Ϊtmp="+ tmp);
		
		astack.push("40");
		astack.printArrayStack();
	}
}
