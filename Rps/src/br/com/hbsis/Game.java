package br.com.hbsis;

import java.util.ArrayList;
import java.util.Arrays;

public class Game {
	public static void main(String[] args) {
		ArrayList<String[]> players = new ArrayList<String[]>();
		String[] retorno;

		players.add(new String[] { "Armando", "P" });
		players.add(new String[] { "Dave", "R" });
		
		System.out.println("--------- Partida Individual ---------");
		retorno = rps_game_winner(players);
		System.out.println("Jogador " + retorno[0] + " é o vencedor(a) com a jogada " + retorno[1]+"!");
		System.out.println(" ");
		System.out.println("--------- Campeonato ---------");
		
		players = new ArrayList<String[]>();
		players.add(new String[] { "Armando", "P" });
		players.add(new String[] { "Dave", "S" });
		players.add(new String[] { "Richard", "R" });
		players.add(new String[] { "Michael", "S" });
		players.add(new String[] { "Allen", "S" });
		players.add(new String[] { "Omer", "P" });
		players.add(new String[] { "David E.", "R" });
		players.add(new String[] { "Richard X", "P" });

		System.out.println(rps_tournament_winner(players));

	}

	public static String[] rps_game_winner(ArrayList<String[]> players) {
		if (players.size() != 2) {
			throw new WrongNumberOfPlayersError("Número errado de jogadores, são permitidos apenas 2!");
		}

		String j1 = players.get(0)[1];
		String j2 = players.get(1)[1];
		String[] strategy = { "R", "P", "S" };

		if (!Arrays.asList(strategy).contains(j1)) {
			throw new NoSuchStrategyError("Estratégia " + j1 + " não encontrada, é permitido apenas R,P ou S");
		}
		if (!Arrays.asList(strategy).contains(j2)) {
			throw new NoSuchStrategyError("Estratégia " + j2 + " não encontrada, é permitido apenas R,P ou S");
		}

		if ((j1.equalsIgnoreCase("R") && j2.equalsIgnoreCase("S"))
				|| (j1.equalsIgnoreCase("S") && j2.equalsIgnoreCase("P"))
				|| (j1.equalsIgnoreCase("P") && j2.equalsIgnoreCase("R")) || (j1.equalsIgnoreCase(j2))) {
			return players.get(0);
		} else {
			return players.get(1);
		}

	}

	public static String rps_tournament_winner(ArrayList<String[]> gamePlayers) {
		ArrayList<String[]> newGames = new ArrayList<String[]>();

		for (int i = 1; i < gamePlayers.size();) {

			ArrayList<String[]> game = new ArrayList<String[]>();

			game.add(gamePlayers.get(i - 1));
			game.add(gamePlayers.get(i));

			newGames.add(rps_game_winner(game));

			i += 2;
		}

		if (newGames.size() == 1)
			return "Jogador " + newGames.get(0)[0] + " é o vencedor(a) com a jogada "+newGames.get(0)[1]+"!";

		return rps_tournament_winner(newGames);
	}

}
