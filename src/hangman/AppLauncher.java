package hangman;


public class AppLauncher {
    public static void main(String[] args) {
        GameLogic gameLogic = new GameLogic();

        gameLogic.playing = true;

        while (gameLogic.playing) {
            gameLogic.menu();
        }

        System.out.println("Thank you.");
    }
}
