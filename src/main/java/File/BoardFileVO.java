package File;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BoardFileVO {
	int f_id;
	int num;
	String org_name;
	String real_name;
	String content_type;
	long length;
	
	
}
