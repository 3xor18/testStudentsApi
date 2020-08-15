package com.gersonAponte.app.utils;

import com.gersonAponte.app.exceptions.CourseException;
import com.gersonAponte.app.exceptions.GlobalAppException;

public class UtilsApp {

	public UtilsApp() {
	}

	public static String validationNotNullAndMaxLengt(String valueVar, String nameVar,int maxLength) throws GlobalAppException{
		if (valueVar == null) {
			throw new CourseException("400", ""+nameVar+"_IS_REQUIRED");
		} else {
			if (valueVar.length() <= maxLength) {
				return valueVar;
			} else {
				throw new CourseException("400", "MAXIMUN_CHARACTERS_FOR_"+nameVar+"_IS_"+maxLength);
			}
		}
	}
	

}
