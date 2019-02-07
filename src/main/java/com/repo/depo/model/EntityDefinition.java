package com.repo.depo.model;

public class EntityDefinition {
	
	

	private String[] collectionName;
	
	public String[] getCollectionName() {
		return collectionName;
	}

	public void setCollectionName(String[] collectionName) {
		this.collectionName = collectionName;
	}

	private class FieldDefinition {
		
		
		public String getLabel() {
			return label;
		}
		public void setLabel(String label) {
			this.label = label;
		}
		public String getDataType() {
			return dataType;
		}
		public void setDataType(String dataType) {
			this.dataType = dataType;
		}
		public Boolean getHidden() {
			return hidden;
		}
		public void setHidden(Boolean hidden) {
			this.hidden = hidden;
		}
		private String label;
		private String dataType;
		private Boolean hidden;
		
	}
}
