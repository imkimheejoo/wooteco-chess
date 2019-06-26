package chess;

import chess.model.board.BoardService;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.HashMap;
import java.util.Map;

import static chess.view.Render.render;

public class ChessGameController {
    public static Route start = (Request request, Response response) -> {
        Map<String, Object> model = new HashMap<>();
        BoardService boardService = new BoardService();
        boardService.initialize();
        model.put("team", boardService.currentTeam());

        Gson gson = new Gson();
        String json = gson.toJson(boardService.getChesses());
        model.put("chesses", json);

        return render(model, "main.html");
    };

    public static Route load = (Request request, Response response) -> {
        Map<String, Object> model = new HashMap<>();
        BoardService boardService = new BoardService();

        model.put("team", boardService.currentTeam());
        Gson gson = new Gson();
        String json = gson.toJson(boardService.getChesses());
        model.put("chesses", json);
        model.put("error",request.queryParams("error"));
        return render(model, "main.html");
    };

    public static Route move = (Request request, Response response) -> {
        Map<String, Object> model = new HashMap<>();
        BoardService boardService = new BoardService();

        String source = request.queryParams("source");
        String destination = request.queryParams("destination");
        String moveResult = boardService.move(source, destination);

        Gson gson = new Gson();
        String json = gson.toJson(boardService.getChesses());
        model.put("chesses", json);

        model.put("team", boardService.currentTeam());
        model.put("moveResult", moveResult);

        return render(model, "main.html");
    };
}