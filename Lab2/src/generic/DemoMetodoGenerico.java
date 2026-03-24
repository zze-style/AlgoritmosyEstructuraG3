package generic;

class DemoMetodoGenerico {
	static <T extends Comparable<T>> boolean igualArrays (T[] x, T[] y){
		//Si las longitudes de los arrays son diferentes,
		// entonces los array son diferentes
		if (x.length != y.length)
			return false;
		for (int i = 0; i < x.length; i++)
			if(!x[i].equals(y[i]))
				return false; //arrays diferentes
		return true; // Contenido de arrays son equivalentes
	}
	public static void main(String[] args) {
		Integer nums[]={1,2,3,4,5};
		Integer nums2[]={1,2,3,4,5};
		Integer nums3[]={1,2,7,4,5};
		Integer nums4[]={1,2,7,4,5,6};
		if (igualArrays(nums,nums))
			System.out.println("nums es igual a nums");
		if (igualArrays(nums,nums2))
			System.out.println("nums es igual a nums2");
		if (igualArrays(nums,nums3))
			System.out.println("nums es igual a num3");
		if (igualArrays(nums,nums4))
			System.out.println("nums es igual a nums4");
		// Crea un array de double                         A)
		//Double dvals[]={1.1,2.2,3.3,4.4,5.5};            B) En esta línea creamos un arreglo de doubles
		//if(igualArrays(nums,dvals))                      C) Esta condicional nos imprimiría el resultado en caso que el arreglo nums sea igual al arreglo dvals. Esta línea da error, debido a que al asignar "nums", el programa espera que "dvals" también sea INTEGER, pero como no lo es, error.
			//System.out.println("nums es igual a dvals"); //D Imprime la confirmación en caso la condicional se cumpliese
	}	
}
