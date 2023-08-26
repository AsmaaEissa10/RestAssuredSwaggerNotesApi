package tests;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.FileNotFoundException;

public class UsersApisTest extends BaseTest {
    String currentTime = String.valueOf(System.currentTimeMillis());
    String token;
    String token2;
    JsonPath jsonPath;

    @Test(priority = 1)
    public void Register() throws FileNotFoundException {
        String response = usersSwaggerApis.register(registerDataJson.getTestData("name"),
                        registerDataJson.getTestData("email") + currentTime + "@gmail.com"
                        , registerDataJson.getTestData("password") + currentTime)
                .then()
                .log()
                .all()
                .statusCode(201).extract().response().asString();
    }
    @Test(priority = 2)
    public void Login() throws FileNotFoundException {
        String response = usersSwaggerApis.login(registerDataJson.getTestData("email") + currentTime + "@gmail.com",
                        registerDataJson.getTestData("password") + currentTime)
                .then()
                .log()
                .all()
                .assertThat().statusCode(200).extract().response().asString();
        System.out.println(response);

        jsonPath = new JsonPath(response);
        token = jsonPath.get("data.token");
        System.out.println(token);
    }

    @Test(priority = 3)
    public void UserProfile() throws FileNotFoundException {
        String response = usersSwaggerApis.userProfile(token)
                .then().assertThat().statusCode(200)
                .extract().response().asString();
        System.out.println(response);

        jsonPath = new JsonPath(response);
        String message = jsonPath.getString("message");
        Assert.assertTrue(message.contains("Profile successful"));
    }
    @Test(priority = 4)
    public void UpdateUserProfile() throws FileNotFoundException {
        String response = usersSwaggerApis.updateUserProfile(updateDataJson.getTestData("name"),
                        updateDataJson.getTestData("phone"),
                        updateDataJson.getTestData("company"), token)
                .then()
                .log()
                .all()
                .assertThat().statusCode(200).extract().response().asString();
        System.out.println(response);

        jsonPath = new JsonPath(response);
        String message = jsonPath.getString("message");
        Assert.assertTrue(message.contains("Profile updated successful"));
        String company = jsonPath.getString("data.company");
        Assert.assertTrue(company.contains(updateDataJson.getTestData("company")));
    }

    @Test(priority = 5)
    public void ForgetPassword() throws FileNotFoundException {
        String response = usersSwaggerApis.forgetPassword(registerDataJson.getTestData("email") + currentTime + "@gmail.com",
                        token)
                .then()
                .log()
                .all()
                .assertThat().statusCode(200).extract().response().asString();
        System.out.println(response);

        jsonPath = new JsonPath(response);
        String message = jsonPath.getString("message");
        Assert.assertTrue(message.contains("Please verify by clicking on the given link"));
    }

    @Test(priority = 6)
    public void NewPassword() throws FileNotFoundException {
        String response = usersSwaggerApis.newPassword(registerDataJson.getTestData("password") + currentTime,
                        newPasswordJson.getTestData("newPassword") + currentTime, token)
                .then()
                .log()
                .all()
                .assertThat().statusCode(200).extract().response().asString();
        System.out.println(response);

        jsonPath = new JsonPath(response);
        String message = jsonPath.getString("message");
        Assert.assertTrue(message.contains("The password was successfully updated"));
    }
    @Test(priority = 7)
    public void Logout() {
        String response = usersSwaggerApis.logout(token)
                .then()
                .log()
                .all()
                .assertThat().statusCode(200).extract().response().asString();
        System.out.println(response);

        jsonPath = new JsonPath(response);
        String message = jsonPath.getString("message");
        Assert.assertTrue(message.contains("User has been successfully logged out"));
    }
    @Test(priority = 8)
    public void LoginAgainWithNewPassword() throws FileNotFoundException {
        String response = usersSwaggerApis.login(registerDataJson.getTestData("email") + currentTime + "@gmail.com",
                        newPasswordJson.getTestData("newPassword") + currentTime)
                .then()
                .log()
                .all()
                .assertThat().statusCode(200).extract().response().asString();
        System.out.println(response);

        jsonPath = new JsonPath(response);
        token2 = jsonPath.get("data.token");
        System.out.println(token2);
    }
    @Test(priority = 9)
    public void DeleteAccount() {
        String response = usersSwaggerApis.deleteAccount(token2)
                .then()
                .log()
                .all()
                .assertThat().statusCode(200).extract().response().asString();
        System.out.println(response);

        jsonPath = new JsonPath(response);
        String message = jsonPath.getString("message");
        Assert.assertTrue(message.contains("Account successfully deleted"));
    }
}