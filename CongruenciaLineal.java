
import java.util.Scanner;

class CongruenciaLineal{
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

        int xn = Integer.parseInt(in.nextLine());
        int a = Integer.parseInt(in.nextLine());
        int c = Integer.parseInt(in.nextLine());
        int m = Integer.parseInt(in.nextLine());
        int cuantos = Integer.parseInt(in.nextLine());
	    int arrCuantos[] = new int[cuantos];    
	        for (int i = 0;i< cuantos; i++) {
	        	xn=((a*xn + c ) % m);
	        	arrCuantos[i]=xn;
	        	System.out.println(xn );
	        	
	        }
	}


}