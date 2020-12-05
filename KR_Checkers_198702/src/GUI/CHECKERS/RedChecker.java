package GUI.CHECKERS;

import javafx.scene.paint.Color;
import jfxtras.labs.util.event.MouseControlUtil;

public class RedChecker extends Checker {

    public RedChecker(int[] coor, double size) {
        create(Color.RED, Color.WHITE, coor, size);
        currCoor = coor;
        isKing = false;
        moveCoors = new int[][]{{-1, -1}, {-1, 1}};
        jumpCoors = new int[][]{{-2, -2}, {-2, 2}};
        MouseControlUtil.makeDraggable(this);
    }
}
