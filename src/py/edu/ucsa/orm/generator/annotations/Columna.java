package py.edu.ucsa.orm.generator.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import py.edu.ucsa.orm.generator.tipodatoenum.TiposDatos;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Columna {
	String nombre();

	int tamanho() default 0;

	boolean nullable();

	TiposDatos tipoDato();

}
