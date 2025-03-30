import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DiaryAnalyzerApp {

    public static void out(String message){
        System.out.println(message);
    }

    public static HashMap<String, Integer> calcStats(String path){
        out(path);
        HashMap<String, Integer> dayAchievements = new HashMap<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(String.valueOf(path)));
            String line;
            while(!(line = bufferedReader.readLine()).isEmpty()){
                int separator = line.indexOf('-');
                if(separator < 0){
                    continue;
                }
                String item = line.substring(0, separator);
                String numberPart = line.substring(separator + 1);
                try {
                    int value = Integer.parseInt(numberPart.trim());
                    if (value == 1){
                        dayAchievements.put(item, 1);
                    }
                } catch (Exception e){
                    out(e.getMessage());
                    out(e.getLocalizedMessage());
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return dayAchievements;
    }

    public static void main(String[] args){
        if (args.length == 0){
            out("No directory given");
            return;
        }
        for (String s : args) {
            System.out.println(s);
        }
        try {
            HashMap<String, Integer> dayAchievements = new HashMap<>();
            Stream<Path> files = Files.list(Path.of(args[0]));
            Set<String> filePaths = files.filter(x -> !Files.isDirectory(x)).map(x -> String.valueOf(x)).filter(x -> x.endsWith(".md")).collect(Collectors.toSet());
            for (String path : filePaths) {
                HashMap<String, Integer> oneDay =  calcStats(path);
                oneDay.keySet().stream().map(x -> dayAchievements.merge(x, 1, (oldValue, newValue) -> oldValue + newValue)).collect(Collectors.toSet());
            }
            dayAchievements.keySet().stream().forEach(x -> out(x + dayAchievements.get(x)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
