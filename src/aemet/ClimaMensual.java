package aemet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "clima_mensual")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClimaMensual {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String indicativo;
	private String p_max;

	private Integer nw_55;

	private Double tm_min;
	private String ta_max;
	private Double ts_min;

	private Integer nt_30;
	private String w_racha;
	private Integer np_100;

	private Integer nw_91;
	private Integer np_001;
	private String ta_min;
	private String w_rec;
	private Integer np_300;
	private Double p_mes;
	private Integer w_med;
	private Integer nt_00;
	private Double ti_max;
	private Double tm_mes;
	private Double tm_max;
	private Integer np_010;
	private String fecha;

	public ClimaMensual() {

	}

	

	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getIndicativo() {
		return indicativo;
	}



	public void setIndicativo(String indicativo) {
		this.indicativo = indicativo;
	}



	public String getP_max() {
		return p_max;
	}



	public void setP_max(String p_max) {
		this.p_max = p_max;
	}



	public Integer getNw_55() {
		return nw_55;
	}



	public void setNw_55(Integer nw_55) {
		this.nw_55 = nw_55;
	}



	public Double getTm_min() {
		return tm_min;
	}



	public void setTm_min(Double tm_min) {
		this.tm_min = tm_min;
	}



	public String getTa_max() {
		return ta_max;
	}



	public void setTa_max(String ta_max) {
		this.ta_max = ta_max;
	}



	public Double getTs_min() {
		return ts_min;
	}



	public void setTs_min(Double ts_min) {
		this.ts_min = ts_min;
	}



	public Integer getNt_30() {
		return nt_30;
	}



	public void setNt_30(Integer nt_30) {
		this.nt_30 = nt_30;
	}



	public String getW_racha() {
		return w_racha;
	}



	public void setW_racha(String w_racha) {
		this.w_racha = w_racha;
	}



	public Integer getNp_100() {
		return np_100;
	}



	public void setNp_100(Integer np_100) {
		this.np_100 = np_100;
	}



	public Integer getNw_91() {
		return nw_91;
	}



	public void setNw_91(Integer nw_91) {
		this.nw_91 = nw_91;
	}



	public Integer getNp_001() {
		return np_001;
	}



	public void setNp_001(Integer np_001) {
		this.np_001 = np_001;
	}



	public String getTa_min() {
		return ta_min;
	}



	public void setTa_min(String ta_min) {
		this.ta_min = ta_min;
	}



	public String getW_rec() {
		return w_rec;
	}



	public void setW_rec(String w_rec) {
		this.w_rec = w_rec;
	}



	public Integer getNp_300() {
		return np_300;
	}



	public void setNp_300(Integer np_300) {
		this.np_300 = np_300;
	}



	public Double getP_mes() {
		return p_mes;
	}



	public void setP_mes(Double p_mes) {
		this.p_mes = p_mes;
	}



	public Integer getW_med() {
		return w_med;
	}



	public void setW_med(Integer w_med) {
		this.w_med = w_med;
	}



	public Integer getNt_00() {
		return nt_00;
	}



	public void setNt_00(Integer nt_00) {
		this.nt_00 = nt_00;
	}



	public Double getTi_max() {
		return ti_max;
	}



	public void setTi_max(Double ti_max) {
		this.ti_max = ti_max;
	}



	public Double getTm_mes() {
		return tm_mes;
	}



	public void setTm_mes(Double tm_mes) {
		this.tm_mes = tm_mes;
	}



	public Double getTm_max() {
		return tm_max;
	}



	public void setTm_max(Double tm_max) {
		this.tm_max = tm_max;
	}



	public Integer getNp_010() {
		return np_010;
	}



	public void setNp_010(Integer np_010) {
		this.np_010 = np_010;
	}



	public String getFecha() {
		return fecha;
	}



	public void setFecha(String fecha) {
		this.fecha = fecha;
	}



	@Override
	public String toString() {
		return "ClimaMensual [id=" + id + ", indicativo=" + indicativo + ", p_max=" + p_max + ", nw_55=" + nw_55
				+ ", tm_min=" + tm_min + ", ta_max=" + ta_max + ", ts_min=" + ts_min + ", nt_30=" + nt_30 + ", w_racha="
				+ w_racha + ", np_100=" + np_100 + ", nw_91=" + nw_91 + ", np_001=" + np_001 + ", ta_min=" + ta_min
				+ ", w_rec=" + w_rec + ", np_300=" + np_300 + ", p_mes=" + p_mes + ", w_med=" + w_med + ", nt_00="
				+ nt_00 + ", ti_max=" + ti_max + ", tm_mes=" + tm_mes + ", tm_max=" + tm_max + ", np_010=" + np_010
				+ ", fecha=" + fecha + "]";
	}

}
