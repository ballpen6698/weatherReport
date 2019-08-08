package com.example.WeatherReportDemo.entity;


public class Weather {
	
		private String description;

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		
		public String toString() {
			StringBuilder output = new StringBuilder();
			output.append("Description:").append(description);
			return output.toString();
		}
}
