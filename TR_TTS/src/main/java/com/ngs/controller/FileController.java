package com.ngs.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.ngs.service.SysUser;
import com.ngs.util.FileMeta;
import com.ngs.util.FileSupport;





@Controller
@SessionAttributes("user")
public class FileController {
	
	static Logger log4j = Logger.getLogger(FileController.class);
	
	
	@RequestMapping("/fileUpload")
	public ModelAndView showFileUpload() {
		String message = " sample upload page !";
		return new ModelAndView("fileUpload", "msg", message);
	}
	

	

	//<!--@RequestMapping("/fileController")  -->


	/***************************************************
	 * URL: /rest/controller/upload  
	 * upload(): receives files
	 * @param request : MultipartHttpServletRequest auto passed
	 * @param response : HttpServletResponse auto passed
	 * @return LinkedList<FileMeta> as json format
	 ****************************************************/
	
	
	LinkedList<FileMeta> files = new LinkedList<FileMeta>();
	FileMeta fileMeta = null;
	
	
	@RequestMapping(value="/uploadMe",  headers="Accept=*", method = RequestMethod.POST,produces = "application/json")
	//public @ResponseBody LinkedList<FileMeta> upload(MultipartHttpServletRequest request, HttpServletResponse response, HttpSession session) {
	public @ResponseBody LinkedList<FileMeta> upload(MultipartHttpServletRequest request, HttpServletResponse response, HttpSession session) {
			 
		//1. build an iterator
		 Iterator<String> itr =  request.getFileNames();
		 MultipartFile mpf = null;

		 //2. get each file
		 while(itr.hasNext()){
			 
			 //2.1 get next MultipartFile
			 mpf = request.getFile(itr.next()); 
			 System.out.println(mpf.getOriginalFilename() +" uploaded! "+files.size());

			 //2.2 if files > 10 remove the first from the list
			 if(files.size() >= 10)
				 files.pop();
			 
			 //2.3 create new fileMeta
			 fileMeta = new FileMeta();
			 fileMeta.setFileName(mpf.getOriginalFilename());
			 //fileMeta.setFileSize(mpf.getSize()/1024+" Kb");
			 fileMeta.setFileSize(mpf.getSize()/1024+"");
			 fileMeta.setFileType(mpf.getContentType());
			 
			 
			 	// ** my addings for the file name creation.
			 
				//get current date
				DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		        Date date = new Date();
		        String dat = dateFormat.format(date); //2014/08/06 15:59:48
		        
		        // get HTML generator
		        //HtmlService htm = new HtmlService();

		        // create a new file name
				SysUser user = (SysUser)session.getAttribute("user");
				
				// Create a new menu for the rendering views after the file upload
				// String menu = htm.generateMenu(user.getRolleId());
				
					// get the original file name
					String newFileName = fileMeta.getFileName().substring(0,fileMeta.getFileName().lastIndexOf("."));
					// get the extention
				    String extention = fileMeta.getFileName().substring(fileMeta.getFileName().lastIndexOf("."));
				    // creating a new file name
				    newFileName = user.getId()+"-"+dat+"-"+newFileName+extention;


					  
			    
			    // *** end of  my addinggs
			 
			 
			 
			 try {
				fileMeta.setBytes(mpf.getBytes());
				
				// further added to check whether file is already exists
				File newFile = new File("/home/admin/nextgenmed/labreports/"+user.getId()+"/"+ newFileName);
				
				if (!newFile.exists()) {
					// newFile.createNewFile();
					// FileCopyUtils.copy(mpf.getBytes(), new FileOutputStream("C:/wamp/www/video/"+mpf.getOriginalFilename()));
					
					FileCopyUtils.copy(mpf.getBytes(), new FileOutputStream("/home/admin/nextgenmed/labreports/"+user.getId()+"/"+ newFileName));

					
				}else{
					//return new ModelAndView("hello", "message", mpf.getOriginalFilename() + " Same file name already exists ! ").addObject("menu",menu);
					//return "redirect:/hello.htm?message="+fileName+ " Same file name already exists ! ";
					return null;
				}
				

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 //2.4 add to files
			 files.add(fileMeta);
			 
		 }
		 
		// result will be like this
		// [{"fileName":"app_engine-85x77.png","fileSize":"8 Kb","fileType":"image/png"},...]
		return files;
 
	}
	
	
	
	@RequestMapping(value="/uploadLabReports",  headers="Accept=*", method = RequestMethod.POST,produces = "application/json")
	//public @ResponseBody LinkedList<FileMeta> upload(MultipartHttpServletRequest request, HttpServletResponse response, HttpSession session) {
	public @ResponseBody LinkedList<FileMeta> uploadLabReports(@RequestParam("medtestid") String medtestid, MultipartHttpServletRequest request, HttpServletResponse response, HttpSession session) {
			
		System.out.println("################33 medtestid "+medtestid);
		//1. build an iterator
		 Iterator<String> itr =  request.getFileNames();
		 MultipartFile mpf = null;

		 //2. get each file
		 while(itr.hasNext()){
			 
			 //2.1 get next MultipartFile
			 mpf = request.getFile(itr.next()); 
			 System.out.println(mpf.getOriginalFilename() +" uploaded! "+files.size());

			 //2.2 if files > 10 remove the first from the list
			 if(files.size() >= 10)
				 files.pop();
			 
			 //2.3 create new fileMeta
			 fileMeta = new FileMeta();
			 fileMeta.setFileName(mpf.getOriginalFilename());
			 //fileMeta.setFileSize(mpf.getSize()/1024+" Kb");
			 fileMeta.setFileSize(mpf.getSize()/1024+"");
			 fileMeta.setFileType(mpf.getContentType());
			 
			 
			 	// ** my addings for the file name creation.
			 
				//get current date
				DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		        Date date = new Date();
		        String dat = dateFormat.format(date); //2014/08/06 15:59:48
		        
		        // get HTML generator
		        //HtmlService htm = new HtmlService();

		        // create a new file name
				SysUser user = (SysUser)session.getAttribute("user");
				
				// Create a new menu for the rendering views after the file upload
				// String menu = htm.generateMenu(user.getRolleId());
				
					// get the original file name
					String newFileName = fileMeta.getFileName().substring(0,fileMeta.getFileName().lastIndexOf("."));
					// get the extention
				    String extention = fileMeta.getFileName().substring(fileMeta.getFileName().lastIndexOf("."));
				    // creating a new file name
				   // newFileName = user.getId()+"-"+dat+"-"+newFileName+extention;
				    newFileName = medtestid+extention;


					  
			    
			    // *** end of  my addinggs
			 
			 
			 
			 try {
				fileMeta.setBytes(mpf.getBytes());
				
				//check whether location is exists
				FileSupport fs = new FileSupport();
				try {
					fs.createFolder("/home/admin/nextgenmed/labreports/"+medtestid+"/");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					log4j.error("folder path creation error in labreports "+medtestid+"  - "+e.getMessage());
				}
			
				
				// further added to check whether file is already exists
				File newFile = new File("/home/admin/nextgenmed/labreports/"+medtestid+"/"+ newFileName);
				
				if (!newFile.exists()) {
					//newFile.createNewFile();
					// FileCopyUtils.copy(mpf.getBytes(), new FileOutputStream("C:/wamp/www/video/"+mpf.getOriginalFilename()));
					
					FileCopyUtils.copy(mpf.getBytes(), new FileOutputStream("/home/admin/nextgenmed/labreports/"+medtestid+"/"+ newFileName));

					
				}else{
					//return new ModelAndView("hello", "message", mpf.getOriginalFilename() + " Same file name already exists ! ").addObject("menu",menu);
					//return "redirect:/hello.htm?message="+fileName+ " Same file name already exists ! ";
					return null;
				}
				

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 //2.4 add to files
			 files.add(fileMeta);
			 
		 }
		 
		// result will be like this
		// [{"fileName":"app_engine-85x77.png","fileSize":"8 Kb","fileType":"image/png"},...]
		return files;
 
	}
	
	
	
	/**
     * Size of a byte buffer to read/write file
     */
    private static final int BUFFER_SIZE = 4096;
             
    /**
     * Path of the file to be downloaded, relative to application's directory
     */
    private String filePath = "/downloads/SpringProject.zip";
     
    /**
     * Method for handling file download request from client
     */
   
    @RequestMapping(value = "/downloadLabReport" , method = RequestMethod.GET) 
    public void doDownload(@RequestParam("medtestid") String medtestid, HttpServletRequest request,HttpServletResponse response) throws IOException {
    	
    
    	/**
        // get absolute path of the application
        ServletContext context = request.getServletContext();
        String appPath = context.getRealPath("");
        System.out.println("appPath = " + appPath);
        String fullPath = appPath + filePath; 
 		**/
        // construct the complete absolute path of the file
       // String fullPath ="/home/admin/nextgenmed/labreports/"+medtestid+"/"+ newFileName;    
    	String fullPath ="/home/admin/nextgenmed/labreports/"+medtestid+"/"+medtestid+".jpg"; 
        File downloadFile = new File(fullPath);
        FileInputStream inputStream = new FileInputStream(downloadFile);
         
        /**
        // get MIME type of the file
        String mimeType = context.getMimeType(fullPath);
        if (mimeType == null) {
            // set to binary type if MIME mapping not found
            mimeType = "application/octet-stream";
        }
        System.out.println("MIME type: " + mimeType);
 		**/
 		
        // set content attributes for the response
        //response.setContentType(mimeType);
        response.setContentLength((int) downloadFile.length());
 
        // set headers for the response
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                downloadFile.getName());
        response.setHeader(headerKey, headerValue);
 
        // get output stream of the response
        OutputStream outStream = response.getOutputStream();
 
        byte[] buffer = new byte[BUFFER_SIZE];
        int bytesRead = -1;
 
        // write bytes read from the input stream into the output stream
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }
 
        inputStream.close();
        outStream.close();
 
    }
	

}
