package hfh.com.poi;

import java.util.ArrayList;
import java.util.List;

public class Corp {

	String no;
	String name;
	String cateNo1;
	String cateName1;
	String cateNo2;
	String cateName2;
	String cateNo3;
	String cateName3;
	String cateNo4;
	String cateName4;
	
	String sendStatus = Constant.corpNoSend;
	List<News> news = new ArrayList<News>();
	
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCateNo1() {
		return cateNo1;
	}
	public void setCateNo1(String cateNo1) {
		this.cateNo1 = cateNo1;
	}
	public String getCateName1() {
		return cateName1;
	}
	public void setCateName1(String cateName1) {
		this.cateName1 = cateName1;
	}
	public String getCateNo2() {
		return cateNo2;
	}
	public void setCateNo2(String cateNo2) {
		this.cateNo2 = cateNo2;
	}
	public String getCateName2() {
		return cateName2;
	}
	public void setCateName2(String cateName2) {
		this.cateName2 = cateName2;
	}
	public String getCateNo3() {
		return cateNo3;
	}
	public void setCateNo3(String cateNo3) {
		this.cateNo3 = cateNo3;
	}
	public String getCateName3() {
		return cateName3;
	}
	public void setCateName3(String cateName3) {
		this.cateName3 = cateName3;
	}
	public String getCateNo4() {
		return cateNo4;
	}
	public void setCateNo4(String cateNo4) {
		this.cateNo4 = cateNo4;
	}
	public String getCateName4() {
		return cateName4;
	}
	public void setCateName4(String cateName4) {
		this.cateName4 = cateName4;
	}
	public String getSendStatus() {
		return sendStatus;
	}
	public void setSendStatus(String sendStatus) {
		this.sendStatus = sendStatus;
	}
	public List<News> getNews() {
		return news;
	}
	public void setNews(List<News> news) {
		this.news = news;
	}
	@Override
	public String toString() {
		return "Corp [no=" + no + ", name=" + name + ", cateNo1=" + cateNo1 + ", cateName1=" + cateName1 + ", cateNo2="
				+ cateNo2 + ", cateName2=" + cateName2 + ", cateNo3=" + cateNo3 + ", cateName3=" + cateName3
				+ ", cateNo4=" + cateNo4 + ", cateName4=" + cateName4 + ", sendStatus=" + sendStatus + ", news=" + news
				+ "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((no == null) ? 0 : no.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Corp other = (Corp) obj;
		if (no == null) {
			if (other.no != null)
				return false;
		} else if (!no.equals(other.no))
			return false;
		return true;
	}	
}
