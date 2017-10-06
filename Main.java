/* Main.java
 * Heloel Hernandez Santos A07007415
 * Jose Pablo Ortiz Lack A01099655
 * Programa para implementar un generador de numeros pseudoaleatorios, asi como mostrar informacion estadistica de estos
 * */

//Importacion de librerias de Java
import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
class Main{

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);

		int xn = Integer.parseInt(in.nextLine()); //Semilla
        int a = Integer.parseInt(in.nextLine()); //Multiplicador
        int c = Integer.parseInt(in.nextLine()); //Corrimiento
        int m = Integer.parseInt(in.nextLine()); //Modulo
		int cuantos = Integer.parseInt(in.nextLine()); //cuantos numeros aleatorios queremos
		
		//Cerramos el scanner para evitar leaks de memoria ;)
		in.close();
		//Declaracion inicial de arreglos para guardar los numeros generados pseudoaleatoriamente. Por default del tamanyo maximo posible
	    double arrCuantos[] = new double[cuantos]; //Arreglo para guardar los numeros pseudoaleatorios
	    double ciclo[] = new double[cuantos]; //Arreglo para guardar la parte de ciclo del periodo
		double cola[] = new double[cuantos];  //Arreglo para guardar la parte de la cola del periodo
		
		arrCuantos[0] = xn; //El primer valor del arreglo siempre es la semilla
		
		int cantidadGenerada = 0; //Variable para guardar la cantidad generada de numeros pseudoaleatorios para continuar una vez se encuentre un ciclo
		boolean hayCiclo = false;
		//Generamos valores pseudoaleatorios hasta cumplir con la cantidad solicitada o se encuentra un ciclo
        for (int i = 1; i< cuantos && hayCiclo == false; i++) {
        	xn=( ( a * xn + c ) % m); //El siguiente numero pseudoaleatorio se encuentra y queda como semilla para el siguiente numero
			
			arrCuantos[i]=xn;

			//iteramos todo el arreglo de numeros aleatorios para saber si existe un ciclo
        	for (int j = 0; j < i; j++) {
        		if (arrCuantos[i] == arrCuantos[j]) { //existe un ciclo en el arreglo
					
					ciclo = new double[i-j]; //Redimensionamos el arreglo de ciclo al tamanyo que necesitamos
        			cola = new double[j]; //Redimensionamos el arreglo de cola al tamanyo que necesitamos
        			for (int k = j; k < i; k++) { //Agregamos los valores desde el inicio de la cola (j) hasta un valor antes del final del arreglo (i)

						ciclo[k-j] = arrCuantos[k];
					}
					
        			for(int k = 0 ; k < j; k++){ //Agregamos todos los valores previos al inicio del ciclo al arreglo de cola
						
						cola[k] = arrCuantos[k];
        			}
        			cantidadGenerada = i; //Guardamos la cantidad actual generada de numeros pseudoaleatorios para continuar generando despues
        			hayCiclo = true; //se cambia la bandera de hayCiclo a true para dejar de generar numeros pseudoaleatorios por medio de la formula
        			break; //Termina la iteración del arreglo buscando ciclos
        		}
        	}
		}
		
		//Al haber un ciclo podemos generar los numeros faltantes iterando sobre el arreglo de ciclo
        for (int i = cantidadGenerada;i < cuantos; i++) {
			
			arrCuantos[i] = ciclo[(i-cantidadGenerada)%ciclo.length];
		}
		
		//Imprimimos La cola y cuantos valores tiene
		System.out.println("--------------");
		System.out.println("COLA:\n");
        for (int i=0; i < cola.length ; i++) {
			
			System.out.print(cola[i] + "->");
		}
		//Verificamos si existe una cola antes de imprimir
		if(cola.length > 0) {
			
			System.out.println(cola[cola.length - 1]);
		}
		System.out.println("Cantidad de elementos: " + cola.length);

		//Imprimimos el ciclo y cuantos valores tiene
		System.out.println("--------------");
		System.out.println("CICLO:\n");
        for (int i=0; i < ciclo.length - 1 ; i++) {
			
			System.out.print(ciclo[i] + "->");
		}

		//Verificamos si existe un ciclo antes de imprimir
		if(ciclo.length > 0) {
			
			System.out.println(ciclo[ciclo.length - 1]);
		}
		System.out.println("Cantidad de elementos: " + ciclo.length);

		//Imprimimos el periodo y cuantos valores tiene
		System.out.println("--------------");
		System.out.println("PERIODO:\n");
        for (int i=0; i < cola.length; i++) {
			
			System.out.print(cola[i] + "->");
		}
		for (int i=0; i < ciclo.length - 1 ; i++) {
			
			System.out.print(ciclo[i] + "->");
		}
		//Verificamos si existe un ciclo antes de imprimir
		if(ciclo.length > 0) {
			
			System.out.println(ciclo[ciclo.length - 1]);
		}
		int cantidadPeriodo = cola.length + ciclo.length;
		System.out.println("Cantidad de elementos: " + cantidadPeriodo);

		
		//Ordenamos el arreglo para poder obtener la mediana
		bubbleSort(arrCuantos);
		System.out.println("--------------");
		//Llamadas e impresiones de los metodos de Media, Mediana, Moda, Varianza y Desviacion Estandar
		System.out.println("El promedio es: "+media(arrCuantos));
		System.out.println("La mediana es: "+mediana(arrCuantos));
		System.out.print("La moda es: ");
		moda(arrCuantos);
		System.out.println("La varianza es: "+varianza(arrCuantos));
		System.out.println("La desviacion estandar es: "+desviacionEstandar(arrCuantos));


		//Declaracion de variables para llevar un conteo de los rangos
		double rango01 = 0, rango12 = 0, rango23 = 0, rango34 = 0, rango45 = 0, rango56 = 0, rango67 = 0, rango78 = 0, rango89 = 0, rango91 = 0;
		
		//Iteramos el arreglo de numeros Aleatorios para calcular los rangos
		for(double numero : arrCuantos) {
			
			double numeroAjustado = numero/(m-1); //Ajustamos el numero a un valor entre 0 y 1
			//Verificamos en que rango se encuentra el numero y contamos acorde
			if(numeroAjustado > 0.0 && numeroAjustado <= 0.1) {
				rango01++;
			}
			else if(numeroAjustado > 0.1 && numeroAjustado <= 0.2){
				rango12++;
			}
			else if(numeroAjustado > 0.2 && numeroAjustado <= 0.3){
				rango23++;
			}
			else if(numeroAjustado > 0.3 && numeroAjustado <= 0.4){
				rango34++;
			}
			else if(numeroAjustado > 0.4 && numeroAjustado <= 0.5){
				rango45++;
			}
			else if(numeroAjustado > 0.5 && numeroAjustado <= 0.6){
				rango56++;
			}
			else if(numeroAjustado > 0.6 && numeroAjustado <= 0.7){
				rango67++;
			}
			else if(numeroAjustado > 0.7 && numeroAjustado <= 0.8){
				rango78++;
			}
			else if(numeroAjustado > 0.8 && numeroAjustado <= 0.9){
				rango89++;
			}
			else if(numeroAjustado > 0.9 && numeroAjustado <= 1.0){
				rango91++;
			}
		}

		//Imprimimos los resultados
		System.out.println("--------------");
		System.out.println("RANGOS DE VALORES:\n");
		System.out.println("Valores entre 0.0 y 0.1: " + (rango01/m) * 100 + "%");
		System.out.println("Valores entre 0.1 y 0.2: " + (rango12/m) * 100 + "%");
		System.out.println("Valores entre 0.2 y 0.3: " + (rango23/m) * 100 + "%");
		System.out.println("Valores entre 0.3 y 0.4: " + (rango34/m) * 100 + "%");
		System.out.println("Valores entre 0.4 y 0.5: " + (rango45/m) * 100 + "%");
		System.out.println("Valores entre 0.5 y 0.6: " + (rango56/m) * 100 + "%");
		System.out.println("Valores entre 0.6 y 0.7: " + (rango67/m) * 100 + "%");
		System.out.println("Valores entre 0.7 y 0.8: " + (rango78/m) * 100 + "%");
		System.out.println("Valores entre 0.8 y 0.9: " + (rango89/m) * 100 + "%");
		System.out.println("Valores entre 0.9 y 1.0: " + (rango91/m) * 100 + "%");

		//Finalizar programa
		System.out.println("--------------");
		System.out.println("FIN DEL PROGRAMA");

	}
	//Metodo Bubble Sort para ordenar el arreglo
	public static void bubbleSort(double[] arreglo) {
    int contador = 0;
    double auxiliar = 0;
    boolean intercambiado = true;
	    
	    while (intercambiado) {

	        intercambiado = false;
	        contador++;
	        
	        for (int i = 0; i < arreglo.length - contador; i++) {
	        
	            if (arreglo[i] > arreglo[i + 1]) {
	                auxiliar = arreglo[i];
	                arreglo[i] = arreglo[i + 1];
	                arreglo[i + 1] = auxiliar;
	                intercambiado = true;
	            	}
	        
	        	}
	    	
	    	}
	}
	//Metodo para obtener la mediana
	public static  double mediana(double[] arreglo){
		double mediana;
		if (arreglo.length % 2 == 0){
		    mediana = ((double)arreglo[arreglo.length/2] + (double)arreglo[arreglo.length/2 - 1])/2;
		}
		else{
		    mediana = (double) arreglo[arreglo.length/2];
		}
		return mediana;
	}
	//Metodo para obtener la Media
	public static double media(double[] arreglo){
		double suma = 0;
		for (double valor : arreglo){ 
			suma += valor;
		}
		double media = 1.0d * suma / arreglo.length;
		return media;
	}
	//Metodo para obtener las Modas
	public static void moda(double arreglo[]){

	  ArrayList<Double> moda = new ArrayList<Double>();

	  int maximo=0;   
	  for (int i = 0; i < arreglo.length; i++){
	    int contador = 0;
	    for (int j = 0; j < arreglo.length; j++){
	      if (arreglo[j] == arreglo[i]){ 
	      	contador++;
	      	}
	    }
	    if (contador > maximo){
	      maximo = contador;
	      moda.clear();
	      moda.add(arreglo[i]);
	    } else{ 
	    	if (contador == maximo){
		      moda.add(arreglo[i]);
		    }
		}
	  }
	  System.out.println(Arrays.toString(moda.toArray()));
	}
	//Metodo para obtener la varianza
	public static double varianza(double[] arreglo){
		 double diferenciasCuadradas = 0.0;
		   double promedio = media(arreglo);
		   for (double valor : arreglo)
		   {
		       double diferencia = valor-promedio;
		       diferencia *= diferencia;
		       diferenciasCuadradas += diferencia;
		   }
		   return (diferenciasCuadradas /(arreglo.length-1));
	}
	//Metodo para obtener la desviacion estandar
	public static double desviacionEstandar(double[] arreglo){
		return (double)Math.pow(varianza(arreglo), 0.5);
	}
}