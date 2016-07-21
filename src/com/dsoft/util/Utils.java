package com.dsoft.util;

import com.dsoft.entity.User;
import com.dsoft.service.AdminService;
import com.sun.mail.util.BASE64DecoderStream;
import com.sun.mail.util.BASE64EncoderStream;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.apache.oro.text.perl.Perl5Util;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigDecimal;
import java.net.URL;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Time;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;


public class Utils {
	private static Logger logger = Logger.getLogger(Utils.class);
    private static Cipher ecipher;
    private static Cipher dcipher;
    public static Integer globalCounter=0;
    private static final String ALGO = "AES";
    public static String AppName = "Point Of Sale";

  	@SuppressWarnings("unchecked")
	public static boolean isInRole(String roleName){
		List<GrantedAuthority> grantedRoles = (List<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        for(GrantedAuthority role : grantedRoles){
            logger.debug("SMNLOG:ROLE--->"+role.getAuthority());
			if(roleName.equals(role.getAuthority()))
				return true;
		}
		return false;
	}

    public static String getLoggedUserRoleName(){
        List<GrantedAuthority> grantedRoles = (List<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        String roleName="";
        for(GrantedAuthority role : grantedRoles){
            roleName = role.getAuthority();
        }
        return roleName;
    }

    public static boolean isNullOrEmpty(String content){
		if(content != null && !content.isEmpty())
			return false;
		else
			return true;
	}


    public static String getLoggedUserName(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = null ;
        name = auth.getName();
        return name;
    }

	public static String getMessageBundlePropertyValue(String key){
        try{
            Properties props = PropertiesLoaderUtils.loadProperties(new ClassPathResource(Constants.MESSAGE_FILE_PATH));
            return props.getProperty(key);
		}catch(Exception ex){
			logger.debug("error : " + ex);
			return "";
		}
	}
	
	public static String getApplicationPropertyValue(String key){
        try{
            Properties props = PropertiesLoaderUtils.loadProperties(new ClassPathResource(Constants.APPLICATION_CONFIGURATION_FILE_PATH));
            return props.getProperty(key);
		}catch(Exception ex){
			logger.debug("error : " + ex);
			return "";
		}
	}

	public static Date getDateFromString(String format, String value){
		try {
			if( value != null && value.length() > 0 ){
				return new SimpleDateFormat(format).parse(value);
			}
		} catch (Exception e) {
			logger.debug("error : "+e);
		}
		return null;
	}
	
	public static String getStringFromDate(String format, Date value){
		try {
			return new SimpleDateFormat(format).format(value);
		} catch (Exception e) {
			logger.debug("error : "+e);
		}
		return "";
	}
	
	public static Date today(){
		return new Date();
	}
	
	
    public static Object getGreenMessage(HttpServletRequest request){
        if(request.getSession().getAttribute("greenMessage") != null){
            Object message = request.getSession().getAttribute("greenMessage");
            request.getSession().removeAttribute("greenMessage");
            return message;
        }
        return "";
    }

    public static Object getErrorMessage(HttpServletRequest request){
        if(request.getSession().getAttribute("redMessage") != null){
            Object message = request.getSession().getAttribute("redMessage");
            request.getSession().removeAttribute("redMessage");
            return message;
        }
        return "";
    }

    public static Object getBlueMessage(HttpServletRequest request){
        if(request.getSession().getAttribute("blueMessage") != null){
            Object message = request.getSession().getAttribute("blueMessage");
            request.getSession().removeAttribute("blueMessage");
            return message;
        }
        return "";
    }

	public static java.util.Date startOfDate(java.util.Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
		}

	public static java.util.Date endOfDate(java.util.Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		return cal.getTime();
	}

	public static java.util.Date prevDay(java.util.Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, -1);
		return cal.getTime();
	}

	public static java.util.Date nextDay(java.util.Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, +1);
		return cal.getTime();
	}

/*	public static void setEditDeleteLinkOnAbstractEntity(AbstractBaseEntity entity, String moduleName){
		if(entity != null && !Utils.isNullOrEmpty(moduleName)){
			entity.setEditLink("<a href='./"+moduleName+".html?id="+entity.getId()+"'>edit</a>");
			entity.setDeleteLink("<a href='javascript:deleteLinkClicked(\"./"+moduleName+"Delete.html?id="+entity.getId()+"\")'>delete</a>");
		}

	}*/

         private static final String EMAIL_REGEXP_EXT = "/^\".+\" <[a-z0-9]+([_\\.-][a-z0-9]+)*@([a-z0-9]+([\\.-][a-z0-9]+)*)+\\.[a-z]{2,}?>$/i";
    private static final String EMAIL_REGEXP = "/^[a-z0-9]+([_\\.-][a-z0-9]+)*@([a-z0-9]+([\\.-][a-z0-9]+)*)+\\.[a-z]{2,}?$/i";
    private static final String NUMBER_REGEXP = "/^[0-9]+$/";
    private static final String DECIMAL_REGEXP = "/^[0-9.]+$/";
    private static final String US_PHONE_REGEXP = "/^(1-)?\\d{3}[\\s-.\\\\]?\\d{3}[\\s-.\\\\]?\\d{4}$/i";

    public static boolean isEmpty(String str) {
        if (str == null)
            return true;

        if (str.trim().length() == 0)
            return true;

        return false;
    }

    public static boolean isEmpty(List list) {
        if (list == null)
            return true;

        if (list.size() < 1)
            return true;

        return false;
    }



    public static boolean isValidDate(String dstr) {
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date d = fmt.parse(dstr);
        } catch (ParseException ex) {
            return false;
        }

        return true;
    }

    public static java.sql.Date stringToDate(String sDate)
              {
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
                  long dl = 0;
                  try {
                      dl = fmt.parse(sDate).getTime();
                  } catch (ParseException e) {
                      logger.debug("---- error in date parse ---- " + e);
                  }
                  return new java.sql.Date(dl);
    }

    public static java.sql.Date stringToTime(String sDate)
            throws ParseException {
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
        long dl = fmt.parse(sDate).getTime();
        return new java.sql.Date(dl);
    }

    public static String dateToString(java.util.Date date) {
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
        return fmt.format(date);
    }

    public static String timeToString(java.util.Date date) {
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
        return fmt.format(date);
    }

    public static String getTimeToString(java.util.Date date) {
        SimpleDateFormat fmt = new SimpleDateFormat("hh:mm a");
        return fmt.format(date);
    }

    public static java.sql.Date getTodaysSqlDate() {
        return new java.sql.Date((new java.util.Date()).getTime());
    }

    /**
     * ****************************** Import *************************************
     */
    // if between 12AM - 9AM then reduce date
    public static Date getOperationTime() {
        Date d = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(d);

        int hr = c.get(Calendar.HOUR_OF_DAY);
        //between 12AM - 9AM
        if (hr >= 0 && hr < 9) {
            c.add(Calendar.DATE, -1);
        }

        return c.getTime();
    }

    /**
     * ****************************** End Import *************************************
     */

    /*
     * With java.util.Date
     */
    public static java.util.Date startDateOfYear(java.util.Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.YEAR, cal.get(Calendar.YEAR));
        cal.set(Calendar.MONTH, 0);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static java.util.Date endDateOfYear(java.util.Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.YEAR, cal.get(Calendar.YEAR));
        cal.set(Calendar.MONTH, 11); // 11 = december
        cal.set(Calendar.DAY_OF_MONTH, 31);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }



    public static java.util.Date xlsStrToDate(String sDate)
            throws ParseException {
        SimpleDateFormat fmt = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
        return new java.util.Date(fmt.parse(sDate).getTime());
    }

    public static java.util.Date strToDate(String sDate)
            throws ParseException {
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
        return new java.util.Date(fmt.parse(sDate).getTime());
    }

    public static java.util.Date validStrToDate(String sDate) {
        try {
            SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
            return new java.util.Date(fmt.parse(sDate).getTime());
        } catch (ParseException ex) {
            // dummy
        }
        return null;
    }

    public static java.util.Date validGeneralStrToDate(String sDate) {
        if (isEmpty(sDate))
            return null;

        java.util.Date date = null;

        try {
            SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
            date = new java.util.Date(fmt.parse(sDate).getTime());
        } catch (Exception ex1) {
        }

        if (date == null) {
            try {
                SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yy");
                date = new java.util.Date(fmt.parse(sDate).getTime());
            } catch (Exception ex2) {
            }
        }
        if (date == null) {
            try {
                SimpleDateFormat fmt = new SimpleDateFormat("dd.MM.yy");
                date = new java.util.Date(fmt.parse(sDate).getTime());
            } catch (Exception ex2) {
            }
        }
        if (date == null) {
            try {
                SimpleDateFormat fmt = new SimpleDateFormat("dd.MM.yyyy");
                date = new java.util.Date(fmt.parse(sDate).getTime());
            } catch (Exception ex2) {
            }
        }
        if (date == null) {
            try {
                SimpleDateFormat fmt = new SimpleDateFormat("dd MM yy");
                date = new java.util.Date(fmt.parse(sDate).getTime());
            } catch (Exception ex2) {
            }
        }
        if (date == null) {
            try {
                SimpleDateFormat fmt = new SimpleDateFormat("dd MM yyyy");
                date = new java.util.Date(fmt.parse(sDate).getTime());
            } catch (Exception ex2) {
            }
        }
        if (date == null) {
            try {
                SimpleDateFormat fmt = new SimpleDateFormat("dd-MM-yy");
                date = new java.util.Date(fmt.parse(sDate).getTime());
            } catch (Exception ex2) {
            }
        }
        if (date == null) {
            try {
                SimpleDateFormat fmt = new SimpleDateFormat("dd-MM-yyyy");
                date = new java.util.Date(fmt.parse(sDate).getTime());
            } catch (Exception ex2) {
            }
        }
        if (date == null) {
            try {
                SimpleDateFormat fmt = new SimpleDateFormat("dd-MMM-yyyy");
                date = new java.util.Date(fmt.parse(sDate).getTime());
            } catch (Exception ex3) {
            }
        }
        if (date == null) {
            try {
                SimpleDateFormat fmt = new SimpleDateFormat("dd MM yyyy HH:mm:ss");
                date = new java.util.Date(fmt.parse(sDate).getTime());
            } catch (Exception ex4) {
            }
        }
        if (date == null) {
            try {
                SimpleDateFormat fmt = new SimpleDateFormat("dd MMM yyyy");
                date = new java.util.Date(fmt.parse(sDate).getTime());
            } catch (Exception ex5) {
            }
        }
        if (date == null) {
            try {
                SimpleDateFormat fmt = new SimpleDateFormat("dd-MMM-yyyy hh:mm aaa");
                date = new java.util.Date(fmt.parse(sDate.replaceAll("/", "-")).getTime());
            } catch (Exception ex6) {
            }
        }
        if (date == null) {
            try {
                SimpleDateFormat fmt = new SimpleDateFormat("dd-MMM-yy");
                date = new java.util.Date(fmt.parse(sDate.replaceAll("/", "-")).getTime());
            } catch (Exception ex6) {
            }
        }


        if (date == null)
            return null;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        //log.debug("year="+year);
        if (year < 50)
            year += 2000;
        else if (year < 100)
            year += 1900;

        cal.set(Calendar.YEAR, year);
        return cal.getTime();
    }

    public static java.util.Date lastDayOfMonth(int month, int year)
            throws ParseException {

        int day = 0;
        if (month >= 1 && month <= 12) {
            if (month == 4 || month == 6 || month == 9 || month == 11) {
                day = 30;
            } else if (month == 2) {
                if ((year % 400 == 0) || (year % 4 == 0 && year % 100 != 0))
                    day = 29;
                else
                    day = 28;
            } else {
                day = 31;
            }
        } else {
            throw new ParseException("Invalid Month", month);
        }

        String lastDay = day + "/" + month + "/" + year;
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
        return new java.util.Date(fmt.parse(lastDay).getTime());
    }

    public static java.util.Date firstDayOfMonth(int month, int year)
            throws ParseException {
        int day = 1;
        String lastDay = day + "/" + month + "/" + year;
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
        return new java.util.Date(fmt.parse(lastDay).getTime());
    }

    public static java.util.Date validLastDayOfMonth(int month, int year) {
        try {
            return lastDayOfMonth(month, year);
        } catch (ParseException ex) {
            return null;
        }
    }



    public static String dateToNotNullStr(java.util.Date date) {
        if (date == null) return "";
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
        return fmt.format(date);
    }

    public static String dateToStr(java.util.Date date) {
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
        return fmt.format(date);
    }

    public static String dateToStrForSpecialReport(java.util.Date date) {
        SimpleDateFormat fmt = new SimpleDateFormat("dd_MM_yyyy");
        return fmt.format(date);
    }


    //Added by ayon
    public static String year(String date) {
        ;
        int index = date.indexOf("/");
        return date.substring(date.lastIndexOf("/") + 1, date.length());
    }
    //end of adding


    public static String dayTextForFileName() {
        SimpleDateFormat fmt = new SimpleDateFormat("dd-MM-yyyy");
        return fmt.format(new Date());
    }

    public static String todayWithTime() {
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
        return fmt.format(new Date());
    }

    public static Date getDateFromToday(int d) {
        try {
            SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, d);
            return new Date(fmt.parse(fmt.format(cal.getTime())).getTime());
        } catch (ParseException ex) {
            // dummy
        }
        return null;
    }

    public static String lastWeek() {
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -7);
        return fmt.format(cal.getTime());
    }

    public static String lastMonth() {
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        cal.roll(Calendar.MONTH, -1);
        return fmt.format(cal.getTime());
    }

    public static String lastYear() {
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        cal.roll(Calendar.YEAR, -1);
        return fmt.format(cal.getTime());
    }

    public static String curRollYear(int i) {
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        cal.roll(Calendar.YEAR, i);
        return fmt.format(cal.getTime());
    }

    public static String nextWeek() {
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 7);
        return fmt.format(cal.getTime());
    }

    public static String month(java.util.Date day) {
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MMMMM/yyyy");
        String date = fmt.format(day);
        int index = date.indexOf("/");
        return date.substring(index + 1, date.lastIndexOf("/"));
    }

    public static Date getNHoursBackTime(int n) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR_OF_DAY, (-1) * n);
        return cal.getTime();
    }

    public static String intToMonthName(int month) {

        switch (month) {

            case 1:
                return "January";
            case 2:
                return "February";
            case 3:
                return "March";
            case 4:
                return "April";
            case 5:
                return "May";
            case 6:
                return "June";
            case 7:
                return "July";
            case 8:
                return "August";
            case 9:
                return "September";
            case 10:
                return "October";
            case 11:
                return "November";
            default:
                return "December";

        }
    }

    public static int getDiffDateBetween(java.util.Date toDate, java.util.Date fromDate) {
        double toMilliSecs = toDate.getTime();
        double fromMilliSecs = fromDate.getTime();

        return (new Double((toMilliSecs - fromMilliSecs) / 86400000)).intValue(); //-->> 864000 = 24 * 60 * 60 * 1000
    }


    /*
    * BigDecimal Formatter
    */

    public static String bigDecimalToString(BigDecimal n) {
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMinimumFractionDigits(2);
        nf.setMaximumFractionDigits(2);
        return nf.format(n.doubleValue());
    }

    public static boolean isValidEmail(String email) {
        Perl5Util perl5Util = new Perl5Util();

        if (perl5Util.match(EMAIL_REGEXP, email))
            return true;
        else if (perl5Util.match(EMAIL_REGEXP_EXT, email))
            return true;

        return false;
    }

    public static boolean isValidEmailOnly(String email) {
        Perl5Util perl5Util = new Perl5Util();

        if (perl5Util.match(EMAIL_REGEXP, email))
            return true;

        return false;
    }

    public static boolean isValidNumber(String number) {
        Perl5Util perl5Util = new Perl5Util();
        if (!perl5Util.match(NUMBER_REGEXP, number))
            return false;
        return true;
    }

    public static boolean isValidDecimal(String number) {
        Perl5Util perl5Util = new Perl5Util();
        if (!perl5Util.match(DECIMAL_REGEXP, number))
            return false;
        return true;
    }

    public static boolean isValidUSPhone(String phone) {
        Perl5Util perl5Util = new Perl5Util();

        if (!perl5Util.match(US_PHONE_REGEXP, phone))
            return false;

        return true;
    }

    public static Date incDate(Date d) {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        c.add(Calendar.DATE, 1);
        return c.getTime();
    }

    public static Date decDate(Date d) {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        c.add(Calendar.DATE, -1);
        return c.getTime();
    }


    public static byte[] getBytesFromFile(File file) throws IOException {
        InputStream is = new FileInputStream(file);
        long length = file.length();
        if (length > Integer.MAX_VALUE) {
            // File is too large
            return null;
        }

        byte[] bytes = new byte[(int) length];

        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length
                && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
            offset += numRead;
        }

        if (offset < bytes.length) {
            throw new IOException("Could not completely read file " + file.getName());
        }

        is.close();
        return bytes;
    }

    public static String md5(String str) {
        DigestUtils du = new DigestUtils();
        return du.md5Hex(str);
    }

    public static String getReferer(HttpServletRequest request) {
        return (Utils.isEmpty(request.getHeader("Referer"))
                ? request.getContextPath() + "/index.html"
                : request.getHeader("Referer"));
    }

    public static String getStringFromFile(String filePath) throws IOException {
        FileInputStream fis = new FileInputStream(filePath);
        int x = fis.available();
        byte b[] = new byte[x];
        fis.read(b);
        fis.close();
        return new String(b);
    }

    public static java.util.Date getTodaysDate() {
        return new java.util.Date();
    }

    public static java.util.Date getOneWeekBack(java.util.Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, -7);
        return cal.getTime();
    }

    public static Object[] addAnObject(Object[] mainArr, Object newOne) {
        Object[] newArr = new Object[mainArr.length + 1];
        for (int i = 0; i < mainArr.length; i++) {
            newArr[i] = mainArr[i];
        }
        newArr[mainArr.length] = newOne;
        return newArr;
    }

    public static Date get24HoursBackTime() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR_OF_DAY, -24);
        return cal.getTime();
    }

    public static void setGreenMessage(HttpServletRequest request, String message) {
        HttpSession session = request.getSession();
        List greenMessages = (List) session.getAttribute("greenMessage");
        if (greenMessages == null) {
            greenMessages = new ArrayList();
        }
        greenMessages.add(message);
        session.setAttribute("greenMessage", greenMessages);
    }


    public static void setErrorMessage(HttpServletRequest request, String error) {
        HttpSession session = request.getSession();
        List redMessages = (List) session.getAttribute("redMessage");
        if (redMessages == null) {
            redMessages = new ArrayList();
        }
        redMessages.add(error);
        session.setAttribute("redMessage", redMessages);
    }

    public static void setBlueMessage(HttpServletRequest request, String error) {
        HttpSession session = request.getSession();
        List redMessages = (List) session.getAttribute("blueMessage");
        if (redMessages == null) {
            redMessages = new ArrayList();
        }
        redMessages.add(error);
        session.setAttribute("blueMessage", redMessages);
    }

    public static double round(double num, int prec) {
        double offset = Math.pow(10.0, prec);
        long roundVal = Math.round(num * offset);
        return roundVal / offset;
    }

    public static String getTimeDifference(java.util.Date date1, java.util.Date date2) {
        if (date1 == null || date2 == null)
            return "";
        long dateTime1 = date1.getTime();
        long dateTime2 = date2.getTime();
        long dif = 0;
        if (dateTime1 > dateTime2) {
            dif = dateTime1 - dateTime2;
        } else {
            dif = dateTime2 - dateTime1;
        }
        Time time = new Time(dif);
        long day = dif / (24 * 60 * 60 * 1000);
        long hour = dif / (60 * 60 * 1000) - day * 24;
        long min = dif / (60 * 1000) - (hour + day * 24) * 60;
        long sec = dif / 1000 - ((hour + day * 24) * 60 + min) * 60;
        StringBuffer buffer = new StringBuffer();
        if (day > 0) {
            buffer.append(day);
            if (day > 1)
                buffer.append(" days ");
            else
                buffer.append(" day ");
        }
        buffer.append(hour + "hr " + min + "min " + sec + "sec");
        return buffer.toString();
    }

    public static String leftPad(String s, int length, char pad) {
        StringBuffer buffer = new StringBuffer(s);
        while (buffer.length() < length) {
            buffer.insert(0, pad);
        }
        return buffer.toString();
    }
/*
    public static String getJobNumber(Counter counter, String prefix) {
        Long counterNum = (new Long(counter.getValue()) + 1) % 10000;
        counter.setValue(counterNum.toString());
        return prefix + Utils.leftPad(counter.getValue(), 4, '0');
    }

    public static String getMrNumber(Counter counter, String prefix) {
        Long counterNum = (new Long(counter.getValue()) + 1) % 100000;
        counter.setValue(counterNum.toString());

        Date today = new Date();
        int year = today.getYear();
        year = year % 100;
        int month = today.getMonth();
        month = month % 100 + 1;
        int oldMonth = (counter.getUpdated().getMonth()) + 1;
        if (oldMonth != month) {
            counter.setValue("1");
        }

        return prefix +
                Utils.leftPad(Integer.toString(year), 2, '0') + "-" +
                Utils.leftPad(Integer.toString(month), 2, '0') + "-" +
                Utils.leftPad(counter.getValue(), 5, '0');

    }

    public static String getTrNumber(Counter counter, String prefix) {
        Long counterNum = (new Long(counter.getValue()) + 1) % 10000;
        counter.setValue(counterNum.toString());

        Date today = new Date();
        int year = today.getYear();
        year = year % 100;
        int month = today.getMonth();
        month = month % 100 + 1;
        int oldMonth = (counter.getUpdated().getMonth()) + 1;
        if (oldMonth != month) {
            counter.setValue("1");
        }

        return prefix +
                Utils.leftPad(Integer.toString(year), 2, '0') + "-" +
                Utils.leftPad(Integer.toString(month), 2, '0') + "-" +
                Utils.leftPad(counter.getValue(), 4, '0');
    }

    //new addition
    public static String getChalanNumberEmpty(Counter counter) {
        Date today = new Date();
        Long counterNum = (new Long(counter.getValue()) + 1) % 10000;

        counter.setValue(counterNum.toString());

        int year = today.getYear();
        year = year % 100;
        int month = today.getMonth();
        month = month % 100 + 1;
        int oldYear = (counter.getUpdated().getYear()) % 100;
        if (oldYear != year) {
            counter.setValue("1");
        }

        return "MT-" + Utils.leftPad(Integer.toString(year), 2, '0') + "-" +
                Utils.leftPad(Integer.toString(month), 2, '0') + "-"
                + Utils.leftPad(counter.getValue(), 5, '0');
    }

    public static String getChalanNumberLaden(Counter counter) {
        Date today = new Date();
        Long counterNum = (new Long(counter.getValue()) + 1) % 10000;

        counter.setValue(counterNum.toString());
        log.debug("----------------------------");
        log.debug("");
        int year = today.getYear();
        year = year % 100;
        ////////////////////////////////////////////
        int month = today.getMonth();
        month = month % 100 + 1;
        int oldYear = (counter.getUpdated().getYear()) % 100;
        if (oldYear < year) {
            counter.setValue("1");
        }
        /////////////////////////////////////////////

        return "LD-" + Utils.leftPad(Integer.toString(year), 2, '0') + "-" +
                Utils.leftPad(Integer.toString(month), 2, '0') + "-"
                + Utils.leftPad(counter.getValue(), 5, '0');
    }

    public static String getShutOutNumber(Counter counter) {

        Long counterNum = (new Long(counter.getValue()) + 1) % 10000;

        counter.setValue(counterNum.toString());
        //counter.save();
        return Utils.leftPad(counter.getValue(), 4, '0');
    }

    //added by rony after 2008/12/14
    public static double getRoundValue(double number) {
        double num = number;
        int temp = (int) (num * 100);
        double result = temp / 100.0;
        return result;
    }
*/
    public static boolean isSameDay(java.util.Date one, java.util.Date two) {
        if (one == null && two == null) return true;
        else if (one == null || two == null) return false;

        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
        return fmt.format(one).equals(fmt.format(two));
    }

    public static String arrayToString(Object[] objArray) {
        String str = "";
        for (int i = 0; i < objArray.length; i++) {
            str += objArray[i];
        }
        return str;
    }

    //added by rony
    //test purpose
    //give the one year back date
    public static java.util.Date getOneYearBack(java.util.Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.YEAR, -1);
        return cal.getTime();
    }


    public static java.util.Date getDateFromDateAndDiff(java.util.Date date, int d) {
        try {
            SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.DATE, d);
            return new Date(fmt.parse(fmt.format(cal.getTime())).getTime());
        } catch (ParseException ex) {
            // dummy
        }
        return null;
    }


    /* Groups the given list according to the index array supplied.
     * If singleList is true then returns the grouping in a single ArrayList
     * If suppress is true then only first row of a group gontains the elements
     *   indicated by the index array and the rest of the rows contains null in
     *   the place of the grouping elements
     *
     * Returns a List which contains the grouping (ie. the seperated Lists)
     */
    public static List groupRows(List list, boolean singleList, boolean suppress, int[] index) {
        ArrayList rList = new ArrayList();
        ArrayList sList = new ArrayList();
        if (index.length == 0) {
            rList.add(list);
            return rList;
        }

        TreeMap map = new TreeMap();
        Iterator i = list.iterator();
        while (i.hasNext()) {
            Object[] objs = (Object[]) i.next();
            Object[] newObjs = objs.clone();
            String key = "";
            for (int j = 0; j < index.length; j++) {
                key += "|" + String.valueOf(objs[index[j]]);
                if (suppress) newObjs[index[j]] = null;
            }

            ArrayList group = new ArrayList();
            if (map.get(key) != null) {
                group = (ArrayList) map.get(key);
                if (suppress) objs = newObjs;
            }

            group.add(objs);
            map.put(key, group);
        }

        Iterator j = map.keySet().iterator();
        while (j.hasNext()) {
            String key = (String) j.next();
            if (singleList) sList.addAll((ArrayList) map.get(key));
            else rList.add(map.get(key));
        }

        if (sList.size() > 0) rList.add(sList);
        return rList;
    }

    //Added by Tusher
    /* Example: for 2 decimal place rounding use dp = 100 */
    public static Double roundDouble(Double s, int dp) {
        if (s == null) return null;
        return (Math.round(s * dp) * 1.0) / dp;
    }

    public static String getRoundValueString(Double number) {
        String numberStr = number.toString();
        String mask = "0.00";
        DecimalFormat df = new DecimalFormat(mask);
        numberStr = df.format(number);
        // remove extra spaces!
        return numberStr.replaceAll("^\\s+", "").replaceAll("\\b\\s{2,}\\b", " ");
    }



    public static String makeRptLayoutLabel(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }

        StringBuffer result = new StringBuffer();

        /*
        * Pretend space before first character
        */
        char prevChar = ' ';

        /*
        * Change underscore to space, insert space before capitals
        */
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '_') {
                result.append(' ');
            } else if (prevChar == ' ' || prevChar == '_') {
                result.append(Character.toUpperCase(c));
            } else if (Character.isUpperCase(c) && !Character.isUpperCase(prevChar)) {
                /*
                 * Insert space before start of word if camel case
                 */
                result.append(' ');
                result.append(Character.toUpperCase(c));
            } else {
                result.append(c);
            }
            prevChar = c;
        }
        return result.toString();
    }


    public static String getMesurment(Double l, Double h, Double w) {
        String lStr = (l == null ? "0" : l.toString());
        String hStr = (h == null ? "0" : h.toString());
        String wStr = (w == null ? "0" : w.toString());
        String result;
        String mask = "#.##";
        DecimalFormat df = new DecimalFormat(mask);
        lStr = (l == null ? "0" : df.format(l));
        hStr = (h == null ? "0" : df.format(h));
        wStr = (w == null ? "0" : df.format(w));
        result = lStr + "X" + hStr + "X" + wStr;

        // remove extra spaces!
        return result.replaceAll("^\\s+", "").replaceAll("\\b\\s{2,}\\b", " ");
    }

    public static String validTimeToString(java.util.Date date) {
        if (date != null) {
            SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
            return fmt.format(date);
        }
        return "";
    }

    /**
     * AUTHOR:Faisal
     * Used in CargoReceiveTimeSheetEditController
     */
    public static String dateTimeToString(java.util.Date date) {
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
        return fmt.format(date);
    }

    public static java.util.Date validStrToDateTime(String sDate) {
        try {
            SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
            return new java.util.Date(fmt.parse(sDate).getTime());
        } catch (ParseException ex) {
            // dummy
        }
        return null;
    }

    //@author: Rony 03 Mar 2010
    public static String dateToStringForFileName(java.util.Date date) {
        SimpleDateFormat fmt = new SimpleDateFormat("dd-MM-yyyy");
        return fmt.format(date);
    }

    //added by rony
    //test purpose
    //give the one year back date
    public static java.util.Date getThreeMonthBack(java.util.Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, -3);
        return cal.getTime();
    }

    //********************************* Import *********************************//


    private static int getCharValue(char ch) {

        switch (ch) {
            case 'A':
                return 10;
            case 'B':
                return 12;
            case 'C':
                return 13;
            case 'D':
                return 14;
            case 'E':
                return 15;
            case 'F':
                return 16;
            case 'G':
                return 17;
            case 'H':
                return 18;
            case 'I':
                return 19;
            case 'J':
                return 20;
            case 'K':
                return 21;
            case 'L':
                return 23;
            case 'M':
                return 24;
            case 'N':
                return 25;
            case 'O':
                return 26;
            case 'P':
                return 27;
            case 'Q':
                return 28;
            case 'R':
                return 29;
            case 'S':
                return 30;
            case 'T':
                return 31;
            case 'U':
                return 32;
            case 'V':
                return 34;
            case 'W':
                return 35;
            case 'X':
                return 36;
            case 'Y':
                return 37;
            case 'Z':
                return 38;
            default:
                return (ch - '0');
        }
    }

    public static boolean isValidContainerNumber(String containerNumber, Map messageHolder) {

        int chkSum = 0;

        if (containerNumber == null) {
            messageHolder.put("massage", "Invalid, Can not be empty");
            return false;
        } else if (containerNumber.length() != 11) {
            messageHolder.put("massage", "Invalid number of digit.");
            return false;
        } else {
            for (int i = 0; i < (containerNumber.length() - 1); i++) {

                char ch = containerNumber.charAt(i);

                if ((ch < 'A' || ch > 'Z') && (ch < '0' || ch > '9')) {
                    messageHolder.put("massage", "Invalid Character \"" + ch + "\"");
                    return false;
                }

                chkSum += (getCharValue(ch) * Math.pow(2, i));
            }

            char ch = containerNumber.charAt(containerNumber.length() - 1);

            if ((ch < 'A' || ch > 'Z') && (ch < '0' || ch > '9')) {
                messageHolder.put("massage", "Invalid Character \"" + ch + "\"");
                return false;
            }

            int ldig = chkSum % 11;
            if (ldig == 10) {
                ldig = 0;
            }

            if (ldig == getCharValue(ch)) {
                messageHolder.put("massage", "Valid Container Number: \""
                        + containerNumber + "\"");
                return true;
            } else {
                messageHolder.put("massage", "Check Sum Failed for number \"" + containerNumber
                        + "\" Last Digit should be: \"" + ldig + "\"");
                return false;
            }
        }
    }


    public static java.util.Date stringToDateWithTime(String sDate) {
        java.util.Date date = null;
        if (isEmpty(sDate))
            return null;
        else {
            try {
                SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy h:mm a");
                date = new java.util.Date(fmt.parse(sDate).getTime());
            } catch (Exception ex4) {
            }

        }
        if (date == null)
            return null;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        //log.debug("year="+year);
        if (year < 50)
            year += 2000;
        else if (year < 100)
            year += 1900;

        cal.set(Calendar.YEAR, year);
        return cal.getTime();

    }

    public static List makeSizeSummary(List<Map> dateList, String contSize, String contTues, String contType, String rowKeyName, String rowTitle) {
        Set<String> rows = new TreeSet<String>();
        Set<String> cols = new TreeSet<String>();
        Map<String, Integer> tuesMap = new HashMap<String, Integer>();
        Map<String, Integer> summery = new HashMap<String, Integer>();
        List resultRow = new ArrayList();
        List result = new ArrayList();

        for (Map dateRow : dateList) {
            if (dateRow.get(contSize) == null ||
                    dateRow.get(contType) == null || dateRow.get(contTues) == null) {
                continue;
            }
            String rowKey = (dateRow.get(rowKeyName) == null ? "N/A" : (String) dateRow.get(rowKeyName));
            String colKey = (String) dateRow.get(contSize) + "-" + (String) dateRow.get(contType);
            rows.add(rowKey);
            cols.add(colKey);
            if (tuesMap.get(colKey) == null) {
                tuesMap.put(colKey, ((Number) dateRow.get(contTues)).intValue());
            }

            if (summery.get(rowKey + colKey) == null) {
                summery.put(rowKey + colKey, new Integer(0));
            }
            summery.put(rowKey + colKey, summery.get(rowKey + colKey) + 1);
        }

        // Title row
        resultRow.add(rowTitle);
        for (String col : cols) {
            resultRow.add(col);
        }
        resultRow.add("Total-Box");
        resultRow.add("Total-Teus");
        result.add(resultRow.toArray());

        // detail rows
        Integer[] total = new Integer[cols.size() + 2];
        for (int i = 0; i < cols.size() + 2; i++) total[i] = 0;

        for (String row : rows) {
            resultRow = new ArrayList();
            resultRow.add(row);
            int totalBox = 0;
            int totalTues = 0;
            int i = 0;
            for (String col : cols) {
                Integer boxSum = summery.get(row + col);
                if (boxSum == null)
                    boxSum = 0;
                resultRow.add(boxSum);
                totalBox += boxSum;
                //log.debug( "DBG :: col [" + col + "] "
                totalTues += boxSum * tuesMap.get(col);
                total[i++] += boxSum;
            }
            total[i++] += totalBox;
            total[i++] += totalTues;
            resultRow.add(totalBox);
            resultRow.add(totalTues);
            result.add(resultRow.toArray());
        }

        // total row
        resultRow = new ArrayList();
        resultRow.add("Total");

        for (Integer tot : total) {
            resultRow.add(tot);
        }
        result.add(resultRow.toArray());

        return result;
    }


    public static boolean checkDoubleEqual(double noOne, double noTwo) {

        double diff = Math.abs(noOne - noTwo);

        if (diff > 0.0001)
            return false;
        else return true;
    }

    public static Date getYearBackTime(int year) {
        Calendar cal = Calendar.getInstance();

        cal.add(Calendar.YEAR, -year);
        return cal.getTime();
    }

    public static int getYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.YEAR);

    }

    public static boolean isValidXlsStrToDate(String sDate) {
        Date date = null;
        try {
            SimpleDateFormat fmt = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
             date = new java.util.Date(fmt.parse(sDate).getTime());

        }catch (ParseException e) {
        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
    }
        if(date != null)
            return true ;
        return false;

}
    public static String getXlsDateToString(String strDate){
        Date date = null;
        if(Utils.isValidXlsStrToDate(strDate)){
            try {
                date = Utils.xlsStrToDate(strDate);
            } catch (ParseException e) {
                e.printStackTrace();
                logger.debug("-EXCEPTION IN -getXlsDateToString method.");
            }
        }
        return  Utils.dateTimeToString(date);
    }

     public static String[] getFileNames(String directoryName){
    	logger.debug("Scanning Directory Name is: "+directoryName);
    	File dir = new File(directoryName);
	    FilenameFilter filter = new FilenameFilter() {
	        @Override
			public boolean accept(File dir, String name) {
	            return name.endsWith(".csv");
	        }
	      };
	      return dir.list(filter);
    }

    public static void moveFiles(String file, String fromDirectory, String toDirectory){
    	  File afile = new File(fromDirectory + file);
          if(!new File(toDirectory).exists()) {
              new File(toDirectory).mkdirs();
          } else {
              logger.debug("AMLOG:: To directory exists.");
          }
    	  logger.debug("Moving file "+ afile.getName()+" From: "+fromDirectory +" To: "+toDirectory);
    	  afile.renameTo(new File(toDirectory + afile.getName()));
    }

    public static byte[] generateEmlFileWithCc( String from
    										   ,String[] to
    										   ,String[] cc
    										   ,String subject
    										   ,String[] attachFilesNames
    										   ,byte[][] attachFilesBytes
    										   ,String[] attachFilesContentTypes
    										   ,String mailBody) throws Exception {

		Properties props = System.getProperties();
		Session session = Session.getInstance(props, null);

		Message message = new MimeMessage(session);
		message.setHeader("X-Unsent", "1");
		message.setFrom(new InternetAddress(from));

		InternetAddress[] toAddress = new InternetAddress[to.length];
		for (int i = 0; i < to.length; i++) {
			if (!Utils.isNullOrEmpty(to[i])) {
				toAddress[i] = new InternetAddress(to[i].trim());
			}
		}

		InternetAddress[] ccAddress = new InternetAddress[cc.length];
		for (int i = 0; i < cc.length; i++) {
			if (!Utils.isNullOrEmpty(cc[i])) {
				ccAddress[i] = new InternetAddress(cc[i].trim());
			}
		}

		message.setRecipients(Message.RecipientType.TO, toAddress);
		message.setRecipients(Message.RecipientType.CC, ccAddress);
		message.setSubject(subject);

		BodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setText(mailBody);

		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);
		//message.setContent(multipart);
		//message.setContent(new String(mailBody.getBytes(), "utf-8"), "text/html; charset=utf-8");

		for (int i = 0; i < attachFilesNames.length; i++) {
            if(!Utils.isNullOrEmpty(attachFilesNames[i])) {
                messageBodyPart = new MimeBodyPart();
                DataSource source = new ByteArrayDataSource(attachFilesBytes[i], attachFilesContentTypes[i]);
                messageBodyPart.setDataHandler(new DataHandler(source));
                messageBodyPart.setFileName(attachFilesNames[i]);
                multipart.addBodyPart(messageBodyPart);
                message.setContent(multipart);
            }
		}

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			message.writeTo(out);
		} finally {
			out.close();
		}
		return out.toByteArray();
    }

    public static void sendAnEmail( String from
            ,String[] to
            ,String[] bcc
            ,String subject
            ,String mailBody) throws Exception {

        final String username = Utils.getApplicationPropertyValue("mail.sender.user.name");
        final String password = Utils.getApplicationPropertyValue("mail.sender.password");

        Properties props = System.getProperties();
        props.put("mail.smtp.host", Utils.getApplicationPropertyValue("smtp.host.name"));
        props.put("mail.smtp.socketFactory.port", Utils.getApplicationPropertyValue("smtp.socketfactory.port"));
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", Utils.getApplicationPropertyValue("smtp.socketfactory.port"));

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        Message message = new MimeMessage(session);
        message.setHeader("X-Unsent", "1");
        message.setFrom(new InternetAddress(from));

        InternetAddress[] toAddress = new InternetAddress[to.length];
        for (int i = 0; i < to.length; i++) {
            if (!Utils.isNullOrEmpty(to[i])) {
                toAddress[i] = new InternetAddress(to[i].trim());
            }
        }
        InternetAddress[] bccAddress = new InternetAddress[bcc.length];
        for (int i = 0; i < bcc.length; i++) {
            if (!Utils.isNullOrEmpty(bcc[i])) {
                bccAddress[i] = new InternetAddress(bcc[i].trim());
            }
        }

        message.setRecipients(Message.RecipientType.TO, toAddress);
        message.setRecipients(Message.RecipientType.BCC, bccAddress);
        message.setSubject(subject);
        message.setText(mailBody);
        Transport.send(message);

    }

    public static String getEmailBody( String link) throws Exception {
		URL url = new URL(link);
	    InputStream is = url.openConnection().getInputStream();
	    BufferedReader reader = new BufferedReader( new InputStreamReader( is )  );
	    StringBuffer body = new StringBuffer();
	    String line = null;
	    while( ( line = reader.readLine() ) != null )  {
	       body.append(line);
	    }
	    reader.close();
    	return body.toString();
    }

    public static java.util.Date getDateAfterDay(int day,Date lastDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(lastDate);
        cal.add(Calendar.DATE, day);
        return cal.getTime();
    }
    public static boolean isToday(Date date) {
        return isSameDay(date, Calendar.getInstance().getTime());
    }
    public static String dateToStrWithFormat(java.util.Date date, String dateFormat) {
        SimpleDateFormat fmt = new SimpleDateFormat(dateFormat);
        return fmt.format(date);
    }

    public static boolean isBeforeDay(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            throw new IllegalArgumentException("The dates must not be null");
        }
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        return isBeforeDay(cal1, cal2);
    }


    public static boolean isBeforeDay(Calendar cal1, Calendar cal2) {
        if (cal1 == null || cal2 == null) {
            throw new IllegalArgumentException("The dates must not be null");
        }
        if (cal1.get(Calendar.ERA) < cal2.get(Calendar.ERA)) return true;
        if (cal1.get(Calendar.ERA) > cal2.get(Calendar.ERA)) return false;
        if (cal1.get(Calendar.YEAR) < cal2.get(Calendar.YEAR)) return true;
        if (cal1.get(Calendar.YEAR) > cal2.get(Calendar.YEAR)) return false;
        return cal1.get(Calendar.DAY_OF_YEAR) < cal2.get(Calendar.DAY_OF_YEAR);
    }

    public static String getWeekEndDatesBetween(Date formDate, Date toDate, int weekendDay) {
        String weekEndDate = "";
        int dayOfWeek = weekendDay;
        Calendar cal = new GregorianCalendar();
        Calendar cal2 = new GregorianCalendar();
        cal.setTime(formDate);
        cal.set(Calendar.DAY_OF_WEEK, dayOfWeek);
        cal2.setTime(toDate);
        while (cal.getTime().compareTo(cal2.getTime()) <= 0)
        {
            weekEndDate +="," + "'"+dateToStrWithFormat(cal.getTime(), "yyyy-MM-dd") +"'";
            cal.add(Calendar.DAY_OF_MONTH, 7);
        }
        weekEndDate = weekEndDate.substring(1,weekEndDate.length());
        return weekEndDate;
    }


    public static int getStringToInteger(String str) {
        if(!Utils.isEmpty(str)) {
            try {
                return  Integer.parseInt(str);
            }catch (Exception ex) {
                logger.debug("String to Integer casting exception " + ex);
            }
        }
        return 0;
    }

    public static int parseInteger(String num) {
        if(!isEmpty(num)) {
            return Integer.parseInt(num);
        }
        return  0;
    }
    public static List getDayNames() {
        String days[] = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        return Arrays.asList(days);
    }
    public static String formattingDateInDb(Date date) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return fmt.format(date);
    }
    public static java.util.Date stringToDate(String sDate, String format) {
        try {
            if(isEmpty(sDate))
                return null;
            SimpleDateFormat fmt = new SimpleDateFormat(format);
            return new java.util.Date(fmt.parse(sDate).getTime());
        } catch (ParseException ex) {
            // dummy
        }
        return null;
    }

    public static Map getJsonStringToMap(String clientResponseStr ) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(clientResponseStr,
                new TypeReference<HashMap<String,Object>>(){});
    }

    public static String generateUniqueId(String startsWith) {
        UUID idOne = UUID.randomUUID();
        String str=""+idOne;
        int uid=str.hashCode();
        String filterStr=""+uid;
        str=filterStr.replaceAll("-", "");
        return startsWith+""+str;
    }

    public static String encrypt(String Data, Key key) throws Exception {
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(Data.getBytes());
        String encryptedValue = new BASE64Encoder().encode(encVal);
        return encryptedValue;
    }

    public static String decrypt(String encryptedData, Key key) throws Exception {
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedData);
        byte[] decValue = c.doFinal(decordedValue);
        String decryptedValue = new String(decValue);
        return decryptedValue;
    }
    public static Key generateKey(byte[] keyValue) throws Exception {
        Key key = new SecretKeySpec(keyValue, ALGO);
        return key;
    }

    public static Date addNDaysInDate(Date date, int noOfDay) throws Exception {
        Calendar calendar = new GregorianCalendar(/* remember about timezone! */);
        calendar.setTime(date);
        calendar.add(Calendar.DATE, noOfDay);
        return calendar.getTime();
    }
}

