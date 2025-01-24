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

import java.util.ArrayList;

public class Main {

    private static final Logger logger = LogManager.getLogger();
    private static Options options = new Options();
    private static CommandLineParser parser = new DefaultParser();

    private static String filepath;

    public static void main(String[] args) {

        options.addOption("i", true, "File Path");

        logger.info("** Starting Maze Runner");
        try {
            CommandLine cmd = parser.parse(options, args);

            if (cmd.hasOption("i")) {
                filepath = cmd.getOptionValue("i");
            }

            logger.info("**** Reading the maze from file " + filepath);
            BufferedReader reader = new BufferedReader(new FileReader(filepath));
            String line;

            PathFinder pathFinder = new PathFinder();

            ArrayList<ArrayList<Character>> mazeArray = pathFinder.getMazeArray();
            while ((line = reader.readLine()) != null) {
                mazeArray.add(new ArrayList<>());

                // Get the index of the current row
                int rowIdx = mazeArray.size() - 1;

                for (int idx = 0; idx < line.length(); idx++) {
                    if (line.charAt(idx) == '#') {
                        logger.trace("WALL ");
                    } else if (line.charAt(idx) == ' ') {
                        logger.trace("PASS ");
                    }
                    mazeArray.get(rowIdx).add(line.charAt(idx));
                }
                logger.trace(System.lineSeparator());
            }

            pathFinder.setMazeArray(mazeArray);
            pathFinder.findEntry();
        for (int i = 0; i < mazeArray.size(); i++){
            System.out.println(mazeArray.get(i));
        }

        } catch (Exception e) {
            logger.error("/!\\ An error has occured /!: \\" + e);
        }
        logger.info("**** Computing path");
        logger.error("PATH NOT COMPUTED");
        logger.info("** End of MazeRunner");
    }
}
