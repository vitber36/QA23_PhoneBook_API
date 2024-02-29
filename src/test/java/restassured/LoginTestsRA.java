package restassured;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import dto.AuthRequestDTO;
import dto.AuthResponseDTO;
import dto.ErrorDTO;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class LoginTestsRA {
    String endpoint="user/login/usernamepassword";

    @BeforeMethod
    public void preCondition(){
        RestAssured.baseURI="https://contactapp-telran-backend.herokuapp.com";
        RestAssured.basePath="v1";
    }

    @Test
    public void loginSuccess(){
        AuthRequestDTO auth=AuthRequestDTO.builder()
                .username("vitber06@mail.ru")
                .password("1978Vit@lik")
                .build();

        AuthResponseDTO responseDTO=given()
                .body(auth)
                .contentType("application/json")
                .when()
                .post(endpoint)
                .then()
                .assertThat().statusCode(200)
                .extract()
                .response()
                .as(AuthResponseDTO.class);

        System.out.println(responseDTO.getToken());
    }

    @Test
    public void loginWrongEmail(){
        AuthRequestDTO auth=AuthRequestDTO.builder()
                .username("vitber06mail.ru")
                .password("1978Vit@lik")
                .build();

        ErrorDTO errorDTO=given()
                .body(auth)
                .contentType(ContentType.JSON)
                .when()
                .post(endpoint)
                .then()
                .assertThat()
                .statusCode(401)
                .extract()
                .response()
                .as(ErrorDTO.class);
        Assert.assertEquals(errorDTO.getMessage(),"Login or Password incorrect");
    }

    @Test
    public void loginWrongEmailFormat(){
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
                .assertThat().statusCode(401)
                .assertThat().body("message",containsString("Login or Password incorrect"))
                .assertThat().body("path",equalTo("/v1/user/login/usernamepassword"));
    }
}
