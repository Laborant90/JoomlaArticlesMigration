package com.jobvms;

/**
 * Portlet implementation class JoomlaArticlesMigrationPortlet
 * Utility for import data to JournalArticles Liferay Portlet from Joomla! CMS database.
 * This Utility get content data from "_content" table Joomla! CMS database and create
 * JournalArticles for each article id "_content". 
 *  
 * @author Spitchenko VM 
 * 
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

public class JoomlaArticlesMigrationPortlet extends MVCPortlet {
		
		/**
	     * Method handles POST request from portlet View
	     *
	     * @param request  - POST request with params
	     * @param response - responce with operation results 
	     *  
	     * @throws PortletException, IOException
	     *  
	     */
		@Override
		public void serveResource(ResourceRequest request, ResourceResponse response) throws PortletException, IOException {
			
			PrintWriter writer = response.getWriter();
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
			try {
				System.out.println("Started import from Joomla content table");
				//get data from request
				System.out.println("content_table_name" + 	ParamUtil.getString(request,"content_table_name"));	
				System.out.println("user" 				+ 	ParamUtil.getString(request,"user"));							
			    System.out.println("url" 				+ 	ParamUtil.getString(request,"url"));
				System.out.println("driver" 			+ 	ParamUtil.getString(request,"driver"));
				
				System.out.println("liferay_user" + ParamUtil.getLong(request,"liferay_user"));
				System.out.println("group_id" + ParamUtil.getLong(request,"group_id"));
				
				Integer count = getDataViaJDBC(
						ParamUtil.getString(request,"content_table_name"), //contentTableName
						ParamUtil.getString(request,"user"),			   //user
						ParamUtil.getString(request,"password") ,   	   //password
						ParamUtil.getString(request,"url"),				   //url
						ParamUtil.getString(request,"driver"),			   //driver
						ParamUtil.getLong(request,"liferay_user"),   	   //userId	
						ParamUtil.getLong(request,"group_id"),   		   //groupId
				        (long) 0,										   //folderId	 
				        (long) 0, 										   //classNameId
				        (long) 0);								           //classPK
				
				jsonObject.put("status", "SUCCESS" );
				jsonObject.put("message", count + "articles was imported succesfully !");
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("Import from Joomla content table stoped with ERROR :"); 				 
				jsonObject.put("status", "ERROR" );
				jsonObject.put("message", e.getLocalizedMessage());				
				e.printStackTrace();
			} finally {
				writer.write(jsonObject.toString());
			}
	 	}
		
		
		/**
	     * Method get data from Joomla! database
	     *
	     * @param contentTableName - "_content" table name
	     * @param user - database user for JDBC connection
	     * @param password - database password for JDBC connection
	     * @param url - database url for JDBC connection
	     * @param driver - database url for JDBC connection (usually MySQL)
	     * @param userId - Liferay userId (this user sets as author of imported articles)
	     * @param groupId - Liferay groupId (group where articles imported)
	     * @param folderId -  articles folderId param (not present in "_content")
	     * @param classNameId -  articles classNameId param (not present in "_content")
	     * @param classPK -  articles classPK param (not present in "_content")
	     *  
	     * @throws PortletException, IOException, ParseException
	     *  
	     */
		private Integer getDataViaJDBC(
			  String contentTableName,
	          String user,			
	          String password, 
	          String url,
	          String driver,
	          Long userId, 
	          Long groupId, 
	          Long folderId, 
	          Long classNameId, 
	          Long classPK) throws ClassNotFoundException, SQLException, PortalException, SystemException, ParseException {	           
	          Class.forName(driver);	          
	          Connection c = null;
	          Integer articlesCount = 0;
	          c = DriverManager.getConnection(url, user, password);
	          StringBuilder sb = new StringBuilder();
	          sb.append("select ");
	          sb.append("`id`,");
	          sb.append("`asset_id`,");
	          sb.append("`title`,");
	          sb.append("`alias`,");
	          sb.append("`title_alias`,");
	          sb.append("`introtext`,");
	          sb.append("`fulltext`,");
	          sb.append("`state`,");
	          sb.append("`sectionid`,");
	          sb.append("`mask`,");
	          sb.append("`catid`,");
	          sb.append("`created`,");
	          sb.append("`created_by`,");
	          sb.append("`created_by_alias`,");
			  sb.append("`modified`,");
	          sb.append("`modified_by`,");
	          sb.append("`checked_out`,");
	          sb.append("`checked_out_time`,");
	          sb.append("`attribs`,");
	          sb.append("`version`,");
	          sb.append("`parentid`,");
	          sb.append("`ordering`,");
	          sb.append("`metakey`,");
	          sb.append("`metadesc`,");
	          sb.append("`access`,");
	          sb.append("`hits`,");
	          sb.append("`metadata`,");
	          sb.append("`featured`,");
	          sb.append("`language`,");
	          sb.append("`xreference`"); 
	          sb.append(" from ");
	          sb.append(contentTableName);
	               
	          System.out.println("Executing query" + sb.toString());
	               
	          Statement st = c.createStatement();
	          ResultSet rs = st.executeQuery(sb.toString());
	          List<JoomlaArticle> joomlaArticles = new ArrayList<>();
	          while(rs.next()){
	        	  JoomlaArticle ja = JoomlaArticleBuilder.buildFromResultSet(rs);
	              System.out.println("Article readed " + ja.toString());
	              joomlaArticles.add(ja);
	              articlesCount++;
	          }
	          //creating liferay journal articles
	          for(JoomlaArticle ja : joomlaArticles) {
	        	  JoomlaArticleBuilder.convertToJournalArticle(userId, groupId, folderId, classNameId, classPK, ja);
	          }
	          if(c != null)
	          c.close();
	          return articlesCount;
	     }

}
