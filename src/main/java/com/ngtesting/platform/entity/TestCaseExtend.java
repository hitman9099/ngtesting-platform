package com.ngtesting.platform.entity;

import javax.persistence.*;

@Entity
@Table(name = "tst_case_extend")
public class TestCaseExtend {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;

    private String prop01;
	private String prop02;
	private String prop03;
	private String prop04;
	private String prop05;
	private String prop06;
	private String prop07;
	private String prop08;
	private String prop09;
	private String prop10;

	private String prop11;
	private String prop12;
	private String prop13;
	private String prop14;
	private String prop15;
	private String prop16;
	private String prop17;
	private String prop18;
	private String prop19;
	private String prop20;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProp01() {
		return prop01;
	}

	public void setProp01(String prop01) {
		this.prop01 = prop01;
	}

	public String getProp02() {
		return prop02;
	}

	public void setProp02(String prop02) {
		this.prop02 = prop02;
	}

	public String getProp03() {
		return prop03;
	}

	public void setProp03(String prop03) {
		this.prop03 = prop03;
	}

	public String getProp04() {
		return prop04;
	}

	public void setProp04(String prop04) {
		this.prop04 = prop04;
	}

	public String getProp05() {
		return prop05;
	}

	public void setProp05(String prop05) {
		this.prop05 = prop05;
	}

	public String getProp06() {
		return prop06;
	}

	public void setProp06(String prop06) {
		this.prop06 = prop06;
	}

	public String getProp07() {
		return prop07;
	}

	public void setProp07(String prop07) {
		this.prop07 = prop07;
	}

	public String getProp08() {
		return prop08;
	}

	public void setProp08(String prop08) {
		this.prop08 = prop08;
	}

	public String getProp09() {
		return prop09;
	}

	public void setProp09(String prop09) {
		this.prop09 = prop09;
	}

	public String getProp10() {
		return prop10;
	}

	public void setProp10(String prop10) {
		this.prop10 = prop10;
	}

	public String getProp11() {
		return prop11;
	}

	public void setProp11(String prop11) {
		this.prop11 = prop11;
	}

	public String getProp12() {
		return prop12;
	}

	public void setProp12(String prop12) {
		this.prop12 = prop12;
	}

	public String getProp13() {
		return prop13;
	}

	public void setProp13(String prop13) {
		this.prop13 = prop13;
	}

	public String getProp14() {
		return prop14;
	}

	public void setProp14(String prop14) {
		this.prop14 = prop14;
	}

	public String getProp15() {
		return prop15;
	}

	public void setProp15(String prop15) {
		this.prop15 = prop15;
	}

	public String getProp16() {
		return prop16;
	}

	public void setProp16(String prop16) {
		this.prop16 = prop16;
	}

	public String getProp17() {
		return prop17;
	}

	public void setProp17(String prop17) {
		this.prop17 = prop17;
	}

	public String getProp18() {
		return prop18;
	}

	public void setProp18(String prop18) {
		this.prop18 = prop18;
	}

	public String getProp19() {
		return prop19;
	}

	public void setProp19(String prop19) {
		this.prop19 = prop19;
	}

	public String getProp20() {
		return prop20;
	}

	public void setProp20(String prop20) {
		this.prop20 = prop20;
	}
}
