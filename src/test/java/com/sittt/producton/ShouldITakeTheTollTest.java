package com.sittt.producton;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.hasText;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.matchesText;
import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Condition.or;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ShouldITakeTheTollTest {

	private static final int DEFAULT_TIMEOUT = 10000;
	
	@BeforeClass
	public static void setUp() {
		Configuration.baseUrl = "http://sittt.co.uk/";
	}
	
	@Test
	public void userCanLoginByUsername() {
	  open("/");
	  $(byNorthSpeed()).waitUntil(not(matchesText("LOADING...")), DEFAULT_TIMEOUT);
	  $(bySouthSpeed()).waitUntil(not(matchesText("LOADING...")), DEFAULT_TIMEOUT);
	  
	  $(By.className("north-duration")).should(containDigits());
	  $(By.className("south-duration")).should(containDigits());
	  $(byNorthSpeed()).shouldHave(speedText());
	  $(bySouthSpeed()).shouldHave(speedText());
	  $(By.className("south")).shouldHave(speedCssClass());
	  $(By.className("north")).shouldHave(speedCssClass());
	}

	private static Condition containDigits() {
		return matchText("[0-9]+");
	}

	private static By byNorthSpeed() {
		return By.className("north-speed");
	}
	
	private static Condition speedText() {
		return or("north-speed", hasText("slower"), hasText("faster"));
	}
	
	private static Condition speedCssClass() {
		return or("north-speed", cssClass("take-toll"),
			cssClass("maybe-take-toll"), cssClass("dont-take-toll"));
	}
	
	private static By bySouthSpeed() {
		return By.className("south-speed");
	}
}
