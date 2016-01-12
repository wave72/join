package it.burningboots.join.client.service;

import it.burningboots.join.shared.PropertyBean;
import it.burningboots.join.shared.SystemException;
import it.burningboots.join.shared.entity.Config;
import it.burningboots.join.shared.entity.Participant;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("dataService")
public interface DataService extends RemoteService {
	
	//Properties
	public PropertyBean getPropertyBean();
	
	//Config
	public Config findConfigByKey(String key) throws SystemException;
	public Integer saveOrUpdateConfig(Config config) throws SystemException;

	//Participants
	public Participant findParticipantById(Integer id) throws SystemException;
	public Participant findParticipantByItemNumber(String itemNumber) throws SystemException;
	public List<Participant> findParticipants(boolean paid) throws SystemException;
	public Participant createTransientParticipant() throws SystemException;
	public Integer saveOrUpdateParticipant(Participant prt) throws SystemException;
}
