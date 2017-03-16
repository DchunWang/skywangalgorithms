package sorting;

/**
 * ð������
 * 
 * ð����������������ɴ�Ҫ��������У�ÿ�α���ʱ���������ǰ��������
 * �ıȽ������������Ĵ�С�����ǰ�߱Ⱥ��ߴ��򽻻����ǵ�λ�ã���������
 * һ�α�����֮������Ԫ�ؾ������е�ĩβ��������ͬ�ķ����ٴα���ʱ��
 * ��ڶ����Ԫ�ؾͱ����������Ԫ��֮ǰ���ظ������ı���������ֱ����������
 * ������Ϊֹ��
 * 
 * ð������ĵ�ʱ�临�Ӷ�ΪO(N^2)
 * ð���������ȶ����㷨
 * 
 * �㷨�ȶ��ԣ������������д���a[i]=a[j]����������֮ǰ��a[i]��a[j]��ǰ�棻����
 * ������֮��a[i]����a[j]��ǰ�棬����������㷨���ȶ��ġ�
 * 
 * @author Stargazer
 * @date 2017-03-16
 */
public class BubbleSort {

	/**
	 * ð������
	 * @param a �����������
	 * @param n ����ĳ���
	 */
	public static void bubbleSort1(int[] a, int n){
		int i, j;
		
		for(i = n-1; i > 0; i--){
			//��a[0...i]�е�������ݷ���ĩβ
			//ÿ���һ����ѭ�����Ͱѡ�0~i���е����һ�����ŵ�������棻
			for(j = 0; j < i; j++){
				
				if(a[j] > a[j+1]){
					//����a[j]��a[j+1]
					int tmp = a[j];
					a[j] = a[j+1];
					a[j+1] = tmp;
				}
			}
		}
	}
	
	
	/**
	 * ð�����򣨸Ľ��棩
	 * @param a
	 * @param n
	 */
	public static void bubbleSort2(int[] a, int n){
		int i, j;
		int flag;		//���
		
		for(i = n-1; i > 0; i--){
			//��ʼ�����Ϊ0
			flag = 0;		
			//��a[0...i]�е��������ݷ���ĩβ
			for(j = 0; j < i; j++){
				if(a[j] > a[j+1]){
					//����a[j]��a[j+1]
					int tmp = a[j];
					a[j] = a[j+1];
					a[j+1] = a[j];
					
					//���������������Ǽ�Ϊ1
					flag = 1;			
				}
			}
			
			/*
			 * ���һ����������ѭ����û�з�����������˵�����������Ѿ������ˣ�
			 * ���Բ�����ȥѭ�������Ƚ�a[0...i]֮���������
			 */
			if(flag == 0){
				break;					//��û������������˵�����������Ѿ�������
			}
		}
	}
	
	//ð���������
	public static void main(String[] args){
		int i;
		int[] a = {5, 3, 7, 9, 1, 4, 2, 6};
		
		System.out.println("before sort: ");
		for(i = 0; i < a.length; i++){
			System.out.print(a[i] + "\t");
		}
		System.out.println("");
		
		bubbleSort1(a, a.length);
		//bubbleSort2(a, a.length);
		
		System.out.println("after sort: ");
		for(i = 0; i < a.length; i++){
			System.out.print(a[i] + "\t");
		}
		System.out.println("");
	}
}
