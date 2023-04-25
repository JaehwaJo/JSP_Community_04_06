package com.sbs.exam.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
public class Member {
  public int id;
  public String regDate;
  public String updateDate;
  public String loginId;
  public String loginPw;

  public Member(Map<String, Object> row) {
    this.id = (int) row.get("id");
    this.regDate = (String) row.get("regDate");
    this.updateDate = (String) row.get("updateDate");
    this.loginId = (String) row.get("loginId");
    this.loginPw = (String) row.get("loginPw");
  }
}