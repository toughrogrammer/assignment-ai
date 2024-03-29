/*
 * Wumpus-Lite, version 0.21 alpha
 * A lightweight Java-based Wumpus World Simulator
 * 
 * Written by James P. Biagioni (jbiagi1@uic.edu)
 * for CS511 Artificial Intelligence II
 * at The University of Illinois at Chicago
 * 
 * Thanks to everyone who provided feedback and
 * suggestions for improving this application,
 * especially the students from Professor
 * Gmytrasiewicz's Spring 2007 CS511 class.
 * 
 * Last modified 4/14/08
 * 
 * DISCLAIMER:
 * Elements of this application were borrowed from
 * the client-server implementation of the Wumpus
 * World Simulator written by Kruti Mehta at
 * The University of Texas at Arlington.
 * 
 */


class WorldApplication {

    private static String VERSION = "v0.21a";
    String gameboard = "";
    String query = "";
    String KB = "";
    String results = "";

    int algo;
    int worldSize;

    public static void main(String args[]) throws Exception {
        WorldApplication wa = new WorldApplication();
        if (wa.readPara(args) == 6) {
            System.out.println(System.currentTimeMillis());
            wa.start();
            System.out.println(System.currentTimeMillis());
        } else {
            wa.usage();
        }

    }

    void start() {
        char[][] map = MapReader.readWumpusWorld(worldSize, gameboard);

        WumpusInferenceEngine engine = new WumpusInferenceEngine(map, worldSize);
        engine.printKnowledgeBase(KB);
        if (algo == 1) {
            engine.runInference(query, results, WumpusInferenceEngine.AlgorithmType.RESOLUTION);
        } else if (algo == 2) {
            engine.runInference(query, results, WumpusInferenceEngine.AlgorithmType.RANDOM_WALKING);
        }
    }

    private void usage() {
        System.out.println("Usage:\n\n-i map eg gameboard.txt");
        System.out.println("-o1 the file that writes results of KB based on map. eg KB.txt");
        System.out.println("-o2 the file that writes results of queries. eg.results.txt");
        System.out.println("-q the inut file of queries. eg query.txt");
        System.out.println("-ws worldsize eg. 4");
        System.out.println("-h algorithms 1 for resolution and 2 for walksat");
    }

    private int readPara(String args[]) {
        int n = 0;
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-i")) {
                this.gameboard = args[i + 1];
                n++;
            } else if (args[i].equals("-q")) {
                this.query = args[i + 1];
                n++;
            } else if (args[i].equals("-ws")) {
                this.worldSize = Integer.parseInt(args[i + 1]);
                n++;
            } else if (args[i].equals("-h")) {
                this.algo = Integer.parseInt(args[i + 1]);
                n++;
            } else if (args[i].equals("-o1")) {
                this.KB = args[i + 1];
                n++;
            } else if (args[i].equals("-o2")) {
                this.results = args[i + 1];
                n++;
            }
        }

        return n;
    }

}