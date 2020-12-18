
import games.GameContext;
import games.StandardGameContext;

import java.util.Random;

class Game {

    private final Player[] players;
    private final GameContext gameContext;
    private Player currentPlayer;

    public Game(int numberOfPlayers) {
        this.players = new Player[numberOfPlayers];
        this.gameContext = new StandardGameContext();
        this.gameContext.getBoard().placePlayers(numberOfPlayers);
    }

    public void connectPlayer(Player newPlayer, int i) {
        this.players[i] = newPlayer;
    }

    public void nextPlayer() {
        int nextPlayerId = currentPlayer.getPlayerId() % players.length;
        this.currentPlayer = players[nextPlayerId];
        this.currentPlayer.getCommunicationService().send("MESSAGE It's your turn now!");
    }

    public GameContext getGameContext() {
        return gameContext;
    }

    public int getCurrentPlayerId() {
        return currentPlayer.getPlayerId();
    }

    public void resend(String message, int playerWhoSent) {
        for (Player player : players) {
            if (player.getPlayerId() != playerWhoSent) {
                player.getCommunicationService().send(message);
            }
        }
    }

    public void play() {
        int randomPlayerId = (new Random().nextInt(players.length));
        this.currentPlayer = players[randomPlayerId];

        int[][] gameInfo = gameContext.getBoard().asGameInfo();
        StringBuilder gameInfoString = new StringBuilder("START " + gameInfo.length);
        for (int[] cellInfo : gameInfo) {
            for (int x : cellInfo) {
                gameInfoString.append(";").append(x);
            }
        }
        System.out.println(gameInfoString);
        for (Player player : players) {
            player.getCommunicationService().send(gameInfoString.toString());
        }

        this.resend("MESSAGE Player "+(randomPlayerId + 1)+" was chosen as first player", randomPlayerId + 1);
        this.currentPlayer.getCommunicationService().send("MESSAGE It's your turn now!");
    }
}