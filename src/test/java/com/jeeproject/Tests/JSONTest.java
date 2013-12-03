package com.jeeproject.Tests;

import static com.jayway.restassured.RestAssured.expect;
import static org.hamcrest.Matchers.equalTo;

 
import org.junit.Test;
 
public class JSONTest {
	@Test
	public void testResponseSuccess() {               // nécessite l'exécution du fichier sql. 
		expect().                                     // vérifie si les réponses JSON de l'api correspondent bien aux données de la bdd
			body("get(0).category", equalTo("rock")).
			body("get(0).title", equalTo("Bankrupt")).
			body("get(0).year", equalTo("2013")).
			body("get(0).artist", equalTo("Phoenix")).
		//	body("birthday", equalTo("1970-01-16T07:56:49.871+01:00")).
		when().
			get("/peguno-project/rest/api/albums/byalbumtitle?name=bankrupt");
		expect().
		body("get(0).category", equalTo("rock")).
		body("get(0).title", equalTo("Wolfgang Amadeus")).
		body("get(0).year", equalTo("2009")).
		body("get(0).artist", equalTo("Phoenix")).
	//	body("birthday", equalTo("1970-01-16T07:56:49.871+01:00")).
	when().
		get("/peguno-project/rest/api/albums/bysongtitle?name=lisztomania");
		expect().
		body("get(0).category", equalTo("rap")).
		body("get(0).title", equalTo("The Marshall Mathers LP")).
		body("get(0).year", equalTo("2000")).
		body("get(0).artist", equalTo("Eminem")).
		body("get(1).category", equalTo("rap")).
		body("get(1).title", equalTo("Recovery")).
		body("get(1).year", equalTo("2010")).
		body("get(1).artist", equalTo("Eminem")).
		body("get(2).category", equalTo("rap")).
		body("get(2).title", equalTo("The Marshall Mathers LP2")).
		body("get(2).year", equalTo("2013")).
		body("get(2).artist", equalTo("Eminem")).
	when().
     get("/peguno-project/rest/api/albums/byauthor?name=eminem");
	}
 
	@Test
	public void testResponseFound() {
		expect().statusCode(200).when().get("/peguno-project/rest/api/albums/byalbumtitle?name=bankrupt");
		expect().statusCode(200).when().get("/peguno-project/rest/api/albums/byalbumtitle?name=lisztomania");
		expect().statusCode(200).when().get("/peguno-project/rest/api/albums/byauthor?name=eminem");
	}
}