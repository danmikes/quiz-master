package model.dao;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import model.entity.Answer;
import java.util.List;

public class AnswerDAO {
    private CouchDBAccess cdb;

        // constructor(s)
    public AnswerDAO (CouchDBAccess cdb) {
        super();
        this.cdb = cdb;
    }

    // opslaan van resultaten in CouchDB per vraag
    public void saveAnswer (Answer answer) {
        Gson gson = new Gson();
        String jsonstring = gson.toJson(answer);
        System.out.println(jsonstring);
        JsonParser parser = new JsonParser();
        JsonObject jsonobject = parser.parse(jsonstring).getAsJsonObject();
        cdb.saveDocument(jsonobject);
    }

/*
    // opslaan van resultaten in CouchDB per einde quiz
    public void saveAnswers (List<Answer> answer) {
        Gson gson = new Gson();
        String jsonstring = gson.toJson(answer);
        System.out.println(jsonstring);
        JsonParser parser = new JsonParser();
        JsonObject jsonobject = parser.parse(jsonstring).getAsJsonObject();
        cdb.saveDocument(jsonobject);
    }

*/


}
