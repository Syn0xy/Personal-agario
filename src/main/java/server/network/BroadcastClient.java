package server.network;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import network.server.Server;
import server.model.GameScene;
import server.model.gameObject.Cell;
import server.model.gameObject.Player;
import utils.Vector2;

public class BroadcastClient {

    private Server io;
    
    private GameScene gameScene;

    private List<Cell> cells;

    public BroadcastClient(Server io, GameScene gameScene) {
        this.io = io;
        this.gameScene = gameScene;
        this.cells = Cell.CELLS;
        this.handleConnections();
        // this.handleSendPackets();
    }

    private void handleConnections() {
        this.io.on("connection", (socket) -> {
            System.out.println("Nouvelle connexion - id: " + socket.getID());
            Player localPlayer = this.gameScene.generatePlayer();
            
            socket.emit("callback_gamescene_dimension", new int[] {
                this.gameScene.getWidth(),
                this.gameScene.getHeight()
            });

            new Timer().schedule(new TimerTask() {

                @Override
                public void run() {
                    List<Cell> cells = gameScene.grid.getCells(localPlayer);
                    socket.emit("callback_cells", cells.toArray(new Cell[cells.size()]));
                }
            
            }, 0, 25);
            
            socket.on("send_player_direction", (data) -> {
                localPlayer.setDirection((Vector2) data);
            });

            // socket.on("get_local_player_index", () -> {
            //     socket.emit("callback_local_player_index", this.cells.indexOf(localPlayer));
            // });
            
            socket.on("disconnect", () -> {
                System.out.println("destroy local player: " + localPlayer);
                localPlayer.destroy();
            });
        });
    }

    // private void handleSendPackets() {
    //     new Timer().schedule(new TimerTask() {
            
    //         @Override
    //         public void run() {
    //             long start = System.nanoTime();
    //             // io.emit("callback_gameobjects", getRegenerateGameObjects());
    //             io.emit("callback_cells", getRegenerateCells());
    //             long end = System.nanoTime();
    //             long time = end - start;
    //             double milli = time / 1_000_000.0;
    //             System.out.println("Time in millisecond: " + milli + "ms");
    //         }
        
    //     }, 0, 150);
    // }
            
    // private Cell[] getRegenerateCells() {
    //     return this.cells.toArray(new Cell[this.cells.size()]);
    // }

}
