package com.exemple.app;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

class UserApiTest {

    @Test
    void shouldGetUserById() {
        given()
            .baseUri("https://jsonplaceholder.typicode.com")
        .when()
            .get("/users/1")
        .then()
            .statusCode(200)
            .body("id", equalTo(1))
            .body("name", notNullValue())
            .body("email", notNullValue());
    }

    @Test
    void shouldCreatePost() {
        given()
            .baseUri("https://jsonplaceholder.typicode.com")
            .contentType("application/json")
            .body("""
                {
                    "title": "foo",
                    "body": "bar",
                    "userId": 1
                }
                """)
        .when()
            .post("/posts")
        .then()
            .statusCode(201)
            .body("title", equalTo("foo"));
    }
}
