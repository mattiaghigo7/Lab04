package it.polito.tdp.lab04.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {

	public List<Studente> cercaStudenteMatricola(int matricola) {
		String sql = "SELECT * " + "FROM studente s " + "WHERE s.matricola=?";
		List<Studente> s = new ArrayList<Studente>();
		Connection conn = ConnectDB.getConnection();
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matricola);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				s.add(new Studente(rs.getInt("matricola"),rs.getString("cognome"),rs.getString("nome"),rs.getString("CDS")));
			}
			rs.close();
			st.close();
			conn.close();
			return s;
		} catch (SQLException e) {
			System.err.println("Errore connessione al database");
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Studente> getIscrittiCorso(String codins) {
		if (codins.compareTo("")==0) {
			String sql = "SELECT s.matricola, s.cognome, s.nome, s.CDS " + "FROM studente s";
			List<Studente> risultato = new ArrayList<Studente>();
			Connection conn = ConnectDB.getConnection();
			try {
				PreparedStatement st = conn.prepareStatement(sql);
				ResultSet rs = st.executeQuery();
				while (rs.next()) {
					risultato.add(new Studente(rs.getInt("matricola"),rs.getString("cognome"),rs.getString("nome"),rs.getString("CDS")));
				}
				rs.close();
				st.close();
				conn.close();
				return risultato;
				
			} catch (SQLException e) {
				System.err.println("Errore connessione al database");
				e.printStackTrace();
				return null;
			}
		}
		String sql = "SELECT s.matricola, s.cognome, s.nome, s.CDS " + "FROM studente s, iscrizione i " + "WHERE s.matricola=i.matricola AND i.codins=?";
		List<Studente> risultato = new ArrayList<Studente>();
		Connection conn = ConnectDB.getConnection();
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, codins);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				risultato.add(new Studente(rs.getInt("matricola"),rs.getString("cognome"),rs.getString("nome"),rs.getString("CDS")));
			}
			rs.close();
			st.close();
			conn.close();
			return risultato;
			
		} catch (SQLException e) {
			System.err.println("Errore connessione al database");
			e.printStackTrace();
			return null;
		}
	}
}
