package ws.example.ws;

import javax.jws.WebService;

import org.springframework.stereotype.Service;

@Service
@WebService(endpointInterface = "com.adenuniv.ws.WsService")
public class WsServiceImpl implements WsService{

	@Override
	public WsRes handelReques(WsRequest req) {
		return new WsRes();
	}

}
