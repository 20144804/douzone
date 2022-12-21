package board;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BoardVO {
	private int num;
	private int parentNo;
	private String title;
	private String content;
	private Date writeDate;
	private String id;
	private String category;
	private int viewCount;
	private int like_count;
	private int dis_like_count;

	
	public BoardVO() {
		
	}
	public BoardVO(int num,String id, String title, String category,String content) {
		super();
		this.num = num;
		this.title = title;
		this.content = content;
		this.category=category;
		this.id = id;
	}
	public BoardVO(int num, String title, String content, String id, Date writeDate, String category) {
		this.num = num;
		this.title = title;
		this.content = content;
		this.category=category;
		this.id = id;
	}
	
		

}
