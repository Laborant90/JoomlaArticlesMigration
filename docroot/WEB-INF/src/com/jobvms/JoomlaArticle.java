package com.jobvms;

/**
 * Java object which represents Joomla! article.  
 *  
 * @author Spitchenko VM 
 * 
 */

import java.util.Date;

public class JoomlaArticle {
	private Long id;
	private Long assetId;
	private String title;
	private String alias;
	private String titleAlias;
	private String introText;
	private String fullText;
	private String state;
	private Long sectionId;
	private String mask;
	private Long catid;
	private Date created;
	private Long createdBy;
	private String createdByAlias;
	private Date modified;
	private Long modifiedBy;
	private Long checkedOut;
	private Date checkedOutTime;
	private Date publishUp;
	private Date publishDown;
	private String images;
	private String urls;
	private String attribs;
	private Long version;
	private Long parentId;
	private Long ordering;
	private String metaKey;
	private String metaDesc;
	private Long access;
	private Long hits;
	private String metaData;
	private Boolean featured;
	private String language;
	private String xreference;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getAssetId() {
		return assetId;
	}
	public void setAssetId(Long assetId) {
		this.assetId = assetId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getTitleAlias() {
		return titleAlias;
	}
	public void setTitleAlias(String titleAlias) {
		this.titleAlias = titleAlias;
	}
	public String getIntroText() {
		return introText;
	}
	public void setIntroText(String introText) {
		this.introText = introText;
	}
	public String getFullText() {
		return fullText;
	}
	public void setFullText(String fullText) {
		this.fullText = fullText;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Long getSectionId() {
		return sectionId;
	}
	public void setSectionId(Long sectionId) {
		this.sectionId = sectionId;
	}
	public String getMask() {
		return mask;
	}
	public void setMask(String mask) {
		this.mask = mask;
	}
	public Long getCatid() {
		return catid;
	}
	public void setCatid(Long catid) {
		this.catid = catid;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Long getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}
	public String getCreatedByAlias() {
		return createdByAlias;
	}
	public void setCreatedByAlias(String createdByAlias) {
		this.createdByAlias = createdByAlias;
	}
	public Date getModified() {
		return modified;
	}
	public void setModified(Date modified) {
		this.modified = modified;
	}
	public Long getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public Long getCheckedOut() {
		return checkedOut;
	}
	public void setCheckedOut(Long checkedOut) {
		this.checkedOut = checkedOut;
	}
	public Date getCheckedOutTime() {
		return checkedOutTime;
	}
	public void setCheckedOutTime(Date checkedOutTime) {
		this.checkedOutTime = checkedOutTime;
	}
	public Date getPublishUp() {
		return publishUp;
	}
	public void setPublishUp(Date publishUp) {
		this.publishUp = publishUp;
	}
	public Date getPublishDown() {
		return publishDown;
	}
	public void setPublishDown(Date publishDown) {
		this.publishDown = publishDown;
	}
	public String getImages() {
		return images;
	}
	public void setImages(String images) {
		this.images = images;
	}
	public String getUrls() {
		return urls;
	}
	public void setUrls(String urls) {
		this.urls = urls;
	}
	public String getAttribs() {
		return attribs;
	}
	public void setAttribs(String attribs) {
		this.attribs = attribs;
	}
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public Long getOrdering() {
		return ordering;
	}
	public void setOrdering(Long ordering) {
		this.ordering = ordering;
	}
	public String getMetaKey() {
		return metaKey;
	}
	public void setMetaKey(String metaKey) {
		this.metaKey = metaKey;
	}
	public String getMetaDesc() {
		return metaDesc;
	}
	public void setMetaDesc(String metaDesc) {
		this.metaDesc = metaDesc;
	}
	public Long getAccess() {
		return access;
	}
	public void setAccess(Long access) {
		this.access = access;
	}
	public Long getHits() {
		return hits;
	}
	public void setHits(Long hits) {
		this.hits = hits;
	}
	public String getMetaData() {
		return metaData;
	}
	public void setMetaData(String metaData) {
		this.metaData = metaData;
	}
	public Boolean getFeatured() {
		return featured;
	}
	public void setFeatured(Boolean featured) {
		this.featured = featured;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getXreference() {
		return xreference;
	}
	public void setXreference(String xreference) {
		this.xreference = xreference;
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("JoomlaArticle [id=" + id);
		sb.append(", assetId=" + assetId);
		sb.append(", title=" + title);
		sb.append(", alias=" + alias); 
		sb.append(", titleAlias=" + titleAlias);
		sb.append(", introText=" + introText);
		sb.append(", fullText=" + fullText);
		sb.append(", state=" + state);
		sb.append(", sectionId=" + sectionId);
		sb.append(", mask="	+ mask);
		sb.append(", catid=" + catid); 
		sb.append(", created=" + created);
		sb.append( ", createdBy=" + createdBy);
		sb.append(", createdByAlias="+ createdByAlias);
		sb.append(", modified=" + modified );
		sb.append(", modifiedBy=" + modifiedBy);
		sb.append(", checkedOut=" + checkedOut);
		sb.append(", checkedOutTime=" + checkedOutTime);
		sb.append(", publishUp="+ publishUp );
		sb.append(", publishDown=" + publishDown ); 
		sb.append(", images=" + images);
		sb.append(", urls=" + urls); 
		sb.append(", attribs=" + attribs);
		sb.append( ", version=" + version ); 
		sb.append(", parentId=" + parentId);
		sb.append( ", ordering=" + ordering ); 
		sb.append(", metaKey=" + metaKey);
		sb.append( ", metaDesc=" + metaDesc ); 
		sb.append(", access=" + access ); 
		sb.append(", hits=" + hits ); 
		sb.append(", metaData=" + metaData ); 
		sb.append(", featured=" + featured);
		sb.append(", language=" + language ); 
		sb.append(", xreference=" + xreference);
		sb.append("]");
		return sb.toString();
	}
	
	
	
}
