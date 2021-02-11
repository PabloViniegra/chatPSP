package impml;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.UUID;

@Getter
@Setter
public class DataPackageChat implements Serializable {
    private String uuid;
    private String nick;
    private String message;
    private LocalTime time;

    public DataPackageChat(String nick, String message) {
        this.uuid = UUID.randomUUID().toString().replace("-", "");
        this.nick = nick;
        this.message = message;
        this.time = LocalTime.now();
    }
}
