module QuizMaster {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    requires java.sql;

    requires gson;
    requires lightcouch;
    //requires junit;

    opens view to javafx.graphics, javafx.fxml;
    opens controller to javafx.fxml;

    opens model.dao to gson, lightcouch, java.sql;
    opens model.entity to gson;
}