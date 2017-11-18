package com.message;

public class DeviceReportInfoParamBean extends ParamBean {
        private String devTid;
        
        private String mac;
        
        private String binVer;
        
        private String binType;
        
        private String ssid;

		public String getDevTid() {
			return devTid;
		}

		public void setDevTid(String devTid) {
			this.devTid = devTid;
		}

		public String getMac() {
			return mac;
		}

		public void setMac(String mac) {
			this.mac = mac;
		}

		public String getBinVer() {
			return binVer;
		}

		public void setBinVer(String binVer) {
			this.binVer = binVer;
		}

		public String getBinType() {
			return binType;
		}

		public void setBinType(String binType) {
			this.binType = binType;
		}

		public String getSsid() {
			return ssid;
		}

		public void setSsid(String ssid) {
			this.ssid = ssid;
		}
        
        
}
