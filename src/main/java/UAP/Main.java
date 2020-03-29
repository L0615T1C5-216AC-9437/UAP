package UAP;

import arc.util.CommandHandler;
import arc.util.Log;
import mindustry.net.Administration;
import mindustry.plugin.Plugin;

import static mindustry.Vars.netServer;

public class Main extends Plugin {
    public void registerServerCommands(CommandHandler handler){
        handler.register("uap","<UUID>", "Un admins player, even if offline", arg -> {
            Administration.PlayerInfo p = netServer.admins.getInfo(arg[0]);
            if (p != null) {
                if (p.timesJoined > 0) {
                    for (Administration.PlayerInfo pi : netServer.admins.getAdmins()) {
                        if (pi.id.equals(arg[0])) {
                            netServer.admins.unAdminPlayer(arg[0]);
                            Log.info("unAdmin: " + arg[0]);
                            return;
                        }
                    }
                    Log.err("UUID `"+arg[0]+"` is not Admin!");
                } else {
                    Log.err("UUID `"+arg[0]+"` not found!");
                }
            } else {
                Log.err("UUID `"+arg[0]+"` not found!");
            }
        });
    }
}