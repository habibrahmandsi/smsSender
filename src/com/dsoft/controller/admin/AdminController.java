package com.dsoft.controller.admin;

import com.dsoft.entity.DataModelBean;
import com.dsoft.entity.Person;
import com.dsoft.service.AdminJdbcService;
import com.dsoft.service.AdminService;
import com.dsoft.util.Constants;
import com.dsoft.util.Utils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@SessionAttributes({"", ""})
public class AdminController {

    private static Logger logger = Logger.getLogger(AdminController.class);
    @Autowired(required = true)
    private AdminService adminService;
    @Autowired(required = true)
    private AdminJdbcService adminJdbcService;

    @InitBinder
    protected void initBinder(ServletRequestDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.DATE_FORMAT);
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, null, new CustomDateEditor(dateFormat, false));
        binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
    }


    @RequestMapping(value = "/*/upsertPerson.do", method = RequestMethod.GET)
    public String getPerson(HttpServletRequest request, Model model) {
        logger.error("SMNLOG: :: upsert Person controller :: ");
        Long personId = request.getParameter("personId") != null ? Long.parseLong(request.getParameter("personId")) : 0;
        Person person = new Person();
        try {
            if (personId > 0) {
                person = adminService.getPerson(personId);
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        if (person == null) {
            logger.debug("ERROR:Failed to load person");
            Utils.setErrorMessage(request, Utils.getMessageBundlePropertyValue("person.load.failed.msg"));
            return "redirect:./userList.do";
        }

        model.addAttribute("person", person);
        return "common/person";
    }


    @RequestMapping(value = "/*/upsertPerson.do", method = RequestMethod.POST)
    public String savePerson(HttpServletRequest request, @ModelAttribute("person") Person person) {
        logger.debug("SMNLOG:: Person POST Controller::");
        Long personId = person.getId();
        try {
            logger.debug("SMNLOG:: person:: " + person);

            adminService.saveOrUpdateObject(person);

            if (personId != null)
                Utils.setGreenMessage(request, Utils.getMessageBundlePropertyValue("person.update.success.msg"));
            else
                Utils.setGreenMessage(request, Utils.getMessageBundlePropertyValue("person.save.success.msg"));
        } catch (Exception ex) {
            logger.error("Save user exception:: " + ex);
            if (personId != null)
                Utils.setErrorMessage(request, Utils.getMessageBundlePropertyValue("person.update.failed.msg"));
            else
                Utils.setErrorMessage(request, Utils.getMessageBundlePropertyValue("person.save.failed.msg"));

        }
        return "redirect:./personList.do";
    }

    @RequestMapping(value = "/*/deletePerson.do", method = RequestMethod.GET)
    public String deleteperson(HttpServletRequest request, Model model) {
        logger.error("SMNLOG: :: delete person controller :: ");
        Long personId = request.getParameter("personId") != null ? Long.parseLong(request.getParameter("personId")) : 0;
        Person person = new Person();
        try {
            if (personId > 0) {
                person = adminService.getPerson(personId);
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        if (person == null) {
            logger.debug("ERROR:Failed to load person");
            Utils.setErrorMessage(request, Utils.getMessageBundlePropertyValue("person.load.failed.msg"));
            return "redirect:./personList.do";
        } else {

            try {
                adminService.deletePerson(person);
                Utils.setGreenMessage(request, Utils.getMessageBundlePropertyValue("person.delete.success.msg"));
            } catch (Exception e) {
                e.printStackTrace();
                logger.debug("ERROR:Failed to delete person");
                Utils.setErrorMessage(request, Utils.getMessageBundlePropertyValue("person.delete.failed.msg"));
                return "redirect:./personList.do";
            }
        }

        model.addAttribute("person", person);

        return "redirect:./personList.do";
    }

    @RequestMapping(value = "/*/personList.do", method = RequestMethod.GET)
    public String personListView(HttpServletRequest request, Model model) {
        logger.error("SMNLOG: :: person view controller :: ");

        return "common/personList";
    }

    @RequestMapping(value = "/*/getPersons.do", method = RequestMethod.GET)
    public
    @ResponseBody
    DataModelBean getPersons(HttpServletRequest request) throws Exception {
        logger.info(":: Get Person List Ajax Controller ::");
        DataModelBean dataModelBean = new DataModelBean();
    /* this params is for dataTables */
        String[] tableColumns = "p.name,p.mobile_no,p.address".split(",");
        int start = request.getParameter(Constants.IDISPLAY_START) != null ? Integer.parseInt(request.getParameter(Constants.IDISPLAY_START)) : 0;
        int length = request.getParameter(Constants.IDISPLAY_LENGTH) != null ? Integer.parseInt(request.getParameter(Constants.IDISPLAY_LENGTH)) : 5;
        int sEcho = request.getParameter(Constants.sEcho) != null ? Integer.parseInt(request.getParameter(Constants.sEcho)) : 0;
        int iSortColIndex = request.getParameter(Constants.iSortCOL) != null ? Integer.parseInt(request.getParameter(Constants.iSortCOL)) : 0;
        String searchKey = request.getParameter(Constants.sSearch) != null ? request.getParameter(Constants.sSearch) : "";
        String sortType = request.getParameter(Constants.sortType) != null ? request.getParameter(Constants.sortType) : "asc";
        String sortColName = "";
        logger.debug("SMNLOG:iSortColIndex:" + iSortColIndex + " sortType:" + sortType + " searchKey:" + searchKey);

        // sorting related operation for data Tables

        sortColName = tableColumns[iSortColIndex];
        logger.debug("SMNLOG:sortColName:" + sortColName);

        String trackingDetailsDataStr = null;
        Map<String, Object> userDataMap;

        try {
            int totalRecords = adminService.getPersonEntitySize();
            if (length < 0) {
                userDataMap = adminJdbcService.getPersons(start, totalRecords, sortColName, sortType, searchKey);
            } else {
                userDataMap = adminJdbcService.getPersons(start, length, sortColName, sortType, searchKey);
            }


                /*
                * DataModelBean is a bean of Data table to
                * handle data Table search, paginatin operation very simply
                */
            dataModelBean.setAaData((List) userDataMap.get("data"));

            if (!Utils.isEmpty(searchKey)) {
                dataModelBean.setiTotalDisplayRecords((Integer) userDataMap.get("total"));
            } else {
                dataModelBean.setiTotalDisplayRecords(totalRecords);
            }
            dataModelBean.setiTotalRecords(totalRecords);
            dataModelBean.setsEcho(sEcho);
            dataModelBean.setiDisplayStart(start);
            dataModelBean.setiDisplayLength(totalRecords);

        } catch (Exception ex) {
            logger.error(":: ERROR:: Failed to load person details data:: " + ex);
        }

        return dataModelBean;
    }

}




