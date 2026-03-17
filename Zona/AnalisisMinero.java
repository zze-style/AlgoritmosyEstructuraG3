package Zona;

public class AnalisisMinero {

    public static void main(String[] args) {
        int filas = 3;
        int columnas = 3;
        Zona[][] mapaMinero = new Zona[filas][columnas];
        
        mapaMinero[0][0] = new Zona("Oro", 10, 0.9);
        mapaMinero[0][1] = new Zona("Plata", 8, 0.85);
        mapaMinero[0][2] = new Zona("Cobre", 12, 0.7);
        mapaMinero[1][0] = new Zona("Oro", 6, 0.92);
        mapaMinero[1][1] = new Zona("Oro", 4, 0.88);
        mapaMinero[1][2] = new Zona("Plata", 7, 0.8);
        mapaMinero[2][0] = new Zona("Cobre", 9, 0.75);
        mapaMinero[2][1] = new Zona("Oro", 5, 0.9);
        mapaMinero[2][2] = new Zona("Plata", 6, 0.82);
        
        int k = 2;
        encontrarMejorRegion(mapaMinero, k);
    }

    public static void encontrarMejorRegion(Zona[][] matriz, int k) {
        double valorMaximo = -1;
        int filaBase = 0;
        int columnaBase = 0;
        
        for (int i = 0; i <= matriz.length - k; i++) {
            for (int j = 0; j <= matriz[0].length - k; j++) {
                double sumaActual = 0; 
                for (int f = i; f < i + k; f++) {
                    for (int c = j; c < j + k; c++) {
                        sumaActual += matriz[f][c].calcularValorEconomico();
                    }
                }
                if (sumaActual > valorMaximo) {
                    valorMaximo = sumaActual;
                    filaBase = i;
                    columnaBase = j;
                }
            }
        }

        System.out.println("Region mas valiosa encontrada en: (" + filaBase + "," + columnaBase + ")");
        System.out.println("Valor total estimado: " + valorMaximo);
    }
}