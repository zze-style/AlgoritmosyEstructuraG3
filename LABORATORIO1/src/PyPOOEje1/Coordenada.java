package PyPOOEje1;

public class Coordenada {
	private double x;
	private double y;
	public Coordenada(){}
	public Coordenada(double x,double y){
		this.x=x;
		this.y=y;
	}
	public Coordenada(Coordenada c){
		this.x=c.getX();
		this.y=c.getY();
	}
	void setX(int x){
		this.x=x;
	}
	void setY(int y){
		this.y=y;
	}
	double getX(){
		return this.x;
	}
	double getY(){
		return this.y;
	}
	double distancia(Coordenada c){
		double ex=this.x-c.getX();
		double ye=this.y-c.getY();
		return Math.sqrt((Math.pow(ex, 2))+(Math.pow(ye, 2)));
	}
	static double distancia(Coordenada c1,Coordenada c2){
		double ex=c1.getX()-c2.getX();
		double ye=c1.getY()-c2.getY();
		return Math.sqrt((Math.pow(ex, 2))+(Math.pow(ye, 2)));
	}
	@Override
	public String toString(){
		return "[" + this.x + "," + this.y + "]";
	}
}