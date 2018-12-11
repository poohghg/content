package org.zerock.furniture.dto;

import java.util.Arrays;
import java.util.Date;

public class FurnitureDTO
{

	private int id, price,hit, counts;
	private String cpn,title,makecp,content,model_id,extension,UUID;
	private Date writeDate;
	private byte[] picture;
	
	public byte[] getPicture()
	{
		return picture;
	}
	public void setPicture(byte[] picture)
	{
		this.picture = picture;
	}
	
	
	public int getCounts()
	{
		return counts;
	}
	public void setCounts(int counts)
	{
		this.counts = counts;
	}
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public int getPrice()
	{
		return price;
	}
	public void setPrice(int price)
	{
		this.price = price;
	}
	public String getCpn()
	{
		return cpn;
	}
	public void setCpn(String cpn)
	{
		this.cpn = cpn;
	}
	public Date getWriteDate()
	{
		return writeDate;
	}
	public void setWriteDate(Date writeDate)
	{
		this.writeDate = writeDate;
	}
	
	
	
	
	
	public String getExtension()
	{
		return extension;
	}
	public void setExtension(String extension)
	{
		this.extension = extension;
	}
	public String getUUID()
	{
		return UUID;
	}
	public void setUUID(String uUID)
	{
		UUID = uUID;
	}
	public String getMakecp()
	{
		return makecp;
	}
	public void setMakecp(String makecp)
	{
		this.makecp = makecp;
	}
	public String getContent()
	{
		return content;
	}
	public void setContent(String content)
	{
		this.content = content;
	}

	
	public String getModel_id()
	{
		return model_id;
	}
	public void setModel_id(String model_id)
	{
		this.model_id = model_id;
	}
	public int getHit()
	{
		return hit;
	}
	public void setHit(int hit)
	{
		this.hit = hit;
	}
	
	
	@Override
	public String toString()
	{
		return "FurnitureDTO [id=" + id + ", price=" + price + ", hit=" + hit + ", counts=" + counts + ", cpn=" + cpn
				+ ", title=" + title + ", makecp=" + makecp + ", content=" + content + ", model_id=" + model_id
				+ ", extension=" + extension + ", UUID=" + UUID + ", writeDate=" + writeDate + ", picture="
				+ Arrays.toString(picture) + "]";
	}
	
	
	
	
	
}
