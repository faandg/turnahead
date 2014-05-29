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

	@Override
	public boolean Login(LoginDTO loginFromClient) {
		boolean result = false;
		try {
			result = loginFromClient.equals(loginDao.checkLogin(loginFromClient));
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return result;
	}
}
