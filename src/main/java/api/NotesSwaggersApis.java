package api;

import pojoBody.Notes.CreateNoteBody;
import pojoBody.Notes.PatchNoteBody;
import pojoBody.Notes.PutNoteBody;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class NotesSwaggersApis {
    CreateNoteBody createNoteBody;
    PatchNoteBody patchNoteBody;
    PutNoteBody putNoteBody;
    public Response createNote(String title, String description, String category, String token){
        createNoteBody = new CreateNoteBody();
        createNoteBody.setTitle(title);
        createNoteBody.setDescription(description);
        createNoteBody.setCategory(category);

        return
                given()
                        .log()
                        .all()
                        .header("x-auth-token",token)
                        .header("Content-Type","application/json")
                        .header("Accept","application/json")
                        .body(createNoteBody)
                .when().post("/notes");
    }
    public Response putNote(String title, String description,Boolean completed,String category,String token, String id){
        putNoteBody = new PutNoteBody();
        putNoteBody.setTitle(title);
        putNoteBody.setDescription(description);
        putNoteBody.setCompleted(completed);
        putNoteBody.setCategory(category);

        return RestAssured
                .given()
                        .log()
                        .all()
                        .pathParam("id",id)
                        .headers("x-auth-token",token)
                        .header("Content-Type","application/json")
                        .header("Accept","application/json")
                        .body(putNoteBody)
                .when()
                        .put("/notes/{id}");
    }
    public Response patchNote(Boolean completed,String token, String id){
        patchNoteBody = new PatchNoteBody();
        patchNoteBody.setCompleted(completed);

        return RestAssured
                .given()
                        .log()
                        .all()
                        .pathParam("id",id)
                        .headers("x-auth-token",token)
                        .header("Content-Type","application/json")
                        .header("Accept","application/json")
                        .body(patchNoteBody)
                .when()
                        .patch("/notes/{id}");
    }
    public Response deleteNote(String id, String token){

        return
                given()
                        .log()
                        .all()
                        .pathParam("id",id)
                        .headers("x-auth-token",token)
                        .header("Content-Type","application/json")
                        .header("Accept","application/json")
                .when()
                        .delete("/notes/{id}");
    }

}
