
import java.util.Scanner;

class Main{
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

        int xn = Integer.parseInt(in.nextLine());
        int a = Integer.parseInt(in.nextLine());
        int c = Integer.parseInt(in.nextLine());
        int m = Integer.parseInt(in.nextLine());
        int cuantos = Integer.parseInt(in.nextLine());
	    int arrCuantos[] = new int[cuantos];
	    int ciclo[] = new int[cuantos];
	    int cola[] = new int[cuantos];
	     
	    boolean hayCiclo = false;
	    arrCuantos[0] = xn;
	    int restantes = 0;
        for (int i = 1;i< cuantos; i++) {
        	xn=((a*xn + c ) % m);
        	arrCuantos[i]=xn;
        	System.out.println(arrCuantos[i]);
        	for (int j = 0; j < i; j++) {
        		if (arrCuantos[i] == arrCuantos[j]) {
        			//entre en el ciclo
        			ciclo = new int[i-j];
        			cola = new int[j];
        			for (int k = j; k < i; k++) {

        				ciclo[k-j] = arrCuantos[k];
        			}
        			for(int k = 0 ; k < j; k++){
        				cola[k] = arrCuantos[k];
        			}
        			restantes = i;
        			i=cuantos;
        			break;
        		}
        	}
        }
        for (int i = restantes;i < cuantos; i++) {
        	arrCuantos[i] = ciclo[(i-restantes)%ciclo.length];
        }
        System.out.println("ciclo \n\n");
        for (int i=0;i<ciclo.length;i++) {
        	System.out.println(ciclo[i]);
        }

        System.out.println("cola \n\n");
        for (int i=0;i<cola.length;i++) {
        	System.out.println(cola[i]);
        }
        System.out.println();
        System.out.println(arrCuantos.length);
	}
}