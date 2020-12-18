package rules;

import boards.Cell;
import boards.GameBoard;

public interface MoveRule extends GameRule {
    Change[] getPossibleMoves(Cell currentCell, GameBoard board);
}
