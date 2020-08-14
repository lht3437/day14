package com.xiaoshu.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class StudentVo extends Student{
		private String mname;
		private Integer num;
		
	//	private String chaxun;
		
		@DateTimeFormat(pattern="yyyy-MM-dd")
	    private Date sdbirth1;
		@DateTimeFormat(pattern="yyyy-MM-dd")
	    private Date sdbirth2;
		
		
	/*	public String getChaxun() {
			return chaxun;
		}

		public void setChaxun(String chaxun) {
			this.chaxun = chaxun;
		}*/

		public Integer getNum() {
			return num;
		}

		public void setNum(Integer num) {
			this.num = num;
		}

		public String getMname() {
			return mname;
		}

		public void setMname(String mname) {
			this.mname = mname;
		}

		public Date getSdbirth1() {
			return sdbirth1;
		}

		public void setSdbirth1(Date sdbirth1) {
			this.sdbirth1 = sdbirth1;
		}

		public Date getSdbirth2() {
			return sdbirth2;
		}

		public void setSdbirth2(Date sdbirth2) {
			this.sdbirth2 = sdbirth2;
		}

	
}
