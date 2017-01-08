package hfh.com.poi;

public class News {
	String addr;
	String title;
	String sDate;
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getsDate() {
		return sDate;
	}
	public void setsDate(String sDate) {
		this.sDate = sDate;
	}
	
	@Override
	public String toString() {
		return "News [addr=" + addr + ", title=" + title + ", sDate=" + sDate + "]";
	}	
}
