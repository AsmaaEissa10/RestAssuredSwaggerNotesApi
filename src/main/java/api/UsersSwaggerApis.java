package api;

import pojoBody.Users.*;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class UsersSwaggerApis {
    UserRegisterBody registerBody;
    UserLoginBody loginBody;
    UserPatchBody userUpdateBody;
    UserForgetPasswordBody userForgetPasswordBody;
    UserNewPasswordBody userNewPasswordBody;

    public Response register(String name, String email, String password){
        registerBody = new UserRegisterBody();
        registerBody.setEmail(email);
        registerBody.setName(name);
        registerBody.setPassword(password);

        return
                given()
                       .log()
                       .all()
                       .header("Content-Type","application/json")
                       .header("Accept","application/json")
                       .body(registerBody)
               .when()
                       .post("/users/register");
    }
    public Response login(String email,String password){
        loginBody = new UserLoginBody();
        loginBody.setEmail(email);
        loginBody.setPassword(password);
        return
                given()
                        .log()
                        .all()
                        .header("Content-Type","application/json")
                        .header("Accept","application/json")
                        .body(loginBody)
                  .when()
                        .post("/users/login");
    }
    public Response userProfile(String token){
        return
                given()
                        .log()
                        .all()
                        .header("x-auth-token",token)
                        .header("Content-Type","application/json")
                        .header("Accept","application/json")
                .when()
                        .get("/users/profile");
    }

    public Response updateUserProfile( String name, String phone, String company,String token){
        userUpdateBody = new UserPatchBody();
        userUpdateBody.setName(name);
        userUpdateBody.setPhone(phone);
        userUpdateBody.setCompany(company);
        return
                given()
                        .log()
                        .all()
                        .header("x-auth-token",token)
                        .header("Content-Type","application/json")
                        .header("Accept","application/json")
                .when()
                        .body(userUpdateBody)
                        .patch("/users/profile");
    }
    public Response forgetPassword( String email,String token){
        userForgetPasswordBody = new UserForgetPasswordBody();
        userForgetPasswordBody.setEmail(email);
        return
                given()
                        .log()
                        .all()
                        .header("x-auth-token",token)
                        .header("Content-Type","application/json")
                        .header("Accept","application/json")
                .when()
                        .body(userForgetPasswordBody)
                        .post("/users/forgot-password");
    }
    public Response newPassword(String currentpassword, String newPassword , String token ){
         userNewPasswordBody = new UserNewPasswordBody();
         userNewPasswordBody.setCurrentPassword(currentpassword);
         userNewPasswordBody.setNewPassword(newPassword);
        return
                given()
                        .log()
                        .all()
                        .header("x-auth-token",token)
                        .header("Content-Type","application/json")
                        .header("Accept","application/json")
                .when()
                        .body(userNewPasswordBody)
                        .post("/users/change-password");
    }
    public Response logout(String token){
        return
                given()
                        .log()
                        .all()
                        .headers("x-auth-token",token)
                        .header("Content-Type","application/json")
                        .header("Accept","application/json")
                        .when().delete("/users/logout");
    }
    public Response deleteAccount(String token2){
        return
                given()
                        .log()
                        .all()
                        .headers("x-auth-token",token2)
                        .header("Content-Type","application/json")
                        .header("Accept","application/json")
                        .when().delete("/users/delete-account");
    }
}
