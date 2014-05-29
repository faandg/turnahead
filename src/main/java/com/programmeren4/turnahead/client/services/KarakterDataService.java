package com.programmeren4.turnahead.client.services;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.programmeren4.turnahead.shared.dto.KarakterDTO;

@RemoteServiceRelativePath("karakterservice")
public interface KarakterDataService extends RemoteService{
		public boolean addKarakterData(KarakterDTO karakterDataDTO);
		public List<KarakterDTO> getKarakters();
	
}
