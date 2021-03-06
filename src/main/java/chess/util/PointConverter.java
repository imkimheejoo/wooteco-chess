package chess.util;

import chess.model.Point;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PointConverter {

    public static Point convertToPoint(String point) {
        List<Integer> xAndY = Arrays.asList(point.split(",")).stream()
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        return Point.of(xAndY.get(0), xAndY.get(1));
    }
}
