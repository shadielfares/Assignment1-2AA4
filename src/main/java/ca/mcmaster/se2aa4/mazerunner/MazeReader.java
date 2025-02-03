package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MazeReader {
    private static final Logger logger = LogManager.getLogger();
    private static String filepath;

    public MazeReader(String filepath) {
        MazeReader.filepath = filepath;
        read(filepath);
    }

    static MazeArray mazeArray = new MazeArray();

    public static void read(String filepath){
        try {
            logger.info("**** Reading the maze from file " + filepath);
            BufferedReader reader = new BufferedReader(new FileReader(filepath));
            String line;

            ArrayList<ArrayList<Character>> mazeArrayList = mazeArray.getMazeArray();
            while ((line = reader.readLine()) != null) {
                mazeArrayList.add(new ArrayList<>());

                // Get the index of the current row
                int rowIdx = mazeArrayList.size() - 1;

                for (int idx = 0; idx < line.length(); idx++) {
                    if (line.charAt(idx) == '#') {
                        logger.trace("WALL ");
                    } else if (line.charAt(idx) == ' ') {
                        logger.trace("PASS ");
                    }
                    //Fill each row index with its respective tile
                    mazeArrayList.get(rowIdx).add(line.charAt(idx));
                }
                logger.trace(System.lineSeparator());
            }
        } catch (Exception e) {
            logger.error("/!\\ An error has occurred while reading the file/!: ");
        }
    }
}
