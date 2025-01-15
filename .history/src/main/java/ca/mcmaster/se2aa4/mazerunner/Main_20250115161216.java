package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.management.RuntimeErrorException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.CommandLine;

public class Main {

    private static final Logger logger = LogManager.getLogger();
    private static Options options = new Options();

    private static String filepath;
    public static void main(String[] args) {
        private static CommandLine cmd = parser.parse(options, args);
        options.addOption("i", true, "File Path");

        logger.info("** Starting Maze Runner");
        try {
            for (int i = 0; i < args.length; i++){
                if (args[i].equals("-i")){
                    filepath = args[i+1];
                } 
            }

            logger.info("**** Reading the maze from file " + filepath);
            BufferedReader reader = new BufferedReader(new FileReader(filepath));
            String line;
            while ((line = reader.readLine()) != null) {
                for (int idx = 0; idx < line.length(); idx++) {
                    if (line.charAt(idx) == '#') {
                        logger.trace("WALL ");
                    } else if (line.charAt(idx) == ' ') {
                        logger.trace("PASS ");
                    }
                }
                logger.trace(System.lineSeparator());
            }
        } catch(Exception e) {
            logger.error("/!\\ An error has occured /!: \\" + e);
        }
        logger.info("**** Computing path");
        logger.error("PATH NOT COMPUTED");
        logger.trace("** End of MazeRunner");
    }
}
