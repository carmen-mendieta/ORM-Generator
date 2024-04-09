package py.edu.ucsa.orm.generator.entities;

import py.edu.ucsa.orm.generator.annotations.Columna;
import py.edu.ucsa.orm.generator.annotations.Entidad;
import py.edu.ucsa.orm.generator.annotations.Id;
import py.edu.ucsa.orm.generator.annotations.Transitorio;
import py.edu.ucsa.orm.generator.tipodatoenum.TiposDatos;

@Entidad(nombre = "SOCIOS")
public class Socio {

	@Id()
	@Columna(nombre = "IdSocio", nullable = false, tipoDato = TiposDatos.INT)
	private int idSocio;

	@Columna(nombre = "NombreSocio", nullable = false, tamanho = 100, tipoDato = TiposDatos.VARCHAR)
	private String nombre;

	
	@Columna(nombre = "ApellidoSocio", nullable = false, tamanho = 100, tipoDato = TiposDatos.VARCHAR)
	private String apellido;

	@Columna(nombre = "EstadoSocio", nullable = true, tamanho = 100, tipoDato = TiposDatos.BOOLEANO)
	private boolean estadoSocio;

	@Transitorio()
	private boolean eliminado;
	
	 private int edad;

}
