package com.neutrinosys.employees;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Programmer extends Employee {

    private int locpd = 0;
    private int yoe = 0;
    private int iq = 0;
    private final String  progRegex = "\\w+\\=(?<locpd>\\w+)\\,\\w+\\=(?<yoe>\\w+)\\,\\w+\\=(?<iq>\\w+)";


    private final Pattern progPat = Pattern.compile(progRegex);

    public Programmer(String personText) {
      super(personText);
      Matcher prgMat = progPat.matcher(peopleMat.group("details"));
      if (prgMat.find()) {
          this.locpd = Integer.parseInt(prgMat.group("locpd"));
          this.yoe= Integer.parseInt(prgMat.group("yoe"));
          this.iq= Integer.parseInt(prgMat.group("iq"));

            }


    }

    public int getSalary(){
        return 3000 + locpd * yoe * iq;
    }

}
