package com.xiaoshu.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.xiaoshu.dao.MajorMapper;
import com.xiaoshu.dao.StudentMapper;

import com.xiaoshu.entity.Major;
import com.xiaoshu.entity.Student;
import com.xiaoshu.entity.StudentVo;
import com.xiaoshu.entity.User;
import com.xiaoshu.entity.UserExample;
import com.xiaoshu.entity.UserExample.Criteria;

@Service
public class StudentService {

	@Autowired
	StudentMapper studentMapper;
	@Autowired
	MajorMapper majorMapper;
	
	public PageInfo<StudentVo> findList(StudentVo studentVo, Integer pageNum, Integer pageSize) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNum, pageSize);
		List<StudentVo> list=studentMapper.findList(studentVo);
		return new PageInfo<>(list);
	}

	public List<Major> findAll() {
		// TODO Auto-generated method stub
		return majorMapper.selectAll();
	}

	public void updateStudent(Student student) {
		// TODO Auto-generated method stub
		studentMapper.updateByPrimaryKeySelective(student);
	}

	public Student existUserWithUserName(String sdName) {
		List<Student> userList = studentMapper.selectByName(sdName);
		return userList.isEmpty()?null:userList.get(0);
	}

	public void addStudent(Student student) {
		// TODO Auto-generated method stub
		studentMapper.insert(student);
		
	}

	public void deleteUser(Integer sdId) {
		// TODO Auto-generated method stub
		studentMapper.deleteByPrimaryKey(sdId);
	}

	public List<StudentVo> countStudent() {
		// TODO Auto-generated method stub
		return studentMapper.countStudent();
	}


	public List<StudentVo> exportStudent(StudentVo studentVo) {
		// TODO Auto-generated method stub
		return studentMapper.findList(studentVo);
	}

	public void importStudent(MultipartFile studentFile) throws InvalidFormatException, IOException {
		// TODO Auto-generated method stub
		Workbook workbook = WorkbookFactory.create(studentFile.getInputStream());
		Sheet sheet = workbook.getSheetAt(0);
		int lastRowNum = sheet.getLastRowNum();
		for (int i = 0; i < lastRowNum; i++) {
			Row row = sheet.getRow(i+1);
			String name = row.getCell(0).toString();
			String sex = row.getCell(1).toString();
			String hobby = row.getCell(2).toString();
			Date birth = row.getCell(3).getDateCellValue();
			String mname = row.getCell(4).toString();
			
			if(!mname.equals("人工")){
				continue;
			}
			
			Student s=new Student();
			s.setSdName(name);
			s.setSdsex(sex);
			s.setSdHobby(hobby);
			s.setSdbirth(birth);
			
			int mid=findMajor(mname);
		
			
			s.setMid(mid);
			studentMapper.insert(s);
		}
		
	}

	private int findMajor(String mname) {
		// TODO Auto-generated method stub
		Major param=new Major();
		param.setMdname(mname);
		Major major = majorMapper.selectOne(param);
		if(major==null){
			majorMapper.insertMajor(param);
			major=param;
			
		}
		return major.getMdId();
	}



}
