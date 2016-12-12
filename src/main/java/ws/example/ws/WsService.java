package ws.example.ws;

import javax.jws.WebService;

@WebService
public interface  WsService {
	WsRes handelReques(WsRequest req);
}
