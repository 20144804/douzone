package board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class BoardFileVO {
	int f_id;
	int num;
	String org_name;
	String real_name;
	String content_type;
	long length;
	
	
	public BoardFileVO() {
		// TODO Auto-generated constructor stub
	}
	
	
}
