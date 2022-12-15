package constant;

import java.util.Arrays;

/**
 * Enum 자주 사용하는 메소드 스켈레톤
 */
public enum GameCommand {
    RETRY("R"),
    QUIT("Q");

    private final String gameCommand;

    GameCommand(String gameCommand) {
        this.gameCommand = gameCommand;
    }

    public static GameCommand getCommandByName(String input) {
        return Arrays.stream(GameCommand.values())
                .filter(command -> command.gameCommand.equals(input))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("R 또는 Q 중 입력하세요."));
    }

    public String getGameCommand() {
        return this.gameCommand;
    }
}
