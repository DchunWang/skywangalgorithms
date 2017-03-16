package sorting;

/**
 * ϣ������/��С��������
 * 
 * ϣ�������ǲ��������һ�֣������ֱ�Ӳ��������㷨�ĸĽ���
 * ϣ��������һ�� ������뷽�������Ļ���˼���ǣ�����n������������У�ȡһ
 * ��С��n������gap(����)�����������Ԫ�طֳ����ɸ��������У����о���Ϊ
 * gap�ı����ļ�¼����ͬһ�����У�Ȼ�󣬶Ը����ڵ�Ԫ�ؽ���ֱ�Ӳ�������
 * ��һ���������֮��ÿһ�����е�Ԫ�ض�������ģ�Ȼ���Сgap��ֵ������
 * ������Ĳ�������gap=1ʱ���������о���������ˡ�
 * 
 * ϣ�������ʱ�临�Ӷȣ�
 * ��ʱ�临�Ӷ���������������gap����ѡȡ�йأ����磬������Ϊ1ʱ��ϣ������
 * �˻�����ֱ�Ӳ������򣬴�ʱ��ʱ�临�Ӷ�ΪO(N^2)����Hibbard������ϣ��
 * �����ʱ�临�Ӷ�ΪO(N^(3/2))��
 * 
 * ϣ������ʱ���ȶ����㷨
 * 
 * �㷨�ȶ��ԣ������������д���a[i]��a[j]����������֮ǰ��a[i]��a[j]��ǰ�棬����
 * ������֮��a[i]����a[j]��ǰ�棬��ƴ��㷨���ȶ��ġ�
 * 
 * @author Stargazer
 * @date 2017-03-16
 */
public class ShellSort {

	/**
	 * ϣ������
	 * 
	 * @param a 	�����������
	 * @param n 	����ĳ���
	 */
	public static void shellSort(int[] a, int n){
		
		//gapΪ������ÿ�μ�Ϊԭ����һ��
		for(int gap = n/2; gap > 0; gap /= 2){
			
			//����������з�Ϊgap���飬��ÿ�鶼ִ��ֱ�Ӳ�������
			for(int i = 0; i < gap; i++){
				
				/**
				 * ע���ʱ����һ������ĸ���Ԫ�ص�λ�ò��������ģ����Ǽ��gap,
				 * ����i, i+gap, i+2gap, i+3gap, i+4gap, i+5gap, ... 
				 * ��������forѭ��������⣬����԰�gapȫ������1�����
				 * ��i,...,j-gap��������������j,..,n������������ÿ�ζ����������ĵ�1��Ԫ��a[j]
				 * ���뵽��������
				 */
				for(int j = i+gap; j < n; j += gap){
					//���a[j] < a[j-gap]����Ѱ��a[j]��λ�ã������������ݵ�λ�ö�����һλ
					if(a[j] < a[j-gap]){
						int temp = a[j];
						int k = j - gap;
						//�����������ҵ�temp>a[k]��λ�ã�������������a[k]֮�������Ԫ�ض�����һλ���Ա��ó����Է�temp��λ��
						while(k >= 0 && a[k] > temp){
							a[k+gap] = a[k];
							k -= gap;
						}
						//whileѭ��������a[k]�͸պ�С��temp,���Ѿ���ԭ��a[k]֮���Ԫ�ض�
						//������һλ����˾Ͱ�temp���뵽a[k]֮���һλλ����
						a[k+gap] = temp;
					}
				}
		
			}
		}
	}
	
	/**
	 * ��ϣ�������еĵ������������
	 *
	 * ע�⣺���ǡ���i��ʼ�������gap���ȵ�Ԫ��ȡ��������ɵģ�
	 * 
	 * @param a 	�����������
	 * @param n	������ܳ���
	 * @param i 		�����ʼλ��
	 * @param gap ��Ĳ���
	 */
	public static void groupSort(int[] a, int n, int i, int gap){
		
		for(int j = i + gap; j < n; j += gap){
			//���a[j] < a[j-gap],���ڡ�i,...,j-gap�������������Ѱ��a[j]��λ�ã����ͺ������ݵ�λ�ö�����һλ
			if(a[j] < a[j-gap]){
				int temp = a[j];
				int k = j-gap;
				while(k >= 0 && a[k] > temp){
					a[k+gap] = a[k];
					k -= gap;
				}
				a[k+gap] = temp;
			}
		}
	}
	
	/**
	 * ϣ������
	 * 
	 * @param a		�����������
	 * @param n	����ĳ���
	 */
	public static void shellSort2(int[] a, int n){
		//gapΪ������ÿ�μ�Ϊԭ����һ��
		for(int gap = n/2; gap > 0; gap /= 2){
			//���������з�Ϊ��gap���飬��ÿһ�鶼ִ��ֱ�Ӳ�������
			for(int i = 0; i < gap; i++)
				groupSort(a, n, i, gap);
		}
	}
	
	public static void main(String[] args){
		int i; 
		int[] a = {5, 3, 7, 9, 1, 4, 2, 6, 8};
		
		System.out.println("before sort:");
		for(i = 0; i < a.length; i++)
			System.out.print(a[i] + "\t");
		System.out.println("");
		
		shellSort(a, a.length);
		//shellSort2(a, a.length);
		
		System.out.println("after sort:");
		for(i = 0; i < a.length; i++)
			System.out.print(a[i] + "\t");
	}
	
}
