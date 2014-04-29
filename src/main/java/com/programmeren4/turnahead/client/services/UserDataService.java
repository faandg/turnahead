package com.programmeren4.turnahead.client.services;


import java.sql.SQLException;
import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.programmeren4.turnahead.shared.dto.UserDataDTO;
import com.programmeren4.turnahead.shared.exception.DAOException;

// methode om gegevens van speler op te vragen uit databank
// om te kunnen tonen op de OverzichtsPagina.java
@RemoteServiceRelativePath("userservice")
public interface UserDataService extends RemoteService{
	public boolean createUser(UserDataDTO userDataDTO) throws SQLException;
	public List<UserDataDTO> getUserData() throws SQLException;
}
