package ooga.model.dataloaders;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import java.io.FileNotFoundException;

class CSV_ParserTest extends DukeApplicationTest {

  //TODO - WRITE THIS TEST

    @Test
    public void testPathRead() throws FileNotFoundException {
        CSV_Parser csv_parser = new CSV_Parser();
        assertTrue(csv_parser.read("data/gamepath_01.csv") != null);

    }
}