package org.zerock.furniture.dto;

import java.sql.Date;

public class Furniture_CommentDTO
{
	private int id,assessment,furnitureid;
	private String userid, comments, cpn;
	private Date writedate;
	
	
	public int getFurnitureid()
	{
		return furnitureid;
	}
	public void setFurnitureid(int furnitureid)
	{
		this.furnitureid = furnitureid;
	}

	public int getAssessment()
	{
		return assessment;
	}
	public void setAssessment(int assessment)
	{
		this.assessment = assessment;
	}
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getUserid()
	{
		return userid;
	}
	public void setUserid(String userid)
	{
		this.userid = userid;
	}
	public String getComments()
	{
		return comments;
	}
	public void setComments(String comment)
	{
		this.comments = comment;
	}
	public Date getWritedate()
	{
		return writedate;
	}
	public void setWritedate(Date writedate)
	{
		this.writedate = writedate;
	}
	

	public String getCpn()
	{
		return cpn;
	}
	public void setCpn(String cpn)
	{
		this.cpn = cpn;
	}
	
	
	
	@Override
	public String toString()
	{
		return "Furniture_CommentDTO [id=" + id + ", assessment=" + assessment + ", furnitureid=" + furnitureid
				+ ", userid=" + userid + ", comments=" + comments + ", cpn=" + cpn + ", writedate=" + writedate + "]";
	}

	
	
}
