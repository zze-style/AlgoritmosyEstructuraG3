package generic;
import java.util.*;

public class Bolsa <T> implements Iterable <T>{
	private ArrayList<T> lista= new ArrayList<T>();
	private int tope;
	public Bolsa(int tope){
		this.tope=tope;
	}
	public void add(T objeto){
		if(lista.size()<=tope){
			lista.add(objeto);
		}else{
			throw new RuntimeException("no cabe mas");
		}
	}
	public Iterator<T> iterator(){
		return lista.iterator();
	}
}
