package com.programmeren4.turnahead.server.model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.programmeren4.turnahead.server.database.DBConnector;
import com.programmeren4.turnahead.shared.dto.ItemDTO;
import com.programmeren4.turnahead.shared.dto.KarakterDTO;
import com.programmeren4.turnahead.shared.exception.DAOException;

public class KarakterDataDao {
	// attributen
	private Connection conn;
	private String sql;

	// private String tabelnaam = "KARAKTER";
	// private String[] tabelvelden =
	// {"CHARACTERID","CHARACTERNAME","CURRENTLOCATION", "CREATION_DATE", "LASTUSE_DATE", "USERID","LOCATIONID"};

	// constructor
	public KarakterDataDao() {
	}

	// getters en setters

	// SELECT - UPDATE - INSERT - DELETE
	/**
	 * Gegevens van een karakter opvragen (SELECT)
	 */
	public KarakterDTO getKarakterData(KarakterDTO karakterData)
			throws DAOException {
		KarakterDTO karakterReturn = null;
		ResultSet rs = null;

		try {
			DBConnector.getInstance().init();
			this.conn = DBConnector.getInstance().getConn();
			sql = "SELECT * FROM programmeren4.KARAKTER WHERE CHARACTERID=" + karakterData.getKarakterId();
			rs = conn.createStatement().executeQuery(sql);
			if (rs.next()) {
				karakterReturn = new KarakterDTO();
				karakterReturn.setKarakterId(rs.getLong("CHARACTERID"));
				karakterReturn.setKarakterName(rs.getString("CHARACTERNAME"));
				karakterReturn.setCurrentLocation(rs.getString("CURRENTLOCATION"));
				karakterReturn.setUserId(rs.getLong("USERID"));
				karakterReturn.setLocationId(rs.getLong("LOCATIONID"));
			}
			if (!rs.isBeforeFirst() ) {    
				 System.out.println("No data"); 
			} 
			
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnector.close(rs);
			DBConnector.getInstance().closeConn();
		}
		return karakterReturn;
	}

	/**
	 * Karakter toevoegen (INSERT) of wijzigen (UPDATE)
	 */
	public void addKarakterData(KarakterDTO karakterData) throws DAOException {
		// Controle (Bestaat Karakter al in tabel Karakter ?)
		boolean karakterTest = this.VerifyKarakterId(karakterData);

		// Naam en id van eerste Locatie in tabel LOCATION ophalen

		try {
			DBConnector.getInstance().init();
			this.conn = DBConnector.getInstance().getConn();

			if (karakterTest == true) {
				// JA -> UPDATE bestaande record
				// "UPDATE programmeren4.KARAKTER SET
				// *veld='karakterData.getX()',*veld='karakterData.getY()',
				// "WHERE KARAKTERID=" + karakterData.getKarakterId();
				String sql = "UPDATE programmeren4.KARAKTER SET ";
				sql += "CHARACTERNAME='" + karakterData.getKarakterName().toUpperCase() + "', ";
				sql += " CURRENTLOCATION='" + karakterData.getCurrentLocation().toUpperCase() + "'";
				sql += " WHERE CHARACTERID=" + karakterData.getKarakterId();

				conn.createStatement().executeUpdate(sql);
			} else {
				// NEEN -> Karakter toevoegen aan de database>
				// INSERT INTO programmeren4.KARAKTER(Columns db) VALUES
				// (karakterData.getXXX(),
				// karakterData.getYYY(), karakterData.getZZZ())
				String sql = "INSERT INTO programmeren4.KARAKTER(";
				sql += "CHARACTERNAME, CURRENTLOCATION, USERID, LOCATIONID) VALUES ('";
				sql += karakterData.getKarakterName() + "', '";
				sql += "SMIDSE', '";
				sql += karakterData.getUserId() + "','"; // UserId van de User die Karakter
				sql += "1";
				sql += "')";
				conn.createStatement().executeUpdate(sql);
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnector.getInstance().closeConn();
		}
	}

	/**
	 * Karakter verwijderen (DELETE)
	 */
	public void deleteKarakterData(String characterName)
			throws DAOException {
		ResultSet rs = null;
		KarakterDTO karakterReturn = null;
		try {
			DBConnector.getInstance().init();
			this.conn = DBConnector.getInstance().getConn();
			System.out.println("Fetching charID for charname " +  characterName);
			sql = "SELECT * FROM programmeren4.KARAKTER WHERE CHARACTERNAME='" + characterName +"'";
			rs = conn.createStatement().executeQuery(sql);
				if (rs.next()) {
					karakterReturn = new KarakterDTO();
					karakterReturn.setKarakterId(rs.getLong("CHARACTERID"));
					karakterReturn.setKarakterName(rs.getString("CHARACTERNAME"));
					karakterReturn.setCurrentLocation(rs.getString("CURRENTLOCATION"));
					karakterReturn.setUserId(rs.getLong("USERID"));
					karakterReturn.setLocationId(rs.getLong("LOCATIONID"));
				}
				if (!rs.isBeforeFirst() ) {    
					 System.out.println("No data"); 
				} 
			
			System.out.println("deleting character with characterid" + karakterReturn.getKarakterId());
			sql = "DELETE FROM programmeren4.KARAKTER WHERE CHARACTERID=" + karakterReturn.getKarakterId();
			conn.createStatement().executeUpdate(sql);
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnector.close(rs);
			DBConnector.getInstance().closeConn();
		}
	}

	// LIST
	/**
	 * Lijst van alle karakters (LIST ALL)
	 */
	public List<KarakterDTO> getKarakters() throws DAOException {
		String query = "SELECT * FROM programmeren4.KARAKTER";
		List<KarakterDTO> list = new ArrayList<KarakterDTO>();
		KarakterDTO karakterReturn = null;
		ResultSet rs = null;

		try {
			DBConnector.getInstance().init();
			this.conn = DBConnector.getInstance().getConn();
			rs = conn.createStatement().executeQuery(query);

			while (rs.next()) {
				karakterReturn = new KarakterDTO();
				karakterReturn.setKarakterId(rs.getLong("CHARACTERID"));
				karakterReturn.setKarakterName(rs.getString("CHARACTERNAME"));
				karakterReturn.setCurrentLocation(rs.getString("CURRENTLOCATION"));
				karakterReturn.setUserId(rs.getLong("USERID"));
				karakterReturn.setLocationId(rs.getLong("LOCATIONID"));

				list.add(karakterReturn);
			}
			if (list.isEmpty()) {
				System.out.println("List fetched from database is empty.");
			}
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnector.getInstance().close(rs);
			DBConnector.getInstance().closeConn();
		}
		return list;
	}

	/**
	 * LIST BY USER - Lijst van alle karakters voor één UserId
	 */
	public List<KarakterDTO> getKaraktersOfUserId(Long userID)
			throws DAOException {
		List<KarakterDTO> list = new ArrayList<KarakterDTO>();
		KarakterDTO karakterReturn = null;
		ResultSet rs = null;

		try {
			DBConnector.getInstance().init();
			this.conn = DBConnector.getInstance().getConn();
			String query = "SELECT * FROM programmeren4.KARAKTER WHERE USERID="
					+ userID.toString();
			rs = conn.createStatement().executeQuery(query);

			while (rs.next()) {
				karakterReturn = new KarakterDTO();
				karakterReturn.setKarakterId(rs.getLong("CHARACTERID"));
				karakterReturn.setKarakterName(rs.getString("CHARACTERNAME"));
				karakterReturn.setCurrentLocation(rs.getString("CURRENTLOCATION"));
				karakterReturn.setUserId(rs.getLong("USERID"));
				karakterReturn.setLocationId(rs.getLong("LOCATIONID"));
				System.out.println(karakterReturn);
				list.add(karakterReturn);
			}
			if (list.isEmpty()) {
				System.out.println("List fetched from database is empty.");
			}		
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnector.getInstance().close(rs);
			DBConnector.getInstance().closeConn();
		}
		return list;
	}

	/**
	 * LIST BY LOCATION - Lijst van alle karakters voor één LocationId
	 */
	public List<KarakterDTO> getKaraktersOfLocationId(KarakterDTO karakterData)
			throws DAOException {
		List<KarakterDTO> list = new ArrayList<KarakterDTO>();
		KarakterDTO karakterReturn = null;
		ResultSet rs = null;

		try {
			DBConnector.getInstance().init();
			this.conn = DBConnector.getInstance().getConn();
			String query = "SELECT * FROM programmeren4.KARAKTER WHERE LOCATIONID="
					+ karakterData.getLocationId();
			rs = conn.createStatement().executeQuery(query);

			while (rs.next()) {
				karakterReturn = new KarakterDTO();
				karakterReturn.setKarakterId(rs.getLong("CHARACTERID"));
				karakterReturn.setKarakterName(rs.getString("CHARACTERNAME"));
				karakterReturn.setCurrentLocation(rs.getString("CURRENTLOCATION"));
				karakterReturn.setUserId(rs.getLong("USERID"));
				karakterReturn.setLocationId(rs.getLong("LOCATIONID"));

				list.add(karakterReturn);
			}
			if (list.isEmpty()) {
				System.out.println("List fetched from database is empty.");
			}
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnector.getInstance().close(rs);
			DBConnector.getInstance().closeConn();
		}
		return list;
	}

	
	//Itemownership
	/**
	 * TODO - verify
	 * Methode om een item toe te wijzen aan een karakter
	 */
	public void AllocateItemToKarakter(KarakterDTO karakterData, ItemDTO itemData){
		try {
			DBConnector.getInstance().init();
			this.conn = DBConnector.getInstance().getConn();		
			sql = "UPDATE programmeren4.ITEMOWNERSHIP SET ";
			sql += "CHARACTERID='" + karakterData.getKarakterId() + "', ";
			sql += "LOCATIONID=null ";
			sql += "WHERE ITEMID=" + itemData.getItemId();
			conn.createStatement().executeUpdate(sql);
			
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnector.getInstance().closeConn();
		}
	}
	
	
	// Overige methodes
	/**
	 * Methode om te controleren of een karakter al aanwezig is (in de database)
	 * (op basis van een KarakterId)
	 */
	public boolean VerifyKarakterId(KarakterDTO karakterData)
			throws DAOException {
		ResultSet rs = null;
		boolean inDatabase = false;

		try {
			DBConnector.getInstance().init();
			this.conn = DBConnector.getInstance().getConn();
			sql = "SELECT * FROM programmeren4.KARAKTER WHERE CHARACTERID=" + karakterData.getKarakterId();
			rs = conn.createStatement().executeQuery(sql);
			// System.out.println("karakterData KarakterID: " +
			// userData.getUserId());

			if (rs.next()) {
				long a = new Long(rs.getLong("CHARACTERID"));
				// System.out.println(a);
				if (a == karakterData.getKarakterId()) {
					inDatabase = true;
					System.out.println("IF-true");
				} else {
					inDatabase = false;
					System.out.println("IF-false");
				}
			}
			System.out.println(inDatabase);
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnector.getInstance().close(rs);
			DBConnector.getInstance().closeConn();
		}
		return inDatabase;
	}

	/**
	 * Methode om te controleren of een Karakter al aanwezig is in de database
	 * (op basis van Karakternaam)
	 */
	public boolean VerifyKarakterNaam(KarakterDTO karakterData)
			throws DAOException {
		ResultSet rs = null;
		boolean inDatabase = false;

		try {
			DBConnector.getInstance().init();
			this.conn = DBConnector.getInstance().getConn();
			sql = "SELECT * FROM programmeren4.KARAKTER WHERE CHARACTERNAME='"
					+ karakterData.getKarakterName().toUpperCase() + "'";
			rs = conn.createStatement().executeQuery(sql);
			// System.out.println("karakterData KarakterID: " +  karakterData.getKarakterId());

			if (rs.next()) {
				if (rs.getRow() == 1
						& new String(rs.getString("CHARACTERNAME").toUpperCase()).equals(karakterData.getKarakterName().toUpperCase())) {
					inDatabase = true;
				} else {
					inDatabase = false;
				}
			}

			System.out.println(inDatabase);
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnector.getInstance().close(rs);
			DBConnector.getInstance().closeConn();
		}
		return inDatabase;
	}

}
