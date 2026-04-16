package api.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;

/**
 * ReusableResponseSpec class to create reusable ResponseSpecification
 */
public class ReusableResponseSpec {

    /**
     * Build response spec for successful requests (200 OK)
     */
    public static ResponseSpecification buildSuccessResponseSpec() {
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();
    }

    /**
     * Build response spec for created resources (201 Created)
     */
    public static ResponseSpecification buildCreatedResponseSpec() {
        return new ResponseSpecBuilder()
                .expectStatusCode(201)
                .expectContentType(ContentType.JSON)
                .build();
    }

    /**
     * Build response spec for bad requests (400)
     */
    public static ResponseSpecification buildBadRequestResponseSpec() {
        return new ResponseSpecBuilder()
                .expectStatusCode(400)
                .build();
    }

    /**
     * Build response spec for unauthorized requests (401)
     */
    public static ResponseSpecification buildUnauthorizedResponseSpec() {
        return new ResponseSpecBuilder()
                .expectStatusCode(401)
                .build();
    }

    /**
     * Build response spec for forbidden requests (403)
     */
    public static ResponseSpecification buildForbiddenResponseSpec() {
        return new ResponseSpecBuilder()
                .expectStatusCode(403)
                .build();
    }

    /**
     * Build response spec for not found (404)
     */
    public static ResponseSpecification buildNotFoundResponseSpec() {
        return new ResponseSpecBuilder()
                .expectStatusCode(404)
                .build();
    }

    /**
     * Build response spec for conflict (409)
     */
    public static ResponseSpecification buildConflictResponseSpec() {
        return new ResponseSpecBuilder()
                .expectStatusCode(409)
                .build();
    }

    /**
     * Build response spec for internal server error (500)
     */
    public static ResponseSpecification buildInternalServerErrorResponseSpec() {
        return new ResponseSpecBuilder()
                .expectStatusCode(500)
                .build();
    }

    /**
     * Build response spec for custom status code
     */
    public static ResponseSpecification buildResponseSpecWithStatusCode(int statusCode) {
        return new ResponseSpecBuilder()
                .expectStatusCode(statusCode)
                .expectContentType(ContentType.JSON)
                .build();
    }

    /**
     * Build response spec for specific status code and content type
     */
    public static ResponseSpecification buildCustomResponseSpec(int statusCode, ContentType contentType) {
        return new ResponseSpecBuilder()
                .expectStatusCode(statusCode)
                .expectContentType(contentType)
                .build();
    }
}
