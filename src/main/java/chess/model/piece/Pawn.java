package chess.model.piece;

import chess.model.Direction;
import chess.model.PlayerType;
import chess.model.Point;

import java.util.List;

public class Pawn extends Piece {
    private static final double PAWN_INITIAL_SCORE = 1;
    private static final double MAX_DISTANCE = 2;
    private static final double MIN_DISTANCE = 1;
    private static final double DIAGONAL_DISTANCE = Math.sqrt(2);
    private boolean isFirstMove = true;

    public Pawn(PlayerType team, Point point) {
        super(team, point, PAWN_INITIAL_SCORE);
    }

    @Override
    public void move(Point destination) {
        super.move(destination);
        isFirstMove = false;
    }

    @Override
    public boolean canMove(Direction direction, Point destination) {
        List<Direction> pawnDirections = Direction.pawnDirection(team);
        double distance = point.calculateDistance(destination);

        if (distance == MAX_DISTANCE) {
            return pawnDirections.get(0) == direction && isFirstMove;
        }
        if (distance == MIN_DISTANCE) {
            return pawnDirections.get(0) == direction;
        }
        if (distance == DIAGONAL_DISTANCE) {
            return pawnDirections.get(1) == direction || pawnDirections.get(2) == direction;
        }

        throw new IllegalArgumentException();
    }

    @Override
    public boolean isAvailableDestinationOfPawn(Direction direction, Piece destinationPiece) {
        List<Direction> pawnDirections = Direction.pawnDirection(team);
        if (direction == pawnDirections.get(0))
            if (!destinationPiece.isNone()) {
                return false;
            }
        if (direction == pawnDirections.get(1) || direction == pawnDirections.get(2)) {
            return destinationPiece.isOpponent(team);
        }
        return true;
    }

    @Override
    public boolean isPawn() {
        return true;
    }

    @Override
    public String toString() {
        return "pawn";
    }
}
