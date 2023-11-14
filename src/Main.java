import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Stream;

public class Main
{
    private static String staffFile = "data/staff.txt";
    private static String dateFormat = "dd.MM.yyyy";

    public static void main(String[] args)
    {
        //ArrayList<Employee> staff = loadStaffFromFile();
        try {
            String data = getDateFromFile("data/udhsfuiasdnufu.json");
            JSONParser parser = new JSONParser();
            JSONArray array = (JSONArray) parser.parse(data);
            for (Object item : array){
                JSONObject jsonObject = (JSONObject) item;
                System.out.println(jsonObject.get("name"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static ArrayList<Employee> loadStaffFromFile()
    {
        ArrayList<Employee> staff = new ArrayList<>();
        try
        {
            List<String> lines = Files.readAllLines(Paths.get(staffFile));
            for(String line : lines)
            {
                String[] fragments = line.split("\t");
                if(fragments.length != 3) {
                    System.out.println("Wrong line: " + line);
                    continue;
                }
                staff.add(new Employee(
                    fragments[0],
                    Integer.parseInt(fragments[1]),
                    (new SimpleDateFormat(dateFormat)).parse(fragments[2])
                ));
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return staff;
    }

    private static String getDateFromFile(String path){
        StringBuilder builder = new StringBuilder(); //add Strings
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));
            for(String line : lines) {
                builder.append(line);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return builder.toString();
    }
}