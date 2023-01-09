package org.example;


import org.apache.commons.cli.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class App {
    private static final Options options = new Options();
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        setOptions();

        HelpFormatter formatter = new HelpFormatter();
        CommandLineParser parser = new DefaultParser();

        try {
            String textSearch;
            String option = "S";
            CommandLine cmd = parser.parse(options, args);
            String filePath = cmd.getOptionValue("f");

            textSearch = cmd.getOptionValue(option);

            if (textSearch == null){
                option = "s";
                textSearch = cmd.getOptionValue(option);
            }

            Pattern pattern = convertRegEx(textSearch, option);

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            Parser xmlParser = new Parser(pattern);
            try{
                saxParser.parse(filePath, xmlParser);
            }catch (SAXException e){
                System.out.println(e.getMessage());
            }

        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("User Profile Info", options);
            System.exit(1);
        }
    }
    private static Pattern convertRegEx(String textSearch, String option){
        String patternText = "(?s).*";

        if (textSearch == null){
            return Pattern.compile(patternText);
        }

        if (option.equals("S")){
            patternText = textSearch.replace("'", "");
            return Pattern.compile(patternText);
        }

        if (option.equals("s") && textSearch.contains("'")){
            patternText = textSearch.replace("'", "");
            patternText = Arrays.stream(patternText.split("\\*\\."))
                    .filter(a-> !a.equals("."))
                    .collect(Collectors.joining());
            patternText = "(?s).*%s$".formatted(patternText);
        }else{
            patternText = "^%s$".formatted(textSearch);
        }

        return Pattern.compile(patternText);
    }
    private static void setOptions(){

        Option optionFile = Option.builder()
                .option("f")
                .longOpt("file")
                .hasArg(true)
                .desc("Expression for filename search.")
                .required()
                .build();

        Option optionSearch = Option.builder()
                .option("s")
                .longOpt("search")
                .hasArg(true)
                .desc("Expression for search name of file.")
                .optionalArg(true)
                .build();

        Option optionSearchExtend = Option.builder()
                .option("S")
                .longOpt("search")
                .hasArg(true)
                .desc("Expression for extend search name of file.")
                .optionalArg(true)
                .build();

        options.addOption(optionFile);
        options.addOption(optionSearch);
        options.addOption(optionSearchExtend);
    }
}
