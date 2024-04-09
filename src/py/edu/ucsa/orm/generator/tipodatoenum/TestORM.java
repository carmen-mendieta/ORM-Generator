package py.edu.ucsa.orm.generator.tipodatoenum;

import py.edu.ucsa.orm.generator.ORM.ProcesadorORM;
import py.edu.ucsa.orm.generator.entities.Socio;

public class TestORM {
	public static void main(String[] args) {
		Socio socio = new Socio();
		ProcesadorORM pro = new ProcesadorORM();
		pro.procesar(socio);
		

	}

}
