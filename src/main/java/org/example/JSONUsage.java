package src.main.java.org.example;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.google.gson.stream.JsonWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Diese Klasse ist der Controller für das Programm. Diese Klasse ist auch austauschbar, je nachdem
 * welche Datenquelle verwendet werden soll. In diesem Fall wird eine JSON-Datei verwendet.
 * @author Dawid Birgauan 5cHIT
 * @version 2023-10-18
 */
public class JSONUsage {

    /**
     * Diese Methode holt sich die Daten aus der JSON-Datei und speichert die Statistiken in die JSON-Datei.
     */
    public JSONUsage() {
        try {
            //Holt sich die Daten aus der JSON-Datei
            FileReader fileReader= new FileReader("daten.json");
            JsonParser jsonParser= new JsonParser();
            JsonObject jsonData= jsonParser.parse(fileReader).getAsJsonObject();
            Rechtschreibtrainer rt= new Rechtschreibtrainer();

            //Holt sich die Statistiken aus der JSON-Datei
            JsonObject statistik= jsonData.getAsJsonObject("statistik");
            int richtig= statistik.get("richtig").getAsInt();
            int falsch= statistik.get("falsch").getAsInt();

            //Holt sich die einzelnen Einträge aus der JSON-Datei
            JsonArray entries= jsonData.getAsJsonArray("entries");
            for(int i=0; i< entries.size(); i++) {
                JsonObject entry= entries.get(i).getAsJsonObject();
                String name= entry.get("name").getAsString();
                String url= entry.get("url").getAsString();
                int[] eingabe= rt.openFrame(name, url, "Bitte erraten Sie das Bild!: ");
                richtig+= eingabe[0];
                falsch+= eingabe[1];
            }

            //Speichert die Statistiken in die JSON-Datei
            statistik.add("richtig", new JsonPrimitive(richtig));
            statistik.add("falsch", new JsonPrimitive(falsch));

            jsonData.add("statistik", statistik);

            fileReader.close();

            //Scheibt die JSON-Datei neu
            FileWriter fileWriter= new FileWriter("daten.json");
            JsonWriter jsonWriter= new JsonWriter(fileWriter);
            jsonWriter.setIndent("  ");
            jsonWriter.jsonValue(jsonData.toString());
            jsonWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
