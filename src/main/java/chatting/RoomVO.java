package chatting;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RoomVO {
	int seq;
	String roomName;

	public RoomVO() {
		
	}

	
}
