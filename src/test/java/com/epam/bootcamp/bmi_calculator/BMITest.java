package com.epam.bootcamp.bmi_calculator;

import static org.junit.Assert.*;

import org.junit.*;
import org.junit.rules.ExpectedException;

import com.epam.bootcamp.bmi_calculator.interfacesImplements.MetricBMI;

public class BMITest {
	
	App app;
	
	/*@Test
	public void GuessTheUnitsTest1() throws Exception{
		GuessTheUnits gtu = new GuessTheUnits(1.7,50.0);
		assertEquals(gtu.getUnitType(),"metric");
	}
	
	@Test
	public void GuessTheUnitsTest2() throws Exception{
		
	}*/
	
	@Before
	public void setup(){
		app = new App();
	}
	
	
	
	@Test
	public void BMITest1() throws Exception{
		app.setHeight(1.65);
		app.setWeight(50);
		assertEquals(app.calculateBMI(),18.5,1);
		assertEquals(app.bmiResult(),"Thinness");
	}
	
	@Test
	public void BMITest2() throws Exception{
		try{
			app.setHeight(0.0);
			app.setWeight(50.0);
			app.calculateBMI();
		}catch(Exception e){
			assertEquals(e.getMessage(),"Height is equals or less than zero.");
		}
	}
	@Test
	public void BMITestHeight2() throws Exception{ // ujemny wzrost !!!
		try {
			app.setHeight(-150.0);
			app.setWeight(50.0);
			app.calculateBMI();
		} catch(Exception e) {
			assertEquals(e.getMessage(), "Height is equals or less than zero.");
		}
	}
	
	@Test
	public void BMITest3() throws Exception{
		try{
			app.setHeight(150);
			app.setWeight(-3.0);
			app.calculateBMI();
		}catch(Exception e){
			assertEquals(e.getMessage(),"Weight is equals or less than zero.");
		}
	}
	@Test
	public void BMITestWeight2() throws Exception{ // waga = 0 !!!
		try {
			app.setHeight(150);
			app.setWeight(0);
			app.calculateBMI();
		} catch(Exception e) {
			assertEquals(e.getMessage(), "Weight is equals or less than zero.");
		}
	}
	@Test
	public void BMIBothZero() throws Exception{ // waga i wzrost = 0 to załatwia warunek z weight !!!
		try {
			app.setHeight(0);
			app.setWeight(0);
			app.calculateBMI();
		} catch(Exception e) {
			assertEquals(e.getMessage(), "Weight is equals or less than zero.");
			assertNotEquals(e.getMessage(), "Height is equals or less than zero.");

		}
	}
	@Test
	public void BMIHeight0() throws Exception{  // wzrost ustawiony na 0 !!!
		try {
			app.setHeight(0.0);
			app.setWeight(50.0);
			app.calculateBMI();
		} catch(Exception e) {
			assertEquals(e.getMessage(), "Height is equals or less than zero.");
		}
	
	}
	@Test
	public void BMIBothNegative() throws Exception{ // wzrost i waga ujemne!!
		try {
			app.setHeight(-150.0);
			app.setWeight(-50.0);
			app.calculateBMI();
		} catch(Exception e) {
			assertEquals(e.getMessage(), "Weight is equals or less than zero.");
		}
	
	}
	
	
	@Test
	public void BMITest4() throws Exception{
		try{
			app.setHeight(170);
			app.setWeight(1764);
			app.calculateBMI();
		}catch(Exception e){
			assertEquals(e.getMessage(),"Height and weight is in different metric.");
		}
	}
	
	@Test
	public void BMITest5() throws Exception{
		app.setHeight(170);
		app.setWeight(50.0);
		assertEquals(app.calculateBMI(),17.3,1);
		assertEquals(app.bmiResult(),"Thinness");
	}
	
	@Test
	public void BMITest6() throws Exception{
		app.setHeight(5.58);
		app.setWeight(1764);
		assertEquals(app.calculateBMI(),17.3,1);
		assertEquals(app.bmiResult(),"Thinness");
	}
	@Test
	public void test1() throws Exception{ // thin < 18.5 !!!
		app.setHeight(1.7);
		app.setWeight(50.0);
		assertEquals(app.calculateBMI(), 17.3, 1);
		assertEquals(app.bmiResult(), "Thinness");
	}
	@Test
	public void test3() throws Exception { // thin narrow case < 18.5 !!
		app.setHeight(150);
		app.setWeight(41.625);
		assertEquals(app.calculateBMI(), 18.5, 0);
		assertNotEquals(app.bmiResult(), "Thinness");
	}
	@Test
	public void test4() throws Exception { // normal narrow case >= 18.5
		app.setHeight(150);
		app.setWeight(41.625);
		assertEquals(app.calculateBMI(), 18.5, 0);
		assertEquals(app.bmiResult(), "Normal");
	}
	@Test
	public void test5() throws Exception { // normal narrow case <= 24.9 !!!
		app.setHeight(150);
		app.setWeight(56.025);
		assertEquals(app.calculateBMI(), 24.9, 0);
		assertEquals(app.bmiResult(), "Normal");
	}
	@Test
	public void testt() throws Exception { // narrow case na overweight < 24.9 moim zdaniem zbedny case nigdy nie wejdzie nie da sie pokryc
		app.setHeight(150);
		app.setWeight(56.025);
		assertEquals(app.calculateBMI(), 24.9, 0);
		assertNotEquals(app.bmiResult(), "Overweight");

	}
	@Test
	public void test7() throws Exception { // <= 29.9 narrow case na overweight !!!
		app.setHeight(181);
		app.setWeight(97.95539);
		assertEquals(app.calculateBMI(), 29.9, 0);
		assertEquals(app.bmiResult(), "Overweight");
	}
	@Test
	public void  test8() throws Exception { // case na Heavily overweight !!
		app.setHeight(100);
		app.setWeight(50.0);
		assertEquals(app.calculateBMI(), 50, 1);
		assertEquals(app.bmiResult(), "Heavily overweight");
	}
	@Test
	public void test9() throws Exception { // różne konfirugarcje guess units
		app.setHeight(170);
		app.setWeight(1000);
		assertEquals(app.calculateBMI(), 346, 1);
		assertEquals(app.bmiResult(), "Heavily overweight");
	}
	@Test
	public void test10() throws Exception { // różne konfiguracje guess units
		app.setHeight(5.5);
		app.setWeight(1000);
		assertEquals(app.calculateBMI(), 161, 1);
		assertEquals(app.bmiResult(), "Heavily overweight");
	}
	@Test
	public void test11() throws Exception { // różne konfiguracje guess units
		app.setHeight(3);
		app.setWeight(1000);
		assertEquals(app.calculateBMI(), 111, 1);
		assertEquals(app.bmiResult(), "Heavily overweight");
	}
	@Test
	public void test12() throws Exception { // różne konfiguracje guess units
		app.setHeight(10);
		app.setWeight(1000);
		assertEquals(app.calculateBMI(), 7030, 1);
		assertEquals(app.bmiResult(), "Heavily overweight");
	}

	
	@Test
	public void BMITest7() throws Exception{
		app.setHeight(86.7);
		app.setWeight(6349.3);
		assertEquals(app.calculateBMI(),37.19,0.5);
		assertEquals(app.bmiResult(),"Heavily overweight");
	}
	
	@Test
	public void BMITest8() throws Exception{
		app.setHeight(6.56);
		app.setWeight(220.47);
		assertEquals(app.calculateBMI(),25,0.1);
		assertEquals(app.bmiResult(),"Overweight");
	}
	
	@Test
	public void BMITest9() throws Exception{
		app.setHeight(200);
		app.setWeight(80);
		assertEquals(app.calculateBMI(),20,0.0);
		assertEquals(app.bmiResult(),"Normal");
	}

	
}