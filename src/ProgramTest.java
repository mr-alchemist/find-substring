import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ProgramTest {

	@Test
	void searchBMFoundCorrect() {
		Program program = new Program();
		assertEquals(5, program.searchBM("ехал грека через реку", "грека"));
		assertEquals(0, program.searchBM("корабли лавировали, лавировали, да так и не вылавировали", "корабли"));
		assertEquals(8, program.searchBM("корабли лавировали, лавировали, да так и не вылавировали", "лавировали"));
		assertEquals(44, program.searchBM("корабли лавировали, лавировали, да так и не вылавировали", "вылавировали"));
		assertEquals(10, program.searchBM("quod erat demonstrandum", "demonstrandum"));
	}
	
	@Test
	void searchBMNotFound() {
		Program program = new Program();
		assertEquals(-1, program.searchBM("ехал грека через реку", "Грека"));
		assertEquals(-1, program.searchBM("корабли лавировали, лавировали, да так и не вылавировали", "коррабли"));
		assertEquals(-1, program.searchBM("корабли лавировали, лавировали, да так и не вылавировали", "Лавировали"));
		assertEquals(-1, program.searchBM("корабли лавировали, лавировали, да так и не вылавировали", " вылавировали "));
		assertEquals(-1, program.searchBM("quod erat demonstrandum", "demonstrandum!"));
	}
	
	@Test
	void searchKMPFoundCorrect() {
		Program program = new Program();
		assertEquals(5, program.searchKMP("ехал грека через реку", "грека"));
		assertEquals(0, program.searchKMP("корабли лавировали, лавировали, да так и не вылавировали", "корабли"));
		assertEquals(8, program.searchKMP("корабли лавировали, лавировали, да так и не вылавировали", "лавировали"));
		assertEquals(44, program.searchKMP("корабли лавировали, лавировали, да так и не вылавировали", "вылавировали"));
		assertEquals(10, program.searchKMP("quod erat demonstrandum", "demonstrandum"));
	}
	
	@Test
	void searchKMPNotFound() {
		Program program = new Program();
		assertEquals(-1, program.searchKMP("ехал грека через реку", "Грека"));
		assertEquals(-1, program.searchKMP("корабли лавировали, лавировали, да так и не вылавировали", "коррабли"));
		assertEquals(-1, program.searchKMP("корабли лавировали, лавировали, да так и не вылавировали", "Лавировали"));
		assertEquals(-1, program.searchKMP("корабли лавировали, лавировали, да так и не вылавировали", " вылавировали "));
		assertEquals(-1, program.searchKMP("quod erat demonstrandum", "demonstrandum!"));
	}
	
	@Test
	void searchKMPAllFoundCorrect() {
		Program program = new Program();
		assertArrayEquals(new int[]{5, 29, 53}, program.searchKMPAll("ехал грека через реку, видит грека в реке рак, сунул грека руку в реку", "грека"));
		assertArrayEquals(new int[]{0}, program.searchKMPAll("корабли лавировали, лавировали, да так и не вылавировали", "корабли"));
	}
	
	@Test
	void searchKMPAllNotFound() {
		Program program = new Program();
		assertArrayEquals(new int[]{}, program.searchKMPAll("ехал грека через реку, видит грека в реке рак, сунул грека руку в реку", "Грека"));
		assertArrayEquals(new int[]{}, program.searchKMPAll("quod erat demonstrandum", "demonstrandum!"));
	}
	
	
	
}
