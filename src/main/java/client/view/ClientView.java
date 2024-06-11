package client.view;

import client.view.network.BroadcastServer;
import view.GameView;

public class ClientView extends GameView {
    
    private static final String TITLE = "Client";
    
    public ClientView(BroadcastServer broadcast) {
        super(broadcast, new ClientCanvas(broadcast));
    }
    
    @Override
    public String getTitle() {
        return super.getTitle() + " - " + TITLE;
    }
    
}
