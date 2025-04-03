package ca.mcmaster.se2aa4.mazerunner;

import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.cli.CommandLine;

//Main should decide whether to call the PathTraversal or Path Checker
// It does this based on the provided option in CLI
public class Main {
    private static final Options options = new Options();
    private static final CommandLineParser parser = new DefaultParser();
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        options.addOption("i", true, "File Path");
        options.addOption("p", true, "Path Checker");

        logger.info("** Starting Maze Runner");
        try {
            CommandLine cmd = parser.parse(options, args);

            if (cmd.hasOption("i") && cmd.hasOption("p")) {
                String filepath = cmd.getOptionValue("i");
                String sequence = cmd.getOptionValue("p");
                new MazeReader(filepath);
                new PathChecker(sequence).checkPath();
            } else if (cmd.hasOption("i")) {
                String filepath = cmd.getOptionValue("i");
                logger.info("Filepath:" + filepath);
                new MazeReader(filepath);
                new PathTraversal().pathTraversal(true);
            } else {
                throw new IllegalStateException("Improper use of flags");
            }
        } catch (Exception e){
            logger.error("/!\\ An error has occurred while picking parsing flags/!: ", e);
        }
    }
}
