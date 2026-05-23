package api.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;

public class ReusableResponseSpec {


    public static ResponseSpecification buildSuccessResponseSpec() {
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();
    }


    public static ResponseSpecification buildCreatedResponseSpec() {
        return new ResponseSpecBuilder()
                .expectStatusCode(201)
                .expectContentType(ContentType.JSON)
                .build();
    }


    public static ResponseSpecification buildBadRequestResponseSpec() {
        return new ResponseSpecBuilder()
                .expectStatusCode(400)
                .build();
    }


    public static ResponseSpecification buildUnauthorizedResponseSpec() {
        return new ResponseSpecBuilder()
                .expectStatusCode(401)
                .build();
    }


    public static ResponseSpecification buildForbiddenResponseSpec() {
        return new ResponseSpecBuilder()
                .expectStatusCode(403)
                .build();
    }


    public static ResponseSpecification buildNotFoundResponseSpec() {
        return new ResponseSpecBuilder()
                .expectStatusCode(404)
                .build();
    }


    public static ResponseSpecification buildConflictResponseSpec() {
        return new ResponseSpecBuilder()
                .expectStatusCode(409)
                .build();
    }


    public static ResponseSpecification buildInternalServerErrorResponseSpec() {
        return new ResponseSpecBuilder()
                .expectStatusCode(500)
                .build();
    }


    public static ResponseSpecification buildResponseSpecWithStatusCode(int statusCode) {
        return new ResponseSpecBuilder()
                .expectStatusCode(statusCode)
                .expectContentType(ContentType.JSON)
                .build();
    }


    public static ResponseSpecification buildCustomResponseSpec(int statusCode, ContentType contentType) {
        return new ResponseSpecBuilder()
                .expectStatusCode(statusCode)
                .expectContentType(contentType)
                .build();
    }
}

