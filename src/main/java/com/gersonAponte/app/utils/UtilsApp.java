package com.gersonAponte.app.utils;

import com.gersonAponte.app.config.AppConstans;
import com.gersonAponte.app.exceptions.CourseException;
import com.gersonAponte.app.exceptions.GlobalAppException;
import com.gersonAponte.app.exceptions.StudentException;

public class UtilsApp {

	private final String DV_CERO = "0";
	private final String DV_K = "K";
	private final int DV_WAITED_10 = 10;
	private final int DV_WAITED_11 = 11;

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

	public String validadRut(String rutIn) throws GlobalAppException {
		String formatedRut;
		String value = rutIn.replace(".", "");
		value = value.replace("-", "");
		value = value.replace(",", "");
		value = value.replace(" ", "");

		int legthRut = value.length();
		String bodyRut = value.substring(0, legthRut - 1);
		String dv = value.substring(legthRut - 1, legthRut);
		dv = dv.toUpperCase();
		rutIn = bodyRut + "-" + dv;

		if (bodyRut.length() < AppConstans.MIN_LENGTH_RUT) {
			throw new StudentException(AppConstans.ERROR_400, "INVALID_LENGTH_RUT");
		}

		int sum = 0;
		int mult = 2;
		for (int i = 1; i <= bodyRut.length(); i++) {
			int characterRut = Integer.parseInt(String.valueOf(value.charAt(bodyRut.length() - i)));
			int index = mult * characterRut;
			sum = sum + index;
			if (mult < 7) {
				mult = mult + 1;
			} else {
				mult = 2;
			}
		}

		int dvWated = sum % 11;
		dvWated = 11 - dvWated;

		if (dvWated == DV_WAITED_11) {
			if (dv.equals(DV_CERO)) {
				formatedRut = bodyRut + "-" + dv;
				return formatedRut;
			} else {
				throw new StudentException(AppConstans.ERROR_400, "INVALID_RUT");
			}
		}

		if (dvWated == DV_WAITED_10) {
			if (dv.equals(DV_K)) {
				formatedRut = bodyRut + "-" + dv;
				return formatedRut;
			} else {
				throw new StudentException(AppConstans.ERROR_400, "INVALID_RUT");
			}
		}

		if (dv.equals(String.valueOf(dvWated))) {
			formatedRut = bodyRut + "-" + dv;
			return formatedRut;
		} else {
			throw new StudentException(AppConstans.ERROR_400, "INVALID_RUT");
		}
	}

}
