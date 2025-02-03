package ca.mcmaster.se2aa4.mazerunner;

import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class FileReader {

    private static Options options = new Options();
    private static CommandLineParser parser = new DefaultParser();
    private static final Logger logger = LogManager.getLogger();

    MazeArray mazeArray = new MazeArray();
    options.addOption("i", true, "File Path");
    options.addOption("p", true, "Path Checker");
    private static String filepath;
}
