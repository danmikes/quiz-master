package model.dao;

import org.lightcouch.CouchDbClient;
import org.lightcouch.CouchDbProperties;
import org.lightcouch.Response;

import com.google.gson.JsonObject;

public class CouchDBAccess {

    private CouchDbClient client;

    public void setupConnection() {
        CouchDbProperties properties = new CouchDbProperties();

        //Set the database name
        properties.setDbName("antwoorden_quizmaster");

        //Create the database if it didn't already exist
        properties.setCreateDbIfNotExist(true);

        //Server is running on localhost
        properties.setHost("localhost");

        //Set the port CouchDB is running on (5984)
        properties.setPort(5984);

        properties.setProtocol("http");

        //uncomment to set the username
        // properties.setUsername("username");
        //uncomment to set the password
        // properties.setPassword("password");
        //Create the database client and setup the connection with given
        //properties
        client = new CouchDbClient(properties);
        if (client == null) {
            System.out.println("***** Couch client is null");
        } else {
            System.out.println("***** Couch clientsetup OK");
        }
    }

    public String saveDocument(JsonObject document) {
        Response response = client.save(document);
        return response.getId();
    }
}
