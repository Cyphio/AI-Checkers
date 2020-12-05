package GUI.CHECKERS;

import javafx.scene.paint.Color;
import jfxtras.labs.util.event.MouseControlUtil;

public class BlackChecker extends Checker {

    public BlackChecker(int[] coor, double size) {
        create(Color.BLACK, Color.WHITE, coor, size);
        currCoor = coor;
        isKing = false;
        moveCoors = new int[][]{{1, -1}, {1, 1}};
        jumpCoors = new int[][]{{2, -2}, {2, 2}};
        MouseControlUtil.makeDraggable(this);
    }
}
