package com.programmeren4.turnahead.server;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.programmeren4.turnahead.client.services.KarakterDataService;
import com.programmeren4.turnahead.server.model.dao.KarakterDataDao;
import com.programmeren4.turnahead.shared.dto.KarakterDTO;
import com.programmeren4.turnahead.shared.exception.DAOException;

public class KarakterDataServiceImpl extends RemoteServiceServlet implements KarakterDataService{
	private static final long serialVersionUID = 691252378774360517L;
	
	KarakterDataDao charDataDao = new KarakterDataDao();
			
	@Override
	public boolean addKarakterData(KarakterDTO karakterDataDTO) {
		try {
			charDataDao.addKarakterData(karakterDataDTO);
		} catch (DAOException e) {
			e.printStackTrace();
		} 
		return true;
	}

	@Override
	public List<KarakterDTO> getKarakters() {
		List<KarakterDTO> charList = new ArrayList<KarakterDTO>();
		try {
			charList.addAll(charDataDao.getKarakters());
			if (charList.isEmpty()){
				System.out.println("The returned arraylist is empty");
			}
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return charList;
	}
			
			



}
