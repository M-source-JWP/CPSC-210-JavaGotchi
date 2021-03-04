package persistence;

import org.json.JSONObject;
// Writable interface taken from JsonSerializationDemo
// at https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
