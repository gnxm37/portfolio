package com.trading.dto;

public class TradingNewsDto {
	private String newsdates;
	private String newstitles;
	private String newscontents;
	private String ymd_time;
    private String kospi;
    private String exchange;
    private String price;
    private String volume;
    private String cvolume;
    private Integer number;
    private String bs_type;
    private String ror;
    private String hbuy;
    private String mvolume;
    
	public String getNewsdates() {
		return newsdates;
	}
	public void setNewsdates(String newsdates) {
		this.newsdates = newsdates;
	}
	public String getNewstitles() {
		return newstitles;
	}
	public void setNewstitles(String newstitles) {
		this.newstitles = newstitles;
	}
	public String getNewscontents() {
		return newscontents;
	}
	public void setNewscontents(String newscontents) {
		this.newscontents = newscontents;
	}
	public String getYmd_time() {
		return ymd_time;
	}
	public void setYmd_time(String ymd_time) {
		this.ymd_time = ymd_time;
	}
	public String getKospi() {
		return kospi;
	}
	public void setKospi(String kospi) {
		this.kospi = kospi;
	}
	public String getExchange() {
		return exchange;
	}
	public void setExchange(String exchange) {
		this.exchange = exchange;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getVolume() {
		return volume;
	}
	public void setVolume(String volume) {
		this.volume = volume;
	}
	public String getCvolume() {
		return cvolume;
	}
	public void setCvolume(String cvolume) {
		this.cvolume = cvolume;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public String getBs_type() {
		return bs_type;
	}
	public void setBs_type(String bs_type) {
		this.bs_type = bs_type;
	}
	public String getRor() {
		return ror;
	}
	public void setRor(String ror) {
		this.ror = ror;
	}
	public String getHbuy() {
		return hbuy;
	}
	public void setHbuy(String hbuy) {
		this.hbuy = hbuy;
	}
	public String getMvolume() {
		return mvolume;
	}
	public void setMvolume(String mvolume) {
		this.mvolume = mvolume;
	}
	
	@Override
	public String toString() {
		return "TradingNewsDto [newsdates=" + newsdates + ", newstitles=" + newstitles + ", newscontents="
				+ newscontents + ", ymd_time=" + ymd_time + ", kospi=" + kospi + ", exchange=" + exchange + ", price="
				+ price + ", volume=" + volume + ", cvolume=" + cvolume + ", number=" + number + ", bs_type=" + bs_type
				+ ", ror=" + ror + ", hbuy=" + hbuy + ", mvolume=" + mvolume + "]";
	}
    
}
