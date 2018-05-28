import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Random;

public class PruebasRendimiento {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double TiempoInicialQuickSortEnteros, TiempoFinalQuickSortEnteros, TiempoTotalQuickSortEnteros;
        ArrayList<Integer> ArregloEnteros = new ArrayList<Integer>();
        ArrayList<Integer> ArregloEnterosParaOrdenamiento = new ArrayList<Integer>();
        ArrayList<Double> ArregloFlotantes = new ArrayList<Double>();
        
        
//ENTEROS
        
        System.out.println("------------------------------OPERACIONES PARA ENTEROS-----------------------" + "\n");
    
        
        Enteros entero = new Enteros();

    //Benchmarking para evaluar el rendimiento del disco duro, mediante la escritura de conjuntos grandes de datos.
        entero.EscribirEnteros();

        ArregloEnteros = entero.LeerIntegers();

    //Benchmarking para la evaluación del rendimiento de la UAL
        entero.SumarEnteros(ArregloEnteros);

        entero.RestarEnteros(ArregloEnteros);

        entero.MultiplicarEnteros(ArregloEnteros);

        entero.DividirEnteros(ArregloEnteros);

    //benchmarking para evaluar el rendimiento de la memoria RAM con algoritmo de quicksort para Enteros
        ArregloEnterosParaOrdenamiento = entero.leerEnterosParaOrdenamiento();

        int ArregloEnterosOrdenadoDescendetemente[] = new int[ArregloEnterosParaOrdenamiento.size()];
        ArregloEnterosOrdenadoDescendetemente = entero.OrdenamientoBubbleSortEnteros(ArregloEnterosParaOrdenamiento);

        TiempoInicialQuickSortEnteros = System.nanoTime();

        entero.QuickSortEnteros(ArregloEnterosOrdenadoDescendetemente, 0, ArregloEnterosParaOrdenamiento.size() - 1);

        TiempoFinalQuickSortEnteros = System.nanoTime();
        TiempoTotalQuickSortEnteros = TiempoFinalQuickSortEnteros - TiempoInicialQuickSortEnteros;
        System.out.println("Tiempo de ordenamiento por QuickSort para Enteros: " + TiempoTotalQuickSortEnteros + "\n");
    

//PUNTO FLOTANTE
        
        System.out.println("\n");

        
        System.out.println("------------------------------OPERACIONES PARA FLOTANTES----------------------------" + "\n");
    
        Flotantes flotante = new Flotantes();

    //Benchmarking para evaluar el rendimiento del disco duro, mediante la escritura de conjuntos grandes de datos.
        flotante.escribirFlotantes();

        ArregloFlotantes = flotante.leerFlotantes();

    //Benchmarking para la evaluación del rendimiento de la unidad de punto flotante
        flotante.sumarFlotantes(ArregloFlotantes);

        flotante.restarFlotantes(ArregloFlotantes);

        flotante.multiplicarFlotantes(ArregloFlotantes);

        flotante.dividirFlotantes(ArregloFlotantes);

    }
}

///////////////////////////////Clase para las operaciones con Enteros///////////////////////////////
class Enteros {

    public void EscribirEnteros() {
        ArrayList<Integer> VectorINT = new ArrayList<Integer>();
        double TiempoInicial, TiempoFinal, TiempoTotal;

        TiempoInicial = System.nanoTime();
        Random rdmInteger = new Random();

        for (int i = 0; i < 5000000; i++) {
            VectorINT.add(rdmInteger.nextInt() * 5000000);
        }

        try {
            File archivoIntegers = new File("AlmacenamientoIntegers.txt");
            //Crear objeto FileWriter que sera el que nos ayude a escribir sobre archivo

            FileWriter escribirIntegers = new FileWriter(archivoIntegers, true);

            //Escribimos en el archivo con el metodo write
            for (int i = 0; i < 5000000; i++) {
                escribirIntegers.write(VectorINT.get(i) + "\r" + "\n");
            }

            //Cerramos la conexion
            escribirIntegers.close();

            TiempoFinal = System.nanoTime();
            TiempoTotal = TiempoFinal - TiempoInicial;
            System.out.println("Tiempo total de escritura para Enteros en nanosegundos: " + TiempoTotal + "\n");

        } //Si existe un problema al escribir cae aqui
        catch (Exception e) {
            System.out.println("Error al escribir");
        }

    }

//Metodo para leer los datos de un archivo de texto y almacenarlos en un array de Enteros
    public ArrayList<Integer> LeerIntegers() {

//Creamos un String que va a contener todo el texto del archivo
        String texto = "";
        ArrayList<String> arrayStrings = new ArrayList<String>();
        ArrayList<Integer> arrayIntegers = new ArrayList<Integer>();

        double TiempoInicial, TiempoFinal, TiempoTotal;
        TiempoInicial = System.nanoTime();
        try {
//Creamos un archivo FileReader que obtiene lo que tenga el archivo
            FileReader lector = new FileReader("AlmacenamientoIntegers.txt");

//El contenido de lector se guarda en un BufferedReader
            BufferedReader contenido = new BufferedReader(lector);

//Con el siguiente ciclo extraemos todo el contenido del objeto "contenido" y lo mostramos
            int i = 0;
            while ((texto = contenido.readLine()) != null) {
                arrayStrings.add(texto);
            }

            for (int j = 0; j < arrayStrings.size(); j++) {
                arrayIntegers.add(Integer.parseInt(arrayStrings.get(j)));
            }

            contenido.close();

            TiempoFinal = System.nanoTime();
            TiempoTotal = TiempoFinal - TiempoInicial;
            System.out.println("Tiempo total de lectura para Enteros en nanosegundos: " + TiempoTotal + "\n");

        } //Si se causa un error al leer cae aqui
        catch (Exception e) {
            System.out.println("Error al leer");
        }
        return arrayIntegers;

    }

//Metodo para sumar los elementos de un arreglo de datos de punto flotante
    public int SumarEnteros(ArrayList<Integer> ArregloEnteros) {

        int sum = 0;
        double TiempoInicial, TiempoFinal, TiempoTotal;

        TiempoInicial = System.nanoTime();

        for (int j = 0; j < ArregloEnteros.size(); j++) {
            sum = sum + ArregloEnteros.get(j);
        }
        TiempoFinal = System.nanoTime();
        TiempoTotal = TiempoFinal - TiempoInicial;
        System.out.println("Resultado de la suma para Enteros: " + sum);
        System.out.println("Tiempo total de suma para Enteros en nanosegundos: " + TiempoTotal + "\n");

        return sum;
    }

    //Metodo para restar los elementos de un arreglo de datos de Enteros
    public int RestarEnteros(ArrayList<Integer> ArregloEnteros) {
        int resta = ArregloEnteros.get(0);
        double TiempoInicial, TiempoFinal, TiempoTotal;

        TiempoInicial = System.nanoTime();

        for (int j = 0; j < ArregloEnteros.size(); j++) {
            resta = resta - ArregloEnteros.get(j);
        }
        TiempoFinal = System.nanoTime();
        TiempoTotal = TiempoFinal - TiempoInicial;
        System.out.println("Resultado de la resta para Enteros: " + resta);
        System.out.println("Tiempo total de resta para Enteros en nanosegundos: " + TiempoTotal + "\n");

        return resta;
    }

    //Metodo para multiplicar los elementos de un arreglo de datos Enteros
    public long MultiplicarEnteros(ArrayList<Integer> ArregloEnteros) {
        double TiempoInicial, TiempoFinal, TiempoTotal;
        long multiplicacion = ArregloEnteros.get(0);

        TiempoInicial = System.nanoTime();

        for (int j = 0; j < ArregloEnteros.size(); j++) {
            multiplicacion = multiplicacion * ArregloEnteros.get(j);
        }

        TiempoFinal = System.nanoTime();
        TiempoTotal = TiempoFinal - TiempoInicial;
        System.out.println("Resultado de la multiplicacion para Enteros " + multiplicacion);
        System.out.println("Tiempo total de multiplicacion para Enteros en nanosegundos: " + TiempoTotal + "\n");

        return multiplicacion;
    }

    //Metodo para dividir los elementos de un arreglo de datos Enteros
    public double DividirEnteros(ArrayList<Integer> ArregloEnteros) {
        double TiempoInicial, TiempoFinal, TiempoTotal;
        double division = ArregloEnteros.get(0);

        TiempoInicial = System.nanoTime();

        for (int j = 0; j < ArregloEnteros.size(); j++) {
            division = division / ArregloEnteros.get(j);
        }
        TiempoFinal = System.nanoTime();
        TiempoTotal = TiempoFinal - TiempoInicial;
        System.out.println("Resultado de la division para Enteros: " + division);
        System.out.println("Tiempo total de division para Enteros en nanosegundos: " + TiempoTotal + "\n");

        return division;

    }

    //Metodo de leer que se realiza  especialmente para el ordenamiento, ya que cuando el arreglo
    //obtenido en la lectura supera los 6000 datos falla la compilacion del quicksort
    public ArrayList<Integer> leerEnterosParaOrdenamiento() {
        //Creamos un String que va a contener todo el texto del archivo
        String texto = "";
        ArrayList<String> arrayStrings = new ArrayList<String>();
        ArrayList<Integer> arrayIntegers = new ArrayList<Integer>();

        try {
            //Creamos un archivo FileReader que obtiene lo que tenga el archivo
            FileReader lector = new FileReader("AlmacenamientoIntegers.txt");

            //El contenido de lector se guarda en un BufferedReader
            BufferedReader contenido = new BufferedReader(lector);

            //Con el siguiente ciclo extraemos todo el contenido del objeto "contenido" y lo mostramos
            for (int i = 0; i < 6000; i++) {
                texto = contenido.readLine();
                arrayStrings.add(texto);
            }

            for (int j = 0; j < arrayStrings.size(); j++) {
                arrayIntegers.add(Integer.parseInt(arrayStrings.get(j)));

            }

            contenido.close();

        } //Si se causa un error al leer cae aqui
        catch (Exception e) {
            System.out.println("Error al leer");
        }
        return arrayIntegers;
    }

    //Metodo ordenamiento en burbuja para organizar datos Enteros en forma descendente
    //Al enviarle el arreglo de datos organizado en forma descendente al quicksort(El cual ordena
    //en forma ascendente), se conseguira que este realice el proceso en el peor de los casos
    public static int[] OrdenamientoBubbleSortEnteros(ArrayList<Integer> ArregloEnteros) {

        int X[] = new int[ArregloEnteros.size()];
        for (int i = 0; i < ArregloEnteros.size(); i++) {
            X[i] = ArregloEnteros.get(i);
        }
        int temp;
        for (int i = 0; i < X.length - 1; i++) {

            for (int j = 1; j < X.length - i; j++) {
                if (X[j - 1] < X[j]) {
                    temp = X[j - 1];
                    X[j - 1] = X[j];
                    X[j] = temp;
                }
            }
        }
        return X;
    }

    public static void QuickSortEnteros(int X[], int izq, int der) {

        int pivote = X[izq]; // tomamos primer elemento como pivote
        int i = izq; // i realiza la búsqueda de izquierda a derecha
        int j = der; // j realiza la búsqueda de derecha a izquierda
        int aux;

        while (i < j) {            // mientras no se crucen las búsquedas
            while (X[i] <= pivote && i < j) {
                i++; // busca elemento mayor que pivote
            }
            while (X[j] > pivote) {
                j--;         // busca elemento menor que pivote
            }
            if (i < j) {                      // si no se han cruzado                      
                aux = X[i];                  // los intercambia
                X[i] = X[j];
                X[j] = aux;
            }
        }
        X[izq] = X[j]; // se coloca el pivote en su lugar de forma que tendremos
        X[j] = pivote; // los menores a su izquierda y los mayores a su derecha
        if (izq < j - 1) {
            QuickSortEnteros(X, izq, j - 1); // ordenamos subarray izquierdo
        }
        if (j + 1 < der) {
            QuickSortEnteros(X, j + 1, der); // ordenamos subarray derecho
        }
    }

}







///////////////////////////////Clase para las operaciones de los flotantes///////////////////////////////
class Flotantes {

    //Metodo para escribir 1 millon de datos aleatorios de punto flotante  en un archivo de texto
    public void escribirFlotantes() {

        ArrayList<Double> VectorDOUBLE = new ArrayList<Double>();
        double TiempoInicial, TiempoFinal, TiempoTotal;

        TiempoInicial = System.nanoTime();
        Random rdmDouble = new Random();

        for (int i = 0; i < 5000000; i++) {
            VectorDOUBLE.add(rdmDouble.nextDouble() * 5000000);
        }

        try {

            File archivoDoubles = new File("AlmacenamientoDoubles.txt");
            //Crear objeto FileWriter que sera el que nos ayude a escribir sobre archivo

            FileWriter escribirDoubles = new FileWriter(archivoDoubles, true);

            //Escribimos en el archivo con el metodo write
            for (int i = 0; i < 5000000; i++) {
                escribirDoubles.write(VectorDOUBLE.get(i) + "\t" + "\n");
            }

            //Cerramos la conexion
            escribirDoubles.close();

            TiempoFinal = System.nanoTime();
            TiempoTotal = TiempoFinal - TiempoInicial;
            System.out.println("Tiempo total de escritura para punto flotante en nanosegundos: " + TiempoTotal + "\n");
        } //Si existe un problema al escribir cae aqui
        catch (Exception e) {
            System.out.println("Error al escribir");
        }

    }

    //Metodo para leer los datos de un archivo de texto y almacenarlos en un array de doubles           
    public ArrayList<Double> leerFlotantes() {
        //Creamos un String que va a contener todo el texto del archivo
        String texto = "";
        ArrayList<String> arrayStrings = new ArrayList<String>();
        ArrayList<Double> arrayDoubles = new ArrayList<Double>();

        double TiempoInicial, TiempoFinal, TiempoTotal;
        TiempoInicial = System.nanoTime();
        try {
            //Creamos un archivo FileReader que obtiene lo que tenga el archivo
            FileReader lector = new FileReader("AlmacenamientoDoubles.txt");

            //El contenido de lector se guarda en un BufferedReader
            BufferedReader contenido = new BufferedReader(lector);

            //Con el siguiente ciclo extraemos todo el contenido del objeto "contenido" y lo mostramos
            int i = 0;
            while ((texto = contenido.readLine()) != null) {
                arrayStrings.add(texto);
            }

            for (int j = 0; j < arrayStrings.size(); j++) {
                arrayDoubles.add(Double.parseDouble(arrayStrings.get(j)));

            }

            contenido.close();

            TiempoFinal = System.nanoTime();
            TiempoTotal = TiempoFinal - TiempoInicial;
            System.out.println("Tiempo total de lectura para punto flotante en nanosegundos: " + TiempoTotal + "\n");

        } //Si se causa un error al leer cae aqui
        catch (Exception e) {
            System.out.println("Error al leer");
        }
        return arrayDoubles;
    }

    //Metodo para sumar los elementos de un arreglo de datos de punto flotante
    public double sumarFlotantes(ArrayList<Double> arreglo) {
        double TiempoInicial, TiempoFinal, TiempoTotal;
        double sum = 0.0;

        TiempoInicial = System.nanoTime();

        for (int j = 0; j < arreglo.size(); j++) {
            sum = sum + arreglo.get(j);
        }
        TiempoFinal = System.nanoTime();
        TiempoTotal = TiempoFinal - TiempoInicial;
        System.out.println("Resultado de la suma para punto flotante: " + sum);
        System.out.println("Tiempo total de suma para punto flotante en nanosegundos: " + TiempoTotal + "\n");

        return sum;
    }

    //Metodo para restar los elementos de un arreglo de datos de punto flotante
    public double restarFlotantes(ArrayList<Double> arreglo) {
        double TiempoInicial, TiempoFinal, TiempoTotal;
        double resta = arreglo.get(0);

        TiempoInicial = System.nanoTime();

        for (int j = 0; j < arreglo.size(); j++) {
            resta = resta - arreglo.get(j);
        }
        TiempoFinal = System.nanoTime();
        TiempoTotal = TiempoFinal - TiempoInicial;
        System.out.println("Resultado de la resta para punto flotante: " + resta);
        System.out.println("Tiempo total de resta para punto flotante en nanosegundos: " + TiempoTotal + "\n");

        return resta;
    }

    //Metodo para multiplicar los elementos de un arreglo de datos de punto flotante
    public double multiplicarFlotantes(ArrayList<Double> arreglo) {
        double TiempoInicial, TiempoFinal, TiempoTotal;
        double multiplicacion = arreglo.get(0);

        TiempoInicial = System.nanoTime();
        for (int j = 0; j < arreglo.size(); j++) {
            multiplicacion = multiplicacion * arreglo.get(j);
        }
        TiempoFinal = System.nanoTime();
        TiempoTotal = TiempoFinal - TiempoInicial;
        System.out.println("Resultado de la multiplicacion para punto flotante: " + multiplicacion);
        System.out.println("Tiempo total de multiplicacion para punto flotante en nanosegundos: " + TiempoTotal + "\n");

        return multiplicacion;
    }

    //Metodo para dividir los elementos de un arreglo de datos de punto flotante
    public double dividirFlotantes(ArrayList<Double> arreglo) {
        double TiempoInicial, TiempoFinal, TiempoTotal;
        double division = arreglo.get(0);

        TiempoInicial = System.nanoTime();
        for (int j = 0; j < arreglo.size(); j++) {
            division = division / arreglo.get(j);
        }
        TiempoFinal = System.nanoTime();
        TiempoTotal = TiempoFinal - TiempoInicial;
        System.out.println("Resultado de la division para punto flotante: " + division);
        System.out.println("Tiempo total de division para punto flotante en nanosegundos: " + TiempoTotal + "\n");

        return division;
    }

}
