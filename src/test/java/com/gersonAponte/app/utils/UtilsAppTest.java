package com.gersonAponte.app.utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.gersonAponte.app.exceptions.GlobalAppException;
import com.gersonAponte.app.exceptions.StudentException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UtilsAppTest {

	private UtilsApp utilsApp;

	@BeforeEach
	public void setUp() {
		utilsApp = new UtilsApp();
	}

	@AfterEach
	public void tearDown() {
		utilsApp = null;
	}

	@Test
	public void dvError() {
		assertThrows(StudentException.class, () -> utilsApp.validadRut("26671572-3"), "Invalid Rut");
	}

	@Test
	public void wrongRut() {
		assertThrows(StudentException.class, () -> utilsApp.validadRut("11111"), "Invalid Rut");
	}

	@Test
	public void rutWithDapWithHyphen() throws GlobalAppException {
		assertEquals("26671572-2", utilsApp.validadRut("26.671.572-2"));
	}

	@Test
	public void rutWithDapWithOutHyphen() throws GlobalAppException {
		assertEquals("26671572-2", utilsApp.validadRut("26.671.5722"));
	}

	@Test
	public void rutWithCommaWithOutHyphen() throws GlobalAppException {
		assertEquals("26671572-2", utilsApp.validadRut("26,671,5722"));
	}

	@Test
	public void rutWithCommaWithHyphen() throws GlobalAppException {
		assertEquals("26671572-2", utilsApp.validadRut("26,671,572-2"));
	}

	@Test
	public void rutWithOutDapWithHyphen() throws GlobalAppException {
		assertEquals("26671572-2", utilsApp.validadRut("26671572-2"));
	}
	
	@Test
	public void rutWithOutDapWithOutHyphen() throws GlobalAppException {
		assertEquals("26671572-2", utilsApp.validadRut("266715722"));
	}
}
