package ooga.model.factories;

import ooga.model.GamePiece;

import java.util.Properties;

public interface Factory {

    public GamePiece getGamePiece(Properties properties);
}
