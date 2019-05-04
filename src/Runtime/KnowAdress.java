package Runtime;

public class KnowAdress extends RuntimeEntity {
public EntityAdress adress;

public KnowAdress(int i, int l, int gs) {
	adress = new EntityAdress();
	this.size = i;
	this.adress.level = l;
	this.adress.displacement = gs;	
}

}
