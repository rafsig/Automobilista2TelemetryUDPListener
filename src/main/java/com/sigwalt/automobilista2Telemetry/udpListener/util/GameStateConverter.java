package com.sigwalt.automobilista2Telemetry.udpListener.util;

import com.sigwalt.automobilista2Telemetry.models.GameState;
import com.sigwalt.automobilista2Telemetry.models.GameStateData;
import com.sigwalt.automobilista2Telemetry.models.SessionState;
import com.sigwalt.automobilista2Telemetry.udpListener.util.parser.DataParser;

public class GameStateConverter implements IPacketConverter<GameStateData> {

	@Override
	public GameStateData convert(byte[] data) {
		GameStateData gameStateData = new GameStateData();
		gameStateData.setGameState(GameState.valueOf(DataParser.parseShort(14, 1, data))); 
		gameStateData.setSessionState(SessionState.valueOf(DataParser.parseShort(15, 1, data)));
		return gameStateData;
	}

}
