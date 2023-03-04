package future.code.dark.dungeon.domen;

import future.code.dark.dungeon.config.Configuration;
import future.code.dark.dungeon.service.GameMaster;

public class Player extends DynamicObject {
    private static final int stepSize = 1;

    public Player(int xPosition, int yPosition) {
        super(xPosition, yPosition, Configuration.PLAYER_SPRITE);
    }

    public void move(Direction direction) {
        super.move(direction, stepSize);

        if (GameMaster.getInstance().getCoin()
                .stream()
                .anyMatch(coin -> this.isCollision(coin))) {
            GameMaster.getInstance().deleteCoin(this.xPosition, this.yPosition);
        }

        if (this.xPosition == GameMaster.getInstance().getExit().xPosition
                && this.yPosition == GameMaster.getInstance().getExit().yPosition) {
            GameMaster.getInstance().victoryFlag = true;
        }
    }

    @Override
    public String toString() {
        return "Player{[" + xPosition + ":" + yPosition + "]}";
    }
}
