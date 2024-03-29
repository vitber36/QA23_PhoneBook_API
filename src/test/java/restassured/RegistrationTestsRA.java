package restassured;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import dto.AuthRequestDTO;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class RegistrationTestsRA {
    String endpoint="user/registration/usernamepassword";
    @BeforeMethod
    public void preCondition(){
        RestAssured.baseURI="https://contactapp-telran-backend.herokuapp.com";
        RestAssured.basePath="v1";
    }
    @Test
    public void registrationSuccess(){
        int i=(int)(System.currentTimeMillis()/1000)%3600;
        AuthRequestDTO auth=AuthRequestDTO.builder()
                .username("vitber06"+i+"@mail.ru")
                .password("1978Vit@lik")
                .build();

        String token=given()
                .body(auth)
                .contentType(ContentType.JSON)
                .when()
                .post(endpoint)
                .then()
                .assertThat().statusCode(200)
                .extract()
                .path("token");
        System.out.println(token);

    }
    @Test
    public void registrationDublicate(){

        AuthRequestDTO auth=AuthRequestDTO.builder()
                .username("vitber06@mail.ru")
                .password("1978Vit@lik")
                .build();

        given()
                .body(auth)
                .contentType(ContentType.JSON)
                .when()
                .post(endpoint)
                .then()
                .assertThat().statusCode(409)
                .assertThat().body("message",containsString("User already exists"));

    }

    @Test
    public void registrationWrongEmail(){

        AuthRequestDTO auth=AuthRequestDTO.builder()
                .username("vitber06mail.ru")
                .password("1978Vit@lik")
                .build();

        given()
                .body(auth)
                .contentType(ContentType.JSON)
                .when()
                .post(endpoint)
                .then()
                .assertThat().statusCode(400)
                .assertThat().body("message.username",containsString("must be a well-formed email address"));

}
}
