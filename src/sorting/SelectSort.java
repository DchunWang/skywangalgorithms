package sorting;

/**
 * ѡ������
 * 
 * ����˼���ǣ�������δ������������ҵ���С��or��󣩵�Ԫ�أ�Ȼ������
 * �����е���ʼλ�ã����� ���ٴ�ʣ��δ�����Ԫ���м���Ѱ����С��or��󣩵�
 * Ԫ�أ�Ȼ��ŵ����������е�ĩβ���Դ����ƣ�ֱ������Ԫ�ؾ�������ϡ�
 * 
 * ѡ�������ʱ�临�Ӷȣ�O(N^2)
 * 
 * ������Ľ��ͣ�������������������N����������һ�˵�ʱ�临�Ӷ���O(N)��
 * ��Ҫ�������ٴ��أ�N-1�Σ���ˣ�ѡ�������ʱ�临�Ӷ���O(N^2)
 * 
 * ѡ���������ȶ����㷨
 * 
 * �㷨�ȶ��ԣ������������д���a[i]=a[j]����������֮ǰ��a[i]��a[j]��ǰ�棬����
 * ������֮��a[i]����a[j]��ǰ�棬��˵����������㷨���ȶ��ģ�
 * 
 * @author Stargazer
 * @date 2017-03-18
 */

public class SelectSort {

	/**
	 * ѡ������
	 * 
	 * @param a			�����������
	 * @param n 		����ĳ���
	 */
	public static void selectSort(int[] a, int n){
		int i;			//��������ĩβλ��
		int j;			//����������ʼλ��
		int min;	//�������е���СԪ��λ��
		
		for(i = 0; i < n; i++){
			min = i;
			
			//�ҳ�a[i+1]~a[n]֮�����СԪ�أ�����ֵ��min
			for(j = i + 1; j < n; j++){
				if(a[j] < a[min])
					min = j;
			}
			
			//��min != i���򽻻�a[i]��a[min]
			//����֮�󣬱�֤��a[0]~a[i]֮���Ԫ���������
			if(min != i){
				int tmp = a[i];
				a[i] = a[min];
				a[min] = tmp;
			}
		}
	}
	
	public static void main(String[] args){
		int i;
		int[] a = {5, 3, 7, 9, 1, 4, 2, 6, 8};
		
		System.out.println("before sort: ");
		for(i = 0; i < a.length; i++)
			System.out.print(a[i] + "\t");
		System.out.println("");
		
		selectSort(a, a.length);
		
		System.out.println("after sort:");
		for(i = 0; i < a.length; i++)
			System.out.print(a[i] + "\t");
	}
}
