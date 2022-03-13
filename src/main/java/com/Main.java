package com;

import java.io.IOException;
import java.security.GeneralSecurityException;
import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Main {

    /**
     * @param args the command line arguments
     * @throws org.apache.commons.cli.ParseException
     */
    public static void main(String[] args) throws ParseException {
        Options options = new Options();

        Option u = Option.builder()
            .option("u")
            .longOpt("url")
            .argName("link")
            .hasArg()
            .desc("link of a web-page")
            .build();
        options.addOption(u);

        Option s = Option.builder()
            .option("s")
            .longOpt("search")
            .argName("search-term")
            .hasArg()
            .desc("search a term")
            .build();
        options.addOption(s);

        Option h = Option.builder()
            .option("h")
            .longOpt("help")
            .desc("show help")
            .build();
        options.addOption(h);

        // define parser
        CommandLine cmd;
        CommandLineParser parser = new BasicParser();
        HelpFormatter helper = new HelpFormatter();

        try {
            cmd = parser.parse(options, args);
            if (cmd.hasOption("u")) {
                Document document = Jsoup.parse(WebRequest.makeWebRequest(cmd.getOptionValue(u)));
                System.out.println(document);
            }

            if (cmd.hasOption("s")) {
                GoogleSearch.search(cmd.getOptionValue(s));
            }

            if (cmd.hasOption("h")) {
                helper.printHelp("go2web:", options);
                System.exit(0);
            }

        } catch (ParseException e) {
            System.out.println(e.getMessage());
            helper.printHelp("go2web:", options);
            System.exit(0);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
