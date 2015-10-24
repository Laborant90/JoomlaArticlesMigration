package com.jobvms;

/**
 * Builder class for Joomla! article object  
 *  
 * @author Spitchenko VM 
 * 
 */

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.journal.model.JournalArticle;
import com.liferay.portlet.journal.model.JournalArticleConstants;
import com.liferay.portlet.journal.service.JournalArticleLocalServiceUtil;

public class JoomlaArticleBuilder {
	
	
	public static JoomlaArticle buildFromResultSet(ResultSet rs) throws SQLException, ParseException {
		 
		
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-mm-dd HH:MM:SS");
		JoomlaArticle joomlaArticle = new JoomlaArticle();		
		joomlaArticle.setId(rs.getLong("id"));
		joomlaArticle.setAssetId( rs.getLong("asset_id"));
		joomlaArticle.setTitle(rs.getString("title"));
		joomlaArticle.setAlias(rs.getString("alias"));
		joomlaArticle.setTitleAlias(rs.getString("title_alias"));
		joomlaArticle.setIntroText(rs.getString("introtext"));
		joomlaArticle.setFullText(rs.getString("fulltext"));
		joomlaArticle.setState(rs.getString("state"));
		joomlaArticle.setSectionId(rs.getLong("sectionid"));
		joomlaArticle.setMask(rs.getString("mask"));
		joomlaArticle.setCatid(rs.getLong("catid"));
		try {
			joomlaArticle.setCreated(sdf.parse(rs.getString("created")));
		} catch(SQLException e) {
			joomlaArticle.setCreated(new Date());
		}
		joomlaArticle.setCreatedBy(rs.getLong("created_by"));
		joomlaArticle.setCreatedByAlias(rs.getString("created_by_alias"));
		try {
			joomlaArticle.setModified(sdf.parse(rs.getString("modified")));
		} catch(SQLException e) {
			joomlaArticle.setModified(new Date());
		}
		joomlaArticle.setModifiedBy(rs.getLong("modified_by"));
		joomlaArticle.setCheckedOut(rs.getLong("checked_out"));
		try {
			joomlaArticle.setCheckedOutTime(sdf.parse(rs.getString("checked_out_time")));
		} catch(SQLException e) {
			joomlaArticle.setCheckedOutTime(new Date());
		}
		joomlaArticle.setAttribs(rs.getString("attribs"));
		joomlaArticle.setVersion(rs.getLong("version"));
		joomlaArticle.setParentId(rs.getLong("parentid"));
		joomlaArticle.setOrdering(rs.getLong("ordering"));
		joomlaArticle.setMetaKey(rs.getString("metakey"));
		joomlaArticle.setMetaDesc(rs.getString("metadesc"));
		joomlaArticle.setAccess(rs.getLong("access"));
		joomlaArticle.setHits(rs.getLong("hits"));
		joomlaArticle.setMetaData(rs.getString("metadata"));
		joomlaArticle.setFeatured(rs.getBoolean("featured"));
		joomlaArticle.setLanguage(rs.getString("language"));
		joomlaArticle.setXreference(rs.getString("xreference")); 	
		System.out.println("resultSet readed!");
		return joomlaArticle;
	}
	public static JournalArticle convertToJournalArticle(
			Long userId, 
			Long groupId,
			Long folderId,
			Long classNameId, 
			Long classPK,
			JoomlaArticle joomlaArticle) throws PortalException, SystemException {
		
			Map<Locale, String> titleMap = new HashMap<>();
			//<?xml version='1.0' encoding='UTF-8'?><root available-locales="ru_RU" default-locale="ru_RU"><Title language-id="ru_RU">тестовая статья</Title></root> 
			titleMap.put(Locale.getDefault(), joomlaArticle.getTitle());
			
			Map<Locale, String> descriptionMap = new HashMap<>();
			//The method addArticle(long,  long,    long,      long,       long,    String,    boolean,       double,  Map<Locale,String>, Map<Locale,String>, String,  String, String,           String,        String,     int,               int,           int,              int,            int,               int,                 int,               int,                int,                 int,                  boolean,     int,             int,          int,             int,           int,              boolean,     boolean,   boolean,    String,        File,               Map<String,byte[]>, String,     ServiceContext) in the type JournalArticleLocalServiceUtil is not applicable for the arguments (Long, Long, Long, Long, Long, Long, Long, Long, Map<String,String>, Map<String,String>, String, String, null, null, null, int, int, int, int, int, int, int, int, int, int, boolean, int, int, int, int, int, boolean, boolean, null, null, null, null, String, null) 
			//                     userId, groupId, folderId, classNameId, classPK, articleId, autoArticleId, version, titleMap,            descriptionMap,    content, type,   ddmStructureKey, ddmTemplateKey, layoutUuid, displayDateMonth, displayDateDay, displayDateYear, displayDateHour, displayDateMinute, expirationDateMonth, expirationDateDay, expirationDateYear, expirationDateHour, expirationDateMinute, neverExpire, reviewDateMonth, reviewDateDay, reviewDateYear, reviewDateHour, reviewDateMinute, neverReview, indexable, smallImage, smallImageURL, smallImageFile,     images,             articleURL, serviceContext)
			ServiceContext serviceContext = new ServiceContext();
			serviceContext.setScopeGroupId(groupId);
			
			return JournalArticleLocalServiceUtil.addArticle(
				userId, 		//userId long
				groupId, 		// groupId long
				folderId, 		//folderId long
				classNameId, 	//classNameId long
				classPK,		//classPK  long
				joomlaArticle.getId().toString(), //articleId  String
				true, 			//autoArticleId boolean
				joomlaArticle.getVersion().doubleValue(), //version double
				titleMap,		//titleMap Map<Locale,String>  
				descriptionMap, //descriptionMap Map<Locale,String> 
				"<?xml version=\"1.0\"?><root available-locales=\"" + Locale.getDefault()  + "\" default-locale=\"" + Locale.getDefault() +"\"><static-content language-id=\"ru_RU\"><![CDATA["+ joomlaArticle.getIntroText() + "]]></static-content></root>", //content String
				JournalArticleConstants.TYPES[0].toString(), //type String
				"", //ddmStructureKey, String 
				"", //ddmTemplateKey, String 
				"", //layoutUuid String 
				new Date().getMonth(), //joomlaArticle.getCreated().getMonth(),//displayDateMonth, 
				new Date().getDay(), //joomlaArticle.getCreated().getDay(), //displayDateDay, 
				new Date().getYear(), //joomlaArticle.getCreated().getYear(), //displayDateYear, 
				new Date().getHours(),// joomlaArticle.getCreated().getHours(), //displayDateHour, 
				new Date().getMinutes(),// joomlaArticle.getCreated().getMinutes(),//displayDateMinute, 
				new Date().getMonth(),// joomlaArticle.getCreated().getMonth(),//expirationDateMonth, 
				new Date().getDay(), //joomlaArticle.getCreated().getDay(),//expirationDateDay, 
				new Date().getYear(), //joomlaArticle.getCreated().getYear(),//expirationDateYear, 
				new Date().getHours(), //joomlaArticle.getCreated().getHours(),//expirationDateHour, 
				new Date().getMinutes(), //joomlaArticle.getCreated().getMinutes(),//expirationDateMinute, 
				true,//neverExpire, 
				new Date().getMonth(), //joomlaArticle.getCreated().getMonth(),//reviewDateMonth, 
				new Date().getDay(), //joomlaArticle.getCreated().getDay(),//reviewDateDay, 
				new Date().getYear(), //joomlaArticle.getCreated().getYear(),//reviewDateYear, 
				new Date().getHours(), //joomlaArticle.getCreated().getHours(),//reviewDateHour, 
				new Date().getMinutes(), //joomlaArticle.getCreated().getMinutes(),//reviewDateMinute, 
				true, //neverReview, 
				true, //indexable, 
				false,//smallImage, 
				"",//smallImageURL, 
				null,//smallImageFile, 
				null,//images, 
				joomlaArticle.getUrls(), //articleURL, 
				serviceContext);
		

		
	}

}
