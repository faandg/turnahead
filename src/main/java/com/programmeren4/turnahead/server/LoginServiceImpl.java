package com.programmeren4.turnahead.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.programmeren4.turnahead.client.services.LoginService;
import com.programmeren4.turnahead.server.model.dao.LoginDao;
import com.programmeren4.turnahead.shared.dto.LoginDTO;
import com.programmeren4.turnahead.shared.exception.DAOException;

public class LoginServiceImpl extends RemoteServiceServlet implements
		LoginService {

	private static final long serialVersionUID = 1L;
	LoginDao loginDao = new LoginDao();
	public Long IngelogdegebruikerID;

	@Override
	public Long Login(LoginDTO loginFromClient) {
		Long result = null;
		try {
			LoginDTO loginfromDB = loginDao.checkLogin(loginFromClient);
			if (loginFromClient.equals(loginfromDB)) {
				result = loginfromDB.getID();
				this.IngelogdegebruikerID = loginfromDB.getID();
			}
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return result;
	}
}
