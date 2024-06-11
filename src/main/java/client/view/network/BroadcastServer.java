package client.view.network;

import java.io.IOException;

import network.client.Client;
import server.model.gameObject.Cell;
import utils.Subject;
import utils.Vector2;

public class BroadcastServer extends Subject {

    private Client socket;

    private int gameWidth;

    private int gameHeight;

    private Cell[] cells;

    private Cell localPlayer;

    private Vector2 localPlayerDirection;

    public BroadcastServer(String serverName, int serverPort) throws IOException {
        this.socket = new Client(serverName, serverPort);
        this.socket.on("connection", () -> handleConnections());
    }
    
    private void handleConnections() {
        System.out.println("Socket connectée: [id=" + this.socket.getID() + "]");
        
        this.socket.on("callback_gamescene_dimension", (data) -> {
            int[] object = (int[]) data;
            this.gameWidth = object[0];
            this.gameHeight = object[1];
        });
        
        // this.socket.on("callback_gameobjects", (data) -> {
        //     this.gameObjects = (Object[][]) data;
        //     this.socket.emit("get_player_position");
        //
        //     if (localPlayerDirection != null) {
        //         this.socket.emit("send_player_direction", localPlayerDirection);
        //     }
        // });
        
        this.socket.on("callback_cells", (data) -> {
            this.cells = (Cell[]) data;
            this.socket.emit("get_local_player_index");
            
            if (this.cells.length > 0) {
                localPlayer = this.cells[0];
            }

            if (localPlayerDirection != null) {
                this.socket.emit("send_player_direction", localPlayerDirection);
            }
            
            this.notifyObserver();        
        });


        this.socket.on("callback_local_player_index", (data) -> {
            int index = (int) data;

            if (index >= 0 && this.cells != null && index < this.cells.length) {
                localPlayer = this.cells[index];
            } else {
                localPlayer = null;
            }

            this.notifyObserver();
        });
    }

    public int getGameWidth() {
        return gameWidth;
    }

    public int getGameHeight() {
        return gameHeight;
    }

    public Cell[] getServerCells() {
        return cells;
    }
    
    public Cell getLocalPlayer() {
        return localPlayer;
    }
    
    public void setLocalPlayerDirection(Vector2 localPlayerDirection) {
        this.localPlayerDirection = localPlayerDirection;
    }
    
}
