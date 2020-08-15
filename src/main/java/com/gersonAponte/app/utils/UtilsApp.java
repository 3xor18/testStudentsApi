package com.gersonAponte.app.utils;

import com.gersonAponte.app.config.AppConstans;
import com.gersonAponte.app.exceptions.CourseException;
import com.gersonAponte.app.exceptions.GlobalAppException;
import com.gersonAponte.app.exceptions.StudentException;

public class UtilsApp {

	public UtilsApp() {
	}

	public static String validationNotNullAndMaxLengt(String valueVar, String nameVar, int maxLength)
			throws GlobalAppException {
		if (valueVar == null) {
			throw new CourseException("400", "" + nameVar + "_IS_REQUIRED");
		} else {
			if (valueVar.length() <= maxLength) {
				return valueVar;
			} else {
				throw new CourseException("400", "MAXIMUN_CHARACTERS_FOR_" + nameVar + "_IS_" + maxLength);
			}
		}
	}

	public static String validarRut(String rutIn) throws GlobalAppException {
		String rutFormateado;
		String valor = rutIn.replace(".", "");
		valor = valor.replace("-", "");
		valor = valor.replace(",", "");
		valor = valor.replace(" ", "");

		int cantidad = valor.length();
		String cuerpo = valor.substring(0, cantidad - 1);
		String dv = valor.substring(cantidad - 1, cantidad);
		dv = dv.toUpperCase();
		rutIn = cuerpo + "-" + dv;

		if (cuerpo.length() < 7) {
			throw new StudentException(AppConstans.ERROR_400, "INVALID_RUT");
		}

		int suma = 0;
		int multiplo = 2;
		for (int i = 1; i <= cuerpo.length(); i++) {
			int caracter = Integer.parseInt(String.valueOf(valor.charAt(cuerpo.length() - i)));
			int index = multiplo * caracter;
			suma = suma + index;
			if (multiplo < 7) {
				multiplo = multiplo + 1;
			} else {
				multiplo = 2;
			}
		}

		int dvEsperado = suma % 11;
		dvEsperado = 11 - dvEsperado;

		if (dvEsperado == 11) {
			if (dv.equals("0")) {
				rutFormateado = cuerpo + "-" + dv;
				return rutFormateado;
			} else {
				throw new StudentException(AppConstans.ERROR_400, "INVALID_RUT");
			}
		}

		if (dvEsperado == 10) {
			if (dv.equals("K")) {
				rutFormateado = cuerpo + "-" + dv;
				return rutFormateado;
			} else {
				throw new StudentException(AppConstans.ERROR_400, "INVALID_RUT");
			}
		}

		if (dv.equals(String.valueOf(dvEsperado))) {
			rutFormateado = cuerpo + "-" + dv;
			return rutFormateado;
		} else {
			throw new StudentException(AppConstans.ERROR_400, "INVALID_RUT");
		}
	}

}
