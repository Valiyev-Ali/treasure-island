import game.Game;

public class Main {
    static boolean[][] visited;
    static boolean hasShovel = false;

    public static void main(String[] args) throws Exception{
            Game.play();
            visited = new boolean[Game.getRows()][Game.getColumns()];
            visited[0][0] = true;
            explore();
        }

    public static void explore() throws Exception{

        if (Game.hasShovel()) {
            Game.pickShovel();
            visited = new boolean[Game.getRows()][Game.getColumns()];
            hasShovel = true;
        }

        if (hasShovel && Game.hasTreasure()) {
            Game.digTreasure();
            System.exit(0);
        }

        int currRow = Game.getLocation().getRow();
        int currCol = Game.getLocation().getColumn();
        visited[currRow][currCol] = true;

        if (currRow != 0 && !visited[currRow - 1][currCol] && tryMove("u")) {
            explore();
            tryMove("d");
        }
        if (currRow != (Game.getRows() - 1) && !visited[currRow + 1][currCol] && tryMove("d")) {
            explore();
            tryMove("u");
        }
        if (currCol != 0 && !visited[currRow][currCol - 1] && tryMove("l")) {
            explore();
            tryMove("r");
        }
        if (currCol != (Game.getColumns() - 1) && !visited[currRow][currCol + 1] && tryMove("r")) {
            explore();
            tryMove("l");
        }
    }
    public static boolean tryMove(String dir) {
        try {
            if (dir.equalsIgnoreCase("u")) Game.moveUp();
            else if (dir.equalsIgnoreCase("d")) Game.moveDown();
            else if (dir.equalsIgnoreCase("l")) Game.moveLeft();
            else Game.moveRight();
            return true;

        } catch (Exception e) {
            return false;
        }
    }
}