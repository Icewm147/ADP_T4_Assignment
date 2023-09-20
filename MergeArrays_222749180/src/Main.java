public class Main {

    static int merge(int arr1[], int arr2[], int arr3[]){
        int i = 0, j = 0, k = 0;
        while (i!=arr1.length && j!=arr2.length){
            if(arr1[i] > arr2[j]) arr3[k++] = arr1[i++];
            else if(arr1[i] < arr2[j]) arr3[k++] = arr2[j++];
            else {
                arr3[k++] = arr1[i++];
                j++;
            }
            System.out.println("\n"+arr3[k-1]);
        }

        /*if(i == arr1.length)
            for(int l = j; l < arr2.length;l++,k++)
                arr3[k] = arr2[l++];
        if(j == arr2.length)
            for(int l = i; l < arr1.length;l++,k++)
                arr3[k] = arr1[l++];*/

        while (j < arr2.length)
            arr3[k++] = arr2[j++];
        while ( i < arr1.length)
            arr3[k++] = arr1[i++];
        return k;
    }

    public static void main(String[] args) {
        int arr1[] ={19, 9, 7, 5, 1};
        int arr2[] ={21, 12, 5, 4, 3, 1};
        int arr3[] = new int[arr1.length + arr2.length];
        int size3;

        System.out.print("\nArray 1 is : ");
        for(int i = 0; i < arr1.length;i++)
            System.out.print(arr1[i] + " ");
        System.out.print("\nArray 2 is : ");
        for(int i = 0; i < arr2.length;i++)
            System.out.print(arr2[i] + " ");

        size3 = merge(arr1, arr2, arr3);

        System.out.print("\nAfter merge the array is :");
        for(int i = 0; i < size3; i++)
            System.out.print(arr3[i] + " ");
    }
}