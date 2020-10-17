package tech.jaykay12;

import org.springframework.stereotype.Component;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class MUIdentifier {
    static Set<String> measurementUnits = new HashSet<>();

    static {
        List<String> measurements = Arrays.asList("m","g","kg","metric ton","kva","amp","cm","inch","rs","kv");
        for(String unit : measurements) {
            measurementUnits.add(unit);
        }
    }

    public Map<String,String> identifyMU(String searchString) {

        Map<String, String> resultMU = new HashMap<>();
        String MU = "";
        List<String> MUs = new ArrayList<>();

        if(Pattern.compile("\\d").matcher(searchString).find()) {
            List<String> words = new LinkedList<>(Arrays.asList(searchString.split("\\s+|\\d+")));
            words.removeAll(Collections.singleton(""));

            int numWords = words.size();
            String word1 = "", word2 = "";

            for(int i=0; i<numWords; i++) {
                if(measurementUnits.contains(words.get(i)) || (i+1<numWords && measurementUnits.contains(words.get(i)+" "+words.get(i+1))) ) {

                    if(measurementUnits.contains(words.get(i)))
                        word1 = words.get(i);
                    else
                        word1 = words.get(i)+" "+words.get(i+1);


                    for (int j=0; j<numWords; j++) {
                        if (measurementUnits.contains(words.get(j)) || (j+1 < numWords && measurementUnits.contains(words.get(j) + " " + words.get(j+1)))) {

                            if(measurementUnits.contains(words.get(j)))
                                word2 = words.get(j);
                            else
                                word2 = words.get(j)+" "+words.get(j+1);

                            Matcher match = Pattern.compile(
                                    "(^((\\d+|\\d+[\\.\\/]+\\d+)(\\s)?(" + word1 + ")?(\\s)?([-*&x]|to|and)+(\\s)?)*(\\d+|\\d+[\\.\\/]+\\d+)\\s?" + word2 + "\\s(?!([-*&x]|to|and))|" +
                                            "\\s((\\d+|\\d+[\\.\\/]+\\d+)(\\s)?(" + word1 + ")?(\\s)?([-*&x]|to|and)+(\\s)?)*(\\d+|\\d+[\\.\\/]+\\d+)\\s?" + word2 + "\\s(?!([-*&x]|to|and))|" +
                                            "\\s((\\d+|\\d+[\\.\\/]+\\d+)(\\s)?(" + word1 + ")?(\\s)?([-*&x]|to|and)+(\\s)?)*(\\d+|\\d+[\\.\\/]+\\d+)\\s?" + word2 + "(\\s)?$)"
                            ).matcher(searchString);

                            if (match.find()) {
                                MU = match.group(1).trim();
                                MUs.add(MU);
                                searchString = searchString.replace(" "+MU, "").replace(MU+" ", "");
                            }

                        }
                    }
                }
            }
        }

        if(!MUs.isEmpty()) {
            MU = MUs.get(0);
        }

        resultMU.put("mu", MU);
        searchString = searchString.replaceAll("[-/\"]", " ")
                .replaceAll("\\s+", " ")
                .replaceAll("(^\\s|\\s$)", "");

        resultMU.put("string", searchString);
        return resultMU;
    }
}
