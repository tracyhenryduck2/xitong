package com.message;

public class AppLoginParamBean {
        private String appTid;
        private String token;
		public String getAppTid() {
			return appTid;
		}
		public void setAppTid(String appTid) {
			this.appTid = appTid;
		}
		public String getToken() {
			return token;
		}
		public void setToken(String token) {
			this.token = token;
		}
		@Override
		public String toString() {
			return "AppLoginParamBean [appTid=" + appTid + ", token=" + token + "]";
		}
        
        
}
