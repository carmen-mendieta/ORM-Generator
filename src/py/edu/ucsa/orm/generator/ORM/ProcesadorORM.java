package py.edu.ucsa.orm.generator.ORM;

import java.lang.reflect.Field;
import java.util.Objects;

import py.edu.ucsa.orm.generator.annotations.Columna;
import py.edu.ucsa.orm.generator.annotations.Entidad;
import py.edu.ucsa.orm.generator.annotations.Id;
import py.edu.ucsa.orm.generator.annotations.Transitorio;
import py.edu.ucsa.orm.generator.exceptions.ClavePrimariaRequeridaException;
import py.edu.ucsa.orm.generator.tipodatoenum.TiposDatos;

public class ProcesadorORM {

	public void procesar(Object o) {
		if (Objects.nonNull(o) && o.getClass().isAnnotationPresent(Entidad.class)) {
			StringBuilder sb = new StringBuilder("CREATE TABLE ");
			Entidad entidad = o.getClass().getAnnotation(Entidad.class);
			sb.append(entidad.nombre() + " (\n");
			try {

				if (TieneID(o)) {
					this.procesarColumnas(o, sb);
				}else {
					throw new ClavePrimariaRequeridaException("La tabla no tiene clave primaria");
				}

			} catch (IllegalArgumentException | IllegalAccessException | ClavePrimariaRequeridaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	private void procesarColumnas(Object o, StringBuilder sb) throws IllegalArgumentException, IllegalAccessException {

		Field[] fields = o.getClass().getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);

			if (NoTieneAnnotations(field)) {

				sb.append("\t" + field.getName() + " VARCHAR(100), \n");
			} else {
				if (!field.isAnnotationPresent(Transitorio.class)) {
					if (field.isAnnotationPresent(Id.class)
							&& field.isAnnotationPresent(Columna.class)
							&& field.getAnnotation(Columna.class).nullable()) {

						throw new IllegalArgumentException("El ID  no puede ser nullable ");

					}

					if (field.isAnnotationPresent(Columna.class)) {
						Columna colum = field.getAnnotation(Columna.class);
						sb.append("\t");
						sb.append(colum.nombre() + " ");
						sb.append(colum.tipoDato().toString() + " ");
						if (colum.tipoDato() == TiposDatos.VARCHAR) {
							sb.append("(" + colum.tamanho() + ") ");
						}
						if (!colum.nullable()) {
							sb.append("NOT NULL");
						}

						if (field.isAnnotationPresent(Id.class)) {
							sb.append(" PRIMARY KEY");
						}
						sb.append(", \n");

					}
				}
			}

		}

		sb.delete(sb.length() - 3, sb.length());

		sb.append(" );");
		System.out.println(sb.toString());
	}

	private boolean NoTieneAnnotations(Field field) {
		return !(field.isAnnotationPresent(Id.class) || field.isAnnotationPresent(Columna.class)
				|| field.isAnnotationPresent(Transitorio.class));
	}

	private boolean TieneID(Object o) {
		Field[] fields = o.getClass().getDeclaredFields();
		for (Field field : fields) {
			if (field.isAnnotationPresent(Id.class)) {
				return true;
			}
		}
		return false;

	}

}
