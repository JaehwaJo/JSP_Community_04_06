package com.sbs.exam.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class Util {
  public static boolean empty(HttpServletRequest req, String paramName) {
    String paramValue = req.getParameter(paramName);

    return empty(paramValue);
  }

  public static boolean empty(Object obj) {
    if (obj == null) {
      return true;
    }

    if (obj instanceof String) {
      return ((String) obj).trim().length() == 0;
    }

    return true;
  }

  public static boolean isNum(HttpServletRequest req, String paramName) {
    String paramValue = req.getParameter(paramName);

    return isNum(paramValue);
  }

  public static boolean isNum(Object obj) {
    if (obj == null) {
      return false;
    }

    if (obj instanceof Long) {
      return true;
    } else if (obj instanceof Integer) {
      return true;
    } else if (obj instanceof String) {
      try {
        Integer.parseInt((String) obj);
        return true;
      } catch (NumberFormatException e) {
        return false;
      }
    }

    return false;
  }

  public static int getInt(HttpServletRequest req, String paramName) {
    return Integer.parseInt(req.getParameter(paramName));
  }

  public static void printEx(String errName, HttpServletResponse resp, Exception e) {
    try {
      resp.getWriter()
          .append("<h1 style='color:red; font-weight:bold; text-align:left;'>[에러 : " + errName + "]</h1>");

      resp.getWriter().append("<pre style='text-align:left; font-weight:bold; font-size:1.3rem;'>");
      e.printStackTrace(resp.getWriter());
      resp.getWriter().append("</pre>");
    } catch (IOException e1) {
      e1.printStackTrace();
    }
  }

  public static String getString(HttpServletRequest req, String paramName) {
    return req.getParameter(paramName);
  }

  public static String f(String format, Object... args) {
    return String.format(format, args);
  }

  public static Map<String, Object> mapOf(Object... args) {
    if (args.length % 2 != 0) {
      throw new IllegalArgumentException("인자를 짝수개 입력해주세요.");
    }

    int size = args.length / 2;

    Map<String, Object> map = new LinkedHashMap<>();

    for (int i = 0; i < size; i++) {
      int keyIndex = i * 2;
      int valueIndex = keyIndex + 1;

      String key;
      Object value;

      try {
        key = (String) args[keyIndex];
      } catch (ClassCastException e) {
        throw new IllegalArgumentException("키는 String으로 입력해야 합니다. " + e.getMessage());
      }

      value = args[valueIndex];

      map.put(key, value);
    }

    return map;
  }

  public static String toJson(Object obj, String defaultValue) {
    ObjectMapper om = new ObjectMapper();

    try {
      return om.writeValueAsString(obj);
    } catch (JsonProcessingException e) {
      return defaultValue;
    }
  }
}